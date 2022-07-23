package COM;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu<E> {

    public int inputInt(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int input;
        while (true) {
            try {
                System.out.println("Please enter an integer in range [" + min + "," + max + "] : ");
                input = Integer.parseInt(sc.nextLine());
                if (input < min || input > max) {
                    System.out.println("Please enter a number within the range");
                    throw new Exception();
                }

                return input;
            } catch (Exception e) {
                System.out.println("Invalid value");
                System.out.println(e.getMessage());
            }
        }
    }

    public int int_getChoice(ArrayList<E> options) {
        int reponse;
        int N = options.size();
        for (int i = 0; i < N; i++) {
            System.out.println( (i+1)+". "+options.get(i));
        }
        System.out.println("Please choos an option 1..N");
        reponse = inputInt(1, N);
        return reponse;
    }

    public E ref_getChoice(ArrayList<E> options) {
        int response;
        int N=options.size();
        do{
            response= int_getChoice(options);
        }
        while(response<0|| response>N);
        return options.get(response-1);
    }
}
