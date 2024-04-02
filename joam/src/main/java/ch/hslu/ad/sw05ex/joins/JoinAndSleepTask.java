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
 * Parametrierbarer Task, der auf einen fremden Thread wartet und danach für
 * eine bestimmte Zeit schläft.
 */
public class JoinAndSleepTask implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(JoinAndSleepTask.class);
    private final String taskName;
    private final int sleepTime;
    private Thread joinFor;

    /**
     * Erzeugt einen Task mit Namen.
     *
     * @param taskName  der Name des Tasks.
     * @param sleepTime die Zeit in mSec die der Task schläft.
     */
    public JoinAndSleepTask(final String taskName, final int sleepTime) {
        this.taskName = taskName;
        this.joinFor = null;
        this.sleepTime = sleepTime;
    }

    public void setJoinFor(Thread toJoinFor) {
        this.joinFor = toJoinFor;
    }

    /**
     * Hier wird auf das Ende des fremden Threads gewartet und danach für eine
     * bestimmte Zeit geschlafen. Beide Teile können unterbrochen werden.
     */
    @Override
    public void run() {
        if (null != this.joinFor) {
            LOG.info("Joining Thread [{}]", this.joinFor.getName());
            try {
                joinFor.join();
                LOG.info("Thread [{}] finished.", this.joinFor.getName());
            } catch (InterruptedException e) {
                LOG.warn("Thread was interrupted during join.");
                Thread.currentThread().interrupt();
                return;
            }
        } else {
            LOG.info("Skipping Thread join.");
        }

        LOG.info("Starting sleep of {} ms", this.sleepTime);
        try {
            Thread.sleep(this.sleepTime);
        } catch (InterruptedException e) {
            LOG.warn("Thread [{}] was interrupted during sleep:", Thread.currentThread().getName());
            LOG.warn(e.getMessage());
            Thread.currentThread().interrupt();
            return;
        }

        LOG.info("Task [{}] finished", this.taskName);
    }
}
