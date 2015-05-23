package com.iüce.entity;

public class Insulin {

	private int id;
	private double kansekeri;
	private double insulinmiktari;
	private String date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getKansekeri() {
		return kansekeri;
	}

	public void setKansekeri(double d) {
		this.kansekeri = d;
	}

	public double getInsulinmiktari() {
		return insulinmiktari;
	}

	public void setInsulinmiktari(double insulinmiktari) {
		this.insulinmiktari = insulinmiktari;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
