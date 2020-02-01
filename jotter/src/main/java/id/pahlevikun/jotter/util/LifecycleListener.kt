package id.pahlevikun.jotter.util

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import id.pahlevikun.jotter.Jotter
import id.pahlevikun.jotter.event.ActivityEvent
import id.pahlevikun.jotter.event.FragmentEvent
import java.lang.ref.WeakReference

internal object LifecycleListener {

    internal fun register(
        application: Application,
        listener: Jotter.Listener?,
        activityFilter: List<String>,
        fragmentFilter: List<String>
    ) {
        application.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            private var weakReference: WeakReference<FragmentManager.FragmentLifecycleCallbacks?>? =
                null

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                weakReference = WeakReference(
                    registerToFragmentLifecycle(
                        activity = activity,
                        listener = listener,
                        fragmentFilter = fragmentFilter
                    )
                )
                Logger.debug("onActivityCreated >>> $activity")
                if (activityFilter.contains(ActivityEvent.CREATE))
                    listener?.onReceiveActivityEvent(
                        activity = activity,
                        event = ActivityEvent.CREATE,
                        bundle = savedInstanceState
                    )
            }

            override fun onActivityStarted(activity: Activity) {
                Logger.debug("onActivityStarted >>> $activity")
                if (activityFilter.contains(ActivityEvent.START))
                    listener?.onReceiveActivityEvent(
                        activity = activity,
                        event = ActivityEvent.START
                    )
            }

            override fun onActivityResumed(activity: Activity) {
                Logger.debug("onActivityResumed >>> $activity")
                if (activityFilter.contains(ActivityEvent.RESUME))
                    listener?.onReceiveActivityEvent(
                        activity = activity,
                        event = ActivityEvent.RESUME
                    )
            }

            override fun onActivityPaused(activity: Activity) {
                Logger.debug("onActivityPaused >>> $activity")
                if (activityFilter.contains(ActivityEvent.PAUSE))
                    listener?.onReceiveActivityEvent(
                        activity = activity,
                        event = ActivityEvent.PAUSE
                    )
            }

            override fun onActivityStopped(activity: Activity) {
                Logger.debug("onActivityStopped >>> $activity")
                if (activityFilter.contains(ActivityEvent.STOP))
                    listener?.onReceiveActivityEvent(
                        activity = activity,
                        event = ActivityEvent.STOP
                    )
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
                Logger.debug("onActivitySaveInstanceState >>> $activity")
                if (activityFilter.contains(ActivityEvent.SAVE_INSTANCE_STATE))
                    listener?.onReceiveActivityEvent(
                        activity = activity,
                        event = ActivityEvent.SAVE_INSTANCE_STATE,
                        bundle = outState
                    )
            }

