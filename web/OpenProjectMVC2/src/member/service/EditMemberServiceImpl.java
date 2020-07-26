package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import service.Service;

public class EditMemberServiceImpl implements Service {

	MemberDao dao;

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		
		String msg = "";

		Connection conn = null;

		try {

			int idx = Integer.parseInt(request.getParameter("idx"));

			conn = ConnectionProvider.getConnection();
			dao = MemberDao.getInstance();

			int resultCnt = dao.memberDelete(conn, idx);

			switch (resultCnt) {
			case 0:
				msg = "회원정보가 존재하지 않습니다.";
			case 1:
				request.getSession().invalidate();
				msg = "정상적으로 탈퇴했습니다.";
			}

		} catch (SQLException e) {
			System.out.println("SQL 오류");
			e.printStackTrace();
		} catch (NumberFormatException ex) {
			System.out.println("숫자 형식이 아닌 데이터 요청입니다.");
			msg = "잘못된 요청입니다. 정상적인 경로를 이용해주세요.";
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		request.setAttribute("msg", msg);

		return "/deleteMember.jsp";
	}

}
