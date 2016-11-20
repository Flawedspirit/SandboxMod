package com.flawedspirit.sandboxmod.proxy;

import com.flawedspirit.sandboxmod.SandboxMod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerItemRenderers(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(SandboxMod.MODID + ":" + id, "inventory"));
	}
}
