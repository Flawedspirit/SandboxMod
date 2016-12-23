package com.flawedspirit.sandboxmod.handlers;

import com.flawedspirit.sandboxmod.handlers.UpdateHandler.UpdateStatus;
import com.flawedspirit.sandboxmod.handlers.UpdateHandler.VersionType;
import com.flawedspirit.sandboxmod.reference.Reference;
import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class EventHandler {
		
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled=true)
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		if(!Reference.IS_DEV) {
			if(ConfigHandler.enableUpdateChecking) {
				if(Reference.LAST_UPDATE_STATE == UpdateStatus.OUTDATED) {
					ChatFormatting versionColor = (Reference.UPDATE_TYPE == VersionType.RELEASE) ? ChatFormatting.BLUE : ChatFormatting.GOLD;
					String append = (Reference.UPDATE_TYPE == VersionType.RELEASE) ? "" : " (beta)";
					event.player.addChatMessage(new TextComponentString(
						"A new version of " + Reference.MODNAME + " is available: " + ChatFormatting.GRAY + "[" + versionColor + Reference.LATEST_VERSION + append + ChatFormatting.GRAY + "]")
					);
				}
			}
		} else {
			// Should hopefully never appear on a release build
			event.player.addChatMessage(new TextComponentString(ChatFormatting.GRAY + "[DEBUG] We are in a dev environment; skipping version checking."));
		}
	}
}
