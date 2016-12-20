package com.flawedspirit.sandboxmod.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockProfaneStone extends BlockBase {

	public BlockProfaneStone(String unlocalizedName) {
		super(Material.ROCK, unlocalizedName);
		this.setHardness(2.0f);
		this.setResistance(10.0f);
		this.setSoundType(SoundType.STONE);
	}
}
