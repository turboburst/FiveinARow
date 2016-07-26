package com.demo.turbo.fiveinarowmodule;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by turbo on 2016-05-15.
 */
public class FiveInaRowPanel extends View {

    private Context context;
    private int PanelWidth;
    private float LineHeight;
    private static int MaxLine = 10;
    private static int MaxInLine = 5;

    private Paint thePaint = new Paint();
    private Bitmap whitePlayer;
    private Bitmap blackPlayer;
    private float rate = 3 * 1.0F / 5;

    private boolean currentWhite = true;
    private boolean currentBlack = false;
    private boolean isGameOver;
    private boolean isWhitePlayerWinner = false;
    private boolean isBlackPlayerWinner = false;

    private static String INSTANCE = "instance";
    private static String INSTANCE_GAME_OVER ="instance_game_over";
    private static String INSTANCE_WHITE_ARRAY = "instance_white_array";
    private static String INSTANCE_BLACK_ARRAY = "instance_black_array";
    private static String INSTANCE_CURRENT_WHITE = "instance_current_white";
    private static String INSTANCE_CURRENT_BLACK = "instance_current_black";

    private ArrayList<Point> whitePlayerList = new ArrayList<Point>();
    private ArrayList<Point> blackPlayerList = new ArrayList<Point>();

    public FiveInaRowPanel(Context context) {
        super(context);
    }

    public FiveInaRowPanel(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        thePaint.setColor(0x77000000);
        thePaint.setAntiAlias(true);
        thePaint.setDither(true);
        thePaint.setStyle(Paint.Style.STROKE);

        whitePlayer = BitmapFactory.decodeResource(getResources(), R.mipmap.stone_w2);
        blackPlayer = BitmapFactory.decodeResource(getResources(), R.mipmap.stone_b1);
    }

    public FiveInaRowPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FiveInaRowPanel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int WidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int WidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int HeightSize = MeasureSpec.getSize(heightMeasureSpec);
        int HeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int Width = Math.min(WidthSize, HeightSize);
        int Height = HeightSize;

