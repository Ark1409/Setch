package org.setch.plugin.setch;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.setch.plugin.AbstractPluginLoader;

public class SetchPluginLoader extends AbstractPluginLoader<SetchPlugin> {

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Class<? extends SetchPlugin> findClass(File file, String main, boolean initialize)
			throws IOException, ReflectiveOperationException {
		return (Class<? extends SetchPlugin>) Class.forName(main, initialize,
				URLClassLoader.newInstance(new URL[] { file.toURI().toURL() }, ClassLoader.getSystemClassLoader()));
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Class<? extends SetchPlugin> findClass(File file, boolean initialize)
			throws IOException, ReflectiveOperationException {
		JarFile jar = new JarFile(file);

		final Enumeration<JarEntry> entries = jar.entries();

		Map<String, byte[]> classDef = new LinkedHashMap<String, byte[]>();

		while (entries.hasMoreElements()) {
			final JarEntry entry = entries.nextElement();

			final String entryClassName = entry.getName().trim();

			final String entryClassCanName = entryClassName.replace("/", ".");

			if (!entryClassCanName.endsWith(".class"))
				continue;

			final InputStream in = jar.getInputStream(entry);

			final byte[] bytes = readInputStream(in).getBytes();

			classDef.put(entryClassCanName, bytes);

		}

		jar.close();

		if (classDef.size() <= 0)
			return null;

		ByteClassLoader loader = new ByteClassLoader();

		loader.extraClassDefs.putAll(classDef);

		for (Map.Entry<String, byte[]> entry : classDef.entrySet()) {
			try {
				return (Class<? extends SetchPlugin>) Class.forName(entry.getKey(), initialize, loader);
			} catch (ReflectiveOperationException | ClassCastException e) {
				continue;
			}

		}

		return null;
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected SetchPlugin initClass(Class<? extends SetchPlugin> clazz) throws ReflectiveOperationException {
		return clazz.getConstructor().newInstance();
	}

	private String readInputStream(InputStream in) throws IOException {
		String finalString = "";

		byte[] buffer = new byte[2048];

		int len;

		while ((len = (in.read(buffer))) > 0) {
			finalString += new String(buffer, 0, len);
		}

		return finalString;
	}

	private static class ByteClassLoader extends URLClassLoader {
		private final Map<String, byte[]> extraClassDefs;

		public ByteClassLoader() {
			super(new URL[] {}, ClassLoader.getSystemClassLoader());
			this.extraClassDefs = new HashMap<String, byte[]>();
		}

		@Override
		protected Class<?> findClass(final String name) throws ClassNotFoundException {
			final byte[] classBytes = this.extraClassDefs.remove(name);
			
			if (classBytes != null) {
				return defineClass(name, classBytes, 0, classBytes.length);
			}
			return super.findClass(name);
		}

	}

}
