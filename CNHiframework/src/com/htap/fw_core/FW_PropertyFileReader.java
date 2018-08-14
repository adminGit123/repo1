package com.htap.fw_core;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * The base class to read the property files or Xproperty files that allows declaring variables as property file keys.
 * <p>
 * <b>Usage:</b><br>
 * <code>FW_PropertyFileReader fw_PropertyFileReader = new FW_PropertyFileReader();</code>
 * <p>
 * @author  Rohit Kothari
 */

public class FW_PropertyFileReader {

	private static Logger logger = LogManager.getLogger(FW_PropertyFileReader.class.getName());



    /**
     * Loads and returns the <code>Map</code> object with values from the property file with name <code>p_StrFileName</code>.
     * <p>
     * <b>Usage:</b><br>
	 * <pre>
	 * {@code
	 * FW_PropertyFileReader fw_PropertyFileReader = new FW_PropertyFileReader();
	 * Properties l_objReporterProperties = fw_PropertyFileReader.getPropertiesMap("D:/myWorkspace/Project/com/test/auto/properties/app.properties");
	 * }
	 * </pre>
     * @param PropFilePath Property file name to be loaded
     * <p>
     * @return map_Propeties <code>Map</code> object containing values from the property file with name <code>PropFilePath</code>
     */
    public Map<String, String> getPropertiesMap(String PropFilePath) {
    	logger.debug("[ FW_PropertyFileReader : getPropertiesMap ] In getPropertiesMap()" + "Property File Path " + PropFilePath);
    	FileInputStream fis;
    	Map<String,String> map_Propeties=null;
         try {
             fis = new FileInputStream(PropFilePath);
             ResourceBundle resources = new PropertyResourceBundle(fis);
             map_Propeties = new HashMap<String,String>();

             //convert ResourceBundle to Map
             Enumeration<String> keys = resources.getKeys();
             while (keys.hasMoreElements()) {
                 String key = keys.nextElement();
                 map_Propeties.put(key, resources.getString(key));
             }

         } catch (FileNotFoundException e) {
        	 logger.error("[ FW_PropertyFileReader : getPropertiesMap ] FileNotFoundException: " + e.getMessage());
         } catch (IOException e) {
        	 logger.error("[ FW_PropertyFileReader : getPropertiesMap ] IOException: " + e.getMessage());
         }
         return map_Propeties;
    }


    /**
   	 * Loads and returns the <code>Map</code> object with values from the X level properties file
   	 * that allows declaring variables as property file keys.
   	 * <p>
   	 * <b>Usage:</b><br>
   	 * <pre>
   	 * {@code
   	 * FW_PropertyFileReader fw_PropertyFileReader = new FW_PropertyFileReader();
   	 * Map<String, String> mapObject_XLevelProperties = fw_PropertyFileReader.getXMap("D:/myWorkspace/Project/com/test/auto/properties/framework.properties");
   	 * }
   	 * </pre>
   	 * @param PropFileNameWithPath Name of the Xproperty file to be loaded.
   	 * <p>
        * @return mapObject_XLevelProperties <code>Map</code> object containing values from the property file with name <code>FileName</code>
        */
       public Map<String, String> loadXProperties(String PropFileNameWithPath) {

           Map<String, String> mapObject_XLevelProperties = null;
           try {
        	   mapObject_XLevelProperties = getXMap(PropFileNameWithPath);
           }  catch (FileNotFoundException p_expFileNotFoundException) {
           	logger.error("[FW_PropertyFileReader : loadXProperties] FileNotFoundException: " + p_expFileNotFoundException.getMessage());
           } catch (IOException p_expIOException) {
           	logger.error("[FW_PropertyFileReader : loadXProperties] IOException: " + p_expIOException.getMessage());
           }
           return mapObject_XLevelProperties;
       }

