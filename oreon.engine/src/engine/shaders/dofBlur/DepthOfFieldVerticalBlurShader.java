package engine.shaders.dofBlur;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import engine.core.Window;
import engine.shaders.Shader;
import engine.textures.Texture2D;
import engine.utils.ResourceLoader;

public class DepthOfFieldVerticalBlurShader extends Shader{

private static DepthOfFieldVerticalBlurShader instance = null;
	
	public static DepthOfFieldVerticalBlurShader getInstance() 
	{
	    if(instance == null) 
	    {
	    	instance = new DepthOfFieldVerticalBlurShader();
	    }
	      return instance;
	}
	
	protected DepthOfFieldVerticalBlurShader()
	{
		super();
		
		addComputeShader(ResourceLoader.loadShader("shaders/computing/DepthOfFieldBlur/verticalGaussianDoF_CS.glsl"));
		
		compileShader();
		
		addUniform("depthmap");
		addUniform("windowWidth");
		addUniform("windowHeight");
	}
	
	public void updateUniforms(Texture2D depthmap)
	{
		glActiveTexture(GL_TEXTURE0);
		depthmap.bind();
		setUniformi("depthmap", 0);
		setUniformf("windowWidth", Window.getInstance().getWidth());
		setUniformf("windowHeight", Window.getInstance().getHeight());
	}
}
