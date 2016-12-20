package com.flawedspirit.sandboxmod.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.flawedspirit.sandboxmod.reference.Reference;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UpdateHandler {
	
	public static String REMOTE = Reference.VERSION_AUTH + Reference.MODID + ".json";
	
	public enum VersionType {
		RELEASE,
		BETA
	}
	
	public enum UpdateStatus {
		CURRENT,
		OUTDATED,
		COMMERROR
	}
	
	public static String getUpdate(VersionType versionType) {
		return getRemoteVersion(REMOTE, versionType);
	}
	
	private static String getRemoteVersion(String location, VersionType versionType) {
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
					JsonObject version = (versionType == VersionType.RELEASE) ? root.getAsJsonObject("release") : root.getAsJsonObject("beta");
					
					if(version.isJsonObject()) {
						JsonElement versionString = version.get("version");
						return versionString.toString().replace("\"", "");
					}
				}
			}
		} catch(IOException ex) {
			ex.printStackTrace();
			Reference.LAST_UPDATE_STATE = UpdateStatus.COMMERROR;
		}
		return null;
	}
	
	public static String compareVersions(String modVersion, String remoteVersion) {
		boolean majorHasUpdate = false,
			minorHasUpdate = false,
			patchHasUpdate = false;
		
		String[] modVersionParts = modVersion.split("\\.|-");
		String[] remoteVersionParts = remoteVersion.split("\\.|-");
		
		if(Integer.parseInt(modVersionParts[0]) < Integer.parseInt(remoteVersionParts[0])) {
			majorHasUpdate = true;
		}
		
		if(Integer.parseInt(modVersionParts[1]) < Integer.parseInt(remoteVersionParts[1])) {
			minorHasUpdate = true;
		}
		
		if(Integer.parseInt(modVersionParts[2]) < Integer.parseInt(remoteVersionParts[2])) {
			patchHasUpdate = true;
		}
		
		if(majorHasUpdate || minorHasUpdate || patchHasUpdate) {
			Reference.LAST_UPDATE_STATE = UpdateStatus.OUTDATED;
			return remoteVersion;
		}
		Reference.LAST_UPDATE_STATE = UpdateStatus.CURRENT;
		return modVersion;
	}
}
