package ch.hslu.ad.sw12ex;

public final class Wordproblem {

    /**
     * Test if word complies with language:
     * A = {0, 1}
     * L = {0, 010, 01110, 011010, 0101110, 0101010, ...}
     *
     * @param string the word to test
     * @return true if word is compliant
     */
    public static boolean isWordLanguage(final String string) {
        State state = State.z0;
        for (int i = 0; i < string.length(); i++) {
            state = state.transition(state, string.charAt(i));
            if (state.equals(State.illegal)) {
                return false;
            }
        }
        return state.equals(State.z1) || state.equals(State.z4);
    }

    private enum State {
        z0, z1, z2, z3, z4, illegal;

        public State transition(State s, char i) {
            return switch (s) {
                case z0 -> i == '0' ? State.z1 : State.illegal;
                case z1, z3, z4 -> i == '1' ? State.z2 : State.illegal;
                case z2 -> switch (i) {
                    case '0' -> State.z4;
                    case '1' -> State.z3;
                    default -> State.illegal;
                };
                case illegal -> State.illegal;
            };
        }
    }
}