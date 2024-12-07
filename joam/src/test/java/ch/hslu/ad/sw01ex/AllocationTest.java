package ch.hslu.ad.sw01ex;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class AllocationTest {

    @Test
    void testCompareTo() {
        var alloc1 = new Allocation(30, 20);
        var alloc2 = new Allocation(30, 223);
        assertThat(alloc1.compareTo(alloc2)).isEqualTo(0);
    }

    @Test
    void testCompareGreater() {
        var alloc1 = new Allocation(30, 20);
        var alloc2 = new Allocation(20, 223);
        assertThat(alloc1.compareTo(alloc2)).isPositive();
    }

    @Test
    void testCompareSmaller() {
        var alloc1 = new Allocation(5, 20);
        var alloc2 = new Allocation(32, 223);
        assertTrue(alloc1.compareTo(alloc2) < 0);
        assertThat(alloc1.compareTo(alloc2)).isNegative();
    }

    @Test
    void testEqualsContract() {
        EqualsVerifier.forClass(Allocation.class).verify();
    }
}