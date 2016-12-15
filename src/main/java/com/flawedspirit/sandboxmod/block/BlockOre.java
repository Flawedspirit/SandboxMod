/*******************************************************************************
 * Copyright (c) 2016 Flawedspirit.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package com.flawedspirit.sandboxmod.block;

import com.flawedspirit.sandboxmod.item.IOreDictItem;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.oredict.OreDictionary;

public class BlockOre extends BlockBase implements IOreDictItem {
	
	private String oreDictName;
	
	public BlockOre(String unlocalizedName, String oreDictName) {
		this(unlocalizedName, oreDictName, 3f, 5f);
	}
	
	public BlockOre(String unlocalizedName, String oreDictName, float hardness, float resistance) {
		super(Material.ROCK, unlocalizedName);
		this.oreDictName = oreDictName;
		setHardness(hardness);
		setResistance(resistance);
	}
	
	@Override
	public BlockOre setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public void registerOreDict() {
		OreDictionary.registerOre(oreDictName, this);
	}
}
