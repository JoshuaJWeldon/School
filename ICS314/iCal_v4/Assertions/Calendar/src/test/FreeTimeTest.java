package test;

import static org.junit.Assert.*;

import java.io.IOException;

import Cal.ICSFile;
import Cal.FreeTime;

import org.junit.Test;


public class FreeTimeTest {

	@Test
	public void test() throws IOException {
		String args[] = {"TestFiles1", "OutputFiles2\\test.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test.ics");
		boolean correctorder = true;
		for(int i = 0; i < icsf.numOfEvents() - 1; i++){
			if(icsf.getEvent(i).compareTo(icsf.getEvent(i + 1)) >= 0){
				correctorder = false;
			}
		}
		assertEquals(true, correctorder);
	}
	
	@Test
	public void test2() throws IOException {
		String args[] = {"TestFiles1", "OutputFiles2\\test.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test.ics");
		assertEquals(0, icsf.getEvent(0).getPriority());
	}
	
	@Test
	public void test3() throws IOException {
		String args[] = {"TestFiles1", "OutputFiles2\\test.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test.ics");
		boolean summaryfreetime = true;
		for(int i = 0; i < icsf.numOfEvents(); i++){
			if(!icsf.getEvent(i).getSummary().equals("Free Time")){
				summaryfreetime = false;
			}
		}
		assertEquals(true, summaryfreetime);
	}
	
	@Test
	public void test4() throws IOException {
		String args[] = {"TestFiles1", "OutputFiles2\\test.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test.ics");
		assertEquals("20150511T000000", icsf.getEvent(0).getDateTimeStart());
	}
	
	@Test
	public void test5() throws IOException {
		String args[] = {"TestFiles1", "OutputFiles2\\test.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test.ics");
		assertEquals("20150511T010000", icsf.getEvent(0).getDateTimeEnd());
	}
	
	@Test
	public void test6() throws IOException {
		String args[] = {"TestFiles1", "OutputFiles2\\test.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test.ics");
		assertEquals("20150511T050000", icsf.getEvent(2).getDateTimeStart());
	}
	
	@Test
	public void test7() throws IOException {
		String args[] = {"TestFiles1", "OutputFiles2\\test.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test.ics");
		assertEquals("20150511T070000", icsf.getEvent(2).getDateTimeEnd());
	}
	
	@Test
	public void nooverlaptest() throws IOException {
		String args[] = {"TestFiles2", "OutputFiles2\\test2.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test2.ics");
		boolean correctorder = true;
		for(int i = 0; i < icsf.numOfEvents() - 1; i++){
			if(icsf.getEvent(i).compareTo(icsf.getEvent(i + 1)) >= 0){
				correctorder = false;
			}
		}
		assertEquals(true, correctorder);
	}
	
	@Test
	public void nooverlaptest2() throws IOException {
		String args[] = {"TestFiles2", "OutputFiles2\\test2.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test2.ics");
		assertEquals(0, icsf.getEvent(0).getPriority());
	}
	
	@Test
	public void nooverlaptest3() throws IOException {
		String args[] = {"TestFiles2", "OutputFiles2\\test2.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test2.ics");
		boolean summaryfreetime = true;
		for(int i = 0; i < icsf.numOfEvents(); i++){
			if(!icsf.getEvent(i).getSummary().equals("Free Time")){
				summaryfreetime = false;
			}
		}
		assertEquals(true, summaryfreetime);
	}
	
	@Test
	public void nooverlaptest4() throws IOException {
		String args[] = {"TestFiles2", "OutputFiles2\\test2.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test2.ics");
		assertEquals("20150511T000000", icsf.getEvent(0).getDateTimeStart());
	}
	
	@Test
	public void nooverlaptest5() throws IOException {
		String args[] = {"TestFiles2", "OutputFiles2\\test2.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test2.ics");
		assertEquals("20150511T010000", icsf.getEvent(0).getDateTimeEnd());
	}
	
	@Test
	public void nooverlaptest6() throws IOException {
		String args[] = {"TestFiles2", "OutputFiles2\\test2.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test2.ics");
		assertEquals("20150511T040000", icsf.getEvent(2).getDateTimeStart());
	}
	
	@Test
	public void nooverlaptest7() throws IOException {
		String args[] = {"TestFiles2", "OutputFiles2\\test2.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test2.ics");
		assertEquals("20150511T050000", icsf.getEvent(2).getDateTimeEnd());
	}
	
	@Test
	public void partialtest() throws IOException {
		String args[] = {"TestFiles3", "OutputFiles2\\test3.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test3.ics");
		boolean correctorder = true;
		for(int i = 0; i < icsf.numOfEvents() - 1; i++){
			if(icsf.getEvent(i).compareTo(icsf.getEvent(i + 1)) >= 0){
				correctorder = false;
			}
		}
		assertEquals(true, correctorder);
	}
	
	@Test
	public void partialtest2() throws IOException {
		String args[] = {"TestFiles3", "OutputFiles2\\test3.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test3.ics");
		assertEquals(0, icsf.getEvent(0).getPriority());
	}
	
	@Test
	public void partialtest3() throws IOException {
		String args[] = {"TestFiles3", "OutputFiles2\\test3.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test3.ics");
		boolean summaryfreetime = true;
		for(int i = 0; i < icsf.numOfEvents(); i++){
			if(!icsf.getEvent(i).getSummary().equals("Free Time")){
				summaryfreetime = false;
			}
		}
		assertEquals(true, summaryfreetime);
	}
	
	@Test
	public void partialtest4() throws IOException {
		String args[] = {"TestFiles3", "OutputFiles2\\test3.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test3.ics");
		assertEquals("20150511T000000", icsf.getEvent(0).getDateTimeStart());
	}
	
	@Test
	public void partialtest5() throws IOException {
		String args[] = {"TestFiles3", "OutputFiles2\\test3.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test3.ics");
		assertEquals("20150511T010000", icsf.getEvent(0).getDateTimeEnd());
	}
	
	@Test
	public void partialtest6() throws IOException {
		String args[] = {"TestFiles3", "OutputFiles2\\test3.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test3.ics");
		assertEquals("20150511T040000", icsf.getEvent(1).getDateTimeStart());
	}
	
	@Test
	public void partialtest7() throws IOException {
		String args[] = {"TestFiles3", "OutputFiles2\\test3.ics"};
		FreeTime.main(args);
		ICSFile icsf = new ICSFile();
		icsf.readICSFile("OutputFiles2\\test3.ics");
		assertEquals("20150511T235959", icsf.getEvent(1).getDateTimeEnd());
	}


}
