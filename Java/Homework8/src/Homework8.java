import java.util.*;

public class Homework8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input a string : ");
        String str = scanner.nextLine();

        for(int i = str.length() - 1; i >= 0; i -= 2){
            System.out.print(str.charAt(i) + " ");
        }
    }
}
