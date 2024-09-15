package net.minecraft.world.item;

import com.mojang.datafixers.util.Pair;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.InstrumentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.SuspiciousEffectHolder;

public class CreativeModeTabs {
    public static final ResourceKey<CreativeModeTab> BUILDING_BLOCKS = createKey("building_blocks");
    public static final ResourceKey<CreativeModeTab> COLORED_BLOCKS = createKey("colored_blocks");
    public static final ResourceKey<CreativeModeTab> NATURAL_BLOCKS = createKey("natural_blocks");
    public static final ResourceKey<CreativeModeTab> FUNCTIONAL_BLOCKS = createKey("functional_blocks");
    public static final ResourceKey<CreativeModeTab> REDSTONE_BLOCKS = createKey("redstone_blocks");
    public static final ResourceKey<CreativeModeTab> HOTBAR = createKey("hotbar");
    public static final ResourceKey<CreativeModeTab> SEARCH = createKey("search");
    public static final ResourceKey<CreativeModeTab> TOOLS_AND_UTILITIES = createKey("tools_and_utilities");
    public static final ResourceKey<CreativeModeTab> COMBAT = createKey("combat");
    public static final ResourceKey<CreativeModeTab> FOOD_AND_DRINKS = createKey("food_and_drinks");
    public static final ResourceKey<CreativeModeTab> INGREDIENTS = createKey("ingredients");
    public static final ResourceKey<CreativeModeTab> SPAWN_EGGS = createKey("spawn_eggs");
    public static final ResourceKey<CreativeModeTab> OP_BLOCKS = createKey("op_blocks");
    public static final ResourceKey<CreativeModeTab> INVENTORY = createKey("inventory");
    private static final Comparator<Holder<PaintingVariant>> PAINTING_COMPARATOR = Comparator.comparing(
        Holder::value,
        Comparator.<PaintingVariant>comparingInt(p_270004_ -> p_270004_.getHeight() * p_270004_.getWidth()).thenComparing(PaintingVariant::getWidth)
    );
    @Nullable
    private static CreativeModeTab.ItemDisplayParameters CACHED_PARAMETERS;

