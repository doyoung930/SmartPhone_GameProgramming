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
    private static final float TARGET_RADIUS = 0.5f;
    private static final float SPEED = 10.0f;
    private static final float FIGHTER_LEFT = FIGHTER_WIDTH / 2;
    private static final float FIGHTER_RIGHT = 9.0f - FIGHTER_WIDTH / 2;
    private static final float FIGHTER_BOTTOM = FIGHTER_HEIGHT / 2;
    private static final float FIGHTER_TOP = 16.0f - FIGHTER_HEIGHT / 2;
    private float tx;
    private float ty;
    private Bitmap targetBitmap;
    private RectF targetRect = new RectF();
    private Bitmap sparkBitmap;
    private RectF sparkRect = new RectF();
    private static final float SPARK_WIDTH = 50 * 0.0243f;
    private static final float SPARK_HEIGHT = 30 * 0.0243f;
    private static final float SPARK_OFFSET = 0.7f;
    private static final float FIRE_INTERVAL = 0.25f;
    private static final float SPARK_DURATION = 0.1f;
    private float accumulatedTime;
    public Fighter() {
        super(R.mipmap.earth2, FIGHTER_X, FIGHTER_Y, FIGHTER_WIDTH, FIGHTER_HEIGHT);
        targetBitmap = BitmapPool.get(R.mipmap.target);
        sparkBitmap = BitmapPool.get(R.mipmap.boom);

    }
    public void setTargetPosition(float tx, float ty) {
        this.tx = tx;
        this.ty = ty;
        targetRect.set(
                tx - TARGET_RADIUS, ty - TARGET_RADIUS,
                tx + TARGET_RADIUS, ty + TARGET_RADIUS);
    }
    private void checkFire() {
        //accumulatedTime += BaseScene.frameTime;
      //  if (accumulatedTime < FIRE_INTERVAL) {
       //     return;
       // }
       //accumulatedTime -= FIRE_INTERVAL;
        //accumulatedTime = 0; // ??
        //fire();
    }
    public void fire() {
        Bullet bullet = Bullet.get(x, y);
        BaseScene.getTopScene().add(MainScene.Layer.bullet, bullet);
    }
    @Override
    public void update() {
        super.update();
        float time = BaseScene.frameTime;
//        if (tx >= x) {
//            x += SPEED * time;
//            if (x > tx) {
//                x = tx;
//            }
//        } else {
//            x -= SPEED * time;
//            if (x < tx) {
//                x = tx;
//            }
//        }
//        if (x < FIGHTER_LEFT) x = tx = FIGHTER_LEFT;
//        if (x > FIGHTER_RIGHT) x = tx = FIGHTER_RIGHT;
//
//        if (ty >= y) {
//            y += SPEED * time;
//            if (y > ty) {
//                y = ty;
//            }
//        } else {
//            y -= SPEED * time;
//            if (y < ty) {
//                y = ty;
//            }
//        }
//        if (y < FIGHTER_BOTTOM) y = ty = FIGHTER_BOTTOM;
//        if (y > FIGHTER_TOP) y = ty = FIGHTER_TOP;
        fixDstRect();
        checkFire();
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (tx != x) {
            canvas.drawBitmap(targetBitmap, null, targetRect, null);
        }
//       if (accumulatedTime < SPARK_DURATION) {
//           sparkRect.set(x - SPARK_WIDTH/2, y - SPARK_HEIGHT/2 - SPARK_OFFSET,
//                   x + SPARK_WIDTH/2, y + SPARK_HEIGHT/2 - SPARK_OFFSET);
//           canvas.drawBitmap(sparkBitmap, null, sparkRect, null);
//       }
    }
}
