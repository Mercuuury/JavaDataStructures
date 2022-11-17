public class ListIterator {
    private Link _current; // Текущий элемент списка
    private Link _previous; // Предыдущий элемент списка
    private Link _following; // Следующий элемент списка
    private List _ourList; // Связанный список

    public ListIterator(List list) {
        _ourList = list;
        reset();
    }

    public void reset() // Начать с first
    {
        _current = _ourList.getFirst();
        _previous = null;
    }

    public boolean atStart() {
        return (_current.prev == null);
    }

    public boolean atEnd() {
        return (_current.next == null);
    }

    public void moveAtStart() {
        _current = _ourList.getFirst();
    }

    public void moveAtEnd() {
        _current = _ourList.getLast();
    }

    public boolean nextLink() {
        if (!atEnd()) {
            _previous = _current;
            _current = _current.next;
            return true;
        }
        return false;
    }

    public boolean prevLink() {
        if (!atStart()) {
            _following = _current;
            _current = _current.prev;
            return true;
        }
        return false;
    }

    public Link getCurrent() {
        return _current;
    }

    public void insertAfter(long dd) {
        Link newLink = new Link(dd);
        if (_ourList.isEmpty()) {
            _ourList.setFirst(newLink);
            _current = newLink;

        } else {
            newLink.next = _current.next;
            _current.next = newLink;
            nextLink();
        }
    }

    public void insertBefore(long dd) {
        Link newLink = new Link(dd);
        if (_previous == null) { // Если в начале или список пуст
            newLink.next = _ourList.getFirst();
            _ourList.setFirst(newLink);
            reset();
        } else {
            newLink.next = _previous.next;
            _previous.next = newLink;
            _current = newLink;
        }
    }

    public long deleteCurrent() {
        long value = _current.value;
        if (_previous == null) { // Если в начале
            _ourList.setFirst(_current.next);
            reset();
        } else {
            _previous.next = _current.next;
            if (atEnd())
                reset();
            else
                _current = _current.next;
        }
        return value;
    }

}
