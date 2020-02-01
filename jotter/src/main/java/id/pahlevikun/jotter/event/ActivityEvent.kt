package id.pahlevikun.jotter.event

import androidx.annotation.StringDef
import id.pahlevikun.jotter.event.ActivityEvent.Companion.CREATE
import id.pahlevikun.jotter.event.ActivityEvent.Companion.DESTROY
import id.pahlevikun.jotter.event.ActivityEvent.Companion.PAUSE
import id.pahlevikun.jotter.event.ActivityEvent.Companion.RESUME
import id.pahlevikun.jotter.event.ActivityEvent.Companion.SAVE_INSTANCE_STATE
import id.pahlevikun.jotter.event.ActivityEvent.Companion.START
import id.pahlevikun.jotter.event.ActivityEvent.Companion.STOP

@Retention(AnnotationRetention.SOURCE)
@StringDef(
    CREATE,
    START,
    RESUME,
    PAUSE,
    STOP,
    SAVE_INSTANCE_STATE,
    DESTROY
)
annotation class ActivityEvent {
    companion object {
        const val CREATE = "CREATE"
        const val START = "START"
        const val RESUME = "RESUME"
        const val PAUSE = "PAUSE"
        const val STOP = "STOP"
        const val SAVE_INSTANCE_STATE = "SAVE_INSTANCE_STATE"
        const val DESTROY = "DESTROY"
    }
}