public interface List {
    ListIterator getIterator();

    Link getFirst();

    Link getLast();

    void setFirst(Link f);

    boolean isEmpty();
}
