package lab0;

import java.util.*;

public class Majsoul {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int test = input.nextInt();
        input.nextLine();
        for (int i = 0; i < test; i++) {
            operate(input);
            if (i<test-1) System.out.println(" ");
            else System.out.print("");
        }
    }
    private static TreeMap<String, Integer> getStandard() {
        TreeMap<String, Integer> compared = new TreeMap<>();
        compared.put("W1", 0);
        compared.put("W2", 1);
        compared.put("W3", 2);
        compared.put("W4", 3);
        compared.put("W5", 4);
        compared.put("W6", 5);
        compared.put("W7", 6);
        compared.put("W8", 7);
        compared.put("W9", 8);

        compared.put("T1", 9);
        compared.put("T2", 10);
        compared.put("T3", 11);
        compared.put("T4", 12);
        compared.put("T5", 13);
        compared.put("T6", 14);
        compared.put("T7", 15);
        compared.put("T8", 16);
        compared.put("T9", 17);

        compared.put("Y1", 18);
        compared.put("Y2", 19);
        compared.put("Y3", 20);
        compared.put("Y4", 21);
        compared.put("Y5", 22);
        compared.put("Y6", 23);
        compared.put("Y7", 24);
        compared.put("Y8", 25);
        compared.put("Y9", 26);

        compared.put("E", 27);
        compared.put("S", 28);
        compared.put("W", 29);
        compared.put("N", 30);
        compared.put("B", 31);
        compared.put("F", 32);
        compared.put("Z", 33);
        TreeMap<String, Integer> standard = new TreeMap<>(
                Comparator.comparing(compared::get)
        );
        standard.put("W1", 0);
        standard.put("W2", 0);
        standard.put("W3", 0);
        standard.put("W4", 0);
        standard.put("W5", 0);
        standard.put("W6", 0);
        standard.put("W7", 0);
        standard.put("W8", 0);
        standard.put("W9", 0);

        standard.put("T1", 0);
        standard.put("T2", 0);
        standard.put("T3", 0);
        standard.put("T4", 0);
        standard.put("T5", 0);
        standard.put("T6", 0);
        standard.put("T7", 0);
        standard.put("T8", 0);
        standard.put("T9", 0);

        standard.put("Y1", 0);
        standard.put("Y2", 0);
        standard.put("Y3", 0);
        standard.put("Y4", 0);
        standard.put("Y5", 0);
        standard.put("Y6", 0);
        standard.put("Y7", 0);
        standard.put("Y8", 0);
        standard.put("Y9", 0);

        standard.put("E", 0);
        standard.put("S", 0);
        standard.put("W", 0);
        standard.put("N", 0);
        standard.put("B", 0);
        standard.put("F", 0);
        standard.put("Z", 0);
        return standard;
    }

    private static void operate(Scanner input) {
        TreeMap<String, Integer> standard = getStandard();
        String[] origin = input.nextLine().split(" ");
        for (int i = 0; i <origin.length; i++) {
            int value= standard.get(origin[i].trim());
            standard.put(origin[i].trim(),value+1);
        }
        boolean isFirst=true;
        for(Map.Entry<String, Integer> map:standard.entrySet()){

            if (map.getValue()==1) {
                if(!isFirst)  System.out.print(" ");
                System.out.print(map.getKey());
                isFirst =false;
            }
            else if (map.getValue()==2){
                if(!isFirst)  System.out.print(" ");
                System.out.print(map.getKey());
                System.out.print(" "+map.getKey());
                isFirst =false;
            }
            else if (map.getValue()==3){
                if(!isFirst)  System.out.print(" ");
                System.out.print(map.getKey());
                System.out.print(" "+map.getKey());
                System.out.print(" "+map.getKey());
                isFirst =false;
            }
            else if (map.getValue()==4){
                if(!isFirst)  System.out.print(" ");
                System.out.print(map.getKey());
                System.out.print(" "+map.getKey());
                System.out.print(" "+map.getKey());
                System.out.print(" "+map.getKey());
                isFirst =false;
            }
        }
    }

}