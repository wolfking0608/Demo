package com.ltl.clipping;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JavaScriptObject {
    Context mContxt;

    public JavaScriptObject(Context mContxt) {
        this.mContxt = mContxt;
    }

    //这个方法名要和js中的方法名相同
    @JavascriptInterface
    public void fun2(String name, String psd) {
        //toast这里弹出的
        Toast.makeText(mContxt, "账号：" + name + " 密码：" + psd, 0).show();
    }

} 
