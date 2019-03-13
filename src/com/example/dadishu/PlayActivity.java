package com.example.dadishu;

import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class PlayActivity extends Activity{
	
	private int count = 0;
	private int time = 3000;
	private int[][] position = new int[][]{{127,125},{283,125},{466,125},
			{606,125},{70,232},{220,232},{369,232},{530,232},{670,232},};
	private ImageView mouse = null;
	private Thread thread = null;
	private boolean normal = true;
	private Handler handler;
	private TextView timeText = null;
	private boolean k =true;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		//	 setContentView(R.layout.main);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mouse =(ImageView) findViewById(R.id.mouse);
		 timeText = (TextView) findViewById(R.id.time);
		 
		 mouse.setOnTouchListener(new OnTouchListener(){

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					v.setVisibility(View.INVISIBLE);
					count++;
					Toast.makeText(PlayActivity.this, "打到["+ count + "]只地鼠", Toast.LENGTH_SHORT).show();
					return false;
				}
				 
			 });
		 
		 
		 
		 handler = new Handler(){
			
			@SuppressLint("NewApi")
			public void handleMessage(Message msg){
				 int index =0;
				 if(time>0 && normal == true){
					 if(msg.what==0x101){
						 timeText.setText("剩余时间："+ time/100 );
						 index =msg.arg1;
						 mouse.setX(position[index][0]);
						 mouse.setY(position[index][1]);
						 mouse.setVisibility(View.VISIBLE);
					 }
					 super.handleMessage(msg);
				 }
				 else if(normal == true)
				 {
					 gameOver();
				 }
			 }
			 
		 };
		
			thread = new MyThread();
	        thread.start(); 
	}


	
	
	
	public void gameOver() {
		timeText.setText("时间到！");
//		handler.removeMessages(time);
		handler.removeCallbacksAndMessages(thread);
	//	handler.removeMessages(count, thread);
		thread = null;
		
 if(k == true){
	  k=false;
     new AlertDialog.Builder(PlayActivity.this).setTitle("游戏结束")
          .setMessage("一共消灭"+ count + "只地鼠").setNegativeButton("再来一局", new  OnClickListener(){


             @Override
             public void onClick(DialogInterface dialog, int which) {
	            thread = new MyThread();
	           time = 3000;
	            count =0;
	           k=true;
	           thread.start();
            }
              }).setNeutralButton("退出",new OnClickListener(){


        @Override
        public void onClick(DialogInterface arg0, int arg1) {
       	 k=true;
	          PlayActivity.this.finish();
        }}).create().show();
		
 }
		
		
	}
	
	
	

	  public boolean onKeyDown(int keyCode, KeyEvent event) {  
	        if (keyCode == KeyEvent.KEYCODE_BACK) {  
	           normal = false;
	           PlayActivity.this.finish();
	        }  
	        return false;} 
	
	

	  public class MyThread extends Thread{
			public void run(){
				
				super.run();
				 k=true;
				int index = 0;
				while(!Thread.currentThread().isInterrupted()){
					index = new Random().nextInt(position.length);
					System.out.print(index);
					Message m = handler.obtainMessage();
					m.what = 0x101;
					m.arg1 = index;
					handler.sendMessage(m);
					if(time >0 && normal == true){
						
					    try {
							Thread.sleep(new Random().nextInt(1000)+1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						time = time -100;
					}else{
						thread.interrupted();
					
				}
				}
	  
			}
	  
	  
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

 }
}
