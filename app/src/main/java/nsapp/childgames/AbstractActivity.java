package nsapp.childgames;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.Window;

import nsapp.childgames.view.MTextView;

public abstract class AbstractActivity extends Activity implements View.OnClickListener {

    protected static final String USER_NAME_KEY = "UserNameKey";
    protected static final String USER_CLASS_KEY = "UserClassKey";
    protected static final int DELAY = 2000;

    protected Dialog dialog;
    protected MTextView messageDialogTV;
    protected MTextView noDialogTV;
    protected MTextView yesDialogTV;
    protected MTextView neutralDialogTV;

    protected void startActivityWithAnim(Class<?> c, boolean finishPrevious) {
        startActivity(new Intent(this, c));
        overridePendingTransition(R.anim.enter_start, R.anim.enter_end);
        if (finishPrevious) {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.leave_start, R.anim.leave_end);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.noDialogTV:
            case R.id.neutralDialogTV:
                dialog.dismiss();
                break;
        }
    }

    protected void onPrepareDialog(View.OnClickListener clickListener) {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        messageDialogTV = (MTextView) dialog.findViewById(R.id.messageDialogTV);

        noDialogTV = (MTextView) dialog.findViewById(R.id.noDialogTV);
        noDialogTV.setOnClickListener(clickListener);

        yesDialogTV = (MTextView) dialog.findViewById(R.id.yesDialogTV);
        yesDialogTV.setOnClickListener(clickListener);

        neutralDialogTV = (MTextView) dialog.findViewById(R.id.neutralDialogTV);
        neutralDialogTV.setOnClickListener(clickListener);
    }

    protected void showConfirmDialog(String message) {
        yesDialogTV.setVisibility(View.VISIBLE);
        noDialogTV.setVisibility(View.VISIBLE);
        neutralDialogTV.setVisibility(View.GONE);
        messageDialogTV.setText(message);
        dialog.show();
    }

    protected void showNeutralDialog(String message) {
        yesDialogTV.setVisibility(View.GONE);
        noDialogTV.setVisibility(View.GONE);
        neutralDialogTV.setVisibility(View.VISIBLE);
        messageDialogTV.setText(message);
        dialog.show();
    }
}
