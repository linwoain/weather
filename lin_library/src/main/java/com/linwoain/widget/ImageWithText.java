package com.linwoain.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.linwoain.library.R;


public class ImageWithText extends LinearLayout {
    TextView tv;
    ImageView iv;

    public ImageWithText(Context context) {
        super(context);
        init(null, 0);
    }

    public ImageWithText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public void init(AttributeSet attrs, int style) {
        if (isInEditMode()) {
            return;
        }
        View.inflate(getContext(), R.layout.sample_image_with_text, this);

        tv = (TextView) findViewById(R.id.tv);
        iv = (ImageView) findViewById(R.id.iv);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ImageWithText);
            this.setText(a.getText(R.styleable.ImageWithText_text).toString());
            this.setImageViewRes(a.getResourceId(R.styleable.ImageWithText_src, R.drawable.ic_launcher));
        }
    }

    /**
     * 设置文字
     *
     * @param text
     */
    public void setText(String text) {
        tv.setText(text);
    }

    /**
     * 设置imageview的图片
     *
     * @param resId
     */
    public void setImageViewRes(int resId) {
        iv.setImageResource(resId);
    }

    /**
     * 设置imageview的图片
     *
     * @param bitmap
     */
    public void setImageBitmap(Bitmap bitmap) {
        iv.setImageBitmap(bitmap);

    }

    /**
     * 设置字体颜色
     *
     * @param color color值
     */
    public void settextColor(int color) {
        tv.setTextColor(color);
    }

}
