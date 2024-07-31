package kr.or.ddit.basic;

/**
    Thread의 stop()메소드를 호출하면 스레드가 바로 멈춘다
    이 때 사용하던 자원을 정리하지 못하고 스레드가 종료되어
    다른 스레드의 실행에 영향을 줄 수 있다.
 */
public class ThreadTest13 {

    public static void main(String[] args) {

//        ThreadStopTest01 th1 = new ThreadStopTest01();
//        th1.start();
//
//        try {
//            Thread.sleep(1000);
//
//        } catch (InterruptedException e) {
//
//        }
//        th1.stop();
        ThreadStopTest02 th2 = new ThreadStopTest02();
        th2.start();
        try{
            Thread.sleep(1000);

        } catch(InterruptedException e){

        }
        th2.interrupt();
    }
}

class ThreadStopTest01 extends Thread {
    private boolean stop;

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void run() {
        while (!stop) {
            System.out.println("쓰레드 실행중");
        }
        System.out.println("자원정리중");
        System.out.println("프로그램 종료");
    }
}

//Interrupt()메소드 이용하여 스레드 멈추기
class ThreadStopTest02 extends Thread {
    @Override
    public void run() {
        try{
            while (true) {
                System.out.println("Thread 실행중 ..");
                Thread.sleep(1);
            }

        }
        catch (InterruptedException e) {

        }
        System.out.println("자원정리");
        System.out.println("스레드 종료");
    }
}
