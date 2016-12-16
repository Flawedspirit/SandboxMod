package com.flawedspirit.sandboxmod.block.counter;

import java.util.List;

import com.flawedspirit.sandboxmod.block.BlockTileEntity;
import com.flawedspirit.sandboxmod.compatibility.top.ITOPInfoProvider;
import com.flawedspirit.sandboxmod.compatibility.waila.IWailaInfoProvider;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BlockCounter extends BlockTileEntity<TileEntityCounter> implements ITOPInfoProvider, IWailaInfoProvider {

	public BlockCounter(String unlocalizedName) {
		super(Material.ROCK, unlocalizedName);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityCounter();
	}
	
	@Override
	public Class<TileEntityCounter> getTileEntityClass() {
		return TileEntityCounter.class;
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
			tile.markDirty();
			player.addChatComponentMessage(new TextComponentString(TextFormatting.GREEN + "Count: " + TextFormatting.WHITE + tile.getCurrentCount()));
		}
		return true;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		
		TileEntity tile = accessor.getTileEntity();
		
		if(tile instanceof TileEntityCounter) {
			TileEntityCounter thisTile = (TileEntityCounter) tile;
			currenttip.add(TextFormatting.GRAY + "Count: " + thisTile.getCurrentCount());
		}
		return currenttip;
	}

	@Override
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world,
			IBlockState blockState, IProbeHitData data) {
		
		TileEntity tile = world.getTileEntity(data.getPos());
		
		if(tile instanceof TileEntityCounter) {
			TileEntityCounter thisTile = (TileEntityCounter) tile;
			
			probeInfo.text(TextFormatting.GRAY + "Why is this thing ticking?");
			probeInfo.horizontal().item(new ItemStack(Items.CLOCK)).text(TextFormatting.GREEN + "Count: " + TextFormatting.WHITE + thisTile.getCurrentCount());
		}
	}
}
