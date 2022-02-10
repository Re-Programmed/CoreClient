package net.coreclient.mods.cps;

import io.github.cottonmc.cotton.gui.client.CottonHud;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.data.Vec2i;
import net.minecraft.text.Text;

public class CpsModUI {
    
    public static Vec2i parentpos = new Vec2i(15, 40);

    static WLabel label;
    public static void DrawCPSDisplay(int cps)
    {
        if(label != null){return;}
        WLabel cpslabel = new WLabel(cps + " CPS");
        
        Vec2i pos = new Vec2i(10 + parentpos.x(), 10 + parentpos.y());

        int x = pos.x();
        int y = pos.y();

        CottonHud.add(cpslabel, x, y);

        label = cpslabel;
    }

    public static void RemoveCPSDisplay()
    {
        if(label == null){return;}
        CottonHud.remove(label);
        label = null;
    }

    public static void UpdateCPSDisplay(int cps)
    {
        if(label == null){return;}
        label.setText(Text.of(cps + " CPS"));
    }

}
