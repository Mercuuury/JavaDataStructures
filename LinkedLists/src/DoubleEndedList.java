public class DoubleEndedList implements List{
    private Link _first;
    private Link _last;

    public DoubleEndedList() {
        _first = null;
        _last = null;
    }

    public DoubleEndedList(long[] arr) {
        _first = null;
        _last = null;
        for (long i : arr)
            this.insertLast(i);
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

    public void insertFirst(long val) {
        Link newLink = new Link(val);
        if (isEmpty())
            _last = newLink;
        newLink.next = _first;
        _first = newLink;
    }

    public void insertLast(long val) {
        Link newLink = new Link(val);
        if (isEmpty())
            _first = newLink;
        else
            _last.next = newLink;
        _last = newLink;
    }

    public double deleteFirst() {
        if (isEmpty())
            return -1;
        double temp = _first.value;
        if (_first.next == null) // Если всего 1 элемент
            _last = null;
        _first = _first.next;
        return temp;
    }

    public boolean isEmpty() {
        return (_first == null);
    }

    public void displayList() {
        System.out.print("DoublyEndedList: ");
        Link cur = _first;
        while (cur != null) {
            cur.displayLink();
            cur = cur.next;
        }
        System.out.println();
    }

    public Link getFirst() {
        return _first;
    }
    
    public Link getLast() {
        return _last;
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

        if (currX.next == null) // Если x последний
            _last = currY;

        if (currX.next == null) // Если y последний
            _last = currX;
    }
}
