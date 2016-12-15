package com.flawedspirit.sandboxmod.compatability;

import com.flawedspirit.sandboxmod.compatability.top.TOPCompatability;
import com.flawedspirit.sandboxmod.compatability.waila.WailaCompatability;

import net.minecraftforge.fml.common.Loader;

public class CompatHandler {
	public static void registerTOPCompat() {
		if(Loader.isModLoaded("theoneprobe")) {
			TOPCompatability.register();
		}
	}
	
	public static void registerWailaCompat() {
		if(Loader.isModLoaded("Waila")) {
			WailaCompatability.register();
		}
	}
}
