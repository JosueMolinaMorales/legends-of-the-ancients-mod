package net.josuemorales.lota.datagen.loot;

import net.josuemorales.lota.block.ModBlocks;
import net.josuemorales.lota.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        // TODO: update to drop rune stone fragment
        this.add(ModBlocks.RUNE_STONE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.RUNE_STONE_ORE.get(), ModItems.RUNE_STONE_FRAGMENT.get()));


        this.dropSelf(ModBlocks.STRIPPED_SACRED_GROVE_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_SACRED_GROVE_LOG.get());
        this.dropSelf(ModBlocks.SACRED_GROVE_WOOD.get());
        this.dropSelf(ModBlocks.SACRED_GROVE_PLANKS.get());
        this.dropSelf(ModBlocks.SACRED_GROVE_LOG.get());
        this.add(ModBlocks.SACRED_GROVE_LEAVES.get(),
                block -> createLeavesDrops(block, ModBlocks.SACRED_GROVE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.SACRED_GROVE_SAPLING.get());
    }

    protected LootTable.Builder createCopperLikeOreDrops(Block block, Item item) {
        return createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 5.0f)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.FORTUNE))));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries()
                .stream()
                .map(RegistryObject::get)::iterator;
    }
}
