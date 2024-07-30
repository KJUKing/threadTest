package kr.or.ddit.basic;

/**
    3개의 스레드가 각각 알파벳 A~Z까지 출력하는데
    출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기
 */
public class ThreadTest11 {
    public static void main(String[] args) {
        PrintCharacter[] pcTest = new PrintCharacter[]{
                new PrintCharacter("홍길동"),
                new PrintCharacter("이순신"),
                new PrintCharacter("강감찬")
        };

        for (PrintCharacter pc : pcTest) {
            pc.start();
        }
        for (int i = 0; i < pcTest.length; i++) {
            try {
                pcTest[i].join();
            } catch (InterruptedException e) {

            }
        }
        System.out.println();
        System.out.println("경기 결과");
        System.out.println("출력순서 : " + PrintCharacter.result);

    }
}

// A~Z까지 출력하는 스레드
class PrintCharacter extends Thread {
    //출력을 마친 스레드가 차례로 저장될 변수 선언
    public static String result = "";
    private String name;

    public PrintCharacter(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for(char ch='A'; ch<='Z'; ch++) {
            System.out.println(name + "의 출력 문자 : " + ch);
            try {
                //난수를 이용하여 출력하는 속도를 조절한다.
                Thread.sleep((int)(Math.random()*500));
            } catch (InterruptedException e) {

            }
        }
        System.out.println(name + " 출력 끝..");
        result += name + " ";
    }
}