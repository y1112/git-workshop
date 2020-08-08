package com.spring.transactionReview;

public class BuyVO {
	private String userId;
	private String amount;
	
	public BuyVO(String userId, String amount) {
		super();
		this.userId = userId;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "BuyVO [userId=" + userId + ", amount=" + amount + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	

}
