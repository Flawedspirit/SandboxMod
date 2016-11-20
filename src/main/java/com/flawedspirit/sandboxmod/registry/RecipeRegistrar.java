package com.flawedspirit.sandboxmod.registry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeRegistrar {

	public static void registerRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(BlockRegistrar.blockExperimentium), "EEE", "EEE", "EEE", 'E', ItemRegistrar.ingotExperimentium);
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegistrar.ingotExperimentium), "NNN", "NNN", "NNN", 'N', ItemRegistrar.nuggetExperimentium);
		
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistrar.ingotExperimentium, 9), BlockRegistrar.blockExperimentium);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistrar.nuggetExperimentium, 9), ItemRegistrar.ingotExperimentium);
	}
	
	public static void registerSmeltingRecipes() {
		GameRegistry.addSmelting(BlockRegistrar.oreExperimentium, new ItemStack(ItemRegistrar.ingotExperimentium), 0.8f);
	}
}
