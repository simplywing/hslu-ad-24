package ch.hslu.ad.sw14ex;

import java.util.*;

public final class Graph {

    private final Map<Integer, GraphNode> nodes = new HashMap<>();
    private final Map<Integer, Set<Integer>> edges = new HashMap<>();
    private int idCounter = 0;

    public int addNode(final String name) {
        int nodeId = idCounter++;
        nodes.put(nodeId, new GraphNode(name));
        return nodeId;
    }

    public void removeNode(final int id) {
        // remove outbound edges
        edges.remove(id);
        // remove inbound edges
        edges.values().stream().filter(Objects::nonNull).forEach(s -> s.remove(id));
        // remove node itself
        nodes.remove(id);
    }

    public void disconnect(final int from, final int to) {
        var s = edges.get(from);
        if (null != s) {
            s.remove(to);
        }
    }

    public int getConnectionCount() {
        return edges.values().stream()
                .filter(Objects::nonNull)
                .mapToInt(Set::size)
                .sum();
    }

    public int getNodeCount() {
        return nodes.size();
    }

    public boolean connect(final int outbound, final int inbound) {
        initEdgeList(outbound);
        return edges.get(outbound).add(inbound);
    }

    public boolean testDirectConnection(final int from, final int to) {
        return null != edges.get(from) && edges.get(from).contains(to);
    }

    private void initEdgeList(final int id) {
        edges.computeIfAbsent(id, k -> new HashSet<>());
    }

    public String getNode(final int id) {
        return nodes.get(id).name();
    }

    private record GraphNode(String name) {
        private GraphNode {
            validateGraphNodeName(name);
        }

        private void validateGraphNodeName(final String name) {
            if (null == name) {
                throw new IllegalArgumentException("Graph node name cannot be null");
            }
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Graph node name must be at least 1 character long.");
            }
        }

    }
}
