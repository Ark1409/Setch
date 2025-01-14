package org.setch.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Abstractly implemented version of the {@link PluginLoader} interface.
 * 
 * @param <T> The superclass of all plugins that this plugin loader will load.
 */
public abstract class AbstractPluginLoader<T> implements PluginLoader<T> {
	protected Map<String, Class<? extends T>> store = new LinkedHashMap<String, Class<? extends T>>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public Collection<? extends T> loadDirectory(File dir, boolean sub)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException {
		return this.loadDirectory(dir, sub, new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return true;
			}

		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<? extends T> loadDirectory(File dir, boolean sub, String main)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException {
		return this.loadDirectory(dir, sub, main, new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return true;
			}

		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public Collection<? extends T> loadDirectory(File dir, boolean sub, FilenameFilter filter)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException {
		if (dir == null)
			throw new NullPointerException("Cannot load null plugin directory");
		if (filter == null)
			throw new NullPointerException("FilenameFilter cannot be null for directory " + dir.getPath());

		if (!sub)
			return loadPlugins(dir.listFiles(filter));

		List<File> files = new LinkedList<File>();

		files.addAll(this.findFiles(dir, filter));

		return loadPlugins(files.toArray(new File[0]));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<? extends T> loadDirectory(File dir, boolean sub, String main, FilenameFilter filter)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException {
		if (dir == null)
			throw new NullPointerException("Cannot load null plugin directory");
		if (filter == null)
			throw new NullPointerException("FilenameFilter cannot be null for directory " + dir.getPath());

		if (!sub)
			return loadPlugins(dir.listFiles(filter), main);

		List<File> files = new LinkedList<File>();

		files.addAll(this.findFiles(dir, filter));

		return loadPlugins(files.toArray(new File[0]), main);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public Collection<? extends T> loadPlugins(File[] files)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException {
		List<T> list = new LinkedList<T>();

		for (int i = 0; i < files.length; i++) {
			list.add(i, loadPlugin(files[i]));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<? extends T> loadPlugins(File[] files, String main)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException {
		List<T> list = new LinkedList<T>();

		for (int i = 0; i < files.length; i++) {
			list.add(i, loadPlugin(files[i], main));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public T loadPlugin(File file)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException {
		Class<? extends T> clazz = loadClass(file, true);
		return initClass(clazz);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T loadPlugin(File file, String main)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException {
		Class<? extends T> clazz = this.loadClass(file, main, true);
		return initClass(clazz);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public Class<? extends T> loadClass(File file)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException {
		return this.loadClass(file, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<? extends T> loadClass(File file, String main)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException {
		return this.loadClass(file, main, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public Class<? extends T> loadClass(File file, boolean initialize)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException {
		if (file == null)
			throw new NullPointerException("Cannot load null plugin directory");
		if (!file.exists())
			throw new FileNotFoundException("File " + file.getAbsolutePath() + " does not exist");
		return loadClass0(file, initialize);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<? extends T> loadClass(File file, String main, boolean initialize)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException {
		if (file == null)
			throw new NullPointerException("Cannot load null plugin directory");
		if (!file.exists())
			throw new FileNotFoundException("File " + file.getAbsolutePath() + " does not exist");

		return loadClass0(file, main, initialize);
	}

	/* To be called after checks in loadClass(String,boolean); */
	private Class<? extends T> loadClass0(File file, boolean initialize)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException {
		if (store.containsKey(file.getAbsolutePath())) {
			return store.get(file.getAbsolutePath());
		}

		Class<? extends T> clazz = findClass(file, initialize);

		store.put(file.getAbsolutePath(), clazz);

		return clazz;
	}

	/* To be called after checks in loadClass(String,String,boolean); */
	private Class<? extends T> loadClass0(File file, String main, boolean initialize)
			throws IOException, ReflectiveOperationException, FileNotFoundException, NullPointerException {
		if (store.containsKey(file.getAbsolutePath())) {
			return store.get(file.getAbsolutePath());
		}

		Class<? extends T> clazz = findClass(file, main, initialize);

		//classStore0(store, file.getAbsolutePath(), clazz);

		store.put(file.getAbsolutePath(), clazz);

		return clazz;
	}

	/* Find files + files from sub directories */
	private List<File> findFiles(File dir, FilenameFilter filter) throws FileNotFoundException {
		List<File> files = new LinkedList<File>();

		if (!dir.exists()) {
			throw new FileNotFoundException("File " + dir.getAbsolutePath() + " does not exist!");
		}

		File[] dirFiles = dir.listFiles(filter);

		for (int i = 0; i < dirFiles.length; i++) {
			File f = dirFiles[i];

			if (!f.exists()) {
				throw new FileNotFoundException("File " + f.getAbsolutePath() + " does not exist!");
			}

			if (f.isDirectory()) {
				files.addAll(findFiles(f, filter));
			} else if (f.isFile()) {
				files.add(f);
			}
		}

		return files;
	}

	/**
	 * Finds the plugin class inside the underlying file.
	 * 
	 * @param file       The plugin file to load.
	 * @param main       The main class of the plugin.
	 * @param initialize If {@code true} the class will be initialized. See Section
	 *                   12.4 of <em>The Java Language Specification</em>.
	 * @return The loaded plugin class, {@code null} if no class was found.
	 * @throws IOException                  If an I/O error occurs.
	 * @throws ReflectiveOperationException If a Reflective Operation error occurs.
	 */
	protected abstract Class<? extends T> findClass(File file, String main, boolean initialize)
			throws IOException, ReflectiveOperationException;

	/**
	 * Finds the plugin class inside the underlying file.
	 * 
	 * @param file       The plugin file to load.
	 * @param initialize If {@code true} the class will be initialized. See Section
	 *                   12.4 of <em>The Java Language Specification</em>.
	 * @return The loaded plugin class, {@code null} if no class was found.
	 * @throws IOException
	 * @throws ReflectiveOperationException
	 */
	protected abstract Class<? extends T> findClass(File file, boolean initialize)
			throws IOException, ReflectiveOperationException;

	/**
	 * Initializes the underlying plugin class.
	 * 
	 * @param clazz The plugin class to initialize.
	 * @return The initialized class.
	 * @throws ReflectiveOperationException If a Reflective Operation error occurs.
	 */
	protected abstract T initClass(Class<? extends T> clazz) throws ReflectiveOperationException;

}
