/*
This class works by keeping a moving average as elements are added.
This could be done by calculating the average every time but that would
be less efficient.
 */
public class MovingAverageImpl implements MovingAverage<Long> {

    // start always stays N behind
    private ListElement<Long> start;

    // current always stays at current node
    private ListElement<Long> current;

    // begin never moves from the beginning of the list
    private ListElement<Long> begin;

    private int n;
    private int count = 0;
    Long movingAverage;

    public MovingAverageImpl(int n) {
        this.current = null;
        this.n = n;
        this.movingAverage = 0L;
    }

    public void addElement(Long element) {
        if (current == null) {
            current = new ListElement<>(element);
            start = current;
            begin = current;
            count = 1;
            movingAverage = element;
            return;
        }

        current.setNext(new ListElement<>(element));
        current = current.next();
        count++;

        // if the number of elements is less than N, we do not keep a moving average
        if (count < n) {
            movingAverage = movingAverage + element;

        // if the number of elements is exactly N, the moving average can be calculated
        } else if (count == n) {
            movingAverage = movingAverage + element;
            movingAverage = movingAverage / n;

        // when an element is added, we want to add it to the average,
        // and remove the "first" element N elements back
        } else {
            long sum = movingAverage * n;
            sum = sum - start.value();
            sum = sum + element;
            movingAverage = sum / n;

            start = start.next();
        }
    }

    public ListElement<Long> getElements() {
        return begin;
    }

    public Long getAverage() {
        // in case the number of elements is less than N, we need to divide as we have not done that yet
        // return zero for the un-initialized case
        if (count == 0) {
            return 0L;
        } else if (count < n) {
            return movingAverage / count;
        }

        return movingAverage;
    }
}
