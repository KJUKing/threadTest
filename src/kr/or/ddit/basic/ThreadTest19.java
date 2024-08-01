package kr.or.ddit.basic;

public class ThreadTest19 {
    public static void main(String[] args) {
        ShareDataBox dataBox = new ShareDataBox();

        ProducerThread th1 = new ProducerThread(dataBox);
        ConsumerThread th2 = new ConsumerThread(dataBox);

        th1.start();
        th2.start();
    }
}

class ShareDataBox{
    private String data;

    //data변수에 값이 있으면 data변수가 null이 될 때까지 기다린다.
    //data변수가 null이 되면 새로운 데이터를 data변수에 저장한다

    public synchronized void setData(String data) {
        if (this.data != null) {
            try{
                wait();
            } catch (InterruptedException e) {
            }
        }
        this.data = data;
        System.out.println("Thread에서 새로 공급한 데이터 : " + data);
        notify();
    }

    // data변수가 null이면 data변수에 문자열이 채워질때까지 기다린다.
    // data변수에 값이 있으면 해당 문자열을 반환한다.
    // 반환 후에는 data변수값을 null로 변경한다.
    public synchronized String getData() {
        if (data == null) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        //data 변수의 값을 임시 변수에 저장한다
        String temp = data;
        data = null;

        System.out.println("스레드가 읽은 데이터 : "+ temp);
        notify();

        return temp;
    }
}

//데이터를 넣어주는 역할만 하는 스레드
class ProducerThread extends Thread{
    private ShareDataBox dataBox;
    public ProducerThread(ShareDataBox shareDataBox) {
        super();
        this.dataBox = shareDataBox;
    }
    public void run() {
        String[] dataArr = new String[]{"홍길동","이순신","강감찬","변학도"};
        for (int i = 0; i < dataArr.length; i++) {
            dataBox.setData(dataArr[i]);
        }
    }
}

class ConsumerThread extends Thread {
    private ShareDataBox dataBox;

    public ConsumerThread(ShareDataBox dataBox) {
        super();
        this.dataBox = dataBox;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            String returnData = dataBox.getData();
        }
    }
}
