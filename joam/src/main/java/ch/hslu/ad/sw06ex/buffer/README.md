# Reflexion

> Warum ist es gerade im Fall der Klasse BoundedBuffer mit Guarded Blocks (N21_IP_ThreadSteuerung, Folien 15-17) nicht
> gut, wenn man als Lock- und Wait-Pool das aktuelle Objekt (this) nimmt?

Die JVM verwendet das Objekt selbst als Monitor (seine intrinsische Sperre), wenn eine Klasse Methodensynchronisierung
oder Blocksynchronisierung mit dem Schlüsselwort this implementiert. Nicht vertrauenswürdiger Code kann die intrinsische
Sperre einer zugänglichen Klasse erlangen und auf unbestimmte Zeit halten. Folglich kann dies zu einer
Deadlock-Situation führen.

> Sie haben bei denjenigen Methoden wo eine InterruptedException auftreten kann, diese an den Aufrufer weitergegeben.
> Warum haben Sie das getan?

Da der Consumer oder der Producer entscheiden soll, wie mit dem Umstand umgegangen wird, wenn beispielsweise kein
Element
in der verlangten Zeit removed oder added werden kann.

> Wie verhält sich Ihr BoundedBuffer beim Eintreffen eines Interrupts? Haben Sie das ausprobiert?

in den verschiedenen Methoden des Bounded Buffers können nur auf den Semaphor methoden `acquire()` und `tryAcquire()`
Interrupts "auftreten".
Die `InterruptedException` würde an den Aufrufer weitergegeben. Wenn nach den vorhin genannten Semaphor-Methodenaufrufen
ein Interrupt auftreten würde, würde dieser einfach ignoriert.