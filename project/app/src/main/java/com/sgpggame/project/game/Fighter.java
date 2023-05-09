package com.sgpggame.project.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.sgpggame.project.R;
//import kr.ac.tukorea.ge.spgp2023.dragonflight.R;
import com.sgpggame.framework.scene.BaseScene;
import com.sgpggame.framework.res.BitmapPool;
import com.sgpggame.framework.objects.Sprite;
import com.sgpggame.framework.view.Metrics;

public class Fighter extends Sprite {
    //private static final float FIGHTER_X = 4.5f;
    private static final float FIGHTER_Y_OFFSET = 1.2f;
    private static final float FIGHTER_WIDTH = 72 * 0.0243f; //1.75f;
    private static final float FIGHTER_HEIGHT = 80 * 0.0243f; //1.75f;
    private static final float TARGET_RADIUS = 0.5f;
    private static final float SPEED = 10.0f;
    private static final float FIGHTER_LEFT = FIGHTER_WIDTH / 2;
    private static final float FIGHTER_RIGHT = 9.0f - FIGHTER_WIDTH / 2;
    private static final String TAG = Fighter.class.getSimpleName();

    private float tx;
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

    private static final float MAX_ROLL_TIME = 0.4f;
    private float rollTime;

//    private static final Rect[] rects = new Rect[] {
//            new Rect(  8, 0,   8 + 42, 80),
//            new Rect( 76, 0,  76 + 42, 80),
//            new Rect(140, 0, 140 + 50, 80),
//            new Rect(205, 0, 205 + 56, 80),
//            new Rect(270, 0, 270 + 62, 80),
//            new Rect(334, 0, 334 + 70, 80),
//            new Rect(406, 0, 406 + 62, 80),
//            new Rect(477, 0, 477 + 56, 80),
//            new Rect(549, 0, 549 + 48, 80),
//            new Rect(621, 0, 621 + 42, 80),
//            new Rect(689, 0, 689 + 42, 80),
//    };

   private static final Rect[] rects = new Rect[] {
           new Rect( 0,    0,  0    + 800, 600),
           new Rect( 800,  0,  800  + 800, 600),
           new Rect( 1600, 0,  1600 + 800, 600),
           new Rect( 2400, 0,  2400 + 800, 600),
           new Rect( 3200, 0,  3200 + 800, 600),
           new Rect( 4000, 0,  4000 + 800, 600),
           new Rect( 4800, 0,  4800 + 800, 600),

           new Rect( 0,    600,  0    + 800, 1200),
           new Rect( 800,  600,  800  + 800, 1200),
           new Rect( 1600, 600,  1600 + 800, 1200),
           new Rect( 2400, 600,  2400 + 800, 1200),
           new Rect( 3200, 600,  3200 + 800, 1200),
           new Rect( 4000, 600,  4000 + 800, 1200),
           new Rect( 4800, 600,  4800 + 800, 1200),
//
           new Rect( 0,    1200,  0    + 800, 1800),
           new Rect( 800,  1200,  800  + 800, 1800),
           new Rect( 1600, 1200,  1600 + 800, 1800),
           new Rect( 2400, 1200,  2400 + 800, 1800),
           new Rect( 3200, 1200,  3200 + 800, 1800),
           new Rect( 4000, 1200,  4000 + 800, 1800),
           new Rect( 4800, 1200,  4800 + 800, 1800),

           new Rect( 0,   1800,  0    + 800, 2400),
           new Rect( 800, 1800,  800  + 800, 2400),
           new Rect( 1600,1800,  1600 + 800, 2400),
           new Rect( 2400,1800,  2400 + 800, 2400),
           new Rect( 3200,1800,  3200 + 800, 2400),
           new Rect( 4000,1800,  4000 + 800, 2400),
           new Rect( 4800,1800,  4800 + 800, 2400),

           new Rect( 0,    2400,  0    + 800, 3000),
           new Rect( 800,  2400,  800  + 800, 3000),
           new Rect( 1600, 2400,  1600 + 800, 3000),
           new Rect( 2400, 2400,  2400 + 800, 3000),
           new Rect( 3200, 2400,  3200 + 800, 3000),
           new Rect( 4000, 2400,  4000 + 800, 3000),
           new Rect( 4800, 2400,  4800 + 800, 3000),

           new Rect( 0,    3000,  0    + 800, 3600),
           new Rect( 800,  3000,  800  + 800, 3600),
           new Rect( 1600, 3000,  1600 + 800, 3600),
           new Rect( 2400, 3000,  2400 + 800, 3600),
           new Rect( 3200, 3000,  3200 + 800, 3600),
           new Rect( 4000, 3000,  4000 + 800, 3600),
           new Rect( 4800, 3000,  4800 + 800, 3600),

           new Rect( 0,    3600,  0    + 800, 4200),
           new Rect( 800,  3600,  800  + 800, 4200),
           new Rect( 1600, 3600,  1600 + 800, 4200),
           new Rect( 2400, 3600,  2400 + 800, 4200),
           new Rect( 3200, 3600,  3200 + 800, 4200),
           new Rect( 4000, 3600,  4000 + 800, 4200),
           new Rect( 4800, 3600,  4800 + 800, 4200),

           new Rect( 0,    4200,  0    + 800, 4800),
           new Rect( 800,  4200,  800  + 800, 4800),
           new Rect( 1600, 4200,  1600 + 800, 4800),
           new Rect( 2400, 4200,  2400 + 800, 4800),
           new Rect( 3200, 4200,  3200 + 800, 4800),
           new Rect( 4000, 4200,  4000 + 800, 4800),
           new Rect( 4800, 4200,  4800 + 800, 4800),

           new Rect( 0,    4800,  0    + 800, 5400),
           new Rect( 800,  4800,  800  + 800, 5400),
           new Rect( 1600, 4800,  1600 + 800, 5400),
           new Rect( 2400, 4800,  2400 + 800, 5400),
           new Rect( 3200, 4800,  3200 + 800, 5400),
           new Rect( 4000, 4800,  4000 + 800, 5400),
           new Rect( 4800, 4800,  4800 + 800, 5400),

//
   };

