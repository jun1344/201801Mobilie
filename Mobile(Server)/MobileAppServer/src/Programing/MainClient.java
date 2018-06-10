package Programing;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MainClient{

	static String userID = "userID";
    static String password = "password";
    static String serverIP = "127.0.0.1";


    public static boolean IDcheck(DataOutputStream dout, DataInputStream din, String userID)
    {
    		JSONObject user = new JSONObject();
    		user.put("userID", userID);
    		try {
				dout.writeUTF(user.toString());
				String result = din.readUTF();
				if (result != "")
	    				return true;
	    			else
	    				return false;
    			} catch (IOException e) {
				// TODO Auto-generated catch block
				return false;
			}

    }

    public static void main(String[] args){
        OutputStream out = null;
        FileInputStream fin;

        try{
        	Socket soc = new Socket(serverIP,11111);
        	System.out.println("Server Connected!");            // 11111 is Server port number
            out =soc.getOutputStream();                         // Create outputstream to socket 
            DataOutputStream dout = new DataOutputStream(out);
            InputStream in = soc.getInputStream();             // Create inputstream to socket
            DataInputStream din = new DataInputStream(in);     
            
            
            Scanner s = new Scanner(System.in);   // Create Scanner instance to receive file name from user
            while(true){
                String command = s.nextLine();   // Receive command from keyboard
                System.out.print(command);
                dout.writeUTF(command);         // Send command to server
            

                if (command.equalsIgnoreCase("Login"))   // If command is login
                {
                   JSONObject user = new JSONObject();   // Use JSON to store needed information
                   user.put("id", "test");
                   user.put("password", "1234");   
                   dout.writeUTF(user.toString());      // Transmit JSON information to server
                   String result = din.readUTF();      // Receive result from user
                   System.out.println(result);
                   if (result.equalsIgnoreCase("Success"))   
                   {
                      userID = "userID";
                      password = "password";
                      System.out.println("Log in success");
                   }else                        // If failed
                   {
                      System.out.println("Log in failed");
                   }
                }
                
                else if (command.equalsIgnoreCase("Signup"))   // If command is sign up
                {
                      JSONObject user = new JSONObject();   // Use JSON to store information which has to be stored at server
                      user.put("id", "test");
                      user.put("password", "1234");
                      dout.writeUTF(user.toString());
                      String result = din.readUTF();
                      if (result.equalsIgnoreCase("Success"))   
                      {
                         userID = "userID";
                         password = "password";
                         System.out.println("Log in success");
                      }else                        // If failed
                      {
                         System.out.println("Log in failed");
                      }
                }
                else if (command.equalsIgnoreCase("IDCHECK"))   // If command is sign up
                {
                      JSONObject user = new JSONObject();   // Use JSON to store information which has to be stored at server
                      user.put("id", "test");
                      user.put("password", "1234");
                      dout.writeUTF(user.toString());
                      String result = din.readUTF();
                      if (result.equalsIgnoreCase("Success"))   
                      {
                         userID = "userID";
                         password = "password";
                         System.out.println("Log in success");
                      }else                        // If failed
                      {
                         System.out.println("Log in failed");
                      }
                }
                else if (command.equalsIgnoreCase("List"))   // If command is sign up
                {
                      JSONObject user = new JSONObject();   // Use JSON to store information which has to be stored at server
          			JSONObject searchKey = new JSONObject();
        			//searchKey.put("keyword", "search");
        			searchKey.put("keyword", "list");
        			searchKey.put("user", "junsuc");
        			dout.writeUTF(searchKey.toString());
        			String inResult = din.readUTF();
        			JSONParser parser = new JSONParser();
        			if(!inResult.equalsIgnoreCase("fail")){
        			JSONObject result = (JSONObject)parser.parse(inResult);
        			JSONArray list = (JSONArray)result.get("list");

        			for(int i=0; i<list.size(); i++)
        			{
        				JSONObject file = (JSONObject)list.get(i);

        				String User = file.get("user").toString();
        				
        				System.out.println(User);

/*
        				FileModel fileModel = new FileModel(UserID, fileID, fileName, type
        						,category, directory, size,
        						date,shareOffset, download, backup);


        				fileList.add(fileModel);*/
        			}
        			}
        			else {
        				System.out.println("fali");
        			}
                }
                else if (command.equalsIgnoreCase("DB"))   // If command is sign up
                {
                    java.util.Date utilDate = new java.util.Date();
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                		int month = utilDate.getMonth();
                		String id = "test1";
                		String R_id = id + String.valueOf(month + 1);
                    	JSONObject user = new JSONObject();   // Use JSON to store information which has to be stored at server
                    	user.put("id",R_id);
                    	user.put("user",id);
                    	user.put("total_score",20);
                    	user.put("people",5);
                    	dout.writeUTF(user.toString());
                    	String result = din.readUTF(); 
                    	System.out.println(result);
                    	
                }
                else if (command.equalsIgnoreCase("score"))   // If command is sign up
                {
                    	JSONObject user = new JSONObject();   // Use JSON to store information which has to be stored at server
                    	user.put("target","junsic115"); //getText·Î ºÒ·¯ 
                    	user.put("user","junsci");
                    	user.put("context","dkdkdkdkdk");
                    	user.put("score",5);
                    	dout.writeUTF(user.toString());
                    	String result = din.readUTF(); 
                    	System.out.println(result);
                    	
                } 
                else if (command.equalsIgnoreCase("Quit"))   // If command is quit command to quit program
                {
	                	out.close();
	                	soc.close();
	                	dout.close();
	                	din.close();
	                	in.close();
	                	System.exit(0);
                }
            }
        }
		                catch(Exception e){
			       }

    }
    
}
