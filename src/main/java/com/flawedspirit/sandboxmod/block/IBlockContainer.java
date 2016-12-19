package com.flawedspirit.sandboxmod.block;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IBlockContainer {
	
	void addPlayerInventory(IInventory playerInventory);
	
	void addContainerInventory();
	
	@Nullable
	ItemStack transferStackInSlot(EntityPlayer player, int index);
	
	boolean canInteractWith(EntityPlayer player);}
