# Quickstart for the gradle-confluent Plugin
This repository provides examples for working with Kafka, most notably, examples used with the `gradle-confluent` Gradle plugin, which is available from the [Gradle Plugin Portal](https://plugins.gradle.org/plugin/com.redpillanalytics.gradle-confluent). You can explore the [GitHub repository](https://github.com/RedPillAnalytics/gradle-confluent) that hosts the plugin and open feature requests or contribute pull requests. The CI process for the plugin publishes the latest [API documentation](https://s3.amazonaws.com/documentation.redpillanalytics.com/gradle-confluent/latest/index.html) which might also be helpful.

I use the [Gradle Docker Compose Plugin](https://github.com/avast/gradle-docker-compose-plugin) to manage the environment necessary for this quickstart. I borrowed and modified the 
[docker compose file](docker-compose.yml) file from the [Confluent Clickstream Example](https://github.com/confluentinc/examples/tree/5.1.0-post/clickstream), so thanks to [Robin Moffatt of Confluent](https://twitter.com/rmoff) for this.

Below is a series of commands that can be executed to provision a Confluent environment using docker-compose. You can walk through the commands by copying and pasting to a terminal, or for an easy way to execute the commands one after another, just use the [Jupyter notebook](https://github.com/RedPillAnalytics/kafka-examples/blob/master/docker-compose.ipynb).

To get the Confluent and KSQL environment necessary for this Quickstart, just do the following:

```Bash
./gradlew composeUp -q
zookeeper uses an image, skipping
kafka uses an image, skipping
schema-registry uses an image, skipping
kafka-connect uses an image, skipping
ksql-server uses an image, skipping
ksql-cli uses an image, skipping
kafkacat uses an image, skipping
datagen uses an image, skipping
Creating network "4d800fa68ea5d141d90d0e03a2a88749_kafka-examples__default" with the default driver
Creating zookeeper ...
Creating zookeeper ... done
Creating kafka     ...
Creating kafka     ... done
Creating kafkacat  ...
Creating schema-registry ...
Creating kafkacat        ... done
Creating schema-registry ... done
Creating kafka-connect   ...
Creating datagen         ...
Creating ksql-server     ...
Creating datagen         ... done
Creating kafka-connect   ... done
Creating ksql-server     ... done
Creating ksql-cli        ...
Creating ksql-cli        ... done
```
You can verify that the three clickstream tables necessary for the Quickstart are there using the KSQL CLI. The tables we are interested are `clickstream`, `clickstream_codes` and `clickstream_users`:

```Bash
./gradlew listTopics

> Task :ksql:listTopics
Name: _confluent-metrics, Registered: false, Partitions: 12, Consumers: 0, Consumer Groups: 0
Name: _schemas, Registered: false, Partitions: 1, Consumers: 0, Consumer Groups: 0
Name: clickstream, Registered: false, Partitions: 1, Consumers: 0, Consumer Groups: 0
Name: clickstream_codes, Registered: false, Partitions: 1, Consumers: 0, Consumer Groups: 0
Name: clickstream_users, Registered: false, Partitions: 1, Consumers: 0, Consumer Groups: 0
Name: docker-connect-configs, Registered: false, Partitions: 1, Consumers: 0, Consumer Groups: 0
Name: docker-connect-offsets, Registered: false, Partitions: 25, Consumers: 0, Consumer Groups: 0
Name: docker-connect-status, Registered: false, Partitions: 5, Consumers: 0, Consumer Groups: 0

BUILD SUCCESSFUL in 0s
1 actionable task: 1 executed
```

Once you are finished with the Confluent environment, you can bring it down just as easy:

```Bash
./gradlew composeDown
Starting a Gradle Daemon (subsequent builds will be faster)

> Task :composeDown
Stopping ksql-server     ...
Stopping schema-registry ...
Stopping kafka           ...
Stopping zookeeper       ...
Stopping ksql-server     ... done
Stopping schema-registry ... done
Stopping kafka           ... done
Stopping zookeeper       ... done
Removing ksql-cli        ...
Removing kafka-connect   ...
Removing datagen         ...
Removing ksql-server     ...
Removing schema-registry ...
Removing kafkacat        ...
Removing kafka           ...
Removing zookeeper       ...
Removing kafkacat        ... done
Removing schema-registry ... done
Removing ksql-server     ... done
Removing ksql-cli        ... done
Removing datagen         ... done
Removing kafka           ... done
Removing zookeeper       ... done
Removing kafka-connect   ... done
Removing network 4d800fa68ea5d141d90d0e03a2a88749_kafka-examples__default

BUILD SUCCESSFUL in 13s
1 actionable task: 1 executed
```