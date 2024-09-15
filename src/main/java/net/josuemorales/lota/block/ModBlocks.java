package net.josuemorales.lota.block;

import net.josuemorales.lota.LegendsOfTheAncientsMod;
import net.josuemorales.lota.item.ModItems;
import net.josuemorales.lota.item.custom.ModFlammableRotatedPillarBlock;
import net.josuemorales.lota.worldgen.ModConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * This class handles the registration of custom blocks for the LegendsOfTheAncientsMod.
 */
public class ModBlocks {
    /**
     * Deferred register for blocks, used to register blocks with the Forge
     * registry.
     */
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            LegendsOfTheAncientsMod.MOD_ID);
    //    public static final RegistryObject<Block> SAPPHIRE_STAIRS = registerBlock("sapphire_stairs",
//            () -> new StairBlock(ModBlocks.SAPPHIRE_BLOCK.get().defaultBlockState(),
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
//    public static final RegistryObject<Block> SAPPHIRE_FENCE = registerBlock("sapphire_fence",
//            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
//    public static final RegistryObject<Block> SAPPHIRE_FENCE_GATE = registerBlock("sapphire_fence_gate",
//            () -> new FenceGateBlock(WoodType.DARK_OAK,
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK),
//                    SoundEvents.CHAIN_PLACE, SoundEvents.ANVIL_BREAK));
//    public static final RegistryObject<Block> SAPPHIRE_WALL = registerBlock("sapphire_wall",
//            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
//    public static final RegistryObject<Block> SAPPHIRE_DOOR = registerBlock("sapphire_door",
//            () -> new DoorBlock(BlockSetType.IRON,
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));
//    public static final RegistryObject<Block> SAPPHIRE_TRAP_DOOR = registerBlock("sapphire_trap_door",
//            () -> new TrapDoorBlock(BlockSetType.IRON,
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));
//    public static final RegistryObject<Block> SAPPHIRE_PRESSURE_PLATE = registerBlock("sapphire_pressure_plate",
//            () -> new PressurePlateBlock(BlockSetType.IRON,
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
//    public static final RegistryObject<Block> SAPPHIRE_BUTTON = registerBlock("sapphire_button",
//            () -> new ButtonBlock(BlockSetType.IRON, 10,
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON)));
//    public static final RegistryObject<Block> SAPPHIRE_SLAB = registerBlock("sapphire_slab",
//            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
//    public static final RegistryObject<Block> RAW_SAPPHIRE_BLOCK = registerBlock("raw_sapphire_block",
//            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK)));

    // TODO: Make this deepslate & change up how it spawns
    public static final RegistryObject<Block> RUNE_STONE_ORE = registerBlock("rune_stone_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SACRED_GROVE_LOG = registerBlock("sacred_grove_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3F)));
    public static final RegistryObject<Block> SACRED_GROVE_WOOD = registerBlock("sacred_grove_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3F)));
    public static final RegistryObject<Block> STRIPPED_SACRED_GROVE_LOG = registerBlock("stripped_sacred_grove_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3F)));
    public static final RegistryObject<Block> STRIPPED_SACRED_GROVE_WOOD = registerBlock("stripped_sacred_grove_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3F)));


    // TODO: Create Plank class
    public static final RegistryObject<Block> SACRED_GROVE_PLANKS = registerBlock("sacred_grove_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> SACRED_GROVE_LEAVES = registerBlock("sacred_grove_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> SACRED_GROVE_SAPLING = registerBlock("sacred_grove_sapling",
            () -> new SaplingBlock(
                    new TreeGrower(
                            "sacred_grove",
                            0.1F,
                            Optional.empty(),
                            Optional.empty(),
                            Optional.of(ModConfiguredFeatures.SACRED_GROVE_KEY),
                            Optional.of(ModConfiguredFeatures.SACRED_GROVE_KEY),
                            Optional.empty(),
                            Optional.empty()),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
            )
    );


    /**
     * Registers a block with the given name and supplier.
     *
     * @param name  The name of the block to register.
     * @param block A supplier that provides an instance of the block.
     * @param <T>   The type of the block.
     * @return A RegistryObject representing the registered block.
     */
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    /**
     * Registers a BlockItem for the given block.
     *
     * @param name  The name of the block item to register.
     * @param block A RegistryObject representing the block.
     * @param <T>   The type of the block.
     * @return A RegistryObject representing the registered block item.
     */
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    /**
     * Registers the block deferred register with the given event bus.
     *
     * @param eventBus The event bus to register with.
     */
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
