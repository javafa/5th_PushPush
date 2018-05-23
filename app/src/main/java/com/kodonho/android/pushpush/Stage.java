package com.kodonho.android.pushpush;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Stage extends View {
    int gridCount;
    float unit;
    Player player;

    public Stage(Context context) {
        super(context);
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
        drawPlayer(canvas);
    }

    private void drawPlayer(Canvas canvas){
        if(player != null)
            canvas.drawCircle(
                    player.x*unit + unit/2,  // x좌표
                    player.y*unit + unit/2,  // y좌표
                    unit/2,           // 크기
                    player.paint);
    }
}
