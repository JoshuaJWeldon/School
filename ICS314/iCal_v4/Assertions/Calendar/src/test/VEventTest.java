package test;

import static org.junit.Assert.*;
import Cal.VEvent;

import org.junit.Test;

public class VEventTest {

	@Test
	public void defaultscheck() {
		VEvent ve = new VEvent();
		assertEquals(0, ve.getPriority());
	}
	
	@Test
	public void defaultscheck2() {
		VEvent ve = new VEvent();
		assertEquals(null, ve.getType());
	}
	
	@Test
	public void defaultscheck3() {
		VEvent ve = new VEvent();
		assertEquals(null, ve.getClassification());
	}
	
	@Test
	public void defaultscheck4() {
		VEvent ve = new VEvent();
		assertEquals(null, ve.getLocation());
	}
	
	@Test
	public void defaultscheck5() {
		VEvent ve = new VEvent();
		assertEquals(null, ve.getSummary());
	}
	
	@Test
	public void defaultscheck7() {
		VEvent ve = new VEvent();
		assertEquals(null, ve.getDateTimeStart());
	}
	
	@Test
	public void defaultscheck9() {
		VEvent ve = new VEvent();
		assertEquals(null, ve.getDateTimeEnd());
	}

	@Test
	public void checksettype() {
		VEvent ve = new VEvent();
		ve.setType("VEVENT");
		assertEquals("VEVENT", ve.getType());
	}
	
	@Test
	public void checksettype2() {
		VEvent ve = new VEvent();
		ve.setType("VEvent");
		ve.setType("valarm");
		assertEquals("valarm", ve.getType());
	}
	
	@Test 
	public void checksetclass() {
		VEvent ve = new VEvent();
		ve.setClassification("Private");
		assertEquals("Private", ve.getClassification());
	}
	
	@Test
	public void checksetclass2() {
		VEvent ve = new VEvent();
		ve.setClassification("Protected");
		ve.setClassification("private");
		assertEquals("private", ve.getClassification());
	}
	
	
	@Test 
	public void checksetlocation() {
		VEvent ve = new VEvent();
		ve.setLocation("School");
		assertEquals("School", ve.getLocation());
	}
	
	@Test
	public void checksetlocationf2() {
		VEvent ve = new VEvent();
		ve.setLocation("Math ClasS");
		ve.setLocation("LIBRARY");
		assertEquals("LIBRARY", ve.getLocation());
	}
	
	@Test 
	public void checksetpriority() {
		VEvent ve = new VEvent();
		ve.setPriority(0);
		assertEquals(0, ve.getPriority());
	}
	
	@Test
	public void checksetpriority2() {
		VEvent ve = new VEvent();
		ve.setPriority(10);
		ve.setPriority(5);
		assertEquals(5, ve.getPriority());
	}
	
	@Test 
	public void checksetsummary() {
		VEvent ve = new VEvent();
		ve.setSummary("Walking dog");
		assertEquals("Walking dog", ve.getSummary());
	}
	
	@Test
	public void checksetsummary2() {
		VEvent ve = new VEvent();
		ve.setSummary("Do homework");
		ve.setSummary("study");
		assertEquals("study", ve.getSummary());
	}
	
	@Test 
	public void checksetdatestart() {
		VEvent ve = new VEvent();
		ve.setDateStart("20150511T020000");
		assertEquals("20150511T020000", ve.getDateTimeStart());
	}
	
	@Test 
	public void checksetdatestart2() {
		VEvent ve = new VEvent();
		ve.setDateStart("20150511T020000");
		assertEquals("20150511", ve.getDateStart());
	}
	
	@Test 
	public void checksetdatestart3() {
		VEvent ve = new VEvent();
		ve.setDateStart("20150511T020000");
		assertEquals(20000, ve.TimeToIntStart());
	}
	
	@Test 
	public void checksetdatestart4() {
		VEvent ve = new VEvent();
		ve.setDateStart("20150511T020000");
		ve.setDateStart("20150511T080000");
		assertEquals("20150511T080000", ve.getDateTimeStart());
	}
	
	@Test 
	public void checksetdatestart5() {
		VEvent ve = new VEvent();
		ve.setDateStart("20150511T020000");
		ve.setDateStart("20150511T080000");
		assertEquals("20150511", ve.getDateStart());
	}
	
	@Test 
	public void checksetdatestart6() {
		VEvent ve = new VEvent();
		ve.setDateStart("20150511T020000");
		ve.setDateStart("20150511T180000");
		assertEquals(180000, ve.TimeToIntStart());
	}
	
