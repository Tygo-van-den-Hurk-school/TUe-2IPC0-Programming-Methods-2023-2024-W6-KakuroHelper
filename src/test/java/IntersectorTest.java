import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Test cases for class Intersector.
 *
 * @author Tom Verhoeff (TU/e)
 */
public class IntersectorTest {

    @BeforeEach
    public void setUp() {
        System.out.println("\nsetUp()");
    }

    /**
     * Test of getIntersection method, of class Intersector(m, false).
     */
    @Test
    public void testGetIntersectionFalse() {
        System.out.println("getIntersection, complement = false");
        final Intersector instance = new Intersector(3, false);
        System.out.println("new Intersector(3, false)");
        final Set<Integer> expResult = new HashSet<>(Arrays.asList(1, 2, 3));
        final Set<Integer> result = instance.getIntersection();
        assertEquals(expResult, result, "getIntersection()");
    }

    /**
     * Test of getIntersection method, of class Intersector(m, true).
     */
    @Test
    public void testGetIntersectionTrue() {
        System.out.println("getIntersection, complement = true");
        final Intersector instance = new Intersector(3, true);
        System.out.println("new Intersector(3, true)");
        final Set<Integer> expResult = new HashSet<>(Arrays.asList(1, 2, 3));
        final Set<Integer> result = instance.getIntersection();
        assertEquals(expResult, result, "getIntersection()");
    }

    /**
     * Test of combinationGenerated method, of class Intersector(m, false).
     */
    @Test
    public void testCombinationGeneratedFalse() {
        System.out.println("combinationGenerated, complement = false");
        final Intersector instance = new Intersector(3, false);
        System.out.println("new Intersector(3, false)");
        Set<Integer> combination;
        combination = new HashSet<>(Arrays.asList(1, 3));
        instance.combinationGenerated(combination);
        System.out.println("combinationGenerated({ 1, 3 })");
        combination = new HashSet<>(Arrays.asList(1, 2));
        instance.combinationGenerated(combination);
        System.out.println("combinationGenerated({ 1, 2 })");
        final Set<Integer> result = instance.getIntersection();
        final Set<Integer> expResult = new HashSet<>(List.of(1));
        assertEquals(expResult, result, "getIntersection()");
    }

    /**
     * Test of combinationGenerated method, of class Intersector(m, false).
     */
    @Test
    public void testCombinationGeneratedTrue() {
        System.out.println("combinationGenerated, complement = true");
        Intersector instance = new Intersector(3, true);
        System.out.println("new Intersector(3, true)");
        Set<Integer> combination;
        combination = new HashSet<>(List.of(1));
        instance.combinationGenerated(combination);
        System.out.println("combinationGenerated({ 1 })");
        combination = new HashSet<>(List.of(2));
        instance.combinationGenerated(combination);
        System.out.println("combinationGenerated({ 2 })");
        Set<Integer> result = instance.getIntersection();
        Set<Integer> expResult = new HashSet<>(List.of(3));
        assertEquals(expResult, result, "getIntersection()");
    }

}
