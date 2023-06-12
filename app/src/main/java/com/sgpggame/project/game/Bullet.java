package com.sgpggame.project.game;

import android.graphics.RectF;
import android.graphics.Paint;

import com.sgpggame.project.framework.BaseScene;
import com.sgpggame.project.R;
import com.sgpggame.project.framework.Sprite;
import com.sgpggame.project.framework.IBoxCollidable;
public class Bullet extends Sprite implements IBoxCollidable {
    private static final float BULLET_WIDTH = 28 * 0.03f;
    private static final float BULLET_HEIGHT = 40 * 0.03f;
    protected static float SPEED = 20.0f;
    protected static Paint paint;
    public Bullet(float x, float y) {
        super(R.mipmap.missile, x, y, BULLET_WIDTH, BULLET_HEIGHT);
        this.x = x;
        this.y = y;
    }
    @Override
    public void update() {
        float frameTime = BaseScene.frameTime;
        y += -SPEED * frameTime;
        fixDstRect();
        if (dstRect.bottom < 0) {
            BaseScene.getTopScene().remove(this);
        }
    }
    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }

}