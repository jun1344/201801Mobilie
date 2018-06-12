package Programing;

import java.sql.*;

/**
* DBconnection을합니다. 
*/
public class DBconnection {
	

	/**
	* 커넥셔을초기설정을 합니다. 
	* @return Connection을 반환합니다. 
	*/
	public  Connection init() throws ClassNotFoundException, SQLException {
		
        Connection connection = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC" , "root", "1204");
        
        return connection;
	}

	/**
	* 유저를 추가 합니다 .
	* @return 함수가 제대로 작동하는지 참과 거짓을 나타내는 Boolean값을 return 합니다.
	*/
	//회원가입 할때의 DB함수
	public  boolean insertUser(String id, String password) throws ClassNotFoundException, SQLException{
         
         Connection con = init();
         String sql = "insert into user(id,password) values(?,?)";
         PreparedStatement pst = con.prepareStatement(sql);
         
         pst.setString(1, id);
         pst.setString(2, password);
         
         int res = pst.executeUpdate();
         if(res>0){
            return true;
         }
         else
            return false;
         
      }
	
	/**
	* 유저를 삭제합니다.
	* @return 함수가 제대로 작동하는지 참과 거짓을 나타내는 Boolean값을 return 합니다.
	*/	
	//회원탈퇴 할때의 DB함수
	public  boolean deleteUser(String id) throws ClassNotFoundException, SQLException{
        
        Connection con = init();
        String sql = "delete from user where id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        
        pst.setString(1, id);
        
        int res = pst.executeUpdate();
        
        if(res>0){
            return true;
         }
         else
            return false;
     }
	
	/**
	* 유저를 업데이트 합니다..
	* @param id 유저의 id를 나타냅니다.
	* @param score 유저의 점수를 나타냅니다.
	* @return 함수가 제대로 작동하는지 참과 거짓을 나타내는 Boolean값을 return 합니다.
	*/
	public  boolean updateScore(String id, int score) throws ClassNotFoundException, SQLException{
        
        Connection con = init();
        String sql = "update user set id = ?, score = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        
        pst.setString(1, id);
        pst.setInt(2, score);
        
        int res = pst.executeUpdate();
        if(res>0){
           return true;
        }
        else
           return false;
     }

	/**
	* 비밀번호를 바꾸게 합니다.
	* @param id 유저의 id를 나타냅니다.
	* @param password 유저의 비밀번호를 나타냅니다.
	* @return 함수가 제대로 작동하는지 참과 거짓을 나타내는 Boolean값을 return 합니다.
	*/
	public  boolean updatePassword(String id, String password) throws ClassNotFoundException, SQLException{
        
        Connection con = init();
        String sql = "update user set id = ?, password = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        
        pst.setString(1, id);
        pst.setString(2, password);
        
        int res = pst.executeUpdate();
        if(res>0){
           return true;
        }
        else
           return false;
     }
	
	/**
	* 유저를 업데이트 합니다..
	* @param id 유저의 id를 나타냅니다.
	* @param password 유저의 비밀번호를 나타냅니다.
	* @return 함수가 제대로 작동하는지 참과 거짓을 나타내는 Boolean값을 return 합니다.
	*/
	public  boolean login(String id, String password) throws ClassNotFoundException, SQLException{
        Connection con = init();
        String sql = "SELECT * FROM user WHERE id = '" + id + "'";
        
        String result = "";
        ResultSet rs = null;
        Statement st = null;
        
        st = con.createStatement();
        rs = st.executeQuery(sql);
        
        while(rs.next())
        {
        	result = rs.getString("password");
        }
        
        if(result.equals(password)){
            return true;
         }
         else
            return false;
	}
	
	//회원가입 할때 아이디 중복확인 하는거 
	//중복아니면 true
	//중복이면 false
	public  boolean idCheck(String id) throws ClassNotFoundException, SQLException{
        Connection con = init();
        String sql = "SELECT * FROM user WHERE id = '" + id + "'";
        
        String result = "";
        ResultSet rs = null;
        Statement st = null;
        int isSelect = 0; 
        st = con.createStatement();
        rs = st.executeQuery(sql);
        
        while(rs.next())
        {
        	isSelect++;
        }
        
        if(isSelect > 0){
            return false;
         }
         else
            return true;
	}
	
	/**
	* 유저 점수를 체크합니다. 
	* @param id 유저의 id를 나타냅니다.
	* @return 함수가 제대로 작동하는지 참과 거짓을 나타내는 Boolean값을 return 합니다.
	*/
	public String idScoreCheck(String target, String user) throws ClassNotFoundException, SQLException{
        Connection con = init();
        String sql = "SELECT * FROM "+target+"_s WHERE user = '" + user + "'";
        
        String result = "";
        ResultSet rs = null;
        Statement st = null;
        int isSelect = 0; 
        st = con.createStatement();
        rs = st.executeQuery(sql);
        
        while(rs.next())
        {
        	isSelect++;
        }
        
        if(isSelect > 0){
            return "exist";
         }
         else
            return "pass";
	}
	
