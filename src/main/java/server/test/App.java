package server.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import redis.clients.jedis.Jedis;
import server.http.HttpUtil;
import server.jedis.JedisUtil;

/**
 * Hello world!
 *
 */
public class App 
{
//	private static Jedis jedis;
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		// 发送 GET 请求
//		String s = HttpUtil.sendGet("http://139.129.96.117:9080/ArenaDebugNew.html", "");
//		System.out.println(s);

		String s = HttpUtil.sendPost("http://139.129.96.117:9080/web/rs/arena/fc_getDebugFlag", "");
		System.out.println(s);
	}
	
}
