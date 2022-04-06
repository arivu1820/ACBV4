package com.futureinspirator.acbaradise;

import java.util.List;

public class ParentItem
{
    String totalitem,yousaved,charge,grandtotal,discount,orderid,payment,date,number,address;
    private List<ChildItem> ChildItemList;

    public ParentItem(String totalitem, String yousaved, String charge, String grandtotal, String discount, String orderid, String payment, String date, String number, String address, List<ChildItem> childItemList) {
        this.totalitem = totalitem;
        this.yousaved = yousaved;
        this.charge = charge;
        this.grandtotal = grandtotal;
        this.discount = discount;
        this.orderid = orderid;
        this.payment = payment;
        this.date = date;
        this.number = number;
        this.address = address;
        this.ChildItemList = childItemList;
    }

    public String getTotalitem() {
        return totalitem;
    }

    public void setTotalitem(String totalitem) {
        this.totalitem = totalitem;
    }

    public String getYousaved() {
        return yousaved;
    }

    public void setYousaved(String yousaved) {
        this.yousaved = yousaved;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(String grandtotal) {
        this.grandtotal = grandtotal;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ChildItem> getChildItemList() {
        return ChildItemList;
    }

    public void setChildItemList(List<ChildItem> childItemList) {
        ChildItemList = childItemList;
    }

    @Override
    public String toString() {
        return "parent_orderhistory{" +
                "totalitem='" + totalitem + '\'' +
                ", yousaved='" + yousaved + '\'' +
                ", charge='" + charge + '\'' +
                ", grandtotal='" + grandtotal + '\'' +
                ", discount='" + discount + '\'' +
                ", orderid='" + orderid + '\'' +
                ", payment='" + payment + '\'' +
                ", date='" + date + '\'' +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

