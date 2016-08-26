/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads1exercise1;

/**
 *
 * @author Oliver
 */
public class Exercise1 extends Thread {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        long sum = 0;
        Runnable r = new MyRunnable();
        // Task 1.
        new Thread(r).start();
        for (long i = 0; i < 1000000000l; i++) {
            sum = sum += i;
        }
        System.out.println(sum);
        // Task 2.
        new Thread(r).start();
        for (int i = 1; i < 6; i++) {
            System.out.println(i);
            Thread.sleep(2000);
        }
        // Task 3.
        Thread t = new Thread(() -> {
            int count = 10;
            while (true) {
                try {
                    count++;
                    System.out.println(count);
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                }
            }
        });
        t.start();
        Thread.sleep(10000);
        t.stop();
    }


    

    static class MyRunnable implements Runnable {

        @Override
        public void run() {

        }

    }

    static class MyAdder {

        volatile int i = 0;

        public void setI(int i) {
            this.i = i;
        }
    }
}
