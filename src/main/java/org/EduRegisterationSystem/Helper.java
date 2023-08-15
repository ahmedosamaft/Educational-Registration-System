package org.EduRegisterationSystem;

import java.util.*;

public class Helper {
    public static Scanner in = new Scanner(System.in);
    static public String generateID(int i,int len,String pfx) {
        return  pfx + "0".repeat(len - String.valueOf(i).length()) + i;
    }

    public static <T> void runMenu(Vector<T> menu) {
        for (int i = 0; i < menu.size(); i++) {
            System.out.println("\t" + (i + 1) + " - " + menu.get(i));
        }
    }

    public static <T> List<T> randomSubset(Vector<T> objects, double prob) {
        int len = Math.min((int) ((Math.random() + prob) * objects.size()),objects.size());
        Collections.shuffle(objects);
        return  objects.subList(0,len);
    }


}
