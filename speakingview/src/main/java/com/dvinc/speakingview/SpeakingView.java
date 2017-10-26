package com.dvinc.speakingview;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Locale;

/**
 *
 * Created by DV on 10/24/2017.
 */

public class SpeakingView extends FrameLayout {

    private TextToSpeech textToSpeech;
    FrameLayout rootView;

    public SpeakingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        rootView = (FrameLayout) inflate(context, R.layout.speaking_view, this);

        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
    }

    public void playPhrase(@NonNull String text) {
        if (textToSpeech.isSpeaking()) {
            textToSpeech.stop();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "Speak");
        } else {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
        //TODO animation here?
    }

    public void disposeView(){
        textToSpeech.shutdown();
    }
}
