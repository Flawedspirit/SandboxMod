/*******************************************************************************
 * Copyright (c) 2016 Flawedspirit.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package com.flawedspirit.sandboxmod;

import com.flawedspirit.sandboxmod.client.ClientProxy;
import com.flawedspirit.sandboxmod.common.CommonProxy;
import com.flawedspirit.sandboxmod.reference.Reference;
import com.flawedspirit.sandboxmod.registry.ItemRegistrar;

import jline.internal.Log;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION, dependencies = Reference.DEPENDANCIES)
public class SandboxMod {

	@Instance(Reference.MODID)
	public static SandboxMod instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;
	
	public static CreativeTabs creativeTab = new CreativeTabs(Reference.MODID) {
		@Override
		public Item getTabIconItem() {
			return ItemRegistrar.ingotExperimentium;
		}
	};
	
	@Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit();
    }
        
    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
    	ClientProxy.registerRenderers();
    }
        
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	Log.info(Reference.MODNAME + " has finished initializing.");
    }
}
