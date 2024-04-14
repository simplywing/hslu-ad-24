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
package ch.hslu.ad.sw08ex.count;

import java.util.concurrent.Callable;

/**
 * Eine Zähl-Aufgabe mit der man Counters testen kann.
 */
public class CountTask implements Callable<Integer> {

    private final Counter counter;
    private final int counts;

    /**
     * Erzeugt eine Zähl-Aufgabe.
     *
     * @param counter zu testender Counter.
     * @param counts  Anzahl Zähldruchläufe.
     */
    public CountTask(Counter counter, int counts) {
        this.counter = counter;
        this.counts = counts;
    }

    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < counts; i++) {
            counter.increment();
        }
        for (int i = 0; i < counts; i++) {
            counter.decrement();
        }
        return counter.get();
    }
}
