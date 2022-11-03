public class SortedList implements List{
    private Link _first;

    public SortedList() {
        _first = null;
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
        System.out.print("List: ");
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

    public void setFirst(Link f) {
        _first = f;
    }

    public ListIterator getIterator() {
        return new ListIterator(this); // Инициализация списком this
    }
}
