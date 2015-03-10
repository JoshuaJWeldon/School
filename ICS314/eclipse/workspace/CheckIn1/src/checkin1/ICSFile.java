package ics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * A simple barebones file for reading and writing .ics files.
 * Has NOT been tested as of yet.
 * Currently does not meet all specifications.
 * Only deals with the following variables: version, classification, location, priority, summary, dtstart, dtend, time zone identifier
 * @author Oscar Hong
 *
 */

public class ICSFile {
	/**
	 * The current version number.
	 * 2.0 by default.
	 */
	private String version = "2.0";
	/**
	 * The timezone of the events.
	 * Set to Pacific/Honolulu by default.
	 */
	private String timezone;
	/**
	 * An ArrayList containing objects of the VEvent class, which is used to store information about a single event.
	 */
	private ArrayList<VEvent> calendarEvents; 
	
	/**
	 * A constructor method that sets the time zone to Pacific/Honolulu.
	 */
	public ICSFile(){
		timezone = "Pacific/Honolulu";
	}
	
	/**
	 * A constructor that sets a specified timezone.
	 * @param tz the desired timezone
	 */
	public ICSFile(String tz){
		timezone = tz;
	}
	
	/**
	 * Accessor to the timezone.
	 * @return the current timezone.
	 */
	public String getTimeZone(){
		return timezone;
	}
	
	/**
	 * Sets the timezone used.
	 * @param tz the desired timezone
	 * @return the new timezone
	 */
	public String setTimeZone(String tz){
		timezone = tz;
		return timezone;
	}
	
	/**
	 * Checks if a file exists and has a .ics extension.
	 * @param fileName the name of the file
	 * @return true if the file exists and has an ics exension and false otherwise
	 */
	public boolean checkFile(String fileName) {
		File f = new File(fileName);
		if(f.isFile() && fileName.toLowerCase().endsWith(".ics")){
			return true;
		}
		else return false;
	}
	
	/**
	 * Reads an .ics file and stores the even information into an array.
	 * Data is stored in VEvent objects.
	 * Makes some assumptions about the way .ics files are written.
	 * @param filename the name of the .ics file to be read
	 * @throws IOException
	 */
	public void readICSFile(String filename) throws IOException {
		if(checkFile(filename)){
			BufferedReader fReader = new BufferedReader(new FileReader(filename));
			calendarEvents = new ArrayList<VEvent>();
			String line;
			String[] data;
			
			while((line = fReader.readLine()) != null){
				data = line.split(":", 2);
				switch(data[0]){
				case "BEGIN": 
					if(!data[1].equals("VCALENDAR")){
						calendarEvents.add(new VEvent());
						calendarEvents.get((calendarEvents.size() - 1)).setType(data[1]);
					}
					else{
						//do something?
					}
					break;
				case "VERSION":
					version = data[1];
					break;
				case "TZID":
					timezone = data[1];
					break;
				case "CLASS":
					calendarEvents.get((calendarEvents.size() - 1)).setClassification(data[1]);
					break;
				case "DTSART":
					calendarEvents.get((calendarEvents.size() - 1)).setDateStart(data[1]);
					break;
				case "DTEND":
					calendarEvents.get((calendarEvents.size() - 1)).setDateEnd(data[1]);
					break;
				case "LOCATION":
					calendarEvents.get((calendarEvents.size() - 1)).setLocation(data[1]);
					break;
				case "PRIORITY":
					calendarEvents.get((calendarEvents.size() - 1)).setPriority(Integer.parseInt(data[1]));
					break;
				case "SUMMARY":
					calendarEvents.get((calendarEvents.size() - 1)).setSummary(data[1]);
					break;
				case "END": //do something?
					break;
				}
			}
			fReader.close();
		}
	}
	
	/**
	 * Writes a .ics file using an array of VEvent objects.
	 * The .ics file will contain events and nothing else.
	 * @param filename
	 * @throws IOException
	 */
	public void writeICSFile(String filename)throws IOException{
		BufferedWriter fWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename, false), "utf-8"));
		fWriter.write("BEGIN:VCALENDAR");
		fWriter.newLine();
		fWriter.write("VERSION:");
		fWriter.write(version);
		fWriter.newLine();
		fWriter.write("TZID:");
		fWriter.write(timezone);
		fWriter.newLine();
		
		for(int i = 0; i < calendarEvents.size(); i++){
			fWriter.write("BEGIN:");
			fWriter.write(calendarEvents.get(i).getType());
			fWriter.newLine();
			if(calendarEvents.get(i).getDateStart() != null){
				fWriter.write("DTSTART:");
				fWriter.write(calendarEvents.get(i).getDateStart());
				fWriter.newLine();
			}
			if(calendarEvents.get(i).getDateEnd() != null){
				fWriter.write("DTEND");
				fWriter.write(calendarEvents.get(i).getDateEnd());
				fWriter.newLine();
			}
			if(calendarEvents.get(i).getClassification() != null){
				fWriter.write("CLASS:");
				fWriter.write(calendarEvents.get(i).getClassification());
				fWriter.newLine();
			}
			if(calendarEvents.get(i).getLocation() != null){
				fWriter.write("LOCATION:");
				fWriter.write(calendarEvents.get(i).getLocation());
				fWriter.newLine();
			}
			fWriter.write("PRIORITY:");
			fWriter.write(calendarEvents.get(i).getPriority());
			fWriter.newLine();
			if(calendarEvents.get(i).getSummary() != null){
				fWriter.write("SUMMARY:");
				fWriter.write(calendarEvents.get(i).getSummary());
				fWriter.newLine();
			}
			
			fWriter.write("END:");
			fWriter.write(calendarEvents.get(i).getType());
			fWriter.newLine();
		}
		fWriter.write("END:VCALENDAR");
		fWriter.close();
	}
}
