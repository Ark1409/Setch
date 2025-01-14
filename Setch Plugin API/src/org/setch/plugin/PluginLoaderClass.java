package org.setch.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipFile;

public abstract class PluginLoaderClass<T> {
//	protected final String pluginDescPath;
//	protected final String pluginLibPath;
//	protected PluginDescriptionType descType;
//
//	protected PluginLoaderClass(String pluginDescPath, String pluginLibPath, PluginDescriptionType descType) {
//		this.pluginDescPath = pluginLibPath;
//		this.pluginLibPath = pluginLibPath;
//		this.descType = descType;
//	}
//
//	/**
//	 * @return the descType
//	 */
//	public PluginDescriptionType getDescType() {
//		return descType;
//	}
//
//	/**
//	 * @param descType the descType to set
//	 */
//	public void setDescType(PluginDescriptionType descType) {
//		this.descType = descType;
//	}
//
//	/**
//	 * Retrieves the {@link PluginLoaderClass.PluginInitType PluginInitType} for the
//	 * Plugin Loader.
//	 * 
//	 * @return The Plugin Loader initialization type.
//	 * @see PluginLoaderClass.PluginInitType
//	 */
//	public abstract PluginInitType getInitType();
//
//	/**
//	 * @return the pluginDescPath
//	 */
//	public String getPluginDescPath() {
//		return pluginDescPath;
//	}
//
//	/**
//	 * @return the pluginLibPath
//	 */
//	public String getPluginLibPath() {
//		return pluginLibPath;
//	}
//
//	public Class<? extends T> loadPluginClass(final File pluginFile) throws IOException {
//		Class<? extends T> clazz = null;
//		final ZipFile zip = new ZipFile(pluginFile);
//		
//		InputStream in = zip.getInputStream(zip.getEntry(pluginDescPath));
//	//	Class.forName(name, initialize, loader)
//		zip.close();
//
//		return clazz;
//	}
//
//	public T loadPlugin(final File pluginFile) throws IOException {
//		Class<? extends T> clazz = this.loadPluginClass(pluginFile);
//
//		return initPluginClass(clazz);
//	}
//
//	protected T initPluginClass(Class<? extends T> clazz) {
//		if (this.getInitType().equals(PluginInitType.CONSTRUCTOR)) {
//
//		} else if (this.getInitType().equals(PluginInitType.ANNOTATION)) {
//
//		} else if (this.getInitType().equals(PluginInitType.METHOD)) {
//
//		}
//		return null;
//	}
//
//	
//
//	/**
//	 * Enumeration containing constants for all possible Plugin Init Types. A
//	 * {@code Plugin Init Type} is a type identifyng the way a {@link PluginLoaderClass}
//	 * should initialize a plugin class.
//	 */
//	public static enum PluginInitType {
//		METHOD, ANNOTATION, CONSTRUCTOR;
//	}
}
