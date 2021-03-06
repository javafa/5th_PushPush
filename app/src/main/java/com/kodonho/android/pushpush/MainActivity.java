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
    GameMap gameMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGame();
        initStage();
        initPlayer();
        initButton();
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
        gameMap = new GameMap();
    }

    private void initStage(){
        container = findViewById(R.id.container);
        // 스테이지 설정
        stage = new Stage(this);
        stage.setConfig(gridCount, unit);
        // 스테이지 프레임 레이아웃에 넣기
        container.addView(stage);

        stage.setCurrentMap(gameMap.map1);
    }

    private void initPlayer(){
        player = new Player();
        stage.addPlayer(player);
    }

    public void initButton(){
        findViewById(R.id.btnUp).setOnClickListener(buttonListener);
        findViewById(R.id.btnDown).setOnClickListener(buttonListener);
        findViewById(R.id.btnLeft).setOnClickListener(buttonListener);
        findViewById(R.id.btnRight).setOnClickListener(buttonListener);
    }

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnUp:    stage.move(Stage.UP);break;
                case R.id.btnDown:  stage.move(Stage.DOWN);break;
                case R.id.btnLeft:  stage.move(Stage.LEFT);break;
                case R.id.btnRight: stage.move(Stage.RIGHT);break;
            }
        }
    };
}
