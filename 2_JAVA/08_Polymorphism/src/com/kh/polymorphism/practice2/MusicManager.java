package com.kh.polymorphism.practice2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MusicManager {
    private List<Music> musicList;
    private Scanner scanner;

    public MusicManager() {
        musicList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addLast() {
        System.out.println("****** 마지막위치에 곡 추가******");
        System.out.print("곡명: ");
        String title = scanner.nextLine();
        System.out.print("가수명: ");
        String artist = scanner.nextLine();

        musicList.add(new Music(title, artist));
        System.out.println("추가 성공");
    }

    public void addFirst() {
        System.out.println("****** 첫위치에 곡 추가******");
        System.out.print("곡명: ");
        String title = scanner.nextLine();
        System.out.print("가수명: ");
        String artist = scanner.nextLine();

        musicList.add(0, new Music(title, artist));
        System.out.println("추가 성공");
    }

    public void printAll() {
        System.out.println("****** 전체 곡 목록 출력******");
        for (Music music : musicList) {
            System.out.println(music.getArtist() + " - " + music.getTitle());
        }
    }

    public void search() {
        System.out.println("****** 특정 곡 검색******");
        System.out.print("검색할 곡명: ");
        String searchTitle = scanner.nextLine();

        boolean found = false;
        for (Music music : musicList) {
            if (music.getTitle().contains(searchTitle)) {
                System.out.println(music.getArtist() + " - " + music.getTitle() + "을(를) 검색했습니다.");
                found = true;
            }
        }
        if (!found) {
            System.out.println("해당 곡을 찾을 수 없습니다.");
        }
    }

    public void delete() {
        System.out.println("****** 특정 곡 삭제******");
        System.out.print("삭제할 곡명: ");
        String deleteTitle = scanner.nextLine();

        boolean removed = false;
        for (Music music : musicList) {
            if (music.getTitle().contains(deleteTitle)) {
                musicList.remove(music);
                System.out.println(music.getArtist() + " - " + music.getTitle() + "을(를) 삭제했습니다.");
                removed = true;
                break;  // 삭제 후 바로 루프 종료
            }
        }
        if (!removed) {
            System.out.println("삭제할 곡이 없습니다.");
        }
    }

    public void update() {
        System.out.println("****** 특정 곡 수정******");
        System.out.print("검색할 곡명: ");
        String searchTitle = scanner.nextLine();

        boolean found = false;
        for (Music music : musicList) {
            if (music.getTitle().contains(searchTitle)) {
                System.out.print("수정할 곡명: ");
                String newTitle = scanner.nextLine();
                System.out.print("수정할 가수명: ");
                String newArtist = scanner.nextLine();

                music.setTitle(newTitle);
                music.setArtist(newArtist);
                System.out.println(music.getArtist() + " - " + music.getTitle() + "의 값이 변경되었습니다.");
                found = true;
                break;  // 수정 후 바로 루프 종료
            }
        }
        if (!found) {
            System.out.println("검색한 곡을 찾을 수 없습니다.");
        }
    }

    public void sortByArtistDesc() {
        System.out.println("****** 가수명 내림차순 정렬******");
        Collections.sort(musicList, Comparator.comparing(Music::getArtist).reversed());
        printAll();
    }

    public void sortByTitleAsc() {
        System.out.println("****** 곡명 오름차순 정렬******");
        Collections.sort(musicList, Comparator.comparing(Music::getTitle));
        printAll();
    }
}
