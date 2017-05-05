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
    SparseArray<PointF> mPoint;
    BTService btService = BTService.getInstance();
    int[] colors = {Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GREEN, Color.GRAY};
    boolean isTouch = false;
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
        int poinId = event.getPointerId(pointIndex);
        int maskAction = event.getActionMasked();
        switch (maskAction) {
            //  Khi chạm tay vào màn hình
            case MotionEvent.ACTION_DOWN:
                // Khi chạm vào một điểm trên màn hình
            case MotionEvent.ACTION_POINTER_DOWN: {
                // Khai báo hai hàm Float và gán tọa độ X và Y của điểm chạm cho nó
                float toadoX = event.getX(pointIndex);
                float toadoY = event.getY(pointIndex);
                // Khai báo mảng PontF đã tạo ở trên
                PointF fpoint = new PointF();
                // gán tọa độ X - Y của điểm chạm cho mảng
                fpoint.x = toadoX;
                fpoint.y = toadoY;
                mPoint.put(poinId, fpoint);
            }
            //  ACTION_MOVE là sự kiện khi ta di chuyển ngón tay trên màn hình
            // Hàm này sẽ cập nhập lại vị trí của điểm chạm khi di chuyển
            case MotionEvent.ACTION_MOVE: {
                // tạo một hàm for để lấy ra tất cả các điểm chạm trên màn hình
                // size là tổng số điểm chạm trên màn hình
                    // Cập nhập lại tọa độ theo ID của từng điểm chạm
                PointF f = mPoint.get(event.getPointerId(0));
                if (f != null) {
                    f.x = event.getX(0);
                    f.y = event.getY(0);
                    sendCoor(f);
                }
                // Khi cập nhập tọa độ xong thì dừng lại
                break;
            }
            // ACTION_UP: Khi nhấc ngón tay lên
            case MotionEvent.ACTION_UP:
                //mPoint.removeAt(poinId);
                // ACTION_POINTER_UP: Khi ngón tay không còn chạm vào điểm chạm nữa.
            case MotionEvent.ACTION_POINTER_UP:
                //ACTION_CANCEL: Chấm dứt hoàn toàn việc chạm vào màn hình
                mPoint.removeAt(poinId);
            case MotionEvent.ACTION_CANCEL:
                // Gỡ bỏ hoàn toàn các điểm với điều kiện ở trên theo ID từng điểm chạm
                //mPoint.removeAt(poinId);
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
}
