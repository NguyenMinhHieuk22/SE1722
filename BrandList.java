
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BrandList extends ArrayList<Brand> {

    public static String inputStr() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a string: ");
        String input = sc.nextLine();
        while (true) {
            if (input.length() != 0) {
                break;
            } else {
                System.err.println("Wrong string. You've entered an empty string. ");
                System.out.print("Please enter again: ");
                input = sc.nextLine();
            }
        }
        return input;
    }

    public double inputDouble() {
        double input;
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Please enter a real number");
                input = Double.parseDouble(sc.nextLine());

                while (input <= 0) {
                    System.err.println("Invalid real number.Please enter again:");
                    input = Double.parseDouble(sc.nextLine());
                }
                return input;

            } catch (Exception e) {
                System.err.println("Invalid input.");
            }
        }
    }

    public boolean loadFromFile(String filename) {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                System.err.println("File does not exist");
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
                StringTokenizer stk = new StringTokenizer(line, ",");
                String brandId = stk.nextToken().trim();
                String brandName = stk.nextToken().trim();
                String soundAndPrice = stk.nextToken().trim();
                stk = new StringTokenizer(soundAndPrice, ":");
                String soundBrand = stk.nextToken().trim();
                Double price = Double.parseDouble(stk.nextToken());
                Brand brand = new Brand(brandId, brandName, soundBrand, price);
                this.add(brand);
            }
            br.close();
            fr.close();
            System.out.println("Complete loading.");
            return true;
        } catch (Exception e) {
            System.err.println("Error: cannot load data from file " + filename);
            System.err.println("Reason: " + e.getMessage());
            return false;
        }

    }

    public boolean saveToFile(String filename) {
        try {
            File f = new File(filename);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Brand brand : this) {
                pw.println(brand.getBrandID() + ", " + brand.getBrandName() + ", " + brand.getSoundBrand() + ": " + brand.getPrice());
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

    public int searchID(String brandID) {
        int N = this.size();
        for (int i = 0; i < N; i++) {
            if (this.get(i).getBrandID().equals(brandID)) {
                return i;
            }
        }
        return -1;
    }

    public Brand getUserChoice() {
        Menu mnu = new Menu();
        return (Brand) mnu.ref_getChoice(this);
    }

    public void addBrand() {
        System.out.println("Enter brandID:");
        String brandID = inputStr();
        while (searchID(brandID) != -1) {
            System.out.println("Brand ID cannot be duplicated");
            System.out.println("Enter brandID again: ");
            brandID = "";
            brandID = inputStr();
        }
        System.out.println("Enter brandName: ");
        String brandName = inputStr();
        System.out.println("Enter soundBrand: ");
        String soundBrand = inputStr();
        System.out.println("Enter brand's price:");
        double price = inputDouble();
        Brand brand = new Brand(brandID, brandName, soundBrand, price);
        this.add(brand);
        System.out.println("Successfully added");
    }

    public void updateBrand() {
        System.out.println("Enter brandID to update:");
        String brandID = inputStr();
        if (searchID(brandID) == -1) {
            System.out.println("BrandID does not exist.");
        } else {
            System.out.println("Enter new brandName for updating: ");
            String brandName = inputStr();
            System.out.println("Enter new soundBrand for updating: ");
            String soundBrand = inputStr();
            System.out.println("Enter new brand's price for updating:");
            double price = inputDouble();
            Brand brand = new Brand(brandID, brandName, soundBrand, price);
            this.set(searchID(brandID), brand);
            System.out.println("Successfully upgraded");
        }
    }

    public void listBrands() {
        System.out.println("Brand list: ");
        for (Brand brand : this) {
            System.out.println(brand);
        }
    }
}
