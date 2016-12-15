/*******************************************************************************
 * Copyright (c) 2016 Flawedspirit.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package com.flawedspirit.sandboxmod.registry;

import com.flawedspirit.sandboxmod.common.Config;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeRegistrar {

	public static void registerRecipes() {
		if(Config.enableExperimentiumHelmetCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistrar.experimentiumHelmet), "EEE", "E E", 'E', ItemRegistrar.ingotExperimentium);
		}
		
		if(Config.enableExperimentiumChestplateCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistrar.experimentiumChestplate), "E E", "EEE", "EEE", 'E', ItemRegistrar.ingotExperimentium);
		}
		
		if(Config.enableExperimentiumLeggingsCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistrar.experimentiumLeggings), "EEE", "E E", "E E", 'E', ItemRegistrar.ingotExperimentium);
		}
		
		if(Config.enableExperimentiumBootsCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(ItemRegistrar.experimentiumBoots), "E E", "E E", 'E', ItemRegistrar.ingotExperimentium);
		}
		
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegistrar.ingotExperimentium), "NNN", "NNN", "NNN", 'N', ItemRegistrar.nuggetExperimentium);
		
		GameRegistry.addShapedRecipe(new ItemStack(BlockRegistrar.blockExperimentium), "EEE", "EEE", "EEE", 'E', ItemRegistrar.ingotExperimentium);
		
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistrar.ingotExperimentium, 9), BlockRegistrar.blockExperimentium);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistrar.nuggetExperimentium, 9), ItemRegistrar.ingotExperimentium);
	}
	
	public static void registerSmeltingRecipes() {
		GameRegistry.addSmelting(BlockRegistrar.oreExperimentium, new ItemStack(ItemRegistrar.ingotExperimentium), 0.8f);
	}
}
