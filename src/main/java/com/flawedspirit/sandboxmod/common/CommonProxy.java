package com.flawedspirit.sandboxmod.common;

import java.io.File;

import org.apache.logging.log4j.Level;

import com.flawedspirit.sandboxmod.SandboxMod;
import com.flawedspirit.sandboxmod.compatibility.CompatHandler;
import com.flawedspirit.sandboxmod.handlers.ConfigHandler;
import com.flawedspirit.sandboxmod.handlers.UpdateHandler;
import com.flawedspirit.sandboxmod.reference.Reference;
import com.flawedspirit.sandboxmod.registry.BlockRegistrar;
import com.flawedspirit.sandboxmod.registry.ItemRegistrar;
import com.flawedspirit.sandboxmod.registry.MaterialRegistrar;
import com.flawedspirit.sandboxmod.registry.RecipeRegistrar;
import com.flawedspirit.sandboxmod.world.SandboxModWorldGen;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public static Configuration config;

	public void preInit(FMLPreInitializationEvent event) {
		File configDir = event.getModConfigurationDirectory();
		config = new Configuration(new File(configDir.getPath(), Reference.MODID + ".cfg"));
		ConfigHandler.loadConfig();
		SandboxMod.logger.log(Level.INFO, "Loaded config.");
		
		MaterialRegistrar.registerMaterials();
		ItemRegistrar.registerItems();
		BlockRegistrar.registerBlocks();
		
		RecipeRegistrar.registerRecipes();
		RecipeRegistrar.registerSmeltingRecipes();
		
		if(ConfigHandler.generateExperimentiumOre) {
			GameRegistry.registerWorldGenerator(new SandboxModWorldGen(), 3);
		}
		
		CompatHandler.registerTOPCompat();
		CompatHandler.registerWailaCompat();
		SandboxMod.logger.log(Level.INFO, "Finished registering common components.");
	}
	
	public void init(FMLInitializationEvent event) {
		SandboxMod.logger.log(Level.INFO, "Current version: " + Reference.VERSION + " :: Latest version: " + UpdateHandler.getUpdate());
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		
		if(config.hasChanged()) {
			config.save();
		}
	}
	
	public void registerItemRenderers(Item item, int meta, String id) {}
}