	@Test 
	public void checksetdateend() {
		VEvent ve = new VEvent();
		ve.setDateEnd("20150511T020000");
		assertEquals("20150511T020000", ve.getDateTimeEnd());
	}
	
	@Test 
	public void checksetdateend2() {
		VEvent ve = new VEvent();
		ve.setDateEnd("20150511T020000");
		assertEquals("20150511", ve.getDateEnd());
	}
	
	@Test 
	public void checksetdateend3() {
		VEvent ve = new VEvent();
		ve.setDateEnd("20150511T020000");
		assertEquals(20000, ve.TimeToIntEnd());
	}
	
	@Test 
	public void checksetdateend4() {
		VEvent ve = new VEvent();
		ve.setDateEnd("20150511T020000");
		ve.setDateEnd("20150511T080000");
		assertEquals("20150511T080000", ve.getDateTimeEnd());
	}
	
	@Test 
	public void checksetdateend5() {
		VEvent ve = new VEvent();
		ve.setDateEnd("20150511T020000");
		ve.setDateEnd("20150511T080000");
		assertEquals("20150511", ve.getDateEnd());
	}
	
	@Test 
	public void checksetdateend6() {
		VEvent ve = new VEvent();
		ve.setDateEnd("20150511T020000");
		ve.setDateEnd("20150511T180000");
		assertEquals(180000, ve.TimeToIntEnd());
	}
	
	@Test
	public void comparison() {
		VEvent ve = new VEvent();
		VEvent ve2 = new VEvent();
		ve.setDateStart("20150511T020000");
		ve2.setDateStart("20150511T020000");
		assertEquals(0, ve.compareTo(ve2));
	}
	
	@Test
	public void comparison2() {
		VEvent ve = new VEvent();
		VEvent ve2 = new VEvent();
		ve.setDateStart("20150511T020000");
		ve2.setDateStart("20150511T180000");
		int temp = ve.compareTo(ve2);
		if(temp > 0) {
			temp = 1;
		}
		else if(temp < 0) {
			temp = -1;
		}
		assertEquals(-1, temp);
	}
	
	@Test
	public void comparison3() {
		VEvent ve = new VEvent();
		VEvent ve2 = new VEvent();
		ve.setDateStart("20150511T111111");
		ve2.setDateStart("20150511T000000");
		int temp = ve.compareTo(ve2);
		if(temp > 0) {
			temp = 1;
		}
		else if(temp < 0) {
			temp = -1;
		}
		assertEquals(1, temp);
	}
	
	@Test
	public void comparison4() {
		VEvent ve = new VEvent();
		VEvent ve2 = new VEvent();
		ve.setDateStart("20150511T111111");
		ve2.setDateStart("20150511T000000");
		ve2.setDateStart("20150511T111111");
		int temp = ve.compareTo(ve2);
		if(temp > 0) {
			temp = 1;
		}
		else if(temp < 0) {
			temp = -1;
		}
		assertEquals(0, temp);
	}
	
	@Test
	public void comparison5() {
		VEvent ve = new VEvent();
		VEvent ve2 = new VEvent();
		ve.setDateStart("20150511T121212");
		ve2.setDateStart("20150511T200000");
		ve.setDateStart("20150511T111111");
		int temp = ve.compareTo(ve2);
		if(temp > 0) {
			temp = 1;
		}
		else if(temp < 0) {
			temp = -1;
		}
		assertEquals(-1, temp);
	}
	
	@Test
	public void comparison6() {
		VEvent ve = new VEvent();
		VEvent ve2 = new VEvent();
		ve.setDateStart("20150511T085622");
		ve2.setDateStart("20150511T198872");
		ve2.setDateEnd("20150511T000000");
		ve.setPriority(10);
		ve2.setClassification("Private");
		ve.setType("VEvent");
		int temp = ve.compareTo(ve2);
		if(temp > 0) {
			temp = 1;
		}
		else if(temp < 0) {
			temp = -1;
		}
		assertEquals(-1, temp);
	}
	
	@Test
	public void comparison7() {
		VEvent ve = new VEvent();
		VEvent ve2 = new VEvent();
		ve.setDateStart("20150511T020000Z");
		ve2.setDateStart("20150511T180000Z");
		int temp = ve.compareTo(ve2);
		if(temp > 0) {
			temp = 1;
		}
		else if(temp < 0) {
			temp = -1;
		}
		assertEquals(-1, temp);
	}
}
