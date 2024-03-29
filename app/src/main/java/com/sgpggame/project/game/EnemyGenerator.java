package com.sgpggame.project.game;

import android.graphics.Canvas;
import java.util.Random;
import android.util.Log;
import com.sgpggame.project.framework.BaseScene;
import com.sgpggame.project.framework.IGameObject;
public class EnemyGenerator implements IGameObject {
    private static final float GEN_INTERVAL = 5.0f;
    private static final String TAG = Enemy.class.getSimpleName();
    private float time;
    private int wave;
    @Override
    public void update() {
        time += BaseScene.frameTime;
        if (time > GEN_INTERVAL) {
            generate();
            time -= GEN_INTERVAL;
        }
    }
    private void generate() {
        wave++;
        Log.v(TAG, "Generating: wave " + wave);
        Random r = new Random();
        BaseScene scene = BaseScene.getTopScene();
        for (int i = 0; i < 1; i++) {
            int level = (wave + 15) / 10 - r.nextInt(3);
            if (level < 0) level = 0;
            if (level > Enemy.MAX_LEVEL) level = Enemy.MAX_LEVEL;
            scene.add(MainScene.Layer.enemy, Enemy.get(i, level));
        }
    }
    @Override
    public void draw(Canvas canvas) {}
}