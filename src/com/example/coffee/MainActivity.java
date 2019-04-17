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
		mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);//����js����ֱ�Ӵ򿪴��ڣ���window.open()��Ĭ��Ϊfalse
		mWebSettings.setJavaScriptEnabled(true);//�Ƿ�����JavaScript�ű����У�Ĭ��Ϊfalse������trueʱ�������ѿ������XSS©��
		mWebSettings.setUseWideViewPort(false);//���ô����ԣ�������������š�����ͼģʽ
		mWebSettings.setLoadWithOverviewMode(false);//��setUseWideViewPort(true)һ������ҳ����Ӧ����
		mWebSettings.setAppCacheEnabled(true);//�Ƿ�ʹ�û���
		mWebSettings.setDomStorageEnabled(true);//��������DOM�洢
		mWebSettings.setLoadsImagesAutomatically(true); // ����ͼƬ
		mWebSettings.setSupportZoom(false);
		mWebSettings.setTextZoom(100);
		webView.loadUrl("http://192.168.0.112:8080/");

		//���ò���ϵͳ�������,ֱ����ʾ�ڵ�ǰWebview
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
//			new AlertDialog.Builder(MainActivity.this).setTitle("��ʾ").setMessage("�Ƿ��˳�").setNeutralButton("ȡ��", null).setNegativeButton("ȷ��", new DialogInterface.OnClickListener() {
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
			Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�����",
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
