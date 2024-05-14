# Optimizing Merge Sort parallelism threshold

The following measurements were averaged over 10 complete sorting operations for each threshold.
The Array consisted of `10_000_000` elements and was populated with pseudo random int's ranging from `0`
to `Integer.MAX_VALUE` using a constant seed.

```
 Threshold  Average Time (ms) 
 ---------  ----------------- 
 5          211.3             
 10         173.5             
 20         165.6             
 40         167.1             
 60         164.6             <-- lowest average time
 70         169.6             
 80         169.6             
 90         170.9             
 100        179.8             
 120        171.3             
 140        175.3             
 160        197.1             
 180        193.6             
 250        189.1             
 500        235.9                      
```

To hone in on the exact threshold with the best performance, the test was repeated with a finer threshold stepping:

``` 
 Threshold  Average Time (ms) 
 ---------  ----------------- 
 20         206.1             
 25         200.0             
 30         198.6             
 35         193.8             
 40         186.1             <-- lowest average time
 45         190.1             
 50         187.5             
 55         187.4             
 60         191.5             
```

and again...

```
 Threshold  Average Time (ms) 
 ---------  ----------------- 
 35         206.3             
 36         193.9             
 37         206.2             
 38         192.2             
 39         189.6             
 40         184.6             
 41         187.4             
 42         184.4             <-- lowest average time
 43         191.3             
 44         188.9             
 45         186.0             
```

Obviously the best performing threshold is 42. :)

Jokes aside, somewhere between 40 and 60 seems optimal.
