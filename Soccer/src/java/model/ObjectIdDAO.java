package model;
// SQL imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

final class ObjectIdDAO { //  final static = const
    public static final String LEAGUE = "League"; 
    public static final String PLAYER = "Player";
    private ObjectIdDAO() { // 無參數建構子       
    }    
    // ID_number: 是 schema.sql 的 id數字(Integer)
    private static final String NEXT_ID_QUERY = "SELECT ID_number FROM ObjectIDs WHERE table_name=? " ; // 取得 id prepareStatement
    private static final String UPDATE_ID_CMD = "UPDATE ObjectIDs SET ID_number=? WHERE table_name=?" ; // 更新 id prepareStatement

    public static int getNextObjectID(String objectClassName, Connection connection) { // 方法
        // JDBC變數
        PreparedStatement query_stmt = null;
        PreparedStatement incr_stmt = null;
        ResultSet result = null; 
        int id;
        try {
            // 建立一個 SELECT 敘述,初始化該敘述並執行查詢動作, 利用 connection 取得 statement
            query_stmt = connection.prepareStatement(NEXT_ID_QUERY);
            query_stmt.setString(1, objectClassName); 
            result = query_stmt.executeQuery(); 
            // .next(): is used to move the cursor to the one row next from the current position. boolean. 如果是 true, 那就執行下面的
            if ( result.next() ) {
                id = result.getInt("ID_number"); // 取得 id
                incr_stmt = connection.prepareStatement(UPDATE_ID_CMD);
                incr_stmt.setInt(1, id + 1); 
                // 把第二個 String 值, 改成 League
                incr_stmt.setString(2, objectClassName); 
                //  is used to execute specified query, it may be create, drop, insert, update, delete etc.
                incr_stmt.executeUpdate();
            } else {
                throw new RuntimeException("No ObjectID entry for class type: " + objectClassName);
            }
        //例外處理及釋放 JDBC 資源
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            if ( result != null ) {
                try { result.close(); } catch (SQLException se) {}
            }
            if ( query_stmt != null ) {
                try { query_stmt.close(); } catch (SQLException se) {}
            }
            if ( incr_stmt != null ) {
                try { incr_stmt.close(); } catch (SQLException se) {}
            }
        }
        return id;
    }
}