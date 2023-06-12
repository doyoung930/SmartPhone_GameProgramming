package com.sgpggame.project.game;

import android.graphics.RectF;

import com.sgpggame.project.framework.AnimSprite;
import com.sgpggame.project.R;
import com.sgpggame.project.framework.Sprite;
import com.sgpggame.project.framework.Metrics;
import com.sgpggame.project.framework.IBoxCollidable;
import com.sgpggame.project.framework.BaseScene;
import com.sgpggame.project.framework.IRecyclable;
import com.sgpggame.project.framework.RecycleBin;
public class Enemy extends AnimSprite implements IBoxCollidable, IRecyclable {
    private static final String TAG = Enemy.class.getSimpleName();
    private static final int[] resIds = {
            R.mipmap.meteor1, R.mipmap.meteor1, R.mipmap.meteor1, R.mipmap.meteor1, R.mipmap.meteor1,
            R.mipmap.meteor1, R.mipmap.meteor1, R.mipmap.meteor1, R.mipmap.meteor1, R.mipmap.meteor1,
            R.mipmap.meteor1, R.mipmap.meteor1, R.mipmap.meteor1, R.mipmap.meteor1, R.mipmap.meteor1,
            R.mipmap.meteor1, R.mipmap.meteor1, R.mipmap.meteor1, R.mipmap.meteor1, R.mipmap.meteor1
    };
    public static final int MAX_LEVEL = resIds.length - 1;
    private static final float SPEED = 2.0f;
    public static final float SIZE = 1.8f;
    protected RectF collisionRect = new RectF();
    //    protected static ArrayList<Enemy> recycleBin = new ArrayList<>();
    static Enemy get(int index, int level) {
        Enemy enemy = (Enemy) RecycleBin.get(Enemy.class);
        if (enemy != null) {
            enemy.x = (Metrics.game_width / 10) * (2 * index + 1);
            enemy.y = -SIZE;
            return enemy;
        }
        return new Enemy(index, level);
    }
    private Enemy(int index, int level) {
        super(resIds[level], (Metrics.game_width / 10) * (2 * index + 1), -SIZE, SIZE, SIZE, 10, 0);
    }
    @Override
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
    @Override
    public void onRecycle() {
    }
}