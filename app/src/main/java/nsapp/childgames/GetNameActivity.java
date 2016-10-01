package nsapp.childgames;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import nsapp.childgames.view.MTextView;

public class GetNameActivity extends AbstractActivity implements View.OnClickListener {

    private MTextView nameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_name);
        onPrepareDialog(this, false);

        nameTV = (MTextView) findViewById(R.id.nameTV);
        nameTV.setOnClickListener(this);

        findViewById(R.id.validTV).setOnClickListener(this);

        ImageView gumIV = (ImageView) findViewById(R.id.gumIV);
        gumIV.setOnClickListener(this);
        gumIV.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                nameTV.setText(" ");
                return true;
            }
        });

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.nameTV:
                showKeyboard();
                break;
            case R.id.gumIV:
                removeLastCharName(nameTV);
                break;
            case R.id.validTV:
                if (nameTV.getText().length() <= 1) {
                    showNeutralDialog(getString(R.string.get_name_no_name_message), getString(R.string.normal_neutral_message));
                } else {
                    showConfirmDialog(getString(R.string.get_name_confirm_message_fmt, TextUtils.substring(nameTV.getText(), 0, nameTV.getText().length() - 1)),
                            getString(R.string.normal_dismiss_message),
                            getString(R.string.normal_valid_message));
                }
                break;
            case R.id.yesDialogTV:
                PreferenceManager.getDefaultSharedPreferences(this).edit().putString(USER_NAME_KEY, TextUtils.substring(nameTV.getText(), 0, nameTV.getText().length() - 1)).apply();
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
                if (nameTV.getText().length() <= 1) {
                    nameTV.setText(TextUtils.concat(nameTV.getText(), keyboardView.getKeyboard().getKeys().get(i).label, " "));
                } else {
                    nameTV.setText(TextUtils.substring(nameTV.getText(), 0, nameTV.getText().length() - 1));
                    nameTV.setText(TextUtils.concat(nameTV.getText(), keyboardView.getKeyboard().getKeys().get(i).label, " "));
                }
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

    private void removeLastCharName(MTextView mTextView) {
        CharSequence text = mTextView.getText();
        if (text.length() > 1) {
            mTextView.setText(TextUtils.concat(text.subSequence(0, text.length() - 2), " "));
        }
    }
}
