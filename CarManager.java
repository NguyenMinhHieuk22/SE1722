
import COM.BrandList;
import COM.CarList;
import COM.Menu;
import java.util.ArrayList;

public class CarManager {

    public static void main(String[] args) {
        BrandList brandList = new BrandList();
        brandList.loadFromFile("C:\\Users\\hiung\\OneDrive\\Máy tính\\Documents\\NetBeansProjects\\CarPrj\\src\\brands.txt");

        CarList carList = new CarList(brandList);
        carList.loadFromFile("C:\\Users\\hiung\\OneDrive\\Máy tính\\Documents\\NetBeansProjects\\CarPrj\\src\\cars.txt");

        int choice;
        Menu<String> menu = new Menu<String>();

        ArrayList<String> ops = new ArrayList<String>();
        ops.add("1.List all brands.");
        ops.add("2.Add a new brand.");
        ops.add("3.Search a brand based on its ID.");
        ops.add("4.Update a brand.");
        ops.add("5.Save brands to the file, named brands.txt.");
        ops.add("6.List all cars in ascending order of brand names.");
        ops.add("7.List cars based on a part of an input brand name.");
        ops.add("8.Add a car.");
        ops.add("9.Remove a car based on its ID.");
        ops.add("10.Update a car based on its ID.");
        ops.add("11.Save cars to file, named cars.txt.");

        do {
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
                    carList.updateCar();
                    break;
                case 5:
                    brandList.saveToFile("C:\\Users\\hiung\\OneDrive\\Máy tính\\Documents\\NetBeansProjects\\CarPrj\\src\\brands.txt");
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
                    carList.saveToFile("C:\\Users\\hiung\\OneDrive\\Máy tính\\Documents\\NetBeansProjects\\CarPrj\\src\\cars.txt");
                    break;
            }
        } while (choice > 0 && choice <= ops.size());
    }
}
