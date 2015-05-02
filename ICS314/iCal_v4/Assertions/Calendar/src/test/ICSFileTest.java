package test;

import static org.junit.Assert.*;

import java.io.IOException;

import Cal.ICSFile;
import Cal.VEvent;

import org.junit.Test;

public class ICSFileTest {

	@Test
	public void numofeventstest() {
		ICSFile icsf = new ICSFile();
		assertEquals(0, icsf.numOfEvents());
	}
	
	@Test
	public void numofeventstest2() throws IOException {
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("TestFiles1\\test2.ics");
		assertEquals(7, icsf.numOfEvents());
	}
	
	@Test
	public void numofeventstest3() throws IOException {
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("TestFiles2\\testfile1.ics");
		assertEquals(1, icsf.numOfEvents());
	}
	
	@Test
	public void numofeventstest4() throws IOException {
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("BadTestFiles\\Checkin 3 Example Input Part 1.png");
		assertEquals(0, icsf.numOfEvents());
	}
	
	@Test
	public void numofeventstest5() throws IOException {
		ICSFile icsf = new ICSFile();
		icsf.addEvent(new VEvent());
		assertEquals(1, icsf.numOfEvents());
	}
	
	@Test
	public void numofeventstest6() throws IOException {
		ICSFile icsf = new ICSFile();
		icsf.addEvent(new VEvent());
		icsf.addEvent(new VEvent());
		assertEquals(2, icsf.numOfEvents());
	}
	
	@Test
	public void numofeventstest7() throws IOException {
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("TestFiles1\\test2.ics");
		icsf.addEvent(new VEvent());
		assertEquals(8, icsf.numOfEvents());
	}
	
	@Test
	public void timezonetest() {
		ICSFile icsf = new ICSFile();
		assertEquals("Pacific/Honolulu", icsf.getTimeZone());
	}
	
	@Test
	public void timezonetest2() {
		ICSFile icsf = new ICSFile("America/New_York");
		assertEquals("America/New_York", icsf.getTimeZone());
	}
	
	@Test
	public void timezonetest3() throws IOException {
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("DifferentTimeZoneEvent\\newyork.ics");
		assertEquals("America/New_York", icsf.getTimeZone());
	}
	
	@Test
	public void timezonetest4() throws IOException {
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("DifferentTimeZoneEvent\\newyork.ics");
		icsf.setTimeZone("Pacific/Honolulu");
		assertEquals("Pacific/Honolulu", icsf.getTimeZone());
	}
	
	@Test
	public void sorttest() throws IOException {
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("TestFiles1\\test2.ics");
		boolean correctorder = true;
		for(int i = 0; i < icsf.numOfEvents() - 1; i++){
			if(icsf.getEvent(i).compareTo(icsf.getEvent(i + 1)) >= 0){
				correctorder = false;
			}
		}
		assertEquals(false, correctorder);
	}
	
	@Test
	public void sorttest2() throws IOException {
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("TestFiles1\\test2.ics");
		boolean correctorder = true;
		icsf.sortEvents();
		for(int i = 0; i < icsf.numOfEvents() - 1; i++){
			if(icsf.getEvent(i).compareTo(icsf.getEvent(i + 1)) >= 0){
				correctorder = false;
			}
		}
		assertEquals(true, correctorder);
	}
	
