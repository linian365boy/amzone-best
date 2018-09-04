package com.szyoy.amazon.dto;

/**
 * @author niange
 * @ClassName: ProductDto
 * @desp:
 * @date: 2018/8/20 下午10:57
 * @since JDK 1.7
 */
public class ProductDto {
    private String productName;
    private String category;
    private int reviewsStart;
    private int reviewsEnd;
    private String stars;
    private double priceStart;
    private double priceEnd;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getReviewsStart() {
        return reviewsStart;
    }

    public void setReviewsStart(int reviewsStart) {
        this.reviewsStart = reviewsStart;
    }

    public int getReviewsEnd() {
        return reviewsEnd;
    }

    public void setReviewsEnd(int reviewsEnd) {
        this.reviewsEnd = reviewsEnd;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public double getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(double priceStart) {
        this.priceStart = priceStart;
    }

    public double getPriceEnd() {
        return priceEnd;
    }

    public void setPriceEnd(double priceEnd) {
        this.priceEnd = priceEnd;
    }
}
