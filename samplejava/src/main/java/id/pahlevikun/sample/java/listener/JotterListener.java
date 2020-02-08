package id.pahlevikun.sample.java.listener;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;

import id.pahlevikun.jotter.Jotter;

public class JotterListener implements Jotter.Listener {
    @Override
    public void onReceiveActivityEvent(Activity activity, String event, Bundle bundle) {
        Log.d("LifeCycleCallback", "ACTIVITY IS " + activity + " >>> " + event);
    }

    @Override
    public void onReceiveFragmentEvent(Fragment fragment, Context context, String event, Bundle bundle) {
        Log.d("LifeCycleCallback", "FRAGMENT IS " + fragment + " >>> " + event);
    }
}
