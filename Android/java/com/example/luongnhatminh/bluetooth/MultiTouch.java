package com.example.luongnhatminh.bluetooth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;


public class MultiTouch extends View{

    Paint mpaint;
    Paint mText;
    Paint ToadoText;
    boolean moving = false;
    private float initX =0;
    private float initY =0;
    private float disX =0;
    private float disY =0;
    static final int NONE = 0;
    float startY, startY1, startX, startX1, startDistance;
    float stopY, stopY1;
    int TRESHOLD = 10;
    int TRESHOLDZ = 20;
    int tap_count = 0;
    int mode = NONE;
    boolean firstTouch = false;
    SparseArray<PointF> mPoint;
    BTService btService = BTService.getInstance();
    int[] colors = {Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GREEN, Color.GRAY};
    public MultiTouch(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }



    public void initView() {
        //Độ sắc nét
        mpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //Gán màu
        mpaint.setColor(Color.MAGENTA);
        //Đổ đầy hình tròn
        mpaint.setStyle(Paint.Style.FILL_AND_STROKE);

        //Dòng text thông báo số điểm chạm
        mText = new Paint();
        //Cỡ chữ
        mText.setTextSize(50);
        ToadoText = new Paint();
        ToadoText.setTextSize(40);
        mPoint = new SparseArray<PointF>();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointIndex = event.getActionIndex();
        int pointId = event.getPointerId(pointIndex);
        int maskAction = event.getActionMasked();
        int count = event.getPointerCount();
        switch (maskAction) {
            //  Khi chạm tay vào màn hình
            case MotionEvent.ACTION_DOWN:
                // Khi chạm vào một điểm trên màn hình
                initX = event.getX();
                initY = event.getY();
                moving = false;
                firstTouch = true;
                tap_count++;
            case MotionEvent.ACTION_POINTER_DOWN: {
                // Khai báo hai hàm Float và gán tọa độ X và Y của điểm chạm cho nó
                float toadoX = event.getX(pointIndex);
                float toadoY = event.getY(pointIndex);
                tap_count++;
                // Khai báo mảng PontF đã tạo ở trên
                PointF fpoint = new PointF();
                // gán tọa độ X - Y của điểm chạm cho mảng
                fpoint.x = toadoX;
                fpoint.y = toadoY;
                mPoint.put(pointId, fpoint);
                if (count == 2) {

                    // You can also use event.getY(1) or the average of the two
                    startY = event.getY(0);
                    startX = event.getX(0);
                    startY1 = event.getY(1);
                    startX1 = event.getX(1);
                    startDistance = distance(event, 0, 1);
                }
            }
            //  ACTION_MOVE là sự kiện khi ta di chuyển ngón tay trên màn hình
            // Hàm này sẽ cập nhập lại vị trí của điểm chạm khi di chuyển
            case MotionEvent.ACTION_MOVE: {
                // tạo một hàm for để lấy ra tất cả các điểm chạm trên màn hình
                // size là tổng số điểm chạm trên màn hình
                // Cập nhập lại tọa độ theo ID của từng điểm chạm
                if (count == 1 && firstTouch) {
                    disX = event.getX() - initX;
                    disY = event.getY() - initY;
                    initX = event.getX();
                    initY = event.getY();
                    PointF f = mPoint.get(event.getPointerId(0));
                    if ((f != null) && (disX != 0 || disY != 0)) {
                        f.x = disX;
                        f.y = disY;
                        sendCoor(f);
                        moving = true;
                    }
                } else if (count == 2) {
                    stopY = event.getY(0);
                    stopY1 = event.getY(1);
                    final float distanceCurrent = distance(event, 0, 1);
                    final float diffX = startX - event.getX(0);
                    final float diffY = startY - event.getY(0);
                    final float diffX1 = startX1 - event.getX(1);
                    final float diffY1 = startY1 - event.getY(1);
                    if ((Math.abs(startY - stopY) > TRESHOLD) && (Math.abs(startY1 - stopY1) > TRESHOLD)
                            && ((startY - stopY) * (startY1 - stopY1) > 0)) {
                        PointF f = mPoint.get(event.getPointerId(0));
                        f.x = 0;
                        f.y = stopY - startY;
                        btService.SendMsg("SC" + ":" + f.y);
                    } else if (Math.abs(distanceCurrent - startDistance) >
                            TRESHOLDZ
                            && (diffY * diffY1) <= 0
                            && (diffX * diffX1) <= 0) {
                        float diff = (float) Math.sqrt(diffX * diffX + diffY * diffY);
                        if ((distanceCurrent - startDistance) > 0)
                            btService.SendMsg("ZO:" + diff);
                        else
                            btService.SendMsg("ZI:" + diff);
                    }
                    startY = stopY;
                    startY1 = stopY1;
                    startX = event.getX(0);
                    startX1 = event.getX(1);
                }
                // Khi cập nhập tọa độ xong thì dừng lại
                break;
            }
            // ACTION_UP: Khi nhấc ngón tay lên
            case MotionEvent.ACTION_UP:
                //mPoint.removeAt(pointId);
                // ACTION_POINTER_UP: Khi ngón tay không còn chạm vào điểm chạm nữa.
                if (!moving && firstTouch)
                {
                    btService.SendMsg("LC");
                }
                tap_count--;
                if (tap_count == 2)
                {
                    //btService.SendMsg("RC");
                }
                tap_count = 0;
            case MotionEvent.ACTION_POINTER_UP:
                moving = false;
                firstTouch = false;
                tap_count++;
                mPoint.removeAt(pointId);
            case MotionEvent.ACTION_CANCEL:
                // Gỡ bỏ hoàn toàn các điểm với điều kiện ở trên theo ID từng điểm chạm
                //mPoint.removeAt(pointId);
        }
        invalidate();
        return true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Vẽ ra đường tròn đánh dấu điểm chạm
        // Hàm for lấy ra tất cả các điểm chạm trên màn hình và vẽ lên các điểm đó
        for (int size = mPoint.size(), i = 0; i < size; i++) {
            PointF pointer = mPoint.valueAt(i);
            // Đổ màu khác nhau cho mỗi điểm chạm
            mpaint.setColor(colors[i % 5]);
            // Kích cỡ điểm đánh dấu và vị trí lấy theo tọa độ
            canvas.drawCircle(pointer.x, pointer.y, 40, mpaint);
            // Đưa ra thông báo tọa độ cho từng điểm chạm
            int a = i + 1;
            canvas.drawText("Tọa độ điểm " + a + ": X: " + pointer.x + " - " + "Y: " + pointer.y, 0, a * 100, ToadoText);
        }
        //Đưa ra thông báo tổng số điểm chạm
        canvas.drawText("Đang có " + mPoint.size() + " điểm chạm", 0, 50, mText);
    }

    private void sendCoor(PointF f){
        if(btService.isConnected())
        {
            btService.SendMsg("[" + f.x + "," + f.y + "]");
        }
        else {
            btService.connectServer();
            btService.SendMsg("[" + f.x + "," + f.y + "]");
        }
    }
    public float distance(MotionEvent event, int first, int second) {
        if (event.getPointerCount() >= 2) {
            final float x = event.getX(first) - event.getX(second);
            final float y = event.getY(first) - event.getY(second);

            return (float) Math.sqrt(x * x + y * y);
        } else {
            return 0;
        }
    }
}