package kr.or.ddit.basic;

public class ThreadTest01 {
    public static void main(String[] args) {

        // *200개, $200개
        // 싱글스레드 프로그램
        for (int i = 1; i <= 200; i++) {
            System.out.print("*");
        }

        for (int i = 1; i <= 200; i++) {
            System.out.print("$");
        }

    }
}
