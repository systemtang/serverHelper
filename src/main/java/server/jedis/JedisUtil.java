package server.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * http://www.cnblogs.com/liuling/p/2014-4-19-04.html
 * @author trj
 * @createDate 2017年1月6日
 */
public class JedisUtil {

	// Redis服务器IP
	private static String ADDR = "127.0.0.1";

	// Redis的端口号
	private static int PORT = 6379;

	// 访问密码
//	private static String AUTH = "Systemt";

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 200;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 50000;

	private static int TIMEOUT = 10000;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;

	private static JedisPool jedisPool = null;
	
	private static Jedis resource = null;

	/**
	 * 初始化Redis连接池
	 */
	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
//			jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
			jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Jedis实例
	 * 
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				resource = jedisPool.getResource();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resource;
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	public static void delJedis(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}
}
