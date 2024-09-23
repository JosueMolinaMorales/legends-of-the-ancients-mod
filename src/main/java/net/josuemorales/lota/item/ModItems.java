package net.josuemorales.lota.item;

import net.josuemorales.lota.LegendsOfTheAncientsMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            LegendsOfTheAncientsMod.MOD_ID);

    public static final RegistryObject<Item> RUNE_STONE_FRAGMENT = ITEMS.register("rune_stone_fragment",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RUNE_STONE = ITEMS.register("rune_stone",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
