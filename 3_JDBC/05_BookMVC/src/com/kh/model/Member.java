package com.kh.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private int member_no;
	private String member_id;
	private String member_pwd;
	private String member_name;
	private char gender;
	private String address;
	private String phone;
	private char status;
	private Date enroll_date;
}
