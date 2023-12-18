import java.util.HashSet;
import java.util.Set;

/**
 * Observer (listener) that calculates the intersection of all combinations, or of their complements
 * (the eliminated numbers); that is, the set of numbers common to all generated combinations, or
 * missing in all generated combinations.
 *
<!--//# BEGIN TODO: Name, student ID, and date-->
<p><b>Tygo van den Hurk, 1705709, 17-dec-2023</b></p>
<!--//# END TODO-->
 */
public class Intersector implements GeneratorListener {

    /**
     * Whether to maintain the intersection of the combination complements, rather than of the
     * combinations themselves.
     */
    private final boolean complement;

    /**
     * Intersection so far.
     */
    private final Set<Integer> intersection;

    /**
     * Constructs initialized Intersector.
     *
     * @param maxNumber  largest number that can occur
     * @param complement whether to intersect complements
     */
    public Intersector(final int maxNumber, final boolean complement) {
        this.intersection = new HashSet<>();
        for (int i = 1; i <= maxNumber; i++) {
            this.intersection.add(i);
        }
        this.complement = complement;
    }

    /**
     * Gets current intersection.
     *
     * @return current intersection
     */
    public Set<Integer> getIntersection() {
        return intersection; // leaks representation; breaks encapsulation
        // alternative: return new HashSet(intersection);
    }

    @Override
    public void combinationGenerated(Set<Integer> combination) {
//# BEGIN TODO: Implementation of combinationGenerated
// Replace this line
//# END TODO
    }

}
