/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/11/4 9:09
 */
package com.linwoain.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.linwoain.library.R;

/**
 * @author linwoain
 * @version 2014/11/4 9:09
 */
public class LinSearchView extends LinearLayout {
    private ImageButton ib;
    private EditText et;

    public LinSearchView(Context context) {
        super(context);
        init();
    }


    public LinSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        View.inflate(getContext(), R.layout.search_view, this);
        ib = (ImageButton) findViewById(R.id.ib_search);
        et = (EditText) findViewById(R.id.et_search);
        ib.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.click(ib,et);
                }
            }
        });
        
        et.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (focusListener != null) {
                        focusListener.onFocus(et);
                    }
                }
            }
        });
    }

    private OnSearchBoxFocusListener focusListener;
    

    private OnSearchButtonClickListener listener;

    public void setHint(String text) {
        et.setHint(text);
    }

    public void setText(String text) {
        et.setText(text);
    }

    public String getText() {

        return et.getText().toString();
    }

    /**
     * 点击了搜索按钮后执行的listener
     * @param listener
     */
    public void setOnSearchButtonClickListener(OnSearchButtonClickListener listener) {
        this.listener = listener;
    }

    /**
     * 获取焦点时
     * @param focusListener
     */
    public void setFocusListener(OnSearchBoxFocusListener focusListener) {
        this.focusListener = focusListener;
    }

    public interface OnSearchButtonClickListener {
        /**
         * 当点击了搜索时执行
         *
         * @param ib
         * @param et
         */
        public void click(ImageButton ib, EditText et);

    }

    public interface OnSearchBoxFocusListener {
        
        /**
         * 当搜索框获取焦点时执行
         * @param view 获取焦点的view
         */
        public void onFocus(View view);
    }
}
