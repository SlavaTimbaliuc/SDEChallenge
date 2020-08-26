import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovingAverageTests {

    private MovingAverage<Long> avg;

    @Before
    public void setup() {
        avg = new MovingAverageImpl(5);
    }

    @Test
    public void getElementTest() {
        avg.addElement(1L);
        avg.addElement(2L);
        avg.addElement(3L);
        avg.addElement(4L);
        avg.addElement(5L);

        Long element = 1L;
        assertEquals(element, avg.getElements().value());
    }

    @Test
    public void sameElementsAsN() {
        avg.addElement(1L);
        avg.addElement(2L);
        avg.addElement(3L);
        avg.addElement(4L);
        avg.addElement(5L);

        Long average = 3L;
        assertEquals(average, avg.getAverage());
    }

    @Test
    public void lessElementsThanN() {
        avg.addElement(2L);
        avg.addElement(2L);

        Long average = 2L;
        assertEquals(avg.getAverage(), average);
    }

    @Test
    public void moreElementsThanN() {
        avg.addElement(1L);
        avg.addElement(2L);
        avg.addElement(3L);
        avg.addElement(4L);
        avg.addElement(5L);
        avg.addElement(6L);
        avg.addElement(7L);

        Long average = 5L;
        assertEquals(average, avg.getAverage());

        avg.addElement(8L);
        Long newAverage = 6L;
        assertEquals(newAverage, avg.getAverage());
    }

}
