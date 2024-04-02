/*
 * Copyright 2024 Hochschule Luzern Informatik.
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
package ch.hslu.ad.sw05ex.joins;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstration von Join und Sleep - Aufgabe 3 - N1_EX_ThreadsSynch.
 */
public class JoinAndSleep {

    private static final Logger LOG = LoggerFactory.getLogger(JoinAndSleep.class);

    /**
     * Main-Demo.
     *
     * @param args not used.
     * @throws InterruptedException wenn Warten unterbrochen wird.
     */
    public static void main(String[] args) throws InterruptedException {
        JoinAndSleepTask task1 = new JoinAndSleepTask("task-1", 2000);
        JoinAndSleepTask task2 = new JoinAndSleepTask("task-2", 3000);
        JoinAndSleepTask task3 = new JoinAndSleepTask("task-3", 4000);

        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");
        Thread thread3 = new Thread(task3, "thread-3");

        task2.setJoinFor(thread3);
        task1.setJoinFor(thread2);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            LOG.warn(e.getMessage());
        }
        thread3.interrupt();
    }
}
