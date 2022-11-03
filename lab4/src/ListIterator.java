public class ListIterator {
    private Link _current; // Текущий элемент списка
    private Link _previous; // Предыдущий элемент списка
    private Link _following; // Следующий элемент списка
    private List _ourList; // Связанный список

    public ListIterator(List list) {
        _ourList = list;
        reset();
    }

    public void reset() // start at ‘first’
    {
        _current = _ourList.getFirst();
        _previous = null;
    }

    public boolean atStart() // true if first link
    {
        return (_current.prev == null);
    }

    public boolean atEnd() // true if last link
    {
        return (_current.next == null);
    }

    public boolean nextLink() // go to next link
    {
        if(!atEnd()) {
            _previous = _current;
            _current = _current.next;
            return true;
        }
        return false;
    }

    public boolean prevLink() // go to prev link
    {
        if(!atStart()) {
            _following = _current;
            _current = _current.prev;
            return true;
        }
        return false;
    }

    public Link getCurrent() // get current link
    {
        return _current;
    }

    public void insertAfter(long dd) // insert after
    { // current link
        Link newLink = new Link(dd);
        if (_ourList.isEmpty()) // empty list
        {
            _ourList.setFirst(newLink);
            _current = newLink;

        } else // not empty
        {
            newLink.next = _current.next;
            _current.next = newLink;
            nextLink(); // point to new link
        }
    }

    public void insertBefore(long dd) // insert before
    { // current link
        Link newLink = new Link(dd);
        if (_previous == null) // beginning of list
        { // (or empty list)
            newLink.next = _ourList.getFirst();
            _ourList.setFirst(newLink);
            reset();
        } else // not beginning
        {
            newLink.next = _previous.next;
            _previous.next = newLink;
            _current = newLink;
        }
    }

    public long deleteCurrent() // delete item at current
    {
        long value = _current.value;
        if (_previous == null) // beginning of list
        {
            _ourList.setFirst(_current.next);
            reset();
        } else // not beginning
        {
            _previous.next = _current.next;
            if (atEnd())
                reset();
            else
                _current = _current.next;
        }
        return value;
    }

}
