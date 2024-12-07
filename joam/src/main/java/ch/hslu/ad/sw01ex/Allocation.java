package ch.hslu.ad.sw01ex;

import org.jetbrains.annotations.NotNull;

public record Allocation(int address, int size) implements Comparable<Allocation> {
    @Override
    public int compareTo(@NotNull Allocation o) {
        return this.address - o.address;
    }
}
