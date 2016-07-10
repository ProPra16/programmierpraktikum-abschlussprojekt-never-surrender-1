package tde.core;

public class Tracker {
	
	int[] times = new int[3];
	long sysTime; 
	
	public void start(){
		sysTime = System.currentTimeMillis();
	}

	public void end(){
		sysTime = (System.currentTimeMillis()-sysTime)/1000;		
	}
	
	public void add(int i){
		if(-1 < i && i < 3){
			times[i] = times[i]+(int)sysTime;
		}
	}
}