    private static ResourceKey<CreativeModeTab> createKey(String pName) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(pName));
    }

    public static CreativeModeTab bootstrap(Registry<CreativeModeTab> pRegistry) {
        Registry.register(
            pRegistry,
            BUILDING_BLOCKS,
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                .title(Component.translatable("itemGroup.buildingBlocks"))
                .icon(() -> new ItemStack(Blocks.BRICKS))
                .displayItems((p_271005_, p_259465_) -> {
                    p_259465_.accept(Items.OAK_LOG);
                    p_259465_.accept(Items.OAK_WOOD);
                    p_259465_.accept(Items.STRIPPED_OAK_LOG);
                    p_259465_.accept(Items.STRIPPED_OAK_WOOD);
                    p_259465_.accept(Items.OAK_PLANKS);
                    p_259465_.accept(Items.OAK_STAIRS);
                    p_259465_.accept(Items.OAK_SLAB);
                    p_259465_.accept(Items.OAK_FENCE);
                    p_259465_.accept(Items.OAK_FENCE_GATE);
                    p_259465_.accept(Items.OAK_DOOR);
                    p_259465_.accept(Items.OAK_TRAPDOOR);
                    p_259465_.accept(Items.OAK_PRESSURE_PLATE);
                    p_259465_.accept(Items.OAK_BUTTON);
                    p_259465_.accept(Items.SPRUCE_LOG);
                    p_259465_.accept(Items.SPRUCE_WOOD);
                    p_259465_.accept(Items.STRIPPED_SPRUCE_LOG);
                    p_259465_.accept(Items.STRIPPED_SPRUCE_WOOD);
                    p_259465_.accept(Items.SPRUCE_PLANKS);
                    p_259465_.accept(Items.SPRUCE_STAIRS);
                    p_259465_.accept(Items.SPRUCE_SLAB);
                    p_259465_.accept(Items.SPRUCE_FENCE);
                    p_259465_.accept(Items.SPRUCE_FENCE_GATE);
                    p_259465_.accept(Items.SPRUCE_DOOR);
                    p_259465_.accept(Items.SPRUCE_TRAPDOOR);
                    p_259465_.accept(Items.SPRUCE_PRESSURE_PLATE);
                    p_259465_.accept(Items.SPRUCE_BUTTON);
                    p_259465_.accept(Items.BIRCH_LOG);
                    p_259465_.accept(Items.BIRCH_WOOD);
                    p_259465_.accept(Items.STRIPPED_BIRCH_LOG);
                    p_259465_.accept(Items.STRIPPED_BIRCH_WOOD);
                    p_259465_.accept(Items.BIRCH_PLANKS);
                    p_259465_.accept(Items.BIRCH_STAIRS);
                    p_259465_.accept(Items.BIRCH_SLAB);
                    p_259465_.accept(Items.BIRCH_FENCE);
                    p_259465_.accept(Items.BIRCH_FENCE_GATE);
                    p_259465_.accept(Items.BIRCH_DOOR);
                    p_259465_.accept(Items.BIRCH_TRAPDOOR);
                    p_259465_.accept(Items.BIRCH_PRESSURE_PLATE);
                    p_259465_.accept(Items.BIRCH_BUTTON);
                    p_259465_.accept(Items.JUNGLE_LOG);
                    p_259465_.accept(Items.JUNGLE_WOOD);
                    p_259465_.accept(Items.STRIPPED_JUNGLE_LOG);
                    p_259465_.accept(Items.STRIPPED_JUNGLE_WOOD);
                    p_259465_.accept(Items.JUNGLE_PLANKS);
                    p_259465_.accept(Items.JUNGLE_STAIRS);
                    p_259465_.accept(Items.JUNGLE_SLAB);
                    p_259465_.accept(Items.JUNGLE_FENCE);
                    p_259465_.accept(Items.JUNGLE_FENCE_GATE);
                    p_259465_.accept(Items.JUNGLE_DOOR);
                    p_259465_.accept(Items.JUNGLE_TRAPDOOR);
                    p_259465_.accept(Items.JUNGLE_PRESSURE_PLATE);
                    p_259465_.accept(Items.JUNGLE_BUTTON);
                    p_259465_.accept(Items.ACACIA_LOG);
                    p_259465_.accept(Items.ACACIA_WOOD);
                    p_259465_.accept(Items.STRIPPED_ACACIA_LOG);
                    p_259465_.accept(Items.STRIPPED_ACACIA_WOOD);
                    p_259465_.accept(Items.ACACIA_PLANKS);
                    p_259465_.accept(Items.ACACIA_STAIRS);
                    p_259465_.accept(Items.ACACIA_SLAB);
                    p_259465_.accept(Items.ACACIA_FENCE);
                    p_259465_.accept(Items.ACACIA_FENCE_GATE);
                    p_259465_.accept(Items.ACACIA_DOOR);
                    p_259465_.accept(Items.ACACIA_TRAPDOOR);
                    p_259465_.accept(Items.ACACIA_PRESSURE_PLATE);
                    p_259465_.accept(Items.ACACIA_BUTTON);
                    p_259465_.accept(Items.DARK_OAK_LOG);
                    p_259465_.accept(Items.DARK_OAK_WOOD);
                    p_259465_.accept(Items.STRIPPED_DARK_OAK_LOG);
                    p_259465_.accept(Items.STRIPPED_DARK_OAK_WOOD);
                    p_259465_.accept(Items.DARK_OAK_PLANKS);
                    p_259465_.accept(Items.DARK_OAK_STAIRS);
                    p_259465_.accept(Items.DARK_OAK_SLAB);
                    p_259465_.accept(Items.DARK_OAK_FENCE);
                    p_259465_.accept(Items.DARK_OAK_FENCE_GATE);
                    p_259465_.accept(Items.DARK_OAK_DOOR);
                    p_259465_.accept(Items.DARK_OAK_TRAPDOOR);
                    p_259465_.accept(Items.DARK_OAK_PRESSURE_PLATE);
                    p_259465_.accept(Items.DARK_OAK_BUTTON);
                    p_259465_.accept(Items.MANGROVE_LOG);
                    p_259465_.accept(Items.MANGROVE_WOOD);
                    p_259465_.accept(Items.STRIPPED_MANGROVE_LOG);
                    p_259465_.accept(Items.STRIPPED_MANGROVE_WOOD);
                    p_259465_.accept(Items.MANGROVE_PLANKS);
                    p_259465_.accept(Items.MANGROVE_STAIRS);
                    p_259465_.accept(Items.MANGROVE_SLAB);
                    p_259465_.accept(Items.MANGROVE_FENCE);
                    p_259465_.accept(Items.MANGROVE_FENCE_GATE);
                    p_259465_.accept(Items.MANGROVE_DOOR);
                    p_259465_.accept(Items.MANGROVE_TRAPDOOR);
                    p_259465_.accept(Items.MANGROVE_PRESSURE_PLATE);
                    p_259465_.accept(Items.MANGROVE_BUTTON);
                    p_259465_.accept(Items.CHERRY_LOG);
                    p_259465_.accept(Items.CHERRY_WOOD);
                    p_259465_.accept(Items.STRIPPED_CHERRY_LOG);
                    p_259465_.accept(Items.STRIPPED_CHERRY_WOOD);
                    p_259465_.accept(Items.CHERRY_PLANKS);
                    p_259465_.accept(Items.CHERRY_STAIRS);
                    p_259465_.accept(Items.CHERRY_SLAB);
                    p_259465_.accept(Items.CHERRY_FENCE);
                    p_259465_.accept(Items.CHERRY_FENCE_GATE);
                    p_259465_.accept(Items.CHERRY_DOOR);
                    p_259465_.accept(Items.CHERRY_TRAPDOOR);
                    p_259465_.accept(Items.CHERRY_PRESSURE_PLATE);
                    p_259465_.accept(Items.CHERRY_BUTTON);
                    p_259465_.accept(Items.BAMBOO_BLOCK);
                    p_259465_.accept(Items.STRIPPED_BAMBOO_BLOCK);
                    p_259465_.accept(Items.BAMBOO_PLANKS);
                    p_259465_.accept(Items.BAMBOO_MOSAIC);
                    p_259465_.accept(Items.BAMBOO_STAIRS);
                    p_259465_.accept(Items.BAMBOO_MOSAIC_STAIRS);
                    p_259465_.accept(Items.BAMBOO_SLAB);
                    p_259465_.accept(Items.BAMBOO_MOSAIC_SLAB);
                    p_259465_.accept(Items.BAMBOO_FENCE);
                    p_259465_.accept(Items.BAMBOO_FENCE_GATE);
                    p_259465_.accept(Items.BAMBOO_DOOR);
                    p_259465_.accept(Items.BAMBOO_TRAPDOOR);
                    p_259465_.accept(Items.BAMBOO_PRESSURE_PLATE);
                    p_259465_.accept(Items.BAMBOO_BUTTON);
                    p_259465_.accept(Items.CRIMSON_STEM);
                    p_259465_.accept(Items.CRIMSON_HYPHAE);
                    p_259465_.accept(Items.STRIPPED_CRIMSON_STEM);
                    p_259465_.accept(Items.STRIPPED_CRIMSON_HYPHAE);
                    p_259465_.accept(Items.CRIMSON_PLANKS);
                    p_259465_.accept(Items.CRIMSON_STAIRS);
                    p_259465_.accept(Items.CRIMSON_SLAB);
                    p_259465_.accept(Items.CRIMSON_FENCE);
                    p_259465_.accept(Items.CRIMSON_FENCE_GATE);
                    p_259465_.accept(Items.CRIMSON_DOOR);
                    p_259465_.accept(Items.CRIMSON_TRAPDOOR);
                    p_259465_.accept(Items.CRIMSON_PRESSURE_PLATE);
                    p_259465_.accept(Items.CRIMSON_BUTTON);
                    p_259465_.accept(Items.WARPED_STEM);
                    p_259465_.accept(Items.WARPED_HYPHAE);
                    p_259465_.accept(Items.STRIPPED_WARPED_STEM);
                    p_259465_.accept(Items.STRIPPED_WARPED_HYPHAE);
                    p_259465_.accept(Items.WARPED_PLANKS);
                    p_259465_.accept(Items.WARPED_STAIRS);
                    p_259465_.accept(Items.WARPED_SLAB);
                    p_259465_.accept(Items.WARPED_FENCE);
                    p_259465_.accept(Items.WARPED_FENCE_GATE);
                    p_259465_.accept(Items.WARPED_DOOR);
                    p_259465_.accept(Items.WARPED_TRAPDOOR);
                    p_259465_.accept(Items.WARPED_PRESSURE_PLATE);
                    p_259465_.accept(Items.WARPED_BUTTON);
                    p_259465_.accept(Items.STONE);
                    p_259465_.accept(Items.STONE_STAIRS);
                    p_259465_.accept(Items.STONE_SLAB);
                    p_259465_.accept(Items.STONE_PRESSURE_PLATE);
                    p_259465_.accept(Items.STONE_BUTTON);
                    p_259465_.accept(Items.COBBLESTONE);
                    p_259465_.accept(Items.COBBLESTONE_STAIRS);
                    p_259465_.accept(Items.COBBLESTONE_SLAB);
                    p_259465_.accept(Items.COBBLESTONE_WALL);
                    p_259465_.accept(Items.MOSSY_COBBLESTONE);
                    p_259465_.accept(Items.MOSSY_COBBLESTONE_STAIRS);
                    p_259465_.accept(Items.MOSSY_COBBLESTONE_SLAB);
                    p_259465_.accept(Items.MOSSY_COBBLESTONE_WALL);
                    p_259465_.accept(Items.SMOOTH_STONE);
                    p_259465_.accept(Items.SMOOTH_STONE_SLAB);
                    p_259465_.accept(Items.STONE_BRICKS);
                    p_259465_.accept(Items.CRACKED_STONE_BRICKS);
                    p_259465_.accept(Items.STONE_BRICK_STAIRS);
                    p_259465_.accept(Items.STONE_BRICK_SLAB);
                    p_259465_.accept(Items.STONE_BRICK_WALL);
                    p_259465_.accept(Items.CHISELED_STONE_BRICKS);
                    p_259465_.accept(Items.MOSSY_STONE_BRICKS);
                    p_259465_.accept(Items.MOSSY_STONE_BRICK_STAIRS);
                    p_259465_.accept(Items.MOSSY_STONE_BRICK_SLAB);
                    p_259465_.accept(Items.MOSSY_STONE_BRICK_WALL);
                    p_259465_.accept(Items.GRANITE);
                    p_259465_.accept(Items.GRANITE_STAIRS);
                    p_259465_.accept(Items.GRANITE_SLAB);
                    p_259465_.accept(Items.GRANITE_WALL);
                    p_259465_.accept(Items.POLISHED_GRANITE);
                    p_259465_.accept(Items.POLISHED_GRANITE_STAIRS);
                    p_259465_.accept(Items.POLISHED_GRANITE_SLAB);
                    p_259465_.accept(Items.DIORITE);
                    p_259465_.accept(Items.DIORITE_STAIRS);
                    p_259465_.accept(Items.DIORITE_SLAB);
                    p_259465_.accept(Items.DIORITE_WALL);
                    p_259465_.accept(Items.POLISHED_DIORITE);
                    p_259465_.accept(Items.POLISHED_DIORITE_STAIRS);
                    p_259465_.accept(Items.POLISHED_DIORITE_SLAB);
                    p_259465_.accept(Items.ANDESITE);
                    p_259465_.accept(Items.ANDESITE_STAIRS);
                    p_259465_.accept(Items.ANDESITE_SLAB);
                    p_259465_.accept(Items.ANDESITE_WALL);
                    p_259465_.accept(Items.POLISHED_ANDESITE);
                    p_259465_.accept(Items.POLISHED_ANDESITE_STAIRS);
                    p_259465_.accept(Items.POLISHED_ANDESITE_SLAB);
                    p_259465_.accept(Items.DEEPSLATE);
                    p_259465_.accept(Items.COBBLED_DEEPSLATE);
                    p_259465_.accept(Items.COBBLED_DEEPSLATE_STAIRS);
                    p_259465_.accept(Items.COBBLED_DEEPSLATE_SLAB);
                    p_259465_.accept(Items.COBBLED_DEEPSLATE_WALL);
                    p_259465_.accept(Items.CHISELED_DEEPSLATE);
                    p_259465_.accept(Items.POLISHED_DEEPSLATE);
                    p_259465_.accept(Items.POLISHED_DEEPSLATE_STAIRS);
                    p_259465_.accept(Items.POLISHED_DEEPSLATE_SLAB);
                    p_259465_.accept(Items.POLISHED_DEEPSLATE_WALL);
                    p_259465_.accept(Items.DEEPSLATE_BRICKS);
                    p_259465_.accept(Items.CRACKED_DEEPSLATE_BRICKS);
                    p_259465_.accept(Items.DEEPSLATE_BRICK_STAIRS);
                    p_259465_.accept(Items.DEEPSLATE_BRICK_SLAB);
                    p_259465_.accept(Items.DEEPSLATE_BRICK_WALL);
                    p_259465_.accept(Items.DEEPSLATE_TILES);
                    p_259465_.accept(Items.CRACKED_DEEPSLATE_TILES);
                    p_259465_.accept(Items.DEEPSLATE_TILE_STAIRS);
                    p_259465_.accept(Items.DEEPSLATE_TILE_SLAB);
                    p_259465_.accept(Items.DEEPSLATE_TILE_WALL);
                    p_259465_.accept(Items.REINFORCED_DEEPSLATE);
                    p_259465_.accept(Items.TUFF);
                    p_259465_.accept(Items.TUFF_STAIRS);
                    p_259465_.accept(Items.TUFF_SLAB);
                    p_259465_.accept(Items.TUFF_WALL);
                    p_259465_.accept(Items.CHISELED_TUFF);
                    p_259465_.accept(Items.POLISHED_TUFF);
                    p_259465_.accept(Items.POLISHED_TUFF_STAIRS);
                    p_259465_.accept(Items.POLISHED_TUFF_SLAB);
                    p_259465_.accept(Items.POLISHED_TUFF_WALL);
                    p_259465_.accept(Items.TUFF_BRICKS);
                    p_259465_.accept(Items.TUFF_BRICK_STAIRS);
                    p_259465_.accept(Items.TUFF_BRICK_SLAB);
                    p_259465_.accept(Items.TUFF_BRICK_WALL);
                    p_259465_.accept(Items.CHISELED_TUFF_BRICKS);
                    p_259465_.accept(Items.BRICKS);
                    p_259465_.accept(Items.BRICK_STAIRS);
                    p_259465_.accept(Items.BRICK_SLAB);
                    p_259465_.accept(Items.BRICK_WALL);
                    p_259465_.accept(Items.PACKED_MUD);
                    p_259465_.accept(Items.MUD_BRICKS);
                    p_259465_.accept(Items.MUD_BRICK_STAIRS);
                    p_259465_.accept(Items.MUD_BRICK_SLAB);
                    p_259465_.accept(Items.MUD_BRICK_WALL);
                    p_259465_.accept(Items.SANDSTONE);
                    p_259465_.accept(Items.SANDSTONE_STAIRS);
                    p_259465_.accept(Items.SANDSTONE_SLAB);
                    p_259465_.accept(Items.SANDSTONE_WALL);
                    p_259465_.accept(Items.CHISELED_SANDSTONE);
                    p_259465_.accept(Items.SMOOTH_SANDSTONE);
                    p_259465_.accept(Items.SMOOTH_SANDSTONE_STAIRS);
                    p_259465_.accept(Items.SMOOTH_SANDSTONE_SLAB);
                    p_259465_.accept(Items.CUT_SANDSTONE);
                    p_259465_.accept(Items.CUT_STANDSTONE_SLAB);
                    p_259465_.accept(Items.RED_SANDSTONE);
                    p_259465_.accept(Items.RED_SANDSTONE_STAIRS);
                    p_259465_.accept(Items.RED_SANDSTONE_SLAB);
                    p_259465_.accept(Items.RED_SANDSTONE_WALL);
                    p_259465_.accept(Items.CHISELED_RED_SANDSTONE);
                    p_259465_.accept(Items.SMOOTH_RED_SANDSTONE);
                    p_259465_.accept(Items.SMOOTH_RED_SANDSTONE_STAIRS);
                    p_259465_.accept(Items.SMOOTH_RED_SANDSTONE_SLAB);
                    p_259465_.accept(Items.CUT_RED_SANDSTONE);
                    p_259465_.accept(Items.CUT_RED_SANDSTONE_SLAB);
                    p_259465_.accept(Items.SEA_LANTERN);
                    p_259465_.accept(Items.PRISMARINE);
                    p_259465_.accept(Items.PRISMARINE_STAIRS);
                    p_259465_.accept(Items.PRISMARINE_SLAB);
                    p_259465_.accept(Items.PRISMARINE_WALL);
                    p_259465_.accept(Items.PRISMARINE_BRICKS);
                    p_259465_.accept(Items.PRISMARINE_BRICK_STAIRS);
                    p_259465_.accept(Items.PRISMARINE_BRICK_SLAB);
                    p_259465_.accept(Items.DARK_PRISMARINE);
                    p_259465_.accept(Items.DARK_PRISMARINE_STAIRS);
                    p_259465_.accept(Items.DARK_PRISMARINE_SLAB);
                    p_259465_.accept(Items.NETHERRACK);
                    p_259465_.accept(Items.NETHER_BRICKS);
                    p_259465_.accept(Items.CRACKED_NETHER_BRICKS);
                    p_259465_.accept(Items.NETHER_BRICK_STAIRS);
                    p_259465_.accept(Items.NETHER_BRICK_SLAB);
                    p_259465_.accept(Items.NETHER_BRICK_WALL);
                    p_259465_.accept(Items.NETHER_BRICK_FENCE);
                    p_259465_.accept(Items.CHISELED_NETHER_BRICKS);
                    p_259465_.accept(Items.RED_NETHER_BRICKS);
                    p_259465_.accept(Items.RED_NETHER_BRICK_STAIRS);
                    p_259465_.accept(Items.RED_NETHER_BRICK_SLAB);
                    p_259465_.accept(Items.RED_NETHER_BRICK_WALL);
                    p_259465_.accept(Items.BASALT);
                    p_259465_.accept(Items.SMOOTH_BASALT);
                    p_259465_.accept(Items.POLISHED_BASALT);
                    p_259465_.accept(Items.BLACKSTONE);
                    p_259465_.accept(Items.GILDED_BLACKSTONE);
                    p_259465_.accept(Items.BLACKSTONE_STAIRS);
                    p_259465_.accept(Items.BLACKSTONE_SLAB);
                    p_259465_.accept(Items.BLACKSTONE_WALL);
                    p_259465_.accept(Items.CHISELED_POLISHED_BLACKSTONE);
                    p_259465_.accept(Items.POLISHED_BLACKSTONE);
                    p_259465_.accept(Items.POLISHED_BLACKSTONE_STAIRS);
                    p_259465_.accept(Items.POLISHED_BLACKSTONE_SLAB);
                    p_259465_.accept(Items.POLISHED_BLACKSTONE_WALL);
                    p_259465_.accept(Items.POLISHED_BLACKSTONE_PRESSURE_PLATE);
                    p_259465_.accept(Items.POLISHED_BLACKSTONE_BUTTON);
                    p_259465_.accept(Items.POLISHED_BLACKSTONE_BRICKS);
                    p_259465_.accept(Items.CRACKED_POLISHED_BLACKSTONE_BRICKS);
                    p_259465_.accept(Items.POLISHED_BLACKSTONE_BRICK_STAIRS);
                    p_259465_.accept(Items.POLISHED_BLACKSTONE_BRICK_SLAB);
                    p_259465_.accept(Items.POLISHED_BLACKSTONE_BRICK_WALL);
                    p_259465_.accept(Items.END_STONE);
                    p_259465_.accept(Items.END_STONE_BRICKS);
                    p_259465_.accept(Items.END_STONE_BRICK_STAIRS);
                    p_259465_.accept(Items.END_STONE_BRICK_SLAB);
                    p_259465_.accept(Items.END_STONE_BRICK_WALL);
                    p_259465_.accept(Items.PURPUR_BLOCK);
                    p_259465_.accept(Items.PURPUR_PILLAR);
                    p_259465_.accept(Items.PURPUR_STAIRS);
                    p_259465_.accept(Items.PURPUR_SLAB);
                    p_259465_.accept(Items.COAL_BLOCK);
                    p_259465_.accept(Items.IRON_BLOCK);
                    p_259465_.accept(Items.IRON_BARS);
                    p_259465_.accept(Items.IRON_DOOR);
                    p_259465_.accept(Items.IRON_TRAPDOOR);
                    p_259465_.accept(Items.HEAVY_WEIGHTED_PRESSURE_PLATE);
                    p_259465_.accept(Items.CHAIN);
                    p_259465_.accept(Items.GOLD_BLOCK);
                    p_259465_.accept(Items.LIGHT_WEIGHTED_PRESSURE_PLATE);
                    p_259465_.accept(Items.REDSTONE_BLOCK);
                    p_259465_.accept(Items.EMERALD_BLOCK);
                    p_259465_.accept(Items.LAPIS_BLOCK);
                    p_259465_.accept(Items.DIAMOND_BLOCK);
                    p_259465_.accept(Items.NETHERITE_BLOCK);
                    p_259465_.accept(Items.QUARTZ_BLOCK);
                    p_259465_.accept(Items.QUARTZ_STAIRS);
                    p_259465_.accept(Items.QUARTZ_SLAB);
                    p_259465_.accept(Items.CHISELED_QUARTZ_BLOCK);
                    p_259465_.accept(Items.QUARTZ_BRICKS);
                    p_259465_.accept(Items.QUARTZ_PILLAR);
                    p_259465_.accept(Items.SMOOTH_QUARTZ);
                    p_259465_.accept(Items.SMOOTH_QUARTZ_STAIRS);
                    p_259465_.accept(Items.SMOOTH_QUARTZ_SLAB);
                    p_259465_.accept(Items.AMETHYST_BLOCK);
                    p_259465_.accept(Items.COPPER_BLOCK);
                    p_259465_.accept(Items.CHISELED_COPPER);
                    p_259465_.accept(Items.COPPER_GRATE);
                    p_259465_.accept(Items.CUT_COPPER);
                    p_259465_.accept(Items.CUT_COPPER_STAIRS);
                    p_259465_.accept(Items.CUT_COPPER_SLAB);
                    p_259465_.accept(Items.COPPER_DOOR);
                    p_259465_.accept(Items.COPPER_TRAPDOOR);
                    p_259465_.accept(Items.COPPER_BULB);
                    p_259465_.accept(Items.EXPOSED_COPPER);
                    p_259465_.accept(Items.EXPOSED_CHISELED_COPPER);
                    p_259465_.accept(Items.EXPOSED_COPPER_GRATE);
                    p_259465_.accept(Items.EXPOSED_CUT_COPPER);
                    p_259465_.accept(Items.EXPOSED_CUT_COPPER_STAIRS);
                    p_259465_.accept(Items.EXPOSED_CUT_COPPER_SLAB);
                    p_259465_.accept(Items.EXPOSED_COPPER_DOOR);
                    p_259465_.accept(Items.EXPOSED_COPPER_TRAPDOOR);
                    p_259465_.accept(Items.EXPOSED_COPPER_BULB);
                    p_259465_.accept(Items.WEATHERED_COPPER);
                    p_259465_.accept(Items.WEATHERED_CHISELED_COPPER);
                    p_259465_.accept(Items.WEATHERED_COPPER_GRATE);
                    p_259465_.accept(Items.WEATHERED_CUT_COPPER);
                    p_259465_.accept(Items.WEATHERED_CUT_COPPER_STAIRS);
                    p_259465_.accept(Items.WEATHERED_CUT_COPPER_SLAB);
                    p_259465_.accept(Items.WEATHERED_COPPER_DOOR);
                    p_259465_.accept(Items.WEATHERED_COPPER_TRAPDOOR);
                    p_259465_.accept(Items.WEATHERED_COPPER_BULB);
                    p_259465_.accept(Items.OXIDIZED_COPPER);
                    p_259465_.accept(Items.OXIDIZED_CHISELED_COPPER);
                    p_259465_.accept(Items.OXIDIZED_COPPER_GRATE);
                    p_259465_.accept(Items.OXIDIZED_CUT_COPPER);
                    p_259465_.accept(Items.OXIDIZED_CUT_COPPER_STAIRS);
                    p_259465_.accept(Items.OXIDIZED_CUT_COPPER_SLAB);
                    p_259465_.accept(Items.OXIDIZED_COPPER_DOOR);
                    p_259465_.accept(Items.OXIDIZED_COPPER_TRAPDOOR);
                    p_259465_.accept(Items.OXIDIZED_COPPER_BULB);
                    p_259465_.accept(Items.WAXED_COPPER_BLOCK);
                    p_259465_.accept(Items.WAXED_CHISELED_COPPER);
                    p_259465_.accept(Items.WAXED_COPPER_GRATE);
                    p_259465_.accept(Items.WAXED_CUT_COPPER);
                    p_259465_.accept(Items.WAXED_CUT_COPPER_STAIRS);
                    p_259465_.accept(Items.WAXED_CUT_COPPER_SLAB);
                    p_259465_.accept(Items.WAXED_COPPER_DOOR);
                    p_259465_.accept(Items.WAXED_COPPER_TRAPDOOR);
                    p_259465_.accept(Items.WAXED_COPPER_BULB);
                    p_259465_.accept(Items.WAXED_EXPOSED_COPPER);
                    p_259465_.accept(Items.WAXED_EXPOSED_CHISELED_COPPER);
                    p_259465_.accept(Items.WAXED_EXPOSED_COPPER_GRATE);
                    p_259465_.accept(Items.WAXED_EXPOSED_CUT_COPPER);
                    p_259465_.accept(Items.WAXED_EXPOSED_CUT_COPPER_STAIRS);
                    p_259465_.accept(Items.WAXED_EXPOSED_CUT_COPPER_SLAB);
                    p_259465_.accept(Items.WAXED_EXPOSED_COPPER_DOOR);
                    p_259465_.accept(Items.WAXED_EXPOSED_COPPER_TRAPDOOR);
                    p_259465_.accept(Items.WAXED_EXPOSED_COPPER_BULB);
                    p_259465_.accept(Items.WAXED_WEATHERED_COPPER);
                    p_259465_.accept(Items.WAXED_WEATHERED_CHISELED_COPPER);
                    p_259465_.accept(Items.WAXED_WEATHERED_COPPER_GRATE);
                    p_259465_.accept(Items.WAXED_WEATHERED_CUT_COPPER);
                    p_259465_.accept(Items.WAXED_WEATHERED_CUT_COPPER_STAIRS);
                    p_259465_.accept(Items.WAXED_WEATHERED_CUT_COPPER_SLAB);
                    p_259465_.accept(Items.WAXED_WEATHERED_COPPER_DOOR);
                    p_259465_.accept(Items.WAXED_WEATHERED_COPPER_TRAPDOOR);
                    p_259465_.accept(Items.WAXED_WEATHERED_COPPER_BULB);
                    p_259465_.accept(Items.WAXED_OXIDIZED_COPPER);
                    p_259465_.accept(Items.WAXED_OXIDIZED_CHISELED_COPPER);
                    p_259465_.accept(Items.WAXED_OXIDIZED_COPPER_GRATE);
                    p_259465_.accept(Items.WAXED_OXIDIZED_CUT_COPPER);
                    p_259465_.accept(Items.WAXED_OXIDIZED_CUT_COPPER_STAIRS);
                    p_259465_.accept(Items.WAXED_OXIDIZED_CUT_COPPER_SLAB);
                    p_259465_.accept(Items.WAXED_OXIDIZED_COPPER_DOOR);
                    p_259465_.accept(Items.WAXED_OXIDIZED_COPPER_TRAPDOOR);
                    p_259465_.accept(Items.WAXED_OXIDIZED_COPPER_BULB);
                })
                .build()
        );
        Registry.register(
            pRegistry,
            COLORED_BLOCKS,
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 1)
                .title(Component.translatable("itemGroup.coloredBlocks"))
                .icon(() -> new ItemStack(Blocks.CYAN_WOOL))
                .displayItems((p_270617_, p_259444_) -> {
                    p_259444_.accept(Items.WHITE_WOOL);
                    p_259444_.accept(Items.LIGHT_GRAY_WOOL);
                    p_259444_.accept(Items.GRAY_WOOL);
                    p_259444_.accept(Items.BLACK_WOOL);
                    p_259444_.accept(Items.BROWN_WOOL);
                    p_259444_.accept(Items.RED_WOOL);
                    p_259444_.accept(Items.ORANGE_WOOL);
                    p_259444_.accept(Items.YELLOW_WOOL);
                    p_259444_.accept(Items.LIME_WOOL);
                    p_259444_.accept(Items.GREEN_WOOL);
                    p_259444_.accept(Items.CYAN_WOOL);
                    p_259444_.accept(Items.LIGHT_BLUE_WOOL);
                    p_259444_.accept(Items.BLUE_WOOL);
                    p_259444_.accept(Items.PURPLE_WOOL);
                    p_259444_.accept(Items.MAGENTA_WOOL);
                    p_259444_.accept(Items.PINK_WOOL);
                    p_259444_.accept(Items.WHITE_CARPET);
                    p_259444_.accept(Items.LIGHT_GRAY_CARPET);
                    p_259444_.accept(Items.GRAY_CARPET);
                    p_259444_.accept(Items.BLACK_CARPET);
                    p_259444_.accept(Items.BROWN_CARPET);
                    p_259444_.accept(Items.RED_CARPET);
                    p_259444_.accept(Items.ORANGE_CARPET);
                    p_259444_.accept(Items.YELLOW_CARPET);
                    p_259444_.accept(Items.LIME_CARPET);
                    p_259444_.accept(Items.GREEN_CARPET);
                    p_259444_.accept(Items.CYAN_CARPET);
                    p_259444_.accept(Items.LIGHT_BLUE_CARPET);
                    p_259444_.accept(Items.BLUE_CARPET);
                    p_259444_.accept(Items.PURPLE_CARPET);
                    p_259444_.accept(Items.MAGENTA_CARPET);
                    p_259444_.accept(Items.PINK_CARPET);
                    p_259444_.accept(Items.TERRACOTTA);
                    p_259444_.accept(Items.WHITE_TERRACOTTA);
                    p_259444_.accept(Items.LIGHT_GRAY_TERRACOTTA);
                    p_259444_.accept(Items.GRAY_TERRACOTTA);
                    p_259444_.accept(Items.BLACK_TERRACOTTA);
                    p_259444_.accept(Items.BROWN_TERRACOTTA);
                    p_259444_.accept(Items.RED_TERRACOTTA);
                    p_259444_.accept(Items.ORANGE_TERRACOTTA);
                    p_259444_.accept(Items.YELLOW_TERRACOTTA);
                    p_259444_.accept(Items.LIME_TERRACOTTA);
                    p_259444_.accept(Items.GREEN_TERRACOTTA);
                    p_259444_.accept(Items.CYAN_TERRACOTTA);
                    p_259444_.accept(Items.LIGHT_BLUE_TERRACOTTA);
                    p_259444_.accept(Items.BLUE_TERRACOTTA);
                    p_259444_.accept(Items.PURPLE_TERRACOTTA);
                    p_259444_.accept(Items.MAGENTA_TERRACOTTA);
                    p_259444_.accept(Items.PINK_TERRACOTTA);
                    p_259444_.accept(Items.WHITE_CONCRETE);
                    p_259444_.accept(Items.LIGHT_GRAY_CONCRETE);
                    p_259444_.accept(Items.GRAY_CONCRETE);
                    p_259444_.accept(Items.BLACK_CONCRETE);
                    p_259444_.accept(Items.BROWN_CONCRETE);
                    p_259444_.accept(Items.RED_CONCRETE);
                    p_259444_.accept(Items.ORANGE_CONCRETE);
                    p_259444_.accept(Items.YELLOW_CONCRETE);
                    p_259444_.accept(Items.LIME_CONCRETE);
                    p_259444_.accept(Items.GREEN_CONCRETE);
                    p_259444_.accept(Items.CYAN_CONCRETE);
                    p_259444_.accept(Items.LIGHT_BLUE_CONCRETE);
                    p_259444_.accept(Items.BLUE_CONCRETE);
                    p_259444_.accept(Items.PURPLE_CONCRETE);
                    p_259444_.accept(Items.MAGENTA_CONCRETE);
                    p_259444_.accept(Items.PINK_CONCRETE);
                    p_259444_.accept(Items.WHITE_CONCRETE_POWDER);
                    p_259444_.accept(Items.LIGHT_GRAY_CONCRETE_POWDER);
                    p_259444_.accept(Items.GRAY_CONCRETE_POWDER);
                    p_259444_.accept(Items.BLACK_CONCRETE_POWDER);
                    p_259444_.accept(Items.BROWN_CONCRETE_POWDER);
                    p_259444_.accept(Items.RED_CONCRETE_POWDER);
                    p_259444_.accept(Items.ORANGE_CONCRETE_POWDER);
                    p_259444_.accept(Items.YELLOW_CONCRETE_POWDER);
                    p_259444_.accept(Items.LIME_CONCRETE_POWDER);
                    p_259444_.accept(Items.GREEN_CONCRETE_POWDER);
                    p_259444_.accept(Items.CYAN_CONCRETE_POWDER);
                    p_259444_.accept(Items.LIGHT_BLUE_CONCRETE_POWDER);
                    p_259444_.accept(Items.BLUE_CONCRETE_POWDER);
                    p_259444_.accept(Items.PURPLE_CONCRETE_POWDER);
                    p_259444_.accept(Items.MAGENTA_CONCRETE_POWDER);
                    p_259444_.accept(Items.PINK_CONCRETE_POWDER);
                    p_259444_.accept(Items.WHITE_GLAZED_TERRACOTTA);
                    p_259444_.accept(Items.LIGHT_GRAY_GLAZED_TERRACOTTA);
                    p_259444_.accept(Items.GRAY_GLAZED_TERRACOTTA);
                    p_259444_.accept(Items.BLACK_GLAZED_TERRACOTTA);
                    p_259444_.accept(Items.BROWN_GLAZED_TERRACOTTA);
                    p_259444_.accept(Items.RED_GLAZED_TERRACOTTA);
                    p_259444_.accept(Items.ORANGE_GLAZED_TERRACOTTA);
                    p_259444_.accept(Items.YELLOW_GLAZED_TERRACOTTA);
                    p_259444_.accept(Items.LIME_GLAZED_TERRACOTTA);
                    p_259444_.accept(Items.GREEN_GLAZED_TERRACOTTA);
                    p_259444_.accept(Items.CYAN_GLAZED_TERRACOTTA);
                    p_259444_.accept(Items.LIGHT_BLUE_GLAZED_TERRACOTTA);
                    p_259444_.accept(Items.BLUE_GLAZED_TERRACOTTA);
                    p_259444_.accept(Items.PURPLE_GLAZED_TERRACOTTA);
                    p_259444_.accept(Items.MAGENTA_GLAZED_TERRACOTTA);
                    p_259444_.accept(Items.PINK_GLAZED_TERRACOTTA);
                    p_259444_.accept(Items.GLASS);
                    p_259444_.accept(Items.TINTED_GLASS);
                    p_259444_.accept(Items.WHITE_STAINED_GLASS);
                    p_259444_.accept(Items.LIGHT_GRAY_STAINED_GLASS);
                    p_259444_.accept(Items.GRAY_STAINED_GLASS);
                    p_259444_.accept(Items.BLACK_STAINED_GLASS);
                    p_259444_.accept(Items.BROWN_STAINED_GLASS);
                    p_259444_.accept(Items.RED_STAINED_GLASS);
                    p_259444_.accept(Items.ORANGE_STAINED_GLASS);
                    p_259444_.accept(Items.YELLOW_STAINED_GLASS);
                    p_259444_.accept(Items.LIME_STAINED_GLASS);
                    p_259444_.accept(Items.GREEN_STAINED_GLASS);
                    p_259444_.accept(Items.CYAN_STAINED_GLASS);
                    p_259444_.accept(Items.LIGHT_BLUE_STAINED_GLASS);
                    p_259444_.accept(Items.BLUE_STAINED_GLASS);
                    p_259444_.accept(Items.PURPLE_STAINED_GLASS);
                    p_259444_.accept(Items.MAGENTA_STAINED_GLASS);
                    p_259444_.accept(Items.PINK_STAINED_GLASS);
                    p_259444_.accept(Items.GLASS_PANE);
                    p_259444_.accept(Items.WHITE_STAINED_GLASS_PANE);
                    p_259444_.accept(Items.LIGHT_GRAY_STAINED_GLASS_PANE);
                    p_259444_.accept(Items.GRAY_STAINED_GLASS_PANE);
                    p_259444_.accept(Items.BLACK_STAINED_GLASS_PANE);
                    p_259444_.accept(Items.BROWN_STAINED_GLASS_PANE);
                    p_259444_.accept(Items.RED_STAINED_GLASS_PANE);
                    p_259444_.accept(Items.ORANGE_STAINED_GLASS_PANE);
                    p_259444_.accept(Items.YELLOW_STAINED_GLASS_PANE);
                    p_259444_.accept(Items.LIME_STAINED_GLASS_PANE);
                    p_259444_.accept(Items.GREEN_STAINED_GLASS_PANE);
                    p_259444_.accept(Items.CYAN_STAINED_GLASS_PANE);
                    p_259444_.accept(Items.LIGHT_BLUE_STAINED_GLASS_PANE);
                    p_259444_.accept(Items.BLUE_STAINED_GLASS_PANE);
                    p_259444_.accept(Items.PURPLE_STAINED_GLASS_PANE);
                    p_259444_.accept(Items.MAGENTA_STAINED_GLASS_PANE);
                    p_259444_.accept(Items.PINK_STAINED_GLASS_PANE);
                    p_259444_.accept(Items.SHULKER_BOX);
                    p_259444_.accept(Items.WHITE_SHULKER_BOX);
                    p_259444_.accept(Items.LIGHT_GRAY_SHULKER_BOX);
                    p_259444_.accept(Items.GRAY_SHULKER_BOX);
                    p_259444_.accept(Items.BLACK_SHULKER_BOX);
                    p_259444_.accept(Items.BROWN_SHULKER_BOX);
                    p_259444_.accept(Items.RED_SHULKER_BOX);
                    p_259444_.accept(Items.ORANGE_SHULKER_BOX);
                    p_259444_.accept(Items.YELLOW_SHULKER_BOX);
                    p_259444_.accept(Items.LIME_SHULKER_BOX);
                    p_259444_.accept(Items.GREEN_SHULKER_BOX);
                    p_259444_.accept(Items.CYAN_SHULKER_BOX);
                    p_259444_.accept(Items.LIGHT_BLUE_SHULKER_BOX);
                    p_259444_.accept(Items.BLUE_SHULKER_BOX);
                    p_259444_.accept(Items.PURPLE_SHULKER_BOX);
                    p_259444_.accept(Items.MAGENTA_SHULKER_BOX);
                    p_259444_.accept(Items.PINK_SHULKER_BOX);
                    p_259444_.accept(Items.WHITE_BED);
                    p_259444_.accept(Items.LIGHT_GRAY_BED);
                    p_259444_.accept(Items.GRAY_BED);
                    p_259444_.accept(Items.BLACK_BED);
                    p_259444_.accept(Items.BROWN_BED);
                    p_259444_.accept(Items.RED_BED);
                    p_259444_.accept(Items.ORANGE_BED);
                    p_259444_.accept(Items.YELLOW_BED);
                    p_259444_.accept(Items.LIME_BED);
                    p_259444_.accept(Items.GREEN_BED);
                    p_259444_.accept(Items.CYAN_BED);
                    p_259444_.accept(Items.LIGHT_BLUE_BED);
                    p_259444_.accept(Items.BLUE_BED);
                    p_259444_.accept(Items.PURPLE_BED);
                    p_259444_.accept(Items.MAGENTA_BED);
                    p_259444_.accept(Items.PINK_BED);
                    p_259444_.accept(Items.CANDLE);
                    p_259444_.accept(Items.WHITE_CANDLE);
                    p_259444_.accept(Items.LIGHT_GRAY_CANDLE);
                    p_259444_.accept(Items.GRAY_CANDLE);
                    p_259444_.accept(Items.BLACK_CANDLE);
                    p_259444_.accept(Items.BROWN_CANDLE);
                    p_259444_.accept(Items.RED_CANDLE);
                    p_259444_.accept(Items.ORANGE_CANDLE);
                    p_259444_.accept(Items.YELLOW_CANDLE);
                    p_259444_.accept(Items.LIME_CANDLE);
                    p_259444_.accept(Items.GREEN_CANDLE);
                    p_259444_.accept(Items.CYAN_CANDLE);
                    p_259444_.accept(Items.LIGHT_BLUE_CANDLE);
                    p_259444_.accept(Items.BLUE_CANDLE);
                    p_259444_.accept(Items.PURPLE_CANDLE);
                    p_259444_.accept(Items.MAGENTA_CANDLE);
                    p_259444_.accept(Items.PINK_CANDLE);
                    p_259444_.accept(Items.WHITE_BANNER);
                    p_259444_.accept(Items.LIGHT_GRAY_BANNER);
                    p_259444_.accept(Items.GRAY_BANNER);
                    p_259444_.accept(Items.BLACK_BANNER);
                    p_259444_.accept(Items.BROWN_BANNER);
                    p_259444_.accept(Items.RED_BANNER);
                    p_259444_.accept(Items.ORANGE_BANNER);
                    p_259444_.accept(Items.YELLOW_BANNER);
                    p_259444_.accept(Items.LIME_BANNER);
                    p_259444_.accept(Items.GREEN_BANNER);
                    p_259444_.accept(Items.CYAN_BANNER);
                    p_259444_.accept(Items.LIGHT_BLUE_BANNER);
                    p_259444_.accept(Items.BLUE_BANNER);
                    p_259444_.accept(Items.PURPLE_BANNER);
                    p_259444_.accept(Items.MAGENTA_BANNER);
                    p_259444_.accept(Items.PINK_BANNER);
                })
                .build()
        );
        Registry.register(
            pRegistry,
            NATURAL_BLOCKS,
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 2)
                .title(Component.translatable("itemGroup.natural"))
                .icon(() -> new ItemStack(Blocks.GRASS_BLOCK))
                .displayItems((p_288945_, p_288946_) -> {
                    p_288946_.accept(Items.GRASS_BLOCK);
                    p_288946_.accept(Items.PODZOL);
                    p_288946_.accept(Items.MYCELIUM);
                    p_288946_.accept(Items.DIRT_PATH);
                    p_288946_.accept(Items.DIRT);
                    p_288946_.accept(Items.COARSE_DIRT);
                    p_288946_.accept(Items.ROOTED_DIRT);
                    p_288946_.accept(Items.FARMLAND);
                    p_288946_.accept(Items.MUD);
                    p_288946_.accept(Items.CLAY);
                    p_288946_.accept(Items.GRAVEL);
                    p_288946_.accept(Items.SAND);
                    p_288946_.accept(Items.SANDSTONE);
                    p_288946_.accept(Items.RED_SAND);
                    p_288946_.accept(Items.RED_SANDSTONE);
                    p_288946_.accept(Items.ICE);
                    p_288946_.accept(Items.PACKED_ICE);
                    p_288946_.accept(Items.BLUE_ICE);
                    p_288946_.accept(Items.SNOW_BLOCK);
                    p_288946_.accept(Items.SNOW);
                    p_288946_.accept(Items.MOSS_BLOCK);
                    p_288946_.accept(Items.MOSS_CARPET);
                    p_288946_.accept(Items.STONE);
                    p_288946_.accept(Items.DEEPSLATE);
                    p_288946_.accept(Items.GRANITE);
                    p_288946_.accept(Items.DIORITE);
                    p_288946_.accept(Items.ANDESITE);
                    p_288946_.accept(Items.CALCITE);
                    p_288946_.accept(Items.TUFF);
                    p_288946_.accept(Items.DRIPSTONE_BLOCK);
                    p_288946_.accept(Items.POINTED_DRIPSTONE);
                    p_288946_.accept(Items.PRISMARINE);
                    p_288946_.accept(Items.MAGMA_BLOCK);
                    p_288946_.accept(Items.OBSIDIAN);
                    p_288946_.accept(Items.CRYING_OBSIDIAN);
                    p_288946_.accept(Items.NETHERRACK);
                    p_288946_.accept(Items.CRIMSON_NYLIUM);
                    p_288946_.accept(Items.WARPED_NYLIUM);
                    p_288946_.accept(Items.SOUL_SAND);
                    p_288946_.accept(Items.SOUL_SOIL);
                    p_288946_.accept(Items.BONE_BLOCK);
                    p_288946_.accept(Items.BLACKSTONE);
                    p_288946_.accept(Items.BASALT);
                    p_288946_.accept(Items.SMOOTH_BASALT);
                    p_288946_.accept(Items.END_STONE);
                    p_288946_.accept(Items.COAL_ORE);
                    p_288946_.accept(Items.DEEPSLATE_COAL_ORE);
                    p_288946_.accept(Items.IRON_ORE);
                    p_288946_.accept(Items.DEEPSLATE_IRON_ORE);
                    p_288946_.accept(Items.COPPER_ORE);
                    p_288946_.accept(Items.DEEPSLATE_COPPER_ORE);
                    p_288946_.accept(Items.GOLD_ORE);
                    p_288946_.accept(Items.DEEPSLATE_GOLD_ORE);
                    p_288946_.accept(Items.REDSTONE_ORE);
                    p_288946_.accept(Items.DEEPSLATE_REDSTONE_ORE);
                    p_288946_.accept(Items.EMERALD_ORE);
                    p_288946_.accept(Items.DEEPSLATE_EMERALD_ORE);
                    p_288946_.accept(Items.LAPIS_ORE);
                    p_288946_.accept(Items.DEEPSLATE_LAPIS_ORE);
                    p_288946_.accept(Items.DIAMOND_ORE);
                    p_288946_.accept(Items.DEEPSLATE_DIAMOND_ORE);
                    p_288946_.accept(Items.NETHER_GOLD_ORE);
                    p_288946_.accept(Items.NETHER_QUARTZ_ORE);
                    p_288946_.accept(Items.ANCIENT_DEBRIS);
                    p_288946_.accept(Items.RAW_IRON_BLOCK);
                    p_288946_.accept(Items.RAW_COPPER_BLOCK);
                    p_288946_.accept(Items.RAW_GOLD_BLOCK);
                    p_288946_.accept(Items.GLOWSTONE);
                    p_288946_.accept(Items.AMETHYST_BLOCK);
                    p_288946_.accept(Items.BUDDING_AMETHYST);
                    p_288946_.accept(Items.SMALL_AMETHYST_BUD);
                    p_288946_.accept(Items.MEDIUM_AMETHYST_BUD);
                    p_288946_.accept(Items.LARGE_AMETHYST_BUD);
                    p_288946_.accept(Items.AMETHYST_CLUSTER);
                    p_288946_.accept(Items.OAK_LOG);
                    p_288946_.accept(Items.SPRUCE_LOG);
                    p_288946_.accept(Items.BIRCH_LOG);
                    p_288946_.accept(Items.JUNGLE_LOG);
                    p_288946_.accept(Items.ACACIA_LOG);
                    p_288946_.accept(Items.DARK_OAK_LOG);
                    p_288946_.accept(Items.MANGROVE_LOG);
                    p_288946_.accept(Items.MANGROVE_ROOTS);
                    p_288946_.accept(Items.MUDDY_MANGROVE_ROOTS);
                    p_288946_.accept(Items.CHERRY_LOG);
                    p_288946_.accept(Items.MUSHROOM_STEM);
                    p_288946_.accept(Items.CRIMSON_STEM);
                    p_288946_.accept(Items.WARPED_STEM);
                    p_288946_.accept(Items.OAK_LEAVES);
                    p_288946_.accept(Items.SPRUCE_LEAVES);
                    p_288946_.accept(Items.BIRCH_LEAVES);
                    p_288946_.accept(Items.JUNGLE_LEAVES);
                    p_288946_.accept(Items.ACACIA_LEAVES);
                    p_288946_.accept(Items.DARK_OAK_LEAVES);
                    p_288946_.accept(Items.MANGROVE_LEAVES);
                    p_288946_.accept(Items.CHERRY_LEAVES);
                    p_288946_.accept(Items.AZALEA_LEAVES);
                    p_288946_.accept(Items.FLOWERING_AZALEA_LEAVES);
                    p_288946_.accept(Items.BROWN_MUSHROOM_BLOCK);
                    p_288946_.accept(Items.RED_MUSHROOM_BLOCK);
                    p_288946_.accept(Items.NETHER_WART_BLOCK);
                    p_288946_.accept(Items.WARPED_WART_BLOCK);
                    p_288946_.accept(Items.SHROOMLIGHT);
                    p_288946_.accept(Items.OAK_SAPLING);
                    p_288946_.accept(Items.SPRUCE_SAPLING);
                    p_288946_.accept(Items.BIRCH_SAPLING);
                    p_288946_.accept(Items.JUNGLE_SAPLING);
                    p_288946_.accept(Items.ACACIA_SAPLING);
                    p_288946_.accept(Items.DARK_OAK_SAPLING);
                    p_288946_.accept(Items.MANGROVE_PROPAGULE);
                    p_288946_.accept(Items.CHERRY_SAPLING);
                    p_288946_.accept(Items.AZALEA);
                    p_288946_.accept(Items.FLOWERING_AZALEA);
                    p_288946_.accept(Items.BROWN_MUSHROOM);
                    p_288946_.accept(Items.RED_MUSHROOM);
                    p_288946_.accept(Items.CRIMSON_FUNGUS);
                    p_288946_.accept(Items.WARPED_FUNGUS);
                    p_288946_.accept(Items.SHORT_GRASS);
                    p_288946_.accept(Items.FERN);
                    p_288946_.accept(Items.DEAD_BUSH);
                    p_288946_.accept(Items.DANDELION);
                    p_288946_.accept(Items.POPPY);
                    p_288946_.accept(Items.BLUE_ORCHID);
                    p_288946_.accept(Items.ALLIUM);
                    p_288946_.accept(Items.AZURE_BLUET);
                    p_288946_.accept(Items.RED_TULIP);
                    p_288946_.accept(Items.ORANGE_TULIP);
                    p_288946_.accept(Items.WHITE_TULIP);
                    p_288946_.accept(Items.PINK_TULIP);
                    p_288946_.accept(Items.OXEYE_DAISY);
                    p_288946_.accept(Items.CORNFLOWER);
                    p_288946_.accept(Items.LILY_OF_THE_VALLEY);
                    p_288946_.accept(Items.TORCHFLOWER);
                    p_288946_.accept(Items.WITHER_ROSE);
                    p_288946_.accept(Items.PINK_PETALS);
                    p_288946_.accept(Items.SPORE_BLOSSOM);
                    p_288946_.accept(Items.BAMBOO);
                    p_288946_.accept(Items.SUGAR_CANE);
                    p_288946_.accept(Items.CACTUS);
                    p_288946_.accept(Items.CRIMSON_ROOTS);
                    p_288946_.accept(Items.WARPED_ROOTS);
                    p_288946_.accept(Items.NETHER_SPROUTS);
                    p_288946_.accept(Items.WEEPING_VINES);
                    p_288946_.accept(Items.TWISTING_VINES);
                    p_288946_.accept(Items.VINE);
                    p_288946_.accept(Items.TALL_GRASS);
                    p_288946_.accept(Items.LARGE_FERN);
                    p_288946_.accept(Items.SUNFLOWER);
                    p_288946_.accept(Items.LILAC);
                    p_288946_.accept(Items.ROSE_BUSH);
                    p_288946_.accept(Items.PEONY);
                    p_288946_.accept(Items.PITCHER_PLANT);
                    p_288946_.accept(Items.BIG_DRIPLEAF);
                    p_288946_.accept(Items.SMALL_DRIPLEAF);
                    p_288946_.accept(Items.CHORUS_PLANT);
                    p_288946_.accept(Items.CHORUS_FLOWER);
                    p_288946_.accept(Items.GLOW_LICHEN);
                    p_288946_.accept(Items.HANGING_ROOTS);
                    p_288946_.accept(Items.FROGSPAWN);
                    p_288946_.accept(Items.TURTLE_EGG);
                    p_288946_.accept(Items.SNIFFER_EGG);
                    p_288946_.accept(Items.WHEAT_SEEDS);
                    p_288946_.accept(Items.COCOA_BEANS);
                    p_288946_.accept(Items.PUMPKIN_SEEDS);
                    p_288946_.accept(Items.MELON_SEEDS);
                    p_288946_.accept(Items.BEETROOT_SEEDS);
                    p_288946_.accept(Items.TORCHFLOWER_SEEDS);
                    p_288946_.accept(Items.PITCHER_POD);
                    p_288946_.accept(Items.GLOW_BERRIES);
                    p_288946_.accept(Items.SWEET_BERRIES);
                    p_288946_.accept(Items.NETHER_WART);
                    p_288946_.accept(Items.LILY_PAD);
                    p_288946_.accept(Items.SEAGRASS);
                    p_288946_.accept(Items.SEA_PICKLE);
                    p_288946_.accept(Items.KELP);
                    p_288946_.accept(Items.DRIED_KELP_BLOCK);
                    p_288946_.accept(Items.TUBE_CORAL_BLOCK);
                    p_288946_.accept(Items.BRAIN_CORAL_BLOCK);
                    p_288946_.accept(Items.BUBBLE_CORAL_BLOCK);
                    p_288946_.accept(Items.FIRE_CORAL_BLOCK);
                    p_288946_.accept(Items.HORN_CORAL_BLOCK);
                    p_288946_.accept(Items.DEAD_TUBE_CORAL_BLOCK);
                    p_288946_.accept(Items.DEAD_BRAIN_CORAL_BLOCK);
                    p_288946_.accept(Items.DEAD_BUBBLE_CORAL_BLOCK);
                    p_288946_.accept(Items.DEAD_FIRE_CORAL_BLOCK);
                    p_288946_.accept(Items.DEAD_HORN_CORAL_BLOCK);
                    p_288946_.accept(Items.TUBE_CORAL);
                    p_288946_.accept(Items.BRAIN_CORAL);
                    p_288946_.accept(Items.BUBBLE_CORAL);
                    p_288946_.accept(Items.FIRE_CORAL);
                    p_288946_.accept(Items.HORN_CORAL);
                    p_288946_.accept(Items.DEAD_TUBE_CORAL);
                    p_288946_.accept(Items.DEAD_BRAIN_CORAL);
                    p_288946_.accept(Items.DEAD_BUBBLE_CORAL);
                    p_288946_.accept(Items.DEAD_FIRE_CORAL);
                    p_288946_.accept(Items.DEAD_HORN_CORAL);
                    p_288946_.accept(Items.TUBE_CORAL_FAN);
                    p_288946_.accept(Items.BRAIN_CORAL_FAN);
                    p_288946_.accept(Items.BUBBLE_CORAL_FAN);
                    p_288946_.accept(Items.FIRE_CORAL_FAN);
                    p_288946_.accept(Items.HORN_CORAL_FAN);
                    p_288946_.accept(Items.DEAD_TUBE_CORAL_FAN);
                    p_288946_.accept(Items.DEAD_BRAIN_CORAL_FAN);
                    p_288946_.accept(Items.DEAD_BUBBLE_CORAL_FAN);
                    p_288946_.accept(Items.DEAD_FIRE_CORAL_FAN);
                    p_288946_.accept(Items.DEAD_HORN_CORAL_FAN);
                    p_288946_.accept(Items.SPONGE);
                    p_288946_.accept(Items.WET_SPONGE);
                    p_288946_.accept(Items.MELON);
                    p_288946_.accept(Items.PUMPKIN);
                    p_288946_.accept(Items.CARVED_PUMPKIN);
                    p_288946_.accept(Items.JACK_O_LANTERN);
                    p_288946_.accept(Items.HAY_BLOCK);
                    p_288946_.accept(Items.BEE_NEST);
                    p_288946_.accept(Items.HONEYCOMB_BLOCK);
                    p_288946_.accept(Items.SLIME_BLOCK);
                    p_288946_.accept(Items.HONEY_BLOCK);
                    p_288946_.accept(Items.OCHRE_FROGLIGHT);
                    p_288946_.accept(Items.VERDANT_FROGLIGHT);
                    p_288946_.accept(Items.PEARLESCENT_FROGLIGHT);
                    p_288946_.accept(Items.SCULK);
                    p_288946_.accept(Items.SCULK_VEIN);
                    p_288946_.accept(Items.SCULK_CATALYST);
                    p_288946_.accept(Items.SCULK_SHRIEKER);
                    p_288946_.accept(Items.SCULK_SENSOR);
                    p_288946_.accept(Items.COBWEB);
                    p_288946_.accept(Items.BEDROCK);
                })
                .build()
        );
        Registry.register(
            pRegistry,
            FUNCTIONAL_BLOCKS,
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 3)
                .title(Component.translatable("itemGroup.functional"))
                .icon(() -> new ItemStack(Items.OAK_SIGN))
                .displayItems(
                    (p_327109_, p_327110_) -> {
                        p_327110_.accept(Items.TORCH);
                        p_327110_.accept(Items.SOUL_TORCH);
                        p_327110_.accept(Items.REDSTONE_TORCH);
                        p_327110_.accept(Items.LANTERN);
                        p_327110_.accept(Items.SOUL_LANTERN);
                        p_327110_.accept(Items.CHAIN);
                        p_327110_.accept(Items.END_ROD);
                        p_327110_.accept(Items.SEA_LANTERN);
                        p_327110_.accept(Items.REDSTONE_LAMP);
                        p_327110_.accept(Items.COPPER_BULB);
                        p_327110_.accept(Items.EXPOSED_COPPER_BULB);
                        p_327110_.accept(Items.WEATHERED_COPPER_BULB);
                        p_327110_.accept(Items.OXIDIZED_COPPER_BULB);
                        p_327110_.accept(Items.WAXED_COPPER_BULB);
                        p_327110_.accept(Items.WAXED_EXPOSED_COPPER_BULB);
                        p_327110_.accept(Items.WAXED_WEATHERED_COPPER_BULB);
                        p_327110_.accept(Items.WAXED_OXIDIZED_COPPER_BULB);
                        p_327110_.accept(Items.GLOWSTONE);
                        p_327110_.accept(Items.SHROOMLIGHT);
                        p_327110_.accept(Items.OCHRE_FROGLIGHT);
                        p_327110_.accept(Items.VERDANT_FROGLIGHT);
                        p_327110_.accept(Items.PEARLESCENT_FROGLIGHT);
                        p_327110_.accept(Items.CRYING_OBSIDIAN);
                        p_327110_.accept(Items.GLOW_LICHEN);
                        p_327110_.accept(Items.MAGMA_BLOCK);
                        p_327110_.accept(Items.CRAFTING_TABLE);
                        p_327110_.accept(Items.STONECUTTER);
                        p_327110_.accept(Items.CARTOGRAPHY_TABLE);
                        p_327110_.accept(Items.FLETCHING_TABLE);
                        p_327110_.accept(Items.SMITHING_TABLE);
                        p_327110_.accept(Items.GRINDSTONE);
                        p_327110_.accept(Items.LOOM);
                        p_327110_.accept(Items.FURNACE);
                        p_327110_.accept(Items.SMOKER);
                        p_327110_.accept(Items.BLAST_FURNACE);
                        p_327110_.accept(Items.CAMPFIRE);
                        p_327110_.accept(Items.SOUL_CAMPFIRE);
                        p_327110_.accept(Items.ANVIL);
                        p_327110_.accept(Items.CHIPPED_ANVIL);
                        p_327110_.accept(Items.DAMAGED_ANVIL);
                        p_327110_.accept(Items.COMPOSTER);
                        p_327110_.accept(Items.NOTE_BLOCK);
                        p_327110_.accept(Items.JUKEBOX);
                        p_327110_.accept(Items.ENCHANTING_TABLE);
                        p_327110_.accept(Items.END_CRYSTAL);
                        p_327110_.accept(Items.BREWING_STAND);
                        p_327110_.accept(Items.CAULDRON);
                        p_327110_.accept(Items.BELL);
                        p_327110_.accept(Items.BEACON);
                        p_327110_.accept(Items.CONDUIT);
                        p_327110_.accept(Items.LODESTONE);
                        p_327110_.accept(Items.LADDER);
                        p_327110_.accept(Items.SCAFFOLDING);
                        p_327110_.accept(Items.BEE_NEST);
                        p_327110_.accept(Items.BEEHIVE);
                        p_327110_.accept(Items.SUSPICIOUS_SAND);
                        p_327110_.accept(Items.SUSPICIOUS_GRAVEL);
                        p_327110_.accept(Items.LIGHTNING_ROD);
                        p_327110_.accept(Items.FLOWER_POT);
                        p_327110_.accept(Items.DECORATED_POT);
                        p_327110_.accept(Items.ARMOR_STAND);
                        p_327110_.accept(Items.ITEM_FRAME);
                        p_327110_.accept(Items.GLOW_ITEM_FRAME);
                        p_327110_.accept(Items.PAINTING);
                        p_327109_.holders()
                            .lookup(Registries.PAINTING_VARIANT)
                            .ifPresent(
                                p_270026_ -> generatePresetPaintings(
                                        p_327110_,
                                        (HolderLookup.RegistryLookup<PaintingVariant>)p_270026_,
                                        p_270037_ -> p_270037_.is(PaintingVariantTags.PLACEABLE),
                                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                                    )
                            );
                        p_327110_.accept(Items.BOOKSHELF);
                        p_327110_.accept(Items.CHISELED_BOOKSHELF);
                        p_327110_.accept(Items.LECTERN);
                        p_327110_.accept(Items.TINTED_GLASS);
                        p_327110_.accept(Items.OAK_SIGN);
                        p_327110_.accept(Items.OAK_HANGING_SIGN);
                        p_327110_.accept(Items.SPRUCE_SIGN);
                        p_327110_.accept(Items.SPRUCE_HANGING_SIGN);
                        p_327110_.accept(Items.BIRCH_SIGN);
                        p_327110_.accept(Items.BIRCH_HANGING_SIGN);
                        p_327110_.accept(Items.JUNGLE_SIGN);
                        p_327110_.accept(Items.JUNGLE_HANGING_SIGN);
                        p_327110_.accept(Items.ACACIA_SIGN);
                        p_327110_.accept(Items.ACACIA_HANGING_SIGN);
                        p_327110_.accept(Items.DARK_OAK_SIGN);
                        p_327110_.accept(Items.DARK_OAK_HANGING_SIGN);
                        p_327110_.accept(Items.MANGROVE_SIGN);
                        p_327110_.accept(Items.MANGROVE_HANGING_SIGN);
                        p_327110_.accept(Items.CHERRY_SIGN);
                        p_327110_.accept(Items.CHERRY_HANGING_SIGN);
                        p_327110_.accept(Items.BAMBOO_SIGN);
                        p_327110_.accept(Items.BAMBOO_HANGING_SIGN);
                        p_327110_.accept(Items.CRIMSON_SIGN);
                        p_327110_.accept(Items.CRIMSON_HANGING_SIGN);
                        p_327110_.accept(Items.WARPED_SIGN);
                        p_327110_.accept(Items.WARPED_HANGING_SIGN);
                        p_327110_.accept(Items.CHEST);
                        p_327110_.accept(Items.BARREL);
                        p_327110_.accept(Items.ENDER_CHEST);
                        p_327110_.accept(Items.SHULKER_BOX);
                        p_327110_.accept(Items.WHITE_SHULKER_BOX);
                        p_327110_.accept(Items.LIGHT_GRAY_SHULKER_BOX);
                        p_327110_.accept(Items.GRAY_SHULKER_BOX);
                        p_327110_.accept(Items.BLACK_SHULKER_BOX);
                        p_327110_.accept(Items.BROWN_SHULKER_BOX);
                        p_327110_.accept(Items.RED_SHULKER_BOX);
                        p_327110_.accept(Items.ORANGE_SHULKER_BOX);
                        p_327110_.accept(Items.YELLOW_SHULKER_BOX);
                        p_327110_.accept(Items.LIME_SHULKER_BOX);
                        p_327110_.accept(Items.GREEN_SHULKER_BOX);
                        p_327110_.accept(Items.CYAN_SHULKER_BOX);
                        p_327110_.accept(Items.LIGHT_BLUE_SHULKER_BOX);
                        p_327110_.accept(Items.BLUE_SHULKER_BOX);
                        p_327110_.accept(Items.PURPLE_SHULKER_BOX);
                        p_327110_.accept(Items.MAGENTA_SHULKER_BOX);
                        p_327110_.accept(Items.PINK_SHULKER_BOX);
                        p_327110_.accept(Items.RESPAWN_ANCHOR);
                        p_327110_.accept(Items.WHITE_BED);
                        p_327110_.accept(Items.LIGHT_GRAY_BED);
                        p_327110_.accept(Items.GRAY_BED);
                        p_327110_.accept(Items.BLACK_BED);
                        p_327110_.accept(Items.BROWN_BED);
                        p_327110_.accept(Items.RED_BED);
                        p_327110_.accept(Items.ORANGE_BED);
                        p_327110_.accept(Items.YELLOW_BED);
                        p_327110_.accept(Items.LIME_BED);
                        p_327110_.accept(Items.GREEN_BED);
                        p_327110_.accept(Items.CYAN_BED);
                        p_327110_.accept(Items.LIGHT_BLUE_BED);
                        p_327110_.accept(Items.BLUE_BED);
                        p_327110_.accept(Items.PURPLE_BED);
                        p_327110_.accept(Items.MAGENTA_BED);
                        p_327110_.accept(Items.PINK_BED);
                        p_327110_.accept(Items.CANDLE);
                        p_327110_.accept(Items.WHITE_CANDLE);
                        p_327110_.accept(Items.LIGHT_GRAY_CANDLE);
                        p_327110_.accept(Items.GRAY_CANDLE);
                        p_327110_.accept(Items.BLACK_CANDLE);
                        p_327110_.accept(Items.BROWN_CANDLE);
                        p_327110_.accept(Items.RED_CANDLE);
                        p_327110_.accept(Items.ORANGE_CANDLE);
                        p_327110_.accept(Items.YELLOW_CANDLE);
                        p_327110_.accept(Items.LIME_CANDLE);
                        p_327110_.accept(Items.GREEN_CANDLE);
                        p_327110_.accept(Items.CYAN_CANDLE);
                        p_327110_.accept(Items.LIGHT_BLUE_CANDLE);
                        p_327110_.accept(Items.BLUE_CANDLE);
                        p_327110_.accept(Items.PURPLE_CANDLE);
                        p_327110_.accept(Items.MAGENTA_CANDLE);
                        p_327110_.accept(Items.PINK_CANDLE);
                        p_327110_.accept(Items.WHITE_BANNER);
                        p_327110_.accept(Items.LIGHT_GRAY_BANNER);
                        p_327110_.accept(Items.GRAY_BANNER);
                        p_327110_.accept(Items.BLACK_BANNER);
                        p_327110_.accept(Items.BROWN_BANNER);
                        p_327110_.accept(Items.RED_BANNER);
                        p_327110_.accept(Items.ORANGE_BANNER);
                        p_327110_.accept(Items.YELLOW_BANNER);
                        p_327110_.accept(Items.LIME_BANNER);
                        p_327110_.accept(Items.GREEN_BANNER);
                        p_327110_.accept(Items.CYAN_BANNER);
                        p_327110_.accept(Items.LIGHT_BLUE_BANNER);
                        p_327110_.accept(Items.BLUE_BANNER);
                        p_327110_.accept(Items.PURPLE_BANNER);
                        p_327110_.accept(Items.MAGENTA_BANNER);
                        p_327110_.accept(Items.PINK_BANNER);
                        p_327110_.accept(Raid.getLeaderBannerInstance(p_327109_.holders().lookupOrThrow(Registries.BANNER_PATTERN)));
                        p_327110_.accept(Items.SKELETON_SKULL);
                        p_327110_.accept(Items.WITHER_SKELETON_SKULL);
                        p_327110_.accept(Items.PLAYER_HEAD);
                        p_327110_.accept(Items.ZOMBIE_HEAD);
                        p_327110_.accept(Items.CREEPER_HEAD);
                        p_327110_.accept(Items.PIGLIN_HEAD);
                        p_327110_.accept(Items.DRAGON_HEAD);
                        p_327110_.accept(Items.DRAGON_EGG);
                        p_327110_.accept(Items.END_PORTAL_FRAME);
                        p_327110_.accept(Items.ENDER_EYE);
                        p_327110_.accept(Items.VAULT);
                        p_327110_.accept(Items.INFESTED_STONE);
                        p_327110_.accept(Items.INFESTED_COBBLESTONE);
                        p_327110_.accept(Items.INFESTED_STONE_BRICKS);
                        p_327110_.accept(Items.INFESTED_MOSSY_STONE_BRICKS);
                        p_327110_.accept(Items.INFESTED_CRACKED_STONE_BRICKS);
                        p_327110_.accept(Items.INFESTED_CHISELED_STONE_BRICKS);
                        p_327110_.accept(Items.INFESTED_DEEPSLATE);
                    }
                )
                .build()
        );
        Registry.register(
            pRegistry,
            REDSTONE_BLOCKS,
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 4)
                .title(Component.translatable("itemGroup.redstone"))
                .icon(() -> new ItemStack(Items.REDSTONE))
                .displayItems((p_270190_, p_259709_) -> {
                    p_259709_.accept(Items.REDSTONE);
                    p_259709_.accept(Items.REDSTONE_TORCH);
                    p_259709_.accept(Items.REDSTONE_BLOCK);
                    p_259709_.accept(Items.REPEATER);
                    p_259709_.accept(Items.COMPARATOR);
                    p_259709_.accept(Items.TARGET);
                    p_259709_.accept(Items.WAXED_COPPER_BULB);
                    p_259709_.accept(Items.WAXED_EXPOSED_COPPER_BULB);
                    p_259709_.accept(Items.WAXED_WEATHERED_COPPER_BULB);
                    p_259709_.accept(Items.WAXED_OXIDIZED_COPPER_BULB);
                    p_259709_.accept(Items.LEVER);
                    p_259709_.accept(Items.OAK_BUTTON);
                    p_259709_.accept(Items.STONE_BUTTON);
                    p_259709_.accept(Items.OAK_PRESSURE_PLATE);
                    p_259709_.accept(Items.STONE_PRESSURE_PLATE);
                    p_259709_.accept(Items.LIGHT_WEIGHTED_PRESSURE_PLATE);
                    p_259709_.accept(Items.HEAVY_WEIGHTED_PRESSURE_PLATE);
                    p_259709_.accept(Items.SCULK_SENSOR);
                    p_259709_.accept(Items.CALIBRATED_SCULK_SENSOR);
                    p_259709_.accept(Items.SCULK_SHRIEKER);
                    p_259709_.accept(Items.AMETHYST_BLOCK);
                    p_259709_.accept(Items.WHITE_WOOL);
                    p_259709_.accept(Items.TRIPWIRE_HOOK);
                    p_259709_.accept(Items.STRING);
                    p_259709_.accept(Items.LECTERN);
                    p_259709_.accept(Items.DAYLIGHT_DETECTOR);
                    p_259709_.accept(Items.LIGHTNING_ROD);
                    p_259709_.accept(Items.PISTON);
                    p_259709_.accept(Items.STICKY_PISTON);
                    p_259709_.accept(Items.SLIME_BLOCK);
                    p_259709_.accept(Items.HONEY_BLOCK);
                    p_259709_.accept(Items.DISPENSER);
                    p_259709_.accept(Items.DROPPER);
                    p_259709_.accept(Items.CRAFTER);
                    p_259709_.accept(Items.HOPPER);
                    p_259709_.accept(Items.CHEST);
                    p_259709_.accept(Items.BARREL);
                    p_259709_.accept(Items.CHISELED_BOOKSHELF);
                    p_259709_.accept(Items.FURNACE);
                    p_259709_.accept(Items.TRAPPED_CHEST);
                    p_259709_.accept(Items.JUKEBOX);
                    p_259709_.accept(Items.DECORATED_POT);
                    p_259709_.accept(Items.OBSERVER);
                    p_259709_.accept(Items.NOTE_BLOCK);
                    p_259709_.accept(Items.COMPOSTER);
                    p_259709_.accept(Items.CAULDRON);
                    p_259709_.accept(Items.RAIL);
                    p_259709_.accept(Items.POWERED_RAIL);
                    p_259709_.accept(Items.DETECTOR_RAIL);
                    p_259709_.accept(Items.ACTIVATOR_RAIL);
                    p_259709_.accept(Items.MINECART);
                    p_259709_.accept(Items.HOPPER_MINECART);
                    p_259709_.accept(Items.CHEST_MINECART);
                    p_259709_.accept(Items.FURNACE_MINECART);
                    p_259709_.accept(Items.TNT_MINECART);
                    p_259709_.accept(Items.OAK_CHEST_BOAT);
                    p_259709_.accept(Items.BAMBOO_CHEST_RAFT);
                    p_259709_.accept(Items.OAK_DOOR);
                    p_259709_.accept(Items.IRON_DOOR);
                    p_259709_.accept(Items.OAK_FENCE_GATE);
                    p_259709_.accept(Items.OAK_TRAPDOOR);
                    p_259709_.accept(Items.IRON_TRAPDOOR);
                    p_259709_.accept(Items.TNT);
                    p_259709_.accept(Items.REDSTONE_LAMP);
                    p_259709_.accept(Items.BELL);
                    p_259709_.accept(Items.BIG_DRIPLEAF);
                    p_259709_.accept(Items.ARMOR_STAND);
                    p_259709_.accept(Items.REDSTONE_ORE);
                })
                .build()
        );
        Registry.register(
            pRegistry,
            HOTBAR,
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 5)
                .title(Component.translatable("itemGroup.hotbar"))
                .icon(() -> new ItemStack(Blocks.BOOKSHELF))
                .alignedRight()
                .type(CreativeModeTab.Type.HOTBAR)
                .build()
        );
        Registry.register(
            pRegistry,
            SEARCH,
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 6)
                .title(Component.translatable("itemGroup.search"))
                .icon(() -> new ItemStack(Items.COMPASS))
                .displayItems((p_327118_, p_327119_) -> {
                    Set<ItemStack> set = ItemStackLinkedSet.createTypeAndComponentsSet();

                    for (CreativeModeTab creativemodetab : pRegistry) {
                        if (creativemodetab.getType() != CreativeModeTab.Type.SEARCH) {
                            set.addAll(creativemodetab.getSearchTabDisplayItems());
                        }
                    }

                    p_327119_.acceptAll(set);
                })
                .backgroundSuffix("item_search.png")
                .alignedRight()
                .type(CreativeModeTab.Type.SEARCH)
                .build()
        );
        Registry.register(
            pRegistry,
            TOOLS_AND_UTILITIES,
            CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 0)
                .title(Component.translatable("itemGroup.tools"))
                .icon(() -> new ItemStack(Items.DIAMOND_PICKAXE))
                .displayItems(
                    (p_327124_, p_327125_) -> {
                        p_327125_.accept(Items.WOODEN_SHOVEL);
                        p_327125_.accept(Items.WOODEN_PICKAXE);
                        p_327125_.accept(Items.WOODEN_AXE);
                        p_327125_.accept(Items.WOODEN_HOE);
                        p_327125_.accept(Items.STONE_SHOVEL);
                        p_327125_.accept(Items.STONE_PICKAXE);
                        p_327125_.accept(Items.STONE_AXE);
                        p_327125_.accept(Items.STONE_HOE);
                        p_327125_.accept(Items.IRON_SHOVEL);
                        p_327125_.accept(Items.IRON_PICKAXE);
                        p_327125_.accept(Items.IRON_AXE);
                        p_327125_.accept(Items.IRON_HOE);
                        p_327125_.accept(Items.GOLDEN_SHOVEL);
                        p_327125_.accept(Items.GOLDEN_PICKAXE);
                        p_327125_.accept(Items.GOLDEN_AXE);
                        p_327125_.accept(Items.GOLDEN_HOE);
                        p_327125_.accept(Items.DIAMOND_SHOVEL);
                        p_327125_.accept(Items.DIAMOND_PICKAXE);
                        p_327125_.accept(Items.DIAMOND_AXE);
                        p_327125_.accept(Items.DIAMOND_HOE);
                        p_327125_.accept(Items.NETHERITE_SHOVEL);
                        p_327125_.accept(Items.NETHERITE_PICKAXE);
                        p_327125_.accept(Items.NETHERITE_AXE);
                        p_327125_.accept(Items.NETHERITE_HOE);
                        p_327125_.accept(Items.BUCKET);
                        p_327125_.accept(Items.WATER_BUCKET);
                        p_327125_.accept(Items.COD_BUCKET);
                        p_327125_.accept(Items.SALMON_BUCKET);
                        p_327125_.accept(Items.TROPICAL_FISH_BUCKET);
                        p_327125_.accept(Items.PUFFERFISH_BUCKET);
                        p_327125_.accept(Items.AXOLOTL_BUCKET);
                        p_327125_.accept(Items.TADPOLE_BUCKET);
                        p_327125_.accept(Items.LAVA_BUCKET);
                        p_327125_.accept(Items.POWDER_SNOW_BUCKET);
                        p_327125_.accept(Items.MILK_BUCKET);
                        p_327125_.accept(Items.FISHING_ROD);
                        p_327125_.accept(Items.FLINT_AND_STEEL);
                        p_327125_.accept(Items.FIRE_CHARGE);
                        p_327125_.accept(Items.BONE_MEAL);
                        p_327125_.accept(Items.SHEARS);
                        p_327125_.accept(Items.BRUSH);
                        p_327125_.accept(Items.NAME_TAG);
                        p_327125_.accept(Items.LEAD);
                        if (p_327124_.enabledFeatures().contains(FeatureFlags.BUNDLE)) {
                            p_327125_.accept(Items.BUNDLE);
                        }

                        p_327125_.accept(Items.COMPASS);
                        p_327125_.accept(Items.RECOVERY_COMPASS);
                        p_327125_.accept(Items.CLOCK);
                        p_327125_.accept(Items.SPYGLASS);
                        p_327125_.accept(Items.MAP);
                        p_327125_.accept(Items.WRITABLE_BOOK);
                        p_327125_.accept(Items.WIND_CHARGE);
                        p_327125_.accept(Items.ENDER_PEARL);
                        p_327125_.accept(Items.ENDER_EYE);
                        p_327125_.accept(Items.ELYTRA);
                        generateFireworksAllDurations(p_327125_, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        p_327125_.accept(Items.SADDLE);
                        p_327125_.accept(Items.CARROT_ON_A_STICK);
                        p_327125_.accept(Items.WARPED_FUNGUS_ON_A_STICK);
                        p_327125_.accept(Items.OAK_BOAT);
                        p_327125_.accept(Items.OAK_CHEST_BOAT);
                        p_327125_.accept(Items.SPRUCE_BOAT);
                        p_327125_.accept(Items.SPRUCE_CHEST_BOAT);
                        p_327125_.accept(Items.BIRCH_BOAT);
                        p_327125_.accept(Items.BIRCH_CHEST_BOAT);
                        p_327125_.accept(Items.JUNGLE_BOAT);
                        p_327125_.accept(Items.JUNGLE_CHEST_BOAT);
                        p_327125_.accept(Items.ACACIA_BOAT);
                        p_327125_.accept(Items.ACACIA_CHEST_BOAT);
                        p_327125_.accept(Items.DARK_OAK_BOAT);
                        p_327125_.accept(Items.DARK_OAK_CHEST_BOAT);
                        p_327125_.accept(Items.MANGROVE_BOAT);
                        p_327125_.accept(Items.MANGROVE_CHEST_BOAT);
                        p_327125_.accept(Items.CHERRY_BOAT);
                        p_327125_.accept(Items.CHERRY_CHEST_BOAT);
                        p_327125_.accept(Items.BAMBOO_RAFT);
                        p_327125_.accept(Items.BAMBOO_CHEST_RAFT);
                        p_327125_.accept(Items.RAIL);
                        p_327125_.accept(Items.POWERED_RAIL);
                        p_327125_.accept(Items.DETECTOR_RAIL);
                        p_327125_.accept(Items.ACTIVATOR_RAIL);
                        p_327125_.accept(Items.MINECART);
                        p_327125_.accept(Items.HOPPER_MINECART);
                        p_327125_.accept(Items.CHEST_MINECART);
                        p_327125_.accept(Items.FURNACE_MINECART);
                        p_327125_.accept(Items.TNT_MINECART);
                        p_327124_.holders()
                            .lookup(Registries.INSTRUMENT)
                            .ifPresent(
                                p_270036_ -> generateInstrumentTypes(
                                        p_327125_, p_270036_, Items.GOAT_HORN, InstrumentTags.GOAT_HORNS, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                                    )
                            );
                        p_327125_.accept(Items.MUSIC_DISC_13);
                        p_327125_.accept(Items.MUSIC_DISC_CAT);
                        p_327125_.accept(Items.MUSIC_DISC_BLOCKS);
                        p_327125_.accept(Items.MUSIC_DISC_CHIRP);
                        p_327125_.accept(Items.MUSIC_DISC_FAR);
                        p_327125_.accept(Items.MUSIC_DISC_MALL);
                        p_327125_.accept(Items.MUSIC_DISC_MELLOHI);
                        p_327125_.accept(Items.MUSIC_DISC_STAL);
                        p_327125_.accept(Items.MUSIC_DISC_STRAD);
                        p_327125_.accept(Items.MUSIC_DISC_WARD);
                        p_327125_.accept(Items.MUSIC_DISC_11);
                        p_327125_.accept(Items.MUSIC_DISC_WAIT);
                        p_327125_.accept(Items.MUSIC_DISC_OTHERSIDE);
                        p_327125_.accept(Items.MUSIC_DISC_RELIC);
                        p_327125_.accept(Items.MUSIC_DISC_5);
                        p_327125_.accept(Items.MUSIC_DISC_PIGSTEP);
                    }
                )
                .build()
        );
        Registry.register(
            pRegistry,
            COMBAT,
            CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 1)
                .title(Component.translatable("itemGroup.combat"))
                .icon(() -> new ItemStack(Items.NETHERITE_SWORD))
                .displayItems(
                    (p_327142_, p_327143_) -> {
                        p_327143_.accept(Items.WOODEN_SWORD);
                        p_327143_.accept(Items.STONE_SWORD);
                        p_327143_.accept(Items.IRON_SWORD);
                        p_327143_.accept(Items.GOLDEN_SWORD);
                        p_327143_.accept(Items.DIAMOND_SWORD);
                        p_327143_.accept(Items.NETHERITE_SWORD);
                        p_327143_.accept(Items.WOODEN_AXE);
                        p_327143_.accept(Items.STONE_AXE);
                        p_327143_.accept(Items.IRON_AXE);
                        p_327143_.accept(Items.GOLDEN_AXE);
                        p_327143_.accept(Items.DIAMOND_AXE);
                        p_327143_.accept(Items.NETHERITE_AXE);
                        p_327143_.accept(Items.TRIDENT);
                        p_327143_.accept(Items.MACE);
                        p_327143_.accept(Items.SHIELD);
                        p_327143_.accept(Items.LEATHER_HELMET);
                        p_327143_.accept(Items.LEATHER_CHESTPLATE);
                        p_327143_.accept(Items.LEATHER_LEGGINGS);
                        p_327143_.accept(Items.LEATHER_BOOTS);
                        p_327143_.accept(Items.CHAINMAIL_HELMET);
                        p_327143_.accept(Items.CHAINMAIL_CHESTPLATE);
                        p_327143_.accept(Items.CHAINMAIL_LEGGINGS);
                        p_327143_.accept(Items.CHAINMAIL_BOOTS);
                        p_327143_.accept(Items.IRON_HELMET);
                        p_327143_.accept(Items.IRON_CHESTPLATE);
                        p_327143_.accept(Items.IRON_LEGGINGS);
                        p_327143_.accept(Items.IRON_BOOTS);
                        p_327143_.accept(Items.GOLDEN_HELMET);
                        p_327143_.accept(Items.GOLDEN_CHESTPLATE);
                        p_327143_.accept(Items.GOLDEN_LEGGINGS);
                        p_327143_.accept(Items.GOLDEN_BOOTS);
                        p_327143_.accept(Items.DIAMOND_HELMET);
                        p_327143_.accept(Items.DIAMOND_CHESTPLATE);
                        p_327143_.accept(Items.DIAMOND_LEGGINGS);
                        p_327143_.accept(Items.DIAMOND_BOOTS);
                        p_327143_.accept(Items.NETHERITE_HELMET);
                        p_327143_.accept(Items.NETHERITE_CHESTPLATE);
                        p_327143_.accept(Items.NETHERITE_LEGGINGS);
                        p_327143_.accept(Items.NETHERITE_BOOTS);
                        p_327143_.accept(Items.TURTLE_HELMET);
                        p_327143_.accept(Items.LEATHER_HORSE_ARMOR);
                        p_327143_.accept(Items.IRON_HORSE_ARMOR);
                        p_327143_.accept(Items.GOLDEN_HORSE_ARMOR);
                        p_327143_.accept(Items.DIAMOND_HORSE_ARMOR);
                        p_327143_.accept(Items.WOLF_ARMOR);
                        p_327143_.accept(Items.TOTEM_OF_UNDYING);
                        p_327143_.accept(Items.TNT);
                        p_327143_.accept(Items.END_CRYSTAL);
                        p_327143_.accept(Items.SNOWBALL);
                        p_327143_.accept(Items.EGG);
                        p_327143_.accept(Items.WIND_CHARGE);
                        p_327143_.accept(Items.BOW);
                        p_327143_.accept(Items.CROSSBOW);
                        generateFireworksAllDurations(p_327143_, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        p_327143_.accept(Items.ARROW);
                        p_327143_.accept(Items.SPECTRAL_ARROW);
                        p_327142_.holders()
                            .lookup(Registries.POTION)
                            .ifPresent(
                                p_327133_ -> generatePotionEffectTypes(
                                        p_327143_, p_327133_, Items.TIPPED_ARROW, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, p_327142_.enabledFeatures()
                                    )
                            );
                    }
                )
                .build()
        );
        Registry.register(
            pRegistry,
            FOOD_AND_DRINKS,
            CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 2)
                .title(Component.translatable("itemGroup.foodAndDrink"))
                .icon(() -> new ItemStack(Items.GOLDEN_APPLE))
                .displayItems((p_327134_, p_327135_) -> {
                    p_327135_.accept(Items.APPLE);
                    p_327135_.accept(Items.GOLDEN_APPLE);
                    p_327135_.accept(Items.ENCHANTED_GOLDEN_APPLE);
                    p_327135_.accept(Items.MELON_SLICE);
                    p_327135_.accept(Items.SWEET_BERRIES);
                    p_327135_.accept(Items.GLOW_BERRIES);
                    p_327135_.accept(Items.CHORUS_FRUIT);
                    p_327135_.accept(Items.CARROT);
                    p_327135_.accept(Items.GOLDEN_CARROT);
                    p_327135_.accept(Items.POTATO);
                    p_327135_.accept(Items.BAKED_POTATO);
                    p_327135_.accept(Items.POISONOUS_POTATO);
                    p_327135_.accept(Items.BEETROOT);
                    p_327135_.accept(Items.DRIED_KELP);
                    p_327135_.accept(Items.BEEF);
                    p_327135_.accept(Items.COOKED_BEEF);
                    p_327135_.accept(Items.PORKCHOP);
                    p_327135_.accept(Items.COOKED_PORKCHOP);
                    p_327135_.accept(Items.MUTTON);
                    p_327135_.accept(Items.COOKED_MUTTON);
                    p_327135_.accept(Items.CHICKEN);
                    p_327135_.accept(Items.COOKED_CHICKEN);
                    p_327135_.accept(Items.RABBIT);
                    p_327135_.accept(Items.COOKED_RABBIT);
                    p_327135_.accept(Items.COD);
                    p_327135_.accept(Items.COOKED_COD);
                    p_327135_.accept(Items.SALMON);
                    p_327135_.accept(Items.COOKED_SALMON);
                    p_327135_.accept(Items.TROPICAL_FISH);
                    p_327135_.accept(Items.PUFFERFISH);
                    p_327135_.accept(Items.BREAD);
                    p_327135_.accept(Items.COOKIE);
                    p_327135_.accept(Items.CAKE);
                    p_327135_.accept(Items.PUMPKIN_PIE);
                    p_327135_.accept(Items.ROTTEN_FLESH);
                    p_327135_.accept(Items.SPIDER_EYE);
                    p_327135_.accept(Items.MUSHROOM_STEW);
                    p_327135_.accept(Items.BEETROOT_SOUP);
                    p_327135_.accept(Items.RABBIT_STEW);
                    generateSuspiciousStews(p_327135_, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    p_327135_.accept(Items.MILK_BUCKET);
                    p_327135_.accept(Items.HONEY_BOTTLE);
                    generateOminousVials(p_327135_, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    p_327134_.holders().lookup(Registries.POTION).ifPresent(p_327138_ -> {
                        generatePotionEffectTypes(p_327135_, p_327138_, Items.POTION, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, p_327134_.enabledFeatures());
                        generatePotionEffectTypes(p_327135_, p_327138_, Items.SPLASH_POTION, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, p_327134_.enabledFeatures());
                        generatePotionEffectTypes(p_327135_, p_327138_, Items.LINGERING_POTION, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, p_327134_.enabledFeatures());
                    });
                })
                .build()
        );
        Registry.register(
            pRegistry,
            INGREDIENTS,
            CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 3)
                .title(Component.translatable("itemGroup.ingredients"))
                .icon(() -> new ItemStack(Items.IRON_INGOT))
                .displayItems(
                    (p_327107_, p_327108_) -> {
                        p_327108_.accept(Items.COAL);
                        p_327108_.accept(Items.CHARCOAL);
                        p_327108_.accept(Items.RAW_IRON);
                        p_327108_.accept(Items.RAW_COPPER);
                        p_327108_.accept(Items.RAW_GOLD);
                        p_327108_.accept(Items.EMERALD);
                        p_327108_.accept(Items.LAPIS_LAZULI);
                        p_327108_.accept(Items.DIAMOND);
                        p_327108_.accept(Items.ANCIENT_DEBRIS);
                        p_327108_.accept(Items.QUARTZ);
                        p_327108_.accept(Items.AMETHYST_SHARD);
                        p_327108_.accept(Items.IRON_NUGGET);
                        p_327108_.accept(Items.GOLD_NUGGET);
                        p_327108_.accept(Items.IRON_INGOT);
                        p_327108_.accept(Items.COPPER_INGOT);
                        p_327108_.accept(Items.GOLD_INGOT);
                        p_327108_.accept(Items.NETHERITE_SCRAP);
                        p_327108_.accept(Items.NETHERITE_INGOT);
                        p_327108_.accept(Items.STICK);
                        p_327108_.accept(Items.FLINT);
                        p_327108_.accept(Items.WHEAT);
                        p_327108_.accept(Items.BONE);
                        p_327108_.accept(Items.BONE_MEAL);
                        p_327108_.accept(Items.STRING);
                        p_327108_.accept(Items.FEATHER);
                        p_327108_.accept(Items.SNOWBALL);
                        p_327108_.accept(Items.EGG);
                        p_327108_.accept(Items.LEATHER);
                        p_327108_.accept(Items.RABBIT_HIDE);
                        p_327108_.accept(Items.HONEYCOMB);
                        p_327108_.accept(Items.INK_SAC);
                        p_327108_.accept(Items.GLOW_INK_SAC);
                        p_327108_.accept(Items.TURTLE_SCUTE);
                        p_327108_.accept(Items.ARMADILLO_SCUTE);
                        p_327108_.accept(Items.SLIME_BALL);
                        p_327108_.accept(Items.CLAY_BALL);
                        p_327108_.accept(Items.PRISMARINE_SHARD);
                        p_327108_.accept(Items.PRISMARINE_CRYSTALS);
                        p_327108_.accept(Items.NAUTILUS_SHELL);
                        p_327108_.accept(Items.HEART_OF_THE_SEA);
                        p_327108_.accept(Items.FIRE_CHARGE);
                        p_327108_.accept(Items.BLAZE_ROD);
                        p_327108_.accept(Items.BREEZE_ROD);
                        p_327108_.accept(Items.HEAVY_CORE);
                        p_327108_.accept(Items.NETHER_STAR);
                        p_327108_.accept(Items.ENDER_PEARL);
                        p_327108_.accept(Items.ENDER_EYE);
                        p_327108_.accept(Items.SHULKER_SHELL);
                        p_327108_.accept(Items.POPPED_CHORUS_FRUIT);
                        p_327108_.accept(Items.ECHO_SHARD);
                        p_327108_.accept(Items.DISC_FRAGMENT_5);
                        p_327108_.accept(Items.WHITE_DYE);
                        p_327108_.accept(Items.LIGHT_GRAY_DYE);
                        p_327108_.accept(Items.GRAY_DYE);
                        p_327108_.accept(Items.BLACK_DYE);
                        p_327108_.accept(Items.BROWN_DYE);
                        p_327108_.accept(Items.RED_DYE);
                        p_327108_.accept(Items.ORANGE_DYE);
                        p_327108_.accept(Items.YELLOW_DYE);
                        p_327108_.accept(Items.LIME_DYE);
                        p_327108_.accept(Items.GREEN_DYE);
                        p_327108_.accept(Items.CYAN_DYE);
                        p_327108_.accept(Items.LIGHT_BLUE_DYE);
                        p_327108_.accept(Items.BLUE_DYE);
                        p_327108_.accept(Items.PURPLE_DYE);
                        p_327108_.accept(Items.MAGENTA_DYE);
                        p_327108_.accept(Items.PINK_DYE);
                        p_327108_.accept(Items.BOWL);
                        p_327108_.accept(Items.BRICK);
                        p_327108_.accept(Items.NETHER_BRICK);
                        p_327108_.accept(Items.PAPER);
                        p_327108_.accept(Items.BOOK);
                        p_327108_.accept(Items.FIREWORK_STAR);
                        p_327108_.accept(Items.GLASS_BOTTLE);
                        p_327108_.accept(Items.NETHER_WART);
                        p_327108_.accept(Items.REDSTONE);
                        p_327108_.accept(Items.GLOWSTONE_DUST);
                        p_327108_.accept(Items.GUNPOWDER);
                        p_327108_.accept(Items.DRAGON_BREATH);
                        p_327108_.accept(Items.FERMENTED_SPIDER_EYE);
                        p_327108_.accept(Items.BLAZE_POWDER);
                        p_327108_.accept(Items.SUGAR);
                        p_327108_.accept(Items.RABBIT_FOOT);
                        p_327108_.accept(Items.GLISTERING_MELON_SLICE);
                        p_327108_.accept(Items.SPIDER_EYE);
                        p_327108_.accept(Items.PUFFERFISH);
                        p_327108_.accept(Items.MAGMA_CREAM);
                        p_327108_.accept(Items.GOLDEN_CARROT);
                        p_327108_.accept(Items.GHAST_TEAR);
                        p_327108_.accept(Items.TURTLE_HELMET);
                        p_327108_.accept(Items.PHANTOM_MEMBRANE);
                        p_327108_.accept(Items.FLOWER_BANNER_PATTERN);
                        p_327108_.accept(Items.CREEPER_BANNER_PATTERN);
                        p_327108_.accept(Items.SKULL_BANNER_PATTERN);
                        p_327108_.accept(Items.MOJANG_BANNER_PATTERN);
                        p_327108_.accept(Items.GLOBE_BANNER_PATTERN);
                        p_327108_.accept(Items.PIGLIN_BANNER_PATTERN);
                        p_327108_.accept(Items.FLOW_BANNER_PATTERN);
                        p_327108_.accept(Items.GUSTER_BANNER_PATTERN);
                        p_327108_.accept(Items.ANGLER_POTTERY_SHERD);
                        p_327108_.accept(Items.ARCHER_POTTERY_SHERD);
                        p_327108_.accept(Items.ARMS_UP_POTTERY_SHERD);
                        p_327108_.accept(Items.BLADE_POTTERY_SHERD);
                        p_327108_.accept(Items.BREWER_POTTERY_SHERD);
                        p_327108_.accept(Items.BURN_POTTERY_SHERD);
                        p_327108_.accept(Items.DANGER_POTTERY_SHERD);
                        p_327108_.accept(Items.FLOW_POTTERY_SHERD);
                        p_327108_.accept(Items.EXPLORER_POTTERY_SHERD);
                        p_327108_.accept(Items.FRIEND_POTTERY_SHERD);
                        p_327108_.accept(Items.GUSTER_POTTERY_SHERD);
                        p_327108_.accept(Items.HEART_POTTERY_SHERD);
                        p_327108_.accept(Items.HEARTBREAK_POTTERY_SHERD);
                        p_327108_.accept(Items.HOWL_POTTERY_SHERD);
                        p_327108_.accept(Items.MINER_POTTERY_SHERD);
                        p_327108_.accept(Items.MOURNER_POTTERY_SHERD);
                        p_327108_.accept(Items.PLENTY_POTTERY_SHERD);
                        p_327108_.accept(Items.PRIZE_POTTERY_SHERD);
                        p_327108_.accept(Items.SCRAPE_POTTERY_SHERD);
                        p_327108_.accept(Items.SHEAF_POTTERY_SHERD);
                        p_327108_.accept(Items.SHELTER_POTTERY_SHERD);
                        p_327108_.accept(Items.SKULL_POTTERY_SHERD);
                        p_327108_.accept(Items.SNORT_POTTERY_SHERD);
                        p_327108_.accept(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.VEX_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.HOST_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.RIB_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE);
                        p_327108_.accept(Items.EXPERIENCE_BOTTLE);
                        p_327108_.accept(Items.TRIAL_KEY);
                        p_327108_.accept(Items.OMINOUS_TRIAL_KEY);
                        Set<TagKey<Item>> set = Set.of(
                            ItemTags.FOOT_ARMOR_ENCHANTABLE,
                            ItemTags.LEG_ARMOR_ENCHANTABLE,
                            ItemTags.CHEST_ARMOR_ENCHANTABLE,
                            ItemTags.HEAD_ARMOR_ENCHANTABLE,
                            ItemTags.ARMOR_ENCHANTABLE,
                            ItemTags.SWORD_ENCHANTABLE,
                            ItemTags.SHARP_WEAPON_ENCHANTABLE,
                            ItemTags.MACE_ENCHANTABLE,
                            ItemTags.FIRE_ASPECT_ENCHANTABLE,
                            ItemTags.WEAPON_ENCHANTABLE,
                            ItemTags.MINING_ENCHANTABLE,
                            ItemTags.MINING_LOOT_ENCHANTABLE,
                            ItemTags.FISHING_ENCHANTABLE,
                            ItemTags.TRIDENT_ENCHANTABLE,
                            ItemTags.DURABILITY_ENCHANTABLE,
                            ItemTags.BOW_ENCHANTABLE,
                            ItemTags.EQUIPPABLE_ENCHANTABLE,
                            ItemTags.CROSSBOW_ENCHANTABLE,
                            ItemTags.VANISHING_ENCHANTABLE
                        );
                        p_327107_.holders().lookup(Registries.ENCHANTMENT).ifPresent(p_327129_ -> {
                            generateEnchantmentBookTypesOnlyMaxLevel(p_327108_, p_327129_, set, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY, p_327107_.enabledFeatures());
                            generateEnchantmentBookTypesAllLevels(p_327108_, p_327129_, set, CreativeModeTab.TabVisibility.SEARCH_TAB_ONLY, p_327107_.enabledFeatures());
                        });
                    }
                )
                .build()
        );
        Registry.register(
            pRegistry,
            SPAWN_EGGS,
            CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 4)
                .title(Component.translatable("itemGroup.spawnEggs"))
                .icon(() -> new ItemStack(Items.PIG_SPAWN_EGG))
                .displayItems((p_270425_, p_260158_) -> {
                    p_260158_.accept(Items.SPAWNER);
                    p_260158_.accept(Items.TRIAL_SPAWNER);
                    p_260158_.accept(Items.ALLAY_SPAWN_EGG);
                    p_260158_.accept(Items.ARMADILLO_SPAWN_EGG);
                    p_260158_.accept(Items.AXOLOTL_SPAWN_EGG);
                    p_260158_.accept(Items.BAT_SPAWN_EGG);
                    p_260158_.accept(Items.BEE_SPAWN_EGG);
                    p_260158_.accept(Items.BLAZE_SPAWN_EGG);
                    p_260158_.accept(Items.BOGGED_SPAWN_EGG);
                    p_260158_.accept(Items.BREEZE_SPAWN_EGG);
                    p_260158_.accept(Items.CAMEL_SPAWN_EGG);
                    p_260158_.accept(Items.CAT_SPAWN_EGG);
                    p_260158_.accept(Items.CAVE_SPIDER_SPAWN_EGG);
                    p_260158_.accept(Items.CHICKEN_SPAWN_EGG);
                    p_260158_.accept(Items.COD_SPAWN_EGG);
                    p_260158_.accept(Items.COW_SPAWN_EGG);
                    p_260158_.accept(Items.CREEPER_SPAWN_EGG);
                    p_260158_.accept(Items.DOLPHIN_SPAWN_EGG);
                    p_260158_.accept(Items.DONKEY_SPAWN_EGG);
                    p_260158_.accept(Items.DROWNED_SPAWN_EGG);
                    p_260158_.accept(Items.ELDER_GUARDIAN_SPAWN_EGG);
                    p_260158_.accept(Items.ENDERMAN_SPAWN_EGG);
                    p_260158_.accept(Items.ENDERMITE_SPAWN_EGG);
                    p_260158_.accept(Items.EVOKER_SPAWN_EGG);
                    p_260158_.accept(Items.FOX_SPAWN_EGG);
                    p_260158_.accept(Items.FROG_SPAWN_EGG);
                    p_260158_.accept(Items.GHAST_SPAWN_EGG);
                    p_260158_.accept(Items.GLOW_SQUID_SPAWN_EGG);
                    p_260158_.accept(Items.GOAT_SPAWN_EGG);
                    p_260158_.accept(Items.GUARDIAN_SPAWN_EGG);
                    p_260158_.accept(Items.HOGLIN_SPAWN_EGG);
                    p_260158_.accept(Items.HORSE_SPAWN_EGG);
                    p_260158_.accept(Items.HUSK_SPAWN_EGG);
                    p_260158_.accept(Items.IRON_GOLEM_SPAWN_EGG);
                    p_260158_.accept(Items.LLAMA_SPAWN_EGG);
                    p_260158_.accept(Items.MAGMA_CUBE_SPAWN_EGG);
                    p_260158_.accept(Items.MOOSHROOM_SPAWN_EGG);
                    p_260158_.accept(Items.MULE_SPAWN_EGG);
                    p_260158_.accept(Items.OCELOT_SPAWN_EGG);
                    p_260158_.accept(Items.PANDA_SPAWN_EGG);
                    p_260158_.accept(Items.PARROT_SPAWN_EGG);
                    p_260158_.accept(Items.PHANTOM_SPAWN_EGG);
                    p_260158_.accept(Items.PIG_SPAWN_EGG);
                    p_260158_.accept(Items.PIGLIN_SPAWN_EGG);
                    p_260158_.accept(Items.PIGLIN_BRUTE_SPAWN_EGG);
                    p_260158_.accept(Items.PILLAGER_SPAWN_EGG);
                    p_260158_.accept(Items.POLAR_BEAR_SPAWN_EGG);
                    p_260158_.accept(Items.PUFFERFISH_SPAWN_EGG);
                    p_260158_.accept(Items.RABBIT_SPAWN_EGG);
                    p_260158_.accept(Items.RAVAGER_SPAWN_EGG);
                    p_260158_.accept(Items.SALMON_SPAWN_EGG);
                    p_260158_.accept(Items.SHEEP_SPAWN_EGG);
                    p_260158_.accept(Items.SHULKER_SPAWN_EGG);
                    p_260158_.accept(Items.SILVERFISH_SPAWN_EGG);
                    p_260158_.accept(Items.SKELETON_SPAWN_EGG);
                    p_260158_.accept(Items.SKELETON_HORSE_SPAWN_EGG);
                    p_260158_.accept(Items.SLIME_SPAWN_EGG);
                    p_260158_.accept(Items.SNIFFER_SPAWN_EGG);
                    p_260158_.accept(Items.SNOW_GOLEM_SPAWN_EGG);
                    p_260158_.accept(Items.SPIDER_SPAWN_EGG);
                    p_260158_.accept(Items.SQUID_SPAWN_EGG);
                    p_260158_.accept(Items.STRAY_SPAWN_EGG);
                    p_260158_.accept(Items.STRIDER_SPAWN_EGG);
                    p_260158_.accept(Items.TADPOLE_SPAWN_EGG);
                    p_260158_.accept(Items.TRADER_LLAMA_SPAWN_EGG);
                    p_260158_.accept(Items.TROPICAL_FISH_SPAWN_EGG);
                    p_260158_.accept(Items.TURTLE_SPAWN_EGG);
                    p_260158_.accept(Items.VEX_SPAWN_EGG);
                    p_260158_.accept(Items.VILLAGER_SPAWN_EGG);
                    p_260158_.accept(Items.VINDICATOR_SPAWN_EGG);
                    p_260158_.accept(Items.WANDERING_TRADER_SPAWN_EGG);
                    p_260158_.accept(Items.WARDEN_SPAWN_EGG);
                    p_260158_.accept(Items.WITCH_SPAWN_EGG);
                    p_260158_.accept(Items.WITHER_SKELETON_SPAWN_EGG);
                    p_260158_.accept(Items.WOLF_SPAWN_EGG);
                    p_260158_.accept(Items.ZOGLIN_SPAWN_EGG);
                    p_260158_.accept(Items.ZOMBIE_SPAWN_EGG);
                    p_260158_.accept(Items.ZOMBIE_HORSE_SPAWN_EGG);
                    p_260158_.accept(Items.ZOMBIE_VILLAGER_SPAWN_EGG);
                    p_260158_.accept(Items.ZOMBIFIED_PIGLIN_SPAWN_EGG);
                })
                .build()
        );
        Registry.register(
            pRegistry,
            OP_BLOCKS,
            CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 5)
                .title(Component.translatable("itemGroup.op"))
                .icon(() -> new ItemStack(Items.COMMAND_BLOCK))
                .alignedRight()
                .displayItems(
                    (p_269980_, p_269981_) -> {
                        if (p_269980_.hasPermissions()) {
                            p_269981_.accept(Items.COMMAND_BLOCK);
                            p_269981_.accept(Items.CHAIN_COMMAND_BLOCK);
                            p_269981_.accept(Items.REPEATING_COMMAND_BLOCK);
                            p_269981_.accept(Items.COMMAND_BLOCK_MINECART);
                            p_269981_.accept(Items.JIGSAW);
                            p_269981_.accept(Items.STRUCTURE_BLOCK);
                            p_269981_.accept(Items.STRUCTURE_VOID);
                            p_269981_.accept(Items.BARRIER);
                            p_269981_.accept(Items.DEBUG_STICK);

                            for (int i = 15; i >= 0; i--) {
                                p_269981_.accept(LightBlock.setLightOnStack(new ItemStack(Items.LIGHT), i));
                            }

                            p_269980_.holders()
                                .lookup(Registries.PAINTING_VARIANT)
                                .ifPresent(
                                    p_270014_ -> generatePresetPaintings(
                                            p_269981_,
                                            (HolderLookup.RegistryLookup<PaintingVariant>)p_270014_,
                                            p_270003_ -> !p_270003_.is(PaintingVariantTags.PLACEABLE),
                                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                                        )
                                );
                        }
                    }
                )
                .build()
        );
        return Registry.register(
            pRegistry,
            INVENTORY,
            CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 6)
                .title(Component.translatable("itemGroup.inventory"))
                .icon(() -> new ItemStack(Blocks.CHEST))
                .backgroundSuffix("inventory.png")
                .hideTitle()
                .alignedRight()
                .type(CreativeModeTab.Type.INVENTORY)
                .noScrollBar()
                .build()
        );
    }

    public static void validate() {
        Map<Pair<CreativeModeTab.Row, Integer>, String> map = new HashMap<>();

        for (ResourceKey<CreativeModeTab> resourcekey : BuiltInRegistries.CREATIVE_MODE_TAB.registryKeySet()) {
            CreativeModeTab creativemodetab = BuiltInRegistries.CREATIVE_MODE_TAB.getOrThrow(resourcekey);
            String s = creativemodetab.getDisplayName().getString();
            String s1 = map.put(Pair.of(creativemodetab.row(), creativemodetab.column()), s);
            if (s1 != null) {
                throw new IllegalArgumentException("Duplicate position: " + s + " vs. " + s1);
            }
        }
    }

    public static CreativeModeTab getDefaultTab() {
        return BuiltInRegistries.CREATIVE_MODE_TAB.getOrThrow(BUILDING_BLOCKS);
    }

    private static void generatePotionEffectTypes(
        CreativeModeTab.Output pOutput, HolderLookup<Potion> pPotions, Item pItem, CreativeModeTab.TabVisibility pTabVisibility, FeatureFlagSet pRequiredFeatures
    ) {
        pPotions.listElements()
            .filter(p_327145_ -> p_327145_.value().isEnabled(pRequiredFeatures))
            .map(p_327116_ -> PotionContents.createItemStack(pItem, p_327116_))
            .forEach(p_270000_ -> pOutput.accept(p_270000_, pTabVisibility));
    }

    private static void generateEnchantmentBookTypesOnlyMaxLevel(
        CreativeModeTab.Output pOutput,
        HolderLookup<Enchantment> pEnchantments,
        Set<TagKey<Item>> pItems,
        CreativeModeTab.TabVisibility pTabVisibility,
        FeatureFlagSet pRequiredFeatures
    ) {
        pEnchantments.listElements()
            .map(Holder::value)
            .filter(p_327123_ -> p_327123_.isEnabled(pRequiredFeatures))
            .filter(p_327114_ -> pItems.contains(p_327114_.getSupportedItems()))
            .map(p_270038_ -> EnchantedBookItem.createForEnchantment(new EnchantmentInstance(p_270038_, p_270038_.getMaxLevel())))
            .forEach(p_269989_ -> pOutput.accept(p_269989_, pTabVisibility));
    }

    private static void generateEnchantmentBookTypesAllLevels(
        CreativeModeTab.Output p_270961_,
        HolderLookup<Enchantment> pEnchantments,
        Set<TagKey<Item>> pItems,
        CreativeModeTab.TabVisibility pTabVisibility,
        FeatureFlagSet pRequiredFeatures
    ) {
        pEnchantments.listElements()
            .map(Holder::value)
            .filter(p_327112_ -> p_327112_.isEnabled(pRequiredFeatures))
            .filter(p_327121_ -> pItems.contains(p_327121_.getSupportedItems()))
            .flatMap(
                p_270024_ -> IntStream.rangeClosed(p_270024_.getMinLevel(), p_270024_.getMaxLevel())
                        .mapToObj(p_270006_ -> EnchantedBookItem.createForEnchantment(new EnchantmentInstance(p_270024_, p_270006_)))
            )
            .forEach(p_270017_ -> p_270961_.accept(p_270017_, pTabVisibility));
    }

    private static void generateInstrumentTypes(
        CreativeModeTab.Output pOutput,
        HolderLookup<Instrument> pInstruments,
        Item pItem,
        TagKey<Instrument> pInstrument,
        CreativeModeTab.TabVisibility pTabVisibility
    ) {
        pInstruments.get(pInstrument)
            .ifPresent(
                p_270021_ -> p_270021_.stream()
                        .map(p_269995_ -> InstrumentItem.create(pItem, (Holder<Instrument>)p_269995_))
                        .forEach(p_270011_ -> pOutput.accept(p_270011_, pTabVisibility))
            );
    }

    private static void generateSuspiciousStews(CreativeModeTab.Output pOutput, CreativeModeTab.TabVisibility pTabVisibility) {
        List<SuspiciousEffectHolder> list = SuspiciousEffectHolder.getAllEffectHolders();
        Set<ItemStack> set = ItemStackLinkedSet.createTypeAndComponentsSet();

        for (SuspiciousEffectHolder suspiciouseffectholder : list) {
            ItemStack itemstack = new ItemStack(Items.SUSPICIOUS_STEW);
            itemstack.set(DataComponents.SUSPICIOUS_STEW_EFFECTS, suspiciouseffectholder.getSuspiciousEffects());
            set.add(itemstack);
        }

        pOutput.acceptAll(set, pTabVisibility);
    }

    private static void generateOminousVials(CreativeModeTab.Output pOutput, CreativeModeTab.TabVisibility pTabVisibility) {
        for (int i = 0; i <= 4; i++) {
            ItemStack itemstack = new ItemStack(Items.OMINOUS_BOTTLE);
            itemstack.set(DataComponents.OMINOUS_BOTTLE_AMPLIFIER, i);
            pOutput.accept(itemstack, pTabVisibility);
        }
    }

    private static void generateFireworksAllDurations(CreativeModeTab.Output pOutput, CreativeModeTab.TabVisibility pTabVisibility) {
        for (byte b0 : FireworkRocketItem.CRAFTABLE_DURATIONS) {
            ItemStack itemstack = new ItemStack(Items.FIREWORK_ROCKET);
            itemstack.set(DataComponents.FIREWORKS, new Fireworks(b0, List.of()));
            pOutput.accept(itemstack, pTabVisibility);
        }
    }

    private static void generatePresetPaintings(
        CreativeModeTab.Output pOutput,
        HolderLookup.RegistryLookup<PaintingVariant> pPaintingVariants,
        Predicate<Holder<PaintingVariant>> pPredicate,
        CreativeModeTab.TabVisibility pTabVisibility
    ) {
        pPaintingVariants.listElements()
            .filter(pPredicate)
            .sorted(PAINTING_COMPARATOR)
            .forEach(
                p_327141_ -> {
                    CustomData customdata = CustomData.EMPTY
                        .update(Painting.VARIANT_MAP_CODEC, p_327141_)
                        .getOrThrow()
                        .update(p_327130_ -> p_327130_.putString("id", "minecraft:painting"));
                    ItemStack itemstack = new ItemStack(Items.PAINTING);
                    itemstack.set(DataComponents.ENTITY_DATA, customdata);
                    pOutput.accept(itemstack, pTabVisibility);
                }
            );
    }

    public static List<CreativeModeTab> tabs() {
        return streamAllTabs().filter(CreativeModeTab::shouldDisplay).toList();
    }

    public static List<CreativeModeTab> allTabs() {
        return streamAllTabs().toList();
    }

    private static Stream<CreativeModeTab> streamAllTabs() {
        return BuiltInRegistries.CREATIVE_MODE_TAB.stream();
    }

    public static CreativeModeTab searchTab() {
        return BuiltInRegistries.CREATIVE_MODE_TAB.getOrThrow(SEARCH);
    }

    private static void buildAllTabContents(CreativeModeTab.ItemDisplayParameters pParameters) {
        streamAllTabs().filter(p_259647_ -> p_259647_.getType() == CreativeModeTab.Type.CATEGORY).forEach(p_269997_ -> p_269997_.buildContents(pParameters));
        streamAllTabs().filter(p_260124_ -> p_260124_.getType() != CreativeModeTab.Type.CATEGORY).forEach(p_270002_ -> p_270002_.buildContents(pParameters));
    }

    public static boolean tryRebuildTabContents(FeatureFlagSet pEnabledFeatures, boolean pHasPermissions, HolderLookup.Provider pHolders) {
        if (CACHED_PARAMETERS != null && !CACHED_PARAMETERS.needsUpdate(pEnabledFeatures, pHasPermissions, pHolders)) {
            return false;
        } else {
            CACHED_PARAMETERS = new CreativeModeTab.ItemDisplayParameters(pEnabledFeatures, pHasPermissions, pHolders);
            buildAllTabContents(CACHED_PARAMETERS);
            return true;
        }
    }
}