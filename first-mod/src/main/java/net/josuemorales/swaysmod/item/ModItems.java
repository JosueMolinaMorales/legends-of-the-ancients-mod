package net.josuemorales.swaysmod.item;

import net.josuemorales.swaysmod.SwaysMod;
import net.josuemorales.swaysmod.item.custom.FuelItem;
import net.josuemorales.swaysmod.item.custom.MetalDetectorItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            SwaysMod.MOD_ID);
    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100).stacksTo(1)));
    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));
    public static final RegistryObject<Item> PINE_CONE = ITEMS.register("pine_cone",
            () -> new FuelItem(new Item.Properties(), 400));
    public static final RegistryObject<Item> SAPPHIRE_STAFF = ITEMS.register("sapphire_staff",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SAPPHIRE_ROD = ITEMS.register("sapphire_rod",
            () -> new Item(new Item.Properties()));
    // TODO: Update
    public static final RegistryObject<Item> SAPPHIRE_SWORD = ITEMS.register("sapphire_sword",
            () -> new SwordItem(ModToolTiers.SAPPHIRE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.DIAMOND, 4, -2.4F))));

    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = ITEMS.register("sapphire_pickaxe",
            () -> new PickaxeItem(ModToolTiers.SAPPHIRE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.SAPPHIRE, 1, -3.2F))));

    public static final RegistryObject<Item> SAPPHIRE_AXE = ITEMS.register("sapphire_axe",
            () -> new AxeItem(ModToolTiers.SAPPHIRE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.SAPPHIRE, 6, -3F))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
