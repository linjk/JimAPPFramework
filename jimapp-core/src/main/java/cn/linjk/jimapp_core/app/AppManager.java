package cn.linjk.jimapp_core.app;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by LinJK on 6/23/16.
 *
 */
public class AppManager {

    private static Stack<Activity> stackActivity;
    private static AppManager      INSTANCE;

    private AppManager() {}

    /**
     *
     * @return AppManager
     */
    public static AppManager getAppManager() {
        if (null == INSTANCE) {
            INSTANCE = new AppManager();
        }

        return INSTANCE;
    }

    /**
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (null == stackActivity) {
            stackActivity = new Stack<>();
        }

        stackActivity.add(activity);
    }

    /**
     *
     *
     * @return activity
     */
    public Activity currentActivity() {
        return stackActivity.lastElement();
    }
}
