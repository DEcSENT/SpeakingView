package dvinc.speakingview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dvinc.speakingview.SpeakingView;

public class MainActivity extends AppCompatActivity {

    private SpeakingView speakingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speakingView = findViewById(R.id.speakingView);

        speakingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakingView.playPhrase("Hello word");
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        speakingView.disposeView();
    }
}
