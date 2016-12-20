package com.flawedspirit.sandboxmod.item;

import com.flawedspirit.sandboxmod.SandboxMod;

import net.minecraft.item.Item;

public class ItemBase extends Item {
	
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

	public void registerItemModel(Item item) {
		SandboxMod.proxy.registerItemRenderers(this, 0, this.unlocalizedName);
	}
}
