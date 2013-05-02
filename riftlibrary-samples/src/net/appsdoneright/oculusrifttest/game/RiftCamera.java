package net.appsdoneright.oculusrifttest.game;

import android.opengl.Matrix;

public class RiftCamera {
	public float mPosX, mPosY, mPosZ;
	public float mYaw, mPitch, mRoll;
	
	public float mIPD, mEyeHeight;
	
	private float mVMatrix[] = new float[16];
	public float mVMatrixLeft[] = new float[16];
	public float mVMatrixRight[] = new float[16];
	
	public RiftCamera() {
		this(6.0f, 1.0f);
	}
	
	public RiftCamera(float IPD, float eyeHeight) {
		setIPD(IPD);
		mEyeHeight = eyeHeight;
	}

	public void setIPD(float IPD) {
		mIPD = IPD/2.0f;
	}
	
	public void update() {
		// replace with rotation matrix from quaternion
		//Matrix.setRotateEulerM(mVMatrix, 0, mPitch, mYaw, mRoll);
		Matrix.setRotateM(mVMatrix, 0, mYaw, 0, 1, 0);
		Matrix.rotateM(mVMatrix, 0, mPitch, 1, 0, 0);
		Matrix.rotateM(mVMatrix, 0, mRoll, 0, 0, 1);
		
		Matrix.translateM(mVMatrix, 0, -mPosX, -mPosY, -mPosZ);
		
		float cosAngle = (float)Math.cos(mYaw / 180.0 * Math.PI);
    	float singAngle = (float)Math.sin(mYaw / 180.0 * Math.PI);
		
		// left eye
		Matrix.translateM(mVMatrixLeft, 0, mVMatrix, 0, -cosAngle * mIPD, -mEyeHeight, -singAngle * mIPD);
		// right eye
		Matrix.translateM(mVMatrixRight, 0, mVMatrix, 0, cosAngle * mIPD, -mEyeHeight, singAngle * mIPD);

	}
	
	
}