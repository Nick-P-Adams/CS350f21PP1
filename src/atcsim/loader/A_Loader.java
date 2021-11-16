package atcsim.loader;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import atcsim.datatype.*;
import atcsim.world.navigation.A_ComponentNavaid;
import atcsim.graphics.view.navigation.OverlayNavigation;

public abstract class A_Loader 
{	
	protected Map <String, A_ComponentNavaid<?>> navaids;
	protected OverlayNavigation overlay;
	
	public A_Loader(Map <String, A_ComponentNavaid<?>> navaids, OverlayNavigation overlay)
	{
		this.navaids = navaids;
		this.overlay = overlay;
	}
	public abstract void load(Scanner scanner) throws IOException;
	protected Latitude getLatitude(int degrees, int minutes, double seconds){return new Latitude(degrees, minutes, seconds);}
	protected Longitude getLongitude(int degrees, int minutes, double seconds){return new Longitude(degrees, minutes, seconds);}
	protected Altitude getAltitude(double altitude){return new Altitude(altitude);}
	protected CoordinateWorld3D getPosition(Latitude latitude, Longitude longitude, Altitude altitude){return new CoordinateWorld3D(latitude, longitude, altitude);}
	protected VHFFrequency getVHFFrequency(int major, int minor){return new VHFFrequency(major, minor);}
	protected void addNavaid(String id, A_ComponentNavaid<?> navaid){this.navaids.put(id, navaid); this.overlay.addNavaid(navaid);}
}
