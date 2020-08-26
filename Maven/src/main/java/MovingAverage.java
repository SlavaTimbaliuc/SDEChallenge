public interface MovingAverage<T> {
    void addElement(T element);

    ListElement<T> getElements();

    T getAverage();
}
