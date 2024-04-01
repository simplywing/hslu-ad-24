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
package ch.hslu.ad.sw05ex.balls;

import java.awt.*;

/**
 * Demonstration von Bällen.
 */
public class DemoBallsMT {

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        final Canvas cvs = Canvas.initCanvas("Ball Demo", 1280, 800, Color.black);
        final String[] color = {"red", "black", "blue", "yellow", "green", "magenta"};
        final int[] size = {10, 12, 14, 16, 18};
        final int ballCount = 20;

        final boolean useVirtualThreads = true;

        for (int i = 0; i <= ballCount; i++) {
            Ball myBall = new Ball(
                    size[i % size.length],
                    (int) (cvs.getWidth() * Math.random()),
                    (int) (400 * Math.random()),
                    color[i % color.length]
            );

            if (useVirtualThreads) {
                Thread.startVirtualThread(myBall);
            } else {
                final Thread myThread = new Thread(myBall, String.format("Ball [%s]", i));
                myThread.start();
            }
        }
    }
}
