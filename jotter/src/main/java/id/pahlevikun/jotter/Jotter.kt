package id.pahlevikun.jotter

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import id.pahlevikun.jotter.event.ActivityEvent
import id.pahlevikun.jotter.event.FragmentEvent
import id.pahlevikun.jotter.util.LifecycleListener
import id.pahlevikun.jotter.util.Logger

class Jotter private constructor(private val builder: Builder) {

    private var listener: Listener? = null
    private var isLogEnabled: Boolean = false
    private var tag: String = TAG
    private var activityEvents: List<String> = listOf()
    private var fragmentEvents: List<String> = listOf()
    private var application: Application? = null

    init {
        application = builder.application
        isLogEnabled = builder.isLogEnabled
        listener = builder.listener
        tag = builder.tag
        activityEvents = builder.activityEvents
        fragmentEvents = builder.fragmentEvents

        Logger.let {
            it.logEnabled = isLogEnabled
            it.tag = tag
        }

        INSTANCE = this
    }

    class Builder(internal val application: Application) {

        internal var isLogEnabled: Boolean = false
        internal var listener: Listener? = null
        internal var tag: String = TAG
        internal var activityEvents = listOf(
            ActivityEvent.CREATE,
            ActivityEvent.START,
            ActivityEvent.RESUME,
            ActivityEvent.PAUSE,
            ActivityEvent.STOP,
            ActivityEvent.SAVE_INSTANCE_STATE,
            ActivityEvent.DESTROY
        )
        internal var fragmentEvents = listOf(
            FragmentEvent.PRE_ATTACH,
            FragmentEvent.ATTACH,
            FragmentEvent.CREATE,
            FragmentEvent.ACTIVITY_CREATE,
            FragmentEvent.PRE_CREATE,
            FragmentEvent.VIEW_CREATE,
            FragmentEvent.START,
            FragmentEvent.RESUME,
            FragmentEvent.PAUSE,
            FragmentEvent.STOP,
            FragmentEvent.SAVE_INSTANCE_STATE,
            FragmentEvent.DESTROY,
            FragmentEvent.VIEW_DESTROY,
            FragmentEvent.DETACH
        )

        fun setLogEnable(isLogEnabled: Boolean): Builder {
            this.isLogEnabled = isLogEnabled
            return this
        }

        fun setCustomTag(tag: String): Builder {
            this.tag = tag
            return this
        }

        fun setJotterListener(listener: Listener): Builder {
            this.listener = listener
            return this
        }

        fun setActivityEventFilter(events: List<String>): Builder {
            this.activityEvents = events
            return this
        }

        fun setFragmentEventFilter(events: List<String>): Builder {
            this.fragmentEvents = events
            return this
        }

        fun build() = Jotter(this)
    }

    fun setJotterListener(listener: Listener) {
        this.listener = listener
    }

    fun startListening() {
        LifecycleListener.register(
            application = this.builder.application,
            listener = this.listener,
            activityFilter = this.activityEvents,
            fragmentFilter = this.fragmentEvents
        )
        if (this.listener == null) {
            Logger.debug(Constant.LISTENER_MESSAGE)
        }
    }

    interface Listener {
        fun onReceiveActivityEvent(
            activity: Activity,
            @ActivityEvent event: String,
            bundle: Bundle? = null
        )

        fun onReceiveFragmentEvent(
            fragment: Fragment,
            context: Context? = null,
            @FragmentEvent event: String,
            bundle: Bundle? = null
        )
    }

    companion object {
        @JvmStatic
        @Volatile
        private var INSTANCE: Jotter? = null

        @Synchronized
        fun getInstance(): Jotter = INSTANCE
            ?: throw RuntimeException(Constant.INSTANCE_MESSAGE)

        @Synchronized
        fun getDefaultInstance(application: Application): Jotter {
            if (INSTANCE == null) {
                INSTANCE = Builder(application).build()
            }
            return INSTANCE!!
        }

        private const val TAG = "Jotter"
    }

    private object Constant {
        internal const val INSTANCE_MESSAGE =
            "Jotter isn't initialized yet. Please use Jotter.Builder(application)!"
        internal const val LISTENER_MESSAGE =
            "Listener not found, you can't receive callbacks, please set it first via Builder or setJotterListener API!"
    }
}