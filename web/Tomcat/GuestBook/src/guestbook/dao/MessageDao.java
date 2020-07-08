package guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import guestbook.model.Message;

public class MessageDao {
	private MessageDao() {
		
	}
	
	static private MessageDao dao=new MessageDao();//싱글톤 처리
	
	public static MessageDao getInstance() {
		return dao;
	}
	
	public int inserTMessage(Connection conn,Message message) throws SQLException{
		int resultCnt=0;
		PreparedStatement pstmt=null;
		try {
			String sql="insert into guestbook_message values(message_id_seq.nextVal,?,?,?)";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,message.getUname());
			pstmt.setString(2,message.getPw());
			pstmt.setString(3,message.getMessage());
			
			pstmt.executeUpdate();
		}finally {
		if(pstmt!=null) {
			pstmt.close();
		}
		}
		return resultCnt;
	}
}
