package org.setch.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;

/**
 * Represents a plugin loader inside the {@code Setch Plugin API}.
 * <p>
 * 
 * Definitions of a Plugin mary vary depending on the implementation of the
 * Setch Plugin API. View documentation of the linked project for a proper
 * definition of {@code plugin}.
 *
 * @param <T> The superclass of all plugins that this plugin loader will load.
 */
public interface PluginLoader<T> {
	/**
	 * Loads the underlying directory of plugins.
	 * 
	 * @param dir The directory to load (not the plugin file).
	 * @param sub {@code True} if the plugin loaders should load plugins in
	 *            sub-directories. {@code False} otherwise.
	 * @return An array of loaded plugins.
	 * @throws IOException                  If an I/O error occurs.
	 * @throws ReflectiveOperationException If no class {@code extending} <i>T</i>
	 *                                      (plugin) was found in any of the loaded
	 *                                      plugins. Also if any other reflective
	 *                                      operation error occurs.
	 * @throws FileNotFoundException        If the directory does not exist.
	 * @throws NullPointerException         If the directory to load is equal to
	 *                                      {@code null}.
	 * @deprecated This method does not necessarily look for the main class
	 *             requested and/or wanted. This method will find the first class
	 *             with {@code T} as its parent. If no classes are found, this
	 *             method will throw a {@link ClassNotFoundException}.
	 */
	@Deprecated
	public Collection<? extends T> loadDirectory(final File dir, final boolean sub)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException;

	/**
	 * Loads the underlying directory of plugins.
	 * 
	 * @param dir  The directory to load (not the plugin file).
	 * @param sub  {@code True} if the plugin loaders should load plugins in
	 *             sub-directories. {@code False} otherwise.
	 * @param main The main class to search for in the array of plugins. Should not
	 *             be {@code null}.
	 * @return An array of loaded plugins.
	 * @throws IOException                  If an I/O error occurs.
	 * @throws ReflectiveOperationException If no class {@code extending} <i>T</i>
	 *                                      (plugin) was found in any of the loaded
	 *                                      plugins. Also if any other reflective
	 *                                      operation error occurs.
	 * @throws FileNotFoundException        If the directory does not exist.
	 * @throws NullPointerException         If the directory to load is equal to
	 *                                      {@code null}.
	 */
	public Collection<? extends T> loadDirectory(final File dir, final boolean sub, final String main)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException;

	/**
	 * Loads the underlying directory of plugins.
	 * 
	 * @param dir    The directory to load (not the plugin file).
	 * @param sub    {@code True} if the plugin loaders should load plugins in
	 *               sub-directories. {@code False} otherwise.
	 * @param filter The {@link FilenameFilter} to load plugins for the directory.
	 * @return An array of loaded plugins.
	 * @throws IOException                  If an I/O error occurs.
	 * @throws ReflectiveOperationException If no class {@code extending} <i>T</i>
	 *                                      (plugin) was found in any of the loaded
	 *                                      plugins. Also if any other reflective
	 *                                      operation error occurs.
	 * @throws FileNotFoundException        If the directory does not exist.
	 * @throws NullPointerException         If the directory to load is equal to
	 *                                      {@code null}.
	 * @deprecated This method does not necessarily look for the main class
	 *             requested and/or wanted. This method will find the first class
	 *             with {@code T} as its parent. If no classes are found, this
	 *             method will throw a {@link ClassNotFoundException}.
	 */
	@Deprecated
	public Collection<? extends T> loadDirectory(final File dir, final boolean sub, final FilenameFilter filter)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException;

	/**
	 * Loads the underlying directory of plugins.
	 * 
	 * @param dir    The directory to load (not the plugin file).
	 * @param sub    {@code True} if the plugin loaders should load plugins in
	 *               sub-directories. {@code False} otherwise.
	 * @param main   The main class to search for in the array of plugins. Should
	 *               not be {@code null}.
	 * @param filter The {@link FilenameFilter} to load plugins for the directory.
	 * @return An array of loaded plugins.
	 * @throws IOException                  If an I/O error occurs.
	 * @throws ReflectiveOperationException If no class {@code extending} <i>T</i>
	 *                                      (plugin) was found in any of the loaded
	 *                                      plugins. Also if any other reflective
	 *                                      operation error occurs.
	 * @throws FileNotFoundException        If the directory does not exist.
	 * @throws NullPointerException         If the directory to load is equal to
	 *                                      {@code null}.
	 */
	public Collection<? extends T> loadDirectory(final File dir, final boolean sub, final String main,
			final FilenameFilter filter)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException;

