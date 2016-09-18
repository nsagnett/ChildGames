package nsapp.childgames;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import nsapp.childgames.utils.OnTerminatedGameListener;
import nsapp.childgames.view.MErrorsGameSurfaceView;

public class ErrorsGameActivity extends AbstractActivity implements OnTerminatedGameListener {

    private MErrorsGameSurfaceView errorsGameSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onPrepareDialog(this);
        setContentView(R.layout.activity_errors_game);

        errorsGameSurfaceView = (MErrorsGameSurfaceView) findViewById(R.id.errorsGameMSV);
        errorsGameSurfaceView.setOnTerminatedGameListener(this);
        errorsGameSurfaceView.setErrorsCount(7);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.yesDialogTV:
                dialog.dismiss();
                showAnswers(findViewById(R.id.referenceV), findViewById(R.id.solutionV));
                break;
            case R.id.noDialogTV:
                errorsGameSurfaceView.removeLastPoint();
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
