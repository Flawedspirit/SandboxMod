package com.flawedspirit.sandboxmod;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import com.flawedspirit.sandboxmod.common.CommonProxy;
import com.flawedspirit.sandboxmod.handlers.UpdateHandler;
import com.flawedspirit.sandboxmod.reference.Reference;
import com.flawedspirit.sandboxmod.registry.ItemRegistrar;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID,
	name = Reference.MODNAME, 
	version = Reference.VERSION,
	updateJSON = Reference.UPDATE_URL,
	acceptedMinecraftVersions = Reference.MC_VERSION,
	dependencies = Reference.DEPENDANCIES
)
public class SandboxMod {

	@Instance(Reference.MODID)
	public static SandboxMod instance;
	
	public static Logger logger;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;
	
	public static CreativeTabs creativeTab = new CreativeTabs(Reference.MODID) {
		@Override
		public Item getTabIconItem() {
			return ItemRegistrar.ingotExperimentium;
		}
	};
	
	@Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		new UpdateHandler();
		
		proxy.preInit(event);
    }
        
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    	proxy.init(event);
    }
        
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.postInit(event);
    	logger.log(Level.INFO, Reference.MODNAME + " has finished initializing.");
    }
}
