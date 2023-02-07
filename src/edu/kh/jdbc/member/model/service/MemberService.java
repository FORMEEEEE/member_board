package edu.kh.jdbc.member.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.vo.Member;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();

	public Member selectAll() {
		Connection conn = getConnection();
		
		Member loginMember = dao.selectAll(conn);
		
		close(conn);
		
		return loginMember;
	}



}
