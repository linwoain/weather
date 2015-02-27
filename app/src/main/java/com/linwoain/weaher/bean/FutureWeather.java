package com.linwoain.weaher.bean;

import com.linwoain.bean.BaseBean;

public class FutureWeather extends BaseBean {

	private static final long serialVersionUID = -4422945756788075072L;

	private String temperature;

	private String weather;

	private String wind;
	
	private String week;
	
	private String date;

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
