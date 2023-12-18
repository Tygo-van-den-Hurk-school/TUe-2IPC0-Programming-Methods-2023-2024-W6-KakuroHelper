/**
 * Generic interface to be implemented by generators, that can be queried for {@code A} objects.
 *
 * @param <A> Type of data items generated.
 * @author Tom Verhoeff (TU/e)
 */
public interface Generator<A> {

    /**
     * Sets the observer.
     * Set to {@code null} to remove observer.
     *
     * @param observer the new observer
     */
    void setObserver(final GeneratorObserver<A> observer);

    /**
     * Gets the current data.
     *
     * @return Current data, as {@link String}
     */
    A getObject();

}
