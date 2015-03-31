package cn.robotium;

import java.text.DecimalFormat;

import android.net.TrafficStats;
import cn.robotium.bean.TestRecordBean;
import cn.robotium.utils.Sleeper;

public class NetInfo {
	private int uid;
	private long time;
	private String systemNetPath= "/proc/net/dev";
	private long tcp_rcv,tcp_snd,trafficBegin,trafficEnd,tracffic;
	private double trafficResult;
	private Sleeper sleeper;

		
	public NetInfo(int uid, long time){
		this.uid = uid;
		this.time = time;
		this.sleeper = new Sleeper();
	}
	
	public void calcAppNetInfo(TestRecordBean bean){
		DecimalFormat fomart = new DecimalFormat();
		fomart.setMaximumFractionDigits(2);
		fomart.setMinimumFractionDigits(2);
		getNetInfo();
		trafficBegin = tracffic;
		int wait = (int) time;
		sleeper.sleep(wait);
		getNetInfo();
		trafficEnd = tracffic;
		trafficResult = trafficEnd - trafficBegin;
		String sumTraffic = fomart.format((trafficResult / 1024)); 
		bean.setNet_total_use(sumTraffic);
	}
	
	public void getNetInfo(){
		tcp_rcv = TrafficStats.getUidRxBytes(uid);
		tcp_snd = TrafficStats.getUidTxBytes(uid);
		tracffic = tcp_rcv + tcp_snd;
	}
	
	
}
