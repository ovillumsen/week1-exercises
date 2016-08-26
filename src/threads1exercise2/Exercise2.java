/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads1exercise2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oliver
 */
public class Exercise2 {

    public static void main(String[] args) {
        Even e = new Even();
        new Thread(() -> {
            while(true) {
            e.next();
            System.out.println(e.n);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Exercise2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
        new Thread(() -> {
            while(true) {
            e.next();
            System.out.println(e.n);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Exercise2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    

    

}
    static public class Even {

        private int n = 0;

        synchronized public int next() {
            n++;
            n++;
            return n;
        }
    }
}
