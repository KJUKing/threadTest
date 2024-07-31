package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/*
        Vector, Hashtable등과 같이 예전부터 존재하던 Collection객체들은 내부에
        동기화 처리가 되어있다.
        그런데 새로구성된 Collection들은 동기화 처리가 되어있지않다.
        그래서 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면
        동기화처리를 한 후에 사용해야한다.
 */
public class ThreadTest17 {
    private static Vector<Integer> vec = new Vector<>();

    private static List<Integer> list = new ArrayList<>();


    private static List<Integer> list1 = Collections.synchronizedList(new ArrayList<>());


    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
//                    vec.add(i);
//                    list.add(i);
                    list1.add(i);
                }
            }
        };
        //---------------------------------

        Thread[] thArr = new Thread[]{
                new Thread(runnable),
                new Thread(runnable),
                new Thread(runnable),
                new Thread(runnable),
                new Thread(runnable)
        };

        for (Thread th : thArr) {
            th.start();
        }

        for (Thread th : thArr) {
            try {
                th.join();
            }catch (InterruptedException e) {

            }
        }
//        System.out.println("vec의 개수 : " + vec.size());
//        System.out.println("list의 개수 : " + list.size());
        System.out.println("list1.size() = " + list1.size());
    }

}
