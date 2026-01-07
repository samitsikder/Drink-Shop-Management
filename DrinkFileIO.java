 package File;

import Entity.*;
import java.io.*;
import java.util.*;

public class DrinkFileIO {

    public static void loadFromFile(Drink[] drinks) {
        try {
            Scanner sc = new Scanner(new File("./File/Drinks.txt"));
            int i = 0;
            while (sc.hasNextLine()) {
                String data[] = sc.nextLine().split(";");
                drinks[i++] = new Drink(
                        data[0],
                        data[1],
                        data[2],
                        Double.parseDouble(data[3]),
                        data[4]
                );
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Load Error");
        }
    }

    public static void saveToFile(Drink[] drinks) {
        try {
            FileWriter fw = new FileWriter("./File/Drinks.txt");
            for (Drink d : drinks) {
                if (d != null) {
                    fw.write(
                        d.getSize() + ";" +
                        d.getCustomerName() + ";" +
                        d.getMobile() + ";" +
                        d.getPrice() + ";" +
                        d.getType() + "\n"
                    );
                }
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Save Error");
        }
    }
}
