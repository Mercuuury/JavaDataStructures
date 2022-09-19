public class OrderedArray implements Array {
    private long[] a; // Ссылка на массив a
    private int nElems; // Количество элементов

    public OrderedArray(int size) // Конструктор
    {
        a = new long[size]; // Создание массива
    }

    public void insert(int value) // Вставка элемента
    {
        int j;
        for (j = 0; j < nElems; j++) // Определение позиции вставки
            if (a[j] > value) // (линейный поиск)
                break;
        for (int k = nElems; k > j; k--) // Перемещение последующих элементов
            a[k] = a[k - 1];
        a[j] = value; // Вставка
        nElems++; // Увеличение размер
    }

    public int find(long searchKey) {
        int lowerBound = 0;
        int upperBound = nElems - 1;
        int curIn;

        while (true) {
            curIn = (lowerBound + upperBound) / 2;
            if (a[curIn] == searchKey)
                return curIn; // Элемент найден
            else if (lowerBound > upperBound)
                return nElems; // Элемент не найден
            else { // Деление диапазона
                if (a[curIn] < searchKey)
                    lowerBound = curIn + 1; // В верхней половине
                else
                    upperBound = curIn - 1; // В нижней половине
            }
        }
    }

    public boolean delete(long value) {
        int j = find(value);
        if (j == nElems) // Найти не удалось
            return false;
        else // Элемент найден
        {
            for (int k = j; k < nElems - 1; k++) // Перемещение последующих элементов
                a[k] = a[k + 1];
            nElems--; // Уменьшение размера
            return true;
        }

    }

    public void print() {
        for (int i = 0; i < nElems; i++) {
            System.out.print(a[i] + ' ');
        }
        System.out.println();
    }

    public int size() {
        return nElems;
    }
}
