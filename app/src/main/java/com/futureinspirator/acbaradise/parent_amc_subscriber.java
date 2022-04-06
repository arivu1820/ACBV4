package com.futureinspirator.acbaradise;

public class parent_amc_subscriber
{

    String key,title,firstservice,secondservice,thirdservice,fourthservice;

    public parent_amc_subscriber(String key, String title, String firstservice, String secondservice, String thirdservice, String fourthservice) {
        this.key = key;
        this.title = title;
        this.firstservice = firstservice;
        this.secondservice = secondservice;
        this.thirdservice = thirdservice;
        this.fourthservice = fourthservice;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstservice() {
        return firstservice;
    }

    public void setFirstservice(String firstservice) {
        this.firstservice = firstservice;
    }

    public String getSecondservice() {
        return secondservice;
    }

    public void setSecondservice(String secondservice) {
        this.secondservice = secondservice;
    }

    public String getThirdservice() {
        return thirdservice;
    }

    public void setThirdservice(String thirdservice) {
        this.thirdservice = thirdservice;
    }

    public String getFourthservice() {
        return fourthservice;
    }

    public void setFourthservice(String fourthservice) {
        this.fourthservice = fourthservice;
    }


    @Override
    public String toString() {
        return "parent_amc_subscriber{" +
                "key='" + key + '\'' +
                ", title='" + title + '\'' +
                ", firstservice='" + firstservice + '\'' +
                ", secondservice='" + secondservice + '\'' +
                ", thirdservice='" + thirdservice + '\'' +
                ", fourthservice='" + fourthservice + '\'' +
                '}';
    }
}
