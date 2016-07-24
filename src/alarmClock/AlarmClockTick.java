package alarmClock;

public class AlarmClockTick implements Runnable{
	private  AlarmClock clock;
	
	public AlarmClockTick(AlarmClock clock){
		this.clock = clock;
	}
    
	public void run(){
		clock.tick();
		if (clock.ring())
		{//time to sound alarm
			for (int i =0;i<60;i++)
				 java.awt.Toolkit.getDefaultToolkit().beep();
			
		}
		else run();  //recursive
		
	}
}
