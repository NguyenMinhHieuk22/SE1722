
import java.util.ArrayList;

public class CarManager {

    public static void main(String[] args) {
        BrandList brandList = new BrandList();
        brandList.loadFromFile("src/brands.txt");

        CarList carList = new CarList(brandList);
        carList.loadFromFile("src/cars.txt");

        int choice;
        Menu<String> menu = new Menu<String>();

        ArrayList<String> ops = new ArrayList<String>();
        ops.add("List all brands.");
        ops.add("Add a new brand.");
        ops.add("Search a brand based on its ID.");
        ops.add("Update a brand.");
        ops.add("Save brands to the file, named brands.txt.");
        ops.add("List all cars in ascending order of brand names.");
        ops.add("List cars based on a part of an input brand name.");
        ops.add("Add a car.");
        ops.add("Remove a car based on its ID.");
        ops.add("Update a car based on its ID.");
        ops.add("Save cars to file, named cars.txt.");

        do {
            System.out.println("=====================================================");
            choice = menu.int_getChoice(ops);
            switch (choice) {
                case 1:
                    brandList.listBrands();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                    String brandID = BrandList.inputStr();
                    int pos = brandList.searchID(brandID);
                    if (pos == -1) {
                        System.err.println("Cannot found brand: " + brandID);
                    } else {
                        System.out.println(brandList.get(pos));
                    }
                    break;
                case 4:
                    brandList.updateBrand();
                    break;
                case 5:
                    brandList.saveToFile("src/brands.txt");
                    break;
                case 6:
                    carList.listCars();
                    break;
                case 7:
                    carList.printBasedBrandName();
                    break;
                case 8:
                    carList.addCar();
                    break;
                case 9:
                    carList.removeCar();
                    break;
                case 10:
                    carList.updateCar();
                    break;
                case 11:
                    carList.saveToFile("src/cars.txt");
                    break;
            }
        } while (choice > 0 && choice <= ops.size() + 1);
    }
}
