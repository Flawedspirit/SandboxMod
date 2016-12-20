package com.flawedspirit.sandboxmod.blocks.counter;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCounter extends TileEntity {
	private int count;
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("count", count);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		count = compound.getInteger("count");
	}
	
	public int getCurrentCount() {
		return count;
	}
	
	public void increment() {
		count++;
		markDirty();
	}
	
	public void decrement() {
		count--;
		markDirty();
	}
}
