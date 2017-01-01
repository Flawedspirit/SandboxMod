package com.flawedspirit.sandboxmod.reference;

public class Reference {
	public static final String MODID        = "sandboxmod";
	public static final String MODNAME      = "Sandbox Mod";
	public static final String VERSION      = "@VERSION@";
	public static final String MC_VERSION   = "[1.10.2,)";
	public static final String UPDATE_URL   = "https://gist.githubusercontent.com/Flawedspirit/9d3bdcf7f2c0967ad6c9b549f8cbf0a2/raw/eb789675116784aeb48c74b2a736a52e592f1577/" + MODID + ".json";
	public static final String DEPENDANCIES = "required-after:Forge@[12.18.2.2099,)";
	public static final int    GUI_ID       = 7263;
	
	public static final String COMMON_PROXY = "com.flawedspirit.sandboxmod.common.CommonProxy";
	public static final String CLIENT_PROXY = "com.flawedspirit.sandboxmod.client.ClientProxy";
	
	public static boolean      IS_DEV       = false;
}
