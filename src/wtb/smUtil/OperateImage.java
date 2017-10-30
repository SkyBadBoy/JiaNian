package wtb.smUtil;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import wtb.core.controller.PictureController;
import wtb.core.model.PictureParam;
import wtb.core.model.Pictures;

public class OperateImage {
	// ===源图片路径名称如:c:\1.jpg
	private String srcpath;
	// ===剪切图片存放路径名称.如:c:\2.jpg
	private String subpath;
	// ===剪切点x坐标
	private int x;
	private int y;
	// ===剪切点宽度
	private int width;
	private int height;
	private Boolean isNeedCut;

	public OperateImage() {
	}

	
	public Boolean getIsNeedCut() {
		return isNeedCut;
	}


	public void setIsNeedCut(Boolean isNeedCut) {
		this.isNeedCut = isNeedCut;
	}


	public String getSrcpath() {
		return srcpath;
	}

	public void setSrcpath(String srcpath) {
		this.srcpath = srcpath;
	}

	public String getSubpath() {
		return subpath;
	}

	public void setSubpath(String subpath) {
		this.subpath = subpath;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	/** 对图片裁剪，并把裁剪完的新图片保存 */
	public void cut() throws IOException {
		FileInputStream is = null;
		ImageInputStream iis = null;
		try {
			String Prefix=srcpath.substring(srcpath.lastIndexOf(".")+1);
			// 读取图片文件
			is = new FileInputStream(srcpath);
			/*
			 * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 声称能够解码指定格式。
			 * 参数：formatName - 包含非正式格式名称 . （例如 "jpeg" 或 "tiff"）等 。
			 */
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(Prefix);
			ImageReader reader = it.next();
			// 获取图片流
			iis = ImageIO.createImageInputStream(is);
			/*
			 * <p>iis:读取源.true:只向前搜索 </p>.将它标记为 ‘只向前搜索’。
			 * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
			 */
			reader.setInput(iis, true);
			/*
			 * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O
			 * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 将从其 ImageReader 实现的
			 * getDefaultReadParam 方法中返回 ImageReadParam 的实例。
			 */
			ImageReadParam param = reader.getDefaultReadParam();
			/*
			 * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
			 * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
			 */
			if(isNeedCut==null || (isNeedCut!=null && isNeedCut==true)){
				Rectangle rect = new Rectangle(x, y, width, height);
				// 提供一个 BufferedImage，将其用作解码像素数据的目标。
				param.setSourceRegion(rect);
			}
			/*
			 * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 它作为一个完整的
			 * BufferedImage 返回。
			 */
			BufferedImage bi = reader.read(0, param);
			// 保存新图片
			ImageIO.write(bi, Prefix, new File(subpath));
		} finally {
			if (is != null)
				is.close();
			if (iis != null)
				iis.close();
		}

	}

	/**
	 * 保存图片
	 * 
	 * @param req
	 * @param prefix
	 * @param bytes
	 * @param imgs
	 * @return
	 * @throws Exception
	 */
	public String SavePicture(HttpServletRequest req, String prefix, byte[] bytes, String[] imgs) throws Exception {
		UUID uuid = UUID.randomUUID();
		String path = req.getSession().getServletContext().getRealPath("/upload");
		String SavefileName = uuid.randomUUID() + prefix;
		InputStream  buffin = new ByteArrayInputStream(bytes);
		BufferedImage img = ImageIO.read(buffin); 
		int Srcwidth=img.getWidth();
		int Srcheight=img.getHeight();  
		 
		if(imgs!=null && imgs.length>=5){
			//一般iphone 拍照的照片宽和高反了
			if(Double.parseDouble(imgs[4])>Double.parseDouble(imgs[5]) && img.getWidth()<=img.getHeight() 
					|| Double.parseDouble(imgs[4])<Double.parseDouble(imgs[5]) && img.getWidth()>=img.getHeight()){
				img=Rotate(img,90);
				Srcwidth=Srcwidth+Srcheight;
				Srcheight=Srcwidth-Srcheight;
				Srcwidth=Srcwidth-Srcheight;
			}
		}
		int width=Srcwidth;
		int height=Srcheight; 
		if(Srcwidth>800){
			width=800;
			height=(Srcheight * width / Srcwidth); 
			
		}
		if(height>800){
			height=800;
			width=(Srcwidth * height / Srcheight); 
			
		}
		
		if(imgs!=null && imgs.length<=4){
			imgs=new String[]{imgs[0],imgs[1],imgs[2],imgs[3],String.valueOf(Srcwidth),String.valueOf(Srcheight)};
		}
		BufferedImage imageobj = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB );   
		imageobj.getGraphics().drawImage(img, 0, 0, width, height, null); // 绘制缩小后的图  

		ImageIO.write(imageobj, prefix.substring(1,prefix.length()), new File(path + "/ImageSrc/" + SavefileName)); 
		
		OperateImage image = new OperateImage();
		if(imgs!=null){
			imgs=parseImageScale(width, height,imgs);
		}
		if(imgs!=null && imgs.length>=4 &&Double.parseDouble(imgs[2])>0 &&Double.parseDouble(imgs[3])>0){
			image.setX((int) Double.parseDouble(imgs[0]));
			image.setY((int) Double.parseDouble(imgs[1]));
			image.setWidth((int) Double.parseDouble(imgs[2]));
			image.setHeight((int) Double.parseDouble(imgs[3]));
			image.setSrcpath(path + "/ImageSrc/" + SavefileName);
			image.setSubpath(path + "/" + SavefileName);
		}else{
			image.setIsNeedCut(false);
			image.setSrcpath(path + "/ImageSrc/" + SavefileName);
			image.setSubpath(path + "/" + SavefileName);
		}
		image.cut();
		return SavefileName;

	}
	/**
	 * 折算图片比例
	 * @return
	 */
	private String[]  parseImageScale(int srcWidth ,int srcHeight,String[] imgconf ){
		if((srcWidth>=800 || srcHeight>=800) && imgconf!=null && imgconf.length>=5 &&Double.parseDouble(imgconf[2])>0 &&Double.parseDouble(imgconf[3])>0){
			float Scale=(float)srcWidth/Float.parseFloat(imgconf[4]);
			int len=0;
			if(imgconf[0].indexOf(".")>0){
				len=imgconf[0].indexOf(".")+3;
				imgconf[0] =imgconf[0].substring(0,((len<imgconf[0].length())? len:imgconf[0].length()));
			}
			if(imgconf[1].indexOf(".")>0){
				len=imgconf[1].indexOf(".")+3;
				imgconf[1] =imgconf[1].substring(0,((len<imgconf[1].length())? len:imgconf[1].length()));
			}
			if(imgconf[2].indexOf(".")>0){
				len=imgconf[2].indexOf(".")+3;
				imgconf[2] =imgconf[2].substring(0,((len<imgconf[2].length())? len:imgconf[2].length()));
			}
			if(imgconf[3].indexOf(".")>0){
				len=imgconf[3].indexOf(".")+3;
				imgconf[3] =imgconf[3].substring(0,((len<imgconf[3].length())? len:imgconf[3].length()));
			}
			
				imgconf[0]=String.valueOf(Float.parseFloat(imgconf[0])*Scale);
				imgconf[1]=String.valueOf(Float.parseFloat(imgconf[1])*Scale);
				imgconf[2]=String.valueOf(Float.parseFloat(imgconf[2])*Scale);
				imgconf[3]=String.valueOf(Float.parseFloat(imgconf[3])*Scale);
			
			
			
		}
		return imgconf;
	}
	/**
	 * 获取图片byte
	 * 
	 * @param file
	 * @param Filepath
	 * @return
	 * @throws Exception 
	 */
	public PictureParam getPicutreBytes(MultipartFile file, String Filepath, Pictures imageUrl,String[] imagesize) throws Exception {
		PictureParam picParam=new PictureParam();
		
		if(file.isEmpty() && imageUrl!=null){
			String path=Filepath;
			 InputStream in = new FileInputStream(path+imageUrl.getUrl());
			 byte[] filebytes = new byte[1024];
	            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
	            int n;  
	            while ((n = in.read(filebytes)) != -1) {  
	                bos.write(filebytes, 0, n);  
	            }  
	            in.close();
	            picParam.setBytes(bos.toByteArray());
	            picParam.setFileName(imageUrl.getUrl());
		}else{
			System.out.println("--------"+file.getOriginalFilename());
			 picParam.setBytes(file.getBytes());
	         picParam.setFileName(file.getOriginalFilename());
		}
		picParam.setPrefix(picParam.getFileName().substring(picParam.getFileName().lastIndexOf(".")).toUpperCase());

		if (!picParam.getPrefix().equals(".GIF") && !picParam.getPrefix().equals(".JPG") && !picParam.getPrefix().equals(".PNG") && !picParam.getPrefix().equals(".JPEG")) {
			picParam.setErrorMessage("图上传失败,目前图片仅支持上传 .gif .jpg .png");
		}
		if ((picParam.getBytes().length / 1024) > 4096) {
			picParam.setErrorMessage("图片上传失败,文件大小不能大于4M");
		}
		if(imagesize!=null&&imagesize.length>=4&& !(imagesize[2].equals("0") && imagesize[3].equals("0")) && Math.floor(Double.parseDouble(imagesize[2]))<=0 && Math.floor(Double.parseDouble(imagesize[3]))<=0){
			picParam.setErrorMessage("图片图片过小，请重新选择！");
		}
		return picParam;

	}
	/**
     * 旋转图片为指定角度
     * 
     * @param bufferedimage
     *            目标图像
     * @param degree
     *            旋转角度
     * @return
     */
	public static BufferedImage Rotate(BufferedImage src, int angel) {
		int src_width = src.getWidth(null);
		int src_height = src.getHeight(null);
		// calculate the new image size
		Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(
				src_width, src_height)), angel);

		BufferedImage res = null;
		res = new BufferedImage(rect_des.width, rect_des.height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = res.createGraphics();
		// transform
		g2.translate((rect_des.width - src_width) / 2,
				(rect_des.height - src_height) / 2);
		g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

		g2.drawImage(src, null, null);
		return res;
	}

	public static Rectangle CalcRotatedSize(Rectangle src, int angel) {
		// if angel is greater than 90 degree, we need to do some conversion
		if (angel >= 90) {
			if(angel / 90 % 2 == 1){
				int temp = src.height;
				src.height = src.width;
				src.width = temp;
			}
			angel = angel % 90;
		}

		double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
		double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
		double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
		double angel_dalta_width = Math.atan((double) src.height / src.width);
		double angel_dalta_height = Math.atan((double) src.width / src.height);

		int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha
				- angel_dalta_width));
		int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha
				- angel_dalta_height));
		int des_width = src.width + len_dalta_width * 2;
		int des_height = src.height + len_dalta_height * 2;
		return new java.awt.Rectangle(new Dimension(des_width, des_height));
	}
	
}