package com.flawedspirit.sandboxmod.block;

import com.flawedspirit.sandboxmod.item.IOreDictItem;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.oredict.OreDictionary;

public class BlockOre extends BlockBase implements IOreDictItem {
	
	private String oreDictName;
	
	public BlockOre(String unlocalizedName) {
		this(unlocalizedName, unlocalizedName, 3f, 5f);
	}
	
	public BlockOre(String unlocalizedName, float hardness, float resistance) {
		this(unlocalizedName, unlocalizedName, hardness, resistance);
	}
	
	public BlockOre(String unlocalizedName, String oreDictName, float hardness, float resistance) {
		super(Material.ROCK, unlocalizedName);
		setHardness(hardness);
		setResistance(resistance);
	}
	
	public BlockOre setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public void initOreDict() {
		OreDictionary.registerOre(oreDictName, this);
	}
}
