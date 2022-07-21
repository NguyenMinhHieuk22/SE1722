package COM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class CarList extends ArrayList<Car> {

    ArrayList<Car> cList = new ArrayList<Car>();
    BrandList brandList;

    public CarList(BrandList bList) {
        this.brandList = bList;
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
    public boolean saveToFile(String filename){
        try {
            File f = new File(filename);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Car car : cList) {
                pw.println(car.getCarID() + ", " + car.getBrand().getBrandID() + ", " + car.getColor() + ", "+ car.getFrameID() + ", " + car.getEngineID()+"\n");
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
    public int searchID(String carID){
        int N = cList.size();
        for (int i = 0; i < N; i++) {
            if (this.get(i).getCarID() == carID) {
                return i;
            }
        }
        return -1;
    }
    public int searchFrame(String fID){
        int N = cList.size();
        for (int i = 0; i < N; i++) {
            if (this.get(i).getFrameID() == fID) {
                return i;
            }
        }
        return -1;
    }
    public int searchEngine(String eID){
        int N = cList.size();
        for (int i = 0; i < N; i++) {
            if (cList.get(i).getEngineID() == eID) {
                return i;
            }
        }
        return -1;
    }
    public void addCar(){
        
    }
    public void printBasedBrandName(){
        
    }
    public boolean removeCar(){
        return false;
    }
    public boolean updateCar(){
        return false;
    }
    public void listCars(){
        
    }
    }

