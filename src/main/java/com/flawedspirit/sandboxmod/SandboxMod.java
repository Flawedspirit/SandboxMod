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
import com.flawedspirit.sandboxmod.registry.RecipeRegistrar;
import com.flawedspirit.sandboxmod.world.SandboxModWorldGen;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = SandboxMod.MODID, name = SandboxMod.MODNAME, version = SandboxMod.VERSION)
public class SandboxMod {

	public static final String MODID     = "sandboxmod";
	public static final String MODNAME   = "Sandbox Mod";
	public static final String VERSION   = "0.0.1-Alpha";
	public static final String MCVERSION = "1.10.2";
	
	@Mod.Instance
	public static SandboxMod instance = new SandboxMod();
	
	@SidedProxy(clientSide = "com.flawedspirit.sandboxmod.proxy.ClientProxy", serverSide = "com.flawedspirit.sandboxmod.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static final SandboxModTab creativeTab = new SandboxModTab();
	
	/* REGISTER MATERIALS */
	public static final ItemArmor.ArmorMaterial experimentiumArmorMaterial = EnumHelper.addArmorMaterial("EXPERIMENTIUM", MODID + ":experimentium", 42, new int[]{3, 6, 8, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3f);
	public static final Item.ToolMaterial experimentiumToolMaterial = EnumHelper.addToolMaterial("EXPERIMENTIUM", 3, 2440, 14f, 4f, 26);
	
	@Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
		System.out.print("Initializing item registries... ");
		ItemRegistrar.registerItems();
		BlockRegistrar.registerBlocks();
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
