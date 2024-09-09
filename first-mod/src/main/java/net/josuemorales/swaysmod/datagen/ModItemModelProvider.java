package net.josuemorales.swaysmod.datagen;

import net.josuemorales.swaysmod.SwaysMod;
import net.josuemorales.swaysmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SwaysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.SAPPHIRE);
        simpleItem(ModItems.PINE_CONE);
        simpleItem(ModItems.RAW_SAPPHIRE);
        simpleItem(ModItems.STRAWBERRY);
        simpleItem(ModItems.METAL_DETECTOR);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(SwaysMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
