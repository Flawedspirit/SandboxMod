package com.flawedspirit.sandboxmod.common;

import java.io.File;

import org.apache.logging.log4j.Level;

import com.flawedspirit.sandboxmod.SandboxMod;
import com.flawedspirit.sandboxmod.compatibility.CompatHandler;
import com.flawedspirit.sandboxmod.handlers.ConfigHandler;
import com.flawedspirit.sandboxmod.handlers.EventHandler;
import com.flawedspirit.sandboxmod.handlers.UpdateHandler;
import com.flawedspirit.sandboxmod.handlers.UpdateHandler.UpdateStatus;
import com.flawedspirit.sandboxmod.handlers.UpdateHandler.VersionType;
import com.flawedspirit.sandboxmod.reference.Reference;
import com.flawedspirit.sandboxmod.registry.BlockRegistrar;
import com.flawedspirit.sandboxmod.registry.ItemRegistrar;
import com.flawedspirit.sandboxmod.registry.MaterialRegistrar;
import com.flawedspirit.sandboxmod.registry.RecipeRegistrar;
import com.flawedspirit.sandboxmod.world.SandboxModWorldGen;

import net.minecraft.item.Item;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public static Configuration config;

	@SuppressWarnings("deprecation")
	public void preInit(FMLPreInitializationEvent event) {
		
		// Check if we are running in a deObf environment
		Reference.IS_DEV = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
		
		File configDir = event.getModConfigurationDirectory();
		config = new Configuration(new File(configDir.getPath(), Reference.MODID + ".cfg"));
		ConfigHandler.loadConfig();
		SandboxMod.logger.log(Level.INFO, "Loaded config.");
		
		// Register mod objects
		MaterialRegistrar.registerMaterials();
		ItemRegistrar.registerItems();
		BlockRegistrar.registerBlocks();
		
		RecipeRegistrar.registerRecipes();
		RecipeRegistrar.registerSmeltingRecipes();
		
		if(ConfigHandler.generateExperimentiumOre) {
			GameRegistry.registerWorldGenerator(new SandboxModWorldGen(), 3);
		}
		
		// Register event handlers
		EventHandler handler = new EventHandler();
		FMLCommonHandler.instance().bus().register(handler);
		
		// Register compatability
		CompatHandler.registerTOPCompat();
		CompatHandler.registerWailaCompat();
		SandboxMod.logger.log(Level.INFO, "Finished registering common components.");
	}
	
	public void init(FMLInitializationEvent event) {
		
		// Get latest version
		if(!Reference.IS_DEV) {
			if(ConfigHandler.enableUpdateChecking) {
				if(ConfigHandler.allowBetaUpdates) {
					Reference.UPDATE_TYPE = VersionType.BETA;
				}
				
				Reference.LATEST_VERSION = UpdateHandler.compareVersions("1.1.4", UpdateHandler.getUpdate(Reference.UPDATE_TYPE));
				
				if(Reference.LAST_UPDATE_STATE == UpdateStatus.OUTDATED) {
					SandboxMod.logger.log(Level.INFO, "A new version of " + Reference.MODNAME + " is available: [" + Reference.LATEST_VERSION + "]");
				} else if(Reference.LAST_UPDATE_STATE == UpdateStatus.CURRENT) {
					SandboxMod.logger.log(Level.INFO, Reference.MODNAME + " is up to date. Yay you!");
				} else {
					SandboxMod.logger.log(Level.WARN, "Unable to query remote version authority!");
				}
			}
		} else {
			SandboxMod.logger.log(Level.INFO, "We are in a dev environment; skipping version checking.");
		}
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		
		if(config.hasChanged()) {
			config.save();
		}
	}
	
	public void registerItemRenderers(Item item, int meta, String id) {}
}
