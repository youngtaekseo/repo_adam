package com.ucl.infra.checkout;

import com.ucl.infra.product.ProductDto;

public class CheckOutDto extends ProductDto {
	private Integer ckoCount;
	private Integer ckoPrice;
	
	public Integer getCkoCount() {
		return ckoCount;
	}
	public void setCkoCount(Integer ckoCount) {
		this.ckoCount = ckoCount;
	}
	public Integer getCkoPrice() {
		return ckoPrice;
	}
	public void setCkoPrice(Integer ckoPrice) {
		this.ckoPrice = ckoPrice;
	}
}
