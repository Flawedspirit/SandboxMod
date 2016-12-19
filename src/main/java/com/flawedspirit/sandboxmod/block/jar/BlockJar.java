package com.flawedspirit.sandboxmod.block.jar;

import java.util.List;

import com.flawedspirit.sandboxmod.block.BlockTileEntity;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockJar extends BlockTileEntity<TileEntityJar> {
	
	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB((0.0625 * 3), 0, (0.0625 * 3), (0.0625 * 13), (0.0625 * 13), (0.0625 * 13));
	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB((0.0625 * 4), 0, (0.0625 * 4), (0.0625 * 12), (0.0625 * 12), (0.0625 * 12));
	protected static final int GUI_ID = 1;

	public BlockJar(String unlocalizedName) {
		super(Material.GLASS, unlocalizedName);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		
		if(!world.isRemote) {
			TileEntityJar tile = (TileEntityJar) world.getTileEntity(pos);
			
			if(!player.isSneaking()) {
				if(heldItem != null) {
					player.setHeldItem(hand, tile.insertItem(heldItem));
				} else {
					player.setHeldItem(hand, tile.removeItem());
				}				
				tile.markDirty();
				world.notifyBlockUpdate(pos, state, state, 3);
			}
		}		
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState blockState) {
		
		TileEntityJar tile = (TileEntityJar) getTileEntity(world, pos);
		ItemStack stack = tile.getStoredItem();
		
		if(stack != null) {
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			world.spawnEntityInWorld(item);
		}
		super.breakBlock(world, pos, blockState);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState blockState) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState blockState) {
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOUNDING_BOX;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entity) {
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION_BOX);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityJar();
	}

	@Override
	public Class<TileEntityJar> getTileEntityClass() {
		return TileEntityJar.class;
	}

	@Override
	protected TileEntity getTileEntity(World world, BlockPos pos) {
		return (TileEntityJar) world.getTileEntity(pos);
	}
}
