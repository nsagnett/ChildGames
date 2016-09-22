package nsapp.childgames;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import org.json.JSONObject;

import java.io.InputStream;

import nsapp.childgames.utils.Resources;

public class SplashScreenActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try {
                    InputStream is = getAssets().open("erreurs_conf.json");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    if (is.read(buffer) != -1) {
                        String s = new String(buffer, "UTF-8");
                        Resources.configuration = new JSONObject(s);
                    }
                    is.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return false;
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);
                if (success) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                            overridePendingTransition(R.anim.fade_start, R.anim.fade_end);
                            finish();
                        }
                    }, 4000);
                }
            }
        }.execute();
    }
}
