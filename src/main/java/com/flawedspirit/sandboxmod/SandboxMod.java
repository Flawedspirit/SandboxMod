/*******************************************************************************
 * Copyright (c) 2016 Flawedspirit.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package com.flawedspirit.sandboxmod;

import com.flawedspirit.sandboxmod.proxy.CommonProxy;
import com.flawedspirit.sandboxmod.registry.BlockRegistrar;
import com.flawedspirit.sandboxmod.registry.ItemRegistrar;
import com.flawedspirit.sandboxmod.registry.MaterialRegistrar;
import com.flawedspirit.sandboxmod.registry.RecipeRegistrar;
import com.flawedspirit.sandboxmod.world.SandboxModWorldGen;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = SandboxMod.MODID, name = SandboxMod.MODNAME, version = SandboxMod.VERSION, dependencies = "required-after:Forge@[12.18.2.2099]")
public class SandboxMod {

	public static final String MODID     = "sandboxmod";
	public static final String MODNAME   = "Sandbox Mod";
	public static final String VERSION   = "@VERSION@";
	
	@Instance(MODID)
	public static SandboxMod instance;
	
	@SidedProxy(clientSide = "com.flawedspirit.sandboxmod.proxy.ClientProxy", serverSide = "com.flawedspirit.sandboxmod.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static final SandboxModTab creativeTab = new SandboxModTab();
	
	@Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
		System.out.print("Initializing object registries... ");
		MaterialRegistrar.registerMaterials();
		ItemRegistrar.registerItems();
		BlockRegistrar.registerBlocks();
		System.out.print("Done!\n");
		
		System.out.print("Initializing recipe registries... ");
		RecipeRegistrar.registerRecipes();
		RecipeRegistrar.registerSmeltingRecipes();
		System.out.print("Done!\n");
		
		System.out.print("Initializing worldgen registry... ");
		GameRegistry.registerWorldGenerator(new SandboxModWorldGen(), 3);
		System.out.print("Done!\n");
    }
        
    @Mod.EventHandler
    public void init(FMLInitializationEvent e) { }
        
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	System.out.println(MODNAME + " has finished initializing.");
    }
}
