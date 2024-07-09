package com.kh.polymorphism.practice1;

import java.util.Scanner;

public class Application {
	
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Application app = new Application();
		app.mainMenu();
	}
	
	public void mainMenu() {
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("나이 : ");
		int age = Integer.parseInt(sc.nextLine());
		System.out.println("==== 메뉴 ====");
		
		boolean check = true;
		while(true) {
			System.out.println("1. 마이페이지");
			System.out.println("2. 도서 대여하기");
			System.out.println("3. 프로그램 종료하기");
			int select = Integer.parseInt(sc.nextLine());
			switch(select) {
			case 1:
				mypage();
				break;
			case 2:
				lent();
				break;
			case 3:
				exit();
				break;
			case 9:
				check = false;
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			}
		}

	}

	private void exit() {
	}

	private void lent() {
	}

	private void mypage() {
	}
	

}
