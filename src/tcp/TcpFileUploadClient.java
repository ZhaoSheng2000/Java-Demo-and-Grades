package tcp;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;
public class TcpFileUploadClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket s = new Socket("192.168.43.81",10010);

        FileInputStream fis = new FileInputStream(" /Users/mac/Desktop/java/代码UTF-8/GetIP.java");
        OutputStream out = s.getOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while((len=fis.read(buf))!=-1) {
            out.write(buf,0,len);
        }
        s.shutdownOutput();


        InputStream In = s.getInputStream();
        byte[] bufIn = new byte[1024];
        int num = In.read(bufIn);
        System.out.println(new String(bufIn,0,num));



        fis.close();
        s.close();
    }
}
