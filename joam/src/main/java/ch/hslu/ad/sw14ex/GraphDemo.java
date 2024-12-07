package ch.hslu.ad.sw14ex;

public final class GraphDemo {
    public static void main(String[] args) {
        Graph network = new Graph();
        int luzern = network.addNode("Luzern");
        int rotkreuz = network.addNode("Rotkreuz");
        int zug = network.addNode("Zug");

        network.connect(luzern, rotkreuz);
        network.connect(rotkreuz, zug);
        network.connect(zug, rotkreuz);
        network.connect(rotkreuz, luzern);
    }
}
