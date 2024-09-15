package net.josuemorales.lota.datagen;

import net.josuemorales.lota.LegendsOfTheAncientsMod;
import net.josuemorales.lota.block.ModBlocks;
import net.josuemorales.lota.utils.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, LegendsOfTheAncientsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
                .addTag(Tags.Blocks.ORES);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.RUNE_STONE_ORE.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.RUNE_STONE_ORE.get());

//        this.tag(BlockTags.FENCES).add(ModBlocks.SAPPHIRE_FENCE.get());
//        this.tag(BlockTags.FENCE_GATES).add(ModBlocks.SAPPHIRE_FENCE_GATE.get());
//        this.tag(BlockTags.WALLS).add(ModBlocks.SAPPHIRE_WALL.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.SACRED_GROVE_LOG.get())
                .add(ModBlocks.SACRED_GROVE_WOOD.get())
                .add(ModBlocks.STRIPPED_SACRED_GROVE_LOG.get())
                .add(ModBlocks.STRIPPED_SACRED_GROVE_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.SACRED_GROVE_PLANKS.get());

    }
}
