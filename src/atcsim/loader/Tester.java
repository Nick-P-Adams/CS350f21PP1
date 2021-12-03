package atcsim.loader;

import java.io.IOException;

import atcsim.datatype.*;

public class Tester 
{
	public static void main(String[] args) throws IOException 
	{
		NavigationOverlayBuilder nob = new NavigationOverlayBuilder();
		nob.loadDefinition​("definition1.txt");
		
		System.out.println(CoordinateWorld.KSFF_N.getLatitude());
	}
}
