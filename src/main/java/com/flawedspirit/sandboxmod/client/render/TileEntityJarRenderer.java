package com.flawedspirit.sandboxmod.client.render;

import com.flawedspirit.sandboxmod.block.jar.TileEntityJar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class TileEntityJarRenderer extends TileEntitySpecialRenderer<TileEntityJar> {
	
	@Override
	public void renderTileEntityAt(TileEntityJar tile, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(tile, x, y, z, partialTicks, destroyStage);
		
		if(tile.getStoredItem() != null) {
			ItemStack stack = tile.getStoredItem();
			EntityItem item = new EntityItem(tile.getWorld(), 0.0d, 0.0d, 0.0d, stack);
			
			item.hoverStart = 0.0f;
			item.getEntityItem().stackSize = stack.stackSize;			
			
			GlStateManager.pushMatrix();
			
			float yDelta = MathHelper.sin((System.currentTimeMillis() % 86400000) / 1200f) * 0.025f + 0.1f;
			float angleDelta = ((System.currentTimeMillis() % 86400000) / 2000f) * (180f / (float) Math.PI);
			
			GlStateManager.translate((float) x + 0.5f, (float) y - 0.1d + yDelta, (float) z + 0.5f);
			GlStateManager.rotate(angleDelta, 0.0f, 1.0f, 0.0f);
			GlStateManager.scale(0.75f, 0.75f, 0.75f);
			
			Minecraft.getMinecraft().getRenderManager().doRenderEntity(item, 0.0d, 0.0d, 0.0d, 0.0f, 0.0f, false);
				
			GlStateManager.popMatrix();
		}
	}
}
