# Fragen zu Aufgabe 1 Grosse Primzahlen

> Wie viele Threads lassen Sie laufen?

`Runtime.getRuntime().availableProcessors() + 1`

> Wie lange dauert es jetzt?

~ 10 Sekunden. Die Resultate variieren aber um +- 20 %, vermutlich aufgrund der Zufallszahlen und der zu kleinen Anzahl
Primzahlen.

> Was passiert, wenn die Anzahl Threads verdoppeln, vervierfachen, verzehnfachen?

Ab einer sehr grossen Anzahl Threads wird es wieder langsamer, die beste Performance scheint um `nProc + 1` zu liegen.

> Können Sie die Applikation noch schneller machen?

Auf dem Desktop läuft sie noch schneller als auf dem Notebook ;-)