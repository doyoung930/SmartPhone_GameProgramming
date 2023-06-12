package com.sgpggame.project.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import com.sgpggame.project.R;
import com.sgpggame.project.framework.BaseScene;
import com.sgpggame.project.framework.BitmapPool;
import com.sgpggame.project.framework.Sprite;

public class Fighter extends Sprite {

    private static final float FIGHTER_X = 4.5f;
    private static final float FIGHTER_Y = 14.8f;
    private static final float FIGHTER_WIDTH = 72 * 0.0243f; //1.75f;
    private static final float FIGHTER_HEIGHT = 80 * 0.0243f; //1.75f;

    private static final float SPARK_WIDTH = 50 * 0.0243f;
    private static final float SPARK_HEIGHT = 30 * 0.0243f;
    private static final float FIRE_INTERVAL = 0.5f;

    private Bitmap targetBitmap;
    private RectF targetRect = new RectF();

    private float tx, ty; // touch event 를 받은 위치. 이 위치를 향해서 움직인다
    private float dx, dy; // 1초간 움직여야 할 양: dx = SPEED*cos(r); dy = SPEED*sin(r);
    private static float SPEED = 10.0f;
    private float angle;

    private float accumulatedTime;

    public Fighter() {
        super(R.mipmap.earth, FIGHTER_X, FIGHTER_Y, FIGHTER_WIDTH, FIGHTER_HEIGHT);

    }

    @Override
    public void update() {
        float time = BaseScene.frameTime;
        x += dx * time;
        if ((dx > 0 && x > tx) || (dx < 0 && x < tx)) {
            x = tx; dx = 0;
        }
        this.y += this.dy * time;
        if ((dy > 0 && y > ty) || (dy < 0 && y < ty)) {
            y = ty; dy = 0;
        }
        fixDstRect();
        checkFire();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate(angle, x, y);
        canvas.drawBitmap(bitmap, null, dstRect, null);
        canvas.restore();
        if (dx != 0 || dy != 0) {
            float r = 1.0f;
            targetRect.set(tx - r, ty - r, tx + r, ty + r);
            canvas.drawBitmap(targetBitmap, null, targetRect, null);
        }
    }

    public void setTargetPosition(float tx, float ty) {
        this.tx = tx;
        this.ty = ty;
        float dx = tx - this.x;
        float dy = ty - this.y;
        double radian = Math.atan2(dy, dx);
        this.dx = (float) (SPEED * Math.cos(radian));
        this.dy = (float) (SPEED * Math.sin(radian));
        angle = (float) Math.toDegrees(radian) + 90;
    }

    private void checkFire() {
        accumulatedTime += BaseScene.frameTime;
        if (accumulatedTime < FIRE_INTERVAL) {
            return;
        }

        accumulatedTime -= FIRE_INTERVAL;
        //accumulatedTime = 0; // ??
        fire();
    }
    public void fire() {
        Bullet bullet = new Bullet(x, y);
        BaseScene.getTopScene().add(bullet);
    }
}
