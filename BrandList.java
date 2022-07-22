package COM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BrandList extends ArrayList<Brand> {

    ArrayList<Brand> bList = new ArrayList<Brand>();

    public String inputStr() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a string: ");
        String input = sc.nextLine();
        while (true) {
            if (input.length()!=0 ) {
                break;
            } else {
                System.err.println("Wrong string. You've entered an empty string. ");
                System.out.print("Please enter again: ");
                input = sc.nextLine();
            }
        }
        return input;
    }

    public boolean loadFromFile(String filename) {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                System.err.println("File does not exist");
                return false;
            }
            if(f.length()==0){
                System.err.println("Warning: "+filename+" has no content.");
                return false;
            }
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ",");
                String brandId = stk.nextToken();
                String brandName = stk.nextToken();
                String soundBrand = stk.nextToken();
                Double price = Double.parseDouble(stk.nextToken());
                Brand brand = new Brand(brandId, brandName, soundBrand, price);
                this.add(brand);
            }

            br.close();
            fr.close();

        } catch (Exception e) {
            System.out.println("Cannot complete loading");
            System.out.println(e.getMessage());
        }
        System.out.println("Loading completed");
        return true;
    }

    public boolean saveToFile(String filename) {
        try {
            File f = new File(filename);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Brand brand : bList) {
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
        
    }

    public Brand getUserChoice() {
        Menu mnu = new Menu();
        return (Brand) mnu.ref_getChoice(this);
    }

    public void addBrand() {
        
    }

    public void updateBrand() {
        
        
    }

    public void listBrands() {
        
        
    }
}

