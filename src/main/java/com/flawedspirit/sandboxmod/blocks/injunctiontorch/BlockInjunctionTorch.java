package com.flawedspirit.sandboxmod.blocks.injunctiontorch;

import java.util.List;
import java.util.Random;

import com.flawedspirit.sandboxmod.blocks.BlockTileEntity;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInjunctionTorch extends BlockTileEntity<TileEntityInjunctionTorch> {

	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB((0.0625 * 4), 0, (0.0625 * 4), (0.0625 * 12), (0.0625 * 15), (0.0625 * 12));
	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB((0.0625 * 5), 0, (0.0625 * 5), (0.0625 * 11), (0.0625 * 14), (0.0625 * 11));
	
	public BlockInjunctionTorch(String unlocalizedName) {
		super(Material.CIRCUITS, unlocalizedName);
		this.setHardness(1.2f);
		this.setLightLevel(1.0f);
		this.setLightOpacity(0);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState blockState) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState blockState) {
		return false;
	}
	
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		float dx = (float)((Math.random() * 4.0d - 2.0d) * 0.125d);
		float dy = (float)((Math.random() * 1.0d - 2.5d) * 0.0625d);
		float dz = (float)((Math.random() * 4.0d - 2.0d) * 0.125d);

		world.spawnParticle(EnumParticleTypes.PORTAL, pos.getX() + 0.5f + dx, pos.getY() + 1 + dy, pos.getZ() + 0.5f + dz, 0.0d, 0.0d, 0.0d, new int[0]);
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
		return new TileEntityInjunctionTorch();
	}

	@Override
	public Class<? extends TileEntity> getTileEntityClass() {
		return TileEntityInjunctionTorch.class;
	}

	@Override
	protected TileEntity getTileEntity(World world, BlockPos pos) {
		return (TileEntityInjunctionTorch) world.getTileEntity(pos);
	}
}
