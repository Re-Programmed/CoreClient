package net.coreclient.mods;

import java.util.Random;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;


public class BloodParticles extends Mod {
    
    @Override
    public void EntityAttack(Entity entity) {
        LivingEntity attacked = null;
        if(entity instanceof LivingEntity)
        {
            attacked = (LivingEntity)entity;
        }else{
            return;
        }
        Random rand = new Random();
        for(int i = 0; i < ((attacked.getMaxHealth() - attacked.getHealth()) * 10) + 10; i++)
        {
            attacked.world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.getDefaultState()), attacked.getX(), attacked.getY(), attacked.getZ(), rand.nextInt(50)-25, 2, rand.nextInt(50)-25);
            attacked.world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.getDefaultState()), attacked.getX(), attacked.getY(), attacked.getZ(), rand.nextInt(50)-25, 2, rand.nextInt(50)-25);
        }
        for(int i = 0; i < attacked.getMaxHealth() - attacked.getHealth(); i++)
        {
            attacked.world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.RED_CONCRETE.getDefaultState()), attacked.getX(), attacked.getY(), attacked.getZ(), rand.nextInt(50)-25, 2, rand.nextInt(50)-25);
            attacked.world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.RED_CONCRETE.getDefaultState()), attacked.getX(), attacked.getY(), attacked.getZ(), rand.nextInt(50)-25, 2, rand.nextInt(50)-25);
        }
    }

}
