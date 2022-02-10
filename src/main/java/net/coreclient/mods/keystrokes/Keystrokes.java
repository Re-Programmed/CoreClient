package net.coreclient.mods.keystrokes;


import java.awt.Label;

import org.checkerframework.checker.units.qual.A;
import org.lwjgl.glfw.GLFW;

import io.github.cottonmc.cotton.gui.client.CottonHud;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.data.Color;
import io.github.cottonmc.cotton.gui.widget.data.Vec2i;
import net.coreclient.mods.Mod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class Keystrokes extends Mod {
    
    WLabel W = null;
    WLabel A = null;
    WLabel S = null;
    WLabel D = null;

    Vec2i parentpos = new Vec2i(15, 15);

    public void RemoveLabels()
    {
        if(W != null && A != null && S != null && D != null)
        {
            CottonHud.remove(W);
            CottonHud.remove(A);
            CottonHud.remove(D);
            CottonHud.remove(S);

            W.setText(Text.of(""));
            A.setText(Text.of(""));
            S.setText(Text.of(""));
            D.setText(Text.of(""));

            W = null;
            A = null;
            S = null;
            D = null;
        }
    }

    @Override
    protected void onDisable() {
        RemoveLabels();
        
    }

    @Override
    protected void onEnable() {
        DrawAllKeys();
    }

    public WLabel DrawToScreen(WLabel label, boolean Pressed, String title, Vec2i location)
    {
        if(label == null)
        {
            label = new WLabel(title);
            label.setSize(50, 50);

            CottonHud.add(label, location.x(), location.y());
        }else{
            label.setLocation(location.x(), location.y());
        }

        if(Pressed)
        {
            label.setColor(Color.GREEN.toRgb());
        }else{
            label.setColor(Color.WHITE.toRgb());
        }
        return label;
    }

    @Override
    public void Update() {
        GetKeys();
    }

    public void GetKeys()
    {
        W = DrawToScreen(W, GLFW.glfwGetKey(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_W) != 0, "W", new Vec2i(10 + parentpos.x(), 10 + parentpos.y()));
        A = DrawToScreen(A, GLFW.glfwGetKey(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_A) != 0, "A", new Vec2i(0 + parentpos.x(), 20 + parentpos.y()));
        S = DrawToScreen(S, GLFW.glfwGetKey(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_S) != 0, "S", new Vec2i(10 + parentpos.x(), 20 + parentpos.y()));
        D = DrawToScreen(D, GLFW.glfwGetKey(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_D) != 0, "D", new Vec2i(20 + parentpos.x(), 20 + parentpos.y()));
    }  

    public Keystrokes DrawAllKeys()
    {
        W = DrawToScreen(W, false, "W", new Vec2i(10 + parentpos.x(), 10 + parentpos.y()));
        A = DrawToScreen(A, false, "A", new Vec2i(0 + parentpos.x(), 20 + parentpos.y()));
        S = DrawToScreen(S, false, "S", new Vec2i(10 + parentpos.x(), 20 + parentpos.y()));
        D = DrawToScreen(D, false, "D", new Vec2i(20 + parentpos.x(), 20 + parentpos.y()));

        return this;
    }

}
