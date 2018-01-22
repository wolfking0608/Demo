package com.ltl.clipping;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by dwb on 2017/12/21.
 * describe1:
 * describe2:
 * email:wolfking0608@163.com
 */

public class PassByValue2JSActivity extends Activity {
    private WebView webView;

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_byvalue_2js);

        webView = (WebView) findViewById(R.id.webView);

        webView.setVerticalScrollbarOverlay(true);
        webView.requestFocus();//如果不设置,则在点击网页文本输入框的时候,不能弹出软键盘和相应其他的一些事件.
        //设置WebView支持JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/demo.html");
        //  String url = "http://192.168.1.27/js_17_android_webview.html";
//        String url = "file:///android_asset/demo2.html";
//        webView.loadUrl(url);

        //在js中调用本地java方法
        webView.addJavascriptInterface(new JsInterface(this), "AndroidWebView");

        //添加客户端支持
        webView.setWebChromeClient(new WebChromeClient());
     //该方法html
        webView.loadUrl("javascript:showInfoFromJava123456('" + "11111" + "')");
        
    }

    private class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        //在js中调用window.AndroidWebView.showInfoFromJs(name)，便会触发此方法。
        @JavascriptInterface
        public void showInfoFromJs(String name) {
            Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
        }
    }

    //在java中调用js代码
    public void sendInfoToJs(View view) {
        String msg = ((EditText) findViewById(R.id.input_et)).getText().toString();
        //调用js中的函数：showInfoFromJava(msg)
        String url = "file:///android_asset/demo2.html";
        webView.loadUrl(url);
        webView.loadUrl("javascript:showInfoFromJava123456('" + msg + "')");
    }
}  
