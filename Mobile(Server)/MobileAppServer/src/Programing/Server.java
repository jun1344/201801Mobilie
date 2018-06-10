package Programing;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {
	
	public int testint = 0;

    public static void main(String... args){
    	
    	double daa;
    	

        //자동 close
        try(ServerSocket server = new ServerSocket()){
            // 서버 초기화
            InetSocketAddress ipep = new InetSocketAddress(9999);
            server.bind(ipep);
             
            System.out.println("Initialize complate");
             
            //LISTEN 대기
            Socket client = server.accept();
            System.out.println("Connection");
             
            //send,reciever 스트림 받아오기
            //자동 close
            try(OutputStream sender = client.getOutputStream();
                InputStream reciever = client.getInputStream();){
                //클라이언트로 hello world 메시지 보내기
                //11byte 데이터
                String message = "hello world";
                byte[] data = message.getBytes();
                sender.write(data);
                 
                //클라이언트로부터 메시지 받기
                //2byte 데이터
                data = new byte[2];
                reciever.read(data, 0, data.length);
                 
                //수신 메시지 출력
                message = new String(data);
                String out = String.format("recieve - %s", message);
                System.out.println(out);
            }
        }catch(Throwable e){
            e.printStackTrace();
        }
    }
    
}
