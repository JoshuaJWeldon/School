package ics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple barebones file for reading and writing .ics files.
 * May not meet specifications.
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
	private ArrayList<VEvent> calendarevents; 
	
	/**
	 * A constructor method that sets the time zone to Pacific/Honolulu.
	 */
	public ICSFile(){
		calendarevents = new ArrayList<VEvent>();
		timezone = "Pacific/Honolulu";
	}
	
	/**
	 * Sorts the events in the current instance.
	 * Sort is based on start times and dates.
	 */
	public void sortEvents(){
		Collections.sort(calendarevents);
	}
	
	/**
	 * A constructor that sets a specified timezone.
	 * @param tz the desired timezone
	 */
	public ICSFile(String tz){
		calendarevents = new ArrayList<VEvent>();
		timezone = tz;
	}
	
	/**
	 * Removes the event at the specified position.
	 * Returns null if index is out of range.
	 * @param i The index of the specified event.
	 * @return Event removed if index is out of range then null.
	 */
	public VEvent removeEvent(int i){
		if(i < calendarevents.size()){
			VEvent temp = calendarevents.get(i);
			calendarevents.remove(i);
			return temp;
		}
		else return null;
	}
	
	/**
	 * Clears all of the events stored.
	 */
	public void clearEvents(){
		calendarevents = new ArrayList<VEvent>();
	}
	
	/**
	 * Adds an event object.
	 * @param e the event to be added.
	 */
	public void addEvent(VEvent e){
		calendarevents.add(e);
	}
	
	/**
	 * Returns an event specified by the index parameter.
	 * @param i Index of the event.
	 * @return The specified event.
	 */
	public VEvent getEvent(int i){
		return calendarevents.get(i);
	}
	
	/**
	 * Returns the amount of events stored in the current instance.
	 * @return The number of events.
	 */
	public int numOfEvents(){
		return calendarevents.size();
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
	public boolean checkFile(String fileName) throws IOException{
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
			String line;
			String[] data;
			
			while((line = fReader.readLine()) != null){
				data = line.split(":", 2);
				switch(data[0]){
				case "BEGIN": 
					if(!data[1].equals("VCALENDAR")){
						calendarevents.add(new VEvent());
						calendarevents.get((calendarevents.size() - 1)).setType(data[1]);
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
					calendarevents.get((calendarevents.size() - 1)).setClassification(data[1]);
					break;
				case "DTSTART":
					calendarevents.get((calendarevents.size() - 1)).setDateStart(data[1]);
					break;
				case "DTEND":
					calendarevents.get((calendarevents.size() - 1)).setDateEnd(data[1]);
					break;
				case "LOCATION":
					calendarevents.get((calendarevents.size() - 1)).setLocation(data[1]);
					break;
				case "PRIORITY":
					calendarevents.get((calendarevents.size() - 1)).setPriority(Integer.parseInt(data[1]));
					break;
				case "SUMMARY":
					calendarevents.get((calendarevents.size() - 1)).setSummary(data[1]);
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
		
		for(VEvent event: calendarevents){
			fWriter.newLine();
			fWriter.write("BEGIN:");
			fWriter.write(event.getType());
			fWriter.newLine();
			if(event.getDateTimeStart() != null){
				fWriter.write("DTSTART:");
				fWriter.write(event.getDateTimeStart());
				fWriter.newLine();
			}
			if(event.getDateTimeEnd() != null){
				fWriter.write("DTEND:");
				fWriter.write(event.getDateTimeEnd());
				fWriter.newLine();
			}
			if(event.getClassification() != null){
				fWriter.write("CLASS:");
				fWriter.write(event.getClassification());
				fWriter.newLine();
			}
			if(event.getLocation() != null){
				fWriter.write("LOCATION:");
				fWriter.write(event.getLocation());
				fWriter.newLine();
			}
			fWriter.write("PRIORITY:");
			fWriter.write(Integer.toString(event.getPriority()));
			fWriter.newLine();
			if(event.getSummary() != null){
				fWriter.write("SUMMARY:");
				fWriter.write(event.getSummary());
				fWriter.newLine();
			}
			
			fWriter.write("END:");
			fWriter.write(event.getType());
			fWriter.newLine();
		}
		fWriter.newLine();
		fWriter.write("END:VCALENDAR");
		fWriter.close();
	}
}
