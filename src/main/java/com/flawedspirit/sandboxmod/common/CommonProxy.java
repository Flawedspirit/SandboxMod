/*******************************************************************************
 * Copyright (c) 2016 Flawedspirit.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package com.flawedspirit.sandboxmod.common;

import com.flawedspirit.sandboxmod.compatability.CompatHandler;
import com.flawedspirit.sandboxmod.registry.BlockRegistrar;
import com.flawedspirit.sandboxmod.registry.ItemRegistrar;
import com.flawedspirit.sandboxmod.registry.MaterialRegistrar;
import com.flawedspirit.sandboxmod.registry.RecipeRegistrar;
import com.flawedspirit.sandboxmod.world.SandboxModWorldGen;

import jline.internal.Log;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void preInit() {
		MaterialRegistrar.registerMaterials();
		ItemRegistrar.registerItems();
		BlockRegistrar.registerBlocks();
		Log.info("Registered objects.");
		
		RecipeRegistrar.registerRecipes();
		RecipeRegistrar.registerSmeltingRecipes();
		Log.info("Registered recipes.");
		
		GameRegistry.registerWorldGenerator(new SandboxModWorldGen(), 3);
		Log.info("Registered world generators.");
		
		CompatHandler.registerTOPCompat();
		CompatHandler.registerWailaCompat();
		Log.info("Registered mod compatability handlers.");
	}
	
	public void init() {}
	
	public void postInit() {}
}
