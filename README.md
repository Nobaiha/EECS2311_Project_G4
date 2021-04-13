# TAB-2-MusicXML™
## Introduction
  The TAB-2-MusicXML™ interface system is a java based application that can be downloaded on to a personal computer that will convert tablature made for guitar, bass and percussion in “.txt” format to musicXML code. This code can the be downloaded in musicXML file format. The final output, at minimum, will contain work details (such as titles and musicians) and attributes (such as time signature and sufficient bars).

## Documentation
  All documents you might need can be found in the [Documentation](https://github.com/Nobaiha/EECS2311_Project_G4/tree/master/Documentation) folder on this Github page. Such Documents include:
  - The System Requirements Specification (SRS)
  - User Manual
  - Testing Document

## Requirements
- JavaSE-15 
- Gradle 6.3

Please ensure you have the latest version of Gradle and Java 15 and Eclipse is using these versions or else the project will not build.
- [JavaSE-15](https://www.oracle.com/kr/java/technologies/javase-jdk15-downloads.html)
- Gradle is updated within Eclipse. Please check under Window -> Preferences -> Gradle.

## Installing & Running 
You can install the project on to Eclipse in *two* ways. 

**1.** 
- Clone the project.
- Import it into eclipse. 

**2.** 
- Download the zipped file.
- Open the zipped file. 
- Open eclipse. 
- Import the project into eclipse.

Once the project is imported, from the Gradle tasks menu please hit **build**, and then hit **run** for Gradle to automatically download the dependencies and run the main class. For further instruction, please refer to the instructor's [video](https://echo360.ca/media/3b4933a0-7218-4d0b-ad2a-5f166f008679/public). 

![Gradle instructions](https://i.imgur.com/z9fxmBy.png)

When selecting a tab to process, please use one of the two provided examples as they have been tested to work. Please refer to the user manual included in the project for more information.

## Dependencies
- [Xembly](https://github.com/yegor256/xembly)

## Known Bugs
- Drum tablature conversion is not fully functional. If you wish to use it, these are some things you must keep in mind:
  -  The duration of each note is not accurate.
  -  Must only have one note per column (As in only one note at a time).
- Selecting a wrong input file type or invalid tab will crash the program.

If it is showing some compiler errors please refer to the next section. 

## How to deal with compiler errors
**1. Errors dealing with build path:** 
  When faced with an error regarding build path a "Quick fixable error" indicated with a light-bulb with an 'x' will appear, with the message "Multiple markers at the line" over the package explorer. This means your Java version or Gradle version is not compatible with our program. Please update to the minimum required version and point Eclipse to use these versions. To deal with this issue: 
  
- Ensure you have updated to Java 15 and Gradle 6.3.
- Once Gradle has been updated, please hit refresh Gradle project.
- Navigate towards the package explorer.
- Right click on the appropriate file with the build path error described above.
- Click on the option “Configure Build Path”.
- Click on the option “Libraries”.
- Select the appropriate java system.
- Click on remove.
- Highlight “classpath”.
- Click “add library” →  JRE system library → “Next” → Execution environment → From drop down menu select javaSE-15
- Select “Finish”.
- Select “Apply & Close”.

**2. Errors dealing with import cannot be resolved message:**
  When faced with an error regarding the “import org.xembly.*;” not working properly a "Quick fixable error" indicated with a light-bulb with an 'x' will appear, which could mean that the build.gradle may not have been imported properly. 
 - The dependencies may need to be updated to include Xembly. That can be found here: https://github.com/yegor256/xembly.

**3. If all else fails:**
  Reinstall and update the current Java workspace and IDE version you are using.

## Authors 
- Suha Siddiqui
- Nobaiha Zaman Rayta
- Zhilong Lin
- Martin Brejniak
- Patchanon Suepai
