package org.ack.sys.base.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

class FileUtilTest {

	@Test
	void testStr(){
		String path = "E:\\myftp\\\\aaa\\bb.jpg";
		String[] strs = path.split("\\\\");
		System.out.println(File.separator);
		System.out.println(File.pathSeparatorChar);
		System.out.println(File.pathSeparator);
		for(int i = 0; i < strs.length; i++){
			String s = strs[i];
			System.out.println("index = " + i + " str = " + s);
		}
	}

	@Test
	void testCreateFile(){
		String path = "E:\\myftp\\/20200708/111.jpg";
		File file = FileUtil.createFile(path);
		assertNotNull(file);
		System.out.println(file.getAbsolutePath());
	}

	@Test
	void testGetCurrentPath() {
		fail("Not yet implemented");
	}

	@Test
	void testGetProperAddrString() {
		fail("Not yet implemented");
	}

	@Test
	void testGetClassesPath() {
		
		String path = FileUtil.getClassesPath();
		File file = FileUtil.createFile(path + "a.txt", "/");
		String p = file.getPath();
		System.out.println(p);
		assertNotNull(path);
		System.out.println(path);
	}

	@Test
	void testGetLocalProperAddr() {
		fail("Not yet implemented");
	}

	@Test
	void testGetProperAddrFile() {
		fail("Not yet implemented");
	}

	@Test
	void testGetProperAddrInputStream() {
		fail("Not yet implemented");
	}

	@Test
	void testWritePropertiesFileStringString() {
		fail("Not yet implemented");
	}

	@Test
	void testWritePropertiesStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	void testCreateFileString() {
		fail("Not yet implemented");
	}

	@Test
	void testCreateFileStringBoolean() {
		fail("Not yet implemented");
	}

}
