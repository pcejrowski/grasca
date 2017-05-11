# grasca 
######*/ɡraˈʒka/*

**Gra**phite from **Sca**la (yet only metrics reader) 

### Motivation
Lack of easy to use Scala library, that allows to read metrics from Graphite server.
 
### Testing

* Using prebuild Docker Image for Graphite & Statsd [https://github.com/hopsoft/docker-graphite-statsd]
```
docker run -d\
 --name graphite\
 --restart=always\
 -p 80:80\
 -p 2003-2004:2003-2004\
 -p 2023-2024:2023-2024\
 -p 8125:8125/udp\
 -p 8126:8126\
 hopsoft/graphite-statsd
```

### to-do:
* integrate JSON rendered data to be returned 
* integrate library, which allows to write to graphite
* create some visualization software
* create simple anomaly detection
* create cross-metric comparator
