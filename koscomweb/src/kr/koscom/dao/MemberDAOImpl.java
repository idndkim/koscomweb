package kr.koscom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.koscom.common.DBUtil;
import kr.koscom.dto.MemberDTO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public int addMember(MemberDTO member) {
		//1.선언
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into member values(?,?,?,?,sysdate)";
		int resultCount = 0;
		try {
		//3.접속
		conn = DBUtil.getConnect();
		//4.쿼리작성
		ps = conn.prepareStatement(sql);
		ps.setString(1, member.getId());
		ps.setString(2, member.getName());
		ps.setString(3, member.getPassword());
		ps.setString(4, member.getEmail());
		
		//5. 실행
		resultCount = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			//2.닫기
			DBUtil.close(conn, ps);
		}
		
		return resultCount;
	}

	@Override
	public MemberDTO getMember(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberDTO member = new MemberDTO();
		String sql = "select id,password,name,email,reg_date from member where id = ?";
		try {
			conn = DBUtil.getConnect();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString(2));
				member.setName(rs.getString(3));
				member.setEmail(rs.getString(4));
				member.setRedDate(rs.getString(5));				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, ps, rs);
		}
		return member;
	}

	@Override
	public List<MemberDTO> getMemberList() {
		List<MemberDTO> memberList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberDTO member = null;
		String sql = "select id,password,name,email,reg_date from member ";
		try {
			conn = DBUtil.getConnect();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				member = new MemberDTO();
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString(2));
				member.setName(rs.getString(3));
				member.setEmail(rs.getString(4));
				member.setRedDate(rs.getString(5));
				memberList.add(member);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, ps, rs);
		}
		return memberList;
	}

	@Override
	public int updateMember(MemberDTO member) {
		Connection conn = null;
		PreparedStatement ps = null;
		int resultCount=0;
		String sql = "update member set name=?,password=?,email=? where id = ?"; 
		try {
			conn = DBUtil.getConnect();
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getPassword());
			ps.setString(3, member.getEmail());
			ps.setString(4, member.getId());
			
			resultCount = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, ps);
		}
		return resultCount;
	}
	
	public static void main(String[] args) {
		MemberDAOImpl dao = new MemberDAOImpl();
		MemberDTO member = new MemberDTO();
		member.setEmail("ccc@ccc.com");
		member.setName("kkkk");
		member.setPassword("111");
		member.setId("carami");
		System.out.println(dao.updateMember(member));
		System.out.println(dao.getMember("carami"));
	}	
}
