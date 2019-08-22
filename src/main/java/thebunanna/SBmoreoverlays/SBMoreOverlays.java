package thebunanna.SBmoreoverlays;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import thebunanna.SBmoreoverlays.config.ConfigHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = SBMoreOverlays.MOD_ID, version = SBMoreOverlays.VERSION, name = SBMoreOverlays.NAME, clientSideOnly = true, dependencies = "after:JEI@[2.28.12.180,);", guiFactory = "thebunanna.moreoverlays.config.GuiFactory")
public class SBMoreOverlays {

    public static final String MOD_ID = "SBmoreoverlays";
    public static final String NAME = "SBMoreOverlays";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "thebunanna.SBmoreoverlays.Proxy")
    public static Proxy proxy;

    public static Logger logger = LogManager.getLogger(NAME);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ConfigHandler.init(event);
        if(proxy!=null)
            proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if(proxy!=null)
            proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        if(proxy!=null)
            proxy.postInit();
    }
}
