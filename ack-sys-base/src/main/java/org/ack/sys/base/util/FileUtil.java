package org.ack.sys.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;


/**
 * 文件常用工具类
 * 
 * @author ack
 *
 */
public class FileUtil {
	
	public static String getCurrentPath() {
		return System.getProperty("user.dir");
	}

	/**
	 * properties文件处理
	 * <p>
	 * 绝对路径
	 * 
	 * @param path
	 * @return <code>Properties</code>
	 */
	public static Properties getProperAddr(String path) {
		InputStream inputStream = null;
		Properties p = null;
		try {
			inputStream = new FileInputStream(path);
			p = getProperAddr(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public static String getClassesPath(String path) {
		ClassLoader classLoader = FileUtil.class.getClassLoader();
		URL url = classLoader.getResource(path);
		return url.getPath();
	}
	
	public static String getClassesPath() {
		//String path = FileUtil.class.getResource("/").getPath();
		return getClassesPath("");
	}

	/**
	 * properties文件处理
	 * <p>
	 * jar包或者classPath
	 * 
	 * @param path
	 * @return
	 */
	public static Properties getLocalProperAddr(String path) {
		InputStream inputStream = FileUtil.class.getClassLoader()
				.getResourceAsStream(path);
		Properties p = getProperAddr(inputStream);
		return p;
	}

	/**
	 * properties文件处理
	 * 
	 * @param in
	 * @return
	 */
	public static Properties getProperAddr(File  file) {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(file));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return p;
	}
	/**
	 * properties文件处理
	 * 
	 * @param in
	 * @return
	 */
	public static Properties getProperAddr(InputStream in) {
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return p;
	}
	
	public static void writeProperties(File file, String keyname,String keyvalue) {          
        try { 
        	Properties p = getProperAddr(file);
            OutputStream fos = new FileOutputStream(file);   
            p.setProperty(keyname, keyvalue);   
            p.store(fos, "Update '" + keyname + "' value");   
        } catch (IOException e) {   
            System.err.println("属性文件更新错误");   
        }   
    } 
	
	public static void writeProperties(String file, String keyname,String keyvalue) {          
		writeProperties(new File(file), keyname, keyvalue);
	}   
	

	/**
	 * 创建新文件
	 * 
	 * @param outputPath
	 * @return
	 */
	public static File createFile(String outputPath) {
		return createFile(outputPath, true);
	}
	
	/**
	 * 创建新文件
	 * 
	 * @param outputPath
	 * @return
	 */
	public static File createFile(String outputPath, String separator) {
		return createFile(outputPath, separator, true);
	}

	/**
	 * @param outputPath
	 *            文件绝对路径
	 * @param b
	 *            true 不在意是否文件已存在 ; false 文件已存在返回null
	 * @return
	 */
	public static File createFile(String outputPath, boolean b) {
		return createFile(outputPath, File.pathSeparator, b);
	}
	
	/**
	 * @param outputPath
	 *            文件绝对路径
	 * @param b
	 *            true 不在意是否文件已存在 ; false 文件已存在返回null
	 * @return
	 */
	public static File createFile(String outputPath, String separator, boolean b) {
		int index = outputPath.lastIndexOf(separator);
		File file = null;
		if(-1 == index){
			file = new File(".");
		} else {
			String dir = outputPath.substring(0, index);
			file = new File(dir);
			if (!file.exists()) {
				file.mkdirs();// create dir
			}
		}
		String realName = outputPath.substring(index + 1);
		File realFile = new File(file, realName);
		if (realFile.exists()) {
			if (b) {
				return realFile;
			}
			return null;
		}
		try {
			realFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return realFile;
	}
	public static boolean isSymlink(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("File must not be null");
        }
        //FilenameUtils.isSystemWindows()
        if (File.separatorChar == '\\') {
            return false;
        }
        File fileInCanonicalDir = null;
        if (file.getParent() == null) {
            fileInCanonicalDir = file;
        } else {
            File canonicalDir = file.getParentFile().getCanonicalFile();
            fileInCanonicalDir = new File(canonicalDir, file.getName());
        }

        if (fileInCanonicalDir.getCanonicalFile().equals(fileInCanonicalDir.getAbsoluteFile())) {
            return false;
        } else {
            return true;
        }
    }
	
	/**
     * Schedules a directory recursively for deletion on JVM exit.
     *
     * @param directory directory to delete, must not be {@code null}
     * @throws NullPointerException if the directory is {@code null}
     * @throws IOException          in case deletion is unsuccessful
     */
    private static void deleteDirectoryOnExit(final File directory) throws IOException {
        if (!directory.exists()) {
            return;
        }

        directory.deleteOnExit();
        if (!isSymlink(directory)) {
            cleanDirectoryOnExit(directory);
        }
    }
    
    /**
     * Schedules a file to be deleted when JVM exits.
     * If file is directory delete it and all sub-directories.
     *
     * @param file file or directory to delete, must not be {@code null}
     * @throws NullPointerException if the file is {@code null}
     * @throws IOException          in case deletion is unsuccessful
     */
    public static void forceDeleteOnExit(final File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectoryOnExit(file);
        } else {
            file.deleteOnExit();
        }
    }

    /**
     * Cleans a directory without deleting it.
     *
     * @param directory directory to clean, must not be {@code null}
     * @throws NullPointerException if the directory is {@code null}
     * @throws IOException          in case cleaning is unsuccessful
     */
    private static void cleanDirectoryOnExit(final File directory) throws IOException {
        if (!directory.exists()) {
            String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }

        if (!directory.isDirectory()) {
            String message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        }

        File[] files = directory.listFiles();
        if (files == null) {  // null if security restricted
            throw new IOException("Failed to list contents of " + directory);
        }

        IOException exception = null;
        for (File file : files) {
            try {
                forceDeleteOnExit(file);
            } catch (IOException ioe) {
                exception = ioe;
            }
        }

        if (null != exception) {
            throw exception;
        }
    }
    
    /**
     * Deletes a file. If file is a directory, delete it and all sub-directories.
     * <p>
     * The difference between File.delete() and this method are:
     * <ul>
     * <li>A directory to be deleted does not have to be empty.</li>
     * <li>You get exceptions when a file or directory cannot be deleted.
     * (java.io.File methods returns a boolean)</li>
     * </ul>
     *
     * @param file file or directory to delete, must not be {@code null}
     * @throws NullPointerException  if the directory is {@code null}
     * @throws FileNotFoundException if the file was not found
     * @throws IOException           in case deletion is unsuccessful
     */
    public static void forceDelete(final File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
        } else {
            final boolean filePresent = file.exists();
            if (!file.delete()) {
                if (!filePresent) {
                    throw new FileNotFoundException("File does not exist: " + file);
                }
                final String message =
                        "Unable to delete file: " + file;
                throw new IOException(message);
            }
        }
    }
    
