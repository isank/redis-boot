# Redis

- [Download &amp; Install](#download--install)
- [Data Types](#data-types)
- [Configuration](#configuration)
- [Persistence](#persistence)
- [Cluster](#cluster)

## Download & Install

https://redis.io/download

## Data Types
- Strings
    - Binary Safe
    - Max length 512 Megabytes
    - all commands - https://redis.io/commands/#string
    - examples

        ```
        SET name john
        OK
        ```

        ```
        GET name
        "john"
        ```
- List
    - they're simply list of Strings
    - sorted by insertion order
    - it is possible to push elements to head or tail
    - it is possible to push elements at a particular index
    - max length of a list is 2<sup>32</sup> - 1 elements (4294967295, more than 4 billion of elements)
    - all commands - https://redis.io/commands#list
    - examples

        ```
        LPUSH numbers 1 2 3 4 5
        (integer) 5 // size of the list
        ```

        ```
        LRANGE numbers 0 -1
        1) "5"
        2) "4"
        3) "3"
        4) "2"
        5) "1"
        ```
- Sets
    - they're unordered collection of Strings
    - does not allow repeated elements
    - max number of members in a set is 2<sup>32</sup> - 1 elements (4294967295, more than 4 billion of elements)
    - all commands - https://redis.io/commands#set
    - examples

        ```
        SADD numbers 1 2 2 3
        (integer) 3 // size of the set
        ```

        ```
        SMEMBERS numbers
        1) "1"
        2) "2"
        3) "3"
        ```
- Sorted Sets
    - similar to sets with only difference that every element is associated with a score
    - all commands - https://redis.io/commands#sorted_set
    - examples
        ```
        ZADD numbers 1 two 2 one
        (integer) 0
        ```

        ```
        ZRANGE numbers 0 -1 WITHSCORES
        1) "two"
        2) "1"
        3) "one"
        4) "2"
        ```
- Hashes
    - they're maps between string fields and string values
    - they're perfect to represent objects
    - every hash can store upto 2<sup>32</sup> - 1 field-value pairs (more than 4 billion)
    - all commands - https://redis.io/commands#hash
    - examples
        
        ```
        HMSET user:1000 firstName john lastName doe address CA
        OK
        ```

        ```
        HGETALL user:1000
        1) "firstName"
        2) "john"
        3) "lastName"
        4) "doe"
        5) "address"
        6) "CA"
        ```

## Configuration
- by default redis picks up the configuration from `redis.conf` present in the redis installations root folder
- we can provide redis with a custom configuration path when starting it
    ```
    <path-to-redis-installation>/redis-server <pah-to-redis-configuration-file>
    ```
- List configurations
    - all configurations
        ```
        INFO
        # Server
        redis_version:5.0.7
        redis_git_sha1:00000000
        redis_git_dirty:0
        redis_build_id:bfa2ad76ea8e4f31
        redis_mode:standalone
        os:Linux 4.15.0-88-generic x86_64
        arch_bits:64
        multiplexing_api:epoll
        atomicvar_api:atomic-builtin
        gcc_version:7.4.0
        process_id:16270
        run_id:736afc29e4391fdde7dcb9c8fbff87c4128a951a
        tcp_port:6379
        uptime_in_seconds:2474
        uptime_in_days:0
        hz:10
        configured_hz:10
        lru_clock:6353914
        executable:/home/globallogic/redis/src/redis-server
        config_file:/home/globallogic/redis/redis.conf

        # Clients
        connected_clients:1
        client_recent_max_input_buffer:2
        client_recent_max_output_buffer:0
        blocked_clients:0
        ```
    - section wise configurations
        ```
        INFO server
        # Server
        redis_version:5.0.7
        redis_git_sha1:00000000
        redis_git_dirty:0
        redis_build_id:bfa2ad76ea8e4f31
        redis_mode:standalone
        os:Linux 4.15.0-88-generic x86_64
        arch_bits:64
        multiplexing_api:epoll
        atomicvar_api:atomic-builtin
        gcc_version:7.4.0
        process_id:16270
        run_id:736afc29e4391fdde7dcb9c8fbff87c4128a951a
        tcp_port:6379
        uptime_in_seconds:2513
        uptime_in_days:0
        hz:10
        configured_hz:10
        lru_clock:6353953
        executable:/home/globallogic/redis/src/redis-server
        config_file:/home/globallogic/redis/redis.conf
        ```

## Persistence
- RDB
    - point-in-time snapshots at specified intervals
    - compact single-file
    - default config
        ```
        save 900 1
        save 300 10
        save 60 10000
        ```
- AOF
    - more durable because of different fsync policies
    - append only file and can get very big in size
    - default config
        ```
        appendfsync everysec
        ```

Further references - https://redis.io/topics/persistence

## Cluster

- Redis consists of 16863 hash slots
- We can have 16864 nodes in a cluster. However, it is recommended to have ~1000 
- You should have minimum of 3 masters. During the failure detection, the majority of the master nodes are required to come to an agreement. If there are only 2 masters, say A and B and B failed, then the A master node cannot reach to a decision according to the protocol. The A node needs another third node, say C, to tell A that it also cannot reach B.
  ![](images/redis-cluster.png)