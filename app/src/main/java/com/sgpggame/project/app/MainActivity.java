package com.sgpggame.project.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.sgpggame.project.framework.BaseScene;
import com.sgpggame.project.R;
import com.sgpggame.project.game.MainScene;
import com.sgpggame.project.framework.GameView;

public class MainActivity extends AppCompatActivity {
    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
        new MainScene().pushScene();
    }
    @Override
    protected void onPause() {
        gameView.pauseGame();
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resumeGame();
    }
    @Override
    protected void onDestroy() {
        BaseScene.popAll();
        super.onDestroy();
    }
}