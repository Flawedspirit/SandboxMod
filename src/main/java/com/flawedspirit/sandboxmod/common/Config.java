package com.flawedspirit.sandboxmod.common;

import org.apache.logging.log4j.Level;

import com.flawedspirit.sandboxmod.SandboxMod;
import com.flawedspirit.sandboxmod.reference.Names;

import net.minecraftforge.common.config.Configuration;

public class Config {
	
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
	
	private static void initRecipeConfig(Configuration config) {
		enableExperimentiumHelmetCrafting = config.getBoolean(Names.Config.EXPERIMENTIUM_HELMET_ENABLED, "recipes", enableExperimentiumHelmetCrafting, "Set this to false to disable crafting of Experimentium Helmet.");
		enableExperimentiumChestplateCrafting = config.getBoolean(Names.Config.EXPERIMENTIUM_CHEST_ENABLED, "recipes", enableExperimentiumChestplateCrafting, "Set this to false to disable crafting of Experimentium Chestplate.");
		enableExperimentiumLeggingsCrafting = config.getBoolean(Names.Config.EXPERIMENTIUM_LEGS_ENABLED, "recipes", enableExperimentiumLeggingsCrafting, "Set this to false to disable crafting of Experimentium Leggings.");
		enableExperimentiumBootsCrafting = config.getBoolean(Names.Config.EXPERIMENTIUM_BOOTS_ENABLED, "recipes", enableExperimentiumBootsCrafting, "Set this to false to disable crafting of Experimentium Boots.");
	}
	
	private static void initWorldConfig(Configuration config) {
		generateExperimentiumOre = config.getBoolean(Names.Config.GENERATE_EXPERIMENTIUM_ORE, "world", generateExperimentiumOre, "Set this to false to disable world ore generation.");
		forceRetroGen = config.getBoolean(Names.Config.FORCE_RETRO_GEN, "world", forceRetroGen, "Set this to true to try and regenerate ores in already-loaded chunks.");
	}
}
