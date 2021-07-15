
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public class TransportationFileReader {

    public TransportationFileReader(LinkedList<City> cities,FileReader f) throws FileNotFoundException {
        Scanner sc = new Scanner(f);
        City city = null;
        int counter = 0;
        String type = null;
        int same=0;
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.length() > 0) {
                String array[] = s.split(" ");
                if (array.length == 1) {
                    type = s;
                    //counter = 0;
                } else {
                    String cityName = array[0];
                    city = new City(cityName, counter);
                    
                    if (cities.size()>0 && cities.getFirst().getCityName().equals(cityName)){
                        counter=0;
                        same++;
                    }

                    if (type.equals("Airway")) {
                        int i = 0;
                        while (i < array[1].length()) {
                            if (array[1].charAt(i)=='1'){
                                city.setAirway(i);
                            }
                            
                            i++;
                        }

                    } else if (type.equals("Highway")) {

                        int i = 0;
                        while (i < array[1].length()) {
                            if (array[1].charAt(i)=='1'){
                                city.setHighway(i);
                            }
                            i++;
                        }

                    } else if (type.equals("Railway")) {
                        int i = 0;
                        while (i < array[1].length()) {
                            if (array[1].charAt(i)=='1'){
                                city.setRailway(i);
                            }
                            i++;
                        }
                        
                    }
                    if (same==0) {
                        cities.add(city);
                    } else {
                        if (city.getAirway().isEmpty() == false) {
                            cities.get(counter ).setAirway(city.getAirway());
                        } else if (city.getHighway().isEmpty() == false) {
                            cities.get(counter).setHighway(city.getHighway());
                        } else if (city.getRailway().isEmpty() == false) {
                            cities.get(counter).setRailway(city.getRailway());
                        }
                    }
                    counter++;

                }
            }
        }
    }

}
