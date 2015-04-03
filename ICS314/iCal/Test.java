package ics;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

	public static void main(String[] args) throws IOException {
		//System.out.println("20150311T093000Z".compareTo("20150311T090000Z"));
		FreeTime test = new FreeTime();
		test.findFreeTime("C:\\Users\\Standard\\Documents\\Test", "C:\\Users\\Standard\\Documents\\Test2\\newtest.ics");
	}

}
