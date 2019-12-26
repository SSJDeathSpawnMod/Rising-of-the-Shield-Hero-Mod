package mod.deathspawn.shield.client.model;

import com.google.common.collect.ImmutableList;
import mod.deathspawn.shield.ShieldHeroMod;
import mod.deathspawn.shield.lib.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.BakedItemModel;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.PerspectiveMapWrapper;
import net.minecraftforge.common.model.TRSRTransformation;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.ArrayList;
import java.util.List;

public class ShieldModelPerspective implements IBakedModel {

    String shieldName;
    ModelResourceLocation GUIMRL;
    ModelResourceLocation normalMRL;

    public ShieldModelPerspective(String shieldName) {
        this.shieldName = shieldName;
        GUIMRL = new ModelResourceLocation(new ResourceLocation(Reference.MODID,"shield_" + this.shieldName),"inventory");
        normalMRL = new ModelResourceLocation(new ResourceLocation(Reference.MODID,"shield_" + this.shieldName + "_3d"),"inventory");
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType)
    {
        ModelManager manager = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager();

        IBakedModel model;
        if(cameraTransformType != TransformType.GUI ) {
            ShieldHeroMod.logger.info("Returning normal Model: " + normalMRL.toString());
            model =  manager.getModel(normalMRL);//Get held model
        }
        else {
            ShieldHeroMod.logger.info("Returning GUI Model: " + GUIMRL.toString());
            model = manager.getModel(GUIMRL); //Get inventory model
        }

        //if(!(model instanceof IFlexibleBakedModel)) model = new IFlexibleBakedModel.Wrapper(model, DefaultVertexFormats.ITEM);

        return Pair.of(model, TRSRTransformation.identity().getMatrix());
    }

    @Override
    public boolean isAmbientOcclusion()
    {
        return true;
    }

    @Override
    public boolean isGui3d()
    {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer()
    {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return null;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms()
    {
        return ItemCameraTransforms.DEFAULT; //The requirement for this is a bug
    }

    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand)
    {
        return new ArrayList<BakedQuad>();
    }

    @Override
    public ItemOverrideList getOverrides()
    {
        return new ItemOverrideList(new ArrayList<ItemOverride>());
    }
}
