 package Entity;

public class Drink {

    private String size;
    private String customerName;
    private String mobile;
    private double price;
    private String type;

    public Drink() {}

    public Drink(String size, String customerName,
                 String mobile, double price, String type) {
        this.size = size;
        this.customerName = customerName;
        this.mobile = mobile;
        this.price = price;
        this.type = type;
    }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getInfo() {
        return "Size: " + size +
               ", Name: " + customerName +
               ", Mobile: " + mobile +
               ", Price: " + price +
               ", Drink: " + type;
    }
}
