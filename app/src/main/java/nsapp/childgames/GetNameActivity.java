package nsapp.childgames;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import nsapp.childgames.utils.Strings;
import nsapp.childgames.view.MTextView;

public class GetNameActivity extends AbstractActivity implements View.OnClickListener {

    private MTextView nameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_name);

        nameTV = (MTextView) findViewById(R.id.nameTV);
        nameTV.setOnClickListener(this);

        findViewById(R.id.validTV).setOnClickListener(this);

        ImageView gumIV = (ImageView) findViewById(R.id.gumIV);
        gumIV.setOnClickListener(this);
        gumIV.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                nameTV.setText("");
                return true;
            }
        });

        onPrepareDialog(this);
    }

    @Override
    protected void onPrepareDialog(View.OnClickListener clickListener) {
        super.onPrepareDialog(clickListener);
        noDialogTV.setText(getString(R.string.get_name_no_message));
        yesDialogTV.setText(getString(R.string.get_name_yes_message));
        neutralDialogTV.setText(getString(R.string.get_name_neutral_message));
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.nameTV:
                showKeyboard();
                break;
            case R.id.gumIV:
                cleanName(nameTV);
                break;
            case R.id.validTV:
                if (TextUtils.isEmpty(nameTV.getText())) {
                    showNeutralDialog(getString(R.string.get_name_no_name_message));
                } else {
                    showConfirmDialog(getString(R.string.get_name_confirm_message_fmt, Strings.firstLetterUppercase(nameTV.getText().toString())));
                }
                break;
            case R.id.yesDialogTV:
                PreferenceManager.getDefaultSharedPreferences(this).edit().putString(USER_NAME_KEY, Strings.firstLetterUppercase(nameTV.getText().toString())).apply();
                dialog.dismiss();
                startActivityWithAnim(ReceptionActivity.class, true);
                break;
        }
    }

    private void showKeyboard() {
        final KeyboardView keyboardView = (KeyboardView) findViewById(R.id.keyboardView);
        keyboardView.setKeyboard(new Keyboard(this, R.xml.letters_keyboard));
        keyboardView.setPreviewEnabled(false);

        keyboardView.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
            @Override
            public void onPress(int i) {
                nameTV.setText(TextUtils.concat(nameTV.getText(), keyboardView.getKeyboard().getKeys().get(i).label));
            }

            @Override
            public void onRelease(int i) {

            }

            @Override
            public void onKey(int i, int[] ints) {

            }

            @Override
            public void onText(CharSequence charSequence) {

            }

            @Override
            public void swipeLeft() {

            }

            @Override
            public void swipeRight() {

            }

            @Override
            public void swipeDown() {

            }

            @Override
            public void swipeUp() {

            }
        });
        keyboardView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_from_bottom));
        nameTV.setOnClickListener(null);
        nameTV.setHint(R.string.empty);
    }

    private void cleanName(MTextView mTextView) {
        CharSequence text = mTextView.getText();
        if (!TextUtils.isEmpty(text)) {
            mTextView.setText(text.subSequence(0, text.length() - 1));
        }
    }
}
