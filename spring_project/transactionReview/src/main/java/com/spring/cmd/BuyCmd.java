package com.spring.cmd;

import org.springframework.stereotype.Service;

import com.spring.transactionReview.BuyVO;

@Service
public interface BuyCmd {
	public void execute(BuyVO buyVO);

}
