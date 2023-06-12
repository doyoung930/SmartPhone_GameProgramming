package com.sgpggame.project.game;

import android.graphics.RectF;
import android.graphics.Paint;
import android.util.Log;
import java.util.ArrayList;
import com.sgpggame.project.framework.BaseScene;
import com.sgpggame.project.R;
import com.sgpggame.project.framework.Sprite;
import com.sgpggame.project.framework.IBoxCollidable;
import com.sgpggame.project.framework.IRecyclable;
import com.sgpggame.project.framework.RecycleBin;

public class Bullet extends Sprite implements IBoxCollidable, IRecyclable {
    private static final float BULLET_WIDTH = 28 * 0.03f;
    private static final float BULLET_HEIGHT = 40 * 0.03f;
    private static final String TAG = Bullet.class.getSimpleName();
    protected static float SPEED = 20.0f;
    protected static Paint paint;
    protected static ArrayList<Bullet> recycleBin = new ArrayList<>();
    public static Bullet get(float x, float y) {
        Bullet bullet = (Bullet) RecycleBin.get(Bullet.class);
        if (bullet != null) {
            bullet.x = x;
            bullet.y = y;
            return bullet;
        }
        return new Bullet(x, y);
    }
    private Bullet(float x, float y) {
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
            recycleBin.add(this);
     //       Log.d(TAG, "remove(): Recycle Bin has " + recycleBin.size() + " bullets");
        }
    }
    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
    @Override
    public void onRecycle() {
    }

}