package com.company.netstudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class URLTest {
    public static void main(String[] args) throws IOException {
        //常见一个url实例
        URL baidu = new URL("https://www.baidu.com");
        URL url = new URL(baidu,"/s?wd=android&rsv_spt=1");
        System.out.println("协议"+url.getProtocol());
        System.out.println("PORT"+url.getPort());
        System.out.println("mao"+url.getRef());
        System.out.println("查询"+url.getQuery());

        //通过url,openStream获取url实例所表示的资源的字节输入流
        InputStream is = baidu.openStream();
        //转成字符流
        InputStreamReader isr = new InputStreamReader(is);
        //为字符流创建缓存
        BufferedReader br = new BufferedReader(isr);
        String data = br.readLine();
        while (data!=null){
            System.out.println(data);
            data = br.readLine();
        }
    }
}
