package com.sgpggame.project.game;

import android.graphics.RectF;

import com.sgpggame.project.R;
import com.sgpggame.project.framework.Sprite;
import com.sgpggame.project.framework.Metrics;
import com.sgpggame.project.framework.IBoxCollidable;
import com.sgpggame.project.framework.BaseScene;
public class Enemy extends Sprite implements IBoxCollidable {
    private static final int[] resIds = {
            R.mipmap.meteor1, R.mipmap.meteor2, R.mipmap.meteor3, R.mipmap.meteor4, R.mipmap.unsuck
    };
    public static final int MAX_LEVEL = resIds.length - 1;
    private static final float SPEED = 2.0f;

    public static final float SIZE = 1.8f;
    protected RectF collisionRect = new RectF();
    public Enemy(int index, int level) {
        super(resIds[level], (Metrics.game_width / 10) * (2 * index + 1), -SIZE, SIZE, SIZE);
    }
    public void update() {
        super.update();
        y += SPEED * BaseScene.frameTime;
        fixDstRect();
        if (dstRect.top > 16.0) {
            BaseScene.getTopScene().remove(this);
        }
        collisionRect.set(dstRect);
        collisionRect.inset(0.11f, 0.11f);
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

}