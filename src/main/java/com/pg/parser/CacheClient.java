package com.pg.parser;

import redis.clients.jedis.Jedis;

public class CacheClient {
    private Jedis jedis = null;

    public CacheClient() {
        jedis = new Jedis("localhost");
    }

    public String set(String key, String value) {
        return jedis.set(key, value);
    }

    public String get(String key) {
        return jedis.get(key);
    }
}
