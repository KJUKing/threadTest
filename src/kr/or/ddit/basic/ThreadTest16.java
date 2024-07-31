package kr.or.ddit.basic;

public class ThreadTest16 {
    private int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    //입금하는 메소드
    public void deposit(int money) {
        balance += money;
    }
    //출금하는 메소드
    public synchronized boolean withdraw(int money) {
        if (balance >= money) {
            try {   //시간 2초 지연시키기
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            balance -= money;
            System.out.println("출금 메소드 안에 잔액 : " + balance);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ThreadTest16 account = new ThreadTest16();
        account.setBalance(10000);

        // 익명구현체로 스레드 구현
        Runnable r = new Runnable(){
            @Override
            public void run() {
                boolean result = account.withdraw(6000);
                System.out.println("스레드에서 result = "+ result +
                        ", 잔액 : " + account.getBalance());
            }
        };

        Thread th1 = new Thread(r);
        Thread th2 = new Thread(r);
        th1.start();
        th2.start();

    }
}
