package net.josuemorales.swaysmod.item;

import net.josuemorales.swaysmod.SwaysMod;
import net.josuemorales.swaysmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
            Registries.CREATIVE_MODE_TAB, SwaysMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SWAYS_TAB = CREATIVE_MODE_TABS.register("sways_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.SAPPHIRE.get()))
                    .title(Component.translatable("creativetab.sways_tab"))
                    .displayItems(((pParameters, pOutput) -> {
                        pOutput.accept(new ItemStack(ModItems.SAPPHIRE.get()));
                        pOutput.accept(new ItemStack(ModItems.RAW_SAPPHIRE.get()));
                        pOutput.accept(new ItemStack(ModBlocks.SAPPHIRE_BLOCK.get()));
                        pOutput.accept(new ItemStack(ModBlocks.RAW_SAPPHIRE_BLOCK.get()));
                        pOutput.accept(new ItemStack(ModBlocks.SAPPHIRE_ORE.get()));
                        pOutput.accept(new ItemStack(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get()));
                        pOutput.accept(new ItemStack(ModBlocks.NETHER_SAPPHIRE_ORE.get()));
                        pOutput.accept(new ItemStack(ModBlocks.END_STONE_SAPPHIRE_ORE.get()));
                        pOutput.accept(new ItemStack(ModItems.METAL_DETECTOR.get()));
                        pOutput.accept(new ItemStack(ModItems.STRAWBERRY.get()));
                        pOutput.accept(new ItemStack(ModItems.PINE_CONE.get()));
                        pOutput.accept(new ItemStack(ModBlocks.SAPPHIRE_STAIRS.get()));
                        pOutput.accept(new ItemStack(ModBlocks.SAPPHIRE_FENCE.get()));
                        pOutput.accept(new ItemStack(ModBlocks.SAPPHIRE_FENCE_GATE.get()));
                        pOutput.accept(new ItemStack(ModBlocks.SAPPHIRE_WALL.get()));
                        pOutput.accept(new ItemStack(ModBlocks.SAPPHIRE_DOOR.get()));
                        pOutput.accept(new ItemStack(ModBlocks.SAPPHIRE_TRAP_DOOR.get()));
                        pOutput.accept(new ItemStack(ModBlocks.SAPPHIRE_PRESSURE_PLATE.get()));
                        pOutput.accept(new ItemStack(ModBlocks.SAPPHIRE_BUTTON.get()));
                        pOutput.accept(new ItemStack(ModBlocks.SAPPHIRE_SLAB.get()));
                        pOutput.accept(new ItemStack(ModItems.SAPPHIRE_STAFF.get()));
                        pOutput.accept(new ItemStack(ModItems.SAPPHIRE_ROD.get()));
                        pOutput.accept(new ItemStack(ModItems.SAPPHIRE_SWORD.get()));
                    }))
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
