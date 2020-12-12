package com.workStudy.core.redis;


@FunctionalInterface
public interface JedisExecutor<T, R> {

    /**
     * Jedis 执行器
     */
    R excute(T t) throws RedisConnectException;
}
