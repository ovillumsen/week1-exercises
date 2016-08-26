/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads2exercise1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oliver
 */
public class GetBytesFromUrl extends Thread {

    String iurl;
    int sum;

    public GetBytesFromUrl(String url) {
        iurl = url;
    }

    public int getSum() {
        return sum;
    }


    


    public static byte[] getBytesFromUrl(String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.connect();
        ByteArrayOutputStream bis = new ByteArrayOutputStream();
        try {
            InputStream is = connection.getInputStream();
            byte[] bytebuff = new byte[4096];
            int read;
            while ((read = is.read(bytebuff)) > 0) {
                bis.write(bytebuff, 0, read);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return bis.toByteArray();
    }

    @Override
    public void run() {
        try {
            sum = calcSum(getBytesFromUrl(iurl));
        } catch (IOException ex) {
            Logger.getLogger(GetBytesFromUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int calcSum(byte[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }
}

