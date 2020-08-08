package com.spring.transactionReview;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TicketController {
	
	@RequestMapping("/buy")
	public String buy() {
		return "buy";
	}
	
	@RequestMapping("/buyOk")
	public String buyOk(BuyVO buyVo, Model model) {
		return "buyResult";
	}
	
}
