package com.example.junsic.receiptdiary;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    //Server Test User ID
    public static String IDNAME;
    static String userID = "userID";
    static String password = "password";
    public static String serverIP = "210.89.190.224";
    public static int port = 11111;

    private boolean isLogin = false;

    EditText editTextId;
    EditText editTextPassword;
    Button signupButton;
    Button signinButton;
    Button webButton;
    RelativeLayout LoadingView;
    RelativeLayout TitleView;

    private SendMassgeHandler mMainHandler = null;
    private LoginThread mLoginThread = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 메인 핸들러 생성
        mMainHandler = new SendMassgeHandler();

        TitleView = findViewById(R.id.appTitleLoadingView);
        editTextId = findViewById(R.id.editTextId);
        editTextPassword = findViewById(R.id.editTextPassword);
        LoadingView = findViewById(R.id.loginLoadingView);
        signupButton = findViewById(R.id.button1);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });

        TitleView.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TitleView.setVisibility(View.GONE);
            }
        }, 3000);

        signinButton = findViewById(R.id.signInButton);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextId.getText().toString().length() == 0 || editTextPassword.getText().toString().length() == 0){
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 입력해주십시오", Toast.LENGTH_LONG).show();
                }
                else {
                    LoadingView.setVisibility(View.VISIBLE);
                    mLoginThread = new LoginThread();
                    mLoginThread.start();
                }
            }
        });

        webButton = findViewById(R.id.webButton);
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url ="https://hyeonjun-93.wixsite.com/receiptdiary";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });



    }

    class LoginThread extends Thread implements Runnable {

        public LoginThread() {
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

                dout.writeUTF("Login");

                JSONObject user = new JSONObject();   // Use JSON to store needed information
                user.put("id", editTextId.getText().toString());
                user.put("password", editTextPassword.getText().toString());
                dout.writeUTF(user.toString());      // Transmit JSON information to server
                String result = din.readUTF();      // Receive result from user

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

            switch (msg.what) {
                case 1:
                    IDNAME = editTextId.getText().toString();
                    LoadingView.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), mainPage.class);
                    startActivity(intent);
                    break;

                case 2:
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 확인해주십시오", Toast.LENGTH_LONG).show();
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



