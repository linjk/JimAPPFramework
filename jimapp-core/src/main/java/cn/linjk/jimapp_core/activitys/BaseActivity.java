package cn.linjk.jimapp_core.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import cn.linjk.jimapp_core.app.AppManager;
import cn.linjk.jimapp_core.utils.AndroidUtils;
import cn.linjk.jimapp_core.utils.ViewServer;

/**
 * Created by Jim on 2016/6/23.
 */
public class BaseActivity extends Activity{

    private final static String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppManager.getAppManager().addActivity(this);
        Log.i(TAG, "--> Current Activity: " + getRunningActivityName());

//        if (AndroidUtils.isDebuggable(this)) {
//            ViewServer.get(this).addWindow(this);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();

//        if (AndroidUtils.isDebuggable(this)) {
//            ViewServer.get(this).setFocusedWindow(this);
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        AppManager.getAppManager().removeActivity(this);

//        if (AndroidUtils.isDebuggable(this)) {
//            ViewServer.get(this).removeWindow(this);
//        }
    }

    public void ExitCurrentApp() {
        AppManager.getAppManager().finishAllActivity();
    }

    private String getRunningActivityName() {
        String contextString = this.toString();

        return contextString.substring(contextString.lastIndexOf(".")+1, contextString.indexOf("@"));
    }
}
