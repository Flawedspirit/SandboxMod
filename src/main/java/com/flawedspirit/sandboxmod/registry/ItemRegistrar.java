
/*******************************************************************************
 * Copyright (c) 2016 Flawedspirit.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package com.flawedspirit.sandboxmod.registry;

import com.flawedspirit.sandboxmod.SandboxMod;
import com.flawedspirit.sandboxmod.item.ArmorBase;
import com.flawedspirit.sandboxmod.item.IModelProvider;
import com.flawedspirit.sandboxmod.item.IOreDictItem;
import com.flawedspirit.sandboxmod.item.ItemBase;

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
		experimentiumHelmet = registerItem(new ArmorBase("experimentiumHelmet", SandboxMod.experimentiumArmorMaterial, EntityEquipmentSlot.HEAD));
		experimentiumChestplate = registerItem(new ArmorBase("experimentiumChestplate", SandboxMod.experimentiumArmorMaterial, EntityEquipmentSlot.CHEST));
		experimentiumLeggings = registerItem(new ArmorBase("experimentiumLeggings", SandboxMod.experimentiumArmorMaterial, EntityEquipmentSlot.LEGS));
		experimentiumBoots = registerItem(new ArmorBase("experimentiumBoots", SandboxMod.experimentiumArmorMaterial, EntityEquipmentSlot.FEET));
		
		ingotExperimentium = registerItem(new ItemBase("ingotExperimentium"));
		nuggetExperimentium = registerItem(new ItemBase("nuggetExperimentium"));
	}
	
	private static <I extends Item> I registerItem(I item) {
		GameRegistry.register(item);
		
		if(item instanceof IModelProvider) {
			((IModelProvider) item).registerItemModel(item);
		}
		
		if(item instanceof IOreDictItem) {
			((IOreDictItem) item).initOreDict();
		}
		
		return item;
	}
}
