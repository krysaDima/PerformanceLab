package task1;

public class Task1 {
    public static void main(String[] args) {

        if (args.length < 2) {
            System.err.println("Использование: java (путь) Task1.java <n> <m>");
            System.err.println("Пример: java (путь) Task1.java 5 3");
            System.exit(1);
        }

        try {
            int n = Integer.parseInt(args[0]);
            int m = Integer.parseInt(args[1]);

            if (n <= 0 || m <= 0) {
                System.err.println("Ошибка: n и m должны быть положительными числами!");
                System.exit(2);
            }

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
            System.err.println("Ошибка: аргументы должны быть целыми числами!");
            System.exit(3);
        }
    }
}
