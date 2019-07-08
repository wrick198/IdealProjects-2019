import javax.xml.catalog.Catalog;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class HelloSocket {
    public static void main(String[] args) {
        try{
            Socket s=new Socket();
            s.connect(new InetSocketAddress("216.97.237.5",80),3000);
            Scanner sc=new Scanner(s.getInputStream(),"UTF-8");
            while (sc.hasNextLine()){
                String line=sc.nextLine();
                System.out.println(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
