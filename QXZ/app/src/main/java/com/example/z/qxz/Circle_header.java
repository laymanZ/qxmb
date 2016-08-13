package com.example.z.qxz;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

//裁剪头像的类
public class Circle_header
{

    private Bitmap bitmap;
    private static Circle_header circle_header;
    int width,height,left,top,right,bottom;
    float roundPx,scaleSize;
    /*public Circle_header(Bitmap bitmap,float scaleSize)
    {
        this.bitmap = bitmap;
        this.scaleSize = scaleSize;
    }*/

    public static Circle_header getInstance()
    {
        if(circle_header == null)
            circle_header = new Circle_header();
        return circle_header;
    }



//   圆形头像
    public Bitmap Circle_head(Bitmap bitmap,float scaleSize)
    {
        //获取宽度和高度
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
//        Log.v("高度", String.valueOf(displayMetrics.heightPixels));
//        Log.v("宽度",String.valueOf(displayMetrics.widthPixels));

        Matrix matrix = new Matrix();
        matrix.setScale(scaleSize,scaleSize);
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        width = bitmap1.getWidth();
        height = bitmap1.getHeight();
        left = 0;
        top = 0;
        right = width;
        bottom = height;
        roundPx = height;
        if (width > height)
        {
            left = (width - height)/2;
            top = 0;
            right = left + height;
            bottom = height;
        }
        else if (height > width)
        {
            left = 0;
            top = (height - width)/2;
            right = width;
            bottom = top + width;
            roundPx = width;
        }
        //Log.i("hahalaaldajfsl", "ps:" + left + ", " + top + ", " + right + ", " + bottom);
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        int color = 0xff424242;
        Paint paint = new Paint();

        Rect rect = new Rect(left, top, right, bottom);
        RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//      http://blog.csdn.net/tianjian4592/article/details/44783283  setXfermode的博客
        /*PorterDuff.Mode.SRC_IN：取两层绘制交集。显示上层。就是如果上面两张图片相叠，
        那么取这两张图片的交集而且显示的是上层那种图片
        PorterDuff.Mode.DST_IN：  取两层绘制交集。显示下层。*/
        canvas.drawBitmap(bitmap1, rect, rect, paint);
        /*Matrix matrix = new Matrix();
        matrix.setScale(0.5f,0.5f);
        canvas.drawBitmap(output,matrix,null);*/
        return output;



       /* // 以下有两种方法画圆,drawRounRect和drawCircle
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);// 画圆角矩形，第一个参数为图形显示区域，第二个参数和第三个参数分别是水平圆角半径和垂直圆角半径。
        canvas.drawCircle(roundPx, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));// 设置两张图片相交时的模式,参考http://trylovecatch.iteye.com/blog/1189452
        canvas.drawBitmap(bitmap, src, dst, paint); //以Mode.SRC_IN模式合并bitmap和已经draw了的Circle*/

    }
}