            override fun onActivityDestroyed(activity: Activity) {
                if (weakReference != null) {
                    unregisterToFragmentLifecycle(activity, weakReference?.get())
                    weakReference?.clear()
                }
                Logger.debug("onActivityDestroyed >>> $activity")
                if (activityFilter.contains(ActivityEvent.DESTROY))
                    listener?.onReceiveActivityEvent(
                        activity = activity,
                        event = ActivityEvent.DESTROY
                    )
            }
        })
    }

    private fun unregisterToFragmentLifecycle(
        activity: Activity,
        callbacks: FragmentManager.FragmentLifecycleCallbacks?
    ) {
        if (callbacks != null && activity is FragmentActivity) {
            val supportFragmentManager = activity.supportFragmentManager
            supportFragmentManager.unregisterFragmentLifecycleCallbacks(callbacks)
        }
    }

    private fun registerToFragmentLifecycle(
        activity: Activity,
        listener: Jotter.Listener?,
        fragmentFilter: List<String>
    ): FragmentManager.FragmentLifecycleCallbacks? {
        if (activity is FragmentActivity) {
            val supportFragmentManager = activity.supportFragmentManager
            val fragmentLifecycleCallbacks: FragmentManager.FragmentLifecycleCallbacks =
                object : FragmentManager.FragmentLifecycleCallbacks() {
                    override fun onFragmentPreAttached(
                        fragmentManager: FragmentManager,
                        fragment: Fragment,
                        context: Context
                    ) {
                        super.onFragmentPreAttached(fragmentManager, fragment, context)
                        Logger.debug("onFragmentPreAttached >>> $fragment >>> $context")
                        if (fragmentFilter.contains(FragmentEvent.PRE_ATTACH))
                            listener?.onReceiveFragmentEvent(
                                fragment = fragment,
                                context = context,
                                event = FragmentEvent.PRE_ATTACH
                            )
                    }

                    override fun onFragmentAttached(
                        fragmentManager: FragmentManager,
                        fragment: Fragment,
                        context: Context
                    ) {
                        super.onFragmentAttached(fragmentManager, fragment, context)
                        Logger.debug("onFragmentAttached >>> $fragment >>> $context")
                        if (fragmentFilter.contains(FragmentEvent.ATTACH))
                            listener?.onReceiveFragmentEvent(
                                fragment = fragment,
                                context = context,
                                event = FragmentEvent.ATTACH
                            )
                    }

                    override fun onFragmentCreated(
                        fragmentManager: FragmentManager,
                        fragment: Fragment,
                        savedInstanceState: Bundle?
                    ) {
                        super.onFragmentCreated(fragmentManager, fragment, savedInstanceState)
                        Logger.debug("onFragmentCreated >>> $fragment")
                        if (fragmentFilter.contains(FragmentEvent.CREATE))
                            listener?.onReceiveFragmentEvent(
                                fragment = fragment,
                                event = FragmentEvent.CREATE,
                                bundle = savedInstanceState
                            )
                    }

                    override fun onFragmentActivityCreated(
                        fragmentManager: FragmentManager,
                        fragment: Fragment,
                        savedInstanceState: Bundle??
                    ) {
                        super.onFragmentActivityCreated(
                            fragmentManager,
                            fragment,
                            savedInstanceState
                        )
                        Logger.debug("onFragmentActivityCreated >>> $fragment")
                        if (fragmentFilter.contains(FragmentEvent.ACTIVITY_CREATE))
                            listener?.onReceiveFragmentEvent(
                                fragment = fragment,
                                event = FragmentEvent.ACTIVITY_CREATE,
                                bundle = savedInstanceState
                            )
                    }

                    override fun onFragmentPreCreated(
                        fragmentManager: FragmentManager,
                        fragment: Fragment,
                        savedInstanceState: Bundle??
                    ) {
                        super.onFragmentPreCreated(fragmentManager, fragment, savedInstanceState)
                        Logger.debug("onFragmentActivityCreated >>> $fragment")
                        if (fragmentFilter.contains(FragmentEvent.PRE_CREATE))
                            listener?.onReceiveFragmentEvent(
                                fragment = fragment,
                                event = FragmentEvent.PRE_CREATE,
                                bundle = savedInstanceState
                            )
                    }

                    override fun onFragmentViewCreated(
                        fragmentManager: FragmentManager,
                        fragment: Fragment,
                        view: View,
                        savedInstanceState: Bundle?
                    ) {
                        super.onFragmentViewCreated(
                            fragmentManager,
                            fragment,
                            view,
                            savedInstanceState
                        )
                        Logger.debug("onFragmentViewCreated >>> $fragment >>> $view")
                        if (fragmentFilter.contains(FragmentEvent.VIEW_CREATE))
                            listener?.onReceiveFragmentEvent(
                                fragment = fragment,
                                event = FragmentEvent.VIEW_CREATE,
                                bundle = savedInstanceState
                            )
                    }

                    override fun onFragmentStarted(
                        fragmentManager: FragmentManager,
                        fragment: Fragment
                    ) {
                        super.onFragmentStarted(fragmentManager, fragment)
                        Logger.debug("onFragmentStarted >>> $fragment")
                        if (fragmentFilter.contains(FragmentEvent.START))
                            listener?.onReceiveFragmentEvent(
                                fragment = fragment,
                                event = FragmentEvent.START
                            )
                    }

                    override fun onFragmentResumed(
                        fragmentManager: FragmentManager,
                        fragment: Fragment
                    ) {
                        super.onFragmentResumed(fragmentManager, fragment)
                        Logger.debug("onFragmentResumed >>> $fragment")
                        if (fragmentFilter.contains(FragmentEvent.RESUME))
                            listener?.onReceiveFragmentEvent(
                                fragment = fragment,
                                event = FragmentEvent.RESUME
                            )
                    }

                    override fun onFragmentPaused(
                        fragmentManager: FragmentManager,
                        fragment: Fragment
                    ) {
                        super.onFragmentPaused(fragmentManager, fragment)
                        Logger.debug("onFragmentPaused >>> $fragment")
                        if (fragmentFilter.contains(FragmentEvent.PAUSE))
                            listener?.onReceiveFragmentEvent(
                                fragment = fragment,
                                event = FragmentEvent.PAUSE
                            )
                    }

                    override fun onFragmentStopped(
                        fragmentManager: FragmentManager,
                        fragment: Fragment
                    ) {
                        super.onFragmentStopped(fragmentManager, fragment)
                        Logger.debug("onFragmentStopped >>> $fragment")
                        if (fragmentFilter.contains(FragmentEvent.STOP))
                            listener?.onReceiveFragmentEvent(
                                fragment = fragment,
                                event = FragmentEvent.STOP
                            )
                    }

                    override fun onFragmentSaveInstanceState(
                        fragmentManager: FragmentManager,
                        fragment: Fragment,
                        outState: Bundle
                    ) {
                        super.onFragmentSaveInstanceState(fragmentManager, fragment, outState)
                        Logger.debug("onFragmentSaveInstanceState >>> $fragment")
                        if (fragmentFilter.contains(FragmentEvent.SAVE_INSTANCE_STATE))
                            listener?.onReceiveFragmentEvent(
                                fragment = fragment,
                                event = FragmentEvent.SAVE_INSTANCE_STATE,
                                bundle = outState
                            )
                    }

                    override fun onFragmentViewDestroyed(
                        fragmentManager: FragmentManager,
                        fragment: Fragment
                    ) {
                        super.onFragmentViewDestroyed(fragmentManager, fragment)
                        Logger.debug("onFragmentViewDestroyed >>> $fragment")
                        if (fragmentFilter.contains(FragmentEvent.VIEW_DESTROY))
                            listener?.onReceiveFragmentEvent(
                                fragment = fragment,
                                event = FragmentEvent.VIEW_DESTROY
                            )
                    }

                    override fun onFragmentDestroyed(
                        fragmentManager: FragmentManager,
                        fragment: Fragment
                    ) {
                        super.onFragmentDestroyed(fragmentManager, fragment)
                        Logger.debug("onFragmentDestroyed >>> $fragment")
                        if (fragmentFilter.contains(FragmentEvent.DESTROY))
                            listener?.onReceiveFragmentEvent(
                                fragment = fragment,
                                event = FragmentEvent.DESTROY
                            )
                    }

                    override fun onFragmentDetached(
                        fragmentManager: FragmentManager,
                        fragment: Fragment
                    ) {
                        super.onFragmentDetached(fragmentManager, fragment)
                        Logger.debug("onFragmentDetached >>> $fragment")
                        if (fragmentFilter.contains(FragmentEvent.DETACH))
                            listener?.onReceiveFragmentEvent(
                                fragment = fragment,
                                event = FragmentEvent.DETACH
                            )
                    }
                }
            supportFragmentManager.registerFragmentLifecycleCallbacks(
                fragmentLifecycleCallbacks,
                true
            )
            return fragmentLifecycleCallbacks
        }
        return null
    }
}