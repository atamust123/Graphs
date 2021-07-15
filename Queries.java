
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Queries {

    public Queries(LinkedList<City> cities,String query) throws FileNotFoundException {
        ListIterator<City> iter = cities.listIterator();
        City c = null;
        Scanner sc = new Scanner(new FileReader(query));
        int s = 0;
        int d = 0;
        while (sc.hasNext()) {
            String array[] = sc.nextLine().split(" ");
            for (int i = 0; i < array.length; i++) {
                if (i == array.length - 1) {
                    System.out.println(array[i]);
                } else {
                    System.out.print(array[i] + ", ");
                }
            }
            switch (array[0]) {
                case "Q1":
                    int limit = Integer.parseInt(array[3]);
                    String type = array[4];
                    while (iter.hasNext()) {
                        c = iter.next();
                        if (c.getCityName().equals(array[1])) {
                            s = c.getCityCounter();
                        } else if (c.getCityName().equals(array[2])) {
                            d = c.getCityCounter();
                        }
                    }
                    Q1(s, d, limit, type, cities);
                    break;
                case "Q2":
                    iter = cities.listIterator();
                    int pass = 1;
                    while (iter.hasNext()) {
                        c = iter.next();
                        if (c.getCityName().equals(array[1])) {
                            s = c.getCityCounter();
                        }
                        if (c.getCityName().equals(array[2])) {
                            d = c.getCityCounter();
                        }
                        if (c.getCityName().equals(array[3])) {
                            pass = c.getCityCounter();
                        }
                    }
                    Q2(s, pass, d, cities);
                    break;
                case "Q3":
                    iter = cities.listIterator();
                    while (iter.hasNext()) {
                        c = iter.next();
                        if (c.getCityName().equals(array[1])) {
                            s = c.getCityCounter();
                        }
                        if (c.getCityName().equals(array[2])) {
                            d = c.getCityCounter();
                        }
                    }
                    type = array[3];
                    Q3(s, d, type.charAt(0), cities);
                    break;
                case "Q4":
                    iter = cities.listIterator();
                    while (iter.hasNext()) {
                        c = iter.next();
                        if (c.getCityName().equals(array[1])) {
                            s = c.getCityCounter();
                        }
                        if (c.getCityName().equals(array[2])) {
                            d = c.getCityCounter();
                        }
                    }
                    int t1=0,
                     t2=0,
                     t3=0;
                    for (int i = 3; i < 6; i++) {
                        if (array[i].charAt(0) == 'A') {
                            t1 = Character.getNumericValue(array[i].charAt(1));
                        } else if (array[i].charAt(0) == 'H') {
                            t2 = Character.getNumericValue(array[i].charAt(1));
                        } else if (array[i].charAt(0) == 'R') {
                            t3 = Character.getNumericValue(array[i].charAt(1));
                        }
                    }
                    Q4(s, d, 'A', t1, 'H', t2, 'R', t3, cities);
                    break;
                case "PRINTGRAPH":
                    printGraph(cities);
                    break;
                default:
                    break;
            }
        }
    }

    public void printGraph(LinkedList<City> cities) {
        for (int i = 0; i < cities.size(); i++) {
            boolean isVisited[] = new boolean[cities.size()];
            System.out.print(cities.get(i).getCityName() + " --> ");
            printAdjacencies(cities.get(i), isVisited);
            for (int j = 0; j < isVisited.length; j++) {
                if (isVisited[j] == true) {
                    System.out.print(cities.get(j).getCityName() + " ");
                }
            }
            System.out.println("");
        }
    }

    private void printAdjacencies(City c, boolean isVisited[]) {
        for (int i : c.getAirway()) {
            isVisited[i] = true;
        }
        for (int i : c.getHighway()) {
            isVisited[i] = true;
        }
        for (int i : c.getRailway()) {
            isVisited[i] = true;
        }
    }

    public void Q4(int s, int d, char type1, int t1, char type2, int t2,
            char type3, int t3, LinkedList<City> cities) {
        boolean[] isVisited = new boolean[cities.size()];
        ArrayList<Integer> path = new ArrayList<>();
        path.add(s);
        LinkedList<Integer> l = null;
        String types = "";
        boolean hasPath = false;
        hasPath = Q4(s, d, type1, t1, type2, t2, type3,
                t3, cities, path, isVisited, l, types, hasPath);
        if (hasPath == false) {
            System.out.println("There is no path");
        }
    }

    private boolean Q4(int s, int d, char type1, int t1Counter, char type2, int t2Counter,
            char type3, int t3Counter, LinkedList<City> cities, ArrayList<Integer> path,
            boolean[] isVisited, LinkedList<Integer> l, String types, boolean hasPath) {
        isVisited[s] = true;
        if (s == d) {

            if (t1Counter == 0 && t2Counter == 0 && t3Counter == 0) {
                hasPath = true;
                for (int i = 0; i < path.size(); i++) {
                    System.out.print(cities.get(path.get(i)).getCityName() + " ");
                    if (i != path.size() - 1) {
                        System.out.print(types.charAt(i) + " ");
                    }
                }
                System.out.println("");
            }

            isVisited[s] = false;
            return hasPath;
        }
        if (t2Counter > 0) {
            l = cities.get(s).getHighway();
            for (int i = 0; i < l.size(); i++) {
                if (!isVisited[l.get(i)]) {
                    path.add(l.get(i));
                    t2Counter--;
                    types += "H";
                    hasPath = Q4(l.get(i), d, type1, t1Counter, type2, t2Counter, type3,
                            t3Counter, cities, path, isVisited, l, types, hasPath);
                    path.remove(new Integer(l.get(i)));
                    types = types.substring(0, types.length() - 1);
                    t2Counter++;

                }
            }
        }
        if (t1Counter > 0) {
            l = cities.get(s).getAirway();
            for (int i = 0; i < l.size(); i++) {
                if (!isVisited[l.get(i)]) {
                    path.add(l.get(i));
                    t1Counter--;
                    types += "A";
                    hasPath = Q4(l.get(i), d, type1, t1Counter, type2, t2Counter, type3,
                            t3Counter, cities, path, isVisited, l, types, hasPath);
                    path.remove(new Integer(l.get(i)));
                    types = types.substring(0, types.length() - 1);
                    t1Counter++;

                }
            }
        }
        if (t3Counter > 0) {
            l = cities.get(s).getRailway();
            for (int i = 0; i < l.size(); i++) {
                if (!isVisited[l.get(i)]) {
                    path.add(l.get(i));
                    t3Counter--;
                    types += "R";
                    hasPath = Q4(l.get(i), d, type1, t1Counter, type2, t2Counter, type3,
                            t3Counter, cities, path, isVisited, l, types, hasPath);
                    path.remove(new Integer(l.get(i)));
                    types = types.substring(0, types.length() - 1);
                    t3Counter++;

                }
            }
        }
        isVisited[s] = false;
        return hasPath;
    }

    public void Q3(int s, int d, char type, LinkedList<City> cities) {
        boolean[] isVisited = new boolean[cities.size()];
        ArrayList<Integer> path = new ArrayList<>();
        path.add(s);
        LinkedList<Integer> l = null;
        String types = "";
        boolean hasPath = false;
        hasPath = Q3(s, d, type, cities, path, isVisited, l, types, hasPath);
        if (hasPath == false) {
            System.out.println("There is no path");
        }
    }

    private boolean Q3(int s, int d, char type, LinkedList<City> cities,
            ArrayList<Integer> path, boolean[] isVisited, LinkedList<Integer> l,
            String types, boolean hasPath) {
        isVisited[s] = true;
        if (s == d) {
            boolean areSameTypes = true;
            for (int i = 0; i < types.length(); i++) {
                if (types.charAt(i) != type) {
                    areSameTypes = false;
                    break;
                }
            }
            if (areSameTypes && types.length() > 0) {
                for (int i = 0; i < path.size(); i++) {
                    System.out.print(cities.get(path.get(i)).getCityName() + " ");
                    if (i != path.size() - 1) {
                        System.out.print(types.charAt(i) + " ");
                    }
                }
                System.out.println("");
                isVisited[s] = false;
                return true;
            }
        }
        l = cities.get(s).getHighway();
        for (int i = 0; i < l.size(); i++) {
            path.add(l.get(i));
            types += "H";
            hasPath = Q3(l.get(i), d, type, cities, path, isVisited, l, types, hasPath);
            path.remove(new Integer(l.get(i)));
            types = types.substring(0, types.length() - 1);
        }
        l = (cities.get(s).getAirway());
        for (int i = 0; i < l.size(); i++) {
            path.add(l.get(i));
            types += "A";
            hasPath = Q3(l.get(i), d, type, cities, path, isVisited, l, types, hasPath);
            path.remove(new Integer(l.get(i)));
            types = types.substring(0, types.length() - 1);
        }
        l = (cities.get(s).getRailway());
        for (int i = 0; i < l.size(); i++) {
            path.add(l.get(i));
            types += "R";
            hasPath = Q3(l.get(i), d, type, cities, path, isVisited, l, types, hasPath);
            path.remove(new Integer(l.get(i)));
            types = types.substring(0, types.length() - 1);
        }
        isVisited[s] = false;
        return hasPath;
    }

    public void Q2(int s, int pass, int d, LinkedList<City> cities) {
        boolean[] isVisited = new boolean[cities.size()];
        ArrayList<Integer> path = new ArrayList<>();
        path.add(s);
        LinkedList<Integer> l = null;
        String types = "";
        boolean hasPath = false;
        hasPath = Q2(s, pass, d, cities, path, isVisited, l, types, hasPath);
        if (hasPath == false) {
            System.out.println("There is no path");
        }
    }

    private boolean Q2(int s, int pass, int d, LinkedList<City> cities,
            ArrayList<Integer> path, boolean[] isVisited, LinkedList<Integer> l,
            String types, boolean hasPath) {
        isVisited[s] = true;
        if (s == d && isVisited[pass] == true) {
            for (int i = 0; i < path.size(); i++) {
                System.out.print(cities.get(path.get(i)).getCityName() + " ");
                if (i != path.size() - 1) {
                    System.out.print(types.charAt(i) + " ");
                }
            }
            System.out.println("");
            isVisited[s] = false;
            return true;
        }

        l = cities.get(s).getHighway();
        for (int i = 0; i < l.size(); i++) {
            if (!isVisited[l.get(i)]) {
                path.add(l.get(i));
                types += "H";
                hasPath = Q2(l.get(i), pass, d, cities, path, isVisited, l, types, hasPath);
                path.remove(new Integer(l.get(i)));
                types = types.substring(0, types.length() - 1);
            }
        }
        l = (cities.get(s).getAirway());
        for (int i = 0; i < l.size(); i++) {
            if (!isVisited[l.get(i)]) {
                path.add(l.get(i));
                types += "A";
                hasPath = Q2(l.get(i), pass, d, cities, path, isVisited, l, types, hasPath);
                path.remove(new Integer(l.get(i)));
                types = types.substring(0, types.length() - 1);
            }
        }
        l = (cities.get(s).getRailway());
        for (int i = 0; i < l.size(); i++) {
            if (!isVisited[l.get(i)]) {
                path.add(l.get(i));
                types += "R";
                hasPath = Q2(l.get(i), pass, d, cities, path, isVisited, l, types, hasPath);
                path.remove(new Integer(l.get(i)));
                types = types.substring(0, types.length() - 1);
            }
        }
        isVisited[s] = false;
        return hasPath;
    }

    public void Q1(int s, int d, int limit, String type, LinkedList<City> cities) {
        boolean[] isVisited = new boolean[cities.size()];
        ArrayList<Integer> path = new ArrayList<>();
        path.add(s);
        LinkedList<Integer> l = null;
        String types = "";
        boolean hasPath = false;
        hasPath = Q1(s, d, limit, type, 0, cities, path, isVisited, l, types, hasPath);
        if (hasPath == false) {
            System.out.println("There is no path");
        }

    }

    private boolean Q1(int s, int d, int limit, String type, int typeCounter,
            LinkedList<City> cities, ArrayList<Integer> path,
            boolean[] isVisited, LinkedList<Integer> l, String types, boolean hasPath) {

        isVisited[s] = true;
        if (s == d) {
            if (typeCounter >= limit) {
                hasPath = true;
                for (int i = 0; i < path.size(); i++) {
                    System.out.print(cities.get(path.get(i)).getCityName() + " ");
                    if (i != path.size() - 1) {
                        System.out.print(types.charAt(i) + " ");
                    }
                }
                System.out.println("");
            }
            isVisited[s] = false;

            return true;
        }
        l = cities.get(s).getHighway();
        for (int i = 0; i < l.size(); i++) {
            if (!isVisited[l.get(i)]) {
                path.add(l.get(i));
                if (type.equals("H")) {
                    typeCounter++;
                }
                types += "H";
                hasPath = Q1(l.get(i), d, limit, type, typeCounter, cities, path, isVisited, l, types, hasPath);
                path.remove(new Integer(l.get(i)));
                types = types.substring(0, types.length() - 1);
                if (type.equals("H")) {
                    typeCounter--;
                }
            }
        }
        l = (cities.get(s).getAirway());
        for (int i = 0; i < l.size(); i++) {

            if (!isVisited[l.get(i)]) {
                path.add(l.get(i));
                if (type.equals("A")) {
                    typeCounter++;
                }
                types += "A";
                hasPath = Q1(l.get(i), d, limit, type, typeCounter, cities, path, isVisited, l, types, hasPath);
                path.remove(new Integer(l.get(i)));
                types = types.substring(0, types.length() - 1);
                if (type.equals("A")) {
                    typeCounter--;
                }
            }
        }
        l = (cities.get(s).getRailway());
        for (int i = 0; i < l.size(); i++) {

            if (!isVisited[l.get(i)]) {
                path.add(l.get(i));
                if (type.equals("R")) {
                    typeCounter++;
                }
                types += "R";
                hasPath = Q1(l.get(i), d, limit, type, typeCounter, cities, path, isVisited, l, types, hasPath);
                path.remove(new Integer(l.get(i)));
                types = types.substring(0, types.length() - 1);
                if (type.equals("R")) {
                    typeCounter--;
                }
            }
        }
        isVisited[s] = false;
        return hasPath;
    }

}
