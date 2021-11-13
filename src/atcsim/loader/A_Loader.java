package atcsim.loader;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import atcsim.world.navigation.A_ComponentNavaid;
import atcsim.graphics.view.navigation.OverlayNavigation;

public abstract class A_Loader 
{	
	public A_Loader(Map <String, A_ComponentNavaid<?>> navaids, OverlayNavigation overlay){}
	public abstract void load(Scanner scanner) throws IOException;
}
