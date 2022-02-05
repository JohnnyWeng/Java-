package model;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;


class PlayerDAO {
    PlayerDAO() {         
    }
    private static final String INSERT_STMT = "INSERT INTO Player (PID, name, address, city, province, postal_code)  VALUES (?, ?, ?, ?, ?, ?)";
    
    void insert(Player player) {
        // JDBC變數
        DataSource ds = null;
        Connection connection = null;
        PreparedStatement insert_stmt = null;
        ResultSet results = null;
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
            // 建立一個 INSERT敘述
            insert_stmt = connection.prepareStatement(INSERT_STMT);
            // 取得下一個 Player 物件的 object ID
            int playerID = ObjectIdDAO.getNextObjectID(ObjectIdDAO.PLAYER, connection);
            // 將取得之 object ID 加入INSERT敘述的第一個欄位
            insert_stmt.setInt(1, playerID);
            // 將球員屬性資料加入INSERT敘述的對應欄位
            insert_stmt.setString(2, player.getName());
            insert_stmt.setString(3, player.getAddress());
            insert_stmt.setString(4, player.getCity());
            insert_stmt.setString(5, player.getProvince());
            insert_stmt.setString(6, player.getPostalCode());
            // 執行INSERT敘述
            insert_stmt.executeUpdate();
            // 設定球員物件的 object ID
            player.playerID = playerID;            
        // 例外處理及釋放 JDBC 資源
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
            if ( insert_stmt != null ) {
                try { insert_stmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            }
            if ( connection != null ) {
                try { connection.close(); } catch (Exception e) { e.printStackTrace(System.err); }
            }
        } // END of try-catch-finally block
    }
}