	/**
	 * Loads an array of plugin files.
	 * 
	 * @param files The plugin files to load
	 * @return An array of loaded plugins.
	 * @throws IOException                  If an I/O error occurs.
	 * @throws ReflectiveOperationException If no class {@code extending} <i>T</i>
	 *                                      (plugin) was found in any of the loaded
	 *                                      plugins. Also if any other reflective
	 *                                      operation error occurs.
	 * @throws FileNotFoundException        If one of the plugin files do not exist.
	 * @throws NullPointerException         If any of the plugin files are equal to
	 *                                      {@code null}.
	 * @deprecated This method does not necessarily look for the main class
	 *             requested and/or wanted. This method will find the first class
	 *             with {@code T} as its parent. If no classes are found, this
	 *             method will throw a {@link ClassNotFoundException}.
	 * @see #loadPlugins(File[], String)
	 */
	public Collection<? extends T> loadPlugins(final File[] files)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException;

	/**
	 * Loads an array of plugin files.
	 * 
	 * @param files The plugin files to load
	 * @param main  The main class to search for in the array of plugins. Should not
	 *              be {@code null}.
	 * @return An array of loaded plugins.
	 * @throws IOException                  If an I/O error occurs.
	 * @throws ReflectiveOperationException If no class {@code extending} <i>T</i>
	 *                                      (plugin) was found in any of the loaded
	 *                                      plugins or if the {@code main} class
	 *                                      does not exist. Also if any other
	 *                                      reflective operation error occurs.
	 * @throws FileNotFoundException        If one of the plugin files do not exist.
	 * @throws NullPointerException
	 */
	public Collection<? extends T> loadPlugins(final File[] files, final String main)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException;

	/**
	 * Loads the {@code plugin} of the underlying plugin file.
	 * 
	 * @param file The plugin file to load.
	 * @return The loaded plugin.
	 * @throws IOException                  If an I/O error occurs.
	 * @throws ReflectiveOperationException If no class {@code extending} <i>T</i>
	 *                                      (plugin) was found. Also if any other
	 *                                      reflective operation error occurs.
	 * @throws FileNotFoundException        If the file does not exist.
	 * @throws NullPointerException         If the file is equal to {@code null}.
	 * @deprecated This method does not necessarily look for the main class
	 *             requested and/or wanted. This method will find the first class
	 *             with {@code T} as its parent. If no classes are found, this
	 *             method will throw a {@link ClassNotFoundException}.
	 * @see #loadPlugin(File, String)
	 */
	@Deprecated
	public T loadPlugin(final File file)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException;

	/**
	 * Loads the {@code plugin} of the underlying plugin file.
	 * 
	 * @param file The plugin file to load.
	 * @param main The main class of the plugin. Should not be {@code null}.
	 * @return The loaded plugin.
	 * @throws IOException                  If an I/O error occurs.
	 * @throws ReflectiveOperationException If no class {@code extending} <i>T</i>
	 *                                      (plugin) was found or if the
	 *                                      {@code main} class does not exist. Also
	 *                                      if any other reflective operation error
	 *                                      occurs.
	 * @throws FileNotFoundException        If the file does not exist.
	 * @throws NullPointerException         If the file is equal to {@code null}.
	 */
	public T loadPlugin(final File file, final String main)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException;

