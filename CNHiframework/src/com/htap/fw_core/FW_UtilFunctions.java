package com.htap.fw_core;
import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * <h1>Base class to provide Basic Utility function</h1>
 * The base class is used to halt execution and directory related functionality
 * This class contains methods to run batch script file
 * <p>
 * <b>Usage:</b><br>
 * <code>FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();</code>
 * <p>
 * @author Rohit Kothari
 */


public class FW_UtilFunctions {
	private static Logger logger = Logger.getLogger(FW_UtilFunctions.class.getName());

	FW_Init fw_Init = new FW_Init();
	public FW_PropertyReader fw_PropertyReader =new FW_PropertyReader();

	/**
	 * Halts the execution unconditionally or Waits for the fixed amount of time unconditionally.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();
	 * fw_UtilFunctions.haltExecution(60);
	 * </code>
	 * <p>
	 * @param seconds Time in seconds to unconditionally wait or halt the execution.
	 */
	public void haltExecution(int seconds){
		logger.debug("[ FW_UtilFunctions : haltExecution ] seconds " + seconds);
		try {
			logger.debug("[FW_UtilFunctions : FW_UtilFunctions : haltExecution] Waiting for " + seconds + " seconds");
			int miliSeconds = seconds * FW_Const.THOUSAND;
			Thread.sleep(miliSeconds);
		} catch (InterruptedException e) {
			logger.error("[FW_UtilFunctions : haltExecution] InterruptedException: " + e.getMessage());

		}

	}


	/**
	 * Compares gold and runtime images pixel to pixel and returns <code><b>true</b></code> if Expected and Actual images match
	 * else returns <code><b>false</b></code>
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();<br>
	 * BufferedImage expectedImage = ImageIO.read( new File("D:/myWorkspace/Project/com/test/auto/img/gold/expected.png"));
	 * BufferedImage runTimeImage = ImageIO.read(new File("D:/myWorkspace/Project/com/test/auto/img/runtime/actual.png"));
	 * boolean match = fw_UtilFunctions.compareImage(expectedImage, runTimeImage);
	 * </code>
	 * <p>
	 * @param expectedImage Expected image or gold image.
	 * @param runTimeImage Actual image or runtime image.
	 * <p>
	 * @return <code><b>true</b></code> if Expected and Actual images match else returns <code><b>false</b></code>
	 */
	public boolean compareGoldImageWithRuntimeImage(Image expectedImage, Image runTimeImage)  {

		logger.debug("[FW_UtilFunctions : compareGoldImageWithRuntimeImage ] comparing expectedImage with runTimeImage.");
		boolean ImageCompareResult = false;


		try {
			PixelGrabber grab1 = new PixelGrabber(expectedImage, 0, 0, -1, -1, false);
			PixelGrabber grab2 = new PixelGrabber(runTimeImage, 0, 0, -1, -1, false);

			int[] data1 = null;

			if (grab1.grabPixels()) {
				int width = grab1.getWidth();
				int height = grab1.getHeight();
				data1 = new int[width * height];
				data1 = (int[]) grab1.getPixels();
			}

			int[] data2 = null;

			if (grab2.grabPixels()) {
				int width = grab2.getWidth();
				int height = grab2.getHeight();
				data2 = new int[width * height];
				data2 = (int[]) grab2.getPixels();
			}

			logger.info("Pixels equal: " + java.util.Arrays.equals(data1, data2));
			ImageCompareResult=java.util.Arrays.equals(data1, data2);

		} catch (InterruptedException e1) {
			logger.error("[FW_UtilFunctions : compareGoldImageWithRuntimeImage ]  ");
		}

		return ImageCompareResult;
	}

	/**
	 * Execute the batch script using java runtime.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();<br>
	 * fw_UtilFunctions.executeBatchFile(pathWithName);
	 * </code>
	 * <p>
	 * @param pathWithName: Takes an argument as complete path with batch file name
	 */

	public void executeBatchFile(String pathWithName)
	{	try {
		Runtime.getRuntime().exec("cmd /c start "+pathWithName);
	} catch (IOException e) {
		logger.error("[FW_UtilFunctions : executeBatchFile] unable to execute batch file: " + e.getMessage());
	}
	}

	/**
	 * Used to create Directory.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();<br>
	 * fw_UtilFunctions.checkAndCreateDirectory("D:\\directoryName")
	 * </code>
	 * </p>
	 * @param directoryNameWithPath : Directory/Folder to be created.
	 */
	public void checkAndCreateDirectory(String directoryNameWithPath) {

		File newFolderName = new File(directoryNameWithPath);
		if(!newFolderName.exists()){
			logger.debug("[FW_UtilFunctions : checkAndCreateFolder] Creating new folder: " + directoryNameWithPath);
			newFolderName.mkdirs();
		}
	}


