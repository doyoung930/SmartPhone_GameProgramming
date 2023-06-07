package com.sgpggame.smartphoneproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sgpggame.smartphoneproject.R;
import com.sgpggame.framework.GameView;
import com.sgpggame.game.MainScene;
public class MainActivity   {
    private GameView gameView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
        new MainScene().pushScene();
}