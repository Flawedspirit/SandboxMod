package com.flawedspirit.sandboxmod.block.tile;

import com.flawedspirit.sandboxmod.block.BlockTileEntity;
import com.flawedspirit.sandboxmod.tileentity.TileEntityCounter;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BlockCounter extends BlockTileEntity<TileEntityCounter> {

	public BlockCounter(String unlocalizedName) {
		super(Material.ROCK, unlocalizedName);
	}
	
	@Override
	public Class<TileEntityCounter> getTileEntityClass() {
		return TileEntityCounter.class;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityCounter();
	}

	@Override
	protected TileEntity getTileEntity(World world, BlockPos pos) {
		return (TileEntityCounter) world.getTileEntity(pos);
	}
	
	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state,
    		EntityPlayer player, EnumHand hand, ItemStack heldItem,
    		EnumFacing side, float hitX, float hitY, float hitZ) {
		
		TileEntityCounter tile = (TileEntityCounter) getTileEntity(world, pos);
		
		if(!world.isRemote) {
			if(hitY < 0.5f) {
				tile.decrement();
			} else {
				tile.increment();
			}
			player.addChatComponentMessage(new TextComponentString(TextFormatting.GREEN + "Count: " + TextFormatting.RESET + tile.getCurrentCount()));
		}
		return true;
	}
}
