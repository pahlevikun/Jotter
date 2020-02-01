package id.pahlevikun.activitylifecyclecallbacks.listener

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

object ActivityLifecycleListener : Application.ActivityLifecycleCallbacks {
    private const val TAG = "LifecycleCallbacks"
    override fun onActivityPaused(p0: Activity) {
        Log.d(TAG,"onActivityPaused at ${p0.localClassName}")
    }

    override fun onActivityStarted(p0: Activity) {
        Log.d(TAG,"onActivityStarted at ${p0.localClassName}")
    }

    override fun onActivityDestroyed(p0: Activity) {
        Log.d(TAG,"onActivityDestroyed at ${p0.localClassName}")
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        Log.d(TAG,"onActivitySaveInstanceState at ${p0.localClassName}")
    }

    override fun onActivityStopped(p0: Activity) {
        Log.d(TAG,"onActivityStopped at ${p0.localClassName}")
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        Log.d(TAG,"onActivityCreated at ${p0.localClassName}")
    }

    override fun onActivityResumed(p0: Activity) {
        Log.d(TAG,"onActivityResumed at ${p0.localClassName}")
    }
}