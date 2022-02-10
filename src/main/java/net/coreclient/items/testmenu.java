package net.coreclient.items;

import net.coreclient.gui.ModDisplay;
import net.coreclient.gui.ModDisplayGUI;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class testmenu extends Item {

    public testmenu(Settings settings) {
        super(settings);
    }
    
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        MinecraftClient.getInstance().setScreen(new ModDisplay(new ModDisplayGUI()));
        return super.use(world, user, hand);
    }
}
