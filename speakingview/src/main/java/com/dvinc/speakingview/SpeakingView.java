package com.dvinc.speakingview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 *
 * Created by DV on 10/24/2017.
 */

public class SpeakingView extends FrameLayout {
    
    public SpeakingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        FrameLayout root = (FrameLayout) inflate(context, R.layout.speaking_view, this);
    }

}
