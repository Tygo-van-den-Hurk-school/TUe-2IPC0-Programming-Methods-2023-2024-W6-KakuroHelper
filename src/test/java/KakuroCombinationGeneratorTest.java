import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Test cases for Kakuro combination generator.
 *
<!--//# BEGIN TODO: Name, student ID, and date-->
<p><b>Tygo van den Hurk, 1705709, 17-dec-2023</b></p>
<!--//# END TODO-->
 */
public class KakuroCombinationGeneratorTest {

    /**
     * The subject under test.
     */
    private KakuroCombinationGenerator instance;

    /**
     * The listener.
     */
    private Checker checker;

    /**
     * Set up tests.
     */
    @BeforeEach
    public void setUp() {
        System.out.println("\nsetUp()");
        this.instance = new KakuroCombinationGenerator();
        this.checker = new Checker();
        final PushPullAdapter checkerAdapter = new PushPullAdapter(checker, true);
        this.instance.setObserver(checkerAdapter);
    }

    // Tests of generate method, of class KakuroCombinationGenerator.

    /**
     * Calls {@code KakuroCombinationGenerator.generate(s, n)},
     * and checks the result, expecting {@code expCount} combinations.
     *
     * @param n        number of digits for extension
     * @param s        digit sum for extension
     * @param expCount expected count
     */
    public void checkGenerator(final int s, final int n, final int expCount) {
        System.out.println("generate(" + s + ", " + n + ")");
        System.out.println("  should generate " + expCount + " combination"
                + ((expCount == 1) ? "" : "s"));
        this.checker.set(s, n);
        this.instance.generate(s, n);
        if (expCount != checker.count) {
            System.out.println("  but generated " + checker.count + " combination"
                    + ((checker.count == 1) ? "" : "s"));
        }
        System.out.println("Call count for generate() = " + instance.count);
        assertEquals(expCount, checker.count, "Number of combinations");
    }

    /**
     * Boundary case: minimal s that still works.
     */
    @Test
    public void testGeneratorMinimal0() {
        this.checkGenerator(0, 0, 1);
    }

    /**
     * Boundary case: minimal s that just does not work.
     */
    @Test
    public void testGeneratorMinimal1() {
        this.checkGenerator(0, 1, 0);
    }

    //# BEGIN TODO: Further test cases

    /**
     * Boundary case: s that just does not work.
     */
    @Test
    public void testGeneratorMinimal2() {
        this.checkGenerator(1, 0, 0);
    }

    /** 
     * Boundary case: s larger then max.
     */
    @Test
    public void testGeneratorSumLargerThenMax() {
        final int numberLargerThenMax = instance.getMaxNumber() + 1;
        this.checkGenerator(numberLargerThenMax, 1, 0);
    }

    /** Boundary case: since no number can be repeated, the max sum is 45. */
    @Test
    public void testGeneratorSumToLarge() {
        this.checkGenerator(420, 69, 0);
    }
    
    /** normal case: should work. */
    @Test
    public void testGeneratorNormal1() {
        this.checkGenerator(1, 1, 1);
    }

    @Test
    public void testGeneratorNormal2() {
        this.checkGenerator(2 + 1, 2, 1);
    }
    
    @Test
    public void testGeneratorNormal3() {
        this.checkGenerator(3 + 2 + 1, 3, 1);
    }

    /** normal case: should work. */
    @Test
    public void testGeneratorNormal4() {
        this.checkGenerator(4 + 3 + 2 + 1, 4, 1);
    }

    @Test
    public void testGeneratorNormal5() {
        this.checkGenerator(5 + 4 + 3 + 2 + 1, 5, 1);
    }
    
    @Test
    public void testGeneratorNormal6() {
        this.checkGenerator(6 + 5 + 4 + 3 + 2 + 1, 6, 1);
    }

    /** normal case: should work. */
    @Test
    public void testGeneratorNormal7() {
        this.checkGenerator(7 + 6 + 5 + 4 + 3 + 2 + 1, 7, 1);
    }

    /** normal case: should work. */
    @Test
    public void testGeneratorNewMax1() {
        this.instance.setMaxNumber(10);
        this.checkGenerator(55, 10, 1);
    }

    /** normal case: should work. */
    @Test
    public void testGeneratorNewMax2() {
        this.instance.setMaxNumber(25);
        this.checkGenerator(100, 10, 18854);
    }
    
    //# END TODO
    // Auxiliary definitions

    /**
     * Listener that checks that each generated combination
     * has indeed the expected sum and length,
     * and that they appear in lexicographic order.
     */
    private class Checker implements GeneratorListener {

