package com.yusufmansur.android;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity {

	// global var
	MediaPlayer ymOpen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		// working with sound, either:
		// 1. small pool = snd track
		// 2. media player
		ymOpen = MediaPlayer.create(Splash.this, R.raw.splashsound);
		ymOpen.start();
		
		Thread timer = new Thread(){
			public void run(){
				try{
					// pause 5s
					sleep(5000);
				} catch(InterruptedException e){
					// debugging
					e.printStackTrace();
				} finally{
					// run next activity
					Intent openMainActivity = new Intent("com.yusufmansur.android.MENU");
					startActivity(openMainActivity);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// destroy splash
		ymOpen.release();
		finish();
	}
	
}
