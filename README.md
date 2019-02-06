# Quickstart for the gradle-confluent Plugin
This repository is a companion to a yet-to-be-published series on using Gradle with KSQL and Kafka Streams.

The plugin used by this repository is available from the [Gradle Plugin Portal](https://plugins.gradle.org/plugin/com.redpillanalytics.gradle-confluent). You can explore the [GitHub repository](https://github.com/RedPillAnalytics/confluent-blog) that hosts the plugin and open feature requests, and maybe even contribute pull requests. Finally, the CI process for the plugin publishes the latest [API documentation](https://s3.amazonaws.com/documentation.redpillanalytics.com/gradle-confluent/latest/index.html) which might also be helpful

Since most of what is contained here is Gradle-based, I also decided to use the wonderful [Gradle Docker Compose Plugin](https://github.com/avast/gradle-docker-compose-plugin) to manage the environment necessary for this quickstart. I borrowed and modified the 
[docker compose file](docker-compose.yml) file from the [Confluent Clickstream Example](https://github.com/confluentinc/examples/tree/5.1.0-post/clickstream), so many thanks to [Robin Moffatt of Confluent](https://twitter.com/rmoff) for this.

To get the Confluent and KSQL environment necessary for this Quickstart, just do the following:

```Bash
==> ./gradlew composeUp

> Task :composeUp
zookeeper uses an image, skipping
kafka uses an image, skipping
schema-registry uses an image, skipping
kafka-connect uses an image, skipping
ksql-server uses an image, skipping
ksql-cli uses an image, skipping
kafkacat uses an image, skipping
datagen uses an image, skipping
Creating network "c8b2e8ae2058f2c39f496f980a66bdbd_confluent-blog__default" with the default driver
Creating zookeeper ...
Creating zookeeper ... done
Creating kafka     ...
Creating kafka     ... done
Creating kafkacat  ...
Creating schema-registry ...
Creating kafkacat        ... done
Creating schema-registry ... done
Creating kafka-connect   ...
Creating ksql-server     ...
Creating datagen         ...
Creating datagen         ... done
Creating ksql-server     ... done
Creating ksql-cli        ...
Creating kafka-connect   ... done
Creating ksql-cli        ... done
Will use localhost as host of zookeeper
Will use localhost as host of kafka
Will use localhost as host of schema-registry
Will use localhost as host of kafka-connect
Will use localhost as host of ksql-server
Will use localhost as host of ksql-cli
Will use localhost as host of kafkacat
Will use localhost as host of datagen
Probing TCP socket on localhost:8083 of service 'kafka-connect'
Waiting for TCP socket on localhost:8083 of service 'kafka-connect' (TCP connection on localhost:8083 of service 'kafka-connect' was disconnected right after connected)
(Repitition of the above message removed for clarity)

...

TCP socket on localhost:8083 of service 'kafka-connect' is ready
Probing TCP socket on localhost:8088 of service 'ksql-server'
TCP socket on localhost:8088 of service 'ksql-server' is ready

BUILD SUCCESSFUL in 1m 5s
1 actionable task: 1 executed
```
You can verify that the three clickstream tables necessary for the Quickstart are there using the KSQL CLI:

```Bash
==> ksql

                  ===========================================
                  =        _  __ _____  ____  _             =
                  =       | |/ // ____|/ __ \| |            =
                  =       | ' /| (___ | |  | | |            =
                  =       |  <  \___ \| |  | | |            =
                  =       | . \ ____) | |__| | |____        =
                  =       |_|\_\_____/ \___\_\______|       =
                  =                                         =
                  =  Streaming SQL Engine for Apache Kafka® =
                  ===========================================

Copyright 2017-2018 Confluent Inc.

CLI v5.0.0, Server v5.1.0 located at http://localhost:8088

Having trouble? Type 'help' (case-insensitive) for a rundown of how things work!

ksql> list topics;

 Kafka Topic            | Registered | Partitions | Partition Replicas | Consumers | ConsumerGroups
----------------------------------------------------------------------------------------------------
 _confluent-metrics     | false      | 12         | 1                  | 0         | 0
 _schemas               | false      | 1          | 1                  | 0         | 0
 clickstream            | false      | 1          | 1                  | 0         | 0
 clickstream_codes      | false      | 1          | 1                  | 0         | 0
 clickstream_users      | false      | 1          | 1                  | 0         | 0
 docker-connect-configs | false      | 1          | 1                  | 0         | 0
 docker-connect-offsets | false      | 25         | 1                  | 0         | 0
 docker-connect-status  | false      | 5          | 1                  | 0         | 0
----------------------------------------------------------------------------------------------------
ksql>
```
