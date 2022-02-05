package model;

public class RegistrationService {
    public RegistrationService() {
    }
    
    public League getLeague(int year, String season)throws ObjectNotFoundException{
        LeagueService leagueSvc = new LeagueService();
        League league = leagueSvc.getLeague(year, season);
        return league;
    }
    
    public void setPlayer(Player player){
        // 建立PlayerDAO物件,將球員資料經PlayerDAO物件寫入資料庫
        PlayerDAO playerDataAccess = new PlayerDAO();
        playerDataAccess.insert(player);
    }
    
    public void register( League league, Player player){        
        RegistrationDAO registrator = new RegistrationDAO();
        registrator.insert(league, player);
    }
}
