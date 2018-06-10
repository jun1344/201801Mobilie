package com.example.junsic.receiptdiary;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by junsic on 2018-05-15.
 */

public class SignUp extends AppCompatActivity {

    Button idCheckButton;
    Button signupButton;
    Button cancleButton;

    EditText idEditText;
    EditText passwordEditText;
    EditText rePasswordEditText;

    RelativeLayout LoadingView;
    boolean isChecked = false;

    public static String serverIP = "210.89.190.224";
    public static int port = 11111;

    private SendMassgeHandler mMainHandler = null;
    private SignUpThread mSignUpThread = null;
    private IDcheckThread mIDcheckThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        // 메인 핸들러 생성
        mMainHandler = new SendMassgeHandler();
        //변수 선언
        idCheckButton = findViewById(R.id.signupIDCheckButton);
        signupButton = findViewById(R.id.signupSignpButton);
        cancleButton = findViewById(R.id.signupCancleButton);

        idEditText = findViewById(R.id.signupIDEditText);
        passwordEditText = findViewById(R.id.signupPasswordEditText);
        rePasswordEditText = findViewById(R.id.signupRePasswordEditText);

        LoadingView = findViewById(R.id.SignUpLodingView);

        // 버튼이벤트

        idCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                소캣
                 */
                if(idEditText.getText().toString().length() > 0){
                    LoadingView.setVisibility(View.VISIBLE);
                    mIDcheckThread = new IDcheckThread();
                    mIDcheckThread.start();
                }
                else{
                    Toast.makeText(getApplicationContext(), "아이디를 입력해 주십시오", Toast.LENGTH_LONG).show();
                }

            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isChecked){
                            if(passwordEditText.getText().toString().equals(rePasswordEditText.getText().toString()) && passwordEditText.getText().toString().length() > 0){
                                LoadingView.setVisibility(View.VISIBLE);
                                mSignUpThread = new SignUpThread();
                                mSignUpThread.start();
                            }
                            else
                                Toast.makeText(getApplicationContext(), "비밀번호를 재확인 해주세요",Toast.LENGTH_LONG).show();


                }
                else{
                    Toast.makeText(getApplicationContext(), "아이디확인 버튼을 눌러 아이디를 확인 해주세요",Toast.LENGTH_LONG).show();
                }
            }
        });

        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    class SignUpThread extends Thread implements Runnable {

        public SignUpThread() {
        }

        @Override
        public void run() {
            OutputStream out = null;
            FileInputStream fin;
            try {
                Socket soc = new Socket(serverIP, port);
                //System.out.println("Server Connected!");            // 11111 is Server port number
                out = soc.getOutputStream();                         // Create outputstream to socket
                DataOutputStream dout = new DataOutputStream(out);
                InputStream in = soc.getInputStream();             // Create inputstream to socket
                DataInputStream din = new DataInputStream(in);

                dout.writeUTF("Signup");

                JSONObject user = new JSONObject();   // Use JSON to store information which has to be stored at server
                user.put("id", idEditText.getText().toString());
                user.put("password", passwordEditText.getText().toString());
                dout.writeUTF(user.toString());
                String result = din.readUTF();
                if (result.equalsIgnoreCase("Success"))
                {
                    // 메시지 얻어오기
                    Message msg = mMainHandler.obtainMessage();

                    // 메시지 ID 설정
                    msg.what = 3;
                    mMainHandler.sendMessage(msg);
                }else                        // If failed
                {
                    // 메시지 얻어오기
                    Message msg = mMainHandler.obtainMessage();

                    // 메시지 ID 설정
                    msg.what = 4;
                    mMainHandler.sendMessage(msg);
                }

            } catch (Exception e) {
            }
            try { Thread.sleep(1000); }
            catch (InterruptedException e) { e.printStackTrace(); }

        }


    }

    class IDcheckThread extends Thread implements Runnable {

        public IDcheckThread() {
        }

        @Override
        public void run() {
            OutputStream out = null;
            FileInputStream fin;
            try {
                Socket soc = new Socket(serverIP, port);
                //System.out.println("Server Connected!");            // 11111 is Server port number
                out = soc.getOutputStream();                         // Create outputstream to socket
                DataOutputStream dout = new DataOutputStream(out);
                InputStream in = soc.getInputStream();             // Create inputstream to socket
                DataInputStream din = new DataInputStream(in);

                dout.writeUTF("IDCHECK");

                JSONObject user = new JSONObject();   // Use JSON to store information which has to be stored at server
                user.put("id", idEditText.getText().toString());
                dout.writeUTF(user.toString());
                String result = din.readUTF();
                if (result.equalsIgnoreCase("Success"))
                {
                    // 메시지 얻어오기
                    Message msg = mMainHandler.obtainMessage();

                    // 메시지 ID 설정
                    msg.what = 1;
                    mMainHandler.sendMessage(msg);
                }else                        // If failed
                {
                    // 메시지 얻어오기
                    Message msg = mMainHandler.obtainMessage();

                    // 메시지 ID 설정
                    msg.what = 2;
                    mMainHandler.sendMessage(msg);
                }

            } catch (Exception e) {
            }
            try { Thread.sleep(1000); }
            catch (InterruptedException e) { e.printStackTrace(); }

        }


    }
    class SendMassgeHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //작은게 성공
            //1,2 ID Check
            //3,4 Signup
            switch (msg.what) {
                case 1:
                    Toast.makeText(getApplicationContext(), "아이디를 확인 했습니다.", Toast.LENGTH_LONG).show();
                    isChecked = true;
                    LoadingView.setVisibility(View.GONE);
                    break;

                case 2:
                    Toast.makeText(getApplicationContext(), "아이디가 중복 되었습니다.", Toast.LENGTH_LONG).show();
                    LoadingView.setVisibility(View.GONE);
                    break;

                case 3:
                    Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_LONG).show();
                    LoadingView.setVisibility(View.GONE);
                    finish();
                    break;


                case 4:
                    Toast.makeText(getApplicationContext(), "회원가입에 실패 했습니다. 다시 확인 해주십시오 ", Toast.LENGTH_LONG).show();
                    LoadingView.setVisibility(View.GONE);
                    break;


                default:
                    Toast.makeText(getApplicationContext(), "네트워크 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
                    LoadingView.setVisibility(View.GONE);
                    break;
            }
        }

    };
}
