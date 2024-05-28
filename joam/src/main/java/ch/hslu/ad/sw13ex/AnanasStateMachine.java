package ch.hslu.ad.sw13ex;

/**
 * Optimierter Suchautomat für den Pattern "ananas";
 */
public final class AnanasStateMachine {

    private static int PATTERN_LENGTH = 6;
    private State machineState;

    public AnanasStateMachine() {
        this.machineState = State.z0;
    }

    public int stateSearch(final String a) {
        for (int i = 0; i < a.length(); i++) {
            if (nextState(a.charAt(i))) {
                return i - PATTERN_LENGTH + 1;
            }
        }
        return -1;
    }

    public boolean nextState(final char c) {
        switch (this.machineState) {
            case z0 -> {
                if (c == 'a') {
                    this.machineState = State.z1;
                }
                return false;
            }
            case z1 -> {
                if (c == 'n') {
                    this.machineState = State.z2;
                } else {
                    this.machineState = State.z0;
                }
                return false;
            }
            case z2 -> {
                if (c == 'a') {
                    this.machineState = State.z3;
                } else {
                    this.machineState = State.z0;
                }
                return false;
            }
            case z3 -> {
                if (c == 'n') {
                    this.machineState = State.z4;
                } else {
                    this.machineState = State.z0;
                }
                return false;
            }
            case z4 -> {
                if (c == 'a') {
                    this.machineState = State.z5;
                } else {
                    this.machineState = State.z0;
                }
                return false;
            }
            case z5 -> {
                if (c == 's') {
                    this.machineState = State.z6;
                    return true;
                } else if (c == 'n') {
                    this.machineState = State.z4;
                } else {
                    this.machineState = State.z0;
                }
                return false;
            }
            case null, default -> {
                return false;
            }
        }
    }

    private enum State {
        z0, z1, z2, z3, z4, z5, z6
    }
}
