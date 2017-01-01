package com.flawedspirit.sandboxmod.blocks;

import com.flawedspirit.sandboxmod.SandboxMod;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBaseContainer extends BlockContainer {
	
	protected String unlocalizedName;

	protected BlockBaseContainer(Material material, String unlocalizedName) {
		super(material);
		this.unlocalizedName = unlocalizedName;
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
		this.setCreativeTab(SandboxMod.creativeTab);
	}

	public void registerItemModel(ItemBlock itemBlock) {
		SandboxMod.proxy.registerItemRenderers(itemBlock, 0, this.unlocalizedName);
	}
	
	@Override
	public BlockBaseContainer setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}
}
