
package COM;

public class Car implements Comparable<Car> {
    protected String carID, color, frameID,engineID;
    protected Brand brand;

    public Car() {
    }

    public Car(String carID,Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
        this.brand = brand;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    @Override
    public int compareTo(Car c) {
        int d = this.brand.getBrandName().compareTo(c.brand.getBrandName());
        if (d != 0) {
            return d;
        }
        return this.carID.compareTo(c.carID);
    }

    @Override
    public String toString() {
        return "<"+carID+", "+brand.brandID+", "+color+", "+frameID+", "+engineID+">";
    }
    public String screenString(){
        return "<"+brand+"\n"+carID+", "+color+", "+frameID+", "+engineID+">";
    }
}
