package net.josuemorales.swaysmod.block;

import net.josuemorales.swaysmod.SwaysMod;
import net.josuemorales.swaysmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * This class handles the registration of custom blocks for the SwaysMod.
 */
public class ModBlocks {
    /**
     * Deferred register for blocks, used to register blocks with the Forge registry.
     */
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            SwaysMod.MOD_ID);
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> RAW_SAPPHIRE_BLOCK = registerBlock("raw_sapphire_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK)));
    public static final RegistryObject<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
            ));
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            () -> new DropExperienceBlock(UniformInt.of(0, 2),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(2f).requiresCorrectToolForDrops()
            ));
    public static final RegistryObject<Block> NETHER_SAPPHIRE_ORE = registerBlock("nether_sapphire_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK).strength(2f).requiresCorrectToolForDrops()
            ));
    public static final RegistryObject<Block> END_STONE_SAPPHIRE_ORE = registerBlock("end_stone_sapphire_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE).strength(2f).requiresCorrectToolForDrops()
            ));

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
