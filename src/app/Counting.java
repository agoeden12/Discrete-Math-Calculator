package app;

import java.util.Scanner;

/**
 * Counting
 */
public class Counting {

    public Counting() {
    }

    public static void permutation(int n, int r) {
        int calculated = (factorial(n) / factorial(n - r));
        System.out.println(String.format("P(%d,%d) = %d", n, r, calculated));
    }

    public static void permutation(String prefix, String str, int len) {
        int n = str.length();
        if (prefix.length() == len) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), len);
        }
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
                String word;
                System.out.print("Please enter a word: ");
                word = input.next();
                System.out.print("Please enter an r value: ");
                r = input.nextInt();
                permutation("", word, r);
                permutation(word.length(), r);
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