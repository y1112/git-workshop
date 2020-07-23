package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;
import service.Service;

public class MemberEditInfoFormServiceImpl implements Service {

	MemberDao dao;
	
	
	
	//userId userName photo
	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		
		Connection conn;
		Member member = null;
		int idx = Integer.parseInt(request.getParameter("idx"));
		System.out.println("@@@idx확인:"+idx);
		try {
			conn = ConnectionProvider.getConnection();
			dao = MemberDao.getInstance();
			
			member=dao.selectByIdx(conn, idx);
			System.out.println("수정할member:"+member);
			
			
			
			member = dao.selectByIdx(conn, idx);
		} catch (NumberFormatException e) {
			System.out.println("숫자 변경이 불가능한 문자열로 입력!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("member", member);

		return "/edit.jsp";
	}

}
