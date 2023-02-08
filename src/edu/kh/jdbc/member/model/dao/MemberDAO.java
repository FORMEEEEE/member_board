package edu.kh.jdbc.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.member.vo.Member;

public class MemberDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	public MemberDAO() {
		try {
		    prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-query.xml"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<Member> selectAll(Connection conn)throws Exception {
			
		List<Member> memberList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
				while(rs.next()) {
				String memberId = rs.getString("MEMBER_ID");
				String memberNm = rs.getString("MEMBER_NM");
				String memberGender = rs.getString("MEMBER_GENDER");
				
				
				Member mem = new Member(memberId,memberNm,memberGender);
				
				
				memberList.add(mem);
				
				
				}
			
			
		}finally {
			close(rs);
			close(stmt);
		}
		return memberList;
	}
	

	

}
