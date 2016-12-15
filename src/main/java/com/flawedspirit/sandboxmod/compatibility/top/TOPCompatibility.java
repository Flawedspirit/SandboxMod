package com.flawedspirit.sandboxmod.compatibility.top;

import com.flawedspirit.sandboxmod.reference.Reference;
import com.google.common.base.Function;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class TOPCompatibility {
	
	private static boolean isRegistered;
	
	public static void register() {
		if(!isRegistered) {
			FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe", "com.flawedspirit.sandboxmod.compatibility.top.TOPCompatibility$InitTheOneProbe");
			isRegistered = true;
		}
	}
	
	public static class InitTheOneProbe implements Function<ITheOneProbe, Void> {

		@Override
		public Void apply(ITheOneProbe theOneProbe) {
			theOneProbe.registerProvider(new IProbeInfoProvider() {

				@Override
				public String getID() {
					return Reference.MODID + ":default";
				}

				@Override
				public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world,
						IBlockState blockState, IProbeHitData data) {
					if(blockState.getBlock() instanceof ITOPInfoProvider) {
						ITOPInfoProvider provider = (ITOPInfoProvider) blockState.getBlock();
						provider.addProbeInfo(mode, probeInfo, player, world, blockState, data);
					}
				}
			});
			return null;
		}
		
	}
}
