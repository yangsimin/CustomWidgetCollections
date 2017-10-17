package com.simon777.customwidgetcollections.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by simon on 2017/10/14.
 */

public class SeparationTextView extends View
{
    String startStr = "313";
    String endStr = "314";
    float y = 0f;
    int alpha = 255;

    TextPaint mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    TextPaint mTextPaintTrans = new TextPaint(Paint.ANTI_ALIAS_FLAG);

    public SeparationTextView(Context context)
    {
        this(context, null);
    }

    public SeparationTextView(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public SeparationTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        mTextPaint.setTextSize(33);
        mTextPaint.setColor(Color.argb(255, 204, 204, 204));
        mTextPaint.setFakeBoldText(true);
        mTextPaintTrans.setTextSize(33);
        mTextPaintTrans.setFakeBoldText(true);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawText(startStr, 0, 2, 0, 60, mTextPaint);
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(startStr, 0, 2, textBounds);

        float width = mTextPaint.measureText(startStr, 0, 2);
        mTextPaintTrans.setColor(Color.argb(alpha, 204, 204, 204));
        canvas.drawText(startStr, 2, 3, width, 60 + y, mTextPaintTrans);

        mTextPaintTrans.setColor(Color.argb(255-alpha, 204, 204, 204));
        canvas.drawText(endStr, 2, 3, width, pos, mTextPaintTrans);
    }

    int pos = 0;
    //点赞
    public void setUpFraction(float fraction)
    {
        this.y = -30 * fraction;
        this.alpha = (int)(255 - 255 * fraction);
        invalidate();
        Log.d("TTT", "setUpFraction: " + fraction + "y:" + y + "alpha:" + alpha);

        pos = 90 + (int)y;
        startStr = "313";
        endStr = "314";
    }

    //取消点赞
    public void setDownFraction(float fraction)
    {
        this.y = 30 * fraction;
        this.alpha = (int)(255 - 255 * fraction);
        invalidate();
        Log.d("TTT", "setUpFraction: " + fraction + "y:" + y + "alpha:" + alpha);

        pos = 30 + (int)y;
        startStr = "314";
        endStr = "313";

    }
}
