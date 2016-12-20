package com.flawedspirit.sandboxmod.block.obsidianpressureplate;

import java.util.Iterator;
import java.util.List;

import com.flawedspirit.sandboxmod.SandboxMod;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockObsidianPressurePlate extends BlockPressurePlate {

	protected String unlocalizedName;

	public BlockObsidianPressurePlate(String unlocalizedName) {
		super(Material.ROCK, Sensitivity.MOBS);
		this.unlocalizedName = unlocalizedName;
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
		this.setSoundType(SoundType.STONE);
		this.setHardness(0.5f);
		this.disableStats();
		this.setCreativeTab(SandboxMod.creativeTab);
	}

	@Override
	protected int computeRedstoneStrength(World world, BlockPos pos) {
		AxisAlignedBB boundingBox = PRESSURE_AABB.offset(pos);
		List<? extends Entity> list = world.getEntitiesWithinAABBExcludingEntity((Entity) null, boundingBox);
		
		if(list != null && !list.isEmpty()) {
			Iterator<? extends Entity> iterator = list.iterator();
			
			while(iterator.hasNext()) {
				Object entity = iterator.next();
				
				if(entity instanceof EntityPlayer) {
					return 15;	
				}
			}
		}
		return 0;
	}
	
	public void registerItemModel(ItemBlock itemBlock) {
		SandboxMod.proxy.registerItemRenderers(itemBlock, 0, this.unlocalizedName);
	}
}
