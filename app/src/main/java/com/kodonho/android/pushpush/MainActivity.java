package com.kodonho.android.pushpush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    float stageWidth,stageHeight;
    int gridCount;
    float unit;
    Stage stage;
    FrameLayout container;
    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGame();
        initStage();
        initPlayer();
    }

    private void initGame(){
        // 화면 전체 사이즈 가져오기
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        // 게임판의 가로세로 사이즈
        stageWidth = metrics.widthPixels;
        stageHeight = stageWidth;
        // 가로세로 격자의 개수
        gridCount = 10;
        // 게임 단위 설정
        unit = stageWidth / gridCount;
    }

    private void initStage(){
        container = findViewById(R.id.container);
        // 스테이지 설정
        stage = new Stage(this);
        stage.setConfig(gridCount, unit);
        // 스테이지 프레임 레이아웃에 넣기
        container.addView(stage);
    }

    private void initPlayer(){
        player = new Player();
        stage.addPlayer(player);
    }

    public void up(View view){
        player.up();
        stage.invalidate();
    }
    public void down(View view){
        player.down();
        stage.invalidate();
    }
    public void right(View view){
        player.right();
        stage.invalidate();
    }
    public void left(View view){
        player.left();
        stage.invalidate();
    }
}
