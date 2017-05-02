package com.picatrix1899.bt;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;


@MCVersion(value = "1.11.2")
public class BTFMLLoadingPlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getASMTransformerClass() {
		//This will return the name of the class "mod.culegooner.ExplosionDropsCore.EDClassTransformer"
		return new String[]{BTClassTransformer.class.getName()};
	}

	@Override
	public String getModContainerClass() {
		//This is the name of our dummy container "mod.culegooner.ExplosionDropsCore.EDDummyContainer"
		return BTDummyContainer.class.getName();
	}

	@Override
	public String getSetupClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		
	}

	@Override
	public String getAccessTransformerClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
