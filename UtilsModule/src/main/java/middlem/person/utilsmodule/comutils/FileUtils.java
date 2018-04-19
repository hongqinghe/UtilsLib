package middlem.person.utilsmodule.comutils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/***********************************************
 *
 * <P> desc:    文件管理类型
 * <P> Author: gongtong
 * <P> Date: 2017-10-24 21:45
 ***********************************************/
 
public class FileUtils {

	private FileUtils() {

	}

	/**
	 * 获取Apk文件存放路径
	 * @return String
     */
	public static String getApkFilePath(){
		String suffix = "/.tdf/";
		String sd_path = Environment.getExternalStorageDirectory().getPath() + suffix;
		
		String data_path = Environment.getDataDirectory().getPath() + suffix;
		
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return sd_path;
		}else {
			return data_path;
		}
	}
	
	/**
	 * 讲指定的Bitmap保存至SD卡
	 * @param bitmap
	 * @param fileName
	 * @return boolean
	 */
	public static boolean saveBitmapToSDCard(Bitmap bitmap, String fileName) {
		// 判断SD卡是否已存在
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			FileOutputStream outputStream = null;
			File inFile = null;
			boolean bSaved = false;
			try {

				String strDir = fileName.substring(0, fileName.lastIndexOf('/') + 1);
				if (!createFilePath(null, strDir)) {
					bSaved = false;
				}
				inFile = new File(fileName);
				outputStream = new FileOutputStream(inFile);
				if (bitmap != null && bitmap.compress(CompressFormat.PNG, 100, outputStream)) {
					bSaved = true;
				} else {
					bSaved = false;
					if (inFile != null) {
						inFile.delete();
					}
				}
			} catch (FileNotFoundException e) {
				if (inFile != null) {
					inFile.delete();
				}
				bSaved = false;
				e.printStackTrace();
			} catch (Exception e) {
				if (inFile != null) {
					inFile.delete();
				}
				bSaved = false;
				e.printStackTrace();
			} finally {
				try {
					if (outputStream != null) {
						outputStream.close();
						outputStream = null;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return bSaved;
		} else {
			return false;
		}
	}
	
	/**
	 * 创建本地文件目录
	 * @param file
	 * @param filePath
	 * @return boolean
	 */
	public static boolean createFilePath(File file, String filePath) {
		int index = filePath.indexOf("/");

		if (index == -1) 
		{
			return false;
		}
		if (index == 0) {
			filePath = filePath.substring(index + 1, filePath.length());
			index = filePath.indexOf("/");
		}

		String path = filePath.substring(0, index);
		File fPath;
		if (file == null) {
			fPath = new File(path);
		} else {
			fPath = new File(file.getPath() + "/" + path);
		}
		if (!fPath.exists()) {
			if (!fPath.mkdir()) // SD卡已满无法在下载文件
			{
				return false;
			}
		}
		if (index < (filePath.length() - 1)) {
			String exPath = filePath.substring(index + 1, filePath.length());
			createFilePath(fPath, exPath);
		}
		
		return true;
	}
	
	/**
	 * 获取文件或文件夹是否存在
	 * @param path
	 * @return boolean
	 */
	public static boolean isFileExist(String path){
		if (StringUtils.isEmpty(path)) {
			return false;
		}
		File file = new File(path);
		
		return file.exists();
	}
	
	/**
	 * 删除文件或文件夹
	 * @param path
	 */
	public static void deleteFiles(String path) {
		File file = new File(path);
		if (file.exists()) { 									// 判断文件是否存在
			if (file.isFile()) { 								// 判断是否是文件
				file.delete(); 									// delete()方法 你应该知道 是删除的意思;
			} else if (file.isDirectory()) { 					// 否则如果它是一个目录
				File files[] = file.listFiles(); 				// 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { 		// 遍历目录下所有的文件
					deleteFiles(files[i].getPath()); 			// 把每个文件 用这个方法进行迭代
				}
			}
			
			file.delete();
		}
	}
	
	/**
	 * 重命名文件
	 * @param oldName
	 * @param newName
	 */
	public static void renameFile(String oldName, String newName) {
		if (isFileExist(oldName)) {
			if (isFileExist(newName)) {
				//deleteFiles(newName);
				return;
			}
			
			File file = new File(oldName);
			file.renameTo(new File(newName));
		}
	}

	/**
	 * 得到热补丁打补丁文件.
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static File getHotFixPatch(Context context, String path, String fileName){
		File fileDir = new File(context.getCacheDir().getAbsolutePath()+ File.separator+path);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		File patchFile = new File(fileDir,fileName);
		return  patchFile;
	}


	/**
	 * 得到热补丁打补丁文件.
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static File getHotFixPatch(String path, String fileName){
		File fileDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+path);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		File patchFile = new File(fileDir,fileName);
		return  patchFile;
	}

	/**
	 * 获取单个文件的MD5值！

	 * @param file
	 * @return
	 */

	public static String getFileMD5(File file) {
		if (!file.isFile()) {
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}

	/**
	 * 根据uri获取文件path
	 * @param context
	 * @param uri
	 * @return
	 */
	public static String getFilePathFromContentUri(Context context, Uri uri) {
		String photoPath = "";
		if(context == null || uri == null) {
			return photoPath;
		}

		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, uri)) {
			String docId = DocumentsContract.getDocumentId(uri);
			if(isExternalStorageDocument(uri)) {
				String[] split = docId.split(":");
				if(split.length >= 2) {
					String type = split[0];
					if("primary".equalsIgnoreCase(type)) {
						photoPath = Environment.getExternalStorageDirectory() + "/" + split[1];
					}
				}
			}
			else if(isDownloadsDocument(uri)) {
				Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
				photoPath = getDataColumn(context, contentUri, null, null);
			}
			else if(isMediaDocument(uri)) {
				String[] split = docId.split(":");
				if(split.length >= 2) {
					String type = split[0];
					Uri contentUris = null;
					if("image".equals(type)) {
						contentUris = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
					}
					else if("video".equals(type)) {
						contentUris = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
					}
					else if("audio".equals(type)) {
						contentUris = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
					}
					String selection = MediaStore.Images.Media._ID + "=?";
					String[] selectionArgs = new String[] { split[1] };
					photoPath = getDataColumn(context, contentUris, selection, selectionArgs);
				}
			}
		}
		else if("file".equalsIgnoreCase(uri.getScheme())) {
			photoPath = uri.getPath();
		}
		else {
			photoPath = getDataColumn(context, uri, null, null);
		}

		return photoPath;
	}

	private static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri.getAuthority());
	}

	private static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri.getAuthority());
	}

	private static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri.getAuthority());
	}

	private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
		Cursor cursor = null;
		String column = MediaStore.Images.Media.DATA;
		String[] projection = { column };
		try {
			cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				int index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(index);
			}
		} finally {
			if (cursor != null && !cursor.isClosed())
				cursor.close();
		}
		return null;
	}
}
