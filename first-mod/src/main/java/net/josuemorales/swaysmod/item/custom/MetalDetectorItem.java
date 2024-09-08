package net.josuemorales.swaysmod.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
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

    private void outputValuableCoordinates(BlockPos below, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " + "(" + below.getX() + ", " + below.getY() + ", " + below.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(Blocks.IRON_ORE) || state.is(Blocks.GOLD_ORE) || state.is(Blocks.DIAMOND_ORE);
    }
}
