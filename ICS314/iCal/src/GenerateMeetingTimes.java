package ics;

import java.io.IOException;

public class GenerateMeetingTimes {

	public static void main(String[] args) throws IOException {
		FreeTime meetings = new FreeTime();
		meetings.generateMeetingTimes(args[0], args[1], args[2]);
	}

}