	/**
	 * Loads the {@code plugin class} of the underlying plugin file. This method
	 * will not initialize the returned class, and so static initializers will not
	 * be ran.
	 * 
	 * @param file The plugin file to load.
	 * 
	 * @return The loaded plugin class.
	 * 
	 * @throws IOException                  If an I/O error occurs.
	 * @throws ReflectiveOperationException If no class {@code extending} <i>T</i>
	 *                                      (plugin) was found. Also if any other
	 *                                      reflective operation error occurs.
	 * @throws FileNotFoundException        If the file does not exist.
	 * @throws NullPointerException         If the file is equal to {@code null}.
	 * 
	 * @deprecated This method does not necessarily look for the main class
	 *             requested and/or wanted. This method will find the first class
	 *             with {@code T} as its parent. If no classes are found, this
	 *             method will throw a {@link ClassNotFoundException}.
	 * @see #loadClass(File, String)
	 * @see #loadClass(File, boolean, String)
	 */
	@Deprecated
	public Class<? extends T> loadClass(final File file)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException;

	/**
	 * Loads the {@code plugin class} from the underlying {@code plugin file},
	 * containing the plugin {@code main class} specified. This method will not
	 * initialize the returned class, and so static initializers will not be ran.
	 * 
	 * @param file The plugin file to load.
	 * @param main The main class of the plugin. Should not be {@code null}.
	 * 
	 * @return The loaded plugin class.
	 * 
	 * @throws IOException                  If an I/O error occurs.
	 * @throws ReflectiveOperationException If the underlying class does not
	 *                                      {@code extend} <i>T</i> or if the
	 *                                      {@code main} class does not exist.
	 * @throws FileNotFoundException        If the file does not exist. Also if any
	 *                                      other reflective operation error occurs.
	 * @throws NullPointerException         If the file is equal to {@code null} or
	 *                                      if the main class is equal to
	 *                                      {@code null}.
	 * 
	 * @see #loadClass(File, boolean, String)
	 */
	public Class<? extends T> loadClass(final File file, final String main)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException;

	/**
	 * Loads the {@code plugin class} of the underlying plugin file. This method
	 * will initialize the returned class if set to do so, and so static
	 * initializers will then be ran.
	 * 
	 * @param file       The plugin file to load.
	 * @param initialize If {@code true} the class will be initialized. See Section
	 *                   12.4 of <em>The Java Language Specification</em>.
	 * 
	 * @return The loaded plugin class.
	 * 
	 * @throws IOException                  If an I/O error occurs.
	 * @throws ReflectiveOperationException If no class {@code extending} <i>T</i>
	 *                                      (plugin) was found. Also if any other
	 *                                      reflective operation error occurs.
	 * @throws FileNotFoundException        If the file does not exist.
	 * @throws NullPointerException         If the file is equal to {@code null}.
	 * 
	 * @deprecated This method does not necessarily look for the main class
	 *             requested and/or wanted. This method will find the first class
	 *             with {@code T} as its parent. If no classes are found, this
	 *             method will throw a {@link ClassNotFoundException}.
	 * 
	 * @see #loadClass(File, String, boolean)
	 */
	@Deprecated
	public Class<? extends T> loadClass(final File file, final boolean initialize)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException;

	/**
	 * Loads the {@code plugin class} from the underlying {@code plugin file},
	 * containing the plugin {@code main class} specified. This method will
	 * initialize the returned class if set to do so, and so static initializers
	 * will then be ran.
	 * 
	 * @param file       The plugin file to load.
	 * @param main       The main class of the plugin. Should not be {@code null}.
	 * @param initialize If {@code true} the class will be initialized. See Section
	 *                   12.4 of <em>The Java Language Specification</em>.
	 * 
	 * @return The loaded plugin class.
	 * 
	 * @throws IOException                  If an I/O error occurs.
	 * @throws ReflectiveOperationException If the underlying class does not
	 *                                      {@code extend} <i>T</i> or if the
	 *                                      {@code main} class does not exist. Also
	 *                                      if any other reflective operation error
	 *                                      occurs.
	 * @throws FileNotFoundException        If the file does not exist.
	 * @throws NullPointerException         If the file is equal to {@code null} or
	 *                                      if the main class is equal to
	 *                                      {@code null}.
	 * 
	 */
	public Class<? extends T> loadClass(final File file, final String main, final boolean initialize)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException;

}
