package net.josuemorales.lota.worldgen.biome;

// Importing various classes necessary for biome registration and customization

import net.josuemorales.lota.LegendsOfTheAncientsMod;
import net.josuemorales.lota.worldgen.ModPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomes {

    // Creating a unique key to identify the custom "Sacred Grove" biome
    public static final ResourceKey<Biome> SACRED_GROVE_BIOME = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(LegendsOfTheAncientsMod.MOD_ID, "sacred_grove_biome"));

    // This method is called to register the biome into the game using the bootstrap context
    public static void boostrap(BootstrapContext<Biome> context) {
        // Register the "Sacred Grove" biome with its corresponding settings
        context.register(SACRED_GROVE_BIOME, sacredGroveBiome(context));
    }

    // This method adds default overworld generation features like caves, lakes, and springs
    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        // Adding default terrain features to the biome (caves, lakes, crystals, etc.)
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    // This method defines the custom settings for the "Sacred Grove" biome
    public static Biome sacredGroveBiome(BootstrapContext<Biome> context) {
        // Setting up spawn rules for mobs in the Sacred Grove
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        // You can add custom creatures here (e.g., RHINO), but currently, it spawns wolves
        // spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.RHINO.get(), 2, 3, 5));
        spawnBuilder.addSpawn(MobCategory.CREATURE,
                new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4)); // Spawn wolves

        // Add standard farm animals and other common mobs to the biome
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Setting up terrain and decoration features for the Sacred Grove biome
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER));

        // Adding default biome features like vanilla biomes do (caves, ores, plants)
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder); // Adds mossy stone to the biome
        BiomeDefaultFeatures.addForestFlowers(biomeBuilder); // Adds forest flowers
        BiomeDefaultFeatures.addFerns(biomeBuilder); // Adds ferns
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder); // Adds ores like iron, coal, etc.
        BiomeDefaultFeatures.addExtraGold(biomeBuilder); // Adds extra gold ore

        // Adding vegetation (trees) to the biome. The trees come from the plains biome's settings.
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_PLAINS);

        // Adding mushrooms and additional vegetation to the biome
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder); // Adds mushrooms
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder); // Adds extra plants
        // Adding a custom placed feature for the Sacred Grove (probably custom trees or plants)
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.SACRED_GROVE_PLACED_KEY);

        // Return the configured biome with all the set features, spawn rules, and visual effects
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true) // The biome has precipitation (rain/snow)
                .downfall(0.8f) // Sets how much it rains/snows
                .temperature(0.7f) // Sets the temperature (0.7 is temperate)
                .generationSettings(biomeBuilder.build()) // Applies the terrain/feature settings
                .mobSpawnSettings(spawnBuilder.build()) // Applies the mob spawning settings
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xe82e3b) // Custom water color
                        .waterFogColor(0xbf1b26) // Custom water fog color
                        .skyColor(0x30c918) // Custom sky color
                        .grassColorOverride(0x7f03fc) // Custom grass color
                        .foliageColorOverride(0xd203fc) // Custom foliage (leaf) color
                        .fogColor(0x22a1e6) // Custom fog color
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS) // Adds ambient sound
                        .backgroundMusic(Musics.GAME)
                        .build()
                )
                .build(); // Builds and returns the final biome object
    }
}
