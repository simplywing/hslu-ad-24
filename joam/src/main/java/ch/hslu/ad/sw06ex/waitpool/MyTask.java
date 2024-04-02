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
package ch.hslu.ad.sw06ex.waitpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

/**
 * Einfacher Task für die Demonstration eines Wait-Pools.
 */
public final class MyTask implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(MyTask.class);
    private final Semaphore semaphore;

    /**
     * Erzeugen einen Task.
     *
     * @param lock für die Synchronisation
     */
    public MyTask(final Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            LOG.info("Waiting to Acquire Semaphore...");
            semaphore.acquire();
        } catch (InterruptedException ex) {
            LOG.warn(ex.getMessage());
            return;
        }
        LOG.info("Semaphore Acquired!");
    }
}
