package com.flawedspirit.sandboxmod.item;

import com.flawedspirit.sandboxmod.SandboxMod;

import net.minecraft.item.Item;

public class ItemBase extends Item implements IModelProvider {
	
	protected String name;
	
	public ItemBase(String unlocalizedName) {
		this(unlocalizedName, 64);
	}
	
	public ItemBase(String unlocalizedName, int maxStackSize) {
		this.name = unlocalizedName;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(SandboxMod.creativeTab);
		setMaxStackSize(maxStackSize);
	}

	@Override
	public void registerItemModel(Item item) {
		SandboxMod.proxy.registerItemRenderers(item, 0, name);
	}
}
