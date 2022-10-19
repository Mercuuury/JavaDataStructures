import java.util.Scanner;

public class HighArrayTest {
    public static void main(String[] args) {
        Long start;
        Long end;
        int size = 100;
        HighArray randomArr = new HighArray(size);
        Scanner in = new Scanner(System.in);

        System.out.println("Начало вставки в массив случайных элементов...");
        start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            randomArr.insert((int) (Math.random() * 100));
        }
        end = System.nanoTime();
        System.out.println("Конец. Время: " + (end - start) + " мс");

        
        System.out.println("Начало поиска по массиву случайных элементов...");
        int searchVal = in.nextInt();
        start = System.nanoTime();
        System.out.println(randomArr.find(searchVal));
        end = System.nanoTime();
        System.out.println("Конец. Время: " + (end - start) + " мс");

        System.out.println("Начало удаления по массиву случайных элементов...");
        int deleteVal = in.nextInt();
        start = System.nanoTime();
        System.out.println(randomArr.delete(deleteVal));
        end = System.nanoTime();
        System.out.println("Конец. Время: " + (end - start) + " мс");

        in.close();
    }
}
