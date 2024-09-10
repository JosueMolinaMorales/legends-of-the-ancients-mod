package net.josuemorales.swaysmod.item.custom;

import net.josuemorales.swaysmod.utils.ModTags;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties properties) {
        super(properties);
    }
    
    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        BlockPos positionClicked = pContext.getClickedPos();
        Player player = pContext.getPlayer();

        if (player == null) {
            return InteractionResult.FAIL;
        }

        boolean foundBlock = false;

        for (int i = 0; i <= positionClicked.getY() + 64; i++) {
            BlockState state = pContext.getLevel().getBlockState(positionClicked.below(i));

            if (isValuableBlock(state)) {
                outputValuableCoordinates(positionClicked.below(i), player, state.getBlock());
                foundBlock = true;

                break;
            }
        }

        if (!foundBlock) {
            player.sendSystemMessage(Component.literal("No valuable blocks found"));
        }

        pContext.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(pContext.getHand()));
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.sways_mod.metal_detector.tooltip"));
    }

    private void outputValuableCoordinates(BlockPos below, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " + "(" + below.getX() + ", " + below.getY() + ", " + below.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES);
    }
}
