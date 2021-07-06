package com.sk89q.worldedit.bukkit.adapter.impl.fawe;

import com.fastasyncworldedit.bukkit.adapter.MapChunkUtil;
import net.minecraft.network.protocol.game.PacketPlayOutMapChunk;

public class MapChunkUtil_1_17_1 extends MapChunkUtil<PacketPlayOutMapChunk> {
    public MapChunkUtil_1_17_1() throws NoSuchFieldException {
        fieldX = PacketPlayOutMapChunk.class.getDeclaredField("a");
        fieldZ = PacketPlayOutMapChunk.class.getDeclaredField("b");
        fieldBitMask = PacketPlayOutMapChunk.class.getDeclaredField("c");
        fieldHeightMap = PacketPlayOutMapChunk.class.getDeclaredField("d");
        fieldChunkData = PacketPlayOutMapChunk.class.getDeclaredField("f");
        fieldBlockEntities = PacketPlayOutMapChunk.class.getDeclaredField("g");
        fieldFull = PacketPlayOutMapChunk.class.getDeclaredField("h");
        fieldX.setAccessible(true);
        fieldZ.setAccessible(true);
        fieldBitMask.setAccessible(true);
        fieldHeightMap.setAccessible(true);
        fieldChunkData.setAccessible(true);
        fieldBlockEntities.setAccessible(true);
        fieldFull.setAccessible(true);
    }

    @Override
    public PacketPlayOutMapChunk createPacket() {
        // TODO ??? return new PacketPlayOutMapChunk();
        throw new UnsupportedOperationException();
    }
}
