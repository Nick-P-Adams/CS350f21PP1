package atcsim.loader;

import java.io.IOException;

import atcsim.loader.navaid.*; 

public class Tester {

	public static void main(String[] args) throws IOException 
	{
		NavigationOverlayBuilder nob = new NavigationOverlayBuilder();
		nob.loadDefinition​("definition1.txt");
	}

}
