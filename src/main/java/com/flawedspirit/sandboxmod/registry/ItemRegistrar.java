package com.flawedspirit.sandboxmod.registry;

import com.flawedspirit.sandboxmod.items.ArmorBase;
import com.flawedspirit.sandboxmod.items.IOreDictItem;
import com.flawedspirit.sandboxmod.items.ItemBase;
import com.flawedspirit.sandboxmod.items.OreDictItem;
import com.flawedspirit.sandboxmod.reference.Names;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistrar {
	
	/* Items */
	public static ItemBase tunedMobCrystal;
	
	/* Armor */
	public static ArmorBase experimentiumHelmet;
	public static ArmorBase experimentiumChestplate;
	public static ArmorBase experimentiumLeggings;
	public static ArmorBase experimentiumBoots;
	
	/* Materials */
	public static Item ingotExperimentium;
	public static Item nuggetExperimentium;
	
	public static void registerItems() {
		tunedMobCrystal = register(new ItemBase(Names.Items.TUNED_MOB_CRYSTAL));
		
		experimentiumHelmet = register(new ArmorBase(Names.Items.EXPERIMENTIUM_HELMET, MaterialRegistrar.experimentiumArmorMaterial, EntityEquipmentSlot.HEAD));
		experimentiumChestplate = register(new ArmorBase(Names.Items.EXPERIMENTIUM_CHEST, MaterialRegistrar.experimentiumArmorMaterial, EntityEquipmentSlot.CHEST));
		experimentiumLeggings = register(new ArmorBase(Names.Items.EXPERIMENTIUM_LEGS, MaterialRegistrar.experimentiumArmorMaterial, EntityEquipmentSlot.LEGS));
		experimentiumBoots = register(new ArmorBase(Names.Items.EXPERIMENTIUM_BOOTS, MaterialRegistrar.experimentiumArmorMaterial, EntityEquipmentSlot.FEET));
		
		ingotExperimentium = register(new OreDictItem(Names.Items.EXPERIMENTIUM_INGOT));
		nuggetExperimentium = register(new OreDictItem(Names.Items.EXPERIMENTIUM_NUGGET));
	}
	
	private static <I extends Item> I register(I item) {
		GameRegistry.register(item);
		
		if(item instanceof ItemBase) {
			((ItemBase) item).registerItemModel(item);
		}
		
		if(item instanceof ArmorBase) {
			((ArmorBase) item).registerItemModel(item);
		}
		
		if(item instanceof IOreDictItem) {
			((IOreDictItem) item).registerOreDict();
		}
		return item;
	}
}
