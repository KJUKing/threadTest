package kr.or.ddit.basic;

import javax.swing.*;

public class ThreadTest06 {
    public static void main(String[] args) {
        Thread th1 = new DataInput();
        Thread th2 = new CountDown();

        th1.start();
        th2.start();
    }
}

class DataInput extends Thread {

    public static boolean inputCheck = false;

    @Override
    public void run() {
        String str = JOptionPane.showInputDialog("아무거나입력");
        System.out.println("입력한 값 : " + str);
        inputCheck = true;
    }
}

class CountDown extends Thread {
    @Override
    public void run() {
        for (int i = 10; i >= 1; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            if (DataInput.inputCheck==true) {
                return;
            }
        }
        System.out.println("10초가 지났습니다.. 프로그램을 종료합니다.");
        System.exit(0);
    }
}