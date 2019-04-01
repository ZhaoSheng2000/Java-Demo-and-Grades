package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class TcpServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        //建立一个Socket端口，并监听一个端口
        ServerSocket ss=new ServerSocket(8888);

        //通过accept方法获取连接过来的客户端对象
        Socket s = ss.accept();
        String ip = s.getInetAddress().getHostAddress();
        System.out.println(ip+"connected....");

        //获取客户端发送过来的数据，服务端要使用客户端对象的读取流来读取数据
        InputStream in = s.getInputStream();
        byte[] buf = new byte[1024];
        int len = in.read(buf);
        System.out.println(new String(buf,0,len));


        /*
         * 给客户端反馈数据，要用到客户端的方法
         */
        OutputStream out = s.getOutputStream();
        Thread.sleep(5000);
        out.write("服务端反馈的数据。。。".getBytes());
        s.close();
        ss.close();

    }
}
