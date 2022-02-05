package web;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InitializeLeagues implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String[] SEASONS = {"Spring","Summer","Fall","Winter"};
        String seasonStr = context.getInitParameter("season-list");
        if((seasonStr != null) || (seasonStr.length()!=0))
            SEASONS = seasonStr.split(", ");
        context.setAttribute("seasonList", SEASONS);
        context.log("The season list has been loaded.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.log("The Context will be destroyed.");
    }
    
}
