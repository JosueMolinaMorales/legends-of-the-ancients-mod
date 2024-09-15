package net.minecraft.client.resources;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MapDecorationTextureManager extends TextureAtlasHolder {
    public MapDecorationTextureManager(TextureManager pTextureManager) {
        super(pTextureManager, new ResourceLocation("textures/atlas/map_decorations.png"), new ResourceLocation("map_decorations"));
    }

    public TextureAtlasSprite get(MapDecoration pMapDecoration) {
        return this.getSprite(pMapDecoration.getSpriteLocation());
    }
}