import java.util.*;

public class Homework7 {
    public static int powSub(int a, int b){
        return (a * a) - 2 * (a * b) + (b * b);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input the length of an array : ");
        int n = scanner.nextInt();
        int[] array = new int[n];

        for(int i = 0; i < n; i++){
            System.out.print("Input " + (i + 1) + ". element of array : ");
            array[i] = scanner.nextInt();
        }

        for(int i = 0 ; i < n / 2; i++){
            System.out.print(powSub(array[i], array[n - i - 1]) + " ");
        }
    }
}
