package adapter;

import scale.IEditThread;
import server.IAutoServer;

//The class combines interface and abstract class that implements interface
public class BuildAuto extends ProxyAutomobile implements ICreateAuto, IUpdateAuto, IFixAuto, IEditThread, IAutoServer {
	// Empty to hide methods implements in ProxyAutomobile
}
