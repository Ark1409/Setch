package org.setch.plugin;

import java.util.Collection;

public interface PluginManager<T> {
	/**
	 * 
	 * @return
	 */
	public Collection<? extends T> getPlugins();
	
	/**
	 * 
	 * @param plugin
	 * @return
	 */
	public boolean addPlugin(T plugin);

	/**
	 * 
	 * @param plugin
	 * @return
	 */
	public boolean removePlugin(T plugin);

	public static PluginManager<? extends Object> getSystemPluginManager() {
		return AbstractPluginManager.systemPluginManager();
	}
}
