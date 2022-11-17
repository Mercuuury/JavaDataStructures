public class SortedList implements List{
    private Link _first;

    public SortedList() {
        _first = null;
    }
    
    public SortedList(long[] arr) {
        _first = null;
        for (long i : arr) {
            this.insert(i);
        } 
    }

    public Link find(long val) // Поиск элемента с заданным значением
    {
        Link cur = _first;
        while (cur.value != val) {
            if (cur.next == null)
                return null;
            else
                cur = cur.next;
        }
        return cur;
    }

    public Link findKey(int key) // Поиск элемента по индексу
    {
        Link cur = _first;
        int idx;

        for (idx = 0; idx < key; idx++) {
            if (cur.next == null)
                return null;
            else
                cur = cur.next;
        }

        return cur;
    }

    public void insert(long val) {
        Link newLink = new Link(val);
        Link prev = null;
        Link cur = _first;

        while (cur != null && val > cur.value) {
            prev = cur;
            cur = cur.next;
        }

        if (prev == null) // Если вставка в начале списка
            _first = newLink;
        else
            prev.next = newLink;

        newLink.next = cur;
    }

    public Link remove() // Удалить первый элемент
    {
        if (isEmpty())
            return null;
        Link temp = _first;
        _first = _first.next;
        return temp;
    }

    public boolean isEmpty() {
        return (_first == null);
    }

    public void displayList() {
        System.out.print("SortedList: ");
        Link current = _first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println();
    }

    public Link getFirst() {
        return _first;
    }

    public Link getLast() {
        Link cur = _first;
        while (cur.next != null)
            cur = cur.next;
        return cur;
    }

    public void setFirst(Link f) {
        _first = f;
    }

    public ListIterator getIterator() {
        return new ListIterator(this); // Инициализация списком this
    }

    public void swap(long x, long y) {
        if (x == y)
            return;
        
        Link prevX = null, currX = _first;
        while (currX != null && currX.value != x) {
            prevX = currX;
            currX = currX.next;
        }

        Link prevY = null, currY = _first;
        while (currY != null && currY.value != y) {
            prevY = currY;
            currY = currY.next;
        }

        if (currX == null || currY == null)
            return;
        
        if (prevX != null) // Если x не первый
            prevX.next = currY;
        else
            _first = currY;

        if (prevY != null) // Если y не первый
            prevY.next = currX;
        else
            _first = currX;

        Link temp = currX.next;
        currX.next = currY.next;
        currY.next = temp;
    }
}