        /**
         * Whether to print combination (2), a dot (1) , or nothing (0).
         */
        public int verbosity;

        /**
         * Number of reported combinations.
         */
        public int count;

        /**
         * Expected sum.
         */
        private int s;

        /**
         * Expected number.
         */
        private int n;

        /**
         * Preceding generated combination.
         */
        private Set<Integer> preceding;

        /**
         * Creates a default initialized checker.
         */
        public Checker() {
            this.verbosity = 2; // default
            this.count = 0;
            this.preceding = null;
        }

        /**
         * Sets the expected sum and length.
         *
         * @param s expected sum
         * @param n expected length
         */
        public void set(final int s, final int n) {
            this.s = s;
            this.n = n;
        }

        @Override
        public void combinationGenerated(final Set<Integer> combination) {
            this.count++;
            switch (this.verbosity) {
                case 1 -> System.out.print(".");
                case 2 -> System.out.println(combination);
                default -> { }
            }
            
            assertAll(

                    () -> assertTrue(precedes(preceding, combination), (
                        KakuroCombinationGeneratorTest.this.getClass().getSimpleName() + "."
                        + this.getClass().getSimpleName() 
                        + ".combinationGenerated(Set<Integer>) failed, "    
                        + "the lexicographic order was not respected."
                        + "preceding was: \"" + preceding + "\", "
                        + "combination was: \"" + combination + "\"."
                        + "preceding was supposed to precede combination but it did not.")),
                        
                    () -> assertTrue(max(combination) <= instance.getMaxNumber(), (
                        KakuroCombinationGeneratorTest.this.getClass().getSimpleName() + "."
                        + this.getClass().getSimpleName() 
                        + ".combinationGenerated(Set<Integer>) failed, "
                        + "the max of the combination (" + max(combination) + "), "
                        + "is not smaller or equal to the expected max ("
                        + instance.getMaxNumber() + ").")),

                    () -> assertTrue(1 <= min(combination), (
                        KakuroCombinationGeneratorTest.this.getClass().getSimpleName() + "."
                        + this.getClass().getSimpleName() 
                        + ".combinationGenerated(Set<Integer>) failed, "
                        + "the sum of the combination (" + sum(combination) + "), "
                        + "is not equal to the expected sum (" + s + ").")),
                    
                    () -> assertEquals(s, sum(combination), (
                        KakuroCombinationGeneratorTest.this.getClass().getSimpleName() + "."
                        + this.getClass().getSimpleName() 
                        + ".combinationGenerated(Set<Integer>) failed, "
                        + "the sum of the combination (" + sum(combination) + "), "
                        + "is not equal to the expected sum (" + s + ").")),

                    () -> assertEquals(n, combination.size(), (
                        KakuroCombinationGeneratorTest.this.getClass().getSimpleName() + "."
                        + this.getClass().getSimpleName() 
                        + ".combinationGenerated(Set<Integer>) failed, "
                        + "the number of the combinations (" + combination.size() + "), "
                        + "is not equal to the expected number of combinations (" + n + ")."))
            );

            this.preceding = new HashSet<>(combination); // NEEDS COPY!
        }
    }

    /**
     * Returns sum of given set of integers.
     *
     * @param c set of integers, not null
     * @return sum of integers in {@code c}
     */
    private int sum(final Set<Integer> c) {
        int result = 0;
        for (int i : c) {
            result += i;
        }
        return result;
    }

    /**
     * Returns minimum of given set of integers.
     *
     * @param c set of integers, not null
     * @return minimum of integers in {@code c}
     */
    private int min(final Set<Integer> c) {
        int result = Integer.MAX_VALUE;
        for (int i : c) {
            if (i < result) {
                result = i;
            }
        }
        return result;
    }

    /**
     * Returns maximum of given set of integers.
     *
     * @param c set of integers, not null
     * @return maximum of integers in {@code c}
     */
    private int max(final Set<Integer> c) {
        int result = Integer.MIN_VALUE;
        for (int i : c) {
            if (i > result) {
                result = i;
            }
        }
        return result;
    }

    /**
     * Determines whether one integer set lexicographically precedes another.
     * Null precedes every non-null set.
     *
     * @param c first set of integers
     * @param d second set of integers
     * @return whether {@code c} strictly precedes {@code d}
     */
    private boolean precedes(final Set<Integer> c, final Set<Integer> d) {
        if (c == null | d == null) {
            return c == null && d != null;
        }
        // c != null && d != null
        for (int i = 1; i < instance.getMaxNumber(); i++) {
            if (c.contains(i) != d.contains(i)) {
                return c.contains(i);
            }
        }
        return false;
    }
}
