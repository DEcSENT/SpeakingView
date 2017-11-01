package dvinc.speakingview;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dvinc.speakingview.SpeakingView;

public class MainActivity extends AppCompatActivity {

    private SpeakingView speakingView;
    private TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speakingView = findViewById(R.id.speakingView);
        textInputLayout = findViewById(R.id.userTextInput);

        speakingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakingView.playPhrase(textInputLayout.getEditText().getText().toString());
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        speakingView.disposeView();
    }
}
