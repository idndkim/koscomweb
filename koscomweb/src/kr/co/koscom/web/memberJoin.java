package kr.co.koscom.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.koscom.dao.MemberDAO;
import kr.koscom.dao.MemberDAOImpl;
import kr.koscom.dto.MemberDTO;

/**
 * Servlet implementation class memberJoin
 */
@WebServlet("/memberJoin")
public class memberJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberJoin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MemberDTO member = new MemberDTO();
		member.setName(request.getParameter("name"));
		member.setId(request.getParameter("id"));
		member.setPassword(request.getParameter("password"));
		member.setEmail(request.getParameter("email"));
		
		MemberDAO dao = new MemberDAOImpl();
		int resultCount = dao.addMember(member);
		
		if( resultCount == 1) {
			response.sendRedirect("memberList.html");			
		}else {
			response.sendRedirect("memberInput.html");			
		}
			
		/*
		response.setContentType("text/html; charaset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>memberJoin</title></head></title>");
		if(resultCount == 1) {
			out.print("<h1> 가입 성공 </h1>");
		}else {
			out.print("<h1> 가입 실패 </h1>");			
		}
		*/		
	}

}
