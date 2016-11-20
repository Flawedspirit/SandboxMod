/*******************************************************************************
 * Copyright (c) 2016 Flawedspirit.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package com.flawedspirit.sandboxmod.world;

import java.util.Random;

import com.flawedspirit.sandboxmod.registry.BlockRegistrar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class SandboxModWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.getDimension() == 0) {
			registerGeneratedObjects(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
	}

	private void registerGeneratedObjects(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		doOverworldGen(BlockRegistrar.oreExperimentium.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 12, 40, 2 + random.nextInt(3), 10);
	}
	
	private void doOverworldGen(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chance) {
		int deltaY = maxY - minY;
		
		for(int i = 0; i < chance; i++) {
			BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));
			
			WorldGenMinable generator = new WorldGenMinable(ore, size);
			generator.generate(world, random, pos);
		}
	}
}
