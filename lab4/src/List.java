public interface List {
    Link getFirst();

    void setFirst(Link f);

    ListIterator getIterator();

    boolean isEmpty();
}
