package net.coreclient.mods;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class TestMod extends Mod {
    
    @Override
    public void Update() {
        MinecraftClient.getInstance().player.sendMessage(Text.of("Hello World"), false);
        super.Update();
    }

}
