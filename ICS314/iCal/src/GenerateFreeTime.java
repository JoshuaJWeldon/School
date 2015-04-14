package ics;

import java.io.IOException;

public class GenerateFreeTime {

	public static void main(String[] args) throws IOException {
		FreeTime freetime = new FreeTime();
		freetime.generateFreeTime(args[0], args[1]);
	}

}
