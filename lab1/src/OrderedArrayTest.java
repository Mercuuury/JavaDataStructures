public class OrderedArrayTest {
    public static void main(String[] args) {
        Long start;
        Long end;
        int size = 100_000;
        OrderedArray orderedArr = new OrderedArray(size);

        System.out.println("Начало вставки в массив упорядоченных элементов...");
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            orderedArr.insert(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Конец. Время: " + (end - start) + " мс");

        System.out.println("Начало двоичного поиска по массиву упорядоченных элементов...");
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            orderedArr.find((int) (Math.random() * 100));
        }
        end = System.currentTimeMillis();
        System.out.println("Конец. Время: " + (end - start) + " мс");

        System.out.println("Начало удаления по массиву упорядоченных элементов...");
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            orderedArr.delete((int) Math.random() * 100);
        }
        end = System.currentTimeMillis();
        System.out.println("Конец. Время: " + (end - start) + " мс");
    }
}
