package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Используйте: java (путь) task2.java <файл_окружности> <файл_точек>");
            System.exit(1);
        }

        String circleFile = args[0];
        String pointFile = args[1];


        try {

            Scanner fileScanner = new Scanner(new File(circleFile));
            double circleX = fileScanner.nextDouble();
            double circleY = fileScanner.nextDouble();
            double circleRadius = fileScanner.nextDouble();


            fileScanner = new Scanner(new File(pointFile));
            double radiusSquared = circleRadius * circleRadius;
            int pointsChecked = 0;

            while (fileScanner.hasNext() && pointsChecked < 100) {
                double pointX = fileScanner.nextDouble();
                double pointY = fileScanner.nextDouble();
                pointsChecked++;

                double dx = pointX - circleX;
                double dy = pointY - circleY;
                double distanceSquared = dx * dx + dy * dy;

                if (Math.abs(distanceSquared - radiusSquared) < 1e-9) {
                    System.out.println(0);
                } else if (distanceSquared < radiusSquared) {
                    System.out.println(1);
                } else {
                    System.out.println(2);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: файл не найден!");
            System.exit(2);
        } catch (Exception e) {
            System.err.println("Ошибка формата файла");
            e.printStackTrace();
            System.exit(3);
        }
    }
}





