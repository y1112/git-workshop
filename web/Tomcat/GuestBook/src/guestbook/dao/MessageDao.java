package guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	
	
	
	public List<Message> selectMessageList(Connection conn,int startRow, int endRow) throws SQLException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Message> list=null;

		try {
			String sql="select message_id, guest_name,password,message " + 
					"from (select rownum rnum, message_id, guest_name,password, message from " + 
					"(select * from guestbook_message order by message_id desc) " + 
					"where rownum<=?) " + 
					"where rnum>=?";
				
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,endRow);
			pstmt.setInt(2,startRow);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Message message=new Message(
						rs.getString("guest_name"),
						rs.getString("password"),
						rs.getString("message"),
						rs.getInt("message_id"));
				list.add(message);
			}
		
		}finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
		}
		
		return list;
	}











	
	
	
	








}