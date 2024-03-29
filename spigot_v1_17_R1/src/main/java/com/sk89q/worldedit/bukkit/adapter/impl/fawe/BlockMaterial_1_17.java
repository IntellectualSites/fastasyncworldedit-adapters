package com.sk89q.worldedit.bukkit.adapter.impl.fawe;

import com.google.common.base.Suppliers;
import com.sk89q.jnbt.CompoundTag;
import com.sk89q.util.ReflectionUtil;
import com.sk89q.worldedit.bukkit.adapter.impl.fawe.nbt.LazyCompoundTag_1_17;
import com.sk89q.worldedit.world.registry.BlockMaterial;
import net.minecraft.core.BlockPosition;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.level.BlockAccessAir;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ITileEntity;
import net.minecraft.world.level.block.entity.TileEntity;
import net.minecraft.world.level.block.state.BlockBase;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.level.material.EnumPistonReaction;
import net.minecraft.world.level.material.Material;
import org.bukkit.craftbukkit.v1_17_R1.block.data.CraftBlockData;

public class BlockMaterial_1_17 implements BlockMaterial {

    private final Block block;
    private final IBlockData defaultState;
    private final Material material;
    private final boolean isTranslucent;
    private final CraftBlockData craftBlockData;
    private final org.bukkit.Material craftMaterial;
    private final int opacity;
    private final CompoundTag tile;

    public BlockMaterial_1_17(Block block) {
        this(block, block.getBlockData());
    }

    public BlockMaterial_1_17(Block block, IBlockData defaultState) {
        this.block = block;
        this.defaultState = defaultState;
        this.material = defaultState.getMaterial();
        this.craftBlockData = CraftBlockData.fromData(defaultState);
        this.craftMaterial = craftBlockData.getMaterial();
        BlockBase.Info blockInfo = ReflectionUtil.getField(BlockBase.class, block, "aP");
        this.isTranslucent = !(boolean) ReflectionUtil.getField(BlockBase.Info.class, blockInfo, "n");
        opacity = defaultState.b(BlockAccessAir.a, BlockPosition.b);
        TileEntity tileEntity = !(block instanceof ITileEntity) ? null : ((ITileEntity) block).createTile(
                BlockPosition.b,
                defaultState
        );
        tile = tileEntity == null
                ? null
                : new LazyCompoundTag_1_17(Suppliers.memoize(() -> tileEntity.save(new NBTTagCompound())));
    }

    public Block getBlock() {
        return block;
    }

    public IBlockData getState() {
        return defaultState;
    }

    public CraftBlockData getCraftBlockData() {
        return craftBlockData;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public boolean isAir() {
        return defaultState.isAir();
    }

    @Override
    public boolean isFullCube() {
        return craftMaterial.isOccluding();
    }

    @Override
    public boolean isOpaque() {
        return material.f();
    }

    @Override
    public boolean isPowerSource() {
        return defaultState.isPowerSource();
    }

    @Override
    public boolean isLiquid() {
        return material.isLiquid();
    }

    @Override
    public boolean isSolid() {
        return material.isBuildable();
    }

    @Override
    public float getHardness() {
        return craftBlockData.getState().k;
    }

    @Override
    public float getResistance() {
        return block.getDurability();
    }

    @Override
    public float getSlipperiness() {
        return block.getFrictionFactor();
    }

    @Override
    public int getLightValue() {
        return defaultState.f();
    }

    @Override
    public int getLightOpacity() {
        return opacity;
    }

    @Override
    public boolean isFragileWhenPushed() {
        // DESTROY
        return material.getPushReaction() == EnumPistonReaction.b;
    }

    @Override
    public boolean isUnpushable() {
        // BLOCK
        return material.getPushReaction() == EnumPistonReaction.c;
    }

    @Override
    public boolean isTicksRandomly() {
        return block.isTicking(defaultState);
    }

    @Override
    public boolean isMovementBlocker() {
        return material.isSolid();
    }

    @Override
    public boolean isBurnable() {
        return material.isBurnable();
    }

    @Override
    public boolean isToolRequired() {
        //TODO Removed in 1.16.1 Replacement not found.
        return true;
    }

    @Override
    public boolean isReplacedDuringPlacement() {
        return material.isReplaceable();
    }

    @Override
    public boolean isTranslucent() {
        return isTranslucent;
    }

    @Override
    public boolean hasContainer() {
        return block instanceof ITileEntity;
    }

    @Override
    public boolean isTile() {
        return block instanceof ITileEntity;
    }

    @Override
    public CompoundTag getDefaultTile() {
        return tile;
    }

    @Override
    public int getMapColor() {
        // rgb field
        return material.h().al;
    }

}
