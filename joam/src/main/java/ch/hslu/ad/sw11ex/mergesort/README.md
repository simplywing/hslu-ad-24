# Optimizing Merge Sort parallelism threshold

The following measurements were averaged over 10 complete sorting operations for each threshold.
The Array consisted of `10_000_000` elements and was populated with pseudo random int's ranging from `0`
to `Integer.MAX_VALUE` using a constant seed.

```
 Threshold  Average Time (ms) 
 ---------  ----------------- 
 5          433.9             
 10         396.2             
 20         371.2             
 40         370.9             <-- lowest average time
 60         371.2             
 70         391.8             
 80         422.2             
 90         422.2             
 100        422.6             
 120        425.5             
 140        424.5             
 160        504.2             
 180        501.2             
 250        519.4             
 500        692.1             
```

To hone in on the exact threshold with best performance, the test was repeated with a finer threshold stepping:

```
 Threshold  Average Time (ms) 
 ---------  ----------------- 
 20         408.2             
 25         409.1             
 30         406.4             
 35         407.7             
 40         391.7             <-- lowest average time
 45         422.4             
 50         414.0             
 55         415.0             
 60         434.2             
```

and again...

```
 Threshold  Average Time (ms) 
 ---------  ----------------- 
 35         398.4             
 36         390.3             
 37         388.5             
 38         391.0             
 39         389.8             
 40         407.5             
 41         399.9             
 42         400.2             
 43         399.1             
 44         408.0             
 45         396.3             
```
