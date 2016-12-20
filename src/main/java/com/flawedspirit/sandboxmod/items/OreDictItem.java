package com.flawedspirit.sandboxmod.items;

import net.minecraftforge.oredict.OreDictionary;

public class OreDictItem extends ItemBase implements IOreDictItem {
	
	protected String oreDictName;
	
	public OreDictItem(String unlocalizedName) {
		this(unlocalizedName, unlocalizedName, 64);
	}
	
	public OreDictItem(String unlocalizedName, String oreDictName) {
		this(unlocalizedName, oreDictName, 64);
	}

	public OreDictItem(String unlocalizedName, String oreDictName, int maxStackSize) {
		super(unlocalizedName, maxStackSize);
		this.oreDictName = oreDictName;
	}
	
	@Override
	public void registerOreDict() {
		OreDictionary.registerOre(this.oreDictName, this);		
	}
}
