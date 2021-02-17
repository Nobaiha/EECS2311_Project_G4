# EECS2311_Project_G4
## Introduction
  The TAB-2-MusicXML™ interface system is a java based application that can be downloaded on to a personal computer that will convert tablature made for percussion and guitar in “.txt” format to musicXML code which will output as a downloadable file in musicXML format that can be used as sheet music or tab sheet music. The final output, at minimum, will contain work details (such as titles and musicians) and attributes (such as time signature and sufficient bars).

## Requirements
- JavaSE-1.8 
- Gradle

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

Once the project is imported, from the Gradle tasks menu please hit **build**, and then hit **run** for Gradle to automatically download the dependencies and run the main class. For further instruction, please refer to the instructor's [video](https://echo360.ca/media/3b4933a0-7218-4d0b-ad2a-5f166f008679/public). When selecting a tab to process, please use one of the two provided examples as they have been tested to work. Please refer to the user manual included in the project for more information.

## Dependencies
- [Xembly](https://github.com/yegor256/xembly)

## Known Bugs
- Not all tabs are supported, only certain guitar tabs are.
- Selecting a wrong input file type or invalid tab will crash the program.

If it is showing some compiler errors please refer to the next section. 

## How to deal with compiler errors
**1. Errors dealing with build path:** 
  When faced with an error regarding build path a "Quick fixable error" indicated with a light-bulb with an 'x' will appear, with the message "Multiple markers at the line" over the package explorer. To deal with this issue: 
- Navigate towards the package explorer.
- Right click on the appropriate file with the build path error described above.
- Click on the option “Configure Build Path”.
- Click on the option “Libraries”.
- Select the appropriate java system.
- Click on remove.
- Highlight “classpath”.
- Click “add library” →  JRE system library → “Next” → Execution environment → From drop down menu select javaSE-1.8
- Select “Finish”.
- Select “Apply & Close”.

**2. Errors dealing with import cannot be resolved message:**
  When faced with an error regarding the “import org.xembly.*;” not working properly a "Quick fixable error" indicated with a light-bulb with an 'x' will appear, which could mean that the build.gradle may not have been imported properly. 
 - The dependencies may need to be update to include Xembly. That can be found here: https://github.com/yegor256/xembly.

## Authors 
- Suha Siddiqui
- Nobaiha Zaman Rayta
- Zhilong Lin
- Martin Brejniak
- Patchanon Suepai

