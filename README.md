# grasca 

/ɡraˈʒka/ - Graphite client for Scala (yet only metrics reader) 

### Motivation
Lack of easy to use Scala library, that allows to read metrics from Graphite server.

### Usage

```
libraryDependencies += "com.github.pcejrowski" %% "grasca" % "0.1.4"
```

See [tests](./src/test/scala/com/github/pcejrowski/grasca) or [scaladoc](http://pcejrowski.github.io/grasca/latest/api) for more details.

Library accesses Graphite API described [here](http://graphite-api.readthedocs.io/en/latest/api.html).

### to-do:
* integrate library, which allows to write to graphite
* create some visualization software
* create simple anomaly detection
* create cross-metric comparator
