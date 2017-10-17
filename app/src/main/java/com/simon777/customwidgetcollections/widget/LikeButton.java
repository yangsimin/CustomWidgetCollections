package com.simon777.customwidgetcollections.widget;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.simon777.customwidgetcollections.R;

/**
 * Cr by simon on 2017/10/13.
 */

public class LikeButton extends RelativeLayout
{
    int[] ImgRes = {R.mipmap.ic_messages_like_unselected, R.mipmap.ic_messages_like_selected, R.mipmap.ic_messages_like_selected_shining};
    boolean isLike = false;
    public LikeButton(Context context)
    {
        this(context, null);
    }

    public LikeButton(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public LikeButton(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        final ImageView likeView = (ImageView) findViewById(R.id.iv_like);
        final ImageView likeShiningView = (ImageView) findViewById(R.id.iv_like_shining);
        final ImageView likeRingView = (ImageView) findViewById(R.id.iv_like_ring);
        final SeparationTextView sepTextView = (SeparationTextView) findViewById(R.id.tv_amount);
        likeShiningView.setImageResource(ImgRes[2]);
        if (!isLike)
        {
            likeView.setImageResource(ImgRes[0]);
            likeShiningView.setAlpha(0f);
            likeShiningView.setScaleX(0f);
            likeShiningView.setScaleY(0f);

            likeRingView.setAlpha(0f);
            likeRingView.setScaleX(0.5f);
            likeRingView.setScaleY(0.5f);
        }
        else
        {
            likeView.setImageResource(ImgRes[1]);
            likeShiningView.setImageAlpha(255);
        }
        likeView.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (isLike)
                {
                    likeView.setImageResource(ImgRes[0]);
                    Keyframe keyframe1 = Keyframe.ofFloat(0.5f, 0.8f);
                    Keyframe keyframe2 = Keyframe.ofFloat(1.0f, 1.0f);
                    PropertyValuesHolder holder1 = PropertyValuesHolder.ofKeyframe("scaleX", keyframe1, keyframe2);
                    PropertyValuesHolder holder2 = PropertyValuesHolder.ofKeyframe("scaleY", keyframe1, keyframe2);
                    ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(likeView, holder1, holder2);
                    objectAnimator1.setDuration(200);
                    objectAnimator1.start();
                    likeShiningView.setAlpha(0f);
                    likeShiningView.setScaleX(0f);
                    likeShiningView.setScaleY(0f);

                    likeRingView.setAlpha(0f);
                    likeRingView.setScaleX(0.5f);
                    likeRingView.setScaleY(0.5f);

                    ObjectAnimator textAnimator = ObjectAnimator.ofFloat(sepTextView, "downFraction", 0, 1);
                    textAnimator.setDuration(300).start();
                }
                else
                {
                    Keyframe keyframe1 = Keyframe.ofFloat(0.2f, 0.8f);
                    Keyframe keyframe2 = Keyframe.ofFloat(0.8f, 1.1f);
                    Keyframe keyframe3 = Keyframe.ofFloat(1.0f, 1.0f);
                    PropertyValuesHolder holder1 = PropertyValuesHolder.ofKeyframe("scaleX", keyframe1, keyframe2, keyframe3);
                    PropertyValuesHolder holder2 = PropertyValuesHolder.ofKeyframe("scaleY", keyframe1, keyframe2, keyframe3);
                    ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(likeView, holder1, holder2);
                    objectAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                    {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation)
                        {
                            float animatedFraction = animation.getAnimatedFraction();
                            if (animatedFraction > 0.2f) likeView.setImageResource(ImgRes[1]);
                        }
                    });

                    objectAnimator1.start();
                    likeShiningView.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(300).setInterpolator(new AnticipateOvershootInterpolator());

                    Keyframe keyframe6 = Keyframe.ofFloat(0.2f, 0f);
                    Keyframe keyframe4 = Keyframe.ofFloat(0.5f, 0.4f);
                    Keyframe keyframe5 = Keyframe.ofFloat(1f, 0f);

                    PropertyValuesHolder holder3 = PropertyValuesHolder.ofKeyframe("alpha", keyframe4, keyframe5, keyframe6);
                    PropertyValuesHolder holder4 = PropertyValuesHolder.ofFloat("scaleX", 1.0f);
                    PropertyValuesHolder holder5 = PropertyValuesHolder.ofFloat("scaleY", 1.0f);
                    ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(likeRingView, holder3, holder4, holder5);
                    objectAnimator2.setDuration(300);
                    objectAnimator2.start();

                    ObjectAnimator textAnimator = ObjectAnimator.ofFloat(sepTextView, "upFraction", 0, 1);
                    textAnimator.setDuration(300).start();
                }
                isLike = !isLike;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

    }
}
