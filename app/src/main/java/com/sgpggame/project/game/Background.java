package com.sgpggame.project.game;

//import kr.ac.tukorea.ge.spgp2023.dragonflight.framework.Metrics;
//import kr.ac.tukorea.ge.spgp2023.dragonflight.framework.Sprite;
import com.sgpggame.project.framework.BaseScene;
import com.sgpggame.project.framework.Metrics;
import com.sgpggame.project.framework.Sprite;

public class Background extends Sprite {
    public Background(int bitmapResId) {
        super(bitmapResId, Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height);
        float height = bitmap.getHeight() * Metrics.game_width / bitmap.getWidth();
        setSize(Metrics.game_width, height);
    }
}