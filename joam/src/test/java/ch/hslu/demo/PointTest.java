/*
 * Copyright 2024 Roland Gisler, HSLU Informatik, Switzerland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * Testfälle für Point.
 */
final class PointTest {

    /**
     * Test of getX method, of class Point.
     */
    @Test
    void testGetX() {
        assertEquals(10, new Point(10, 0).getX());
    }

    /**
     * Test of getY method, of class Point.
     */
    @Test
    void testGetY() {
        assertEquals(20, new Point(0, 20).getY());
    }

    /**
     * Test {@link ch.hslu.demo.Point#getQuadrant()}.
     */
    @Test
    void testGetQuadrant1() {
        assertEquals(1, new Point(1, 1).getQuadrant());
    }

    /**
     * Test {@link ch.hslu.demo.Point#getQuadrant()}.
     */
    @Test
    void testGetQuadrant2() {
        assertEquals(2, new Point(-1, 1).getQuadrant());
    }

    /**
     * Test {@link ch.hslu.demo.Point#getQuadrant()}.
     */
    @Test
    void testGetQuadrant3() {
        assertEquals(3, new Point(-1, -1).getQuadrant());
    }

    /**
     * Test {@link ch.hslu.demo.Point#getQuadrant()}.
     */
    @Test
    void testGetQuadrant4() {
        assertEquals(4, new Point(1, -1).getQuadrant());
    }

    /**
     * Test {@link ch.hslu.demo.Point#getQuadrant()}.
     */
    @Test
    void testGetQuadrantXAchse() {
        assertThat(new Point(0, 1).getQuadrant()).isZero();
    }

    /**
     * Test {@link ch.hslu.demo.Point#getQuadrant()}.
     */
    @Test
    void testGetQuadrantYAchse() {
        assertThat(new Point(1, 0).getQuadrant()).isZero();
    }

    /**
     * Test {@link ch.hslu.demo.Point#getQuadrant()}.
     */
    @Test
    void testGetQuadrantNullpunkt() {
        assertThat(new Point(0, 0).getQuadrant()).isZero();
    }

    /**
     * Test {@link ch.hslu.demo.Point#toString()}.
     */
    @Test
    void testToString() {
        assertThat(new Point(3, 3).toString()).containsSequence("Point[");
    }

    /**
     * Test {@link ch.hslu.demo.Point#equals(java.lang.Object)} Contract.
     */
    @Test
    void testEqualsContract() {
        EqualsVerifier.forClass(Point.class).verify();
    }

    /**
     * Test {@link ch.hslu.demo.Point#equals(java.lang.Object)}.
     */
    @Test
    void testEqualsSame() {
        final Point point1 = new Point(1, 1);
        final Point point2 = point1;
        assertEquals(point1, point2);
    }

    /**
     * Test {@link ch.hslu.demo.Point#equals(java.lang.Object)}.
     */
    @Test
    void testEqualsEqual() {
        final Point point1 = new Point(1, 1);
        final Point point2 = new Point(1, 1);
        assertEquals(point1, point2);
    }

    /**
     * Test {@link ch.hslu.demo.Point#equals(java.lang.Object)}.
     */
    @Test
    void testEqualsNotEqual() {
        final Point point1 = new Point(1, 2);
        final Point point2 = new Point(2, 1);
        assertNotEquals(point1, point2);
    }
}
