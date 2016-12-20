package com.flawedspirit.sandboxmod.block;

import com.flawedspirit.sandboxmod.SandboxMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block {

	protected String unlocalizedName;
	
	public BlockBase(Material material, String unlocalizedName) {
		this(material, unlocalizedName, 3f, 5f);
	}
	
	public BlockBase(Material material, String unlocalizedName, float hardness, float resistance) {
		super(material);
		this.unlocalizedName = unlocalizedName;
		setUnlocalizedName(unlocalizedName);
		setRegistryName(unlocalizedName);
		setHardness(hardness);
		setResistance(resistance);
		setCreativeTab(SandboxMod.creativeTab);
	}
	
	public void registerItemModel(ItemBlock itemBlock) {
		SandboxMod.proxy.registerItemRenderers(itemBlock, 0, this.unlocalizedName);
	}
	
	@Override
	public BlockBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
