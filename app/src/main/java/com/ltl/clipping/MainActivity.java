package com.ltl.clipping;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity extends Activity {
    WebView view;
    JavaScriptObject js;
    TextView titleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        view = (WebView) findViewById(R.id.web);
        titleTv = (TextView) findViewById(R.id.text);
        js = new JavaScriptObject(this);
        view.getSettings().setDefaultTextEncodingName("utf-8");
        view.getSettings().setJavaScriptEnabled(true);
        //这里添加调用js的方法对象
        view.addJavascriptInterface(js, "isMethod");
        view.loadUrl("file:///android_asset/demo.html");
        view.setWebViewClient(new WebViewClient() {


            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            // 出现错误是   的回调
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                // TODO Auto-generated method stub
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });


        view.setWebChromeClient(new WebChromeClient() {
                                    @Override
                                    public void onReceivedTitle(WebView view, String title) {
                                        // 设置标题
                                        super.onReceivedTitle(view, title);
                                        if (titleTv != null) {
                                            titleTv.setText(title);
                                        }
                                    }

                                    @Override
                                    public void onShowCustomView(View view, CustomViewCallback callback) {
                                        // TODO Auto-generated method stub
                                        super.onShowCustomView(view, callback);
                                    }

                                }

        );

    }


}
