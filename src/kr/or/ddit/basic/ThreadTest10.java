package kr.or.ddit.basic;

public class ThreadTest10 {
    public static void main(String[] args) {
        YieldTest th1 = new YieldTest("1번스레드");
        YieldTest th2 = new YieldTest("2번스레드");

        th1.start();
        th2.start();

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {

        }
        System.out.println("11111111111111111111111-------------------");

        th1.work = false;

        try {
            Thread.sleep(5);

        } catch (InterruptedException e) {

        }
        System.out.println("22222222222222222222222-------------------");

        th1.work = true;

        try {
            Thread.sleep(5);

        } catch (InterruptedException e) {

        }
        System.out.println("33333333333333333333333-------------------");
        th1.stop =true;
        th2.stop = true;

    }
}

class YieldTest extends Thread {
    public boolean stop = false;
    public boolean work = true;

    public YieldTest(String name) {
        super(name); // 스레드 이름 설정
    }

    @Override
    public void run() {
        while (!stop) {
            if (work) {
                //getName() --> 스레드의 이름을 반환함
                System.out.println(getName() + " 작업 중");

            } else {
                System.out.println(getName() + " 양보.. ");
                Thread.yield();
            }
        }
    }
}
