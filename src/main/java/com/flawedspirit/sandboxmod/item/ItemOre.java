package com.flawedspirit.sandboxmod.item;

import net.minecraftforge.oredict.OreDictionary;

public class ItemOre extends ItemBase implements IOreDictItem {
	
	private String oreDictName;

	public ItemOre(String unlocalizedName, String oreDictName) {
		super(unlocalizedName);
		this.oreDictName = oreDictName;		
	}

	@Override
	public void initOreDict() {
		OreDictionary.registerOre(oreDictName, this);
	}
}
