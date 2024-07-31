package kr.or.ddit.basic;

// 스레드에서 객체를 공통으로 사용하는 예제

/*
    원주율을 계산하는 스레드와
    계산된 원주율을 출력하는 스레드가 있다.

    원주율을 저장하는 객체가 필요하다
    이 객체를 두 스레드에서 공통으로 사용해서처리한다
 */
public class ThreadTest14 {
    public static void main(String[] args) {
        // 공통으로 사용할 객체 생성
        ShareData sd = new ShareData();

        // 스레드 객체를 생성하고 공통으로 사용할 객체를 각각의 스레드에 주입
        CalcPIThread ct = new CalcPIThread();
        ct.setSd(sd);

        PrintPIThread pt = new PrintPIThread(sd);

        ct.start();
        pt.start();

    }
}

// 원주율 관리 클래스(공통으로 사용할 클래스)
class ShareData {
    public double result;
    public boolean isOK;
}

// 원주율을 계산하는 스레드
class CalcPIThread extends Thread {
    private ShareData sd;

    public void setSd(ShareData sd) {
        this.sd = sd;
    }

    @Override
    public void run() {
        // 원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 - ...) *4
        //      1 - 3 + 5 - 7 + 9 ...
        //      0   1   2   3   4
        double sum = 0.0;
        for (int i =1; i<1_000_000_000; i+=2) {
            if ((i / 2) % 2 == 0) { // i를 2로 나눈 몫이 짝수일때
                sum += 1.0 / i;

            } else {
                sum -= 1.0 / i;
            }
        }
        sd.result = sum *4;
        sd.isOK = true;
    }
}

//계산이 완료되면 계산된 원주율 출력하는 클래스
class PrintPIThread extends Thread {
    private ShareData sd;

    public PrintPIThread(ShareData sd) {
        this.sd = sd;
    }
    public void setSd(ShareData sd) {
        this.sd = sd;
    }

    @Override
    public void run() {
        while (true) {
            if (sd.isOK) {
                break;
            }else {
                yield();
            }
        }

        System.out.println();
        System.out.println("결과 : " + sd.result);
        System.out.println(" PI : " + Math.PI);

    }
}
