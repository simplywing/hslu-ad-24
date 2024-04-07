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
package ch.hslu.ad.sw06ex.signal;

/**
 * Ein nach oben nicht begrenztes Semaphor, d.h. der Semaphoren zähler kann
 * unendlich wachsen.
 */
public final class Semaphore {

    private final int limit; // Limit für Anzahl Passiersignale. 0 → kein Limit.
    private int sema; // Semaphoren zähler
    private int waitingCount; // Anzahl der wartenden Threads.

    /**
     * Erzeugt ein Semaphore mit 0 Passiersignalen.
     */
    public Semaphore() {
        this(0);
    }

    /**
     * Erzeugt ein Semaphore mit einem Initialwert für den Semaphoren zähler.
     *
     * @param permits Anzahl Passiersignale zur Initialisierung.
     * @throws IllegalArgumentException wenn der Initialwert negativ ist.
     */
    public Semaphore(final int permits) throws IllegalArgumentException {
        this(permits, 0);
    }

    /**
     * Erzeugt ein nach oben begrenztes Semaphore.
     *
     * @param permits Anzahl Passiersignale zur Initialisierung.
     * @param limit   maximale Anzahl der Passiersignale.
     * @throws IllegalArgumentException wenn Argumente ungültige Werte besitzen.
     */
    public Semaphore(final int permits, final int limit) throws IllegalArgumentException {
        if (permits < 0) {
            throw new IllegalArgumentException("permits cannot be lower than 0" + permits + " < 0");
        }
        if (limit < 0) {
            throw new IllegalArgumentException("limit cannot be lower than 0" + limit + " < 0");
        }
        if (limit < permits) {
            throw new IllegalArgumentException("limit must be equal to or higher than the permits count");
        }
        this.sema = permits;
        this.limit = limit;
        this.waitingCount = 0;
    }

    /**
     * Entspricht dem P() Eintritt (Passieren) in einen synchronisierten
     * Bereich, wobei mitgezählt wird, der wievielte Eintritt es ist. Falls der
     * Zähler null ist, wird der Aufrufer blockiert.
     *
     * @throws java.lang.InterruptedException falls das Warten unterbrochen
     *                                        wird.
     */
    public synchronized void acquire() throws InterruptedException {
        this.acquire(1);
    }

    /**
     * Entspricht dem P() Eintritt (Passieren) in einen synchronisierten
     * Bereich, wobei mitgezählt wird, der wievielte Eintritt es ist. Falls der
     * Zähler null ist, wird der Aufrufer blockiert.
     *
     * @param permits Anzahl Passiersignale zum Eintritt.
     * @throws java.lang.InterruptedException falls das Warten unterbrochen
     *                                        wird.
     */
    public synchronized void acquire(final int permits) throws InterruptedException {
        if (permits > this.limit) {
            throw new IllegalArgumentException("cannot acquire " + permits + " permits because the Semaphore limit is set to " + this.limit + " permits");
        }
        while (sema < permits) {
            waitingCount += permits;
            this.wait();
            waitingCount -= permits;
        }
        sema -= permits;
    }

    /**
     * Entspricht dem V() Verlassen (Freigeben) eines synchronisierten
     * Bereiches, wobei ebenfalls mitgezählt wird, wie oft der Bereich verlassen
     * wird.
     */
    public synchronized void release() {
        this.release(1);
    }

    /**
     * Entspricht dem V() Verlassen (Freigeben) eines synchronisierten
     * Bereiches, wobei mitgezählt wird, wie oft der Bereich verlassen wird.
     *
     * @param permits Anzahl Passiersignale zur Freigabe.
     */
    public synchronized void release(final int permits) {
        if (this.limit != 0 && this.sema + permits > this.limit) {
            throw new IllegalArgumentException("cannot release because limit is reached.");
        }
        sema += permits;
        this.notifyAll();
    }

    /**
     * Lesen der Anzahl wartenden Threads.
     *
     * @return Anzahl wartende Threads.
     */
    public synchronized int pending() {
        return waitingCount;
    }

    public int getLimit() {
        return this.limit;
    }
}
