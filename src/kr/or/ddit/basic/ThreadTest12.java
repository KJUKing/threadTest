package kr.or.ddit.basic;

import java.util.*;

/*
    10마리의 말들이 경주하는 경마 프로그램을 작성하시오

    말을 Horse라는 스레드 클래스로 작성하고
    이클래스의 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다
    그리고 이 클래스에는 등수를 오름차순으로 처리할 수있는 내부 정렬기준이 있다(comparable인터페이스 구현)

    경기구간은 1~ 50 구간으로 되어있다.

    그리고, 경기 중간 중간에 각 말들의 현재 위치를 나타내시오
    예)
    말이름01 ---->---------------------------
    말이름02 -->-----------------------------
    ..
    ..
    말이름10 -------->-----------------------

    경기가 모두 끝나면 등수 순으로 출력한다.
 */
public class ThreadTest12 {
    public static void main(String[] args) {

        //10마리의 말 만들기
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            horses.add(new Horse("말이름 " + i));
        }

        //출발하기
        for (Horse horse : horses) {
            horse.start();
        }

        //완주시킬때까지 스레드 기다리기
        for (Horse horse : horses) {
            try {
                horse.join();
            } catch (InterruptedException e) {
            }
        }

        Collections.sort(horses);
        System.out.println("경기가 끝났습니다. 최종 순위입니다.");
        for (Horse horse : horses) {
            System.out.println(horse.getHorseName() + " - " + horse.getGrade()+"등");
        }
    }


}

class Horse extends Thread implements Comparable<Horse> {
    private String horseName;
    private int grade; //순위
    private int position;
    private int currentRank = 1;
    private int trackLength = 50;


    public Horse(String name) {
        this.horseName = name;
        this.position = 0; //그냥 0으로 초기화해서 오류나는거방지
    }

    public int getCurrentRank() {
        return currentRank;
    }

    public int getGrade() {
        return grade;
    }

    public String getHorseName() {
        return horseName;
    }

    @Override
    public void run() {
//        Map<Integer, String> map = new HashMap<Integer, String>();
        //맵을 쓰려고했지만 지피티는 리스트를 추천하여 리스트로 리팩토링

        //위치 만들기
        List<String> track = new ArrayList<>(trackLength);
        for (int i = 0; i < trackLength; i++) {
            track.add("-");
        }

        while (position < trackLength) {
            position ++; // 달리는중
            updateTrack(track); // 실시간 위치 변경
            displayTrack(track);// 실시간 위치 보여주기
            try {
                //난수를 이용하여 출력하는 속도를 조절한다.
                Thread.sleep((int)(Math.random()*500));
            } catch (InterruptedException e) {

            }
        }
        grade = currentRank++;
        System.out.println(horseName + " 완주 - " + grade + "등");
        System.out.println();

    }

    private void displayTrack(List<String> track) {
        System.out.print(horseName + " ");
        for (String s : track) {
            System.out.print(s);
        }
        System.out.println();
    }

    private void updateTrack(List<String> track) {
        for (int i = 0; i < trackLength -1; i++) {
            if (i == position) {
                track.set(i, ">");
            } else {
                track.set(i, "-");
            }
        }
    }

    @Override
    public int compareTo(Horse other) {
        return Integer.compare(this.grade, other.grade);
    }
}
