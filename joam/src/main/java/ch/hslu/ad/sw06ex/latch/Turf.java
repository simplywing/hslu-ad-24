/*
 * Copyright 2024 Hochschule Luzern - Informatik.
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
package ch.hslu.ad.sw06ex.latch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Eine Rennbahn für das Pferderennen.
 */
public final class Turf {

    private static final Logger LOG = LoggerFactory.getLogger(Turf.class);
    private static final int HORSES = 5;

    /**
     * Privater Konstruktor.
     */
    private Turf() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) throws InterruptedException {
        final Synch starterBox = new Latch();
        final Collection<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= HORSES; i++) {
            Thread vThread = Thread.startVirtualThread(new RaceHorse(starterBox, "🏇 " + i));
            threads.add(vThread);
        }

        LOG.info("Sleeping for 2 seconds...");
        Thread.sleep(2000);

        LOG.info("Start! 🔫");
        starterBox.release();

        LOG.info("Wait for all 🏇 to finish...");
        threads.forEach((Thread t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                LOG.warn(e.getMessage());
            }
        });

        LOG.info("All 🏇 finished!");
    }
}
