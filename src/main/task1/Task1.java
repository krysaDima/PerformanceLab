import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        System.out.println("Введите значения n и m через пробел и нажмите Enter:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] parts = input.split(" ");


        if (parts.length < 2) {
            System.out.println("Ошибка: нужно ввести два числа через пробел!");
            return;
        }

        try {
            int n = Integer.parseInt(parts[0]);
            int m = Integer.parseInt(parts[1]);

            StringBuilder path = new StringBuilder();
            int current = 1;
            boolean[] visited = new boolean[n + 1];

            do {
                path.append(current);
                visited[current] = true;
                current = (current + m - 1) % n;
                if (current == 0) current = n;
            } while (!visited[current]);

            System.out.println("Результат: " + path);

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите числа, а не текст!");
        } finally {
            scanner.close();
        }
    }
}
