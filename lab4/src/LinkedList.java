public class LinkedList implements List {
    private Link _first;

    public LinkedList() {
        _first = null;
    }

    public LinkedList(long[] arr) {
        _first = null;
        for (int i = arr.length - 1; i >= 0; i--)
            this.insertFirst(arr[i]);
    }

    public Link find(long val) // Поиск элемента по значению
    {
        Link cur = _first; // начинаем поиск с первого узла
        while (cur.value != val) {
            if (cur.next == null) // если дошли до конца
                return null;
            else
                cur = cur.next; // переходим к следующему узлу
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

    public Link delete(long val) // Удаление элемента с заданным значением
    {
        if (isEmpty())
            return null;
        Link cur = _first;
        Link prev = _first;

        while (cur.value != val) { // поиск нужного элемента
            if (cur.next == null)
                return null;
            else {
                prev = cur;
                cur = cur.next;
            }
        }

        if (cur == _first) // Если удаляемый элемент - первый
            _first = _first.next;
        else
            prev.next = cur.next; // "Пропускаем" его
        return cur;
    }

    public Link deleteKey(int key) // Удаление элемента по индексу
    {
        if (isEmpty())
            return null;
        Link cur = _first;
        Link prev = _first;

        for (int idx = 0; idx < key; idx++) { // поиск нужного элемента
            if (cur.next == null)
                return null;
            else {
                prev = cur;
                cur = cur.next;
            }
        }

        if (cur == _first) // Если удаляемый элемент - первый
            _first = _first.next;
        else
            prev.next = cur.next; // "Пропускаем" его
        return cur;
    }

    public void insertFirst(long value) {
        Link newLink = new Link(value);
        newLink.next = _first; // newLink -> old first
        _first = newLink; // first -> newLink
    }

    public Link deleteFirst() {
        if (isEmpty())
            return null;
        Link temp = _first; // сохраняем ссылку на удаляемый элемент
        _first = _first.next; // удаляем (подменяем первый элемент)
        return temp;
    }

    public boolean isEmpty() {
        return (_first == null);
    }

    public void displayList() {
        System.out.print("LinkedList: ");
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
        while (currX != null && currX.value != x) { // Поиск X
            prevX = currX;
            currX = currX.next;
        }

        Link prevY = null, currY = _first;
        while (currY != null && currY.value != y) { // Поиск Y
            prevY = currY;
            currY = currY.next;
        }

        if (currX == null || currY == null) // Если не нашли
            return;
        
        if (prevX != null) // Если x не первый в списке
            prevX.next = currY;
        else
            _first = currY;

        if (prevY != null) // Если y не первый в списке
            prevY.next = currX;
        else
            _first = currX;

        // Меняем указатели
        Link temp = currX.next;
        currX.next = currY.next;
        currY.next = temp;
    }

}
