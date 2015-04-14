package ics;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A file for methods to determine free times based on people's schedules and meeting times based on two sets of schedules.
 * This class can be modified to support finding free times between more people.
 * Free time is specified as the time between two events.
 * Input events must have the same date and timezone.
 * This program is meant to be able to deal with overlapping events.
 * This program determines priorities for free time events based on the busy events surrounding it.
 * This program determines the priority of meeting times based on the priority of the free time events that lead to it.
 * The priority of a meeting time event represents it's convenience.
 * @author Oscar Hong
 * @author Joshua Weldon
 *
 */

public class FreeTime {
	/**
	 * Checks an event to ensure that the start time and end time occur on the specified date.
	 * If the check fails then the program will exit.
	 * @param date The specified date.
	 * @param event The event to be examined.
	 */
	private void checkDate(String date, VEvent event){
		if(!date.equals(event.getDateEnd()) || !date.equals(event.getDateStart())){
			System.out.println("All events must be on the same date");
			System.exit(1);
		}
	}
	
	/**
	 * Calculates the priority of a free time event based on the priorities of the events before and after it.
	 * Since a priority of zero is undefined it is equivalent to a priority of 5, which is the average priority.
	 * The free time priority varies inversely with the priorities of the events before and after it.
	 * The priority of the next event is given 4 times the weight of the priority of the prior event.
	 * @param event1 The event prior to the free time.
	 * @param event2 The event after the free time.
	 * @return The priority of the free time event.
	 */
	private int calculateFreeTimePriority(VEvent event1, VEvent event2){
		int priority1;
		int priority2;
		
		if(event1.getPriority() == 0){
			priority1 = 5;
		}
		else{
			priority1 = event1.getPriority();
		}
		if(event2.getPriority() == 0){
			priority2 = 5;
		}
		else{
			priority2 = event2.getPriority();
		}
		
		double priority = (.2 * priority1) + (.8 * priority2);
		
		priority = 10 - priority;
		
		return (int) Math.round(priority);
	}
	
	//TODO: timezone- check if all are the same
	/**
	 * A method that takes all the .ics files in the specified directory and calculates the free time events between the events found.
	 * The results are then writen into an ICSFile object.
	 * This method is intended to be able to handle overlap between events.
	 * All free time events have the summary "Free Time."
	 * The program will exit with an appropriate message if no free time events can be found.
	 * @param dir The specified directory.
	 * @return An ICSFile object containing the free time events.
	 * @throws IOException
	 */
	private ICSFile findFreeTime(String sourcedir) throws IOException{
		String date;
		Path path = Paths.get(sourcedir);
		ICSFile schedule, freetimes;
		VEvent  event1, event2, freetime;
		
		
		schedule = new ICSFile();
		event2 = new VEvent();
		
		DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.{ics}");
		for (Path entry: stream) {
			schedule.readICSFile(entry.toString());
		}
	
		freetimes = new ICSFile(schedule.getTimeZone());
	
		if(schedule.numOfEvents() == 0){
			System.out.println("There are no events");
			System.exit(1);
		}
		
		schedule.sortEvents();

		event1 = schedule.getEvent(0);
		
		date = event1.getDateStart();
		
		checkDate(date, event1);
		
		if(schedule.numOfEvents() > 1){
			event2 = schedule.getEvent(1);
			checkDate(date, event2);
			
		}
		/*
		 * The following adds free time event when a time gap is found between the start of one activity and the end of another.
		 * If two events overlap the one with the latest end time will be used.
		 * If two events have the same end time the one with the higher priority will be used. 0 counts as a 5.
		 */
		for(int i = 1; i < schedule.numOfEvents(); i++){
			
			freetime = new VEvent();
			
			if(event1.getTimeEnd() < event2.getTimeStart()){	
				int priority = calculateFreeTimePriority(event1, event2);
				
				freetime = new VEvent();
				freetime.setType("VEVENT");
				freetime.setSummary("Free Time");
				freetime.setDateStart(event1.getDateTimeEnd());
				freetime.setDateEnd(event2.getDateTimeStart());
				freetime.setPriority(priority);
				freetimes.addEvent(freetime);
				
				event1 = event2;
			}
			
			if(event1.getTimeEnd() < event2.getTimeEnd()){
				event1 = event2;	
			}
			else if (event1.getTimeEnd() == event2.getTimeEnd()){
				if(event1.getPriority() < event2.getPriority()){
					event1 = event2;
				}
			}
			
			if(i + 1 < schedule.numOfEvents()){
				event2 = schedule.getEvent(i + 1);
				checkDate(date, event2);
			}
			
		}
		
		if(freetimes.numOfEvents() == 0){
			System.out.println("Could not generate any free time");
			System.exit(1);
		}
		
		return freetimes;
	}
	
