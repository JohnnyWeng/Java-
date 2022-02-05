package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// Connection pool 使用
import javax.sql.DataSource; // 將資料庫相關資料(資料庫URL, JDBC Driver, 使用者帳號, 密碼) 封裝成 DataSource

// import javax.naming.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

// import java.util.*;
import java.util.List;
import java.util.LinkedList;

public class LeagueDAO {

    public LeagueDAO() {
    }
    
    private static final String RETRIEVE_STMT = "SELECT * FROM League WHERE yearno=? AND season=?";

//   --------------------------------------------------------------------------------------------------------------------------------
    
    League retrieve(int year, String season) throws ObjectNotFoundException {
        // JDBC變數
        DataSource ds = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet results = null;
        int num_of_rows = 0;
        // 領域物件變數
        League league = null;
        try {
            // 從JNDI 中取得DataSource
            Context ctx = new InitialContext();
            if ( ctx == null ) {
                throw new RuntimeException("JNDI Context could not be found.");
            }
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/leagueDB");
            if ( ds == null ) {
                throw new RuntimeException("DataSource could not be found.");
            }
            // 取得資料庫連線
            connection = ds.getConnection();
            // 建立一個SELECT 敘述
            stmt = connection.prepareStatement(RETRIEVE_STMT);
            // 初始化該敘述並執行查詢動作
            stmt.setInt(1, year);
            stmt.setString(2, season);
            results = stmt.executeQuery();
            // 迭代查詢的結果
            while ( results.next() ) {
                int objectID = results.getInt("LID");
                num_of_rows++;
                if ( num_of_rows > 1 ) {
                    throw new SQLException("Too many rows were returned.");
                }
                // 建立並填寫League 物件中的資料
                league = new League(objectID, results.getInt("yearno"), results.getString("season"), results.getString("title"));
            }
            if ( league != null ) {
                return league;
            } else {
                throw new ObjectNotFoundException();
            }
        // 處理 SQL 錯誤
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        // 處理 JNDI 錯誤
        } catch (NamingException ne) {
            throw new RuntimeException("A JNDI error occured. " + ne.getMessage());
        // 釋放 JDBC 資源
        } finally {
            if ( results != null ) {
                try { results.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            }
            if ( stmt != null ) {
                try { stmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            }
            if ( connection != null ) {
                try { connection.close(); } catch (Exception e) { e.printStackTrace(System.err); }
            }
        } // END of try-catch-finally block
    } // END of the retrieve method
    

    private static final String RETRIEVE_ALL_STMT = "SELECT * FROM League" ; // Prepared Statement (預編程式):把資料預先放進 statement
    
    List<League> retrieveAll() { 
        DataSource ds = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet results = null;
        
        // 領域物件變數: 宣告一個 LinkedList 
        List<League>  leagueList = new LinkedList<League> ();
        // 建立一個 "空的物件"
        League league = null;
        try {
            // "取得 JNDI 物件"
            Context ctx = new InitialContext();
            // 如果是空的就丟出例外,無法找到 JNDI
            if ( ctx == null ) {
                throw new RuntimeException("JNDI Context could not be found."); // 無法找到 JNDI
            }
            // 由 JNDI 物件搜尋 DataSource 資源
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/leagueDB"); 
            if ( ds == null ) {
                throw new RuntimeException("DataSource could not be found."); // DataSource 不存在
            }
            
            // Connection -> PreparedStatement -> ResultSet (Execute Query)
            connection = ds.getConnection(); 
            // 建立一個 SELECT 敘述 
            stmt = connection.prepareStatement(RETRIEVE_ALL_STMT); 
            // 執行查詢動作
            results = stmt.executeQuery(); // 取得結果是一個 ResultSet, 執行 statement
            
            // 迭代查詢的結果
            while ( results.next() ) { 
                int objectID = results.getInt("LID");
                league = new League(objectID, results.getInt("yearno"), 
                        results.getString("season"), results.getString("title"));
                leagueList.add(league);
            }
            // 傳回包含所有聯盟的集合, 也就是傳回 leagueList 給 jsp 顯示         
            return leagueList; 
    
        // 例外處理及釋放 JDBC 資源
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } catch (NamingException ne) {
            throw new RuntimeException("A JNDI error occured. " + ne.getMessage());
        } finally {
            if ( results != null ) {
                try { results.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            }
            if ( stmt != null ) {
                try { stmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            }
            if ( connection != null ) {
                try { connection.close(); } catch (Exception e) { e.printStackTrace(System.err); }
            }
        } // END of try-catch-finally block
    }
    
    
    
    // 我們需要四個資料來 insert into league 
    private static final String INSERT_STMT = "INSERT INTO League (LID, yearno, season, title)  VALUES (?, ?, ?, ?)";
    // 給我聯盟物件來新增聯盟
    void insert(League league) {
        // JDBC變數
        DataSource ds = null;
        Connection connection = null;
        PreparedStatement insert_stmt = null;
        ResultSet results = null;
        try {
            // 從JNDI 中取得 DataSource
            Context ctx = new InitialContext();
            if ( ctx == null ) {
                throw new RuntimeException("JNDI Context could not be found.");
            }
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/leagueDB");
            if ( ds == null ) {
                throw new RuntimeException("DataSource could not be found.");
            }
            // 利用 DataSource 取得資料庫連線
            connection = ds.getConnection();
            // 建立一個 INSERT 敘述
            insert_stmt = connection.prepareStatement(INSERT_STMT); 
            
            // 取得下一個 League 物件的 object ID
            int leagueID = ObjectIdDAO.getNextObjectID(ObjectIdDAO.LEAGUE, connection);
            insert_stmt.setInt(1, leagueID);
            // 將聯盟屬性資料加入INSERT敘述的對應欄位
            insert_stmt.setInt(2, league.year);
            insert_stmt.setString(3, league.season);
            insert_stmt.setString(4, league.title);
            // 執行 INSERT 敘述
            insert_stmt.executeUpdate();         
            
            // 修改聯盟物件的 object ID
            league.objectID = leagueID;
        // 例外處理及釋放 JDBC 資源
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } catch (NamingException ne) { // 子类的名称描述錯誤
            throw new RuntimeException("A JNDI error occured. " + ne.getMessage());
        } finally {
            if ( results != null ) {
                try { results.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            }
            if ( insert_stmt != null ) {
                try { insert_stmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            }
            if ( connection != null ) {
                try { connection.close(); } catch (Exception e) { e.printStackTrace(System.err); }
            }
        } // END of try-catch-finally block
    }
}
