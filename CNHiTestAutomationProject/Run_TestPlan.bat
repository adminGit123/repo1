set WORKINGDIR=C:\Workspace_PostReleaseTest\CNHiTestAutomationProject
set CLASSPATH=%WORKINGDIR%\bin;%WORKINGDIR%\libs\libs-common\*;%WORKINGDIR%\libs\libs-core\*;%WORKINGDIR%\libs\libs-datareader\*;%WORKINGDIR%\libs\libs-extentReports\*;%WORKINGDIR%\libs\libs-restAssured\*;

cd %WORKINGDIR%
java org.testng.TestNG testNGXMLAPI.xml
cd %WORKINGDIR%
pause