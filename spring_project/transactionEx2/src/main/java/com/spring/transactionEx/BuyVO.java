package com.spring.transactionEx;

public class BuyVO {
	private String UserId;
	private String amount;
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public BuyVO(String userId, String amount) {
		super();
		UserId = userId;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "BuyVO [UserId=" + UserId + ", amount=" + amount + "]";
	}
	public BuyVO() {
	}
	
}