	/**
	* 유저 가계부 테이블을 검색합니다. 
	* @param id 유저의 id를 나타냅니다.
	* @return 함수가 제대로 작동하는지 참과 거짓을 나타내는 Boolean값을 return 합니다.
	*/
	public boolean searchTables(String id) throws ClassNotFoundException, SQLException{
        
		boolean isCheck = false;
		
		Connection con = init(); 
        String sql = "SHOW tables IN test";
        
        String result = "";
        ResultSet rs = null;
        Statement st = null;
        
        st = con.createStatement();
        rs = st.executeQuery(sql);
        
        while(rs.next())
        {
        	result = rs.getString(1);
        	isCheck = result.equalsIgnoreCase(id);
        }
        
        return isCheck;
	}
	/**
	* 유저 가계부 테이블을 만듭니다.
	* @param id 테이블 id를 나타냅니다. 
	* @param score 점수를 나타냅니다.
	* @param total 총점을 나타냅니다.
	* @param user 유져 아이디를 나타냅니다. 
	* @return 함수가 제대로 작동하는지 참과 거짓을 나타내는 Boolean값을 return 합니다.
	*/
	public boolean makeTable(String id, String user, Integer score, Integer total) throws ClassNotFoundException, SQLException{

        Connection con = init();
        String sql = "insert into receipt(id,user,score,total) values(?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
       
        makeReceipt(id);
        
        pst.setString(1, id);
        pst.setString(2, user);
        pst.setInt(3, score);
        pst.setInt(4, total);
        
        //makeReceipt(id);
        
        int res = pst.executeUpdate();
        if(res>0){
           return true;
        }
        else
           return false;
        
        
	}
	/**
	* 유저 가계부 리스트를 불러옵니다. 
	* @param id 테이블 id를 나타냅니다. 
	* @param score 점수를 나타냅니다.
	* @param total 총점을 나타냅니다.
	* @param user 유져 아이디를 나타냅니다. 
	* @return 함수가 제대로 작동하는지 참과 거짓을 나타내는 Boolean값을 return 합니다.
	*/
	public String loadList(String keyword, String user) throws ClassNotFoundException, SQLException{
        
		Connection con = init();
		String sql = "";
		if (keyword.equals("search"))
        {
              sql = "SELECT * FROM receipt WHERE user = '" + user  +"' and type = 1 ";
        }else if(keyword.equals("list"))
        {
              sql = "SELECT * FROM receipt WHERE type = 1 order by rand() limit 2";
        }
        
        ResultSet rs = null;
        Statement st = null;
        String result = "";
        
        st = con.createStatement();
        rs = st.executeQuery(sql);
        
        while(rs.next())
        {
              result += "li23il";
              result += rs.getString(1)+"li72il";
              result += rs.getString(2)+"li72il";
              result += rs.getInt(3)+"li72il";
              result += rs.getInt(4)+"li72il";
              result += rs.getTimestamp(5)+"li72il";
              result += rs.getInt(6)+"li72il";
              result += rs.getInt(7)+"li72il";
              result += rs.getInt(8)+"li72il";
              result += rs.getInt(9)+"li72il";
              result += rs.getInt(10)+"li72il";
              result += rs.getInt(11);
        }
                       
        if (result.length() == 0)
        {
           return "";
        }
        
        result = result.substring(5, result.length());
        
        if(st != null) st.close();
        if(con != null) con.close();
        
        return result;
	}
	/**
	* 유저 가계부를 평가합니다 .
	* @param id 테이블 id를 나타냅니다. 
	* @param score 점수를 나타냅니다.
	* @param total 총점을 나타냅니다.
	* @return 함수가 제대로 작동하는지 참과 거짓을 나타내는 Boolean값을 return 합니다.
	*/
	public boolean scoreReceipt(String target, String id,String context, int score) throws ClassNotFoundException, SQLException{
		
		Connection con = init();
		if(idScoreCheck(target, id).equalsIgnoreCase("pass")) {
        String sql = "insert into " + target +"_s(user,context,score) values(?, ?, ?)";
        
        PreparedStatement pst = con.prepareStatement(sql);
       
        makeReceipt(id);
        
        pst.setString(1, id);
        pst.setString(2, context);
        pst.setInt(3, score);
        
        int res = pst.executeUpdate();
        return true;
		}
		else {
			return false;
		}
	}
	public void reloadTable() throws ClassNotFoundException, SQLException{
		
	}
	
	public boolean makeReceipt(String id) throws ClassNotFoundException, SQLException{
		
		Connection con = init();
        String sql = "CREATE TABLE " + id + "(" + 
        		" id INT NOT NULL AUTO_INCREMENT," + 
        		" type INT NOT NULL DEFAULT 0," + 
        		" name VARCHAR(45) NOT NULL," + 
        		" cost INT NOT NULL DEFAULT 0," + 
        		" date DATE NOT NULL," + 
        		"  PRIMARY KEY (`id`))";
        PreparedStatement pst = con.prepareStatement(sql);
        
        int res = pst.executeUpdate();

/*        sql = "CREATE TABLE " + id + "_s(" + 
        		" id INT NOT NULL AUTO_INCREMENT," +  
        		" user VARCHAR(45) NOT NULL," + 
        		" context VARCHAR(45) NOT NULL," + 
        		" score INT(11) NOT NULL DEFAULT 0," + 
        		" time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," + 
        		"  PRIMARY KEY (`id`))";
        pst = con.prepareStatement(sql);
        	
        res = pst.executeUpdate();*/

        return true;
	}
	/**
	* 유저 가계부 테이블에 데이터를  만듭니다.
	* @param id 테이블 id를 나타냅니다. 
	* @param score 점수를 나타냅니다.
	* @param total 총점을 나타냅니다.
	* @param user 유져 아이디를 나타냅니다. 
	* @return 함수가 제대로 작동하는지 참과 거짓을 나타내는 Boolean값을 return 합니다.
	*/
	public boolean insertReceipt(String id, String user, Integer score, Integer total) throws ClassNotFoundException, SQLException{

        Connection con = init();
        String sql = "insert into ?(user,score,total) values(?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
       
        makeReceipt(id);
        
        pst.setString(1, id);
        pst.setString(2, user);
        pst.setInt(3, score);
        pst.setInt(4, total);
        
        //makeReceipt(id);
        
        int res = pst.executeUpdate();
        if(res>0){
           return true;
        }
        else
           return false;
        
        
	}

}
