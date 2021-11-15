package atcsim.loader.navaid;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import atcsim.datatype.*;
import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.A_Loader;
import atcsim.world.navigation.A_ComponentNavaid;
import atcsim.world.navigation.ComponentNavaidVOR;

public class LoaderVOR extends A_Loader 
{
	public LoaderVOR(Map<String, A_ComponentNavaid<?>> navaids, OverlayNavigation overlay) 
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
			
			Latitude latitude = new Latitude(Integer.parseInt(specParts[3].trim()), Integer.parseInt(specParts[4].trim()), Double.parseDouble(specParts[5].trim()));
			Longitude longitude = new Longitude(Integer.parseInt(specParts[6].trim()), Integer.parseInt(specParts[7].trim()), Double.parseDouble(specParts[8].trim()));
			Altitude altitude = new Altitude(Double.parseDouble(specParts[9].trim()));
			VHFFrequency frequency = new VHFFrequency(Integer.parseInt(specParts[1].trim()), Integer.parseInt(specParts[2].trim()));
			
			CoordinateWorld3D position = new CoordinateWorld3D(latitude, longitude, altitude);
			ComponentNavaidVOR vor = new ComponentNavaidVOR(specParts[0].trim(), position, frequency);
			
			this.navaids.put(specParts[0].trim(), vor);
			this.overlay.addNavaid(vor);
			
			nextLine = scanner.nextLine();
		}
	}
}
