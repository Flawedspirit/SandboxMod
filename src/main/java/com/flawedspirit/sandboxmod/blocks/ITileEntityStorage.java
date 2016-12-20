package com.flawedspirit.sandboxmod.blocks;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public interface ITileEntityStorage {
	
	public ItemStack insertItem(ItemStack stack);
	
	public ItemStack removeItem();
	
	public ItemStack getStoredItem();
	
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing);
	
	@Nullable
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing);
	
	public NBTTagCompound writeToNBT(NBTTagCompound compound);
	
	public void readFromNBT(NBTTagCompound compound);
	
	public SPacketUpdateTileEntity getUpdatePacket();
	
	public NBTTagCompound getUpdateTag();
	
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet);
}
