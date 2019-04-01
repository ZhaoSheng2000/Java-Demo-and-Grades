package tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpTransferServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10009);
        Socket s = ss.accept();
        String ip = s.getInetAddress().getHostAddress();
        System.out.println(ip+"  is connected...");

        //读取Socket流中的数据
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));

        //Socket输出流。将大写数据写入到Socket输出流，并发送给客户端、
        BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        String line = null;
        while((line=bufIn.readLine())!=null) {
            System.out.println(line);

            bufOut.write(line.toUpperCase());
            bufOut.newLine();
            bufOut.flush();
        }
        s.close();
        ss.close();
    }


}
