package com.flawedspirit.sandboxmod.handlers;

import com.flawedspirit.sandboxmod.reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.ForgeVersion.CheckResult;
import net.minecraftforge.common.ForgeVersion.Status;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class UpdateHandler {
	
	public static CheckResult updateCheckResult;
	
	private static boolean notified = false;
	private static int ticksBeforeNotify;

	public UpdateHandler() {
		if(!Reference.IS_DEV && ConfigHandler.enableUpdateChecking) {
			MinecraftForge.EVENT_BUS.register(this);
		}
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onTick(TickEvent.ClientTickEvent event) {
		
		if(!Reference.IS_DEV && ConfigHandler.enableUpdateChecking) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			
			if(!notified && player != null) {
				ticksBeforeNotify++;
				
				if(ticksBeforeNotify >= 200 && event.side.isClient()) {
					MinecraftForge.EVENT_BUS.unregister(this);
					
					if(updateCheckResult.status == Status.OUTDATED) {
						player.addChatComponentMessage(getUpdateNotice(updateCheckResult));
						
						notified = true;
						ticksBeforeNotify = 0;
					}
				}
			}
		}
	}
	
	public static CheckResult getUpdateStatus() {
		Object thisMod = Loader.instance().getIndexedModList().get(Reference.MODID);
		
		return ForgeVersion.getResult((ModContainer) thisMod);
	}
	
	private static ITextComponent getUpdateNotice(CheckResult result) {
		
		String banner = "[\"" + TextFormatting.GOLD + "A new version of " + Reference.MODNAME + " is available!" + TextFormatting.RESET + "\n";
		String versions = "Current version: " + TextFormatting.RED + Reference.VERSION + TextFormatting.RESET + "; New version: " + TextFormatting.AQUA + result.target + TextFormatting.RESET + "§r\n[\",";
		String changelogComponent = "{\"text\":\"" + TextFormatting.AQUA + "Changelog" + TextFormatting.RESET + "\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"" + "https://flawedspirit.com" + "\"}},";
		String downloadComponent = "{\"text\":\"" + TextFormatting.AQUA + "Download" + TextFormatting.RESET + "\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"" + "https://flawedspirit.com" + "\"}},";
		String linkComponent = changelogComponent + "\"] [\"," + downloadComponent + "\"]\"]";
		
		String message = banner + versions + linkComponent;
		
		return ITextComponent.Serializer.jsonToComponent(message);
	}
}
