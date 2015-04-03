package ics;

/**
 * This class represents an event for Calendar files.
 * The data conforms to the iCalendar standard.
 * This is an incomplete representation.
 * 
 * @author Oscar Hong
 *
 */

public class VEvent implements Comparable<VEvent>{
	/**
	 * The type of event (i.e. event, alarm, todo, etc.)
	 */
	private String type;
	/**
	 * A classification of the event.
	 */
	private String classification;
	/**
	 * The location of the event.
	 */
	private String location;
	/**
	 * The priority of the event. 
	 * Ranges from 0-9 and lower numbers are of a higher priority.
	 * 0 means that the priority is undefined. 
	 */
	private int priority;
	/**
	 * A short Summary of the event.
	 */
	private String summary;
	/**
	 * When the event begins.
	 * Format is YYYYMMDD"T"HHMMSS"Z".
	 */
	private String dateStart;
	/**
	 * When the event ends.
	 * Format is YYYYMMDD"T"HHMMSS"Z".
	 */
	private String dateEnd;
	
	/**
	 * Constructor for class.
	 * Priority is 0 by default.
	 */
	public VEvent(){
		priority = 0;
	}
	
	/**
	 * Accessor for the event's type.
	 * @return the type
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * Sets the type of event.
	 * @param t the new type
	 * @return the new type
	 */
	public String setType(String t){
		type = t;
		return type;
	}
	
	/**
	 * Acessor to the classification of the event.
	 * @return the classification of the event
	 */
	public String getClassification(){
		return classification;
	}
	
	/**
	 * Sets the classification of the event.
	 * @param c the new classification
	 * @return the new classification
	 */
	public String setClassification(String c){
		classification = c;
		return classification;
	}
	
	/**
	 * Acessor for the location of the event.
	 * @return the location of the event
	 */
	public String getLocation(){
		return location;
	}
	
	/**
	 * Sets the location of the event.
	 * @param l the new location
	 * @return the new location
	 */
	public String setLocation(String l){
		location = l;
		return location;
	}
	
	/**
	 * Accessor for the priority.
	 * Lower numbers mean higher priority.
	 * Values of 0-9.
	 * 0 = undefined priority.
	 * @return the priority for the event
	 */
	public int getPriority(){
		return priority;
	}
	
	/**
	 * Sets the priority.
	 * Lower numbers mean higher priority.
	 * Values of 0-9.
	 * @param p the new priority
	 * @return the new priority
	 */
	public int setPriority(int p){
		priority = p;
		return priority;
	}
	
	/**
	 * Accessor for the summary of the event.
	 * @return the summary
	 */
	public String getSummary(){
		return summary;
	}
	
	/**
	 * Sets the summary of the event.
	 * @param s the new summary
	 * @return the new summary
	 */
	public String setSummary(String s){
		summary = s;
		return summary;
	}
	
	/**
	 * Accessor for time the event starts.
	 * Format is YYYYMMDD"T"HHMMSS"Z".
	 * @return the start time
	 */
	public String getDateStart(){
		return dateStart;
	}
	
	/**
	 * Sets the start time of the event.
	 * Format is YYYYMMDD"T"HHMMSS"Z".
	 * @param ds the new start time
	 * @return the new start time
	 */
	public String setDateStart(String ds){
		dateStart = ds;
		return dateStart;
	}
	
	/**
	 * Accessor for the end time of the event.
	 * Format is YYYYMMDD"T"HHMMSS"Z".
	 * @return the end time
	 */
	public String getDateEnd(){
		return dateEnd;
	}
	
	/**
	 * Sets the end time for an event.
	 * Format is YYYYMMDD"T"HHMMSS"Z".
	 * @param de the new end time
	 * @return the new end time
	 */
	public String setDateEnd(String de){
		dateEnd = de;
		return de;
	}
	
	/**
	 * String representation of VEVENT.
	 * Mainly for testing purposes.
	 */
	public String toString(){
		String stringrep = ("TYPE: " + type + "\n" + "CLASS: " + classification + "\n" + "LOCATION: " + location + "\n" + "PRIORITY: " + priority + "\n" + "SUMMARY: " + summary + "\n" + "DTSTART: " + dateStart + "\n" + "DTEND: " + dateEnd+ "\n");
		return stringrep;
	}
	
	/*
	 * (non-Javadoc) Compares two VEvent through start date.
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(VEvent event2){
		return dateStart.compareTo(event2.getDateStart()); 
	}
}
