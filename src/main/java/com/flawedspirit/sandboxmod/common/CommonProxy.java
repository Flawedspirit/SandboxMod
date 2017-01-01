package com.flawedspirit.sandboxmod.common;

import java.io.File;

import org.apache.logging.log4j.Level;

import com.flawedspirit.sandboxmod.SandboxMod;
import com.flawedspirit.sandboxmod.compatibility.CompatHandler;
import com.flawedspirit.sandboxmod.handlers.ConfigHandler;
import com.flawedspirit.sandboxmod.handlers.EventHandler;
import com.flawedspirit.sandboxmod.handlers.UpdateHandler;
import com.flawedspirit.sandboxmod.reference.Reference;
import com.flawedspirit.sandboxmod.registry.BlockRegistrar;
import com.flawedspirit.sandboxmod.registry.ItemRegistrar;
import com.flawedspirit.sandboxmod.registry.MaterialRegistrar;
import com.flawedspirit.sandboxmod.registry.RecipeRegistrar;
import com.flawedspirit.sandboxmod.world.SandboxModWorldGen;

import net.minecraft.item.Item;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class CommonProxy {

	public static Configuration config;

	public void preInit(FMLPreInitializationEvent event) {
		
		// Check if we are running in a development environment
		Reference.IS_DEV = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
		
		File configDir = event.getModConfigurationDirectory();
		config = new Configuration(new File(configDir.getPath(), Reference.MODID + ".cfg"));
		ConfigHandler.loadConfig();
		SandboxMod.logger.log(Level.INFO, "Loaded config.");
		
		// Check for updates
		if(!Reference.IS_DEV) {
			if(ConfigHandler.enableUpdateChecking) {
				UpdateHandler.updateCheckResult = UpdateHandler.getUpdateStatus();
			}
		} else {
			SandboxMod.logger.log(Level.INFO, "We are in a development environment; skipping update check."); 
		}
		
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
		MinecraftForge.EVENT_BUS.register(handler);
		
		// Register compatibility
		CompatHandler.registerTOPCompat();
		CompatHandler.registerWailaCompat();
		SandboxMod.logger.log(Level.INFO, "Finished registering common components.");
	}
	
	public void init(FMLInitializationEvent event) {}
	
	public void postInit(FMLPostInitializationEvent event) {
		
		if(config.hasChanged()) {
			config.save();
		}
	}
	
	abstract public void registerItemRenderers(Item item, int meta, String id);
}
