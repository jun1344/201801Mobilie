package Programing;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientUser {

Socket client=null;
    
    String ipAddress;//서버 아이피 주소
    static final int port=5000;
    
    BufferedReader reader;
    
    InputStream is;
    ObjectInputStream ois;
    
    OutputStream os;
    ObjectOutputStream oos;//출력스트림 변수 정의
    
    String sendData;//서버로 보낼 자료를 저장할 변수
    String receiveData;//서버에서 보낸 자료를 저장할
    //변수
    
    public ClientUser(){}
    
    public ClientUser(String ip){//생성자 오버로딩.
        ipAddress=ip;//서버 아이피 주소 저장
        
        try{
            System.out.println("**사용자**");
            client=new Socket(ipAddress,port);
//서버 아이피주소와 포트번호를 주면 클라이언트 소캣객체가
//생성되고 난 이후 서버에서도 소캣객체가 생성됨.
            reader= new BufferedReader(new InputStreamReader(System.in));
 
            os=client.getOutputStream();
            oos=new ObjectOutputStream(os);
            //출력스트림 객체 생성
            is=client.getInputStream();
            ois=new ObjectInputStream(is);
            //입력스트림 객체 생성
            
            System.out.print("입력:");
            
            		while((sendData = reader.readLine()) !=null){
            				oos.writeObject(sendData);//읽어들인 자료를 서버로

            				oos.flush();//출력스트림을 비움.
            				if(sendData.equals("quit")) break;//while문 종료
            					receiveData=(String)ois.readObject();
            					//서버에서 다시 보낸 자료를 받아 저장.
            					System.out.println(client.getInetAddress()+"로 부터"+ "받은 메시지:"+receiveData);
            					System.out.print("입력:");
            				}
            is.close(); ois.close(); os.close(); oos.close();
  			client.close();
  
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);//클라이언트 프로그램 종료
        }
    }//생성자
    
    public static void main(String[] args) {
          new ClientUser("xxx.xxx.xxx.xxx");
          /* cmd -> ipconfig
           * 내 자신 컴퓨터 주소를 뜻하는 ip:127.0.0.1
           * 내 자신 컴퓨터 주소를 뜻하는 :localhost
           * 서버 아이피 주소를 입력해도 된다.
           * 내 자신 컴에 서버 프로그램을 실행했기 때문에
           * 127.0.0.1 또는 localhost를 사용해도 된다.
           */
    }
}
