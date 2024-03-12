/*
 * Copyright 2024 Roland Gisler, HSLU Informatik, Switzerland.
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

import nl.altindag.console.ConsoleCaptor;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Beispiel für Integrationstests. Namenskonvention 'IT' am Schluss. Werden mit
 * dem Goal integration-test ausgeführt.
 */
final class DemoAppIT {

    /**
     * Demo Integrations-Test mit Auswertung des Console-Outputs (von Log).
     */
    @Test
    void testMain() {
        try (ConsoleCaptor consoleCaptor = new ConsoleCaptor()) {
            DemoApp.main(new String[0]);
            assertThat(consoleCaptor.getStandardOutput()).hasSize(1);
            assertThat(consoleCaptor.getStandardOutput().get(0))
                    .contains("Point[x=2,y=-1] befindet sich in Quadrant: 4");
        }
    }
}
