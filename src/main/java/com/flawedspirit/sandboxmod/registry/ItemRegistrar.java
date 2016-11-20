
/*******************************************************************************
 * Copyright (c) 2016 Flawedspirit.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package com.flawedspirit.sandboxmod.registry;

import com.flawedspirit.sandboxmod.item.IModelProvider;
import com.flawedspirit.sandboxmod.item.IOreDictItem;
import com.flawedspirit.sandboxmod.item.ItemBase;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistrar {
	
	/* Materials */
	public static Item ingotExperimentium;
	public static Item nuggetExperimentium;
	
	public static void registerItems() {
		ingotExperimentium = registerItem(new ItemBase("ingotExperimentium"));
		nuggetExperimentium = registerItem(new ItemBase("nuggetExperimentium"));
	}
	
	private static <I extends Item> I registerItem(I item) {
		GameRegistry.register(item);
		
		if(item instanceof IModelProvider) {
			((ItemBase) item).registerItemModel(item);
		}
		
		if(item instanceof IOreDictItem) {
			((IOreDictItem) item).initOreDict();
		}
		
		return item;
	}
}
