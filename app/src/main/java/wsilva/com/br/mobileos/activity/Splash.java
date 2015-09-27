package wsilva.com.br.mobileos.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.activity.CoreActivity;

public class Splash extends CoreActivity implements Runnable  {

	private final int DELAY=3100;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.lay_splash);
		
		//Animar a imagem
		ImageView imgSplash=(ImageView) findViewById(R.id.imgSplash);
		int images[]={R.mipmap.img_splash_mobileos};
		animate(imgSplash, images, 0, true);
		
		Handler handler=new Handler();
		handler.postDelayed(this, DELAY);
	}
	
	@Override
	public void run() 
	{
		Intent i = new Intent(getBaseContext(), Login.class);
		startActivity(i);
		finish();
	}
	
	
	private void animate(final ImageView imageView, final int images[],
			final int imageIndex, final boolean forever) {

		// imageView <-- The View which displays the images
		// images[] <-- Holds R references to the images to display
		// imageIndex <-- index of the first image to show in images[]
		// forever <-- If equals true then after the last image it starts all
		// over again with the first image resulting in an infinite loop. You
		// have been warned.

		int fadeInDuration = 200; // Configure time values here
		int timeBetween = 2000;
		int fadeOutDuration = 1000;

		imageView.setVisibility(View.INVISIBLE); // Visible or invisible by
													// default - this will apply
													// when the animation ends
		imageView.setImageResource(images[imageIndex]);

		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator()); // add this
		fadeIn.setDuration(fadeInDuration);

		Animation fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator()); // and this
		fadeOut.setStartOffset(fadeInDuration + timeBetween);
		fadeOut.setDuration(fadeOutDuration);

		AnimationSet animation = new AnimationSet(false); // change to false
		animation.addAnimation(fadeIn);
		animation.addAnimation(fadeOut);
		animation.setRepeatCount(1);
		imageView.setAnimation(animation);

		animation.setAnimationListener(new Animation.AnimationListener() 
		{
			public void onAnimationEnd(Animation animation) {
				if (images.length - 1 > imageIndex) {
					// Calls itself until it gets to the end of the array
					animate(imageView, images, imageIndex + 1, forever); 
				} else {
					if (forever == true) {
						// Calls itself to start the animation all over again in a loop if forever = true
						animate(imageView, images, 0, forever); 
					}
				}
			}

			public void onAnimationRepeat(Animation animation) {
			}

			public void onAnimationStart(Animation animation) {
			}
		});
	}
}
