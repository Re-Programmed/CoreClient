package net.coreclient;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.coreclient.gui.ModDisplayGUI;
import net.coreclient.mods.BloodParticles;
import net.coreclient.mods.Mod;
import net.coreclient.mods.TestMod;
import net.coreclient.mods.cps.CpsMod;
import net.coreclient.mods.keystrokes.Keystrokes;
import net.coreclient.waypoints.waypoint;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.ActionResult;

public class Main implements ModInitializer {

    public static Mod[] mods = {new TestMod(), new BloodParticles(), new Keystrokes(), new CpsMod()};

    @Override
    public void onInitialize() {

        AttackEntityCallback.EVENT.register((player, world, hand, entity, result) -> 
        {
           if(player.getName().asString() == MinecraftClient.getInstance().player.getName().asString())
           {
                for(Mod mod : mods)
                {
                    mod.Call_EntityAttack(entity);
                }
           }
            return ActionResult.PASS;
        });

        ModDisplayGUI.RegisterKeys();

        Runnable loop = new Runnable() {
            public void run() {
                Update();
            }
        };
        
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(loop, 0, 100, TimeUnit.MILLISECONDS);
    }

    void Update()
    {
        if(MinecraftClient.getInstance() != null)
            {
                if(MinecraftClient.getInstance().player != null)
                {
                    ClientPlayerEntity cpe = MinecraftClient.getInstance().player;
                    
                    for(Mod m : mods)
                    {
                        m.Call_Update();
                    }

                    waypoint.UpdateAllWaypoints();
                }
            }
    }
    
}
