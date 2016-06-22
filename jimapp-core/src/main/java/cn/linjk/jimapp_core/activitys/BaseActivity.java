package cn.linjk.jimapp_core.activitys;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Jim on 2016/6/23.
 *
 * 与业务无关的基类，app应用开发的基类应继承此类进行扩展开发
 */
public class BaseActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
