package com.flawedspirit.sandboxmod.block.counter;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCounter extends TileEntity {
	private int count;
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("count", count);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		count = compound.getInteger("count");
		super.readFromNBT(compound);
	}
	
	public int getCurrentCount() {
		return count;
	}
	
	public void increment() {
		count++;
	}
	
	public void decrement() {
		count--;
	}
}
