package kr.or.ddit.basic;

import javax.swing.*;

/*
    컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오

    컴퓨터의 가위바위보난수를 이용해서 구하고
    사용자의 가위바위보는 showInputDialog()이용하여 입력받음

    입력시간은 5초로 제한하고 카운트 다운을 진행
    5초안에 입력이 없으면 게임에 진것으로 처리

    5초안에 입력이 완료되면 컴퓨터와 사용자 사이의 승패를 구해서 출력한다

    결과예시)
    1) 5초안에 입력을 못했을경우
            - 시간 초과로 당신이 졌습니다
    2) 5초안에 입력했을경우
            -- 결과 --
           컴퓨터 - 가위
           사용자 - 바위
           결과 - 당신이 이겼습니다.
 */
public class ThreadTest07 {


    public static void main(String[] args) {
        Thread th1 = new DataInputV1();
        Thread th2 = new CountDownV1();

        th1.start();
        th2.start();
    }
}

class DataInputV1 extends Thread {

    public static boolean inputCheck = false;
    public static String userInput = null;

    @Override
    public void run() {
        System.out.println("가위 바위 보 내기");
        userInput = JOptionPane.showInputDialog("가위 바위 보 셋중 하나 입력");
        inputCheck = true;
    }
}

class CountDownV1 extends Thread {
    @Override
    public void run() {
        String[] computer = {"가위", "바위", "보"};
        String computerChoice = computer[(int) (Math.random() * 3)];

        for (int i = 5; i >= 1; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            if (DataInputV1.inputCheck) {
                System.out.println("-- 결과 --");
                System.out.println("사용자 - " + DataInputV1.userInput);
                System.out.println("컴퓨터 - " +computerChoice);
                result(DataInputV1.userInput, computerChoice);
                return;
            }
        }
        System.out.println("시간 초과로 당신이 졌습니다..");
        System.exit(0);
    }

    private void result(String a, String b) {
        if (a.equals(b)) {
            System.out.println("비겼습니다");
        } else if (a.equals("가위") && b.equals("바위")) {
            System.out.println("졌습니다");
        } else if (a.equals("가위") && b.equals("보")) {
            System.out.println("이겼습니다");
        } else if (a.equals("바위") && b.equals("가위")) {
            System.out.println("이겼습니다");
        } else if (a.equals("바위") && b.equals("보")) {
            System.out.println("졌습니다");
        } else if (a.equals("보") && b.equals("가위")) {
            System.out.println("졌습니다");
        } else if (a.equals("보") && b.equals("바위")) {
            System.out.println("이겼습니다");
        }
    }
}