public class LinkedList implements List{
    private Link _first;

    public LinkedList() {
        _first = null;
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

        for(int idx = 0; idx < key; idx++) { // поиск нужного элемента
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
        newLink.next = _first; // newLink -> старый first
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
