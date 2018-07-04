package kr.koscom.dao;

import java.util.List;

import kr.koscom.dto.MemberDTO;

public interface MemberDAO {
	public int addMember(MemberDTO member);
	public MemberDTO getMember(String id);
	public List<MemberDTO> getMemberList();
	public int  updateMember(MemberDTO member);
}