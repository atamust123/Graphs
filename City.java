
import java.util.LinkedList;

public class City {

    private String cityName;
    private int cityCounter;
    private LinkedList<Integer> railway;
    private LinkedList<Integer> highway;
    private LinkedList<Integer> airway;

    public City(String cityName, int cityCounter) {
        railway = new LinkedList<>();
        highway = new LinkedList<>();
        airway = new LinkedList<>();
        this.cityName = cityName;
        this.cityCounter = cityCounter;
        
    }
    

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCounter() {
        return cityCounter;
    }

    public void setCityCounter(int cityCounter) {
        this.cityCounter = cityCounter;
    }

    public LinkedList<Integer> getRailway() {
        return railway;
    }

    public void setRailway(int adjacency) {
        this.railway.add(adjacency);
    }

    public LinkedList<Integer> getHighway() {
        return highway;
    }

    public void setHighway(int adjacency) {
        this.highway.add(adjacency);
    }

    public LinkedList<Integer> getAirway() {
        return airway;
    }

    public void setAirway(int adjacency) {
        this.airway.add(adjacency);
    }

    public void setRailway(LinkedList<Integer> railway) {
        this.railway = railway;
    }

    public void setHighway(LinkedList<Integer> highway) {
        this.highway = highway;
    }

    public void setAirway(LinkedList<Integer> airway) {
        this.airway = airway;
    }

}
