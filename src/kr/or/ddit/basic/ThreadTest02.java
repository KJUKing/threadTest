package kr.or.ddit.basic;

public class ThreadTest02 {
    public static void main(String[] args) {
        // 멀티 쓰레드 프로그램

        // Thread를 사용하는 방법

        // 방법1)
        // Thread클래스를 상속한 class를 작성하고 이 class의 인스턴스를 생성한다.
        MyThread1 th1 = new MyThread1();
        // 생성된 Thread클래스의 인스턴스의 start()메소드를 호출하여 실행한다.
        th1.start();

        // 방법2) ==> Runnable인터페이스를 구현한 class 작성후 이class의 인스턴스 생성
        // 이 인스턴스를 Thread클래스의 인스턴스를 생성할 때 생성자의 인수값으로 넣어준다
        // Thread클래스의 인스턴스의 start()메소드를 호출해서 실행한다.
        MyRunner1 r1 = new MyRunner1();
        new Thread(r1).run();

        // 방법2-2) Runnable의 익명 구현체를 이용하는 방법
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    System.out.print("@");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                }
            }
        };

        Thread th3 = new Thread(r2);
        th3.start();

        System.out.println("메인 메소드 끝");
        /*Runnable r2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    System.out.print("@");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                }
            }
        };*/
    }
}

// 방법1) ==> Thread클래스를 상속한 class 만들기 (*문자 200개 출력하는 메소드)
class MyThread1 extends Thread {

    @Override
    public void run() {
        // 이 run()메소드에서는 이 스레드가 처리할 내용을 기술하면 된다.
        for (int i = 0; i < 200; i++) {
            System.out.print("*");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
        }
    }
}

// 방법2) Runnable인터페이스를 구현한 class
class MyRunner1 implements Runnable {
    @Override
    public void run() {
        // 이 run()메소드에서는 이 스레드가 처리할 내용을 기술하면 된다.
        for (int i = 0; i < 200; i++) {
            System.out.print("$");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
        }
    }
}



