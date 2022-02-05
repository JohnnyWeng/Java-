package model;

// 引入Utility 類別
import java.util.List;

public class LeagueService {
    
    // 宣告服務元件所使用之 LeagueDAO 物件
    private LeagueDAO leagueDataAccess;
    
    // 初始化 LeagueDAO 物件
    public LeagueService() {
        leagueDataAccess = new LeagueDAO(); 
    }
    
    // 取得所有聯盟.使用 LeagueDAO 的 retrieveAll() 方法
    public List<League> getAllLeagues() {
        return leagueDataAccess.retrieveAll(); 
    }
 
    
    public League getLeague(int year, String season) throws ObjectNotFoundException {
        return leagueDataAccess.retrieve(year, season);
    }
    
    // 新增聯盟至資料庫
    public League createLeague(int year, String season, String title) {
        // 建立聯盟物件(League.java), ObjectID 未知,以 -1 帶入
        League league = new League(-1, year, season, title);
        // 新增聯盟至資料庫
        leagueDataAccess.insert(league);
        return league;
    }    
}
