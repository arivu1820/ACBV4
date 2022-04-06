package com.futureinspirator.acbaradise;

public class parentbooking
{
    String title,quantity,price,totalprice;

    public parentbooking(String title, String quantity, String totalprice) {
        this.title = title;
        this.quantity = quantity;
        this.totalprice = totalprice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }


}

