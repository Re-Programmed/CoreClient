package net.coreclient.waypoints;

import java.util.ArrayList;

import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class waypoint {

    public static ArrayList<waypoint> waypoints = new ArrayList<waypoint>(); 

    public final Position location;
    public final World world;

    public waypoint(Position location, World world)
    {
        this.location = location;
        this.world = world;
    }

    public static void CreateWaypoint(Position loc, World world)
    {
        waypoints.add(new waypoint(loc, world));
    }

    public static void UpdateAllWaypoints()
    {
        for(waypoint point : waypoints)
        {
            UpdateWaypoint(point);
        }
    }

    public static void UpdateWaypoint(waypoint point)
    {
        for(int y = 0; y < 319; y++)
        {
            point.world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.BLUE_CONCRETE.getDefaultState()), point.location.getX(), y, point.location.getZ(), 0, 256, 0);
        }
    }
    
}
