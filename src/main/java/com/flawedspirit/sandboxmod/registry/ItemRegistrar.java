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
