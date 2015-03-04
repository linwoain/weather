package com.linwoain.util;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;

import com.linwoain.library.LApplication;
import com.linwoain.library.R;

/**
 * Created by linwoain(linwoain@outlook.com) Time 2014/9/29 16:03
 * 知我者谓我心忧，不知我者谓我何求！
 */
public class PopupUtil {

	/**
	 * 可以实现，其中的context参数由LApplication.getContext()获取到
	 *
	 * @param view
	 *            显示在哪个view的下方
	 * @param list
	 *            候选的字符串列表
	 * @param callback
	 *            选中的回调
	 */
	public static void showPopup(View view, List<String> list, final PositionClick callback) {
		Context context = LApplication.getContext();
		showPopup(context, view, list, callback);
	}

	/**
	 * 
	 * 显示一个待选项的PopupWindow
	 * 
	 * @author linwoain
	 * @version 2014年11月15日 上午9:26:35
	 * @param context
	 *            上下文
	 * @param view
	 * @param list
	 * @param callback
	 */
	@Deprecated
	public static void showPopup(Context context, View view, List<String> list, final PositionClick callback) {
		LLogUtils.i(list.size());
		final PopupWindow popup = new PopupWindow();
		ListView lv = new ListView(context);
		lv.setPadding(5, 5, 5, 5);
		popup.setFocusable(true);
		popup.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		popup.setContentView(lv);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				popup.dismiss();
				if (callback != null) {
					callback.onItemClick(position);
				}
			}
		});
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.select_items, list);
		lv.setAdapter(adapter);
		lv.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT));
		popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
		popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
		popup.showAsDropDown(view);
	}

	/**
	 * 显示一个动态Spinner，类似的选项框,Spinner控件必须是一个已存在的控件
	 * 
	 * @author linwoain
	 * @version 2014年11月15日 上午9:28:37
	 * @param spinner
	 * @param list
	 * @param callback
	 */
	public static void showSpainner(Spinner spinner, List<String> list, final PositionClick callback) {
		if (null == spinner || list == null) {
			throw new RuntimeException("spinner或list不能为null");
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(LApplication.getContext(), android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (callback != null) {
					callback.onItemClick(position);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

	}

	/**
	 * 当PopupWindow中条目被点击时的回调
	 */
	public interface PositionClick {
		/**
		 * 当某个条目被点击时的回调
		 *
		 * @param position
		 */
		public void onItemClick(int position);

	}
}
