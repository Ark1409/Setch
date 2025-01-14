package org.setch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;

import org.setch.plugin.setch.SetchPlugin;
import org.setch.plugin.setch.SetchPluginLoader;

/**
 * Launcher for the {@code Setch Plugin API}.
 */
public final class Main {
	protected static String[] args;

	public static void main(String[] args) {
		Main.args = args;
		SetchPluginLoader loader = new SetchPluginLoader();
		try {
			Collection<? extends SetchPlugin> pluginsList = loader.loadDirectory(new File("plugins"), true,
					"com.myplugin.main.PluginMain", new FilenameFilter() {

						@Override
						public boolean accept(File dir, String name) {
							return name.endsWith(".jar");
						}

					});

			final SetchPlugin[] plugins = pluginsList.toArray(new SetchPlugin[0]);

			for (int i = 0; i < plugins.length; i++) {
				final SetchPlugin plugin = plugins[i];

				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						final int eStatus = plugin.enable();

						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						final int dStatus = plugin.disable();

						System.out.println("[" + plugin.getName() + "Summary:] ");
						System.out.println("Enable status: " + eStatus);
						System.out.println("Disable status: " + dStatus);
					}

				});

				plugin.initPlugin(loader, t);
				
				t.start();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
		
		System.out.println("Plugins have been loaded!");

	}

}
