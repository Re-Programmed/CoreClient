package net.coreclient.mods.cps;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.coreclient.mods.Mod;

import net.minecraft.client.MinecraftClient;

public class CpsMod extends Mod {

    boolean mouseHeld = false;

    ArrayList<Integer> cpsLogs = new ArrayList<Integer>(); 

    int currentclicks = 0;

    @Override
    public void Update() {
        ClickCheck();
    }

    public void ClickCheck()
    {
        if(!mouseHeld)
        {
            if(MinecraftClient.getInstance().mouse.wasLeftButtonClicked())
            {
                mouseHeld = true;

                currentclicks++;

            }else{
                mouseHeld = false;
            }
        }
    }

    @Override
    protected void onEnable()
    {
        cpsLogs.clear();
        CpsModUI.DrawCPSDisplay(0);
    }

    @Override
    protected void onDisable()
     {
        cpsLogs.clear();
        CpsModUI.RemoveCPSDisplay();
    }

    public void InitClickRegistry()
    {
        Runnable loop = new Runnable() {
            public void run() {
                if(active)
                {
                    cpsLogs.add(currentclicks);

                    int added = 0;

                    for(int i : cpsLogs)
                    {
                        added += i;
                    }

                    CpsModUI.UpdateCPSDisplay(added/cpsLogs.size());

                    currentclicks = 0;
                }
            }
        };
        
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(loop, 1, 1, TimeUnit.SECONDS);
       
    }

}
