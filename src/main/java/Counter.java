import java.util.Set;

/**
 * Listener that counts the objects generated.
 *
 * @author Tom Verhoeff (TU/e)
 */
public class Counter implements GeneratorListener {

    /**
     * Number of objects generated.
     */
    private int count = 0;

    /**
     * Gets the current count.
     *
     * @return current count
     */
    public int getCount() {
        return count;
    }

    @Override
    public void combinationGenerated(final Set<Integer> combination) {
        count++;
    }

}
