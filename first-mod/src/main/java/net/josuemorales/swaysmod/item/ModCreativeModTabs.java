package net.josuemorales.swaysmod.item;

import net.josuemorales.swaysmod.SwaysMod;
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

    public static final RegistryObject<CreativeModeTab> SWAYS_TAB =
            CREATIVE_MODE_TABS.register("sways_tab",
                                        () -> CreativeModeTab.builder()
                                                .icon(() -> new ItemStack(
                                                        ModItems.SAPPHIRE.get()))
                                                .title(Component.translatable(
                                                        "creativetab.sways_tab"))
                                                .displayItems(
                                                        ((pParameters, pOutput) -> {
                                                            pOutput.accept(
                                                                    new ItemStack(ModItems.SAPPHIRE.get()));
                                                            pOutput.accept(
                                                                    new ItemStack(ModItems.RAW_SAPPHIRE.get()));
                                                        }))
                                                .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
