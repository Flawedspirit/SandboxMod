package com.flawedspirit.sandboxmod.compatibility.waila;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class WailaCompatibility implements IWailaDataProvider {
	
	//private static final WailaCompatability INSTANCE = new WailaCompatability();
	private static boolean isRegistered;
	private static boolean isLoaded;
	
	private WailaCompatibility() {}
	
	public static void load(IWailaRegistrar registrar) {
		
		 if(!isRegistered) {
			 throw new RuntimeException("WAILA compatibility handler not properly registered! I suspect this is a bad thing!");
		 }
		 
		 if(!isLoaded) {
			 //registrar.registerHeadProvider(INSTANCE, DataBlock.class);
			 isLoaded = true;
		 }
	}
	
	public static void register() {
		
		if(!isRegistered) {
			FMLInterModComms.sendMessage("Waila", "register", "com.flawedspirit.sandboxmod.compatibility.waila.WailaCompatibility.load");
			isRegistered = true;
		}
	}

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return null;
	}
	


	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		Block block = accessor.getBlock();
		
		if(block instanceof IWailaInfoProvider) {
			return ((IWailaInfoProvider) block).getWailaBody(itemStack, currenttip, accessor, config);
		}
		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world,
			BlockPos pos) {
		return tag;
	}

}
