import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class FreeTime {
	/**
	 * A list of free time events.
	 */
	private static ArrayList<VEvent> freeTime = new ArrayList<VEvent>();
	
	
	/**
	 * A method that takes all the .ics files in the specified directory and calculates the free time events between the events found.
	 * The results are then writen into an .ics file in the specified path.
	 * This method is intended to handle overlap between events.
	 * @param dir The specified directory.
	 * @param targetPath The specified path.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		
		String dir, targetPath;
		
		dir        = args[0];
		targetPath = args[1];
		
		Path path = Paths.get(dir);
		ICSFile schedule = new ICSFile();
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.{ics}")) {
		    for (Path entry: stream) {
		        schedule.readICSFile(entry.toString());
		    }
		
		ArrayList<VEvent> temp = schedule.calendarEvents;
		Collections.sort(temp);
		boolean check = true;
		int i3;
		
		for(int i = 0; i < temp.size(); i++){
			for(int i2 = (i - 1); 0 < i2; i2--){
				if(temp.get(i).getDateEnd().compareTo(temp.get(i2).getDateEnd()) < 0){
					check = false;
				}
			}
			if(check && i != (temp.size() - 1)){
				i3 = (i + 1);
				while(check && i3 < (temp.size() - 1)){
					if(temp.get(i).getDateEnd().compareTo(temp.get(i3).getDateStart()) < 0){
						check = false;
						freeTime.add(new VEvent());
						freeTime.get(freeTime.size() - 1).setType("VEVENT");
						freeTime.get(freeTime.size() - 1).setSummary("Free Time");
						freeTime.get(freeTime.size() - 1).setDateStart(temp.get(i).getDateEnd());
						freeTime.get(freeTime.size() - 1).setDateEnd(temp.get(i3).getDateStart());
					}
					else if(temp.get(i).getDateEnd().compareTo(temp.get(i3).getDateEnd()) < 0){
						check = false;
					}
					i3++;
				}
			}
			check = true;
		}
		
		ICSFile free = new ICSFile();
		for(int i = 0; i < freeTime.size(); i++){
			free.calendarEvents = new ArrayList<VEvent>();
			free.calendarEvents.add(freeTime.get(i));
			free.writeICSFile(targetPath + (i + 1) + ".ics");
		}
		
		} catch (IOException x) {
		    System.err.println(x);
		}
	}
	
}
