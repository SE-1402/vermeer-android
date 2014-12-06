package com.candroid.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.util.Log;
import android.view.View;

public class LineDrawable extends View {

    private Path mPath;
    private Paint mPaint;
    private PathShape mPathShape;
    private ShapeDrawable mDrawable;

    public LineDrawable(Context context) {
        super(context);
        setFocusable(true);
        init();
    }

    private void init() {
        this.mPath = new Path();
        this.mPath.moveTo(50, 0);
        this.mPath.lineTo(0, 50);
        this.mPath.close();

        this.mPaint = new Paint();
        this.mPaint.setColor(Color.RED);
        this.mPaint.setStyle(Paint.Style.STROKE);

        this.mPathShape = new PathShape(this.mPath, 50, 50);
        this.mDrawable = new ShapeDrawable(this.mPathShape);
        this.mDrawable.getPaint().set(this.mPaint);
        this.mDrawable.setBounds(0, 0, this.getWidth(), this.getHeight());
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
