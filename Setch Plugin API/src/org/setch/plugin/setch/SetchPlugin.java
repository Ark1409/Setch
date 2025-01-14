package org.setch.plugin.setch;

import java.util.UUID;

import org.setch.plugin.PluginLoader;

public abstract class SetchPlugin implements Plugin {
	protected PluginLoader<? extends SetchPlugin> pluginLoader;
	protected boolean init = false;
	protected Thread thread;

	/** Empty constructor. Must exist in sub-classes. */
	protected SetchPlugin() {
	}

	/**
	 * Initializes the current {@code Setch Plugin}. This method should not be
	 * called if not by the plugin loader / initializer / creator.
	 * 
	 * @param pluginLoader The {@link PluginLoader} used to load this plugin.
	 */
	public final void initPlugin(PluginLoader<? extends SetchPlugin> pluginLoader, Thread thread) {
		if (init)
			return;
		this.pluginLoader = pluginLoader;
		this.thread = thread;
		init = true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final UUID getUniqueId() {
		return UUID.nameUUIDFromBytes(Integer.valueOf(System.identityHashCode(this)).toString().getBytes());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final PluginLoader<? extends SetchPlugin> getPluginLoader() {
		return pluginLoader;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Thread getThread() {
		return this.thread;
	}

}
