package net.minecraft.commands.arguments.item;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.serialization.DynamicOps;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemInput {
    private static final Dynamic2CommandExceptionType ERROR_STACK_TOO_BIG = new Dynamic2CommandExceptionType(
        (p_308404_, p_308405_) -> Component.translatableEscape("arguments.item.overstacked", p_308404_, p_308405_)
    );
    private final Holder<Item> item;
    private final DataComponentMap components;

    public ItemInput(Holder<Item> pItem, DataComponentMap pComponents) {
        this.item = pItem;
        this.components = pComponents;
    }

    public Item getItem() {
        return this.item.value();
    }

    public ItemStack createItemStack(int pCount, boolean pAllowOversizedStacks) throws CommandSyntaxException {
        ItemStack itemstack = new ItemStack(this.item, pCount);
        itemstack.applyComponents(this.components);
        if (pAllowOversizedStacks && pCount > itemstack.getMaxStackSize()) {
            throw ERROR_STACK_TOO_BIG.create(this.getItemName(), itemstack.getMaxStackSize());
        } else {
            return itemstack;
        }
    }

    public String serialize(HolderLookup.Provider pLevelRegistry) {
        StringBuilder stringbuilder = new StringBuilder(this.getItemName());
        String s = this.serializeComponents(pLevelRegistry);
        if (!s.isEmpty()) {
            stringbuilder.append('[');
            stringbuilder.append(s);
            stringbuilder.append(']');
        }

        return stringbuilder.toString();
    }

    private String serializeComponents(HolderLookup.Provider pLevelRegistries) {
        DynamicOps<Tag> dynamicops = pLevelRegistries.createSerializationContext(NbtOps.INSTANCE);
        return this.components.stream().flatMap(p_325610_ -> {
            DataComponentType<?> datacomponenttype = p_325610_.type();
            ResourceLocation resourcelocation = BuiltInRegistries.DATA_COMPONENT_TYPE.getKey(datacomponenttype);
            Optional<Tag> optional = p_325610_.encodeValue(dynamicops).result();
            return resourcelocation != null && !optional.isEmpty() ? Stream.of(resourcelocation.toString() + "=" + optional.get()) : Stream.empty();
        }).collect(Collectors.joining(String.valueOf(',')));
    }

    private String getItemName() {
        return this.item.unwrapKey().<Object>map(ResourceKey::location).orElseGet(() -> "unknown[" + this.item + "]").toString();
    }
}