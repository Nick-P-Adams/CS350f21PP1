package atcsim.loader.navaid;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import atcsim.datatype.*;
import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.A_Loader;
import atcsim.world.navigation.A_ComponentNavaid;
import atcsim.world.navigation.ComponentNavaidFix;

public class LoaderFix extends A_Loader 
{
	public LoaderFix(Map <String, A_ComponentNavaid<?>> navaids, OverlayNavigation overlay)
	{
		super(navaids, overlay);
	}

	@Override
	public void load(Scanner scanner) throws IOException 
	{
		String nextLine = scanner.nextLine();
		
		while(!(nextLine.trim().equals("")))
		{
			String[] specParts = nextLine.split(",");
			
			Latitude latitude = new Latitude(Integer.parseInt(specParts[1].trim()), Integer.parseInt(specParts[2].trim()), Double.parseDouble(specParts[3].trim()));
			Longitude longitude = new Longitude(Integer.parseInt(specParts[4].trim()), Integer.parseInt(specParts[5].trim()), Double.parseDouble(specParts[6].trim()));
			Altitude altitude = new Altitude(Double.parseDouble(specParts[7].trim()));
			
			CoordinateWorld3D position = new CoordinateWorld3D(latitude, longitude, altitude);
			ComponentNavaidFix fix = new ComponentNavaidFix(specParts[0].trim(), position);
			
			this.navaids.put(specParts[0].trim(), fix);
			this.overlay.addNavaid(fix);
			
			nextLine = scanner.nextLine();
		}
	}
}
