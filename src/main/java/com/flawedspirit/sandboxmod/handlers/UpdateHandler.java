package com.flawedspirit.sandboxmod.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.logging.log4j.Level;

import com.flawedspirit.sandboxmod.SandboxMod;
import com.flawedspirit.sandboxmod.reference.Reference;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UpdateHandler {
	
	public static String REMOTE = "http://minecraft.flawedspirit.com/" + Reference.MODID + ".json";
	public static UpdateStatus versionStatus = UpdateStatus.CURRENT;
	
	//debug move somewhere else
	public static VersionType type = VersionType.RELEASE;
	
	public enum VersionType {
		RELEASE,
		BETA
	}
	
	public enum UpdateStatus {
		CURRENT,
		OUTDATED,
		COMMERROR
	}
	
	public static String getUpdate() {
		return getRemoteVersion(REMOTE);
	}
	
	private static String getRemoteVersion(String location) {
		try {
			URL url = new URL(location);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			JsonParser parser = new JsonParser();
			
			int read;
			char[] chars = new char[1024];
			
			while((read = reader.read(chars)) != -1) {
				buffer.append(chars, 0, read);
			}
			
			JsonElement json = parser.parse(buffer.toString());
			
			if(json.isJsonObject()) {
				JsonObject jsonObject = json.getAsJsonObject();
				JsonObject root = jsonObject.getAsJsonObject("update");
				
				if(root.isJsonObject()) {
					JsonObject version = (type == VersionType.RELEASE) ? root.getAsJsonObject("release") : root.getAsJsonObject("beta");
					
					if(version.isJsonObject()) {
						JsonElement versionString = version.get("version");
						return versionString.toString().replace("\"", "");
					}
				}
			}
		} catch(IOException ex) {
			SandboxMod.logger.log(Level.WARN, "Unable to query remote version authority: " + ex.getMessage());
			versionStatus = UpdateStatus.COMMERROR;
		}
		return null;
	}
}
