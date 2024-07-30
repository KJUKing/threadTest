package kr.or.ddit.basic;

public class ThreadTest03 {
    // 스레드가 수행되는 시간을 체크해보자
    public static void main(String[] args) {


        MyRunner2 r = new MyRunner2();
        Thread th = new Thread(r);
        long start = System.currentTimeMillis();
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {

        }
        long end = System.currentTimeMillis();
        System.out.println("경과시간 : "+ (end - start));
    }
}

class MyRunner2 implements Runnable {
    @Override
    public void run() {
        long sum = 0L;
        for (long i = 1L; i <= 1_000_000_000L; i++) {
            sum += i;
        }
        System.out.println("합계 :" + sum);
    }
}
