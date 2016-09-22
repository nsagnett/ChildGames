package nsapp.childgames;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.AnimationUtils;

import nsapp.childgames.view.MTextView;

public class HomeActivity extends AbstractActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final MTextView touchScreenTV = ((MTextView) findViewById(R.id.touchScreenTV));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                touchScreenTV.setVisibility(View.VISIBLE);
                touchScreenTV.startAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.blink));
                findViewById(R.id.homeRL).setOnClickListener(HomeActivity.this);
            }
        }, 500);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.homeRL:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                if (prefs.getString(USER_NAME_KEY, null) == null) {
                    startActivityWithAnim(GetNameActivity.class, false);
                } else {
                    startActivityWithAnim(ReceptionActivity.class, false);
                }
                break;
        }
    }
}
