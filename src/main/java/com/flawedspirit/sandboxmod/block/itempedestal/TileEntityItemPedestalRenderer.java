package com.flawedspirit.sandboxmod.block.itempedestal;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class TileEntityItemPedestalRenderer extends TileEntitySpecialRenderer<TileEntityItemPedestal> {
	
	@Override
	public void renderTileEntityAt(TileEntityItemPedestal tile, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(tile, x, y, z, partialTicks, destroyStage);
		
		if(tile.getStoredItem() != null) { // To be fixed
			ItemStack stack = tile.getStoredItem();
			EntityItem item = new EntityItem(tile.getWorld(), 0.0d, 0.0d, 0.0d, stack);
			
			item.hoverStart = 0.0f;
			item.getEntityItem().stackSize = 1;
			RenderHelper.enableStandardItemLighting();
			GlStateManager.enableLighting();
			
			GlStateManager.pushMatrix();
			{
				float yDelta = MathHelper.sin((System.currentTimeMillis() % 86400000) / 1200f) * 0.05f + 0.05f;
				float angleDelta = ((System.currentTimeMillis() % 86400000) / 2000f) * (180f / (float) Math.PI);
				
				GlStateManager.translate((float) x + 0.5f, (float) y + 0.9f + yDelta, (float) z + 0.5f);
				GlStateManager.rotate(angleDelta, 0.0f, 1.0f, 0.0f);
				
				Minecraft.getMinecraft().getRenderManager().doRenderEntity(item, 0.0d, 0.0d, 0.0d, 0.0f, 0.0f, false);	
			}
			GlStateManager.popMatrix();
		}
	}
}
