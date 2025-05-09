import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int maxPoints = 100;
        final double precision = 1e-9;
        double circleX = 0;
        double circleY = 0;
        double circleRadius = 0;
        double pointX = 0;
        double pointY = 0;
        String pointFile;
        String circleFile;

        String noFileMessage = """
              "Ошибка, файл не найден!"
              """;
        String circleSetMsg = """
               Введите путь к файлу с кординатами окружности: 
               """;
        String invalidFormatMsg = """
              Ошибка формата, в файле не достаточно чисел для кординат!
              """;
        String pointSetMsg = """
              Введите путь к файлу с кардинатами точек: 
              """;

        while (true){
            System.out.println(circleSetMsg);
            circleFile = scanner.nextLine();

            try {
                Scanner fileScanner = new Scanner(new File(circleFile));
                circleX = Double.parseDouble(fileScanner.next());
                circleY = Double.parseDouble(fileScanner.next());
                circleRadius = Double.parseDouble(fileScanner.next());
                break;

            }catch (FileNotFoundException ex){
                System.out.println(noFileMessage);
            }catch (Exception ex){
                System.out.println(invalidFormatMsg);
                ex.printStackTrace();
            }
        }

        while (true){
            System.out.println(pointSetMsg);
            pointFile = scanner.nextLine();

            try {
                Scanner fileScanner = new Scanner(new File(pointFile));
                double  radiusSquared = circleRadius * circleRadius;
                int pointsChecked = 0;
                while (fileScanner.hasNext() && pointsChecked < maxPoints){
                    pointX = Double.parseDouble(fileScanner.next());
                    pointY = Double.parseDouble(fileScanner.next());
                    pointsChecked ++;

                    double  distanceSquared =  Math.pow(pointX - circleX , 2 ) + Math.pow(pointY - circleY, 2);
                    if (Math.abs((distanceSquared - radiusSquared)) < precision) {
                        System.out.println(0);
                    }
                    else if (distanceSquared < radiusSquared) {
                        System.out.println(1);
                    }
                    else System.out.println(2);
                }
                break;

            }catch (FileNotFoundException ex){
                System.out.println(noFileMessage);
            }catch (Exception ex){
                System.out.println(invalidFormatMsg);
                ex.printStackTrace();
            }
        }

        scanner.close();
    }
}
