Reflektieren Sie die Aufgabe (hilft auch bei einer eventuellen Präsentation) und beantworten Sie sich die folgenden
Fragen:

> Warum eigentlich Threads verwenden?

Damit der Canvas und die Hauptapplikation reaktiv bleiben.

> Wie werden die Threads erzeugt?

Entweder mit dem Konstruktor der `Thread` Klasse (für Platform Threads) oder mit der
Factory-Methode `Thread.startVirtualThread()` für virtuelle Threads.

> Wann werden die Threads beendet?

Die Threads werden beendet sobald die Methode `run()` des übergebenen `Runnable` Objektes beendet/returned.

> Merken Sie einen Unterschied zwischen konventionellen und virtuellen Threads?

Virtuelle Threads starten schneller und haben einen vordefinierten Namen. Platform Threads können beliebig benannt
werden.

> Was ist die gemeinsame Ressource in dieser Aufgabe?

Die gemeinsam genutzte Ressource ist die `Canvas` Klasse, auf welche über die Singleton-Factory Methode zugegriffen
wird.
