package net.coreclient.gui;

import org.lwjgl.glfw.GLFW;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WToggleButton;
import io.github.cottonmc.cotton.gui.widget.data.Color;
import net.coreclient.Main;
import net.coreclient.mods.Mod;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

public class ModDisplayGUI extends LightweightGuiDescription {
    
    private static KeyBinding OpenKey;

    static WGridPanel root;

    static int current_cell = 0;

    public ModDisplayGUI()
    {
        current_cell = 0;

        root = new WGridPanel();

        setRootPanel(root);
        root.setSize(300, 200);

        RegisterModOption("Hello World", Main.mods[0], new Runnable() {

            @Override
            public void run() {
                
            }

        });
        RegisterModOption("Blood Particles", Main.mods[1], new Runnable() {

            @Override
            public void run() {
                WGridPanel p = new WGridPanel();

                setRootPanel(p);

                p.setSize(200, 100);

                WLabel label = new WLabel("Blood Particles");

                label.setColor(Color.WHITE.toRgb());

                p.add(label, 1, 1);

                WLabel labeldesc = new WLabel("Spawns blood particles when you hit a mob/player.");

                labeldesc.setColor(Color.WHITE.toRgb());

                p.add(labeldesc, 1, 2);
            }

        });

        RegisterModOption("Keystrokes", Main.mods[2], new Runnable() {

            @Override
            public void run() {
                WGridPanel p = new WGridPanel();

                setRootPanel(p);

                p.setSize(200, 100);

                WLabel label = new WLabel("Keystrokes");

                label.setColor(Color.WHITE.toRgb());

                p.add(label, 1, 1);

                WLabel labeldesc = new WLabel("Shows what keys are being pressed in game on the HUD.");

                labeldesc.setColor(Color.WHITE.toRgb());

                p.add(labeldesc, 1, 2);
            }

        });

/*
        WButton buttonMarker = new WButton();
        buttonMarker.setLabel(Text.of("Add Waypoint"));
        root.add(buttonMarker, 1, 2);

        buttonMarker.setOnClick(new Runnable() {

            @Override
            public void run() {
                waypoint.CreateWaypoint(MinecraftClient.getInstance().player.getPos(), MinecraftClient.getInstance().player.getWorld());
            }

        });
        */
    }

    public static void RegisterModOption(String name, Mod mod, Runnable info)
    {
        WToggleButton modtoggle = new WToggleButton();

        modtoggle.setToggle(mod.GetActive());

        modtoggle.setLabel(Text.of(name));

        mod.SetToggle(modtoggle);

       // WLabel modlabel = new WLabel(name);

        root.add(modtoggle, 1, current_cell + 1);

        //root.add(modlabel, 2, current_cell + 1);

        WButton infobutton = new WButton();
        infobutton.setLabel(Text.of("C"));

        infobutton.setOnClick(info);

        root.add(infobutton, 9, current_cell + 1);

        current_cell++;
    }

    public static void RegisterKeys()
    {
        OpenKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "Open Menu",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "Core Client Menu"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (OpenKey.wasPressed()) {
                client.setScreen(new ModDisplay(new ModDisplayGUI()));
            }
        });
    }

}
