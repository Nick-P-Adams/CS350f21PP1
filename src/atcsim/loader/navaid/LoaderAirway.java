package atcsim.loader.navaid;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import atcsim.datatype.*;
import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.A_Loader;
import atcsim.world.navigation.A_ComponentNavaid;
import atcsim.world.navigation.ComponentNavaidAirway;

public class LoaderAirway extends A_Loader 
{
	public LoaderAirway(Map <String, A_ComponentNavaid<?>> navaids, OverlayNavigation overlay)
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
			ComponentNavaidAirway airway = null;
			
			if(specParts[1].trim().equals("CC"))
			{
				Latitude latitude = new Latitude(Integer.parseInt(specParts[2].trim()), Integer.parseInt(specParts[3].trim()), Double.parseDouble(specParts[4].trim()));
				Longitude longitude = new Longitude(Integer.parseInt(specParts[5].trim()), Integer.parseInt(specParts[6].trim()), Double.parseDouble(specParts[7].trim()));
				Altitude altitude = new Altitude(Double.parseDouble(specParts[8].trim()));
				CoordinateWorld3D position1 = new CoordinateWorld3D(latitude, longitude, altitude);
				
				latitude = new Latitude(Integer.parseInt(specParts[9].trim()), Integer.parseInt(specParts[10].trim()), Double.parseDouble(specParts[11].trim()));
				longitude = new Longitude(Integer.parseInt(specParts[12].trim()), Integer.parseInt(specParts[13].trim()), Double.parseDouble(specParts[14].trim()));
				altitude = new Altitude(Double.parseDouble(specParts[15].trim()));
				CoordinateWorld3D position2 = new CoordinateWorld3D(latitude, longitude, altitude);
				
				 airway = new ComponentNavaidAirway(specParts[0], position1, position2);
			}
			else if(specParts[1].trim().equals("NC"))
			{
				Latitude latitude = new Latitude(Integer.parseInt(specParts[3].trim()), Integer.parseInt(specParts[4].trim()), Double.parseDouble(specParts[5].trim()));
				Longitude longitude = new Longitude(Integer.parseInt(specParts[6].trim()), Integer.parseInt(specParts[7].trim()), Double.parseDouble(specParts[8].trim()));
				Altitude altitude = new Altitude(Double.parseDouble(specParts[9].trim()));
				CoordinateWorld3D position = new CoordinateWorld3D(latitude, longitude, altitude);
				
				airway = new ComponentNavaidAirway(specParts[0], this.navaids.get(specParts[2].trim()), position);
			}
			else if(specParts[1].trim().equals("NN"))
			{
				airway = new ComponentNavaidAirway(specParts[0], this.navaids.get(specParts[2].trim()), this.navaids.get(specParts[3].trim()));
			}
			
			this.navaids.put(specParts[0].trim(), airway);
			this.overlay.addNavaid(airway);
			
			nextLine = scanner.nextLine();
		}
	}
}
