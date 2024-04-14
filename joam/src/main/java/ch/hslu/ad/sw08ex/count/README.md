# Fragen zu Aufgabe 2 Speed Count

> Was können Sie über die Performance der beiden Thread-sicheren Zähler aussagen?

Der Thread-sichere Zähler basierend auf dem `AtomicInteger` ist schneller.

> Was stellen Sie bei den Messresultaten fest?

Der Atomic Counter ist 20 - 40 % schneller, die Messresultate variieren jedoch stark.

> Wie erklären Sie sich die Messresultate?

Atomic operationen werden an die Hardware delegiert und sind deshalb schneller als die Synchronisation per Monitor.

> Welche Genauigkeit bezüglich gemessener Zeit erreicht Ihre Messung?

Ziemlich ungenau, siehe zweite Frage.