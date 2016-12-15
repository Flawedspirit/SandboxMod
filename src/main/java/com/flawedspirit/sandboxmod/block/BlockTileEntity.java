package com.flawedspirit.sandboxmod.block;

import javax.annotation.Nullable;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockTileEntity<TE extends TileEntity> extends BlockBase implements ITileEntityProvider {

	public BlockTileEntity(Material material, String unlocalizedName) {
		super(material, unlocalizedName);
	}
	
	@Override
	@Nullable
	public abstract TileEntity createNewTileEntity(World world, int meta);
	
	public abstract Class<? extends TileEntity> getTileEntityClass();
	
	protected abstract TileEntity getTileEntity(World world, BlockPos pos);
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
}
