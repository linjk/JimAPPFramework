package cn.linjk.jimapp_core.app;

import android.app.Application;

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
    }

    public boolean isInBackground() {
        return inBackground;
    }

    public void setInBackground(boolean inBackground) {
        this.inBackground = inBackground;
    }
}
