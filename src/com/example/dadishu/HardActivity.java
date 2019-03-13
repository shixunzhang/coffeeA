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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HardActivity extends Activity{
	
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
	private boolean boomed = false;
	private ImageView boom = null;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		//	 setContentView(R.layout.main);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hard);
		
		mouse =(ImageView) findViewById(R.id.mouse1);
		boom = (ImageView) findViewById(R.id.boom);
		 timeText = (TextView) findViewById(R.id.time1);
		
		 mouse.setOnTouchListener(new OnTouchListener(){

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					v.setVisibility(View.INVISIBLE);
					count++;
					Toast.makeText(HardActivity.this, "打到["+ count + "]只地鼠", Toast.LENGTH_SHORT).show();
					return false;
				}
				 
			 });
		 
		 
		 boom.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				boomed=true;
				if(k == true){
					  k=false;
				 new AlertDialog.Builder(HardActivity.this).setTitle("游戏结束")
		         .setMessage("你碰到了炸弹 一共消灭"+ count + "只地鼠").setNegativeButton("再来一局", new  OnClickListener(){


		            @Override
		            public void onClick(DialogInterface dialog, int which) {
			            // TODO Auto-generated method stub
		            	boomed = false;
			            thread = new MyThread();
			           time = 3000;
			            count =0;
			            k=true;
			           thread.start();
		           }
		             }).setNeutralButton("退出",new OnClickListener(){


		       @Override
		       public void onClick(DialogInterface arg0, int arg1) {
			     // TODO Auto-generated method stub
		    	     k=true;
			          HardActivity.this.finish();
		       }}).create().show();
				//return false;
			}
				return false;
			}
		 });
		 
		 
		 
		 
		 handler = new Handler(){
				
				@SuppressLint("NewApi")
				public void handleMessage(Message msg){
					 int index =0;
					 if(time>0 && normal == true && boomed==false){
						 if(msg.what==0x101){
							 index =msg.arg1;
							 timeText.setText("剩余时间："+ time/100 );
							 switch(new Random().nextInt(5))
							 {
							 case 0:
								 boom.setX(position[index][0]);
								 boom.setY(position[index][1]);
								 boom.setVisibility(View.VISIBLE);
								 break;
							default:
									 
							 mouse.setX(position[index][0]);
							 mouse.setY(position[index][1]);
							 mouse.setVisibility(View.VISIBLE);
						 }
					 }
						 super.handleMessage(msg);
					 }
					 else if(normal == true && boomed == false)
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
     new AlertDialog.Builder(HardActivity.this).setTitle("游戏结束")
          .setMessage("一共消灭"+ count + "只地鼠").setNegativeButton("再来一局", new  OnClickListener(){


             @Override
             public void onClick(DialogInterface dialog, int which) {
	            // TODO Auto-generated method stub
	            thread = new MyThread();
	           time = 3000;
	            count =0;
	           k=true;
	           thread.start();
            }
              }).setNeutralButton("退出",new OnClickListener(){


        @Override
        public void onClick(DialogInterface arg0, int arg1) {
	     // TODO Auto-generated method stub
       	 k=true;
	          HardActivity.this.finish();
        }}).create().show();
		
 }
		
		
	}
	
	
	

	  public boolean onKeyDown(int keyCode, KeyEvent event) {  
	        if (keyCode == KeyEvent.KEYCODE_BACK) {  
	           normal = false;
	           HardActivity.this.finish();
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
					if(time >0 && normal == true && boomed == false){
						
					    try {
							Thread.sleep(new Random().nextInt(500)+500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						time = time -50;
					}else{
						thread.interrupted();
					
				}
				}
	  
			
			}
			
	  }
	  


}  
	  

	

