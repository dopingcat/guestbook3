package com.hanains.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hanains.guestbook.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	private Connection getConnection() throws SQLException {
		Connection connection = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection( dbURL, "webdb", "webdb" );
		} catch( ClassNotFoundException ex ){
			System.out.println( "드라이버 로딩 실패-" + ex );
		}
		return connection;
	}
	
	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String sql = "select no, name, password, message, reg_date from GUESTBOOK order by reg_date desc";
		
		try {
			if(connection == null) {
				connection = getConnection();
			}
			pstmt = connection.prepareStatement(sql);
			
			resultSet = pstmt.executeQuery(sql);
			
			while(resultSet.next()) {
				list.add(new GuestbookVo(
						resultSet.getLong(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getString(5)));
			}
		} catch (SQLException sqle) {
			System.err.println("excute sql failed - " + sqle.toString());
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.toString());
			}
		}
		return list;
	}
	
	public void insert(GuestbookVo vo) {
		String sql = "insert into guestbook values(GUESTBOOK_SEQ.nextval, ?, ?, ?, SYSDATE)";
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			if(connection == null) {
				connection = getConnection();
			}
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
			
			pstmt.executeUpdate();
		} catch (SQLException sqle) {
			System.err.println(sqle.toString());
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.toString());
			}
		}
	}
	
	public void delete(Long no, String password) {
		String sql = "delete from guestbook where no=? and password=?";
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			if(connection == null) {
				connection = getConnection();
			}
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			pstmt.setString(2, password);
			
			pstmt.executeUpdate();
		} catch (SQLException sqle) {
			System.err.println(sqle.toString());
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.toString());
			}
		}
	}
}
