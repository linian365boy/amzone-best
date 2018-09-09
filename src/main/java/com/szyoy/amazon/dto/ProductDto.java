package com.szyoy.amazon.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
    private Integer reviewsStart;
    private Integer reviewsEnd;
    private String stars;
    private Double priceStart;
    private Double priceEnd;
    private String sortBy = "id";
    private String order = "desc";

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

    public Integer getReviewsStart() {
        return reviewsStart;
    }

    public void setReviewsStart(Integer reviewsStart) {
        this.reviewsStart = reviewsStart;
    }

    public Integer getReviewsEnd() {
        return reviewsEnd;
    }

    public void setReviewsEnd(Integer reviewsEnd) {
        this.reviewsEnd = reviewsEnd;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public Double getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(Double priceStart) {
        this.priceStart = priceStart;
    }

    public Double getPriceEnd() {
        return priceEnd;
    }

    public void setPriceEnd(Double priceEnd) {
        this.priceEnd = priceEnd;
    }
    
    public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
