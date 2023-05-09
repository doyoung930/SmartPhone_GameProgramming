package com.sgpggame.project.game;

import android.graphics.RectF;

import com.sgpggame.project.R;
//import kr.ac.tukorea.ge.spgp2023.dragonflight.R;
import com.sgpggame.framework.scene.BaseScene;
import com.sgpggame.framework.interfaces.IBoxCollidable;
import com.sgpggame.framework.interfaces.IRecyclable;
import com.sgpggame.framework.scene.RecycleBin;
import com.sgpggame.framework.objects.Sprite;

public class Bullet extends Sprite implements IBoxCollidable, IRecyclable {
    private static final float BULLET_WIDTH = 28 * 0.0243f;
    private static final float BULLET_HEIGHT = 40 * 0.0243f;
    private static final String TAG = Bullet.class.getSimpleName();
    protected static float SPEED = 20.0f;
    protected int power;

    // 불릿 좌표를 저장된 터치 좌표로
    public static Bullet get(float x, float y, int power) {
        Bullet bullet = (Bullet) RecycleBin.get(Bullet.class);
        if (bullet != null) {
            bullet.x = x;
            bullet.y = y;
            bullet.power = power;
            return bullet;
        }
        return new Bullet(x, y, power);
    }
    private Bullet(float x, float y, int power) {
        super(R.mipmap.laser_1, x, y, BULLET_WIDTH, BULLET_HEIGHT);
        this.x = x;
        this.y = y;
        this.power = power;
    }
    @Override
    public void update() {
        float frameTime = BaseScene.frameTime;
        y += -SPEED * frameTime;
        fixDstRect();

        if (dstRect.bottom < 0) {
            BaseScene.getTopScene().remove(MainScene.Layer.bullet, this);
        }
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }

    @Override
    public void onRecycle() {
    }

    public int getPower() {
        return power;
    }
}
