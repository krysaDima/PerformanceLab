package task4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Используйте: java task4 <файл с числами>");
            System.exit(1);
        }

        String filePath = args[0];
        try {
            Scanner scanner = new Scanner(new File(filePath));
            List<Integer> numbers = new ArrayList<>();
            int sum = 0;

            while (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                numbers.add(num);
                sum += num;
            }
            scanner.close();

            if (numbers.isEmpty()) {
                System.err.println("Файл не содержит чисел");
                System.exit(2);
            }

            double average = (double) sum / numbers.size();


            int floorAvg = (int) Math.floor(average);
            int ceilAvg = (int) Math.ceil(average);

            int movesFloor = 0;
            int movesCeil = 0;

            for (int num : numbers) {
                movesFloor += Math.abs(num - floorAvg);
                movesCeil += Math.abs(num - ceilAvg);
            }

            int minMoves = Math.min(movesFloor, movesCeil);
            
            System.out.println("Минимальное количество ходов: " + minMoves);

        } catch (FileNotFoundException ex) {
            System.err.println("Ошибка! Файл не найден!");
            System.exit(3);
        }
    }
}