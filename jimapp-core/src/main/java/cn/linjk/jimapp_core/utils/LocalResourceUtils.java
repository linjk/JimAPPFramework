package cn.linjk.jimapp_core.utils;

import cn.linjk.jimapp_core.app.JimAppBase;

/**
 * Created by LinJK on 7/2/16.
 */
public final class LocalResourceUtils {
    public static String getString(int resId, Object... args) {
        if (args.length > 0) {
            return JimAppBase.get().getString(resId, args);
        }
        else {
            return JimAppBase.get().getString(resId);
        }
    }
}
