/**
 * 
 */
package com.jeeplus.modules.service;

import com.jeeplus.modules.threadPool.MyThread485;
import com.jeeplus.modules.threadPool.PipelineRobotdata;

/**
 * @author admin
 *
 */
public class ThreadInitService {


	public static int online;

	public static Thread thread;


	public void init() {
		System.out.println("55555");
//		new Thread(new MyThread485()).start();
//		new AlarmDataThread().start();
	}

	class AlarmDataThread extends Thread {

		public AlarmDataThread() {

		}

		public void run() {
			while (true) {
				Object devAlarm = null;
				try {
					devAlarm = MyThread485.devAlarmQueue.take();
					PipelineRobotdata.pipelineData(devAlarm.toString());

				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		}
	}

	public ThreadInitService() {
		super();
//	        System.out.println("构造函数1");
	}

	public void destroy() {
//	        System.out.println("this is destroy method1");
	}

}
