package me.luisito;

import net.fabricmc.api.ModInitializer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerBrandChanger implements ModInitializer {
	public static final String MOD_ID = "serverbrandchanger";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static String brand = "fabric";

	@Override
	public void onInitialize() {
		try {
			// Get jar path
			String jarPath = (ServerBrandChanger.class.getProtectionDomain().getCodeSource().getLocation()
					.toURI()).getPath();
			File jarFile = new File(jarPath);

			// Create config folder in same folder as mod .jar file
			File configDir = new File(
					Paths.get(jarFile.getParentFile().getAbsolutePath(), "serverbrandchanger/").toAbsolutePath()
							.toString());
			configDir.mkdir();

			// Create new or read existing config file
			File configFile = new File(Paths.get(configDir.getAbsolutePath(), "brand.txt").toString());
			if (configFile.exists()) {
				BufferedReader reader = new BufferedReader(new FileReader(configFile));

				String line = reader.readLine();
				brand = line;

				reader.close();
			} else {
				configFile.createNewFile();
				BufferedWriter out = new BufferedWriter(new FileWriter(configFile));
				out.write("fabric");
				out.close();
			}

		} catch (URISyntaxException | IOException ex) {
			LOGGER.error("Failed to write config! Error:");
			ex.printStackTrace();
		}
	}
}