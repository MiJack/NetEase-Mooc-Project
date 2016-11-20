package com.mijack.course.bean;

import org.hibernate.validator.constraints.Length;

/**
 * 商品的JavaBean
 *
 * @author Mr.Yuan
 * @since 2016/11/7.
 */
public class Product {

    private int id;
    @Length(min = 2, max = 80)
    private String title;
    @Length(min = 2, max = 140)
    private String summary;
    private String image;
    @Length(min = 2, max = 1000)
    private String detail;
    private float price;
    private boolean isBuy;
    private boolean isSell;
    private int trxCount;
    private float buyPrice;
    private long buyTime;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public boolean getIsBuy() {
        return isBuy;
    }

    public boolean getIsSell() {
        return isSell;
    }

    public void setSell(boolean sell) {
        isSell = sell;
    }

    public int getTrxCount() {
        return trxCount;
    }

    public void setTrxCount(int trxCount) {
        this.trxCount = trxCount;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public long getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(long buyTime) {
        this.buyTime = buyTime;
    }
}