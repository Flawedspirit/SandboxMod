package com.flawedspirit.sandboxmod.registry;

import com.flawedspirit.sandboxmod.reference.Reference;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class MaterialRegistrar {
	/* REGISTER MATERIALS */
	public static ItemArmor.ArmorMaterial experimentiumArmorMaterial = null;
	public static Item.ToolMaterial experimentiumToolMaterial = null;
	
	public static void registerMaterials() {
		experimentiumArmorMaterial = EnumHelper.addArmorMaterial("EXPERIMENTIUM", Reference.MODID + ":experimentium", 42, new int[]{3, 6, 8, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3f);
		experimentiumToolMaterial = EnumHelper.addToolMaterial("EXPERIMENTIUM", 3, 2440, 14f, 4f, 26);
	}
}
