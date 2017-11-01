package com.dvinc.speakingview;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import java.util.Locale;

/**
 *
 * Created by DV on 10/24/2017.
 */

public class SpeakingView extends FrameLayout {

    private TextToSpeech textToSpeech;
    private FrameLayout rootView;
    private Animation circleAnimation;
    private View backgroundView;
    private boolean isPhrasePlaying;

    public SpeakingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        rootView = (FrameLayout) inflate(context, R.layout.speaking_view, this);
        backgroundView = findViewById(R.id.backgroundView);

        circleAnimation = AnimationUtils.loadAnimation(context, R.anim.circle_animation);
        circleAnimation.setAnimationListener(circleAnimation1Listener);

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

        startCircleAnimation();
        //TODO: Maybe add default text with error animation?
    }

    public void disposeView(){
        textToSpeech.shutdown();
    }

    void startCircleAnimation(){
        backgroundView.startAnimation(circleAnimation);
    }

    Animation.AnimationListener circleAnimation1Listener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            isPhrasePlaying = true;
            backgroundView.setBackgroundResource(R.drawable.circle);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(textToSpeech.isSpeaking()){
                //TODO: Warning, need other animation because this gives bug with circle!
                startCircleAnimation();
            } else {
                backgroundView.setBackground(null);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
}
