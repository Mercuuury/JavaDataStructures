public class DoublyLinkedList implements List {
    private Link _first;
    private Link _last;

    public DoublyLinkedList() {
        _first = null;
        _last = null;
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
        System.out.print("List (first-->last): ");
        Link cur = _first;
        while (cur != null) {
            cur.displayLink();
            cur = cur.next;
        }
        System.out.println();
    }

    public void displayBackward() {
        System.out.print("List (last-->first): ");
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

    public void setFirst(Link f) {
        _first = f;
    }

    public ListIterator getIterator() {
        return new ListIterator(this); // Инициализация списком this
    }
}
