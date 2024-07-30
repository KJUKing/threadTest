package kr.or.ddit.basic;

public class ThreadTest08 {
    public static void main(String[] args) {
        Autosave auto = new Autosave();

//        auto.setDaemon(true); 반드시 start메소드 호출전 설정
        System.out.println("데몬 스레드여부" + auto.isDaemon());
        auto.start();
//        auto.setDaemon(true);

        try {
            for (int i = 0; i <= 20; i++) {
                System.out.println(i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {

        }
        System.out.println("main 스레드 종료...");
    }
}

//자동 저장하는 스레드(3초에 한번씩 저장하는 스레드
class Autosave extends Thread {
    //처리결과를 저장하는 메소드

    public void save() {
        System.out.println("처리 결과를 저장합니다.");
    }


    public void run() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        save();
    }

}
