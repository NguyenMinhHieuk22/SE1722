package COM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class CarList extends ArrayList<Car> {

    ArrayList<Car> cList = new ArrayList<Car>();
    BrandList brandList;

    public CarList(BrandList bList) {
        this.brandList = bList;
    }

    public static String inputFrame() {
        String input;
        while (true) {
            try {
                System.out.println("Enter Frame ID. It must be in the format 'F00000' and cannot be duplicated");
                input = BrandList.inputStr();
                if (input.matches("^F\\d{5}$") == false) {
                    System.err.println("Enter a String of 'F00000'");
                }
                return input;
            } catch (Exception e) {
                System.out.println("Invalid input.");
                System.err.println(e.getMessage());
            }
        }
    }

    public static String inputEngine() {
        String input;
        while (true) {
            try {
                System.out.println("Enter Engine ID. It must be in the format 'E00000' and cannot be duplicated");
                input = BrandList.inputStr();

                if (input.matches("^E\\d{5}$") == false) {
                    System.err.println("Enter a String of 'E00000'");
                }
                return input;
            } catch (Exception e) {
                System.err.println("Invalid input. ");
                System.err.println(e.getMessage());
            }
        }
    }

    public boolean loadFromFile(String filename) {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                System.err.println("File does not exist.");
                return false;
            }
            if (f.length() == 0) {
                System.err.println("Error: " + filename + " has no content to read.");
                return false;
            }
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ", ");
                String carID = stk.nextToken();
                String brandID = stk.nextToken();
                String color = stk.nextToken();
                String frameID = stk.nextToken();
                String engineID = stk.nextToken();
                Brand brand = brandList.get(brandList.searchID(brandID));
                Car car = new Car(carID, brand, color, frameID, engineID);
                cList.add(car);
                br.close();
                fr.close();

            }
        } catch (Exception e) {
            System.err.println("Cannot complete loading.");
            System.err.println(e.getMessage());
        }
        System.out.println("Loading completed.");
        return true;
    }

    public boolean saveToFile(String filename) {
        
    }

    public int searchID(String carID) {
        int N = cList.size();
        for (int i = 0; i < N; i++) {
            if (this.get(i).getCarID().equals(carID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchFrame(String fID) {
        int N = cList.size();
        for (int i = 0; i < N; i++) {
            if (this.get(i).getFrameID(.equals(fID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String eID) {
        int N = cList.size();
        for (int i = 0; i < N; i++) {
            if (cList.get(i).getEngineID().equals(eID)) {
                return i;
            }
        }
        return -1;
    }

    public void addCar() {
        String carID;

        System.out.println("Enter a Car ID: ");
        carID = BrandList.inputStr();

        if (searchID(carID) != -1) {
            System.err.println("Car ID cannot be duplicated!");
            return;
        }

        Menu<Brand> menu = new Menu<Brand>();
        Brand brand =(Brand) menu.ref_getChoice(brandList);

        System.out.println("Enter a color: ");
        String color = BrandList.inputStr();
        String frameID;
        do {
            frameID = inputFrame();
            if (searchFrame(frameID) == -1) {
                break;
            }
            System.err.println("FrameID cannot be duplicated.");
        } while (true);

        String engineID;
        do {
            engineID = inputEngine();
            if (searchEngine(engineID) == -1) {
                break;
            }
            System.err.println("EngineID cannot be duplicated.");
        } while (true);

        Car car = new Car(carID, brand, color, frameID, engineID);
        this.add(car);
    }

    public void printBasedBrandName() {
        System.out.println("Enter a brand name(or a part of it):");
        String aPartOfBrandName = BrandList.inputStr();
        int count = 0;

        for (Car c : this) {
            if (c.getBrand().getBrandName().contains(aPartOfBrandName)) {
                System.out.println(c.screenString());
                count++;
            }
        }
        if (count == 0) {
            System.err.println("No car is detected!");
        }
    }

    public boolean removeCar() {
        
    }

    public boolean updateCar() {
        
    }

    public void listCars() {
        Collections.sort(cList);
        for (Car car : cList) {
            System.out.println(car.screenString());
        }
    }
}
