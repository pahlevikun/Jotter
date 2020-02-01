package id.pahlevikun.activitylifecyclecallbacks.listener

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import id.pahlevikun.jotter.Jotter

object JotterListener : Jotter.Listener {
    override fun onReceiveActivityEvent(
        activity: Activity,
        event: String,
        bundle: Bundle?
    ) {
        Log.d("LifeCycleCallback", "ACTIVITY IS $activity >>> $event")
    }

    override fun onReceiveFragmentEvent(
        fragment: Fragment,
        context: Context?,
        event: String,
        bundle: Bundle?
    ) {
        Log.d("LifeCycleCallback", "FRAGMENT IS $fragment >>> $event")
    }
}