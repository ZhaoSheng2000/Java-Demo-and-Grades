package tcp;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;

public class TcpTransferClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket s = new Socket("192.168.43.81",10009);

        //读取键盘数据的流对象
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

        //将数据写入到Socket输出流，并发给服务端
        BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

        //定义Socket读取流，读取服务端返回的大写信息
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line = null;
        while((line=bufr.readLine())!=null) {
            if("over".equals(line))
                break;
            bufOut.write(line);
            bufOut.newLine();
            bufOut.flush();

            String str = bufIn.readLine();
            System.out.println("sever::::"+str);
        }
        bufr.close();
        s.close();
    }
}
