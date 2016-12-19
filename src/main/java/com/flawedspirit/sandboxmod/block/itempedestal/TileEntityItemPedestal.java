package com.flawedspirit.sandboxmod.block.itempedestal;

import javax.annotation.Nullable;

import com.flawedspirit.sandboxmod.block.ITileEntityStorage;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityItemPedestal extends TileEntity implements ITileEntityStorage {

	private static final int MAX_SLOTS = 1;
	
	private ItemStackHandler inventory = new ItemStackHandler(MAX_SLOTS);
	private IItemHandler itemHandler = this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
	
	public ItemStack insertItem(ItemStack stack) {
		return itemHandler.insertItem(0, stack, false);
	}
	
	public ItemStack removeItem() {
		return itemHandler.extractItem(0, 64, false);
	}
	
	public ItemStack getStoredItem() {
		return inventory.getStackInSlot(0);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Nullable
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setTag("inventory", inventory.serializeNBT());
		
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		inventory.deserializeNBT(compound.getCompoundTag("inventory"));
	}
	
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {           
        NBTTagCompound compound = new NBTTagCompound();
        this.writeToNBT(compound);
       
        return new SPacketUpdateTileEntity(pos, getBlockMetadata(), compound);
    }
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound compound = super.getUpdateTag();
		this.writeToNBT(compound);
		
		return compound;
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		this.readFromNBT(packet.getNbtCompound());
	}
}
