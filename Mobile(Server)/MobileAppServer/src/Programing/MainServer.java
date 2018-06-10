package Programing;

import java.io.DataInputStream;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.sql.Date;
 
public class MainServer {
	
	public static DBconnection db = new DBconnection();		// Create instance to connect with MYSQL DB
	public static int portList[] = new int[70000];			// To manage used port and unused port, 1 is used port and 0 is unused port
    static Socket client = new Socket();						// Socket instance which communicate with client
	private static FileInputStream fin;						// Inputstream read from socket
	
    public static void main(String[] args) throws Exception{
        ServerSocket soc = new ServerSocket(11111);  // Open server socket with port number 11111
        portList[11111] = 1;							// Now port 11111 is used so change to 1
        System.out.println("Server Start");
        while(true)
        {
	        client = soc.accept(); 					// Wait and accept client connection request
	        portList[client.getPort()] = 1;			// Now new socket is created to communicate with socket so change port number of it's socket to 1
	        System.out.println("client accept!");
	        new Updown(client).start();				// Each client request is handled in each thread
        }
        
    }
    
    private static class Updown extends Thread
    {
    		private InputStream in = null;				
    		private FileOutputStream out = null;
    		private DataInputStream din = null;
    		private OutputStream s_out = null;
    		private DataOutputStream dout = null;
    		private Socket client = null;
    		
    		private ServerSocket data = null;					// If client request upload or download file, then this thread create new child thread
    		private Socket sock = null;
    		private int port = new Random().nextInt(60000)+1000;	// Server socket's port number is randomly decided
    		
