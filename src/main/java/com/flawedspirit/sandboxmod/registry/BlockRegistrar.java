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
	
	public static void registerBlocks() {
		blockExperimentium = registerBlock(new BlockBase(Material.IRON, "blockExperimentium"));
		oreExperimentium = registerBlock(new BlockOre("oreExperimentium"));
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
		}
		
		return block;
	}
	
	private static <B extends Block> B registerBlock(B block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		
		return registerBlock(block, itemBlock);
	}
}
