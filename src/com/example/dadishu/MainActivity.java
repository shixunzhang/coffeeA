package com.example.dadishu;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {


	private Button easyBon = null;
	private Button exit = null;
	private Button helpBtn = null;
	private Button hardBtn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		easyBon = (Button) findViewById(R.id.easyBtn);
        exit = (Button) findViewById(R.id.exit);
        helpBtn = (Button) findViewById(R.id.helpBtn);
        hardBtn = (Button) findViewById(R.id.hardBtn);


        easyBon.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, PlayActivity.class);
				MainActivity.this.startActivity(intent);

			}
        });


        hardBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, HardActivity.class);
				MainActivity.this.startActivity(intent);

			}
        });



        helpBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, HelpActivity.class);
				MainActivity.this.startActivity(intent);

			}
        });

        exit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			new AlertDialog.Builder(MainActivity.this).setTitle("提示").setMessage("是否退出").setNeutralButton("取消", null).setNegativeButton("确定", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					MainActivity.this.finish();
				}
			}).create().show();


			}
        });
    }



	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
