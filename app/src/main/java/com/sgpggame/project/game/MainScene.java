package com.sgpggame.project.game;

import android.util.Log;
import android.view.MotionEvent;
import java.util.Random;
import com.sgpggame.project.framework.BaseScene;
import com.sgpggame.project.framework.Metrics;
import com.sgpggame.project.framework.IGameObject;
public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final Fighter fighter;
    public MainScene() {
        fighter = new Fighter();
        add(fighter);
        add(new EnemyGenerator());
    }
    @Override
    public void update(long elapsedNanos) {
        super.update(elapsedNanos);
        checkCollision();
    }
    private void checkCollision() {
        for (IGameObject o1 : objects) {
            if (!(o1 instanceof Enemy)) {
                continue;
            }
            Enemy enemy = (Enemy) o1;
//            boolean removed = false;
            for (IGameObject o2 : objects) {
                if (!(o2 instanceof Bullet)) {
                    continue;
                }
                Bullet bullet = (Bullet) o2;
                if (CollisionHelper.collides(enemy, bullet)) {
                    Log.d(TAG, "Collision !!");
                    remove(bullet);
                    remove(enemy);
//                    removed = true;
                    break;
                }
            }
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float x = Metrics.toGameX(event.getX());
                float y = Metrics.toGameY(event.getY());
                fighter.setTargetPosition(x, y);
                return true;
        }
        return super.onTouchEvent(event);
    }
}