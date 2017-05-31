# grasca 

/ɡraˈʒka/ - **Gra**phite client in **Sca**la (yet only metrics reader) 

### Motivation
Lack of easy to use Scala library, that allows to read metrics from Graphite server.

### Dependency
SBT:
```
libraryDependencies += "com.github.pcejrowski" %% "grasca" % "0.1.4"
```
Maven:
```
<dependency>
  <groupId>com.github.pcejrowski</groupId>
  <artifactId>grasca_{scala version}</artifactId>
  <version>0.1.4</version>
</dependency>
```
 
### Usage
```scala
MetricsAPI("localhost").find("dummy-query")
MetricsAPI("localhost").expand("dummy-query")
MetricsAPI("localhost").index
RenderAPI("localhost").render("dummy-target")
```
See [tests](./src/test/scala/com/github/pcejrowski/grasca) or [scaladoc](http://pcejrowski.github.io/grasca/latest/api).

### API
Library accesses Graphite API described here: [http://graphite-api.readthedocs.io/en/latest/api.html]

### to-do:
* integrate library, which allows to write to graphite
* create some visualization software
* create simple anomaly detection
* create cross-metric comparator
