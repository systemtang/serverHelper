package server.time;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import redis.clients.jedis.Jedis;
import server.jedis.JedisUtil;

/**
 * http://blog.csdn.net/bzuld/article/details/8598776
 * @author trj
 * @createDate 2017年1月6日
 */
public class TimeUtil {

	public void Timer(){
		// 第四种方法：安排指定的任务task在指定的时间firstTime开始进行重复的固定速率period执行．
		// Timer.scheduleAtFixedRate(TimerTask task,Date firstTime,long period)
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 12); // 控制时
		calendar.set(Calendar.MINUTE, 0); // 控制分
		calendar.set(Calendar.SECOND, 0); // 控制秒

		Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				try {
					autoSave();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("-------进行了自动存储--------");
			}
		}, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
	}
	
	public static void autoSave(){
		// 1.读取redis保存的数据
		Jedis jedis = JedisUtil.getJedis();
		jedis.get("visitedNum");
		// 2.读取文件中保存的数据
		
		// 3.文件 > redis ? 异常 : 保存redis>>文件
	}
}
