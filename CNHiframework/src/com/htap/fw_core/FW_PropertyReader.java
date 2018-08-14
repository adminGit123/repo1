package com.htap.fw_core;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * The base class to read all the property files used in hybrid test automation platform. The object created from this class
 * initializes the <code>Properties</code> and <code>Map</code> objects for
 * <b>framework.properties,  resources_XX_XX.properties and app.properties</b>.
 * <p>
 * <b>Usage:</b><br>
 * <code>FW_PropertyReader fw_PropertyReader = new FW_PropertyReader();</code>
 * <p>
 * @author      Rohit Kothari
 */
public class FW_PropertyReader extends FW_PropertyFileReader  {

	private Map<String, String> mapObj_appPropertiees = null;

	private Map<String, String> mapObj_frameworkXLevelPropeties = null;

    private Map<String, String> mapObj_resourceProperties = null;

    private static Logger logger = LogManager.getLogger(FW_PropertyFileReader.class.getName());

    /** Variable for FW_PropertyReader class */
    private static FW_PropertyReader m_objectFW_PropertyReader = null;

	public FW_PropertyReader(){
		logger.debug("[ FW_PropertyReader ] in FW_PropertyReader() ");
		try {
			mapObj_frameworkXLevelPropeties = loadXProperties(System.getProperty("user.dir")+FW_Const.CONFIG_RELATIVE_FILE_PATH + FW_Const.FW_PROPERTIES_FILE_NAME);

			mapObj_appPropertiees = getPropertiesMap(System.getProperty("user.dir")+FW_Const.BACK_SLASH+FW_Const.SOURCE_FOLDER+ FW_Const.BACK_SLASH + mapObj_frameworkXLevelPropeties.get(FW_Const.PROPERTIES_RELATIVE_FILE_PATH)	+ FW_Const.BACK_SLASH+ mapObj_frameworkXLevelPropeties.get(FW_Const.FW_APP_PROPERTIES_FILE_NAME));

			mapObj_resourceProperties = getResourceMap(mapObj_appPropertiees.get(FW_Const.APP_PROP_APP_LOCALE), mapObj_frameworkXLevelPropeties.get(FW_Const.PROPERTIES_FILE_RELATIVE_PATH) + FW_Const.BACK_SLASH + FW_Const.APP_RESOURCE_FILE);
		} catch (Exception e) {
		   logger.error("[ FW_PropertyReader ] FileNotFoundException: " + e.getMessage());;
		}

	}

	/**
     * Method to return instance of FW_PropertyReader
     * @return XPropertyReader instance
     *
     */
    public synchronized static FW_PropertyReader getInstance() {
        if (m_objectFW_PropertyReader == null) {
        	m_objectFW_PropertyReader = new FW_PropertyReader();
        }
        return m_objectFW_PropertyReader;
    }

	/**
	 * Returns the value for property key <code>app_strKey</code> from <code>app.properties</code> file.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_PropertyReader fw_PropertyReader = new FW_PropertyReader();<br>
	 * String application_url = fw_PropertyReader.getAppPropertyKeyValue("APP_URL");
	 * </code>
	 * <p>
	 * @param app_strKey Parameter key name for which the value is required
	 * <p>
	 * @return String value for property key <code>app_strKey</code>
	 */
    public String getAppPropertyValue(String app_strKey) {
    	logger.debug("[FW_PropertyReader : getMapObjAppPropertyKeyValue ] In getMapObjAppPropertyKeyValue Method"+ "Key For Application Property  "+app_strKey);
		return mapObj_appPropertiees.get(app_strKey);
      }



	/**
	 * Returns the value for property key <code>frm_strKey</code> from <code>framework.properties</code> file.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_PropertyReader fw_PropertyReader = new FW_PropertyReader();<br>
	 * String browser = fw_PropertyReader.getFrameworkPropertiesKeyValue("BROWSER");
	 * </code>
	 * <p>
	 * @param frm_strKey Parameter key name for which the value is required
	 * <p>
	 * @return String value for property key <code>frm_strKey</code>
	 */
    public String getFrameworkPropertiesValue(String frm_strKey) {
    	logger.debug("[FW_PropertyReader : getMapObjFrameworkPropertiesKeyValue] in getMapObjFrameworkPropertiesKeyValue Method"+ "Key For Framework Property  "+frm_strKey);
    	return mapObj_frameworkXLevelPropeties.get(frm_strKey);
    }



	/**
	 * Returns the value for property key <code>resource_strKey</code> from <code>resource.properties</code> file.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_PropertyReader fw_PropertyReader = new FW_PropertyReader();<br>
	 * String browser = fw_PropertyReader.getFrameworkPropertiesKeyValue("BROWSER");
	 * </code>
	 * <p>
	 * @param resource_strKey Parameter key name for which the value is required
	 * <p>
	 * @return String value for property key <code>resource_strKey</code>
	 */
    public String getResourcePropertiesValue(String resource_strKey) {
    	logger.debug("[FW_PropertyReader : getMapObjResourcePropertiesKeyValue] In getMapObjResourcePropertiesKeyValue Method"+ "Key For Resource Property  "+resource_strKey);
    	return mapObj_resourceProperties.get(resource_strKey);
    }



}
