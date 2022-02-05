package model;

public class League {
    int objectID;
    int year;
    String season;
    String title;

    public League(int objectID, int year, String season, String title) {
        this.objectID = objectID;
        this.year = year;
        this.season = season;
        this.title = title;
    }

    public int getYear() {
        return year;
    }
    public String getSeason() {
        return season;
    }
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
    
    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if ( o instanceof League ) {
            League l = (League) o;
            result = (this.objectID == l.objectID);
        }
        return result;
    }

    @Override
    public int hashCode() {
        Integer OID = new Integer(objectID);
        return OID.hashCode();
    }

}
