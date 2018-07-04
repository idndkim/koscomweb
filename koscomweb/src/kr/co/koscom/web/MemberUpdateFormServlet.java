package kr.co.koscom.web;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.koscom.dao.MemberDAO;
import kr.koscom.dao.MemberDAOImpl;
import kr.koscom.dto.MemberDTO;

/**
 * Servlet implementation class MemberUpdateFormServlet
 */
@WebServlet("/MemberUpdateFormServlet")
public class MemberUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		MemberDAO dao = new MemberDAOImpl();
		MemberDTO member = dao.getMember(id);
		
		request.setAttribute("member", member);
		RequestDispatcher rd = request.getRequestDispatcher("memberUpdateForm.jsp");
		rd.forward(request, response);
		
	}

}