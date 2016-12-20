package com.flawedspirit.sandboxmod.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockSanctifiedStone extends BlockBase {

	public BlockSanctifiedStone(String unlocalizedName) {
		super(Material.ROCK, unlocalizedName);
		this.setHardness(2.0f);
		this.setResistance(10.0f);
		this.setSoundType(SoundType.STONE);
	}
	
	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		return 9;
	}
}
