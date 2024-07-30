package kr.or.ddit.basic;

/**
    1~20억까지의 합계 구하는 프로그램을 하나의 스레드가 단독으로 처리할 때와
    여러개의 스레드가 협력해서 처리할 때의 경과시간을 비교해보자
 */
public class ThreadTest04 {
    public static void main(String[] args) {

        SumThread sm = new SumThread(1L, 2_000_000_000L);

        Thread th = new Thread(sm);
        SumThread[] sumArr = new SumThread[]{
                new SumThread(1L, 500_000_000L),
                new SumThread(500_000_001L, 1_000_000_000L),
                new SumThread(1_000_000_001L, 1_500_000_000L),
                new SumThread(1_500_000_001L, 2_000_000_000L)
        };

        long startTime = System.currentTimeMillis();
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {

        }

        long endTime = System.currentTimeMillis();

        System.out.println("단독 처리 시간 : " +(endTime - startTime));

        //여러개의 스레드가 협력 처리
        startTime = System.currentTimeMillis();
        for (int i = 0; i < sumArr.length; i++) {
            sumArr[i].start();
        }
        for (SumThread smt : sumArr) {
            try {
                smt.join();
            } catch (InterruptedException e) {

            }
        }
        endTime = System.currentTimeMillis();

        System.out.println("협력 처리 경과 시간 : " + (endTime - startTime));
    }
}

class SumThread extends Thread {
    //합계를 구할
    private long start;
    private long end;

    public SumThread(long start, long end) {
        super();
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        long sum = 0L;

        for (long i = start; i <= end; i++) {
            sum += i;
        }

        System.out.println(start + "부터" + end + "까지의 합계 : " + sum);
    }
}
