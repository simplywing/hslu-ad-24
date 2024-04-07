# Fragen zu Aufgabe 3 Signalgeber

> Wie fair ist das im Input N21vorgestellte Semaphor?

Nicht sehr fair, es wird kein FIFO Prinzip implementiert. Ein schwaches Semaphor besitzt einen Pool und garantiert nicht
die chronologisch richtige Abarbeitung der Warteschlange.

> Was ist die Ursache für die entsprechende Fairness?

Siehe oben. Ein starkes Semaphor wäre fairer, da der erste wartende Thread als erstes benachrichtigt würde.

> Wie könnten Sie die bestehende Fairness verbessern?

Ein starkes Semaphore mit einer Queue implementieren.

> Das im AD Input N21 (N21_IP_ThreadSteuerung) vorgestellte Semaphor hat in der Methode release noch Potential zur
> Verbesserung.

Eine Queue um ein starkes Semaphore zu implementieren, Siehe oben.

