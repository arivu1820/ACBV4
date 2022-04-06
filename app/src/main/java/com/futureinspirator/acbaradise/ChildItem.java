package com.futureinspirator.acbaradise;

public class ChildItem
{
    String title,quantity,price,totalprice;

    public ChildItem(String title, String quantity, String price, String totalprice) {
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.totalprice = totalprice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuantity() {
        return quantity+"  X  ";
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    @Override
    public String toString() {
        return "parent_orderhistorylist{" +
                "title='" + title + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                ", totalprice='" + totalprice + '\'' +
                '}';
    }
}