    public Fighter() {
        super(R.mipmap.player, Metrics.game_width / 2, Metrics.game_height - FIGHTER_Y_OFFSET, FIGHTER_WIDTH, FIGHTER_HEIGHT);
        targetBitmap = BitmapPool.get(R.mipmap.target);
      //  sparkBitmap = BitmapPool.get(R.mipmap.laser_0);
        tx = x;
    }

    public void setTargetPosition(float tx, float ty) {
        this.tx = tx;
        targetRect.set(
                tx - TARGET_RADIUS, y - TARGET_RADIUS,
                tx + TARGET_RADIUS, y + TARGET_RADIUS);
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
        MainScene scene = (MainScene) BaseScene.getTopScene();
        int score = scene.getScore();
        int power = 10 + score / 1000;
        Bullet bullet = Bullet.get(x, y, power);
        BaseScene.getTopScene().add(MainScene.Layer.bullet, bullet);
    }

    @Override
    public void update() {
        super.update();

        float time = BaseScene.frameTime;
        if (tx >= x) {
            x += SPEED * time;
            if (x > tx) {
                x = tx;
            }
        } else {
            x -= SPEED * time;
            if (x < tx) {
                x = tx;
            }
        }
        if (x < FIGHTER_LEFT) x = tx = FIGHTER_LEFT;
        if (x > FIGHTER_RIGHT) x = tx = FIGHTER_RIGHT;
        fixDstRect();

        int sign = tx < x ? -1 : x < tx ? 1 : 0; // roll 을 변경시킬 부호를 정한다
        if (x == tx) {                         // 비행기가 멈췄을 때
            if (rollTime > 0) sign = -1;         // 오른쪽으로 움직이고 있었다면 감소시킨다
            else if (rollTime < 0) sign = 1;     // 왼쪽으로 움직이고 있었다면 증가시킨다
        }
        rollTime += sign * time;
        if (x == tx) {                           // 비행기가 멈췄을 때
            if (sign < 0 && rollTime < 0) rollTime = 0; // 감소중이었는데 0 을 지나쳤다면 0으로
            if (sign > 0 && rollTime > 0) rollTime = 0; // 증가중이었는데 0 을 지나쳤다면 0으로
        }
        if (rollTime < -MAX_ROLL_TIME) rollTime = -MAX_ROLL_TIME;    // 최대 MAX_ROLL_TIME 까지만
        else if (rollTime > MAX_ROLL_TIME) rollTime = MAX_ROLL_TIME;

        if (rollTime != 0) {
            Log.v(TAG, "RollTime = " + rollTime);
        }

        checkFire();
    }

    @Override
    public void draw(Canvas canvas) {
        int rollIndex = 5 + (int)(rollTime * 5 / MAX_ROLL_TIME);
        canvas.drawBitmap(bitmap, rects[rollIndex], dstRect, null);
        if (tx != x) {
            canvas.drawBitmap(targetBitmap, null, targetRect, null);
        }
        if (accumulatedTime < SPARK_DURATION) {
            sparkRect.set(x - SPARK_WIDTH/2, y - SPARK_HEIGHT/2 - SPARK_OFFSET,
                    x + SPARK_WIDTH/2, y + SPARK_HEIGHT/2 - SPARK_OFFSET);
           // canvas.drawBitmap(sparkBitmap, null, sparkRect, null);
        }
    }
}
