package com.iesika.ComplexControl;

import li.cil.oc.api.Driver;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = ComplexControl.MODID, version = ComplexControl.VERSION)
public class ComplexControl
{
    public static final String MODID = "ComplexControl";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	Driver.add(new DriverIC2Crop());
    	Driver.add(new DriverWindKineticGenerator());
    }
}
