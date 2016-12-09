/*******************************************************************************
 * Copyright (c) 2016 Flawedspirit.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package com.flawedspirit.sandboxmod.item;

import net.minecraftforge.oredict.OreDictionary;

public class ItemOre extends ItemBase implements IOreDictItem {
	
	private String oreDictName;

	public ItemOre(String unlocalizedName, String oreDictName) {
		super(unlocalizedName);
		this.oreDictName = oreDictName;		
	}

	@Override
	public void registerOreDict() {
		OreDictionary.registerOre(oreDictName, this);
	}
}
