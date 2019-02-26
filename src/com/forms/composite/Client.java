package com.forms.composite;

public class Client {
	public static void main(String[] args) {
		Folder f1,f2;
		AbstractFile f3,f4,f5;
		f1 = new Folder("D盘");
		f2 = new Folder("文件夹1");
		f3 = new ImgFile("照片");
		f4 = new TextFile("文本");
		f5 = new VideoFile("视频");
		f2.add(f3);
		f2.add(f4);
		f1.add(f2);
		f1.add(f5);
		
//		f2.killVirus();
		f1.killVirus();
		
	}
}
