package com.kh.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.model.Book;

import config.ServerInfo;


public class BookController implements ServerInfo {

	private Connection conn;

	private void dbc() {
	 try {
		Class.forName(DRIVER_NAME);
		conn = DriverManager.getConnection(URL, USER, PASSWORD);
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
	} 
	 public void closeAll(PreparedStatement ps, Connection conn) {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	 public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) {
			try {
				rs.close();
				closeAll(ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	// 1. 전체 책 조회
	public ArrayList<Book> printBookAll() {
		// 반복문을 이용해서 책 리스트 출력
		dbc();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("SELECT * FROM book");
			ResultSet rs = ps.executeQuery();
			ArrayList<Book> list = new ArrayList();
			
			while(rs.next()) {
				Book book = new Book();
				book.setBk_no(rs.getInt("bk_no"));
				book.setBk_title(rs.getString("bk_title"));
				book.setBk_author(rs.getString("bk_author"));
				book.setBk_price(rs.getInt("bk_price"));
				book.setBk_pub_no(rs.getInt("bk_pub_no"));
				list.add(book);
			}
			closeAll(rs,ps,conn);
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 제목 체크
	public boolean titleCheck(String bk_title) {
		PreparedStatement ps;
		try {
			dbc();
			ps = conn.prepareStatement("SELECT * FROM book WHERE bk_title = ?");
			ps.setString(1, bk_title);
			
			ResultSet rs = ps.executeQuery();
			String titleCheck = null;
			if(rs.next()) {
				titleCheck = rs.getString("bk_title");
			}
		    closeAll(rs,ps, conn);
		    
		    if(titleCheck != null) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean authorCheck(String bk_author) {
		PreparedStatement ps;
		try {
			dbc();
			ps = conn.prepareStatement("SELECT * FROM book WHERE bk_author = ?");
			ps.setString(1, bk_author);
			
			ResultSet rs = ps.executeQuery();
			String authorCheck = null;
			if(rs.next()) {
				authorCheck = rs.getString("bk_title");
			}
		    closeAll(rs,ps, conn);
		    
		    if(authorCheck != null) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	// 2. 책 등록
	public boolean registerBook(String bk_title, String bk_author) {
		try {
			dbc();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO book(bk_title, bk_author) VALUES (?, ?)");
			
			if (titleCheck(bk_title) && authorCheck(bk_author)) {
				return false;
			}
			else {
				ps.setString(1, bk_title);
				ps.setString(2, bk_author);
				ps.executeUpdate();
				closeAll(ps,conn);
				return true;
				
			}
		} catch (SQLException e) {
			return false;
		}
		
		// 책 제목, 책 저자를 사용자한테 입력 받아
		// 기존 제목, 저자 있으면 등록 안되게!
		// 등록에 성공하면 "성공적으로 책을 등록했습니다." 출력
		// 실패하면 "책을 등록하는데 실패했습니다." 출력
	}

	// 3. 책 삭제
	public boolean sellBook(int bk_no) {
		// printBookAll로 전체 책 조회를 한 후
		// 삭제할 책 번호 선택을 사용자한테 입력 받아
		// 삭제에 성공하면 "성공적으로 책을 삭제했습니다." 출력
		// 실패하면 "책을 삭제하는데 실패했습니다." 출력
		dbc();
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM book WHERE bk_no = ?");
			ps.setInt(1, bk_no);
			ps.executeUpdate();
			closeAll(ps,conn);
			return true;
		} catch (SQLException e) {
			return false;
		}
		
		
	}

	// 4. 회원가입
	public boolean registerMember(String member_id, String member_pwd, String member_name) {
		dbc();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO member(member_id, member_pwd,member_name) Values (?,?,?)");
			ps.setString(1,member_id);
			ps.setString(2,member_pwd);
			ps.setString(3,member_name);
			ps.executeUpdate();
			closeAll(ps,conn);
			return true;
		} catch (SQLException e) {
			return false;
		}
		
		// 아이디, 비밀번호, 이름을 사용자한테 입력 받아
		// 회원가입에 성공하면 "성공적으로 회원가입을 완료하였습니다." 출력
		// 실패하면 "회원가입에 실패했습니다." 출력
	}

	// 5. 로그인
	public String login(String member_id, String member_pwd) {
		dbc();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT member_name FROM member Where member_id = ? AND member_pwd = ?"); // &&도 가능!
			ps.setString(1, member_id);
			ps.setString(2, member_pwd);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String member_name = rs.getString("member_name");
				closeAll(rs, ps, conn);
				return member_name;
			} else {
				closeAll(rs, ps, conn);
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		// 아이디, 비밀번호를 사용자한테 입력 받아
		// 로그인에 성공하면 "~~님, 환영합니다!" 출력 후 memberMenu() 호출
		// 로그인에 성공하면 "~~님, 환영합니다!" 출력 후
	}
	}
	
	// 1. 책 대여
		public void rentBook() {
			// printBookAll 메서드 호출하여 전체 책 조회 출력 후
			// 대여할 책 번호 선택을 사용자한테 입력 받아
			// 대여에 성공하면 "성공적으로 책을 대여했습니다." 출력
			// 대여에 성공하면 "성공적으로 책을 대여했습니다." 출력
		}

		// 2. 내가 대여한 책 조회
		public void printRentBook() {
			// 내가 대여한 책들을 반복문을 이용하여 조회
			// 대여 번호, 책 제목, 책 저자, 대여 날짜, 반납 기한 조회
		}

		// 3. 대여 취소
		public void deleteRent() {
			// printRentBook 매서드 호출하여 내가 대여한 책 조회 출력 후
			// 취소할 대여 번호 선택을 사용자한테 입력 받아
			// 취소에 성공하면 "성공적으로 대여를 취소했습니다." 출력
			// 실패하면 "대여를 취소하는데 실패했습니다." 출력
		}

		// 4. 회원탈퇴
		public void deleteMember() {
			// 회원탈퇴에 성공하면 "회원탈퇴 하였습니다 ㅠㅠ" 출력
			// 실패하면 "회원탈퇴하는데 실패했습니다." 출력
		}

	}


