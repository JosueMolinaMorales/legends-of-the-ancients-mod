package net.josuemorales.lota.datagen;

import net.josuemorales.lota.LegendsOfTheAncientsMod;
import net.josuemorales.lota.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, LegendsOfTheAncientsMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.RUNE_STONE_ORE);


        logBlock(((RotatedPillarBlock) ModBlocks.SACRED_GROVE_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.SACRED_GROVE_WOOD.get()), blockTexture(ModBlocks.SACRED_GROVE_LOG.get()), blockTexture(ModBlocks.SACRED_GROVE_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_SACRED_GROVE_LOG.get()), blockTexture(ModBlocks.STRIPPED_SACRED_GROVE_LOG.get()),
                new ResourceLocation(LegendsOfTheAncientsMod.MOD_ID, "block/stripped_pine_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_SACRED_GROVE_WOOD.get()), blockTexture(ModBlocks.STRIPPED_SACRED_GROVE_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_SACRED_GROVE_LOG.get()));

        blockItem(ModBlocks.SACRED_GROVE_LOG);
        blockItem(ModBlocks.SACRED_GROVE_WOOD);
        blockItem(ModBlocks.STRIPPED_SACRED_GROVE_LOG);
        blockItem(ModBlocks.STRIPPED_SACRED_GROVE_WOOD);

        blockWithItem(ModBlocks.SACRED_GROVE_PLANKS);

        leavesBlock(ModBlocks.SACRED_GROVE_LEAVES);
        saplingBlock(ModBlocks.SACRED_GROVE_SAPLING);
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }


    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(LegendsOfTheAncientsMod.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
