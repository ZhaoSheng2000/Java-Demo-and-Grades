package tcp;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpfileUploadServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10010);
        Socket s = ss.accept();
        String ip = s.getInetAddress().getHostAddress();
        System.out.println(ip+"  is connected...");


        InputStream in = s.getInputStream();
        FileOutputStream fos = new FileOutputStream("sever1.txt");
        byte[] buf = new byte[1024];
        int len = 0;
        while((len=in.read(buf))!=-1) {
            fos.write(buf,0,len);
        }


        OutputStream out = s.getOutputStream();
        out.write("上传成功".getBytes());

        fos.close();
        s.close();
        ss.close();

    }
}
