
/*******************************************************************************
 * Copyright (c) 2016 Flawedspirit.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package com.flawedspirit.sandboxmod.registry;

import com.flawedspirit.sandboxmod.item.ArmorBase;
import com.flawedspirit.sandboxmod.item.IOreDictItem;
import com.flawedspirit.sandboxmod.item.OreDictItem;
import com.flawedspirit.sandboxmod.reference.Names;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistrar {
	
	/* Armor */
	public static ArmorBase experimentiumHelmet;
	public static ArmorBase experimentiumChestplate;
	public static ArmorBase experimentiumLeggings;
	public static ArmorBase experimentiumBoots;
	
	/* Materials */
	public static Item ingotExperimentium;
	public static Item nuggetExperimentium;
	
	public static void registerItems() {
		experimentiumHelmet = registerItem(new ArmorBase(Names.Items.EXPERIMENTIUM_HELMET, MaterialRegistrar.experimentiumArmorMaterial, EntityEquipmentSlot.HEAD));
		experimentiumChestplate = registerItem(new ArmorBase(Names.Items.EXPERIMENTIUM_CHEST, MaterialRegistrar.experimentiumArmorMaterial, EntityEquipmentSlot.CHEST));
		experimentiumLeggings = registerItem(new ArmorBase(Names.Items.EXPERIMENTIUM_LEGS, MaterialRegistrar.experimentiumArmorMaterial, EntityEquipmentSlot.LEGS));
		experimentiumBoots = registerItem(new ArmorBase(Names.Items.EXPERIMENTIUM_BOOTS, MaterialRegistrar.experimentiumArmorMaterial, EntityEquipmentSlot.FEET));
		
		ingotExperimentium = registerItem(new OreDictItem(Names.Items.EXPERIMENTIUM_INGOT));
		nuggetExperimentium = registerItem(new OreDictItem(Names.Items.EXPERIMENTIUM_NUGGET));
	}
	
	private static <I extends Item> I registerItem(I item) {
		GameRegistry.register(item);
		
		if(item instanceof IOreDictItem) {
			((IOreDictItem) item).registerOreDict();
		}
		
		return item;
	}
}
