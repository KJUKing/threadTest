package kr.or.ddit.basic;

//스레드의 상태값을 출력하는 프로그램

public class ThreadTest09 {
    public static void main(String[] args) {
        PrintThreadState th = new PrintThreadState(new TargetThread());
        th.start();
    }
}

//스레드의 상태값 검사할 대상이 되는 스레드
class TargetThread extends Thread{
    @Override
    public void run() {
        for (long i = 1L; i <= 20_000_000_000L; i++) {
            long a;
            a = i+1;
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {

        }

        for (long i =1L; i < 20_000_000_000L; i++) {
            long a;
            a = i+1;
        }
    }
}

//검사 대상 스레드(TargetThread)의 상태값을 출력하는 스레드
class PrintThreadState extends Thread {
    private TargetThread target;

    public PrintThreadState(TargetThread target) {
        super();
        this.target = target;
    }

    @Override
    public void run() {
        while (true) {
            //스레드의 ㅅ아태값 구하기 -> getState()메소드 사용
            Thread.State state = target.getState();
            System.out.println("TargetThread의 상태값 : " + state);

            if (state == State.NEW) {
                target.start();
            }
            if (state == State.TERMINATED) {
                break;
            }

            try{
                Thread.sleep(500);

            } catch (InterruptedException e) {
            }

        }
    }
}