package Cal;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FreeTime {

	//TODO: timezone- check if all are the same
	/**
	 * A method that takes all the .ics files in the specified directory and calculates the free time events between the events found.
	 * The results are then writen into an .ics file in the specified path.
	 * This method is intended to handle overlap between events.
	 * @param dir The specified directory.
	 * @param targetPath The specified path.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		
		String  dir, targetPath, date;
		Path    path;
		ICSFile schedule, freetimes;
		VEvent  event1, event2, freetime;
		
		
		dir        = args[0];
		targetPath = args[1];
		
		path = Paths.get(dir);
		
		schedule = new ICSFile();
		
		String timezone = null;
		
		DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.{ics}");
		for (Path entry: stream) {
			if(schedule.numOfEvents() == 0){
				schedule.readICSFile(entry.toString());
				timezone = schedule.getTimeZone();
			}
			else{
				schedule.readICSFile(entry.toString());
				if(!timezone.equals(schedule.getTimeZone())){
					System.out.println("All events must be in the same time zone.");
					System.exit(1);
				}
			}
		}
	
		freetimes = new ICSFile(schedule.getTimeZone());
	
		if(schedule.numOfEvents() == 0){
			System.out.println("There are no events");
			System.exit(1);
		}
		
		schedule.sortEvents();

		event1 = schedule.getEvent(0);
		event2 = new VEvent();
		
		date = event1.getDateStart();
		
		if(!date.equals(event1.getDateEnd())){
			System.out.println("All events must be on the same date");
			System.exit(1);
		}
		
		if(event1.TimeToIntStart() != 0){
			freetime = new VEvent();
			freetime.setType("VEVENT");
			freetime.setSummary("Free Time");
			freetime.setDateStart(date + "T000000");
			freetime.setDateEnd(event1.getDateTimeStart());
			freetimes.addEvent(freetime);
		}
		
		if(schedule.numOfEvents() > 1){
			event2 = schedule.getEvent(1);
			if(!(date.equals(event2.getDateEnd())&&date.equals(event2.getDateStart()))){
				System.out.println("All events must be on the same date");
				System.exit(1);
			}
			
		}
		
		for(int i = 1; i < schedule.numOfEvents(); i++){
			
			freetime = new VEvent();
			
			if(event1.TimeToIntEnd() < event2.TimeToIntStart()){
				
				freetime = new VEvent();
				freetime.setType("VEVENT");
				freetime.setSummary("Free Time");
				freetime.setDateStart(event1.getDateTimeEnd());
				freetime.setDateEnd(event2.getDateTimeStart());
				freetimes.addEvent(freetime);
				
				event1 = event2;
			}
			
			if(event1.TimeToIntEnd() < event2.TimeToIntEnd()){
				event1 = event2;	
			}
			
			if(i + 1 < schedule.numOfEvents()){
				event2 = schedule.getEvent(i + 1);
				if(!(date.equals(event2.getDateEnd())&&date.equals(event2.getDateStart()))){
					System.out.println("All events must be on the same date");
					System.exit(1);
				}
			}
			
		}
		
		//after
		if(event1.TimeToIntEnd() < 235999){
			freetime = new VEvent();
			freetime.setType("VEVENT");
			freetime.setSummary("Free Time");
			freetime.setDateStart(event1.getDateTimeEnd());
			freetime.setDateEnd(date + "T235959");
			freetimes.addEvent(freetime);
		}
		
		if(freetimes.numOfEvents() == 0){
			System.out.println("Could not generate any free time");
			System.exit(1);
		}
		
		freetimes.writeICSFile(targetPath);
		
		
		
	}
	
}
