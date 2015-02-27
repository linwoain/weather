package com.linwoain.weaher.bean;


import com.linwoain.bean.BaseBean;

public class Today extends BaseBean {


	private static final long serialVersionUID = 4670899096536597061L;

	
	private String city;
	
	private String date_y;
	
	private String week;
	
	private String temperature;
	
	private String weather;
	
	
	private String wind;
	/**
	 * *穿衣指数
	 */
	private String dressing_index;
	/**
	 * 穿衣建议
	 */
	private String dressing_advice;
	/**
	 * 紫外线强度
	 */
	private String uv_index;
	
	/**
	 * 旅游指数
	 */
	private String travel_index;
	
	/**
	 * 洗车指数
	 */
	private String wash_index;
	/**
	 * 舒适度指数
	 */
	private String 	confort_index;
	/**
	 * 晨练指数
	 */
	private String 	exercise_index;
	/**
	 * 干燥指数
	 */
	private String 	drying_index;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDate_y() {
		return date_y;
	}
	public void setDate_y(String date_y) {
		this.date_y = date_y;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
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
	public String getDressing_index() {
		return dressing_index;
	}
	public void setDressing_index(String dressing_index) {
		this.dressing_index = dressing_index;
	}
	public String getDressing_advice() {
		return dressing_advice;
	}
	public void setDressing_advice(String dressing_advice) {
		this.dressing_advice = dressing_advice;
	}
	public String getUv_index() {
		return uv_index;
	}
	public void setUv_index(String uv_index) {
		this.uv_index = uv_index;
	}
	public String getTravel_index() {
		return travel_index;
	}
	public void setTravel_index(String travel_index) {
		this.travel_index = travel_index;
	}
	public String getWash_index() {
		return wash_index;
	}
	public void setWash_index(String wash_index) {
		this.wash_index = wash_index;
	}
	public String getConfort_index() {
		return confort_index;
	}
	public void setConfort_index(String confort_index) {
		this.confort_index = confort_index;
	}
	public String getExercise_index() {
		return exercise_index;
	}
	public void setExercise_index(String exercise_index) {
		this.exercise_index = exercise_index;
	}
	public String getDrying_index() {
		return drying_index;
	}
	public void setDrying_index(String drying_index) {
		this.drying_index = drying_index;
	}
	
	
}
