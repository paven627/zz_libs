package test.java.thread.timer;

import java.util.Timer;
import java.util.TimerTask;


/**
 * jdk中 Time定时器 
 *
 */
public class Traditional {

	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println('a');
			}
		}, 1000,1000);
	}

}
