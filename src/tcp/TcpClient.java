package tcp;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class TcpClient {
    /*
     * Tcp分客户端和服务端
     * 客户端对应的对象是Socket
     * 服务端对应的是SeverSocket
     * 客户端：
     * 通过查阅Socket对象，发现该对象建立时，就可以连接指定主机
     * 因为tcp是面向对象连接的
     * 所以在建立Socket服务时就要有服务端存在，并能连接成功、
     * 只有形成通路后才会在该通路进行数据传输
     */


    /*
     * 1.创建socket服务，并指定要连接的主机和端口
     *
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        //创建客户端的Socket服务，并指定主机和端口
        Socket s = new Socket("192.168.43.81",8888);
        //为了发送数据，应该获取Socket流中的输出流
        OutputStream out = s.getOutputStream();

        out.write("来自客户端的数据".getBytes());
        /*
         * 读取服务端反馈的数据
         */
        InputStream in = s.getInputStream();
        byte[] buf = new byte[1024];
        int len = in.read(buf);
        System.out.println(new String(buf,0,len));
        s.close();
    }

}
