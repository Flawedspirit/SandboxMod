/*******************************************************************************
 * Copyright (c) 2016 Flawedspirit.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package com.flawedspirit.sandboxmod.item;

import com.flawedspirit.sandboxmod.SandboxMod;
import com.flawedspirit.sandboxmod.client.ClientProxy;

import net.minecraft.item.Item;

public class ItemBase extends Item implements IItemModelProvider {
	
	protected String unlocalizedName;
	
	public ItemBase(String unlocalizedName) {
		this(unlocalizedName, 64);
	}
	
	public ItemBase(String unlocalizedName, int maxStackSize) {
		this.unlocalizedName = unlocalizedName;
		setUnlocalizedName(unlocalizedName);
		setRegistryName(unlocalizedName);
		setCreativeTab(SandboxMod.creativeTab);
		setMaxStackSize(maxStackSize);
	}

	@Override
	public void registerItemModel(Item item) {
		ClientProxy.registerItemRenderers(item, 0, unlocalizedName);
	}
}
