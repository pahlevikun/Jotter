package id.pahlevikun.jotter.event

import androidx.annotation.StringDef
import id.pahlevikun.jotter.event.FragmentEvent.Companion.ACTIVITY_CREATE
import id.pahlevikun.jotter.event.FragmentEvent.Companion.ATTACH
import id.pahlevikun.jotter.event.FragmentEvent.Companion.CREATE
import id.pahlevikun.jotter.event.FragmentEvent.Companion.DESTROY
import id.pahlevikun.jotter.event.FragmentEvent.Companion.DETACH
import id.pahlevikun.jotter.event.FragmentEvent.Companion.PAUSE
import id.pahlevikun.jotter.event.FragmentEvent.Companion.PRE_ATTACH
import id.pahlevikun.jotter.event.FragmentEvent.Companion.PRE_CREATE
import id.pahlevikun.jotter.event.FragmentEvent.Companion.RESUME
import id.pahlevikun.jotter.event.FragmentEvent.Companion.SAVE_INSTANCE_STATE
import id.pahlevikun.jotter.event.FragmentEvent.Companion.START
import id.pahlevikun.jotter.event.FragmentEvent.Companion.STOP
import id.pahlevikun.jotter.event.FragmentEvent.Companion.VIEW_CREATE
import id.pahlevikun.jotter.event.FragmentEvent.Companion.VIEW_DESTROY

@Retention(AnnotationRetention.SOURCE)
@StringDef(
    PRE_ATTACH,
    ATTACH,
    CREATE,
    ACTIVITY_CREATE,
    PRE_CREATE,
    VIEW_CREATE,
    START,
    RESUME,
    PAUSE,
    STOP,
    SAVE_INSTANCE_STATE,
    DESTROY,
    VIEW_DESTROY,
    DETACH
)
annotation class FragmentEvent {
    companion object {
        const val PRE_ATTACH = "PRE_ATTACH"
        const val ATTACH = "ATTACH"
        const val ACTIVITY_CREATE = "ACTIVITY_CREATE"
        const val CREATE = "CREATE"
        const val PRE_CREATE = "PRE_CREATE"
        const val VIEW_CREATE = "VIEW_CREATE"
        const val START = "START"
        const val RESUME = "RESUME"
        const val PAUSE = "PAUSE"
        const val STOP = "STOP"
        const val SAVE_INSTANCE_STATE = "SAVE_INSTANCE_STATE"
        const val DESTROY = "DESTROY"
        const val VIEW_DESTROY = "VIEW_DESTROY"
        const val DETACH = "DETACH"
    }
}