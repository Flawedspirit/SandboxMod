/*******************************************************************************
 * Copyright (c) 2016 Flawedspirit.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package com.flawedspirit.sandboxmod.registry;

import com.flawedspirit.sandboxmod.block.BlockBase;
import com.flawedspirit.sandboxmod.block.BlockOre;
import com.flawedspirit.sandboxmod.block.BlockTileEntity;
import com.flawedspirit.sandboxmod.block.tile.BlockCounter;
import com.flawedspirit.sandboxmod.item.IModelProvider;
import com.flawedspirit.sandboxmod.item.IOreDictItem;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRegistrar {
	
	/* Materials */
	public static BlockBase blockExperimentium;
	
	/* Ores */
	public static BlockOre oreExperimentium;
	
	/* Tile Entities */
	public static BlockCounter counter;
	
	public static void registerBlocks() {
		blockExperimentium = registerBlock(new BlockBase(Material.IRON, "blockExperimentium"));
		oreExperimentium = registerBlock(new BlockOre("oreExperimentium"));
		counter = registerBlock(new BlockCounter("counter"));		
	}
	
	private static <B extends Block> B registerBlock(B block, ItemBlock itemBlock) {
		GameRegistry.register(block);
		
		if(itemBlock != null) {
			GameRegistry.register(itemBlock);
			
			if(block instanceof IModelProvider) {
				((IModelProvider) block).registerItemModel(itemBlock);
			}
			
			if(block instanceof IOreDictItem) {
				((IOreDictItem) block).registerOreDict();
			} else if(itemBlock instanceof IOreDictItem) {
				((IOreDictItem) itemBlock).registerOreDict();
			}
			
			if (block instanceof BlockTileEntity) {
				GameRegistry.registerTileEntity(((BlockTileEntity<?>)block).getTileEntityClass(), block.getRegistryName().toString());
			}
		}
		
		return block;
	}
	
	private static <B extends Block> B registerBlock(B block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		
		return registerBlock(block, itemBlock);
	}
}
