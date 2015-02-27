package com.linwoain.weaher.bean;


import java.util.List;

import com.linwoain.bean.BaseBean;

public class Result extends BaseBean {

	private static final long serialVersionUID = 120239239153063559L;

	private SK sk;
	
	private Today today;
	
	private List<FutureWeather> future;

	public SK getSk() {
		return sk;
	}

	public void setSk(SK sk) {
		this.sk = sk;
	}

	public Today getToday() {
		return today;
	}

	public void setToday(Today today) {
		this.today = today;
	}


    public List<FutureWeather> getFuture() {
        return future;
    }

    public void setFuture(List<FutureWeather> future) {
        this.future = future;
    }
}
