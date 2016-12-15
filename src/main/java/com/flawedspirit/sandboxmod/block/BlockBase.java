/*******************************************************************************
 * Copyright (c) 2016 Flawedspirit.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package com.flawedspirit.sandboxmod.block;

import com.flawedspirit.sandboxmod.SandboxMod;
import com.flawedspirit.sandboxmod.client.ClientProxy;
import com.flawedspirit.sandboxmod.item.IItemModelProvider;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IItemModelProvider {

	protected String name;
	
	public BlockBase(Material material, String unlocalizedName) {
		this(material, unlocalizedName, 3f, 5f);
	}
	
	public BlockBase(Material material, String unlocalizedName, float hardness, float resistance) {
		super(material);
		this.name = unlocalizedName;
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setResistance(resistance);
		setCreativeTab(SandboxMod.creativeTab);
	}
	
	public void registerItemModel(ItemBlock itemBlock) {
		ClientProxy.registerItemRenderers(itemBlock, 0, name);
	}
	
	@Override
	public BlockBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public void registerItemModel(Item item) {
		ClientProxy.registerItemRenderers(item, 0, name);
	}
}
