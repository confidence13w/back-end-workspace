package com.kh.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.model.Member;

public class MemberController {
	
	private String currentPassword;

	public MemberController() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnect() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "qwer1234");
	}
	
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		ps.close();
		conn.close();
	}
	
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		rs.close();
		closeAll(ps, conn);
	}
	
	public void signUp(Member member) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    try {
	        conn = getConnect();
	        if (!isUsernameExists(member.getId(), conn)) {
	            String sql = "INSERT INTO member (id, password, name) VALUES (?, ?, ?)";
	            ps = conn.prepareStatement(sql);
	            ps.setString(1, member.getId());
	            ps.setString(2, member.getPassword());
	            ps.setString(3, member.getName());
	            
	            int rowsInserted = ps.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("회원가입 성공!");
	            }
	        } else {
	            System.out.println("이미 가입되어 있습니다.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	private boolean isUsernameExists(String username, Connection conn) throws SQLException {
	    String sql = "SELECT * FROM member WHERE id = ?";
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setString(1, username);
	    ResultSet rs = ps.executeQuery();
	    return rs.next();
	    
		// 회원가입 기능 구현! 
		// -> 아이디가 기존에 있는지 체크 여부!
		// -> member 테이블에 데이터 추가!
	}
	
	public Member login(String username, String password) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Member member = null;
	    try {
	        conn = getConnect();
	        String sql = "SELECT * FROM member WHERE id = ? AND password = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, username);
	        ps.setString(2, password);
	        
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            member = new Member();
	            member.setId(rs.getString("id"));
	            member.setPassword(rs.getString("password"));
	            member.setName(rs.getString("name"));
	        } else {
	            System.out.println("유효하지 않은 사용자 이름 또는 아이디입니다.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return member;
	    
		// 로그인 기능 구현! 
		// -> member 테이블에서 id와 password로 멤버 정보 하나 가져오기!
	}

	public void changePassword(String username, String newPassword) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    try {
	        conn = getConnect();

	        Member currentMember = login(username, currentPassword);
	        if (currentMember != null) {
	            String sql = "UPDATE member SET password = ? WHERE id = ?";
	            ps = conn.prepareStatement(sql);
	            ps.setString(1, newPassword);
	            ps.setString(2, username);
	            
	            int rowsUpdated = ps.executeUpdate();
	            if (rowsUpdated > 0) {
	                System.out.println("비밀번호 수정 성공!");
	            }
	        } else {
	            System.out.println("유효하지 않은 사용자 이름 또는 현재 비밀번호 입니다.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		// 비밀번호 바꾸기 기능 구현!
		// -> login 메서드 활용 후 사용자 이름이 null이 아니면 member 테이블에서 id로 새로운 패스워드로 변경

	}

	public void changeName(String username, String newName) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    try {
	        conn = getConnect();
	        String sql = "UPDATE member SET name = ? WHERE id = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, newName);
	        ps.setString(2, username);
	        
	        int rowsUpdated = ps.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("이름 수정 성공!");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
		// 이름 바꾸기 기능 구현!
		// -> member 테이블에서 id로 새로운 이름으로 변경 
		
	}
	
}