package cn.linjk.jimapp_core.app;

import android.app.Application;

import cn.linjk.jimapp_core.R;
import cn.linjk.jimapp_core.utils.ToastUtils;

/**
 * Created by LinJK on 6/23/16.
 */
public class JimAppBase extends Application{
    protected static JimAppBase INSTANCE;

    private boolean inBackground = false;

    public JimAppBase() {
        INSTANCE = this;
    }

    public static JimAppBase get() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ToastUtils.init(R.layout.toast, R.id.imageViewToast, R.id.textViewMesssage);
    }

    public boolean isInBackground() {
        return inBackground;
    }

    public void setInBackground(boolean inBackground) {
        this.inBackground = inBackground;
    }
}
