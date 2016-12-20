package com.flawedspirit.sandboxmod.registry;

import com.flawedspirit.sandboxmod.handler.ConfigHandler;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipeRegistrar {

	public static void registerRecipes() {
		if(ConfigHandler.enableExperimentiumHelmetCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistrar.experimentiumHelmet), "EEE", "E E", 'E', ItemRegistrar.ingotExperimentium);
		}
		
		if(ConfigHandler.enableExperimentiumChestplateCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistrar.experimentiumChestplate), "E E", "EEE", "EEE", 'E', ItemRegistrar.ingotExperimentium);
		}
		
		if(ConfigHandler.enableExperimentiumLeggingsCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistrar.experimentiumLeggings), "EEE", "E E", "E E", 'E', ItemRegistrar.ingotExperimentium);
		}
		
		if(ConfigHandler.enableExperimentiumBootsCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistrar.experimentiumBoots), "E E", "E E", 'E', ItemRegistrar.ingotExperimentium);
		}
		
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegistrar.ingotExperimentium), "NNN", "NNN", "NNN", 'N', ItemRegistrar.nuggetExperimentium);
		GameRegistry.addShapedRecipe(new ItemStack(BlockRegistrar.blockExperimentium), "EEE", "EEE", "EEE", 'E', ItemRegistrar.ingotExperimentium);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistrar.itemPedestal), " S ", "NQN", "WSW", 'S', new ItemStack(Blocks.STONE_SLAB, 1, 0), 'N', "nuggetGold", 'Q', new ItemStack(Blocks.QUARTZ_BLOCK, 1, 2), 'W', "slabWood"));
		
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistrar.ingotExperimentium, 9), BlockRegistrar.blockExperimentium);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistrar.nuggetExperimentium, 9), ItemRegistrar.ingotExperimentium);
	}
	
	public static void registerSmeltingRecipes() {
		GameRegistry.addSmelting(BlockRegistrar.oreExperimentium, new ItemStack(ItemRegistrar.ingotExperimentium), 0.8f);
	}
}
