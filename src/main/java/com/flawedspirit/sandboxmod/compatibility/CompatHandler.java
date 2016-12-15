package com.flawedspirit.sandboxmod.compatibility;

import com.flawedspirit.sandboxmod.compatibility.top.TOPCompatibility;
import com.flawedspirit.sandboxmod.compatibility.waila.WailaCompatibility;

import net.minecraftforge.fml.common.Loader;

public class CompatHandler {
	public static void registerTOPCompat() {
		if(Loader.isModLoaded("theoneprobe")) {
			TOPCompatibility.register();
		}
	}
	
	public static void registerWailaCompat() {
		if(Loader.isModLoaded("Waila")) {
			WailaCompatibility.register();
		}
	}
}
