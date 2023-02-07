package edu.kh.jdbc.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

	public Member selectAll(Connection conn) {
	
		try {
			String sql = "";
			
		}finally {
			
		}
		return null;
	}
	

	

}
