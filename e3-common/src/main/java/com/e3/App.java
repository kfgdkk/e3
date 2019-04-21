package com.e3;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    // 单实例连接redis
    @Test
    public void testJedisSingle() {

        Jedis jedis = new Jedis("192.168.177.128", 6379);
        jedis.set("name", "1234");
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();

    }
    @Test
    public void pool(){
        JedisPool pool = new JedisPool("192.168.177.128",6379);
        Jedis jedis = null;
        try {
            jedis=pool.getResource();
            jedis.set("name","李四");
            String s = jedis.get("name");
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (jedis!=null){
                jedis.close();
            }
        }
    }
    @Test
    public void testJedisCluster(){
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.177.128",7001));
        nodes.add(new HostAndPort("192.168.177.128",7002));
        nodes.add(new HostAndPort("192.168.177.128",7003));
        nodes.add(new HostAndPort("192.168.177.128",7004));
        nodes.add(new HostAndPort("192.168.177.128",7005));
        nodes.add(new HostAndPort("192.168.177.128",7006));
        JedisCluster cluster = null;
        try {
            cluster = new JedisCluster(nodes);
            cluster.set("key1","20");
            String s = cluster.get("key1");
            System.out.println(s);
            cluster.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