       /**
        * Returns the HashMap for the X level property file with name <code>PropFileName</code>.
        * <p>
        * <b>Usage:</b><br>
        * <pre>
   	 * {@code
   	 * FW_PropertyFileReader fw_PropertyFileReader = new FW_PropertyFileReader();
   	 * Map<String, String> mapObject_XLevelProperties = fw_PropertyFileReader.getXMap("D:/myWorkspace/Project/com/test/auto/properties/framework.properties");
   	 * }
        * </pre>
        * @param PropFileNameWithPath Property file name to be loaded
        * <p>
        * @return mapObject_XLevelProperties <code>Map</code> object containing values from the property file with name <code>PropFileName</code>
        * @throws FileNotFoundException File does not exists in the path specified
        * @throws IOException IO error while reading the file
        */
       public Map<String, String> getXMap(String PropFileNameWithPath)
           throws FileNotFoundException, IOException {
           FileInputStream PropertiesFile;
           Map<String, String> mapObject_XLevelProperties = new HashMap<String, String>();
           PropertiesFile = new FileInputStream(PropFileNameWithPath);

           Properties XLevelProperties_obj = null;

           //here we use FW_XProperties, which can handle and substitute values from properties file
           XLevelProperties_obj =  new FW_XProperties();
           XLevelProperties_obj.load(PropertiesFile);

           if (!XLevelProperties_obj.isEmpty()) {
               Enumeration<Object> EnumObject = XLevelProperties_obj.keys();
               while (EnumObject.hasMoreElements()) {
                   String l_strKey = (String)EnumObject.nextElement();
                   mapObject_XLevelProperties.put(l_strKey,
                       String.valueOf(XLevelProperties_obj.getProperty(l_strKey)));
               }
           }
           PropertiesFile.close();
           return mapObject_XLevelProperties;
       }

       /**
        * Returns the resource map in corresponding language <code>LocalLang</code> and as specified
        * in the locale file with name <code>PropFileNameWithPath</code>.
        * <p>
        * <b>Usage:</b><br>
        * <pre>
   	 * {@code
   	 * FW_PropertyFileReader fw_PropertyFileReader = new FW_PropertyFileReader();
   	 * Map<String, String> mapObject_ResourceProperties = fw_PropertyFileReader.getResourceMap("en_US", "D:/myWorkspace/Project/com/test/auto/properties/resource");
   	 * }
        * </pre>
        * @param LocalLang Locale or Language to be tested
        * @param PropFileNameWithPath Resource file name prefix to be loaded
        * <p>
        * @return mapObject_ResourceProperties <code>Map</code> object containing values from the resource file with name <code>PropFileNameWithPath</code>
        */
   	public Map<String, String> getResourceMap(String LocalLang, String PropFileNameWithPath) {

   			Map<String, String>  mapObject_ResourceProperties = new HashMap<String, String>();

   			if (LocalLang == null) {
   				LocalLang = FW_Const.DEFAULT_LOCALE;
   			}
   			PropFileNameWithPath = PropFileNameWithPath + FW_Const.UNDERSCORE + LocalLang;
   			Locale locale = new Locale(LocalLang);
   			ResourceBundle ResourceBundleObject = null;
   			try {
   				String String_Key = null;
   				String String_Value = null;

   				ResourceBundleObject =
   					PropertyResourceBundle.getBundle(PropFileNameWithPath, locale);
   					Enumeration<String> l_objKeySet = ResourceBundleObject.getKeys();

   				while (l_objKeySet.hasMoreElements()) {
   					String_Key = l_objKeySet.nextElement();
   					String_Value = ResourceBundleObject.getString(String_Key);
   					mapObject_ResourceProperties.put(String_Key, String_Value);
   				}

   			} catch (MissingResourceException p_objIOException) {
   	        	logger.error("[FW_PropertyFileReader : getResourceMap] MissingResourceException: " + p_objIOException.getMessage());
   			}

   			return mapObject_ResourceProperties;
   		}

   }

