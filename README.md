Description
---------------------------------------
This plugins helps zipping/packaging projects.

Notes
---------------------------------------
Please, if you find any bug, use our [Issue Tracker](https://github.com/willybarro/ProjectZipper/issues).

Usage
---------------------------------------
1. Open any project on Netbeans (if you have any project opened, go to step 2)
2. Start Project Zipper (Click on "File" > "Project Zipper")
3. Select a project
4. Select resources you wish to export
5. Select your export location
6. Click on "Export"

Download
---------------------------------------
- [Version 0.92 beta](https://github.com/downloads/willybarro/ProjectZipper/nbprojectzipper-0.92.nbm)
- [Version 0.9 beta](https://github.com/downloads/willybarro/ProjectZipper/0.9.nbm)

@TODO (to release v1.0)
---------------------------------------
- JavaDoc every class and method
- JUnit tests
- Write a decent  Readme
- Write a decent .nbm/project description
- Publish to NB community (nbplugins official site)

Known Issues
---------------------------------------
- Empty packages being created on Windows
- Files on the treefilechooser not sorting in normal "Directories first, files after" way
- Messy Wizard View (Perfectionism :))
- Ant plugin detection not working

Changelog:
---------------------------------------
- __v0.92__ @ 2011-08-03
 - Fix: File Tree Chooser not showing contents when using the plugin on Windows
 - Added: Project Chooser dialog
 - Modified: Changed project architecture to MVC-based
 - Modified: Usage section (this document)

- __v0.9__ @ 2011-06-25
 - Beginning of the project
 - Fully functional module (Not on Windows)