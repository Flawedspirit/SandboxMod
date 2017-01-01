package com.flawedspirit.sandboxmod.blocks.redstonefurnace;

import com.flawedspirit.sandboxmod.blocks.BlockBaseContainer;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedstoneFurnace extends BlockBaseContainer {

	protected BlockRedstoneFurnace(String unlocalizedName) {
		super(Material.ROCK, unlocalizedName);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, 
			EntityPlayer player, EnumHand hand, ItemStack heldItem,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) {
			// TO BE IMPLEMENTED
			//player.openGui(SandboxMod.instance, 1, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity tile = world.getTileEntity(pos);
		
		if(tile instanceof IInventory) {
			InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
		}
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		
		if(tile instanceof TileEntityRedstoneFurnace) {
			
		}
		return state;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityRedstoneFurnace();
	}
}
