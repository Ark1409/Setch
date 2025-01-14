package org.setch.plugin.setch;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import org.setch.plugin.AbstractPluginManager;

public class SetchPluginManager extends AbstractPluginManager<SetchPlugin> {
	/**
	 * Retrieves the plugin with the underlying name.
	 * 
	 * @param name The name of the plugin to find.
	 * @return The plugin with the matching name, {@code null} if no plugin was
	 *         found.
	 */
	public SetchPlugin getPlugin(String name) {
		Collection<? extends SetchPlugin> plugins = this.getPlugins();

		Iterator<? extends SetchPlugin> iter = plugins.iterator();

		while (iter.hasNext()) {
			SetchPlugin p = iter.next();

			if (p.getName().equals(name))
				return p;
		}
		
		return null;
	}

	/**
	 * Retrieves the plugin with the underlying {@link UUID uuid}.
	 * 
	 * @param uuid The {@link UUID} of the plugin to find.
	 * @return The plugin with the matching {@link UUID uuid}, {@code null} if no
	 *         plugin was found.
	 */
	public SetchPlugin getPlugin(UUID uuid) {
		Collection<? extends SetchPlugin> plugins = this.getPlugins();

		Iterator<? extends SetchPlugin> iter = plugins.iterator();

		while (iter.hasNext()) {
			SetchPlugin p = iter.next();

			if (p.getUniqueId().equals(uuid))
				return p;
		}
		
		return null;
	}
}