	/**
	 * A method that takes all the .ics files in the specified directory and calculates the free time events between the events found.
	 * The results are then writen into an .ics file in the specified path.
	 * This method is intended to be able to handle overlap between events.
	 * @param dir The specified directory.
	 * @param targetPath The specified path, including the name of the file..
	 * @throws IOException
	 */
	public void generateFreeTime(String sourcedir, String targetpath) throws IOException{
		ICSFile freetimes = findFreeTime(sourcedir);

		exportEvents(targetpath, freetimes, -1);
	}
	
	/**
	 * Creates a group of .ics files in the specified directory using the ICSFile object given.
	 * Each event is given it's own folder.
	 * The names of each file is the name specified within the target path in addition to a number (i.e. name1.ics, name2.ics, ... nameN.ics).
	 * @param targetpath The path that the files will be exported to. Includes the desired filenames.
	 * @param export The ICSFile containing the events to export.
	 * @throws IOException
	 */
	private void exportEvents(String targetpath, ICSFile export, int besttime) throws IOException{
		ICSFile temp;
		
		for(int i = 0; i < export.numOfEvents(); i++){
			temp = new ICSFile();
			temp.addEvent(export.getEvent(i));
			if(i != besttime){
				temp.writeICSFile(targetpath + (i + 1) + ".ics");
			}
			else{
				temp.writeICSFile(targetpath + (i + 1) + "BestTime.ics");
			}
		}
	}
	
	/**
	 * A helper method for creating a possible meeting event.
	 * Priority is the average of the two, rounded up.
	 * Technically, it doesn't matter which order the priorities are passed.
	 * @param start The start time of the possible meeting.
	 * @param end The end time of the possible meeting.
	 * @param priority1 The priority of the first event.
	 * @param priority2 The priority of the second event.
	 * @return A VEvent object representing the possible meeting time.
	 */
	private VEvent possibleMeeting(String start, String end, int priority1, int priority2){
		VEvent possiblemeeting = new VEvent();
		double priority = priority1 + priority2;
		
		priority = priority/2;
		possiblemeeting.setType("VEVENT");
		possiblemeeting.setSummary("POSSIBLE MEETING TIME");
		possiblemeeting.setDateStart(start);
		possiblemeeting.setDateEnd(end);
		possiblemeeting.setPriority((int) Math.round(priority));
		
		return possiblemeeting;
	}
	
	/**
	 * Determines the position of the meeting in the ICSFile that is the most convenient time or at least one of them.
	 * Uses the priority number listed in the event.
	 * @param meetings The ICSFile containing the list of events.
	 * @return The position of the most suitable meeting.
	 */
	private int bestTime(ICSFile meetings){
		int besttime = -1;
		int bestpriority = 0;
		
		for(int i = 0; i < meetings.numOfEvents(); i++){
			if(meetings.getEvent(i).getPriority() > bestpriority){
				bestpriority = meetings.getEvent(i).getPriority();
				besttime = i;
			}
		}
		return besttime;
	}
	
