package app;

import java.util.Scanner;

/**
 * Counting
 */
public class Counting {

    public Counting() {
    }

    public static void permutations(int n, int r) {
        int calculated = (factorial(n) / factorial(n - r));
        System.out.println(String.format("P(%d,%d) = %d", n, r, calculated));
    }

    public static void choosing(int n, int r) {
        int calculated = (factorial(n) / (factorial(r) * factorial(n - r)));
        System.out.println(String.format("%dC%d = %d", n, r, calculated));
    }

    public static void choosingWithRepition(int n, int r) {
        n += r - 1;
        int calculated = (factorial(n) / (factorial(r) * factorial(n - 1)));
        System.out.println(String.format("%dC\'%d = %d", n, r, calculated));
    }

    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n, r;

        System.out.print(
                "Would you like to calculate a permutation (P), a combination (C), or a combination with repitition (R)? ");
        switch (input.next().toString().toLowerCase()) {
            case "p":
                System.out.print("Please enter an n value: ");
                n = input.nextInt();
                System.out.print("Please enter an r value: ");
                r = input.nextInt();
                permutations(n, r);
                break;

            case "c":
                System.out.print("Please enter an n value: ");
                n = input.nextInt();
                System.out.print("Please enter an r value: ");
                r = input.nextInt();
                choosing(n, r);
                break;

            case "r":
                System.out.print("Please enter an n value: ");
                n = input.nextInt();
                System.out.print("Please enter an r value: ");
                r = input.nextInt();
                choosingWithRepition(n, r);
                break;

            default:
                break;
        }
        System.out.println();

        input.close();
    }
}