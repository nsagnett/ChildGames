package nsapp.childgames;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.AnimationUtils;

import org.json.JSONException;
import org.json.JSONObject;

import nsapp.childgames.utils.Resources;
import nsapp.childgames.view.MTextView;

public class SplashScreenActivity extends AbstractActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final MTextView touchScreenTV = ((MTextView) findViewById(R.id.touchScreenTV));

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
    protected void onResume() {
        super.onResume();
        try {
            erreursGameConfig = new JSONObject(Resources.loadJSONFromAsset(this, "erreurs_conf.json"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
