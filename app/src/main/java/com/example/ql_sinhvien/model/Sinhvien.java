package com.example.ql_sinhvien.model;

public class Sinhvien {
    private  String id;
    private  String Mssv;
    private  String Hoten;
    private  String Email;
    private  String Sdt;

    @Override
    public String toString() {
        return "Sinhvien{" +
                "id='" + id + '\'' +
                ", Mssv='" + Mssv + '\'' +
                ", Hoten='" + Hoten + '\'' +
                ", Email='" + Email + '\'' +
                ", Sdt='" + Sdt + '\'' +
                '}';
    }

    public Sinhvien() {
    }

    public Sinhvien(String id, String mssv, String hoten, String email, String sdt) {
        this.id = id;
        Mssv = mssv;
        Hoten = hoten;
        Email = email;
        Sdt = sdt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMssv() {
        return Mssv;
    }

    public void setMssv(String mssv) {
        Mssv = mssv;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }
}