	/**
	 * Used to delete Directory.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();<br>
	 * fw_UtilFunctions.deleteDirectory("D:\\directoryName")
	 * </code>
	 * </p>
	 * @param directoryName : Directory/Folder to be deleted.
	 */
	public void deleteDirectory(String directoryName){
		File file = new File(directoryName);
		try {
			if (!file.exists()){
				logger.info("[FW_UtilFunctions : deleteDirectory ] Directory does not Exists!");
			}
			FileUtils.forceDelete(new File(directoryName));
			logger.info("Directory is deleted.");
		} catch (IOException e) {
			logger.error("[FW_UtilFunctions : deleteDirectory ]  Unable to delete directory"+ e.getMessage());

		}

	}

	/**
	 * Runs the external program
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();
	 * fw_UtilFunctions.runExternalProgram("notepad.exe");
	 * </code>
	 * <p>
	 * @param programName Name of the program to be executed.
	 */
	public void runExternalProgram(String programName) {
		logger.info("[FW_UtilFunctions : runExternalProgram] Running program: " + programName);
		Runtime rt = Runtime.getRuntime();
		try {
			Process proc = rt.exec("cmd /c start " + programName);
			logger.info("[FW_UtilFunctions : runExternalProgram] Process exitValue: " + proc.waitFor());
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Returns the absolute file path for the test data file supplied by the user <code>dataFilename</code>.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();<br>
	 * String absolutePath = fw_UtilFunctions.getAbsoluteTestDataFilePath("D_WebGoogleMapsInput.csv");
	 * </code>
	 * <p>
	 * @param dataFilename Name of the test data file to read.
	 * <p>
	 * @return absolutePath Absolute file path for the test data file.
	 */
	public String getAbsoluteTestDataFilePath(String dataFilename) {
		FW_Init fw_Init = new FW_Init();

		String absolutePath="";
		try {
			String filename = fw_Init.getWorkingDir() + FW_Const.BACK_SLASH + fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_WORKING_DIR) + FW_Const.BACK_SLASH + FW_Const.TEST_INPUT_DATA_RELATIVE_PATH + dataFilename;
			logger.debug("[FW_UtilFunctions : getAbsoluteTestDataFilePath] File name: " + filename);
			File fileObj = new File(filename);

			if(fileObj.isFile() && fileObj.canRead()) {
				absolutePath = fileObj.getAbsolutePath();
			} else {
				logger.error("[FW_UtilFunctions : getAbsoluteTestDataFilePath] File not found! " + filename);
				throw new UnknownError("File not found!");
			}

		} catch (Exception e) {
			logger.error("[FW_UtilFunctions : getAbsoluteTestDataFilePath] Exception: File not found! " + e.getMessage());
			throw new UnknownError("File not found!");
		}
		return absolutePath;
	}
	/**
	 * This method will create results folder with name supplied by the user and add current time stamp as suffix.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();<br>
	 * fw_UtilFunctions.createNewResultsFolder(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_RESULTS_FOLDER));
	 * </code>
	 * <p>
	 * @param foldernamewithPath Name of the test result folder alonh with path.
	 * @return Return the name of the result folder.
	 */
	public String createNewResultsFolder(String foldernamewithPath){
		String timestamp = new Timestamp(System.currentTimeMillis()).toString();
		timestamp = timestamp.replaceAll("[ :.]", "_");
		String resultsFolder=foldernamewithPath+"_"+timestamp;
		File d = new File(resultsFolder);
		d.mkdir();
		return resultsFolder;
	}

	/**
	 * This function recursively copy all the sub folder and files from sourceFolder to destinationFolder.
	 *<p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();<br>
	 * fw_UtilFunctions.copyResultsFolder(resultsFolder, fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_RESULTS_FOLDER));
	 * </code>
	 * <p>
	 * @param sourceFolderNameWithPath Name of the source folder along with path from where the content has to be copied.
	 * @param destinationFolderNameWithPath Name of the destination folder along with path from where the content will be pasted.
	 */
	public void copyResultsFolder(String sourceFolderNameWithPath, String destinationFolderNameWithPath)
	{
		//Source directory which you want to copy to new location
		File sourceFolder = new File(sourceFolderNameWithPath);

		//Target directory where files should be copied
		File destinationFolder = new File(destinationFolderNameWithPath);

		//Check if sourceFolder is a directory or file
		//If sourceFolder is file; then copy the file directly to new location
		if (sourceFolder.isDirectory())
		{
			//Verify if destinationFolder is already present; If not then create it
			if (destinationFolder.exists())
			{
				try {
					FileUtils.deleteDirectory(destinationFolder);
				} catch (IOException e) {
					e.printStackTrace();
				}
				destinationFolder.mkdir();
				logger.debug("[FW_UtilFunctions : getAbsoluteTestDataFilePath] Directory created: " + destinationFolder);
			}
			else{
				destinationFolder.mkdir();
				logger.debug("[FW_UtilFunctions : getAbsoluteTestDataFilePath] Directory created: " + destinationFolder);
			}

			try {
				FileUtils.copyDirectory(sourceFolder, destinationFolder);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}



