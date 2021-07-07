package com.sk89q.worldedit.bukkit.adapter.impl.fawe;

import com.fastasyncworldedit.core.beta.IQueueChunk;
import com.fastasyncworldedit.core.beta.IQueueExtent;
import com.fastasyncworldedit.core.beta.implementation.lighting.NullRelighter;
import com.fastasyncworldedit.core.beta.implementation.lighting.Relighter;
import com.fastasyncworldedit.core.beta.implementation.lighting.RelighterFactory;
import com.fastasyncworldedit.core.object.RelightMode;
import com.sk89q.worldedit.world.World;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.jetbrains.annotations.NotNull;

public class TuinityRelighterFactory_1_17 implements RelighterFactory {

    @Override
    public @NotNull Relighter createRelighter(RelightMode relightMode, World world, IQueueExtent<IQueueChunk> queue) {
        org.bukkit.World w = Bukkit.getWorld(world.getName());
        if (w == null) return NullRelighter.INSTANCE;
        return new TuinityRelighter_1_17(((CraftWorld) w).getHandle(), queue);
    }
}
