package cn.linjk.jimapp_core.app;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by LinJK on 6/23/16.
 *
 * 用于管理activity
 */
public class AppManager {

    private static Stack<Activity> stackActivity; //用于保存栈的activity以便于进行操作
    private static AppManager      INSTANCE;

    private AppManager() {}

    /**
     * 获取AppManager单例
     *
     * @return AppManager单例对象
     */
    public static AppManager getAppManager() {
        if (null == INSTANCE) {
            INSTANCE = new AppManager();
        }

        return INSTANCE;
    }

    /**
     * 添加activity到堆栈
     *
     * @param activity 要压入堆栈的activity
     */
    public void addActivity(Activity activity) {
        if (null == stackActivity) {
            stackActivity = new Stack<>();
        }

        stackActivity.add(activity);
    }

    /**
     * 获取activity堆栈栈顶的activity
     *
     * @return 当前activity堆栈栈顶的activity
     */
    public Activity currentActivity() {
        return stackActivity.lastElement();
    }
}
