package model;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;

public class RegistrationDAO {

    public RegistrationDAO() {
    }
    
    private static final String INSERT_STMT = "INSERT INTO Registration (LID, PID)  VALUES (?, ?)";
    
    void insert(League league, Player player) {
        // JDBC變數
        DataSource ds = null;
        Connection connection = null;
        PreparedStatement insert_stmt = null;
        ResultSet results = null;
        try {
            // 從 JNDI 中取得DataSource
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
            insert_stmt = connection.prepareStatement(INSERT_STMT);
            insert_stmt.setInt(1, league.objectID);
            insert_stmt.setInt(2, player.playerID);
            // 執行INSERT敘述
            insert_stmt.executeUpdate();      
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
