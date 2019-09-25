# Hazelcast IoT Edge Demo: Datacenter Component

## Overview

The Datacenter Component of the Hazelcast IoT Edge Demo simulates a
central data store in a datacenter. This data store receives a
continuous stream of information from devices on "the edge" and
aggregates this information, enriches it, and presents it in a
slick UI to assist in business decisionmaking.

The Datacenter Component uses the
[Hazelcast IMDG](https://hazelcast.org/) in-memory data grid
to aggregate streamed data into in-memory key-value stores
and the [Apache Tomcat](http://tomcat.apache.org) application
server to serve up the UI.
