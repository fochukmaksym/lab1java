import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n ОБЕРІТЬ ЗАВДАННЯ");
            System.out.println("1 - Обчислення виразу (дріб)");
            System.out.println("2 - Найдовший зростаючий ланцюжок у масиві");
            System.out.println("3 - Побудова вектора X з матриці");
            System.out.println("4 - Вилучення найдовших слів з тексту");
            System.out.println("0 - Вихід");
            System.out.print("Ваш вибір: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1 -> runTask1(sc);
                case 2 -> runTask2(sc);
                case 3 -> runTask3(sc);
                case 4 -> runTask4(sc);
                case 0 -> {
                    System.out.println("Завершення роботи.");
                    return;
                }
                default -> System.out.println("Невірний вибір! Спробуйте ще раз.");
            }
        }
    }

//ЗАВДАННЯ 1
    private static void runTask1(Scanner sc) {
        System.out.println("\n--- Завдання 1 ---");
        System.out.print("Введіть n та m (через пробіл): ");
        double n = sc.nextDouble();
        double m = sc.nextDouble();

        if (m == -2 || n == m) {
            System.out.println("Помилка: Ділення на нуль!");
            return;
        }

        // 1) double -> double
        double resDouble = Math.pow((n + 1) / (m + 2) + 5 / (n - m), 2) * n * m;

        // 2) int -> double
        int ni = (int) n, mi = (int) m;
        double resFromInt = Math.pow((double)(ni + 1) / (mi + 2) + 5.0 / (ni - mi), 2) * ni * mi;

        // 3) double -> int
        int resInt = (int) resDouble;

        System.out.printf("1) Дійсні дані -> Дійсний результат: %.4f%n", resDouble);
        System.out.printf("2) Цілі дані   -> Дійсний результат: %.4f%n", resFromInt);
        System.out.println("3) Дійсні дані -> Цілий результат:   " + resInt);
    }

//ЗАВДАННЯ 2
    private static void runTask2(Scanner sc) {
        System.out.println("\n--- Завдання 2 ---");
        System.out.print("Введіть кількість елементів масиву n (n <= 300): ");
        int n = sc.nextInt();
        int[] a = new int[n];

        System.out.println("Введіть " + n + " елементів масиву:");
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();

        int maxL = 1, curL = 1, maxEnd = 0;
        for (int i = 1; i < n; i++) {
            if (a[i] > a[i - 1]) {
                curL++;
                if (curL > maxL) {
                    maxL = curL;
                    maxEnd = i;
                }
            } else {
                curL = 1;
            }
        }

        System.out.println("Найдовший зростаючий ланцюжок:");
        for (int i = maxEnd - maxL + 1; i <= maxEnd; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

//ЗАВДАННЯ 3
    private static void runTask3(Scanner sc) {
        System.out.println("\n--- Завдання 3 ---");
        System.out.print("Введіть розмірність матриці n (n <= 20): ");
        int n = sc.nextInt();
        double[] X = new double[n];

        System.out.println("Введіть елементи матриці " + n + "x" + n + ":");
        for (int i = 0; i < n; i++) {
            double min = Double.MAX_VALUE, max = -Double.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                double val = sc.nextDouble();
                if (val < min) min = val;
                if (val > max) max = val;
            }
            X[i] = (Math.abs(max) + Math.abs(min)) / 2.0;
        }

        System.out.println("Сформований вектор X:");
        for (int i = 0; i < n; i++) {
            System.out.printf("X[%d] = %.4f%n", i + 1, X[i]);
        }
    }

//ЗАВДАННЯ 4
    private static void runTask4(Scanner sc) {
        System.out.println("\n--- Завдання 4 ---");
        System.out.println("Введіть текст:");
        String text = sc.nextLine();
        String[] words = text.split("[\\s\\p{Punct}]+");
        int maxLen = 0;
        for (String w : words) {
            if (w.length() > maxLen) maxLen = w.length();
        }

        for (String w : words) {
            if (w.length() == maxLen && !w.isEmpty()) {
                text = text.replaceAll("\\b" + w + "\\b", "");
            }
        }

        System.out.println("Результат після вилучення найдовших слів:");
        System.out.println(text.replaceAll("\\s+", " ").trim());
    }
}