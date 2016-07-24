package alarmClock;

public class UseAlarmClock {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//create an instance of an AlarmClock
		AlarmClock myAlarmClock = new AlarmClock();
		
		//set the alarm clock
		
		
		//set alarm time
		
		myAlarmClock.setAlarmHour(12);
		myAlarmClock.setAlarmMinutes(10);
		myAlarmClock.setAlarmPM(true);
		
		System.out.println("my clock " + myAlarmClock.getTime());
		System.out.println("my clock alarm " + myAlarmClock.getAlarmTime());
		//put alarm clock on
		myAlarmClock.setAlarmClockOn();
		System.out.println("my clock " + myAlarmClock.getTime());
		myAlarmClock.snooze();
		System.out.println("my clock " + myAlarmClock.getTime());
		System.out.println("my clock alarm " + myAlarmClock.getAlarmTime());
		System.out.println("end application");
		
		

	}

}
