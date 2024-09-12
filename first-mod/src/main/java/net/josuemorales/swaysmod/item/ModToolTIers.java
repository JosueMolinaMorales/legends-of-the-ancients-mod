package net.josuemorales.swaysmod.item;

import net.josuemorales.swaysmod.utils.ModTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;


public class ModToolTiers {

    public static final ForgeTier SAPPHIRE = new ForgeTier(
            1500,
            1.5f,
            7,
            15,
            ModTags.Blocks.NEEDS_SAPPHIRE_TOOL,
            () -> Ingredient.of(ModItems.SAPPHIRE.get()),
            ModTags.Blocks.INCORRECT_FOR_SAPPHIRE_TOOL
    );
}
