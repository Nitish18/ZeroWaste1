package com.example.zerowaste1;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity {

	
	Thread thr;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
        
		
		setContentView(R.layout.splash);
		
		
		
		Thread thr = new Thread(){
			
			public void run(){
				
				try{
					
                sleep(1000);
					
					
					
				   }
				
				
				
				catch(InterruptedException e)
				
				{
					e.printStackTrace();
						
				}
				
				
				
				finally{
					
					
					Intent i1 = new Intent(Splash.this,MainActivity.class);
					startActivity(i1);
					
					
					
				}
				
				
				
				
				
				
			}
			
			
			
		};
		
		thr.start();
		
		
	}
	
	
	
	
	
	
	

}
