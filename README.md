# PaytmLabs SDE Challenge

## Coding Question

Write an interface for a data structure that can provide the moving average of the last N elements added, add elements to the structure and get access to the elements. Provide an efficient implementation of the interface for the data structure.

### Minimum Requirements

1. Provide a separate interface (IE `interface`/`trait`) with documentation for the data structure
2. Provide an implementation for the interface
3. Provide any additional explanation about the interface and implementation in a README file.

## Design Question

Design A Google Analytic like Backend System.
We need to provide Google Analytic like services to our customers. Please provide a high level solution design for the backend system. Feel free to choose any open source tools as you want.

### Requirements

1. Handle large write volume: Billions of write events per day.
2. Handle large read/query volume: Millions of merchants wish to gain insight into their business. Read/Query patterns are time-series related metrics.
3. Provide metrics to customers with at most one hour delay.
4. Run with minimum downtime.
5. Have the ability to reprocess historical data in case of bugs in the processing logic.

# Solution for Design Question

In order to process a large number of records quickly, we need a microservices approach behind a load balancer, 
and a queueing system in front that, like kafka. Requests come in through kafka topics, and then are routed via the 
load balancer to the first non-busy microservice. New services can be spun up during high load.

After processing the records, they can be put on another topic and processed into a database where they can be kept
for historial records. Something like Coldline or Nearline GCP storage buckets would work for this, 
as they are very cheap for minimum reading.

The database technology chosen for reading must be robust. Regardless of technology chosen, replication should be a
primary concern. Cassandra is a good candidate, as well as something like GCP Bigtable because of time-series data.

To achieve minimum downtime, a canary deploy strategy should be implemented. New versions of services can be deployed
as a rolling deploy, with a decreasing percentage of old version services remaining live to ensure that there are no
errors in new versions. As well as helping with deployment, a microservice system achieves up time by efficient error
recovery. When a service crashes, it can be restarted without impacting performance.

To provide metrics to customers, we need a database that is good for metrics. Some good choices for this are: 
ELK stack, InfluxDB with Grafana, or a managed service like Stackdriver Monitoring from GCP.

