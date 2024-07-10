package com.kh.polymorphism.practice2;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        MusicManager manager = new MusicManager();
        Scanner scanner = new Scanner(System.in);

        int menuNum = 0;
        for(;;) {
            printMenu();
            System.out.print("메뉴번호입력: ");
            String input = scanner.nextLine();

            if (!input.matches("\\d")) {
                System.out.println("잘못입력하셨습니다. 다시입력해주세요.");
                continue;
            }

            menuNum = Integer.parseInt(input);
            switch (menuNum) {
                case 1:
                    manager.addLast();
                    break;
                case 2:
                    manager.addFirst();
                    break;
                case 3:
                    manager.printAll();
                    break;
                case 4:
                    manager.search();
                    break;
                case 5:
                    manager.delete();
                    break;
                case 6:
                    manager.update();
                    break;
                case 7:
                    manager.sortByArtistDesc();
                    break;
                case 8:
                    manager.sortByTitleAsc();
                    break;
                case 9:
                    System.out.println("종료");
                    break;
                default:
                    System.out.println("잘못된 메뉴 번호입니다.");
            }
        } 
    }

    public static void printMenu() {
        System.out.println("===== 메인메뉴 =====");
        System.out.println("1. 마지막위치에 곡 추가");
        System.out.println("2. 첫위치에 곡 추가");
        System.out.println("3. 전체 곡 목록 출력");
        System.out.println("4. 특정 곡 검색");
        System.out.println("5. 특정 곡 삭제");
        System.out.println("6. 특정 곡 수정");
        System.out.println("7. 가수명 내림차순 정렬");
        System.out.println("8. 곡명 오름차순 정렬");
        System.out.println("9. 종료");
    }
}
