import java.util.Set;

/**
 * Adapter that adapts an object of a class implementing {@link GeneratorListener} (which works by
 * pushing) to act like a class implementing {@code GeneratorObserver<Set<Integer>>} (which works by
 * pulling). Client code of this adapter can decide that pulling is not needed, when the listener
 * being adapted is not using the pushed data (see the pull parameter in the constructor). This
 * could improve efficiency.
 *
<!--//# BEGIN TODO: Name, student ID, and date-->
<p><b>Tygo van den Hurk, 1705709, 17-dec-2023</b></p>
<!--//# END TODO-->
 */
public class PushPullAdapter implements GeneratorObserver<Set<Integer>> {

    /**
     * The adapted listener.
     */
    private final GeneratorListener listener;

    /**
     * Whether to pull objects and pass them on, or to ignore them.
     */
    private final boolean pull;

    /**
     * Constructs a new {@link PushPullAdapter} that optionally
     * suppresses data pulling to improve performance.
     *
     * @param listener observer for the generator
     * @param pull     whether to pull the object
     */
    public PushPullAdapter(final GeneratorListener listener, final boolean pull) {
        this.listener = listener;
        this.pull = pull;
    }

    /**
     * If pulling needed, pull the data and pass it on the listener interface;
     * if not pulling, pass {@code null} on the listener interface.
     *
     * @param generator the originating generator
     */
    @Override
    public void objectGenerated(final Generator<Set<Integer>> generator) {
    
        //# BEGIN TODO: Implementation of objectGenerated

        if (this.pull) { // if pulling is needed:
            this.listener.combinationGenerated(generator.getObject());
        
        } else { // if pushing is needed:
            this.listener.combinationGenerated(null);
        }

        //# END TODO
    }

}
