package net.minecraft.client.render.camera;

import net.minecraft.game.physics.AxisAlignedBB;

public final class Frustrum implements ICamera {
	private ClippingHelper clippingHelper = ClippingHelperImplementation.init();
	public float[][] m_Frustum = new float[16][16];

	public final boolean isBoundingBoxInFrustrum(AxisAlignedBB aabb) {
		ClippingHelper clippingHelper10001 = this.clippingHelper;
		float f7 = aabb.maxZ;
		float f6 = aabb.maxY;
		float f5 = aabb.maxX;
		float f4 = aabb.minZ;
		float f3 = aabb.minY;
		float f2 = aabb.minX;
		ClippingHelper clippingHelper9 = this.clippingHelper;

		for(int i8 = 0; i8 < 6; ++i8) {
			if(clippingHelper9.frustrum[i8][0] * f2 + clippingHelper9.frustrum[i8][1] * f3 + clippingHelper9.frustrum[i8][2] * f4 + clippingHelper9.frustrum[i8][3] <= 0.0F && clippingHelper9.frustrum[i8][0] * f5 + clippingHelper9.frustrum[i8][1] * f3 + clippingHelper9.frustrum[i8][2] * f4 + clippingHelper9.frustrum[i8][3] <= 0.0F && clippingHelper9.frustrum[i8][0] * f2 + clippingHelper9.frustrum[i8][1] * f6 + clippingHelper9.frustrum[i8][2] * f4 + clippingHelper9.frustrum[i8][3] <= 0.0F && clippingHelper9.frustrum[i8][0] * f5 + clippingHelper9.frustrum[i8][1] * f6 + clippingHelper9.frustrum[i8][2] * f4 + clippingHelper9.frustrum[i8][3] <= 0.0F && clippingHelper9.frustrum[i8][0] * f2 + clippingHelper9.frustrum[i8][1] * f3 + clippingHelper9.frustrum[i8][2] * f7 + clippingHelper9.frustrum[i8][3] <= 0.0F && clippingHelper9.frustrum[i8][0] * f5 + clippingHelper9.frustrum[i8][1] * f3 + clippingHelper9.frustrum[i8][2] * f7 + clippingHelper9.frustrum[i8][3] <= 0.0F && clippingHelper9.frustrum[i8][0] * f2 + clippingHelper9.frustrum[i8][1] * f6 + clippingHelper9.frustrum[i8][2] * f7 + clippingHelper9.frustrum[i8][3] <= 0.0F && clippingHelper9.frustrum[i8][0] * f5 + clippingHelper9.frustrum[i8][1] * f6 + clippingHelper9.frustrum[i8][2] * f7 + clippingHelper9.frustrum[i8][3] <= 0.0F) {
				return false;
			}
		}

		return true;
	}
	
	
	public final boolean cubeInFrustum(float x1, float y1, float z1, float x2, float y2, float z2) {
		for(int i7 = 0; i7 < 6; ++i7) {
			if(this.m_Frustum[i7][0] * x1 + this.m_Frustum[i7][1] * y1 + this.m_Frustum[i7][2] * z1 + this.m_Frustum[i7][3] <= 0.0F && this.m_Frustum[i7][0] * x2 + this.m_Frustum[i7][1] * y1 + this.m_Frustum[i7][2] * z1 + this.m_Frustum[i7][3] <= 0.0F && this.m_Frustum[i7][0] * x1 + this.m_Frustum[i7][1] * y2 + this.m_Frustum[i7][2] * z1 + this.m_Frustum[i7][3] <= 0.0F && this.m_Frustum[i7][0] * x2 + this.m_Frustum[i7][1] * y2 + this.m_Frustum[i7][2] * z1 + this.m_Frustum[i7][3] <= 0.0F && this.m_Frustum[i7][0] * x1 + this.m_Frustum[i7][1] * y1 + this.m_Frustum[i7][2] * z2 + this.m_Frustum[i7][3] <= 0.0F && this.m_Frustum[i7][0] * x2 + this.m_Frustum[i7][1] * y1 + this.m_Frustum[i7][2] * z2 + this.m_Frustum[i7][3] <= 0.0F && this.m_Frustum[i7][0] * x1 + this.m_Frustum[i7][1] * y2 + this.m_Frustum[i7][2] * z2 + this.m_Frustum[i7][3] <= 0.0F && this.m_Frustum[i7][0] * x2 + this.m_Frustum[i7][1] * y2 + this.m_Frustum[i7][2] * z2 + this.m_Frustum[i7][3] <= 0.0F) {
				return false;
			}
		}

		return true;
	}
}