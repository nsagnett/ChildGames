package nsapp.childgames;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import org.json.JSONException;
import org.json.JSONObject;

import nsapp.childgames.utils.OnTerminatedGameListener;
import nsapp.childgames.view.MErrorsGameSurfaceView;

public class ErrorsGameActivity extends AbstractActivity implements OnTerminatedGameListener {

    private MErrorsGameSurfaceView joueurMSV;
    private View modeleV;
    private View solutionV;
    private JSONObject choseConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onPrepareDialog(this);
        setContentView(R.layout.activity_errors_game);

        joueurMSV = (MErrorsGameSurfaceView) findViewById(R.id.joueurMSV);
        modeleV = findViewById(R.id.modeleV);
        solutionV = findViewById(R.id.solutionV);
        joueurMSV.setOnTerminatedGameListener(this);

        try {
            //String choseImage = getIntent().getStringExtra(CHOSE_IMAGE_EXTRA_KEY);
            String choseImage = "singe";

            choseConf = erreursGameConfig.getJSONObject(choseImage);

            joueurMSV.setBackgroundResource(getResources().getIdentifier("erreurs_" + choseImage + "_joueur", "drawable", getPackageName()));
            modeleV.setBackgroundResource(getResources().getIdentifier("erreurs_" + choseImage + "_modele", "drawable", getPackageName()));
            solutionV.setBackgroundResource(getResources().getIdentifier("erreurs_" + choseImage + "_solution", "drawable", getPackageName()));
            joueurMSV.setErrorsCount(choseConf.getInt("erreurs"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.yesDialogTV:
                dialog.dismiss();
                showAnswers(modeleV, solutionV);
                break;
            case R.id.noDialogTV:
                joueurMSV.removeLastPoint();
                break;
        }
    }

    @Override
    public void onTerminatedGame() {
        showConfirmDialog(getString(R.string.end_game));
    }

    private void showAnswers(View out, View in) {
        Animation taExit = AnimationUtils.loadAnimation(this, R.anim.view_slide_out);
        taExit.setFillAfter(true);

        Animation taEnter = AnimationUtils.loadAnimation(this, R.anim.view_slide_in);
        taEnter.setFillAfter(true);


        out.startAnimation(taExit);
        in.setVisibility(View.VISIBLE);
        in.startAnimation(taEnter);
    }
}
