package Programing;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
 
public class Client {
    public static void main(String... args){
        //자동 close
        try(Socket client = new Socket()){
            //클라이언트 초기화
/*            InetSocketAddress ipep = new InetSocketAddress("210.89.190.224", 9999);*/
        	InetSocketAddress ipep = new InetSocketAddress("192.0.0.1", 9999);
            //접속
            client.connect(ipep);
             
            //send,reciever 스트림 받아오기
            //자동 close
            try(OutputStream sender = client.getOutputStream();
                InputStream receiver = client.getInputStream();){
                //서버로부터 데이터 받기
                //11byte
                byte[] data = new byte[11];
                receiver.read(data);
                 
                //수신메시지 출력
                String message = new String(data);
                String out = String.format("recieve - %s", message);
                System.out.println(out);
                 
                //서버로 데이터 보내기
                //2byte
                message = "OK";
                data = message.getBytes();
                sender.write(data, 0, data.length);
            }
        }catch(Throwable e){
            e.printStackTrace();
        }
    }
}
