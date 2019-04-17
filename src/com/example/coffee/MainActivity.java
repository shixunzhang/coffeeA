package com.example.coffee;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

	private WebView webView;
	private long exitTime = 0;
	WebSettings mWebSettings;
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		webView = (WebView) findViewById(R.id.webView);
		mWebSettings = webView.getSettings();
		mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
		mWebSettings.setJavaScriptEnabled(true);//是否允许JavaScript脚本运行，默认为false。设置true时，会提醒可能造成XSS漏洞
		mWebSettings.setUseWideViewPort(false);//设置此属性，可任意比例缩放。大视图模式
		mWebSettings.setLoadWithOverviewMode(false);//和setUseWideViewPort(true)一起解决网页自适应问题
		mWebSettings.setAppCacheEnabled(true);//是否使用缓存
		mWebSettings.setDomStorageEnabled(true);//开启本地DOM存储
		mWebSettings.setLoadsImagesAutomatically(true); // 加载图片
		mWebSettings.setSupportZoom(false);
		mWebSettings.setTextZoom(100);
		webView.loadUrl("http://192.168.0.112:8080/");

		//设置不用系统浏览器打开,直接显示在当前Webview
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});

//        exit.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//			new AlertDialog.Builder(MainActivity.this).setTitle("提示").setMessage("是否退出").setNeutralButton("取消", null).setNegativeButton("确定", new DialogInterface.OnClickListener() {
//
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					MainActivity.this.finish();
//				}
//			}).create().show();
//
//
//			}
//        });
    }


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void exit() {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(getApplicationContext(), "再按一次退出程序",
					Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
			System.exit(0);
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
