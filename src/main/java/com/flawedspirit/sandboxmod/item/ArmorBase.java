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

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public class ArmorBase extends ItemArmor implements IItemModelProvider {
	
	private String name;

	public ArmorBase(String unlocalizedName, ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, 0, equipmentSlotIn);
		this.name = unlocalizedName;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(SandboxMod.creativeTab);
	}

	@Override
	public void registerItemModel(Item item) {
		ClientProxy.registerItemRenderers(this, 0, name);
	}
}
