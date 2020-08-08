package com.spring.transactionEx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class BuyDAO {
	JdbcTemplate template;

	PlatformTransactionManager transactionManager;

	@Autowired
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public void buyTicket(final BuyVO buyVO) {
		System.out.println("buyTicket() 호출");
		System.out.println("티켓 구매 고객 아이디: " + buyVO.getUserId());
		System.out.println("티켓 구매 수량: " + buyVO.getAmount());

		TransactionDefinition def=new DefaultTransactionDefinition();
		TransactionStatus status=transactionManager.getTransaction(def);
		try {
			template.update(new PreparedStatementCreator() { //둘 중 어느 것을 사용해도 상관 없음
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					String sql = "insert into card(username,amount) values(?,?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, buyVO.getUserId());
					ps.setInt(2, Integer.parseInt(buyVO.getAmount()));
	
					return ps;
				}
		
			});

			String sql = "insert into ticket(username,cnt) values(?,?)";

			template.update(sql, new PreparedStatementSetter() { //둘 중 어느 것을 사용해도 상관 없음
	
				@Override
				public void setValues(PreparedStatement preparedStatement) throws SQLException {
					preparedStatement.setString(1, buyVO.getUserId());
					preparedStatement.setInt(2, Integer.parseInt(buyVO.getAmount()));
				}
			});
			
			transactionManager.commit(status);
		}catch(Exception e) {
			e.printStackTrace();
			
			transactionManager.rollback(status);
		}
	}// buyTicket()
}
