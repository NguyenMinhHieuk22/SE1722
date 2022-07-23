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
                System.out.println("File does not exist.");
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
            System.out.println("Cannot complete loading.");
            System.out.println(e.getMessage());
        }
        System.out.println("Loading completed.");
        return true;
    }

    public boolean saveToFile(String filename) {
        try {
            File f = new File(filename);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Car car : cList) {
                pw.println(car.getCarID() + ", " + car.getBrand().getBrandID() + ", " + car.getColor() + ", " + car.getFrameID() + ", " + car.getEngineID() + "\n");
            }
            pw.close();
            fw.close();

        } catch (Exception e) {
            System.out.println("Cannot complete saving");
            System.out.println(e.getMessage());
        }
        System.out.println("Saving completed");
        return true;
    }

    public int searchID(String carID) {
        int N = cList.size();
        for (int i = 0; i < N; i++) {
            if (this.get(i).getCarID() == carID) {
                return i;
            }
        }
        return -1;
    }

    public int searchFrame(String fID) {
        int N = cList.size();
        for (int i = 0; i < N; i++) {
            if (this.get(i).getFrameID() == fID) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String eID) {
        int N = cList.size();
        for (int i = 0; i < N; i++) {
            if (cList.get(i).getEngineID() == eID) {
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
            System.out.println("Car ID cannot be duplicated!");
            return;
        }

        Menu<Brand> menu = new Menu<Brand>();
        Brand brand = menu.ref_getChoice(brandList);

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
            System.out.println("No car is detected!");
        }
    }

    public boolean removeCar() {
        System.out.println("Enter a removed ID");
        String removedID = BrandList.inputStr();
        int pos = searchID(removedID);
        if (pos == -1) {
            System.err.println("Not found!");
            return false;
        } else {
            cList.remove(pos);
            System.out.println("Remove completed");
        }
        return true;
    }

    public boolean updateCar() {
        System.out.println("Enter a Car ID");
        String carID = BrandList.inputStr();

        int pos = searchID(carID);
        if (pos == -1) {
            System.out.println("Not found!");
            return false;
        } else {
            Menu<Brand> menu = new Menu<Brand>();
            Brand brand = menu.ref_getChoice(brandList);

            System.out.println("Enter a color: ");
            String color = BrandList.inputStr();
            String frameID;
            String engineID;

            do {
                frameID = inputFrame();
                if (searchFrame(frameID) != -1 && !this.get(pos).getFrameID().equals(frameID)) {
                    System.err.println("FrameID cannot be duplicated!");
                } else {
                    break;
                }
            } while (true);

            do {
                engineID = inputEngine();
                if (searchEngine(engineID) != -1 && !this.get(pos).getEngineID().equals(engineID)) {
                    System.out.println("EngineID cannot be duplicated!");
                } else {
                    break;
                }
            } while (true);

            Car car = new Car(carID, brand, color, frameID, engineID);
            cList.set(pos, car);
        }
        return true;
    }

    public void listCars() {
        Collections.sort(cList);
        for (Car car : cList) {
            System.out.println(car.screenString());
        }
    }
}
