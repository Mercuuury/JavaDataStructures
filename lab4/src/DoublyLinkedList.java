public class DoublyLinkedList implements List {
    private Link _first;
    private Link _last;

    public DoublyLinkedList() {
        _first = null;
        _last = null;
    }

    public DoublyLinkedList(long[] arr) {
        _first = null;
        _last = null;
        for (long i : arr)
            this.insertLast(i);
    }

    public Link find(long val) // Поиск элемента по значению
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

    public Link findKey(int key) { // Поиск элемента по индексу
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
        else
            _first.prev = newLink;

        newLink.next = _first;
        _first = newLink;
    }

    public void insertLast(long val) {
        Link newLink = new Link(val);
        if (isEmpty())
            _first = newLink;
        else {
            _last.next = newLink;
            newLink.prev = _last;
        }

        _last = newLink;
    }

    public Link deleteFirst() {
        Link temp = _first;
        if (_first.next == null) // Если всего 1 элемент
            _last = null;
        else
            _first.next.prev = null;

        _first = _first.next;
        return temp;
    }

    public Link deleteLast() {
        Link temp = _last;
        if (_first.next == null) // Если всего 1 элемент
            _first = null;
        else
            _last.prev.next = null;

        _last = _last.prev;
        return temp;
    }

    public boolean insertAfter(long val, long newLinkVal) // Вставка элемента после элемента с указанным значением
    {
        if (isEmpty())
            return false;
        Link cur = find(val);

        Link newLink = new Link(newLinkVal);
        if (cur == _last) { // Если вставляем в конец
            newLink.next = null;
            _last = newLink;
        } else {
            newLink.next = cur.next;
            cur.next.prev = newLink;
        }

        newLink.prev = cur;
        cur.next = newLink;
        return true;
    }

    public boolean insertAfterKey(int key, long newLinkVal) // Вставка элемента после элемента с указанным индексом
    {
        if (isEmpty())
            return false;
        Link cur = findKey(key);

        Link newLink = new Link(newLinkVal);
        if (cur == _last) { // Если вставляем в конец
            newLink.next = null;
            _last = newLink;
        } else {
            newLink.next = cur.next;
            cur.next.prev = newLink;
        }

        newLink.prev = cur;
        cur.next = newLink;
        return true;
    }

    public Link delete(long val) // Удаление элемента по значению
    {
        if (isEmpty())
            return null;
        Link cur = find(val);

        if (cur == _first) // Удаляем первый
            _first = cur.next;
        else
            cur.prev.next = cur.next;

        if (cur == _last) // Удаляем последний
            _last = cur.prev;
        else
            cur.next.prev = cur.prev;

        return cur;
    }

    public Link deleteKey(int key) // Удаление элемента по ключу
    {
        if (isEmpty())
            return null;
        Link cur = findKey(key);

        if (cur == _first) // Удаляем первый
            _first = cur.next;
        else
            cur.prev.next = cur.next;

        if (cur == _last) // Удаляем последний
            _last = cur.prev;
        else
            cur.next.prev = cur.prev;

        return cur;
    }

    public boolean isEmpty() {
        return (_first == null);
    }

    public void displayForward() {
        System.out.print("DoublyLinkedList (first-->last): ");
        Link cur = _first;
        while (cur != null) {
            cur.displayLink();
            cur = cur.next;
        }
        System.out.println();
    }

    public void displayBackward() {
        System.out.print("DoublyLinkedList (last-->first): ");
        Link cur = _last;
        while (cur != null) {
            cur.displayLink();
            cur = cur.prev;
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
        if (isEmpty() || x == y)
            return;

        Link Link1 = find(x);
        Link Link2 = find(y);

        if (Link1 == _first)
            _first = Link2;
        else if (Link2 == _first)
            _first = Link1;
        if (Link1 == _last)
            _last = Link2;
        else if (Link2 == _last)
            _last = Link1;

        Link temp;
        temp = Link1.next;
        Link1.next = Link2.next;
        Link2.next = temp;

        if (Link1.next != null)
            Link1.next.prev = Link1;
        if (Link2.next != null)
            Link2.next.prev = Link2;

        temp = Link1.prev;
        Link1.prev = Link2.prev;
        Link2.prev = temp;

        if (Link1.prev != null)
            Link1.prev.next = Link1;
        if (Link2.prev != null)
            Link2.prev.next = Link2;
    }
}
