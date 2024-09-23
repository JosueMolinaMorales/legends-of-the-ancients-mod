package net.josuemorales.lota.worldgen.biome;

import net.josuemorales.lota.LegendsOfTheAncientsMod;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(LegendsOfTheAncientsMod.MOD_ID, "overworld"), 5));
    }
}
