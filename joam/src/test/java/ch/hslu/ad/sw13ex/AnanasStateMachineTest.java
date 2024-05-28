package ch.hslu.ad.sw13ex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnanasStateMachineTest {

    @Test
    void stateSearchMatch() {
        var machine = new AnanasStateMachine();
        String s = "sdfsdfjsdkanananananas";
        int index = machine.stateSearch(s);
        assertEquals(16, index);
    }

    @Test
    void stateSearchNoMatch() {
        var machine = new AnanasStateMachine();
        String s = "sdfsdfjsaas";
        int index = machine.stateSearch(s);
        assertEquals(-1, index);
    }

    @Test
    void stateSearchNoMatch2() {
        var machine = new AnanasStateMachine();
        String s = "ananaa";
        int index = machine.stateSearch(s);
        assertEquals(-1, index);
    }

    @Test
    void stateSearchNoMatch3() {
        var machine = new AnanasStateMachine();
        String s = "ananananananananas";
        int index = machine.stateSearch(s);
        assertEquals(12, index);
    }
}