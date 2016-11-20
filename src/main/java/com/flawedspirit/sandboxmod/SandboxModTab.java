package com.flawedspirit.sandboxmod;

import com.flawedspirit.sandboxmod.registry.ItemRegistrar;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SandboxModTab extends CreativeTabs {

	public SandboxModTab() {
		super(SandboxMod.MODID);
	}

	@Override
	public Item getTabIconItem() {
		return ItemRegistrar.ingotExperimentium;
	}
}
