package cn.linjk.jimapp_core.utils;

import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.linjk.jimapp_core.app.JimAppBase;

/**
 * Created by LinJK on 7/2/16.
 */
public class ToastUtils {


    public static final void init(int newLayoutRes, int newImageViewRes, int newTextViewRes) {
        layoutRes    = newLayoutRes;
        imageViewRes = newImageViewRes;
        textViewRes  = newTextViewRes;
    }

    public static void showToast(String message) {
        showToast(message, DEFAULT_DURATION);
    }

    public static void showToast(int messageId) {
        showToast(LocalResourceUtils.getString(messageId), DEFAULT_DURATION);
    }

    public static void showToast(String message, int duration) {
        showToast(message, duration, DEFAULT_GRAVITY, DEFAULT_X_OFFSET, DEFAULT_Y_OFFSET);
    }

    public static void showToast(String message, int duration, int gravity, int xOffset, int yOffset) {
        showCustomToast(null, message, duration, gravity, xOffset, yOffset);
    }

    public static void showInfoToast(String message, int duration, int gravity, int xOffset, int yOffset, int infoInconRes) {
        showCustomToast(infoInconRes, message, duration, gravity, xOffset, yOffset);
    }

    public static void showWarningToast(String message, int duration, int gravity, int xOffset, int yOffset, int warningInconRes) {
        showCustomToast(warningInconRes, message, duration, gravity, xOffset, yOffset);
    }

    public static void showErrorToast(String message, int duration, int gravity, int xOffset, int yOffset, int errorInconRes) {
        showCustomToast(errorInconRes, message, duration, gravity, xOffset, yOffset);
    }

    public static void showSuccessToast(String message, int duration, int gravity, int xOffset, int yOffset, int successInconRes) {
        showCustomToast(successInconRes, message, duration, gravity, xOffset, yOffset);
    }

    ////////////////////////////////////////////
    public static void showToastOnUIThread(String message) {
        HANDLER.sendMessage(HANDLER.obtainMessage(1, message));
    }

    public static void showToastOnUITHread(int messageId) {
        showToastOnUIThread(LocalResourceUtils.getString(messageId));
    }

    private static void showCustomToast(Integer imageIconRes, String message, int duration,
                                        int gravity, int xOffset, int yOffset) {
        JimAppBase application = JimAppBase.get();

        if (!application.isInBackground()) {
            LayoutInflater inflater = LayoutInflater.from(application);
            View           view     = inflater.inflate(layoutRes, null);

            showSystemError = message;

            if (imageIconRes != null) {
                ImageView imageView = (ImageView)view.findViewById(imageViewRes);
                imageView.setImageResource(imageIconRes);
                imageView.setVisibility(View.VISIBLE);
            }

            TextView textView = (TextView)view.findViewById(textViewRes);
            textView.setText(message);

            if (toast == null) {
                toast = new Toast(application);
                toast.setGravity(gravity, xOffset, yOffset);
            }

            toast.setDuration(duration);
            toast.setView(view);
            toast.show();
        }
    }

    private static final Handler HANDLER = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            showToast((String)msg.obj);
        }
    };

    ////////////////////////////////////////////
    public static String showSystemError = "";

    private static int   layoutRes;
    private static int   imageViewRes;
    private static int   textViewRes;
    private static Toast toast;

    private static final int DEFAULT_DURATION = Toast.LENGTH_SHORT;
    private static final int DEFAULT_GRAVITY  = Gravity.BOTTOM;
    private static final int DEFAULT_X_OFFSET = 0;
    private static final int DEFAULT_Y_OFFSET = 0;
}
