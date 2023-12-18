import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Smoke tests for {@link CompositeGeneratorObserver}.
 *
 * @author Tom Verhoeff (TU/e)
 */
public class CompositeGeneratorObserverTest {

    /**
     * Generator of strings.
     */
    private Generator<String> generator;

    /**
     * Composite observer under test.
     */
    private CompositeGeneratorObserver<String> instance;

    /**
     * Setup smoke tests.
     */
    @BeforeEach
    public void setUp() {
        System.out.println("\nsetUp()");
        generator = new Generator<>() {

            @Override
            public void setObserver(GeneratorObserver<String> observer) {
                // ignored on purpose
            }

            @Override
            public String getObject() {
                return null; // on purpose
            }
        };
        instance = new CompositeGeneratorObserver<>();
    }

    /**
     * Tests doB() for composite with no registered callbacks. Checks that nothing goes wrong.
     */
    @Test
    public void test0() {
        System.out.println("objectGenerated, w/o observers");
        instance.objectGenerated(generator);
        assertTrue(true);
    }

    /**
     * Tests add() and doB() for composite with one registered callback. Checks that a data item is
     * passed on.
     */
    @Test
    public void test1() {
        System.out.println("objectGenerated, with 1 observer");
        final Generator<String> expected = generator;
        class MyGeneratorObserver implements GeneratorObserver<String> {

            public int called = 0;

            @Override
            public void objectGenerated(Generator<String> generator) {
                assertEquals(expected, generator, "generator");
                this.called++;
                // N.B. cannot change local variable of enclosing method
            }
        }

        MyGeneratorObserver observer = new MyGeneratorObserver();
        instance.add(observer);
        instance.objectGenerated(expected);
        assertEquals(1, observer.called, "Observer call count");
    }

    /**
     * Tests add() and doB() for composite with two registered callbacks.
     */
    @Test
    public void testAdd2() {
        System.out.println("objectGenerated, with 2 observers");
        final Generator<String> expected = generator;
        class MyGeneratorObserver implements GeneratorObserver<String> {

            public int called = 0;

            @Override
            public void objectGenerated(Generator<String> generator) {
                assertEquals(expected, generator, "generator");
                this.called++;
                // N.B. cannot change local variable of enclosing method
            }
        }

        MyGeneratorObserver observer1 = new MyGeneratorObserver();
        MyGeneratorObserver observer2 = new MyGeneratorObserver();
        instance.add(observer1);
        instance.add(observer2);
        instance.objectGenerated(expected);
        assertAll(
                () -> assertEquals(1, observer1.called, "Observer1 call count"),
                () -> assertEquals(1, observer2.called, "Observer2 call count")
        );
    }

    /**
     * Tests add() for IllegalArgumentException.
     */
    @Test
    public void testAddException() {
        System.out.println("add(null)");
        try {
            instance.add(null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail("Expected IllegalArgumentException, but got " + e);
        }
    }

    /**
     * Tests remove() with non-registered callback.
     */
    @Test
    public void testRemove0() {
        System.out.println("remove(non-registered observer)");
        final GeneratorObserver<String> observer = generator -> {};
        instance.remove(observer);
        assertTrue(true);
    }

    /**
     * Tests remove() with registered callback. Checks that removed callback is not triggered.
     */
    @Test
    public void testRemove1() {
        System.out.println("remove(registered observer)");
        final GeneratorObserver<String> observer = generator -> fail("objectGenerated called");
        instance.add(observer);
        instance.remove(observer);
        instance.objectGenerated(generator);
        assertTrue(true);
    }

}
