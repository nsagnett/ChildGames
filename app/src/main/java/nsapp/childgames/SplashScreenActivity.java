package nsapp.childgames;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import nsapp.childgames.view.MTextView;

public class SplashScreenActivity extends AbstractActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        MTextView smileyTV = ((MTextView) findViewById(R.id.smileyTV));
        final MTextView touchScreenTV = ((MTextView) findViewById(R.id.touchScreenTV));

        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(DELAY);
        aa.setStartOffset(400);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        touchScreenTV.setVisibility(View.VISIBLE);
                        touchScreenTV.startAnimation(AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.blink));
                        findViewById(R.id.splashScreenRL).setOnClickListener(SplashScreenActivity.this);
                    }
                }, DELAY);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        smileyTV.startAnimation(aa);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.splashScreenRL:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                if (prefs.getString(USER_NAME_KEY, null) == null) {
                    startActivityWithAnim(GetNameActivity.class, true);
                } else {
                    startActivityWithAnim(ReceptionActivity.class, true);
                }
                break;
        }
    }
}
