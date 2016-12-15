package com.flawedspirit.sandboxmod.block.itempedestal;

import com.flawedspirit.sandboxmod.block.BlockTileEntity;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BlockItemPedestal extends BlockTileEntity<TileEntityItemPedestal> {

	public BlockItemPedestal(String unlocalizedName) {
		super(Material.ROCK, unlocalizedName);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState blockState) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState blockState) {
		return false;
	}
	
	public int getRenderType() {
		return -1;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityItemPedestal();
	}
	
	@Override
	public Class<TileEntityItemPedestal> getTileEntityClass() {
		return TileEntityItemPedestal.class;
	}
	
	@Override
	protected TileEntity getTileEntity(World world, BlockPos pos) {
		return (TileEntityItemPedestal) world.getTileEntity(pos);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		
		if(!world.isRemote) {
			TileEntityItemPedestal tile = (TileEntityItemPedestal) world.getTileEntity(pos);
			
			if(!player.isSneaking()) {
				if(heldItem != null) {
					player.setHeldItem(hand, tile.insertItem(heldItem));
				} else {
					player.setHeldItem(hand, tile.removeItem());
				}
				
				if(tile.getStoredItem() != null) {
					player.addChatComponentMessage(new TextComponentString(TextFormatting.GREEN + "Stored item: " + TextFormatting.RESET + tile.getStoredItem().getDisplayName()));
				}
				
				tile.markDirty();
				world.notifyBlockUpdate(pos, state, state, 3);
			}
		}		
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState blockState) {
		
		TileEntityItemPedestal tile = (TileEntityItemPedestal) getTileEntity(world, pos);
		ItemStack stack = tile.getStoredItem();
		
		if(stack != null) {
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			world.spawnEntityInWorld(item);
		}
		super.breakBlock(world, pos, blockState);
	}
}
