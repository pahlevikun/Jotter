package id.pahlevikun.activitylifecyclecallbacks

import android.app.Application
import id.pahlevikun.activitylifecyclecallbacks.listener.ActivityLifecycleListener
import id.pahlevikun.activitylifecyclecallbacks.listener.JotterListener
import id.pahlevikun.jotter.Jotter
import id.pahlevikun.jotter.event.ActivityEvent
import id.pahlevikun.jotter.event.FragmentEvent

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        // This is for registering the listener object
        // listenWithActivityLifecycleCallback()
        // init Jotter for listening to all event
        listenWithJotter()
    }

    private fun listenWithJotter() {
        Jotter
            .Builder(this)
            .setLogEnable(true)
            .setActivityEventFilter(listOf(ActivityEvent.CREATE, ActivityEvent.DESTROY))
            .setFragmentEventFilter(listOf(FragmentEvent.VIEW_CREATE, FragmentEvent.PAUSE))
            .setJotterListener(JotterListener)
            .build()
            .startListening()
    }

    private fun listenWithActivityLifecycleCallback() {
        registerActivityLifecycleCallbacks(ActivityLifecycleListener)
    }
}