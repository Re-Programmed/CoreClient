package net.coreclient.mods;

import io.github.cottonmc.cotton.gui.widget.WToggleButton;
import net.minecraft.entity.Entity;

public abstract class Mod {

    protected boolean active = false;
    
    WToggleButton toggleButton = null;
    
    public Mod()
    {
        Start();
    }

    public void Call_EntityAttack(Entity entity)
    {
        if(active)
        {
            EntityAttack(entity);
        }
    }

    public void EntityAttack(Entity entity)
    {

    }

    public boolean GetActive()
    {
        return active;
    }

    public void SetToggle(WToggleButton toggle)
    {
        toggleButton = toggle;
    }

    public void SetActive(boolean active)
    {
        if(this.active == active){return;}
        this.active = active;

        if(active)
        {
            onEnable();
        }else{
            onDisable();
        }
    }

    protected void onDisable()
    {

    }

    protected void onEnable()
    {

    } 

    public void Call_Update()
    {

        if(toggleButton != null)
        {
            SetActive(toggleButton.getToggle());
        }

        if(active)
        {
            Update();
        }
    }

    public void Update()
    {

    }

    public void Start()
    {

    }

}
