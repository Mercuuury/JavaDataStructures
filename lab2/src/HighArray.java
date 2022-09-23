public class HighArray implements Cloneable {
    private long[] a; // Ссылка на массив a
    private int nElems; // Количество элементов

    public HighArray(int size) // Конструктор
    {
        this.a = new long[size]; // Создание массива
        this.nElems = 0;
    }

    public void insert(int value) // Вставка элемента
    {
        this.a[nElems++] = value;
    }

    public void print() {
        for (int i = 0; i < this.nElems; i++) {
            System.out.print(this.a[i] + " ");
        }
        System.out.println();
    }

    public HighArray clone() throws CloneNotSupportedException {
        HighArray newHighArray = (HighArray) super.clone();
        newHighArray.a = (long[]) a.clone();
        return newHighArray;
    }

    public void bubbleSort() {
        int out, in;
        for (out = this.nElems - 1; out > 1; out--) // Внешний цикл (обратный)
            for (in = 0; in < out; in++) // Внутренний цикл (прямой)
                if (this.a[in] > this.a[in + 1]) // Порядок нарушен?
                    swap(in, in + 1); // Поменять местами
    }

    public void selectionSort() {
        int out, in, min;
        for (out = 0; out < this.nElems - 1; out++) // Внешний цикл
        {
            min = out;
            for (in = out + 1; in < this.nElems; in++) // Внутренний цикл
                if (this.a[in] < this.a[min]) // Если значение меньше min, устанавливаем новый min
                    min = in;
            swap(out, min); // Меняем местами
        }
    }

    public void insertionSort() {
        int in, out;
        for (out = 1; out < this.nElems; out++) // out is dividing line
        {
            long temp = this.a[out]; // remove marked item
            in = out; // start shifts at out
            while (in > 0 && this.a[in - 1] >= temp) // until one is smaller,
            {
                this.a[in] = this.a[in - 1]; // shift item right,
                --in; // go left one position
            }
            this.a[in] = temp; // insert marked item
        }
    }

    public void quickSort() {
        recQuickSort(0, nElems - 1);
    }

    public void recQuickSort(int left, int right) {
        if (right - left <= 0) // if size <= 1,
            return; // already sorted
        else // size is 2 or larger
        {
            long pivot = this.a[right]; // rightmost item
            // partition range
            int partition = partitionIt(left, right, pivot);
            recQuickSort(left, partition - 1); // sort left side
            recQuickSort(partition + 1, right); // sort right side
        }
    } // end recQuickSort()

    public int partitionIt(int left, int right, long pivot) {
        int leftPtr = left - 1; // left (after ++)
        int rightPtr = right; // right-1 (after --)
        while (true) { // find bigger item
            while (this.a[++leftPtr] < pivot)
                ; // (nop)
                  // find smaller item
            while (rightPtr > 0 && this.a[--rightPtr] > pivot)
                ; // (nop)
            if (leftPtr >= rightPtr) // if pointers cross,
                break; // partition done
            else // not crossed, so
                swap(leftPtr, rightPtr); // swap elements
        } // end while(true)
        swap(leftPtr, right); // restore pivot
        return leftPtr; // return pivot location
    } // end partitionIt(

    private void swap(int one, int two) {
        long temp = this.a[one];
        this.a[one] = this.a[two];
        this.a[two] = temp;
    }

}
