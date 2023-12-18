import java.util.EventListener;
import java.util.Set;

/**
 * Interface with events that can be triggered by a Kakuro combination generator.
 *
 * @author Tom Verhoeff (TU/e)
 */
public interface GeneratorListener extends EventListener {

    /**
     * Reports a generated combination.
     *
     * @param combination the newly generated combination
     */
    void combinationGenerated(final Set<Integer> combination);

}
