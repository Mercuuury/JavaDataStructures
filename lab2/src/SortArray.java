public class SortArray implements Cloneable {
    private int[] a; // Ссылка на массив a
    private int nElems; // Количество элементов

    public SortArray(int size) // Конструктор
    {
        this.a = new int[size]; // Создание массива
        this.nElems = 0;
    }

    public void insert(int value) // Вставка элемента
    {
        this.a[nElems++] = value;
    }

    public void print() // Распечатать массив
    {
        for (int i = 0; i < this.nElems; i++) {
            System.out.print(this.a[i] + " ");
        }
        System.out.println();
    }

    public int size() // Размер массива
    {
        return nElems;
    }

    public SortArray clone() throws CloneNotSupportedException { // Клонировать массив
        SortArray newSortArray = (SortArray) super.clone();
        newSortArray.a = (int[]) a.clone();
        return newSortArray;
    }

    public void insertionSort() // Сортировка вставками
    {
        for (int left = 0; left < this.nElems; left++) {
            int value = this.a[left]; // Сохраняем значение вынутого элемента
            int i = left - 1; // Перемещаемся по элементам до вынутого
            for (; i >= 0; i--) {
                if (value < this.a[i]) { // Если вытащили значение меньшее — передвигаем больший элемент дальше
                    this.a[i + 1] = this.a[i];
                } else { // Иначе - вытащили значение большее. Остановка
                    break;
                }
            }
            this.a[i + 1] = value; // В освободившееся место вставляем вытащенное значение
        }
    }

    public void selectionSort() // Сортировка выбором
    {
        int out, in, min;
        for (out = 0; out < this.nElems - 1; out++) {
            min = out;
            for (in = out + 1; in < this.nElems; in++)
                if (this.a[in] < this.a[min]) // Если значение меньше min, устанавливаем новый min
                    min = in;
            swap(out, min); // Меняем местами
        }
    }

    public void mergeSort() // Сортировка слиянием (метод экземпляра класса)
    {
        mergeSort(this.a);
    }

    private static void mergeSort(int[] inputArray) // Сортировка слиянием (метод класса)
    {
        if (inputArray.length < 2) // Если размер массива 1 - он отсортирован
            return;
        // Делим массив пополам
        int midIndex = inputArray.length / 2;
        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[inputArray.length - midIndex];
        // Наполняем подмассивы
        for (int i = 0; i < midIndex; i++)
            leftHalf[i] = inputArray[i];
        for (int i = midIndex; i < inputArray.length; i++)
            rightHalf[i - midIndex] = inputArray[i];
        // Рекурсивно сортируем подмассивы
        mergeSort(leftHalf);
        mergeSort(rightHalf);
        merge(inputArray, leftHalf, rightHalf); // Обьединяем
    }

    private static void merge(int[] inputArray, int[] leftHalf, int[] rightHalf) // Слияние подмассивов
    {
        int i = 0, j = 0, k = 0;
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;
        while (i < leftSize && j < rightSize) { // Проходим по обоим подмассивам
            if (leftHalf[i] <= rightHalf[j]) { // Взвешиваем элементы обоих массивов и записываем меньший
                inputArray[k] = leftHalf[i];
                i++;
            } else {
                inputArray[k] = rightHalf[j];
                j++;
            }
            k++;
        }
        // Объединяем подмассивы с исходным
        while (i < leftSize) {
            inputArray[k] = leftHalf[i];
            i++;
            k++;
        }
        while (j < rightSize) {
            inputArray[k] = rightHalf[j];
            j++;
            k++;
        }
    }

    public void quickSort() // Быстрая сортировка
    {
        recQuickSort(0, this.nElems - 1);
    }

    private void recQuickSort(int left, int right) // Рекурсивный метод быстрой сортировки
    {
        if (right - left <= 0) // Если размер <= 1,
            return; // массив отсортирован
        else {
            int pivot = this.a[right]; // опорный элемент
            int partition = partitionIt(left, right, pivot); // индекс разбиения
            recQuickSort(left, partition - 1);
            recQuickSort(partition + 1, right);
        }
    }

    private int partitionIt(int left, int right, int pivot) // Разбиение массива
    {
        int LPointer = left - 1;
        int RPointer = right;
        while (true) {
            while (this.a[++LPointer] < pivot)
                ; // Находим больший элемент
            while (RPointer > 0 && this.a[--RPointer] > pivot)
                ; // Находим меньший элемент
            if (LPointer >= RPointer) // если указатели пересеклись
                break;
            else
                swap(LPointer, RPointer); // меняем элементы под указателями
        }
        swap(LPointer, right); // меняем элементы под левым указателем и правой границой
        return LPointer; // возвращаем индекс разбиения (левый элемент правого подмассива)
    }

    private void swap(int one, int two) // Обмен элементов массива местами
    {
        int temp = this.a[one];
        this.a[one] = this.a[two];
        this.a[two] = temp;
    }
}
