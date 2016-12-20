package com.flawedspirit.sandboxmod.blocks;

import com.flawedspirit.sandboxmod.items.IOreDictItem;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.oredict.OreDictionary;

public class BlockOre extends BlockBase implements IOreDictItem {
	
	protected String oreDictName;
	
	public BlockOre(String unlocalizedName) {
		this(unlocalizedName, unlocalizedName, 3f, 5f);
	}
	
	public BlockOre(String unlocalizedName, String oreDictName) {
		this(unlocalizedName, oreDictName, 3f, 5f);
	}
	
	public BlockOre(String unlocalizedName, String oreDictName, float hardness, float resistance) {
		super(Material.ROCK, unlocalizedName);
		this.oreDictName = oreDictName;
		setHardness(hardness);
		setResistance(resistance);
	}
	
	@Override
	public BlockOre setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public void registerOreDict() {
		OreDictionary.registerOre(this.oreDictName, this);
	}
}
