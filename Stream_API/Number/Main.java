package JavaCore.Stream_API;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        List<Integer> newIntList = new ArrayList<>();

        for (int x : intList) {
            if (x > 0 && x % 2 == 0) {
                newIntList.add(x);
            }
        }
        Collections.sort(newIntList);
        for (int x : newIntList) {
            System.out.println(x);
        }
    }
}