	@Test
	public void readwritetest() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		icsf.readICSFile("TestFiles2\\testfile1.ics");
		icsf.writeICSFile("OutputFiles1\\test2.ics");
		icsf2.readICSFile("OutputFiles1\\test2.ics");
		assertEquals("VEVENT", icsf2.getEvent(0).getType());
	}
	
	@Test
	public void readwritetest2() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		icsf.readICSFile("TestFiles2\\testfile1.ics");
		icsf.writeICSFile("OutputFiles1\\test2.ics");
		icsf2.readICSFile("OutputFiles1\\test2.ics");
		assertEquals("20150511T020000", icsf2.getEvent(0).getDateTimeStart());
	}
	
	@Test
	public void readwritetest3() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		icsf.readICSFile("TestFiles2\\testfile1.ics");
		icsf.writeICSFile("OutputFiles1\\test2.ics");
		icsf2.readICSFile("OutputFiles1\\test2.ics");
		assertEquals("20150511T040000", icsf2.getEvent(0).getDateTimeEnd());
	}
	
	@Test
	public void readwritetest4() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		icsf.readICSFile("TestFiles2\\testfile1.ics");
		icsf.writeICSFile("OutputFiles1\\test2.ics");
		icsf2.readICSFile("OutputFiles1\\test2.ics");
		assertEquals(9, icsf2.getEvent(0).getPriority());
	}
	
	@Test
	public void readwritetest5() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		icsf.readICSFile("TestFiles2\\testfile1.ics");
		icsf.writeICSFile("OutputFiles1\\test2.ics");
		icsf2.readICSFile("OutputFiles1\\test2.ics");
		assertEquals("Busy", icsf2.getEvent(0).getSummary());
	}
	
	@Test
	public void readwritetest6() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		icsf.readICSFile("TestFiles2\\testfile1.ics");
		icsf.writeICSFile("OutputFiles1\\test2.ics");
		icsf2.readICSFile("OutputFiles1\\test2.ics");
		assertEquals(null, icsf2.getEvent(0).getLocation());
	}
	
	@Test
	public void secondreadwritetest() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		ICSFile icsf3 = new ICSFile();
		icsf.readICSFile("TestFiles2\\testfile1.ics");
		icsf.writeICSFile("OutputFiles1\\test2.ics");
		icsf2.readICSFile("OutputFiles1\\test2.ics");
		icsf2.writeICSFile("OutputFiles1\\test2.ics");
		icsf3.readICSFile("OutputFiles1\\test2.ics");
		assertEquals("VEVENT", icsf3.getEvent(0).getType());
	}
	
	@Test
	public void secondreadwritetest2() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		ICSFile icsf3 = new ICSFile();
		icsf.readICSFile("TestFiles2\\testfile1.ics");
		icsf.writeICSFile("OutputFiles1\\test2.ics");
		icsf2.readICSFile("OutputFiles1\\test2.ics");
		icsf2.writeICSFile("OutputFiles1\\test2.ics");
		icsf3.readICSFile("OutputFiles1\\test2.ics");
		assertEquals("20150511T020000", icsf3.getEvent(0).getDateTimeStart());
	}
	
	@Test
	public void secondreadwritetest3() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		ICSFile icsf3 = new ICSFile();
		icsf.readICSFile("TestFiles2\\testfile1.ics");
		icsf.writeICSFile("OutputFiles1\\test2.ics");
		icsf2.readICSFile("OutputFiles1\\test2.ics");
		icsf2.writeICSFile("OutputFiles1\\test2.ics");
		icsf3.readICSFile("OutputFiles1\\test2.ics");
		assertEquals("20150511T040000", icsf3.getEvent(0).getDateTimeEnd());
	}
	
	@Test
	public void secondreadwritetest4() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		ICSFile icsf3 = new ICSFile();
		icsf.readICSFile("TestFiles2\\testfile1.ics");
		icsf.writeICSFile("OutputFiles1\\test2.ics");
		icsf2.readICSFile("OutputFiles1\\test2.ics");
		icsf2.writeICSFile("OutputFiles1\\test2.ics");
		icsf3.readICSFile("OutputFiles1\\test2.ics");
		assertEquals(9, icsf3.getEvent(0).getPriority());
	}
	
	@Test
	public void secondreadwritetest5() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		ICSFile icsf3 = new ICSFile();
		icsf.readICSFile("TestFiles2\\testfile1.ics");
		icsf.writeICSFile("OutputFiles1\\test2.ics");
		icsf2.readICSFile("OutputFiles1\\test2.ics");
		icsf2.writeICSFile("OutputFiles1\\test2.ics");
		icsf3.readICSFile("OutputFiles1\\test2.ics");
		assertEquals("Busy", icsf3.getEvent(0).getSummary());
	}
	
	@Test
	public void secondreadwritetest6() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		ICSFile icsf3 = new ICSFile();
		icsf.readICSFile("TestFiles2\\testfile1.ics");
		icsf.writeICSFile("OutputFiles1\\test2.ics");
		icsf2.readICSFile("OutputFiles1\\test2.ics");
		icsf2.writeICSFile("OutputFiles1\\test2.ics");
		icsf3.readICSFile("OutputFiles1\\test2.ics");
		assertEquals(null, icsf3.getEvent(0).getLocation());
	}
	
	@Test
	public void thirdreadwritetest() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		ICSFile icsf3 = new ICSFile();
		icsf.readICSFile("TestFiles1\\test.ics");
		icsf.writeICSFile("OutputFiles1\\test3.ics");
		icsf2.readICSFile("OutputFiles1\\test3.ics");
		icsf2.writeICSFile("OutputFiles1\\test3.ics");
		icsf3.readICSFile("OutputFiles1\\test3.ics");
		assertEquals("UH", icsf3.getEvent(0).getLocation());
	}
	
	@Test
	public void thirdreadwritetest2() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		ICSFile icsf3 = new ICSFile();
		icsf.readICSFile("TestFiles1\\test.ics");
		icsf.writeICSFile("OutputFiles1\\test3.ics");
		icsf2.readICSFile("OutputFiles1\\test3.ics");
		icsf2.writeICSFile("OutputFiles1\\test3.ics");
		icsf3.readICSFile("OutputFiles1\\test3.ics");
		assertEquals("Home", icsf3.getEvent(1).getLocation());
	}
	
	@Test
	public void thirdreadwritetest3() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		ICSFile icsf3 = new ICSFile();
		icsf.readICSFile("TestFiles1\\test.ics");
		icsf.writeICSFile("OutputFiles1\\test3.ics");
		icsf2.readICSFile("OutputFiles1\\test3.ics");
		icsf2.writeICSFile("OutputFiles1\\test3.ics");
		icsf3.readICSFile("OutputFiles1\\test3.ics");
		assertEquals("Event", icsf3.getEvent(1).getSummary());
	}
	
	@Test
	public void thirdreadwritetest4() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		ICSFile icsf3 = new ICSFile();
		icsf.readICSFile("TestFiles1\\test.ics");
		icsf.writeICSFile("OutputFiles1\\test3.ics");
		icsf2.readICSFile("OutputFiles1\\test3.ics");
		icsf2.writeICSFile("OutputFiles1\\test3.ics");
		icsf3.readICSFile("OutputFiles1\\test3.ics");
		assertEquals(0, icsf3.getEvent(1).getPriority());
	}
	
	@Test
	public void thirdreadwritetest5() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		ICSFile icsf3 = new ICSFile();
		icsf.readICSFile("TestFiles1\\test.ics");
		icsf.writeICSFile("OutputFiles1\\test3.ics");
		icsf2.readICSFile("OutputFiles1\\test3.ics");
		icsf2.writeICSFile("OutputFiles1\\test3.ics");
		icsf3.readICSFile("OutputFiles1\\test3.ics");
		assertEquals("20150511T070000", icsf3.getEvent(1).getDateTimeStart());
	}
	
	@Test
	public void thirdreadwritetest6() throws IOException {
		ICSFile icsf = new ICSFile();
		ICSFile icsf2 = new ICSFile();
		ICSFile icsf3 = new ICSFile();
		icsf.readICSFile("TestFiles1\\test.ics");
		icsf.writeICSFile("OutputFiles1\\test3.ics");
		icsf2.readICSFile("OutputFiles1\\test3.ics");
		icsf2.writeICSFile("OutputFiles1\\test3.ics");
		icsf3.readICSFile("OutputFiles1\\test3.ics");
		assertEquals("20150511T040000", icsf3.getEvent(0).getDateTimeEnd());
	}

}
