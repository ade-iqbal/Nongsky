package com.nongskuy.nongskuy.data;

import com.google.gson.annotations.SerializedName;

public class PencarianData {

	@SerializedName("id")
	private int id;

	@SerializedName("gambar")
	private String gambar;

	@SerializedName("nama_toko")
	private String namaToko;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("tipe")
	private String tipe;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("longitude")
	private String longitude;

	@SerializedName("jarak")
	private String jarak;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGambar() {
		return gambar;
	}

	public void setGambar(String gambar) {
		this.gambar = gambar;
	}

	public String getNamaToko() {
		return namaToko;
	}

	public void setNamaToko(String namaToko) {
		this.namaToko = namaToko;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getTipe() {
		return tipe;
	}

	public void setTipe(String tipe) {
		this.tipe = tipe;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getJarak() {
		return jarak;
	}

	public void setJarak(String jarak) {
		this.jarak = jarak;
	}
}