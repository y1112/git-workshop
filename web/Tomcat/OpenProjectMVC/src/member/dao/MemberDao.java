package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

import member.model.Member;

public class MemberDao {

	private MemberDao() {
	}

	private static MemberDao dao = new MemberDao();

	public static MemberDao getInstance() {
		return dao;
	}
	
	public int selectById(Connection conn, String id) throws SQLException {
		int resultCnt=0;
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
		String sql="select count(*) from member where uid=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				resultCnt=rs.getInt(1);
			}
			
			if(rs!=null)
				rs.close();
			
			if(pstmt!=null)
				pstmt.close();
			
		} finally {}
		
		return resultCnt;
	}
	
	
	public int insertMember(Connection conn, Member member) throws SQLException {
		int resultCnt=0;
		
		PreparedStatement pstmt=null;
		String sql="INSERT INTO member(uid,upw,uname,uphoto) VALUES(?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, member.getUid());
			pstmt.setString(2, member.getUpw());
			pstmt.setString(3, member.getUname());
			pstmt.setString(4, member.getUphoto());
			
			resultCnt=pstmt.executeUpdate();
		}finally {
			if(pstmt!=null)
				pstmt.close();
		}
		
		return resultCnt;
	}

}