        if(WidthMode == MeasureSpec.UNSPECIFIED)
        {
            Width = HeightSize;
        }
        else if(HeightMode == MeasureSpec.UNSPECIFIED)
        {
            Width = WidthSize;
        }
        setMeasuredDimension(Width, Width);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        PanelWidth = w;
        LineHeight = PanelWidth * 1.0F / MaxLine;
        int playerWidth = (int)(LineHeight * rate);
        whitePlayer = Bitmap.createScaledBitmap(whitePlayer, playerWidth, playerWidth, false);
        blackPlayer = Bitmap.createScaledBitmap(blackPlayer, playerWidth, playerWidth, false);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        drawTheBoard(canvas);
        drawThePlayer(canvas);
        checkGameState();

    }

    private void checkGameState()
    {
        boolean whitePlayerWin = CheckIfFiveInLine(whitePlayerList);
        boolean blackPlayerWin = CheckIfFiveInLine(blackPlayerList);
        if(whitePlayerWin || blackPlayerWin)
        {
            isGameOver = true;
            isWhitePlayerWinner = whitePlayerWin;
            isBlackPlayerWinner = blackPlayerWin;
            if(isWhitePlayerWinner)
            {
                Toast.makeText(getContext(), "The white player is winner", Toast.LENGTH_LONG).show();
            }
            else if(isBlackPlayerWinner)
            {
                Toast.makeText(getContext(), "The black player is winner", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean CheckIfFiveInLine(List<Point> points)
    {
        for(Point p: points)
        {
            int x = p.x;
            int y = p.y;
            boolean win = checkHorizontal(x, y, points);
            if(win)
            {
                return true;
            }
            win = checkVertical(x, y, points);
            if(win)
            {
                return true;
            }
            win = checkLeftXie(x, y, points);
            if(win)
            {
                return true;
            }
            win = checkRightXie(x, y, points);
            if(win)
            {
                return true;
            }
        }
        return false;
    }

    private boolean checkHorizontal(int x, int y, List<Point> points)
    {
        int count = 1;
        for(int i = 1; i < MaxInLine; i ++)
        {
            if(points.contains(new Point(x - i, y)))
            {
                count ++;
            }
            else
            {
                break;
            }
        }

        if(count == MaxInLine)
        {
            return true;
        }

        for(int i = 1; i < MaxInLine; i ++)
        {
            if(points.contains(new Point(x + i, y)))
            {
                count ++;
            }
            else
            {
                break;
            }
        }

        if(count == MaxInLine)
        {
            return true;
        }
        return false;
    }
    private boolean checkVertical(int x, int y, List<Point> points)
    {
        int count = 1;
        for(int i = 1; i < MaxInLine; i ++)
        {
            if(points.contains(new Point(x, y - i)))
            {
                count ++;
            }
            else
            {
                break;
            }
        }

        if(count == MaxInLine)
        {
            return true;
        }

        for(int i = 1; i < MaxInLine; i ++)
        {
            if(points.contains(new Point(x, y + i)))
            {
                count ++;
            }
            else
            {
                break;
            }
        }

        if(count == MaxInLine)
        {
            return true;
        }
        return false;
    }
    private boolean checkLeftXie(int x, int y, List<Point> points)
    {
        int count = 1;
        for(int i = 1; i < MaxInLine; i ++)
        {
            if(points.contains(new Point(x - i, y + i)))
            {
                count ++;
            }
            else
            {
                break;
            }
        }

        if(count == MaxInLine)
        {
            return true;
        }

        for(int i = 1; i < MaxInLine; i ++)
        {
            if(points.contains(new Point(x + i, y - i)))
            {
                count ++;
            }
            else
            {
                break;
            }
        }

        if(count == MaxInLine)
        {
            return true;
        }
        return false;
    }

    private boolean checkRightXie(int x, int y, List<Point> points)
    {
        int count = 1;
        for(int i = 1; i < MaxInLine; i ++)
        {
            if(points.contains(new Point(x + i, y + i)))
            {
                count ++;
            }
            else
            {
                break;
            }
        }

        if(count == MaxInLine)
        {
            return true;
        }

        for(int i = 1; i < MaxInLine; i ++)
        {
            if(points.contains(new Point(x - i, y - i)))
            {
                count ++;
            }
            else
            {
                break;
            }
        }

        if(count == MaxInLine)
        {
            return true;
        }
        return false;
    }

    private void drawThePlayer(Canvas canvas)
    {
        for(int i = 0; i < whitePlayerList.size(); i ++)
        {
            Point whitePlayerpoint = whitePlayerList.get(i);
            canvas.drawBitmap(whitePlayer,
                    (whitePlayerpoint.x + (1 - rate) / 2) * LineHeight,
                    (whitePlayerpoint.y + (1 - rate) / 2) * LineHeight, null);
        }

        for(int i = 0; i < blackPlayerList.size(); i ++)
        {
            Point blackPlayerpoint = blackPlayerList.get(i);
            canvas.drawBitmap(blackPlayer,
                    (blackPlayerpoint.x + (1 - rate) / 2) * LineHeight,
                    (blackPlayerpoint.y + (1 - rate) / 2) * LineHeight, null);
        }
    }

    private void drawTheBoard(Canvas canvas)
    {
        int w = PanelWidth;
        float lineH = LineHeight;

        for(int i = 0; i < MaxLine; i ++)
        {
            int startX = (int)(lineH / 2);
            int endX = (int)(w - lineH / 2);
            int y = (int)((0.5 +i) * lineH);
            canvas.drawLine(startX, y, endX, y, thePaint);
            canvas.drawLine(y, startX, y, endX, thePaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(isGameOver)
        {
            return false;
        }

        int Action = event.getAction();

        if(Action == MotionEvent.ACTION_UP)
        {
            int x = (int) event.getX();
            int y = (int) event.getY();
            Point p = new Point((int)(x / LineHeight), (int)(y / LineHeight));

            if(whitePlayerList.contains(p) || blackPlayerList.contains(p))
            {
                return false;
            }

            if(currentWhite)
            {
                whitePlayerList.add(p);
            }
            else
            {
                blackPlayerList.add(p);
            }
            invalidate();
            currentWhite = !currentWhite;
            currentBlack = !currentBlack;
        }
        return true;
    }

    @Override
    protected Parcelable onSaveInstanceState()
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE, super.onSaveInstanceState());
        bundle.putBoolean(INSTANCE_GAME_OVER, isGameOver);
        bundle.putBoolean(INSTANCE_CURRENT_BLACK, currentBlack);
        bundle.putBoolean(INSTANCE_CURRENT_WHITE, currentWhite);
        bundle.putParcelableArrayList(INSTANCE_WHITE_ARRAY, whitePlayerList);
        bundle.putParcelableArrayList(INSTANCE_BLACK_ARRAY, blackPlayerList);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state)
    {
        if(state instanceof Bundle)
        {
            Bundle bundle = (Bundle)state;
            isGameOver = bundle.getBoolean(INSTANCE_GAME_OVER);
            whitePlayerList = bundle.getParcelableArrayList(INSTANCE_WHITE_ARRAY);
            blackPlayerList = bundle.getParcelableArrayList(INSTANCE_BLACK_ARRAY);
            currentBlack = bundle.getBoolean(INSTANCE_CURRENT_BLACK);
            currentWhite = bundle.getBoolean(INSTANCE_CURRENT_WHITE);

            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public void restart()
    {
        whitePlayerList.clear();
        blackPlayerList.clear();
        isGameOver = false;
        isWhitePlayerWinner = false;
        isBlackPlayerWinner = false;
        invalidate();
    }
}
