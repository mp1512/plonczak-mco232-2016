package alarmClock;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AlarmClock {
	private int hour;   //0-23  military time
	private int minutes; //0-59
	private int second; //0-59
	private int alarmHour;  
	private int alarmMinutes;
	private boolean alarmOn;

	/** no args constructor
	 *  sets time to 12am
	 *  sets alarm time to 12 am
	 */
	public AlarmClock(){ //no args constructor
		//pick up the current system time, simulate an alarm clock that
		//picks up the current time via satellite 
		GregorianCalendar currentTime = new GregorianCalendar();
		this.hour = currentTime.get(Calendar.HOUR_OF_DAY);  //returns hours in 0-24 format
		this.minutes =currentTime.get(Calendar.MINUTE);
		second =0;
		alarmHour = 12;
		alarmMinutes =0;
		alarmOn = false;
		
	}
	/**setter - setHour
	 * 
	 * @param hour - value of the hour
	 * if it is not a valid value - set hour to 0
	 * 
	 */
	public void setHour(int hour){
		this.hour = hour >= 0 && hour < 24? hour : 0;

	}

	/**setter - setMinute
	 * 
	 * @param minute - value of the minute
	 * if it is not a valid value - set minute to 0
	 */
	public void setMinute(int minutes){
		this.minutes = minutes >=0 && minutes <=59? minutes : 0;
	}

	public void addMinute(){
		this.minutes++;
		if (this.minutes == 60){
			this.hour++;
			this.minutes =0;
			if (this.hour == 24){
				this.hour = 0;  //reset to 12 am - midnight which is 0 in military time
			}
		}
	}
	/** setPM
	 * adjust the hour to military time to
	 * represent am or pm
	 * 
	 * @param pm true == pm, false == am 
	 */
	public void setPM(boolean pm){
		if (pm)  //setting to afternoon, evening time
		{
			if (hour < 12 )   //12 pm is left as is, otherwise must add 12 for military time
		       setHour(hour+12);
		}
		else //setting to morning hours
		{
			if (hour == 12)
				setHour(0);    //12 am in military time is 0
			else if (hour > 12)   
			   setHour (hour -12);
		}
			
		
	}

	public void setAlarmHour(int alarmHour){
		this.alarmHour = alarmHour >= 0 && alarmHour < 24? alarmHour : 0;
	}

	public void setAlarmMinutes(int minutes){
		this.alarmMinutes = minutes >=0 && minutes <=59? minutes : 0;
	}

	public void addAlarmMinute(){
		this.alarmMinutes++;
		if (this.alarmMinutes == 60){
			this.alarmHour++;
			this.alarmMinutes =0; //reset to zero, added next hour
			if (alarmHour == 24){
				alarmHour =0;     //12 midnight is reset to zero in military time
			}
		}
	}
	
	/**setAlarmAMPM
	 * adjust the AlarmHour accordingly
	 * @param pm  true ==pm , false ==am
	 *  
	 */
	public void setAlarmPM(boolean pm){
		if (pm)
		{
			if (getAlarmHour() < 12)
		       setAlarmHour(alarmHour+12);
		}
		else
		{
			if (getAlarmHour() == 12)
				setAlarmHour(0);
			else if (getAlarmHour() > 12)
				setAlarmHour(alarmHour-12);
		}
		
	}


	public int getHour(){
		return hour;
	}

	public int getMinutes(){
		return minutes;
	}

	/** getTime() converts military time to
	 *  standard time display
	 * @return  string that displays the time and am or pm
	 */
	public String getTime(){
		String time;
		boolean pm = false;
		int hr = getHour();
		if (getHour()>12){
			hr  -=12;  //subtract 12 to convert to standard time
		    pm = true;
		}
		if (hr ==0){
			hr =12;             //12 midnight is 12 in standard time
			}
		time = hr + ":";
		if (minutes < 10)
			time = time + "0" + minutes;
		else
			time = time + minutes;
		if (pm) {
			//add pm to the time
			time = time + " pm";
		}
		else {
			//add am to the time
			time = time + " am";
		}
		return time;
	}

	public int getAlarmHour(){
		return alarmHour;
	}

	public int getAlarmMinutes(){
		return alarmMinutes;
	}

	/** getAlarmTime()
	 * 
	 * @return String with standard time
	 */
	public String getAlarmTime(){
		String time;
		boolean pm = false;
		int hr = getAlarmHour();
		if (getAlarmHour()>12)
		{
			hr -= 12;  //subtract 12 to convert to standard time
		    pm = true;
		}
		if (hr == 0) hr = 12; //convert to regular time
		time = hr + ":";
		if (alarmMinutes < 10)
			time = time + "0" + alarmMinutes;
		else
			time = time + alarmMinutes;
		if (pm) 
			//add pm to the time
			time = time + " pm";
		else
		{
			time = time + " am";
		}
		return time;

	}
	public void setAlarmClockOn(){
		alarmOn = true;
		
		if (hour == alarmHour &&
				minutes == alarmMinutes)
			//time to sound alarm
			for (int i =0;i<60;i++)
				java.awt.Toolkit.getDefaultToolkit().beep();
		else
		{  
			try{
				while (hour != alarmHour ||
						minutes != alarmMinutes){

					Thread.sleep(100);
					tick();
				}
			}
			catch(InterruptedException exception){
				System.out.println("terminated prematurely");
			}
			for (int i =0;i<60;i++)
				java.awt.Toolkit.getDefaultToolkit().beep();
		}

	}
     
	/** tick() simulates the ticking of the clock
	 *  adds one minute at a time
	 *  resets the hour when minutes reaches 60
	 *  resets the hour to military time when hour reaches 24
	 *  visibility - package level
	 */
	
	 void tick(){
		minutes++;
		if (minutes ==60){
			hour++;
			minutes =0;
		}
		if (hour == 24)
			hour =0;
		System.out.println(getTime());

	}
	public boolean ring(){
		//check if alarm clock should ring
		if (hour == alarmHour &&
				minutes == alarmMinutes)
			return true;
		else 
			return false;
	}

	public void snooze(){
		//change alarm time for 15 minutes from now
		int minutes;
		minutes = getMinutes(); //current minutes
		minutes += 15;  //add 15 minutes
		 
		try{
			setAlarmMinutes(minutes>60?minutes-60:minutes); //
			Thread.sleep(7500); //going to sleep for 7500 milliseconds to simulate snooze
			if (minutes  > 60){
				setAlarmHour(hour+1);  //convert to next hour
				
			
			}
		}
		catch (InterruptedException exception){
			System.out.println("terminated prematurely");
		}
		setAlarmClockOn();
	}



}
