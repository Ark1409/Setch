package com.myplugin.main;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.setch.plugin.setch.SetchPlugin;

public class PluginMain extends SetchPlugin {

	@Override
	public int disable() {
		Logger.getLogger(getClass().getCanonicalName()).log(Level.INFO, "Plugin has been disabled");
		return 0;
	}

	@Override
	public int enable() {
		Logger.getLogger(getClass().getCanonicalName()).log(Level.INFO, "Plugin has been enabled!");
		return 0;
	}

	@Override
	public String getName() {
		return "My Setch Plugin";
	}

}
