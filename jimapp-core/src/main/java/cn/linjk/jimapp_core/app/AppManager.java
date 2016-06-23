package cn.linjk.jimapp_core.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * Created by LinJK on 6/23/16.
 * <p/>
 * A class to manager the activity in the application.
 *
 * @author  Linjk
 * @version 1.0
 */
public class AppManager {

    private static Stack<Activity> stackActivity;
    private static AppManager      INSTANCE;

    private AppManager() {}

    /**
     * Gets app manager.
     *
     * @return app manager
     */
    public static AppManager getAppManager() {
        if (null == INSTANCE) {
            INSTANCE = new AppManager();
        }

        return INSTANCE;
    }

    /**
     * Add activity to Activity stack.
     *
     * @param activity the activity
     */
    public void addActivity(Activity activity) {
        if (null == stackActivity) {
            stackActivity = new Stack<Activity>();
        }

        stackActivity.add(activity);
    }

    /**
     * Current activity .
     * <p/>
     * The returned activity is the top activity of the activity stack.
     *
     * @return activity
     */
    public Activity currentActivity() {
        return stackActivity.lastElement();
    }

    /**
     * Finish Activity.
     * <p/>
     * Finished the top activity of the activity stack.
     */
    public void finishActivity() {
        finishActivity(stackActivity.lastElement());
    }

    /**
     * Finished the defined activity.
     *
     * @param activity the activity to finish.
     */
    public void finishActivity(Activity activity) {
        if (null != activity) {
            stackActivity.remove(activity);

            if (!activity.isFinishing()) {
                activity.finish();
            }
            activity = null;
        }
    }

    /**
     * Remove an activity from the activity stack.
     * <p/>
     * You can used when override 'Activity.onDestroy()'
     *
     * @param activity the activity to remove from stack.
     */
    public void removeActivity(Activity activity) {
        if (null != activity) {
            stackActivity.remove(activity);
        }
    }

    /**
     * Finish the activity with a class name.
     *
     * @param clazz the class name of the activity
     */
    public void finiseActivity(Class<?> clazz) {
        for (Activity activity : stackActivity) {
            if (activity.getClass().equals(clazz)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * Finish all activity in the stack.
     */
    public void finishAllActivity() {
        for (int i = 0, size = stackActivity.size(); i < size; i++) {
            if (null != stackActivity.get(i)) {
                stackActivity.get(i).finish();
            }
        }

        stackActivity.clear();
    }

    /**
     * Exit the application.
     */
    public void ExitApp(Context context) {
        try {
            finishAllActivity();

            ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.restartPackage(context.getPackageName());

            System.exit(0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
