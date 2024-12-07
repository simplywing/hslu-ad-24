package ch.hslu.ad.sw14ex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void testAddNode() {
        Graph myGraph = new Graph();
        int nodeId = myGraph.addNode("Test 1");
        assertEquals("Test 1", myGraph.getNode(nodeId));
    }

    @Test
    void testConnectNodes() {
        Graph myGraph = new Graph();
        int nodeId1 = myGraph.addNode("Test 1");
        int nodeId2 = myGraph.addNode("Test 2");
        boolean newConnection = myGraph.connect(nodeId1, nodeId2);
        assertTrue(newConnection);
    }

    @Test
    void testDirectNodeConnection() {
        Graph myGraph = new Graph();
        int nodeId1 = myGraph.addNode("Test 1");
        int nodeId2 = myGraph.addNode("Test 2");
        myGraph.connect(nodeId1, nodeId2);
        assertTrue(myGraph.testDirectConnection(nodeId1, nodeId2));
        assertFalse(myGraph.testDirectConnection(nodeId2, nodeId1));
    }

    @Test
    void testGetConnectionCount() {
        Graph myGraph = new Graph();
        int nodeId1 = myGraph.addNode("Test 1");
        int nodeId2 = myGraph.addNode("Test 2");
        myGraph.connect(nodeId1, nodeId2);
        assertEquals(1, myGraph.getConnectionCount());
    }

    @Test
    void testDisconnect() {
        Graph myGraph = new Graph();
        int nodeId1 = myGraph.addNode("Test 1");
        int nodeId2 = myGraph.addNode("Test 2");
        myGraph.connect(nodeId1, nodeId2);
        myGraph.connect(nodeId2, nodeId1);
        myGraph.disconnect(nodeId1, nodeId2);
        assertEquals(1, myGraph.getConnectionCount());
        assertTrue(myGraph.testDirectConnection(nodeId2, nodeId1));
    }

    @Test
    void testRemoveNode() {
        Graph myGraph = new Graph();
        int nodeId1 = myGraph.addNode("Test 1");
        int nodeId2 = myGraph.addNode("Test 2");
        myGraph.connect(nodeId1, nodeId2);
        myGraph.connect(nodeId2, nodeId1);
        myGraph.removeNode(nodeId1);
        assertEquals(0, myGraph.getConnectionCount());
        assertEquals(1, myGraph.getNodeCount());
    }

    @Test
    void testNodeConnectToSelf() {
        Graph myGraph = new Graph();
        int nodeId1 = myGraph.addNode("Test 1");
        myGraph.connect(nodeId1, nodeId1);
        assertEquals(1, myGraph.getConnectionCount());
        assertEquals(1, myGraph.getNodeCount());
    }
}