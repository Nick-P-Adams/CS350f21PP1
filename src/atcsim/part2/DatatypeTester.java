package atcsim.part2;

import atcsim.datatype.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class DatatypeTester 
{
	@Test
	public void testAltitude() 
	{
		Altitude a1 = new Altitude(1000);
		Altitude a2 = new Altitude(200);
		
		double altitudeTest = a1.getValue_() + a2.getValue_();
		assertEquals(1200, altitudeTest);
		
		altitudeTest = a2.getValue_() + a1.getValue_();
		assertEquals(1200, altitudeTest);
		
		altitudeTest = a1.getValue_() - a2.getValue_();
		assertEquals(800, altitudeTest);
		
		altitudeTest = a2.getValue_() - a1.getValue_();
		assertEquals(-800, altitudeTest);
		
		int altCompareTest = a1.compareTo(a1);
		assertEquals(0, altCompareTest);
		
		altCompareTest = a2.compareTo(a1);
		assertEquals(-1, altCompareTest);
		
		altCompareTest = a1.compareTo(a2);
		assertEquals(1, altCompareTest);
	}
	
	@Test
	public void testAttitudeYaw() 
	{
		AttitudeYaw y = new AttitudeYaw(10);
		AttitudeYaw attitudeYawTest = new AttitudeYaw(0);
		
		//This part is testing the add method 
		assertEquals(10, attitudeYawTest.add_(y).getValue_());
		
		attitudeYawTest = new AttitudeYaw(355);
		assertEquals(5, attitudeYawTest.add_(y).getValue_());
		
		//This part is testing the subtract method
		attitudeYawTest = new AttitudeYaw(0);
		assertEquals(-10, attitudeYawTest.subtract_(y).getValue_());
		
		attitudeYawTest = new AttitudeYaw(355);
		assertEquals(-15, attitudeYawTest.subtract_(y).getValue_());
	}
	
	@Test
	public void testCourse()
	{
	  Course c = new Course(10);
	  Course courseTest = new Course(0);
	  
	  //This part is testing the add method 
	  assertEquals(10, courseTest.add_(c).getValue_());
			
	  courseTest = new Course(355);
	  assertEquals(5, courseTest.add_(c).getValue_());
			
	  //This part is testing the subtract method
	  courseTest = new Course(0);
	  assertEquals(350, courseTest.subtract_(c).getValue_());
			
	  courseTest = new Course(355);
	  assertEquals(345, courseTest.subtract_(c).getValue_());
	  //I believe the difference is that course means the intended angle for the airplane to match to meet it's course.  
	  //Where as AttitudeYaw is the current yaw angle of the aircraft (relative to the horizon?).
	} 

	@Test
	public void testCoordinateWorld()
	{
		CoordinateWorld p1 = CoordinateWorld.KSFF;
		CoordinateWorld p2 = new CoordinateWorld(new Latitude(1, 2, 3), new Longitude(3, 2, 1));
		
		//p1 equality test
		int positionCompareTest = p1.compareTo(p1);
		assertEquals(0, positionCompareTest);
		
		//p1 + p2 test. There is a very minuscule amount of precision loss from .add_() so I add a delta of 0.0000000001
		CoordinateWorld p3 = p1.add_(p2);
		double[] positionArrayActual = {p3.getLatitude().getDegrees(), p3.getLatitude().getMinutes(), p3.getLatitude().getSeconds(),
										p3.getLongitude().getDegrees(), p3.getLongitude().getMinutes(), p3.getLongitude().getSeconds()};
		double[] positionArrayExpected = {50, 41, 35, 120, 27, 31};
		assertArrayEquals(positionArrayExpected, positionArrayActual, 0.0000000001);
		
		//Since the .add_() method results in a tiny amount of precision loss I am going to add the two positions 
		//in another way and test that as well. 
		double latDegrees = p1.getLatitude().getDegrees() + p2.getLatitude().getDegrees(),
		       latMinutes = p1.getLatitude().getMinutes() + p2.getLatitude().getMinutes(),
		       latSeconds = p1.getLatitude().getSeconds() + p2.getLatitude().getSeconds();
		double longDegrees = p1.getLongitude().getDegrees() + p2.getLongitude().getDegrees(),
			   longMinutes = p1.getLongitude().getMinutes() + p2.getLongitude().getMinutes(),
			   longSeconds = p1.getLongitude().getSeconds() + p2.getLongitude().getSeconds();
		double[] positionArrayActual2 = {latDegrees, latMinutes, latSeconds, longDegrees, longMinutes, longSeconds};
		assertArrayEquals(positionArrayExpected, positionArrayActual2);
	}
}