    public static void forceDelete(String path) throws IOException {
    	File file = new File(path);
    	forceDelete(file);
    }
    
    /**
     * Cleans a directory without deleting it.
     *
     * @param directory directory to clean
     * @throws IOException              in case cleaning is unsuccessful
     * @throws IllegalArgumentException if {@code directory} does not exist or is not a directory
     */
    public static void cleanDirectory(final File directory) throws IOException {
        if (!directory.exists()) {
            final String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }

        if (!directory.isDirectory()) {
            final String message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        }

        final File[] files = directory.listFiles();
        if (files == null) {  // null if security restricted
            throw new IOException("Failed to list contents of " + directory);
        }

        IOException exception = null;
        for (File file : files) {
            try {
                forceDelete(file);
            } catch (IOException ioe) {
                exception = ioe;
            }
        }

        if (null != exception) {
            throw exception;
        }
    }
	
	public static void deleteDirectory(final File directory) throws IOException {
        if (!directory.exists()) {
            return;
        }

        if (!isSymlink(directory)) {
            cleanDirectory(directory);
        }

        if (!directory.delete()) {
            final String message =
                    "Unable to delete directory " + directory + ".";
            throw new IOException(message);
        }
    }
	
	public static String getFileType(String fileName) {
		String[] fileType = fileName.split("\\.");
		int len = fileType.length;
		if (len == 1) {
			return null;
		}
		String type = fileType[len - 1];
		return type;
		
	}

	public static boolean typeMatch(String fileName, String types) {
		boolean b = false;
		String[] strs = types.split(",");
		String fileType = getFileType(fileName);
		if(null == fileType) {
			return b;
		}
		for(String type : strs) {
			if(fileType.equalsIgnoreCase(type)) {
				b = true;
				break;
			}
		}
		return b;
	}
}
