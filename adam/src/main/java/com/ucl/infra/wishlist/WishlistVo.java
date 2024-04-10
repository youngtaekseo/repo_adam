package com.ucl.infra.wishlist;

import com.ucl.common.base.BaseVo;

public class WishlistVo extends BaseVo {
	private String  shSeq;
	private String  shMbrSeq;
	
//=============================================================================

	public String getShSeq() {
		return shSeq;
	}
	public void setShSeq(String shSeq) {
		this.shSeq = shSeq;
	}
	public String getShMbrSeq() {
		return shMbrSeq;
	}
	public void setShMbrSeq(String shMbrSeq) {
		this.shMbrSeq = shMbrSeq;
	}	
}
