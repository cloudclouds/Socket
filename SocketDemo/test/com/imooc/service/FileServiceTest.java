package com.imooc.service;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.imooc.entity.File;

public class FileServiceTest {

	@Test
	public void testUploadFile() throws IOException {
		java.io.File file=new java.io.File("e://imgs/1.jpg");
		long len=file.length();
		byte[] b=new byte[(int)len];
		FileInputStream is=new FileInputStream(file);
		is.read(b);
		File f=new File();
		f.setFname(file.getName());
		f.setFcontent(b);
		System.out.println(new FileService().uploadFile(f));
	}

}
