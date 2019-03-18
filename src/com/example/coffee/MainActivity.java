package com.example.coffee;



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


	private Button exit = null;
	private Button helpBtn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


        exit = (Button) findViewById(R.id.exit);
        helpBtn = (Button) findViewById(R.id.helpBtn);



        helpBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, HelpActivity.class);
				MainActivity.this.startActivity(intent);

			}
        });

        exit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
			new AlertDialog.Builder(MainActivity.this).setTitle("��ʾ").setMessage("�Ƿ��˳�").setNeutralButton("ȡ��", null).setNegativeButton("ȷ��", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
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