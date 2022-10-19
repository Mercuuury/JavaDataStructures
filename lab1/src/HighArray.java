public class HighArray implements Array {
    private long[] a; // Ссылка на массив a
    private int nElems; // Количество элементов

    public HighArray(int size) // Конструктор
    {
        a = new long[size]; // Создание массива
        nElems = 0;
    }

    public void insert(int value) // Вставка элемента
    {
        a[nElems++] = value;
    }

    public int find(long searchKey) // Поиск элемента
    {
        int j;
        for (j = 0; j < nElems; j++) // Для каждого элемента
            if (a[j] == searchKey) // Значение найдено?
                break; // Да - выход из цикла
        if (j == nElems) // Достигнут последний элемент?
            return -1; // Да
        else
            return j; // Нет
    }

    public boolean delete(long value) // Удаление элемента
    {
        int j;
        for (j = 0; j < nElems; j++) // Поиск заданного значения
            if (value == a[j])
                break;
        if (j == nElems) // Найти не удалось
            return false;
        else { // Значение найдено
            for (int k = j; k < nElems - 1; k++) // Сдвиг последующих элементов
                a[k] = a[k + 1];
            nElems--; // Уменьшение размера
            return true;
        }
    }

    public void print() {
        for (int i = 0; i < nElems; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public int size() {
        return nElems;
    }
}
