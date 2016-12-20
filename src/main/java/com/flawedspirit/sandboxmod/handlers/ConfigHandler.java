package com.flawedspirit.sandboxmod.handlers;

import org.apache.logging.log4j.Level;

import com.flawedspirit.sandboxmod.SandboxMod;
import com.flawedspirit.sandboxmod.common.CommonProxy;
import com.flawedspirit.sandboxmod.reference.Names;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
	
	// Update options
	public static boolean enableUpdateChecking = true;
	public static boolean allowBetaUpdates = false;
	
	// Crafting recipes
	public static boolean enableExperimentiumHelmetCrafting = true;
	public static boolean enableExperimentiumChestplateCrafting = true;
	public static boolean enableExperimentiumLeggingsCrafting = true;
	public static boolean enableExperimentiumBootsCrafting = true;
	
	// World generation
	public static boolean generateExperimentiumOre = true;
	public static boolean forceRetroGen = false;
	
	public static void loadConfig() {
		Configuration config = CommonProxy.config;
		
		try {
			config.load();
			initUpdateConfig(config);
			initRecipeConfig(config);
			initWorldConfig(config);
		} catch(Exception ex) {
			SandboxMod.logger.log(Level.ERROR, "Could not load the config file! I suspect this is a bad thing!", ex);
		} finally {
			if(config.hasChanged()) {
				config.save();
			}
		}
	}
	
	private static void initUpdateConfig(Configuration config) {
		enableUpdateChecking = config.getBoolean(Names.Config.UPDATES_ENABLED, "updates", enableUpdateChecking, "Set this to false to disable automatic update checks.");
		allowBetaUpdates = config.getBoolean(Names.Config.BETA_UPDATES_ENABLED, "updates", allowBetaUpdates, "Set this to true to enable update checking of beta builds.");
	}
	
	private static void initRecipeConfig(Configuration config) {
		enableExperimentiumHelmetCrafting = config.getBoolean(Names.Config.EXPERIMENTIUM_HELMET_ENABLED, "recipes", enableExperimentiumHelmetCrafting, "");
		enableExperimentiumChestplateCrafting = config.getBoolean(Names.Config.EXPERIMENTIUM_CHEST_ENABLED, "recipes", enableExperimentiumChestplateCrafting, "");
		enableExperimentiumLeggingsCrafting = config.getBoolean(Names.Config.EXPERIMENTIUM_LEGS_ENABLED, "recipes", enableExperimentiumLeggingsCrafting, "");
		enableExperimentiumBootsCrafting = config.getBoolean(Names.Config.EXPERIMENTIUM_BOOTS_ENABLED, "recipes", enableExperimentiumBootsCrafting, "");
	}
	
	private static void initWorldConfig(Configuration config) {
		generateExperimentiumOre = config.getBoolean(Names.Config.GENERATE_EXPERIMENTIUM_ORE, "world", generateExperimentiumOre, "Set this to false to disable world ore generation.");
		forceRetroGen = config.getBoolean(Names.Config.FORCE_RETRO_GEN, "world", forceRetroGen, "Set this to true to try and regenerate ores in already-loaded chunks.");
	}
}
