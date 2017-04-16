package shiftinggears.block.crank;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * crank - shadowfacts
 * Created using Tabula 5.1.0
 */
public class ModelCrank extends ModelBase {
	public ModelRenderer base;
	public ModelRenderer shape2;
	public ModelRenderer shape4;
	public ModelRenderer shape5;
	public ModelRenderer shape6;

	public ModelCrank() {
		this.textureWidth = 32;
		this.textureHeight = 32;
		this.shape2 = new ModelRenderer(this, 0, 6);
		this.shape2.setRotationPoint(1.5F, -4.0F, 1.5F);
		this.shape2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
		this.base = new ModelRenderer(this, 0, 0);
		this.base.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.base.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4, 0.0F);
		this.shape4 = new ModelRenderer(this, 0, 6);
		this.shape4.setRotationPoint(1.5F, -4.0F, 1.5F);
		this.shape4.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
		this.setRotateAngle(shape4, 1.5707963267948966F, 0.0F, 0.0F);
		this.shape6 = new ModelRenderer(this, 5, 6);
		this.shape6.setRotationPoint(1.0F, -10.0F, 4.0F);
		this.shape6.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
		this.shape5 = new ModelRenderer(this, 0, 6);
		this.shape5.setRotationPoint(1.5F, -11.0F, 4.5F);
		this.shape5.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1, 0.0F);
	}

	public void render(float scale) {
		this.shape2.render(scale);
		this.base.render(scale);
		this.shape4.render(scale);
		this.shape6.render(scale);
		this.shape5.render(scale);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