	/**
	 * A method that determines possible meeting times between two people.
	 * The events are extracted from the two specified directories.
	 * Each directory represents the events of a person's day.
	 * A priority will be assigned to each meeting time that reflects the convenience of the meeting.
	 * The priority is based off of the priorities of the two free time events.
	 * Program will exit if no meeting times are found or if the dates of the two schedules are different.
	 * @param sourcedir1 The directory representing the first set of events.
	 * @param sourcedir2 The directory representing the second set of events.
	 * @return An ICSFile containing possible meeting times.
	 * @throws IOException
	 */
	private ICSFile findPossibleMeetingTimes(String sourcedir1, String sourcedir2) throws IOException{
		ICSFile meetingtimes = new ICSFile();
		ICSFile firstschedule = findFreeTime(sourcedir1);
		ICSFile secondschedule = findFreeTime(sourcedir2);
		VEvent event1 = firstschedule.removeEvent(0);
		VEvent event2 = secondschedule.removeEvent(0);
		
		checkDate(event1.getDateStart(), event2);
		
		/*
		 * The following loop checks for all cases when comparing two free times to each other.
		 * Events are removed from the two ICSFiles until one or both are empty.
		 * Depending on the case, the events are modified or become a removed event from an ics file.
		 */
		while((event1 != null) && (event2 != null)){		
			if((event1.getTimeStart() == event2.getTimeStart()) && (event1.getTimeEnd() == event2.getTimeEnd())){
				meetingtimes.addEvent(possibleMeeting(event1.getDateTimeStart(), event1.getDateTimeEnd(), event1.getPriority(), event2.getPriority()));
				event1 = firstschedule.removeEvent(0);
				event2 = secondschedule.removeEvent(0);
			}
			else if((event1.getTimeStart() < event2.getTimeEnd()) && (event1.getTimeEnd() > event2.getTimeStart())){
				if(event1.getTimeStart() > event2.getTimeStart()){
					if(event1.getTimeEnd() > event2.getTimeEnd()){
						meetingtimes.addEvent(possibleMeeting(event1.getDateTimeStart(), event2.getDateTimeEnd(), event1.getPriority(), event2.getPriority()));
						event1.setDateStart(event2.getDateTimeEnd());
						event2 = secondschedule.removeEvent(0);
					}
					else{
						meetingtimes.addEvent(possibleMeeting(event1.getDateTimeStart(), event1.getDateTimeEnd(), event1.getPriority(), event2.getPriority()));
						event2.setDateStart(event1.getDateTimeEnd());
						event1 = firstschedule.removeEvent(0);
					}
				}
				else {
					if(event1.getTimeEnd() < event2.getTimeEnd()){
						meetingtimes.addEvent(possibleMeeting(event2.getDateTimeStart(), event1.getDateTimeEnd(), event1.getPriority(), event2.getPriority()));
						event2.setDateStart(event1.getDateTimeEnd());
						event1 = firstschedule.removeEvent(0);
					}
					else {
						meetingtimes.addEvent(possibleMeeting(event2.getDateTimeStart(), event2.getDateTimeEnd(), event1.getPriority(), event2.getPriority()));
						event1.setDateStart(event2.getDateTimeEnd());
						event2 = secondschedule.removeEvent(0);
					}
				}
			}
			else {
				if(event1.getTimeStart() > event2.getTimeStart()){
					event2 = secondschedule.removeEvent(0);
				}
				else{
					event1 = firstschedule.removeEvent(0);
				}
			}	
		}
		
		if(meetingtimes.numOfEvents() == 0){
			System.out.println("Could not find any common free time");
			System.exit(1);
		}
		
		return meetingtimes;
	}
	
	/**
	 * A method that takes two directories of .ics files, finds the free time events they have in common, and then finds possible meeting times based on free time events.
	 * The list of possible meeting times is exported to the specified folder in targetpath and includes the name of the desired files (i.e. name1.ics, name2.ics,... nameN.ics).
	 * The best time found for a meeting will have "BestTime" added to the name of the file.
	 * The events must occur on the same day and timezone.
	 * @param sourcedir1 The directory containing the first set of events.
	 * @param sourcedir2 The directory containing the second set of events.
	 * @param targetpath The path that the files will be generated in (including the name of the file).
	 * @throws IOException
	 */
	public void generateMeetingTimes(String sourcedir1, String sourcedir2, String targetpath) throws IOException{
		ICSFile meetingtimes = findPossibleMeetingTimes(sourcedir1, sourcedir2);
		
		exportEvents(targetpath, meetingtimes, bestTime(meetingtimes));
	}
	
}
