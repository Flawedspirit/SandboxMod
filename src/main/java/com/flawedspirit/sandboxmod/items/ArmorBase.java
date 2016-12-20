package com.flawedspirit.sandboxmod.items;

import com.flawedspirit.sandboxmod.SandboxMod;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public class ArmorBase extends ItemArmor {
	
	protected String unlocalizedName;

	public ArmorBase(String unlocalizedName, ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, 0, equipmentSlotIn);
		this.unlocalizedName = unlocalizedName;
		setUnlocalizedName(unlocalizedName);
		setRegistryName(unlocalizedName);
		setCreativeTab(SandboxMod.creativeTab);
	}

	public void registerItemModel(Item item) {
		SandboxMod.proxy.registerItemRenderers(this, 0, unlocalizedName);
	}
}
