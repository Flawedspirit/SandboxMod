/*******************************************************************************
 * Copyright (c) 2016 Flawedspirit.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package com.flawedspirit.sandboxmod.client;

import org.apache.logging.log4j.Level;

import com.flawedspirit.sandboxmod.SandboxMod;
import com.flawedspirit.sandboxmod.block.itempedestal.TileEntityItemPedestal;
import com.flawedspirit.sandboxmod.block.jar.TileEntityJar;
import com.flawedspirit.sandboxmod.client.render.TileEntityItemPedestalRenderer;
import com.flawedspirit.sandboxmod.client.render.TileEntityJarRenderer;
import com.flawedspirit.sandboxmod.common.CommonProxy;
import com.flawedspirit.sandboxmod.reference.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		registerRenderers();
	}

	public static void registerItemRenderers(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reference.MODID + ":" + id, "inventory"));
	}
	
	public static void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityItemPedestal.class, new TileEntityItemPedestalRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJar.class, new TileEntityJarRenderer());
		SandboxMod.logger.log(Level.INFO, "Finished initializing renderers.");
	}
}