    		public Updown(Socket client)
    		{
    			this.client = client;							// Receive socket instance from parent to communicate with client
    		}
        
    		
        public void run()
        {
        	
        	try {
        		
        	in = client.getInputStream();               
		    din = new DataInputStream(in);  
		    s_out = client.getOutputStream();    
		    dout = new DataOutputStream(s_out); 
        		
	        while(true){
	        		String command = din.readUTF().toString();				// Receive command from client
	        		System.out.print(command);
	        		
	        		if (command.equalsIgnoreCase("UPLOAD") || command.equalsIgnoreCase("DOWNLOAD"))
	        		{

	        			while (portList[port] == 1)
	        			{
	    		    		port = new Random().nextInt(60000)+1000;
	        			}
	        			
	        			// Create server socket with port number randomly selected
	        			this.data = new ServerSocket(this.port);
	        		    dout.writeInt(this.port);				// Notice this port number to client.
	        			sock = data.accept();
	        			portList[port] = 1;
	        			portList[sock.getPort()] = 1;
	        			System.out.flush();
	        			try {
/*	        				new UpdownData(sock, command).run();*/
	        			} finally
	        			{
	        				portList[sock.getPort()] = 0;
	        				portList[port] = 0;
	        				sock.close();
	        				data.close();
	        			}
	        		}
	        		
	        		else if (command.equalsIgnoreCase("SIGNUP"))
	        		{
	        			// If command is signup
	        			// Create JSON objects to parse information from message which client send
	        			JSONParser parser = new JSONParser();
	        			JSONObject user = (JSONObject)parser.parse(din.readUTF());
	        			
	        			if(db.idCheck(user.get("id").toString()))
	        			{
	        				db.insertUser(user.get("id").toString(), user.get("password").toString());
	        				dout.writeUTF("Success");
	        			}else
	        			{
	        				dout.writeUTF("Fail");
	        			}
	        		}
	        		else if (command.equalsIgnoreCase("LIST"))
	        		{
	        			// If command is search
	        			// Create JSON objects to parse information from message which client send
	        			JSONParser parser = new JSONParser();
	        			JSONObject searchKey = (JSONObject)parser.parse(din.readUTF());
	        			
	        			// Create JSON objects to store result information
	        			JSONArray result = new JSONArray();
	        			JSONObject output = new JSONObject();
	        			
	        			// Search file table with information which is parsed from message which client send
	        			// Put the result of searching to JSON object and send it to client
	        			String list[] = db.loadList(searchKey.get("keyword").toString(), searchKey.get("user").toString()).split("li23il");
	        			for(int i=0; i<list.length; i++)
	        			{
	        				JSONObject tmpJSON = new JSONObject();
	        				String tmp[] = list[i].split("li72il");
//	        				System.out.println(db.searchFile(searchKey.get("keyword").toString(), searchKey.get("std").toString(), searchKey.get("range").toString()));
	        				tmpJSON.put("id", tmp[0]);
	        				tmpJSON.put("user", tmp[1]);
	        				tmpJSON.put("total_score", tmp[2]);
	        				tmpJSON.put("people", tmp[3]);
	        				tmpJSON.put("Makedate", tmp[4]);
	        				tmpJSON.put("sobi1", tmp[5]);
	        				tmpJSON.put("sobi2", tmp[6]);
	        				tmpJSON.put("sobi3", tmp[7]);
	        				tmpJSON.put("prePri", tmp[8]);
	        				tmpJSON.put("use", tmp[9]);
	        				tmpJSON.put("type", tmp[10]);
	        				result.add(tmpJSON);
	        			}
	        			output.put("list", result);
	        			System.out.println(result);
	        			dout.writeUTF(output.toString());
	        		}
	        		else if (command.equalsIgnoreCase("score"))
	        		{
	        			JSONParser parser = new JSONParser();
	        			JSONObject user = (JSONObject)parser.parse(din.readUTF());
	        			System.out.println(user);
	        			if(db.scoreReceipt(user.get("target").toString(), 
	        					user.get("user").toString(),
	        					user.get("context").toString(),
	        					Integer.valueOf(user.get("score").toString())))
	        			{
	        				dout.writeUTF("Success");
	        			}else
	        			{
	        				dout.writeUTF("Fail");
	        			}
	        		}
	        		else if (command.equalsIgnoreCase("LOGIN"))
	        		{
	        			JSONParser parser = new JSONParser();
	        			JSONObject user = (JSONObject)parser.parse(din.readUTF());
	        			
	        			if(db.login(user.get("id").toString(), user.get("password").toString()))
	        			{
	        				dout.writeUTF("Success");
	        			}else
	        			{
	        				dout.writeUTF("Fail");
	        			}
	        		}
	        		else if (command.equalsIgnoreCase("IDCHECK"))
	        		{
	        			JSONParser parser = new JSONParser();
	        			JSONObject user = (JSONObject)parser.parse(din.readUTF());
	        			
	        			if(db.idCheck(user.get("id").toString()))
	        			{
	        				dout.writeUTF("Success");
	        			}else
	        			{
	        				dout.writeUTF("Fail");
	        			}
	        		}
	        		else if (command.equalsIgnoreCase("DB"))
	        		{
	        			JSONParser parser = new JSONParser();
	        			JSONObject user = (JSONObject)parser.parse(din.readUTF());
	        			//id,user,score,total,update)
	        			if(!(db.searchTables(user.get("id").toString()))){
	        				System.out.println(db.makeTable(user.get("id").toString(),
		        					user.get("user").toString(), Integer.valueOf(user.get("score").toString()),
		        					Integer.valueOf(user.get("total").toString())));
	        				dout.writeUTF("Success");
	        			}
	        			else {
	        				dout.writeUTF("Same Table exist");
	        			}
	        		}
	        		
	        		else if (command.equalsIgnoreCase("QUIT"))
	        		{
	        			// If command is quit, close all stream and socket
	        			in.close();
	        			din.close();
	        			out.close();
	        			dout.close();
	        			s_out.close();
	        			this.client.close();
	        			break;
	        		}
	        }
        	} catch (Exception e)
        	{
        		System.out.println("Server(thread1) Error : " + e);
        		try {
					dout.writeUTF("Fail");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
        	}
        }
    }
 }
    