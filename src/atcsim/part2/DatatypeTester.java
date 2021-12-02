package atcsim.part2;

import atcsim.datatype.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DatatypeTester {

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

}
