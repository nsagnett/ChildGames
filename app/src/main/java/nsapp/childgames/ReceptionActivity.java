package nsapp.childgames;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import nsapp.childgames.view.MTextView;

public class ReceptionActivity extends AbstractActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception);

        String userName = PreferenceManager.getDefaultSharedPreferences(this).getString(USER_NAME_KEY, "");
        MTextView receptionTV = (MTextView) findViewById(R.id.receptionTV);
        receptionTV.setText(getString(R.string.reception_message_fmt, userName));
        findViewById(R.id.erreursGameTV).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.erreursGameTV:
                startActivityWithAnim(ErrorsGameActivity.class, false);
                break;
        }
    }
}
