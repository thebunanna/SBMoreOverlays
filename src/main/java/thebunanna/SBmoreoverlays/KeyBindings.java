package thebunanna.SBmoreoverlays;

import java.lang.reflect.Field;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thebunanna.SBmoreoverlays.chunkbounds.ChunkBoundsHandler;
import thebunanna.SBmoreoverlays.itemsearch.GuiHandler;
import thebunanna.SBmoreoverlays.itemsearch.JeiModule;
import thebunanna.SBmoreoverlays.lightoverlay.LightOverlayHandler;

public class KeyBindings {

    public static KeyBinding lightOverlay = new KeyBinding("key."+SBMoreOverlays.MOD_ID+".lightoverlay.desc", Keyboard.KEY_F7, "key."+SBMoreOverlays.MOD_ID+".category");
    public static KeyBinding chunkBounds= new KeyBinding("key."+SBMoreOverlays.MOD_ID+".chunkbounds.desc", Keyboard.KEY_F9, "key."+SBMoreOverlays.MOD_ID+".category");
    public static KeyBinding invSearch = new KeyBinding("key."+SBMoreOverlays.MOD_ID+".invsearch.desc", Keyboard.KEY_Z, "key."+SBMoreOverlays.MOD_ID+".category");

    public static void init(){
        ClientRegistry.registerKeyBinding(lightOverlay);
        ClientRegistry.registerKeyBinding(chunkBounds);
        ClientRegistry.registerKeyBinding(invSearch);

        MinecraftForge.EVENT_BUS.register(new KeyBindings());
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(receiveCanceled=true)
    public void onKeyEvent(KeyInputEvent event)
    {
        if(lightOverlay.isPressed()){
            LightOverlayHandler.toggleMode();
        }

        if(chunkBounds.isPressed()){
            ChunkBoundsHandler.toggleMode();
        }

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onGuiKeyEvent(GuiScreenEvent.KeyboardInputEvent.Post event){
        GuiScreen screen = Minecraft.getMinecraft().currentScreen;

        if(Keyboard.isKeyDown(invSearch.getKeyCode()) && Proxy.isJeiInstalled() &&
                (screen instanceof GuiContainer) && !(screen instanceof GuiContainerCreative) && !checkFocus(screen)){
            GuiHandler.toggleMode();
        }
    }

    private static boolean checkFocus(GuiScreen gui){
        //Check JEI Filter Focus
        if(Proxy.isJeiInstalled() && JeiModule.keyableOverlay!=null && JeiModule.keyableOverlay.hasKeyboardFocus())
            return true;

        /*
         * Check Gui Textfield focus
         * It checks every Field in the class if it contains a GuiTextField or a Object that is a instance of GuiTextField
         * Then it makes them Accessible and check if it is focused or not.
         * It should work with every Container. Also with GuiContainers from other mods
         */
        Field[] fields = gui.getClass().getDeclaredFields();
        for(Field field : fields){
            if(GuiTextField.class.isAssignableFrom(field.getType())){
                try {
                    field.setAccessible(true);
                    Object textField = field.get(gui);
                    if(textField !=null && ((GuiTextField)textField).isFocused()) return true;
                } catch (IllegalAccessException ignored) {}
            }
        }

        return false;
    }
}
