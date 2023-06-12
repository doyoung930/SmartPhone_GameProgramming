package com.sgpggame.project.game;


import android.util.Log;
import android.view.MotionEvent;
import java.util.ArrayList;
import com.sgpggame.project.R;
import com.sgpggame.project.framework.AnimSprite;
import com.sgpggame.project.framework.BaseScene;
import com.sgpggame.project.framework.Metrics;
import com.sgpggame.project.framework.IGameObject;
import com.sgpggame.project.framework.CollisionHelper;
public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final Fighter fighter;
    public enum Layer {
        bg, ui, enemy, bullet, player, controller, COUNT
    }

    private Score score;
    public MainScene() {
        initLayers(Layer.COUNT);
        fighter = new Fighter();
        add(Layer.player, fighter);
        add(Layer.bg, new VertScrollBackground(R.mipmap.space, 1.0f));
        //add(Layer.bg, new Background(R.mipmap.clouds));
        score = new Score();
        add(Layer.ui, score);
        add(Layer.controller, new EnemyGenerator());
        add(Layer.controller, new CollisionChecker());
    }
    public void addScore(int amount) {
        score.add(amount);
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