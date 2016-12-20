package com.flawedspirit.sandboxmod.reference;

import com.flawedspirit.sandboxmod.handlers.UpdateHandler.UpdateStatus;
import com.flawedspirit.sandboxmod.handlers.UpdateHandler.VersionType;

public class Reference {
	public static final String MODID = "sandboxmod";
	public static final String MODNAME = "Sandbox Mod";
	public static final String VERSION = "@VERSION@";
	public static final String DEPENDANCIES = "required-after:Forge@[12.18.2.2099,)";
	
	public static final String COMMON_PROXY = "com.flawedspirit.sandboxmod.common.CommonProxy";
	public static final String CLIENT_PROXY = "com.flawedspirit.sandboxmod.client.ClientProxy";
	
	public static boolean IS_DEV = false;
	
	public static final String VERSION_AUTH = "http://minecraft.flawedspirit.com/";
	
	// Non-static version info
	public static VersionType UPDATE_TYPE = VersionType.RELEASE;
	public static UpdateStatus LAST_UPDATE_STATE = UpdateStatus.CURRENT;
	public static String LATEST_VERSION = VERSION;
}
