package com.candroid.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.util.Log;
import android.view.View;

import com.candroid.app.dto.FillAttributes;
import com.candroid.app.dto.LineAttributes;

public class RectangleDrawable extends View {

    private Path mPath;
    private Paint mPaint;
    private PathShape mPathShape;
    private ShapeDrawable mDrawable;

    public RectangleDrawable(Context context) {
        super(context);
        setFocusable(true);
        init();
    }

    public RectangleDrawable(Context context, LineAttributes lineAttributes, FillAttributes fillAttributes) {
        super(context);
        setFocusable(true);
        init();
    }

    private void init() {
        this.mPath = new Path();
        this.mPath.moveTo(0, 0);
        this.mPath.lineTo(0, 50);
        this.mPath.lineTo(20, 50);
        this.mPath.lineTo(20, 0);
        this.mPath.close();

        this.mPaint = new Paint();
        this.mPaint.setColor(Color.RED);
        this.mPaint.setStyle(Paint.Style.STROKE);

        this.mPathShape = new PathShape(this.mPath, 50, 50);
        this.mDrawable = new ShapeDrawable(this.mPathShape);
        this.mDrawable.getPaint().set(this.mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d("onDraw", "Drawing");

        int x = 10;
        int y = 10;
        int width = 300;
        int height = 50;

        this.mDrawable.setBounds(x, y, x + width, y + height);
        this.mDrawable.draw(canvas);
    }
}
