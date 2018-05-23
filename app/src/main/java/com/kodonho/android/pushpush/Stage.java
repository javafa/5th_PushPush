package com.kodonho.android.pushpush;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Stage extends View {

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    int gridCount;
    float unit;
    Player player;

    int currentMap[][];
    Paint gridPaint = new Paint();
    Paint boxPaint = new Paint();
    Paint goalPaint = new Paint();

    public Stage(Context context) {
        super(context);
        gridPaint.setColor(Color.GRAY); // 사각형의 색
        gridPaint.setStyle(Paint.Style.STROKE); // 사각형의 스타일
        gridPaint.setStrokeWidth(1); // 선두께

        boxPaint.setColor(Color.BLACK);
        goalPaint.setColor(Color.MAGENTA);
    }

    public void setConfig(int gridCount, float unit){
        this.gridCount = gridCount;
        this.unit = unit;
    }

    public void addPlayer(Player player) {
        this.player = player;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawMap(canvas);
        drawPlayer(canvas);
    }

    Paint tempPaint;
    private void drawMap(Canvas canvas){
        for(int y=0; y<currentMap.length; y++){
            for(int x=0; x<currentMap[0].length; x++){
                if(currentMap[y][x] == 0) {
                    tempPaint = gridPaint;
                }else if(currentMap[y][x] == 1){
                    tempPaint = boxPaint;
                }else if(currentMap[y][x] == 9){
                    tempPaint = goalPaint;
                }
                canvas.drawRect(
                        x * unit,
                        y * unit,
                        x * unit + unit,
                        y * unit + unit,
                        tempPaint);
            }
        }
    }

    private void drawPlayer(Canvas canvas){
        if(player != null)
            canvas.drawCircle(
                    player.x * unit + unit/2,  // x좌표
                    player.y * unit + unit/2,  // y좌표
                    unit/2,           // 크기
                    player.paint);
    }

    public void move(int direction){
        switch(direction){
            case UP:
                if((player.y - 1) >= 0)
                    player.up();
                break;
            case DOWN:
                if((player.y + 1) < gridCount)
                    player.down();
                break;
            case LEFT:
                if((player.x - 1) >= 0)
                    player.left();
                break;
            case RIGHT:
                if(collisionCheck(RIGHT))
                    player.right();
                break;
        }
        invalidate();
    }

    public boolean collisionCheck(int direction){
        if((player.x + 1) >= gridCount)
            return false;
        // 다음 진행할 곳의 장애물 검사
        if(currentMap[player.y][player.x+1] == 1){
               if(currentMap[player.y][player.x+2] == 1 ){
                   return false;
               }
            currentMap[player.y][player.x+1] = 0;
            currentMap[player.y][player.x+2] = 1;
        }

        return true;
    }

    public void setCurrentMap(int[][] map) {
        currentMap = map;
    }
}
