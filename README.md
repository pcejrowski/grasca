# grasca 

[![Join the chat at https://gitter.im/grasca/Lobby](https://badges.gitter.im/grasca/Lobby.svg)](https://gitter.im/grasca/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

/ɡraˈʒka/ - Graphite client for Scala (yet only metrics reader) 

### Motivation
Lack of easy to use Scala library, that allows to read metrics from Graphite server.

### Usage

```
libraryDependencies += "com.github.pcejrowski" %% "grasca" % "0.1.6"
```

See [tests](./src/test/scala/com/github/pcejrowski/grasca) or [scaladoc](https://pcejrowski.github.io/grasca/latest/api/com/github/pcejrowski/grasca/) for more details.

Library accesses Graphite API described [here](http://graphite-api.readthedocs.io/en/latest/api.html).


### to-do:
    * integrate library, which allows to write to graphite
    * create some visualization software
    * create simple anomaly detection
    * create cross-metric comparator

[License](https://pcejrowski.github.io/grasca/LICENSE)