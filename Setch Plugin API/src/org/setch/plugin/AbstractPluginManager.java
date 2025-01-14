package org.setch.plugin;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractPluginManager<T> implements PluginManager<T> {
	protected List<T> plugins;

	private static final DefaultPluginManager sys;

	static {
		sys = new DefaultPluginManager();
	}

	protected AbstractPluginManager() {
		this.plugins = new LinkedList<T>();
	}

	protected AbstractPluginManager(List<? extends T> plugins) {
		this.plugins = new LinkedList<T>();
		this.plugins.addAll(plugins);
	}

	@Override
	public Collection<? extends T> getPlugins() {
		return plugins;
	}

	@Override
	public boolean addPlugin(T plugin) {
		return this.plugins.add(plugin);
	}

	@Override
	public boolean removePlugin(T plugin) {
		return this.plugins.remove(plugin);
	}

	/* Retrieves the default plugin manager */
	static DefaultPluginManager systemPluginManager() {
		return sys;
	}

	/* Dummy class for system plugin manager */
	static class DefaultPluginManager extends AbstractPluginManager<Object> {
	}
}
