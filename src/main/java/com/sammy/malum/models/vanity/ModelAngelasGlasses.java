package com.sammy.malum.models.vanity;// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class ModelAngelasGlasses<T extends LivingEntity> extends BipedModel<T>
{
	public final ModelRenderer glasses;
	
	public ModelAngelasGlasses()
	{
		super(1);
		textureWidth = 64;
		textureHeight = 64;
		
		glasses = new ModelRenderer(this);
		glasses.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedHead.addChild(glasses);
		glasses.setTextureOffset(0, 0).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.3F, false);
	}
	
	@Override
	public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		//previously the render function, render code was moved to a method below
	}
	
	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		bipedHead = glasses;
		glasses.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}