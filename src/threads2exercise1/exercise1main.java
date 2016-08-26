/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads2exercise1;

/**
 *
 * @author Oliver
 */
public class exercise1main extends Thread {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Avilable Processors: " + Runtime.getRuntime().availableProcessors());
        GetBytesFromUrl ex1 = new GetBytesFromUrl("https://fronter.com/cphbusiness/design_images/images.php/Classic/login/fronter_big-logo.png");
        //ex1.start();
        GetBytesFromUrl ex2 = new GetBytesFromUrl("https://fronter.com/cphbusiness/design_images/images.php/Classic/login/folder-image-transp.png");
        //ex2.start();
        GetBytesFromUrl ex3 = new GetBytesFromUrl("https://fronter.com/volY12-cache/cache/img/design_images/Classic/other_images/button_bg.png");
        //ex3.start();
        long start = System.nanoTime();
        // Start
//        ex1.start();
//        ex2.start();
//        ex3.start();
        // Run
        ex1.run();
        ex2.run();
        ex3.run();

        ex1.join();
        ex2.join();
        ex3.join();
        System.out.println(ex1.getSum() + ex2.getSum() + ex3.getSum());
        long end = System.nanoTime();
        System.out.println("Time Sequental: " + (end - start));
        // The difference between start and run is that using start will generate a thread and use run while run will call the method run.
        // Start will have the advantage of using run in a different thread and therefore can move on with the program.
        // Run will be stuck going through the method run before generating a thread.
    }
}
