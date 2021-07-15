
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.LinkedList;

public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException  {
        LinkedList<City> cities = new LinkedList<>();
        System.setOut(new PrintStream(new FileOutputStream(args[2])));
        new TransportationFileReader(cities,new FileReader(args[0]));
        new Queries(cities,args[1]);
    }

}
