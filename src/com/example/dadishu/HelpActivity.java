package com.example.dadishu;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;




public class HelpActivity extends Activity{

	private WebView webView;
	WebSettings mWebSettings;

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	    setContentView(R.layout.help);

		webView = (WebView) findViewById(R.id.webView);



		mWebSettings = webView.getSettings();
		mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
		mWebSettings.setJavaScriptEnabled(true);//是否允许JavaScript脚本运行，默认为false。设置true时，会提醒可能造成XSS漏洞
//		mWebSettings.setSupportZoom(true);//是否可以缩放，默认true
//		mWebSettings.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
		mWebSettings.setUseWideViewPort(false);//设置此属性，可任意比例缩放。大视图模式
		mWebSettings.setLoadWithOverviewMode(false);//和setUseWideViewPort(true)一起解决网页自适应问题
		mWebSettings.setAppCacheEnabled(true);//是否使用缓存
		mWebSettings.setDomStorageEnabled(true);//开启本地DOM存储
		mWebSettings.setLoadsImagesAutomatically(true); // 加载图片
		mWebSettings.setSupportZoom(false);
		mWebSettings.setTextZoom(100);

		webView.loadUrl("http://192.168.0.117:8080/");
		//设置不用系统浏览器打开,直接显示在当前Webview
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});




//
//		webView.setWebViewClient(new WebViewClient(){
//			@Override
//			public boolean shouldOverrideUrlLoading(WebView view, String url) {
//				view.loadUrl(url);
//				return true;
//			}
//		});
//
//		webView.loadUrl("http://192.168.0.117:8080/");
//
//		webView.getSettings().setDomStorageEnabled(true);
//		 webView.getSettings().setAppCacheMaxSize(1024*1024*8);//存储的最大容量
//		 String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
//		 webView.getSettings().setAppCachePath(appCachePath);
//		webView.getSettings().setAllowFileAccess(true);
//		webView.getSettings().setAppCacheEnabled(true);
//		webView.getSettings().setDomStorageEnabled(true);//是否开启本地DOM存储
//
//		mWebSettings.setJavaScriptEnabled(true);//是否允许JavaScript脚本运行，默认为fals

//		WebSettings webSettings = webView.getSettings(); //支持缩放，默认为true。 .setUseWideViewPort(true); // 缩放至屏幕的大小 webSettings .setLoadWithOverviewMode(true); //设置默认编码 webSettings .setDefaultTextEncodingName("utf-8"); ////设置自动加载图片 webSettings .setLoadsImagesAutomatically(true);
//		.settings.setJavaScriptEnabled(true);// 设置可以运行JS脚本
//		.settings.setSupportZoom(false);// 用于设置webview放大
//		.settings.setBuiltInZoomControls(false);
//

//		Intent intent = new Intent(getActivity(), ProgressWebviewActivity.class);
//		intent.putExtra("url", "http://10.0.0.188:8080/");
//		startActivity(intent);


	}

}
