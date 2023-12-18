import java.util.ArrayList;
import java.util.List;

/**
 * A composite observer that distributes events to all registered observers.
 * Observers can be added and removed at run-time.
 *
 * @param <A> Type of generator
 *
<!--//# BEGIN TODO: Name, student ID, and date-->
<p><b>Tygo van den Hurk, 1705709, 17-dec-2023</b></p>
<!--//# END TODO-->
 */
public class CompositeGeneratorObserver<A> implements GeneratorObserver<A> {

    /**
     * List of registered observers.
     */
    private final List<GeneratorObserver<A>> observers;

    // Representation invariant:
    //    (\forall obs : observers; obs != null)

    /**
     * Constructs an empty composite observer.
     */
    public CompositeGeneratorObserver() {
        this.observers = new ArrayList<>();
    }

    /**
     * Registers an observer.
     *
     * @param observer observer for functionality B to register
     * @pre {@code observer != null}
     * @throws IllegalArgumentException if {@code observer == null}
     */
    public void add(final GeneratorObserver<A> observer) {
        if (observer == null) {
            throw new IllegalArgumentException(
                    "GeneratorObserver.add: pre observer != null failed");
        }
        this.observers.add(observer);
    }

    /**
     * Deregisters an observer.
     * Has no effect if observer was not registered.
     *
     * @param observer observer to deregister
     */
    public void remove(final GeneratorObserver<A> observer) {
        this.observers.remove(observer);
    }

    /**
     * Distributes notification to all registered observers.
     */
    @Override
    public void objectGenerated(Generator<A> generator) {
//# BEGIN TODO: Implementation of objectGenerated
// Replace this line
//# END TODO
    }

}
