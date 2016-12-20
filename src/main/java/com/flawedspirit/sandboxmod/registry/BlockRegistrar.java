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
import com.flawedspirit.sandboxmod.block.counter.BlockCounter;
import com.flawedspirit.sandboxmod.block.itempedestal.BlockItemPedestal;
import com.flawedspirit.sandboxmod.block.jar.BlockJar;
import com.flawedspirit.sandboxmod.block.obsidianpressureplate.BlockObsidianPressurePlate;
import com.flawedspirit.sandboxmod.item.IOreDictItem;
import com.flawedspirit.sandboxmod.reference.Names;

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
	public static BlockItemPedestal itemPedestal;
	public static BlockJar itemJar;
	public static BlockObsidianPressurePlate obsidianPlate;
	
	public static void registerBlocks() {
		blockExperimentium = register(new BlockBase(Material.IRON, Names.Blocks.BLOCK_EXPERIMENTIUM));
		oreExperimentium = register(new BlockOre(Names.Blocks.ORE_EXPERIMENTIUM));
		counter = register(new BlockCounter(Names.Blocks.COUNTER));
		itemPedestal = register(new BlockItemPedestal(Names.Blocks.ITEM_PEDESTAL));
		itemJar = register(new BlockJar(Names.Blocks.GLASS_JAR));
		obsidianPlate = register(new BlockObsidianPressurePlate(Names.Blocks.OBSIDIAN_PLATE));
	}
	
	private static <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		
		return register(block, itemBlock);
	}
	
	private static <T extends Block> T register(T block, ItemBlock itemBlock) {
		GameRegistry.register(block);
		
		if(itemBlock != null) {
			GameRegistry.register(itemBlock);
			
			if(block instanceof BlockBase) {
				((BlockBase) block).registerItemModel(itemBlock);
			}
			
			if(block instanceof BlockObsidianPressurePlate) {
				((BlockObsidianPressurePlate) block).registerItemModel(itemBlock);
			}
			
			if(block instanceof IOreDictItem) {
				((IOreDictItem) block).registerOreDict();
			}
			
			if(itemBlock instanceof IOreDictItem) {
				((IOreDictItem) itemBlock).registerOreDict();
			}
			
			if (block instanceof BlockTileEntity) {
				GameRegistry.registerTileEntity(((BlockTileEntity<?>)block).getTileEntityClass(), block.getRegistryName().toString());
			}
		}
		
		return block;
	}
	

}
