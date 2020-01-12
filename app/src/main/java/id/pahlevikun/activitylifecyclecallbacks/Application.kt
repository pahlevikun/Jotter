package id.pahlevikun.activitylifecyclecallbacks

import android.app.Application

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        // This is for registering the listener object
        registerActivityLifecycleCallbacks(ActivityLifecycleListener)
        // This is for registering the listener class
        // registerActivityLifecycleCallbacks(ActivityLifecycleHandler(this))
    }
}