package edu.kh.jdbc.main.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.jdbc.member.vo.Member;
import static edu.kh.jdbc.common.JDBCTemplate.*;

public class MainDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	private Properties prop = null;
	// Map<Stirng, String> 제한, XML 파일 읽고/쓰는데 특화
	
	public MainDAO() {
		try {
			// Properties 객체 생성
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("main-query.xml"));
			// main query 파일의 내용을 읽어와 properties 객체에 저장
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	
	
	
	/** 로그인 DAO
	 * @param conn
	 * @param memberId
	 * @param memberPw
	 * @return loginMeneber
	 * @throws Exception
	 */
	public Member login(Connection conn, String memberId, String memberPw) throws Exception {
		
		// 1. 결과 저장용 변수 선언
		Member loginMember = null;
		
		try {
			// 2. sql 얻어오기(main-query.xml)
			String sql = prop.getProperty("login");
			
			//3. preparedstatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			//4 . ?에 값 대입
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			// 5. sql(Select) 수행 결과(result set) 반환받기
			rs = pstmt.executeQuery();
			
			// 6. 조회 결과가 있을 경우
			// 	컬럼값을 모두 얻어와
			// 	member 객체를 생성해서 loginmember에 대입
			
			if(rs.next()) {
				
				loginMember = new Member(rs.getInt("MEMBER_NO"),
						memberId,
						rs.getString("MEMBER_NM"),
						rs.getString("MEMBER_GENDER"),
						rs.getString("ENROLL_DATE")
						);
				
			}
			
		}finally {
			// 7. 사용한 jdbc 객체 자원 반환
			close(rs);
			close(pstmt);
		}
		
		
		// 8. 조회 결과 반환
		return loginMember;
	}




	/** 아이디 중복검사 dao
	 * @param conn
	 * @param memberId
	 * @return result
	 */
	public int idDupCheck(Connection conn, String memberId)throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("idDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//result = rs.getInt("COUNT(*)");
				result = rs.getInt(1); // 컬럼순서 > 1개이상일때는 비추
			}
			
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		return result;
	}




	/**
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member member) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("signUp");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberGender());
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
	
		return result;
	}

}
