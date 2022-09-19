public class HighArrayTest {
    public static void main(String[] args) {
        Long start;
        Long end;
        int size = 100_000;
        HighArray randomArr = new HighArray(size);

        System.out.println("Начало вставки в массив случайных элементов...");
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            randomArr.insert((int) (Math.random() * 100));
        }
        end = System.currentTimeMillis();
        System.out.println("Конец. Время: " + (end - start) + " мс");


        System.out.println("Начало поиска по массиву случайных элементов...");
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            randomArr.find((int) (Math.random() * 100));
        }
        end = System.currentTimeMillis();
        System.out.println("Конец. Время: " + (end - start) + " мс");


        System.out.println("Начало удаления по массиву случайных элементов...");
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            randomArr.delete((int) Math.random() * 100);
        }
        end = System.currentTimeMillis();
        System.out.println("Конец. Время: " + (end - start) + " мс");
    }
}
