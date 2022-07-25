
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class CarList extends ArrayList<Car> {

    BrandList brandList;

    public CarList(BrandList bList) {
        this.brandList = bList;
    }

    public static String inputFrame() {
        String input;
        while (true) {
            try {
                System.out.println("Enter new Frame ID. It must be in the format 'F00000' and cannot be duplicated");
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
                System.out.println("Enter new Engine ID. It must be in the format 'E00000' and cannot be duplicated");
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
            File file = new File(filename);
            if (!file.exists()) {
                System.err.println("Error: file " + filename + " does not exist to load data.");
                return false;
            }
            if (file.length() == 0) {
                System.err.println("Warning: " + filename + " has no content to read.");
                return false;
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";

            while ((line = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ", ");
                String carID = stk.nextToken().trim();
                String brandId = stk.nextToken().trim();
                String color = stk.nextToken().trim();
                String frameID = stk.nextToken().trim();
                String engineID = stk.nextToken().trim();

                int i = brandList.searchID(brandId);
                Brand brand = brandList.get(i);

                Car car = new Car(carID, brand, color, frameID, engineID);
                this.add(car);
            }
            br.close();
            fr.close();
            System.out.println("Complete loading.");
            return true;
        } catch (Exception e) {
            System.err.println("Error: cannot load data from file " + filename);
            System.err.println("Reason: " + e);
            return false;
        }
    }

    public boolean saveToFile(String filename) {
        try {
            File f = new File(filename);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Car car : this) {
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
        int N = this.size();
        for (int i = 0; i < N; i++) {
            if (this.get(i).getCarID().equals(carID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchFrame(String fID) {
        int N = this.size();
        for (int i = 0; i < N; i++) {
            if (this.get(i).getFrameID().equals(fID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String eID) {
        int N = this.size();
        for (int i = 0; i < N; i++) {
            if (this.get(i).getEngineID().equals(eID)) {
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
        System.out.println("Successfully added");
                
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
            this.remove(pos);
            System.out.println("Remove completed");
        }
        return true;
    }

    public boolean updateCar() {
        System.out.println("Enter a Car ID to update");
        String carID = BrandList.inputStr();

        int pos = searchID(carID);
        if (pos == -1) {
            System.out.println("Not found!");
            return false;
        } else {
            Menu<Brand> menu = new Menu<Brand>();
            Brand brand = menu.ref_getChoice(brandList);

            System.out.println("Enter a new color for updating: ");
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
            this.set(pos, car);
            System.out.println("Successfully upgraded");
        }
        return true;
    }

    public void listCars() {
        Collections.sort(this);
        for (Car car : this) {
            System.out.println(car.screenString());
        }
    }
}
