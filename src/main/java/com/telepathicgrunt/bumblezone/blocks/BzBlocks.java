package com.telepathicgrunt.bumblezone.blocks;

import com.telepathicgrunt.bumblezone.Bumblezone;
import com.telepathicgrunt.bumblezone.mixin.MaterialInvoker;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import com.telepathicgrunt.bumblezone.fluids.SugarWaterFluid;


public class BzBlocks {
    public static Material RESIDUE = ((MaterialInvoker)((MaterialInvoker)new Material.Builder(MaterialColor.ADOBE))
            .getNotOpaque())
            .getPushDestroys()
            .doesNotBlockMovement()
            .replaceable()
            .notSolid()
            .build();

    public static final Block POROUS_HONEYCOMB = new PorousHoneycomb();
    public static final Block FILLED_POROUS_HONEYCOMB = new FilledPorousHoneycomb();
    public static final Block EMPTY_HONEYCOMB_BROOD = new EmptyHoneycombBrood();
    public static final Block HONEYCOMB_BROOD = new HoneycombBrood();
    public static final Block SUGAR_INFUSED_STONE = new SugarInfusedStone();
    public static final Block SUGAR_INFUSED_COBBLESTONE = new SugarInfusedCobblestone();
    public static final Block HONEY_CRYSTAL = new HoneyCrystal();
    public static final Block STICKY_HONEY_RESIDUE = new StickyHoneyResidue();
    public static final Block STICKY_HONEY_REDSTONE = new StickyHoneyRedstone();
    public static final Block BEESWAX_PLANKS = new BeeswaxPlanks();

    public static final FlowingFluid SUGAR_WATER_FLUID = new SugarWaterFluid.Source();
    public static final FlowingFluid SUGAR_WATER_FLUID_FLOWING = new SugarWaterFluid.Flowing();
    public static final Block SUGAR_WATER_BLOCK = new SugarWaterBlock(SUGAR_WATER_FLUID);

    /**
     * registers the Blocks so they now exist in the registry
     */
    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new ResourceLocation(Bumblezone.MODID, "porous_honeycomb_block"), POROUS_HONEYCOMB);
        Registry.register(Registry.BLOCK, new ResourceLocation(Bumblezone.MODID, "filled_porous_honeycomb_block"), FILLED_POROUS_HONEYCOMB);
        Registry.register(Registry.BLOCK, new ResourceLocation(Bumblezone.MODID, "dead_honeycomb_larva_block"), EMPTY_HONEYCOMB_BROOD);
        Registry.register(Registry.BLOCK, new ResourceLocation(Bumblezone.MODID, "honeycomb_larva_block"), HONEYCOMB_BROOD);
        Registry.register(Registry.BLOCK, new ResourceLocation(Bumblezone.MODID, "sugar_infused_stone"), SUGAR_INFUSED_STONE);
        Registry.register(Registry.BLOCK, new ResourceLocation(Bumblezone.MODID, "sugar_infused_cobblestone"), SUGAR_INFUSED_COBBLESTONE);
        Registry.register(Registry.BLOCK, new ResourceLocation(Bumblezone.MODID, "honey_crystal"), HONEY_CRYSTAL);
        Registry.register(Registry.BLOCK, new ResourceLocation(Bumblezone.MODID, "sticky_honey_residue"), STICKY_HONEY_RESIDUE);
        Registry.register(Registry.BLOCK, new ResourceLocation(Bumblezone.MODID, "sticky_honey_redstone"), STICKY_HONEY_REDSTONE);
        Registry.register(Registry.BLOCK, new ResourceLocation(Bumblezone.MODID, "beeswax_planks"), BEESWAX_PLANKS);
        Registry.register(Registry.FLUID, new ResourceLocation(Bumblezone.MODID, "sugar_water_still"), SUGAR_WATER_FLUID);
        Registry.register(Registry.FLUID, new ResourceLocation(Bumblezone.MODID, "sugar_water_flowing"), SUGAR_WATER_FLUID_FLOWING);
        Registry.register(Registry.BLOCK, new ResourceLocation(Bumblezone.MODID, "sugar_water_block"), SUGAR_WATER_BLOCK);
    }
}