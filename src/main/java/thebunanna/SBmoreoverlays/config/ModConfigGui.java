package thebunanna.SBmoreoverlays.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import thebunanna.SBmoreoverlays.SBMoreOverlays;

import java.util.ArrayList;
import java.util.List;

public class ModConfigGui extends GuiConfig {
    public ModConfigGui(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(), SBMoreOverlays.MOD_ID, false, false, StatCollector.translateToLocal("gui.config."+ SBMoreOverlays.MOD_ID+".tile"));
    }

    private static List<IConfigElement> getConfigElements(){
        List<IConfigElement> elements = new ArrayList<>();
        for(String category : ConfigHandler.categories){
            elements.add(new ConfigElement(ConfigHandler.config.getCategory(category).setLanguageKey("config."+SBMoreOverlays.MOD_ID+".category."+category)));
        }
        return elements;
    }
}
