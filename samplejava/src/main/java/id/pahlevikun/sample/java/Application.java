package id.pahlevikun.sample.java;

import java.util.Arrays;

import id.pahlevikun.jotter.Jotter;
import id.pahlevikun.jotter.event.ActivityEvent;
import id.pahlevikun.jotter.event.FragmentEvent;
import id.pahlevikun.sample.java.listener.JotterListener;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new Jotter
            .Builder(this)
            .setLogEnable(true)
            .setActivityEventFilter(Arrays.asList(ActivityEvent.CREATE, ActivityEvent.DESTROY))
            .setFragmentEventFilter(Arrays.asList(FragmentEvent.VIEW_CREATE, FragmentEvent.PAUSE))
            .setJotterListener(new JotterListener())
            .build()
            .startListening();
    }
}
