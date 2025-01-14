package org.setch.plugin.setch;

import java.util.UUID;

import org.setch.plugin.PluginLoader;

public interface Plugin {
	/**
	 * Enables the plugin.
	 * 
	 * @return The status code for the enabled plugin (0 = successfully enabled).
	 */
	public int enable();

	/**
	 * Disabled the plugin.
	 * 
	 * @return The status code for the disabled plugin (0 = successfully disabled)
	 */
	public int disable();

	/**
	 * Retrieves the name for the plugin. This should be unique.
	 * 
	 * @return The plugin's name.
	 */
	public String getName();

	/**
	 * Retrieves the current {@link UUID} for the plugin.
	 * 
	 * @return The plugin's current {@link UUID}.
	 */
	public UUID getUniqueId();

	/**
	 * Retrieves the {@link PluginLoader} for the current plugin.
	 * 
	 * @return The plugin's {@link PluginLoader}.
	 */
	public PluginLoader<? extends Plugin> getPluginLoader();

	/**
	 * Retrieves the {@link Thread} for the current plugin.
	 * 
	 * @return The plugin's thread.
	 */
	public Thread getThread();
}
