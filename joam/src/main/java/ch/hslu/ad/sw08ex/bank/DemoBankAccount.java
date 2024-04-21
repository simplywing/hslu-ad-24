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
package ch.hslu.ad.sw08ex.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Demonstration der Bankgeschäfte - Aufgabe 4 - N3_EX_WeiterführendeKonzepte.
 */
public final class DemoBankAccount {

    private static final Logger LOG = LoggerFactory.getLogger(DemoBankAccount.class);

    /**
     * Privater Konstruktor.
     */
    private DemoBankAccount() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     * @throws InterruptedException wenn Warten unterbrochen wird.
     */
    public static void main(String[] args) throws InterruptedException {
        final ArrayList<BankAccount> source = new ArrayList<>();
        final ArrayList<BankAccount> target = new ArrayList<>();
        final int threadCount = Runtime.getRuntime().availableProcessors() + 1;
        final int amount = 10_000_000;
        final int number = 100;
        final int tries = 10;

        for (int j = 0; j < tries; j++) {
            source.clear();
            target.clear();

            for (int i = 0; i < number; i++) {
                source.add(new BankAccount(amount));
                target.add(new BankAccount());
            }
            // Account Tasks starten...
            final ArrayList<Callable<Boolean>> tasks = new ArrayList<>();
            for (int i = 0; i < number; i++) {
                tasks.add(new AccountTask(source.get(i), target.get(i), amount));
            }

            final long startTime, endTime;
            try (ExecutorService executor = Executors.newFixedThreadPool(threadCount)) {
                startTime = System.currentTimeMillis();
                executor.invokeAll(tasks);
                endTime = System.currentTimeMillis();

                for (int i = 0; i < number; i++) {
                    if (source.get(i).getBalance() != 0 || target.get(i).getBalance() != amount) {
                        LOG.warn("Some transfers seem to have failed:");
                        LOG.warn("source({}) = {}; target({}) = {};", i, source.get(i).getBalance(), i, target.get(i).getBalance());
                    }
                }

                LOG.info("{} Transfers between {} BankAccounts each, took {} ms", amount, number, (endTime - startTime));
            } catch (InterruptedException e) {
                LOG.error(e.getMessage());
            }
        }
    }
}
