package udp;

import java.net.*;

public class GetIP {
    public static void main(String[] args) throws Exception{
        /*
         * 获取本机IP
         * 获取本机名字
         */
        InetAddress i = InetAddress.getLocalHost();
        System.out.println(i.toString());
        System.out.println("adress::::"+i.getHostAddress());
        System.out.println("name:::"+i.getHostName());
        /*
         * 获取任意一台电脑的名字
         */
        InetAddress ia = InetAddress.getByName("www.zzuli.edu.cn");
        System.out.println(ia.toString());
        System.out.println("adress:::"+ia.getHostAddress());
        System.out.println("name:::"+ia.getHostName());

    }

}
