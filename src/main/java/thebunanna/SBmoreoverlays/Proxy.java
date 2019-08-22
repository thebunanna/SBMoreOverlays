package thebunanna.SBmoreoverlays;

import net.minecraftforge.fml.common.Loader;
import thebunanna.SBmoreoverlays.chunkbounds.ChunkBoundsHandler;
import thebunanna.SBmoreoverlays.itemsearch.GuiHandler;
import thebunanna.SBmoreoverlays.lightoverlay.LightOverlayHandler;

public class Proxy {

    private static boolean enable_jei=false;

    public void preInit(){
        enable_jei = Loader.isModLoaded("JEI");

        KeyBindings.init();

        LightOverlayHandler.init();
        ChunkBoundsHandler.init();
        GuiHandler.init();
    }

    public void init(){

    }

    public void postInit(){

    }

    public static boolean isJeiInstalled(){
        return enable_jei;
    }
}
