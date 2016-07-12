package tde.core;

public class Tracker {
	
	int[] times = new int[3];
	long sysTime; 
	
	/**
	 * legt die aktuelle Systemzeit in sysTime
	 */
	public void start(){
		sysTime = System.currentTimeMillis();
	}
	
	/**
	 * zieht von der aktuellen Systemzeit sysTime ab und teil das Ergbnis durch 1000 (in sec) 
	 */
	public void end(){
		sysTime = (System.currentTimeMillis()-sysTime)/1000;		
	}
	
	/**
	 * 
	 * @param bekommt als int Wert die Zahl der aktuellen Phase
	 * wandelt sysTime in int um und addiert sie zur passenden Stelle vom int Array times 
	 */
	public void add(int i){
		if(-1 < i && i < 3){
			times[i] = times[i]+(int)sysTime;
		}
	}
}