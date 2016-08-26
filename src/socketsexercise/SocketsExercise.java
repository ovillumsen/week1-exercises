package socketsexercise;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketsExercise
{

    static String ip = "localhost";
    static int port = 8080;
    static DateFormat dateFormat = new SimpleDateFormat("EEEEEEEEE dd. LLLLLLLL yyyy HH:mm:ss zzz");
    static Date date = new Date();

    public static void main(String[] args) throws IOException
    {
        if (args.length == 2)
        {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }

        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(ip, port));
        System.out.println("Server started\nPort: " + port + "\nIP: " + ip);

        while (true)
        {
            Socket link = ss.accept();
            System.out.println("New client connected");
            handleClient(link);
        }
    }

    public static void handleClient(Socket s)
    {
        try
        {
            Scanner scan = new Scanner(s.getInputStream());
            PrintWriter print = new PrintWriter(s.getOutputStream(), true);
            String msg = "";
            print.println("Connected - " + dateFormat.format(date));
            while (!msg.equals("STOP"))
            {
                msg = scan.nextLine();
                if (msg.startsWith("#UPPER"))
                {
                    print.println(msg.substring(7).toUpperCase());
                }
                if (msg.startsWith("#LOWER"))
                {
                    print.println(msg.substring(7).toLowerCase());
                }
                if (msg.startsWith("#REVERSE"))
                {
                    String input = msg.substring(8, msg.length());
                    StringBuilder sb = new StringBuilder();
                    sb.append(input);
                    sb.reverse();
                    print.println(sb.toString().substring(0, 1).toUpperCase()
                            + sb.toString().substring(1, sb.length()).toLowerCase());
                }
                if (msg.startsWith("#TRANSLATE"))
                {
                    String word = msg.substring(11, msg.length());
                    switch (word)
                    {
                        case "hund":
                            if (word.equals("hund"))
                            {
                                print.println("dog");
                                break;
                            }
                        case "hest":
                            if (word.equals("hest"))
                            {
                                print.println("horse");
                                break;
                            }
                        case "kat":
                            if (word.equals("kat"))
                            {
                                print.println("cat");
                                break;
                            }
                        case "bil":
                            if (word.equals("bil"))
                            {
                                print.println("car");
                                break;
                            }
                        default:
                        {   
                            print.println("#WORD NOT FOUND");
                        }
                    }
                }
            }
            scan.close();
            print.close();
            s.close();
        } catch (IOException ex)
        {
            Logger.getLogger(SocketsExercise.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}