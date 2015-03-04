package com.linwoain.widget;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.linwoain.library.R;

/**
 * 自定义的输入用户名控件
 *
 * @author: linwoain
 * @version: 2014年8月30日 上午11:03:08
 */
public class InputUser extends LinearLayout {

    private View rootView;
    private TextView mTextView;
    private EditText mEditText;

    private void initView(Context context) {
        rootView = View.inflate(context, R.layout.l_inputbox, this);
        mTextView = (TextView) rootView.findViewById(R.id.tv_user);
        mEditText = (EditText) rootView.findViewById(R.id.et_user);


    }

    public InputUser(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public InputUser(Context context) {
        super(context);
        initView(context);
    }

    /**
     * 设置输入为密码型
     */
    public void setIsPassword() {
        mEditText.setInputType((InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
    }

    /**
     * 设置tv上显示的内容
     *
     * @author: linwoain
     * @version: 2014年8月30日 上午11:25:02
     */
    public void setTvUser(String text) {
        mTextView.setText(text);
    }

    /**
     * 获取TextView，显示“用户名”的控件
     *
     * @author: linwoain
     * @version: 2014年8月30日 上午11:26:38
     */
    public TextView getTvUser() {
        return mTextView;
    }

    public void setText(String text) {
        mEditText.setText(text);
    }

    /**
     * 获取输入框的对象
     *
     * @author: linwoain
     * @version: 2014年8月30日 上午11:27:15
     */
    public EditText getEditText() {
        return mEditText;
    }


    public String getText() {
        return mEditText.getText().toString();
    }

    public void setHint(String text) {
        mEditText.setHint(text);
    }
}
