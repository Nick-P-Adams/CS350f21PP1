package atcsim.loader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.navaid.*;
import atcsim.world.navigation.*;

public class NavigationOverlayBuilder 
{
	private Map <String, A_ComponentNavaid<?>> navaids;
	private OverlayNavigation overlay;
	private A_Loader navaidLoader;
	
	public NavigationOverlayBuilder()
	{
		this.navaids = new HashMap<String, A_ComponentNavaid<?>>();
		this.overlay = new OverlayNavigation("NavaidOverlay");
	}
	
	public OverlayNavigation loadDefinition​(String filespec) throws IOException
	{
		File navaidSpecs = new File(filespec);
		Scanner fileScanner = new Scanner(navaidSpecs);
		
		while(fileScanner.hasNext())
		{
			String nextLine = fileScanner.nextLine().trim();
			
			if(nextLine.equals("[NAVAID:FIX]"))
			{
				this.navaidLoader = new LoaderFix(this.navaids, this.overlay);
				this.navaidLoader.load(fileScanner);		
			}
			else if(nextLine.equals("[NAVAID:NDB]"))
			{
				this.navaidLoader = new LoaderNDB(this.navaids, this.overlay);
				this.navaidLoader.load(fileScanner);
			}
			else if(nextLine.equals("[NAVAID:VOR]"))
			{
				this.navaidLoader = new LoaderVOR(this.navaids, this.overlay);
				this.navaidLoader.load(fileScanner);
			}
			else if(nextLine.equals("[NAVAID:ILS]"))
			{
				this.navaidLoader = new LoaderILS(this.navaids, this.overlay);
				this.navaidLoader.load(fileScanner);
			}
			else if(nextLine.equals("[NAVAID:AIRWAY]"))
			{
				this.navaidLoader = new LoaderAirway(this.navaids, this.overlay);
				this.navaidLoader.load(fileScanner);
			}
		}
		
		return this.overlay;
	}

}