### Build Instructions
```bash
jpackage --name DownloadVideo \
--input . --main-jar out/artifacts/downloadapp_jar/downloadapp.jar \
--jlink-options --bind-services
```
Requires JDK 14 or higher. If you build on an Apple Silicon Mac, it will not work on Intel Macs.
