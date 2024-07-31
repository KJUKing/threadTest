package kr.or.ddit.basic;

public class ThreadTest15 {
    public static void main(String[] args) {
        ShareObject sObj = new ShareObject(); // 공통 객체 생성

        TestThread th1 = new TestThread("1번 스레드", sObj);
        TestThread th2 = new TestThread("2번 스레드", sObj);

        th1.start();
        th2.start();
    }
}

//공통 사용 객체
class ShareObject {
    private int sum =0;
//    public synchronized void add() { //방법  1 메소드에붙히기
//        int num = sum;
//
//        num += 10; //10증가
//
//        sum = num;
//
//        System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
//
//    }
    public void add() { //방법2
        synchronized (this) {
            int num = sum;

            num += 10; //10증가

            sum = num;

            System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
        }
    }
}

class TestThread extends Thread {
    private ShareObject sObj;

    public TestThread(String name, ShareObject sObj) {
        super(name);        //Thread의 name값 설정
        this.sObj = sObj;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            sObj.add();
        }
    }
}