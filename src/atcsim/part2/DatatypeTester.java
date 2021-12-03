package atcsim.part2;

import atcsim.datatype.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class DatatypeTester 
{
	@Test
	public void testAltitude() 
	{
		Altitude a1 = new Altitude(1000),
				 a2 = new Altitude(200);
		
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
	public void testAngleNavigational()
	{
		AngleNavigational a1 = new AngleNavigational(90),
						  a2 = new AngleNavigational(180);
		
		assertEquals(270, a1.reciprocate().getValue_());
		assertEquals(0, a2.reciprocate().getValue_());
		assertEquals(135, a1.interpolate(a2, new Scaler(0.5)).getValue_());
		assertEquals(315, a2.interpolate(a1, new Scaler(0.5)).getValue_());
	}
	
	@Test
	public void testAttitudePitch()
	{
		AttitudePitch p = new AttitudePitch(10),
					  attitudePitchTest = new AttitudePitch(0);
		
		assertEquals(10, attitudePitchTest.add_(p).getValue_());
		
		attitudePitchTest = new AttitudePitch(90);
		assertEquals(100, attitudePitchTest.add_(p).getValue_());
		
		attitudePitchTest = new AttitudePitch(175);
		assertEquals(-175, attitudePitchTest.add_(p).getValue_());
	}
	
	@Test
	public void testAttitudeYaw() 
	{
		AttitudeYaw y = new AttitudeYaw(10),
					attitudeYawTest = new AttitudeYaw(0);
		
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
	  Course c = new Course(10),
			 courseTest = new Course(0);
	  
	  //This part is testing the add method 
	  assertEquals(10, courseTest.add_(c).getValue_());
			
	  courseTest = new Course(355);
	  assertEquals(5, courseTest.add_(c).getValue_());
			
	  //This part is testing the subtract method
	  courseTest = new Course(0);
	  assertEquals(350, courseTest.subtract_(c).getValue_());
			
	  courseTest = new Course(355);
	  assertEquals(345, courseTest.subtract_(c).getValue_());
	  //Course is the direction the aircraft is moving in, while yaw is the direction the aircraft is facing on the horizontal plane.
	} 

	@Test
	public void testCoordinateWorld()
	{
		CoordinateWorld p1 = CoordinateWorld.KSFF,
						p2 = new CoordinateWorld(new Latitude(1, 2, 3), new Longitude(3, 2, 1));
		
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
	
	@Test
	public void testCoordinateWorld3D()
	{
		//Test Start Coordinate.
		CoordinateWorld p = CoordinateWorld.KSFF;

		//Self Test Block
		CoordinatePolarNavigational calculateBearingTest = p.calculateBearing(p);

		assertEquals(90, calculateBearingTest.getAngle().getValue_());
		assertEquals(0, calculateBearingTest.getRadiusNauticalMiles().getValue_());

		//KSFF_N Test Block
		calculateBearingTest = p.calculateBearing(CoordinateWorld.KSFF_N);
		assertEquals(0, calculateBearingTest.getAngle().getValue_());
		
		Radius r = new Radius(CoordinateWorld.KSFF_N.getLatitude().subtract_(p.getLatitude()).getValue_());
		CoordinatePolarNavigational expected = new CoordinatePolarNavigational(new AngleNavigational(0),r);
		assertEquals(expected.getRadiusNauticalMiles().getValue_(), calculateBearingTest.getRadiusNauticalMiles().getValue_());
		
		//KSFF_E Test Block
		calculateBearingTest = p.calculateBearing(CoordinateWorld.KSFF_E);
		assertEquals(90, calculateBearingTest.getAngle().getValue_());
		
		r = new Radius(p.getLongitude().subtract_(CoordinateWorld.KSFF_E.getLongitude()).getValue_());
		expected = new CoordinatePolarNavigational(new AngleNavigational(90),r);
		assertEquals(expected.getRadiusNauticalMiles().getValue_(), calculateBearingTest.getRadiusNauticalMiles().getValue_());

		//KSFF_S Test Block
		calculateBearingTest = p.calculateBearing(CoordinateWorld.KSFF_S);
		assertEquals(180, calculateBearingTest.getAngle().getValue_());
		
		r = new Radius(p.getLatitude().subtract_(CoordinateWorld.KSFF_S.getLatitude()).getValue_());
		expected = new CoordinatePolarNavigational(new AngleNavigational(180),r);
		assertEquals(expected.getRadiusNauticalMiles().getValue_(), calculateBearingTest.getRadiusNauticalMiles().getValue_());

		//KSFF_W Test Block
		calculateBearingTest = p.calculateBearing(CoordinateWorld.KSFF_W);
		assertEquals(270, calculateBearingTest.getAngle().getValue_());
		
		r = new Radius(CoordinateWorld.KSFF_W.getLongitude().subtract_(p.getLongitude()).getValue_());
		expected = new CoordinatePolarNavigational(new AngleNavigational(270),r);
		assertEquals(expected.getRadiusNauticalMiles().getValue_(), calculateBearingTest.getRadiusNauticalMiles().getValue_());
	}
}
