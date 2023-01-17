X-LINUX_GNSS1_V1.1.0 Linux Package
![latest tag](https://img.shields.io/github/v/tag/STMicroelectronics/meta-x-linux-gnss1.svg?color=brightgreen)
==============================================================================================================
The X-LINUX-GNSS1 is a Linux Package running on STM32MPU .This software provides applications for reading the NMEA GNSS data from X-NUCLEO-GNSS1A1(Teseo-LIV3F) or X-NUCLEO-GNSS2A1(Teseo-VIC3DA) plugged to the Arduino Connector of STM32MP157F-DK2 Discovery Board .X-LINUX-GNSS1 includes user space applications and Yocto Recipe (device tree) for the X-NUCLEO-GNSS1A1/X-NUCLEO-GNSS2A1 board ,a library for NMEA protocol support and POSIX thread for task scheduling to ensure better asynchronous message parsing.


X-LINUX-GNSS1 software features:

- Standalone applications to read the NMEA data over UART and I²C
- Complete software to build applications on Linux using Teseo-LIV3F GNSS module and Teseo-VIC3DA module
- Middleware for the NMEA protocol
- POSIX thread task scheduling to ensure better asynchronous message parsing
- Easy portability across different Linux platforms
- Application example to retrieve and parse GNSS data and send them to DSHASSETRACKING for live tracking
- Python example to read the NMEA data over UART


![image](https://user-images.githubusercontent.com/8255773/199161263-892e6251-8ffb-4209-b424-18e6c9cb7ea7.png)



Source Code Directories :
==============================================================================================================


1.Recipe
This folder contains the  Recipe for adding meta-gnss1 layer to the Yocto Build System


System Requirements :
==============================================================================================================
A Linux® PC running under Ubuntu® 18.04 or 20.04 is to be used. The developer can follow the below
link.
https://wiki.st.com/stm32mpu/wiki/PC_prerequisites

Build :
==============================================================================================================
#This is required to build the recipes and create STM32MP1 images which has GNSS application and device tree settings embedded (see
https://wiki.st.com/stm32mpu/wiki/STM32MP1_Distribution_Package). 

#Go to the host PC directory where to build the Distribution Package
PC $> cd <working directory path>/Distribution-Package

#Initialize repo in the current directory
PC $> repo init -u https://github.com/STMicroelectronics/oe-manifest.git -b refs/tags/openstlinux-5.15-yocto-kirkstone-mp1-v22.06.15
PC $> repo sync

#Initializing the OpenEmbedded build environment
PC $> DISTRO=openstlinux-weston MACHINE=stm32mp1 source layers/meta-st/scripts/envsetup.sh

#Doing the bitbake
PC $> bitbake st-image-weston

#Download the X-LINUX-GNSS1[2] application package and copy it to /openstlinux-5.15-yocto-kirkstone-mp1-v22.06.15/build-openstlinuxweston-stm32mp1 directory in the Distribution Package.
PC $> cp –rf X-LINUX-GNSS1_v1.0.0 /STM32MP15-Ecosystem-v4.0.0/Distribution-Package/openstlinux-5.15-yocto-kirkstone-mp1-v22.06.15/build-openstlinuxweston-stm32mp1
PC $> cd STM32MP15-Ecosystem-v4.0.0/Distribution-Package/openstlinux-5.15-yocto-kirkstone-mp1-v22.06.15/build-openstlinuxweston-stm32mp1

#Create a layer(meta-gnss1)
PC $> bitbake-layers create-layer --priority 7 ../layers/meta-st/meta-gnss1

#Add a layer(meta-gnss1)
PC $> bitbake-layers add-layer ../layers/meta-st/meta-gnss1

# Update the configuration to add new components in your image.
PC $> echo 'IMAGE_INSTALL:append = "gnss1"' >> ../layers/meta-st/meta-st-openstlinux/conf/layer.conf

or

PC $>$vi ../layers/meta-st/meta-st-openstlinux/conf/layer.conf 
IMAGE_INSTALL:append = "gnss1"


#Delete completely meta-gnss1 that is created by the tool and copy the meta-gnss1 downloaded from github /(X-LINUX-GNSS1).
PC $> rm -rf ../layers/meta-st/meta-gnss1/


#And copy the layer provided from the X-LINUX-GNSS1.
PC $> cp  -rf X-LINUX-GNSS1_V1.0.0/Recipe/meta-gnss1/   ../layers/meta-st/

#Add the Sources path(Location where CMakeLists.txt is present) inside gnss1_0.1.bbappend
# “/path-to/openstlinux-5.15-yocto-kirkstone-mp1-v22.06.15/Distribution-Package/build-openstlinuxweston-stm32mp1/X-LINUX-GNSS1_V1.0.0/Application/Source/gnss_x_linux/Sources”
which is inside layers/meta-st/meta-gnss1/recipes-gnss1/gnss1/gnss1_0.1.bbappend

PC $>vi ../layers/meta-st/meta-gnss1/recipes-gnss1/gnss1/gnss1_0.1.bbappend

#Update “dunfell” with “kirkstone” inside ‘layers/meta-st/meta-gnss1/conf/layer.conf’.


PC $>  bitbake st-image-weston

#New Images will be formed in the tmp-glibc/deploy/images/stm32mp1/  directory
PC $> cd tmp-glibc/deploy/images/stm32mp1/ 

For more details see section 5.2 on https://wiki.st.com/stm32mpu/wiki/X-NUCLEO-GNSS1A1_Expansion_Board

Deploy :
==============================================================================================================
#Follow instructions on ST wiki page: Flashing the built image to flash the new built images onto the discovery kit
FlashLayout_sdcard_stm32mp157f-dk2-trusted.tsv and FlashLayout_sdcard_stm32mp157f-dk2-trusted will be created besides other images
Follow below link (https://wiki.st.com/stm32mpu/wiki/STM32MP15_Discovery_kits_-_Starter_Package#Image_flashing)  to flash the binary.

Run:
==============================================================================================================

Run the application using command : ./<application name> ( e.g gnss_uart)

  
Related Information and Documentation:
==============================================================================================================

- [X-LINUX-GNSS1](https://www.st.com/en/embedded-software/x-linux-gnss1.html)
- [X-NUCLEO-GNSS1A1](https://www.st.com/en/ecosystems/x-nucleo-gnss1a1.html)
- [X-NUCLEO-GNSS2A1](https://www.st.com/en/ecosystems/x-nucleo-gnss2a1.html)
- [STM32MP157F-DK2](https://www.st.com/en/evaluation-tools/stm32mp157f-dk2.html)
- [Getting Started with X-LINUX-GNSS1](https://www.st.com/content/ccc/resource/technical/document/user_manual/group0/00/bd/07/b2/84/29/46/4f/DM00460180/files/DM00460180.pdf/jcr:content/translations/en.DM00460180.pdf)
- [STM32 Nucleo boards](http://www.st.com/stm32nucleo)
- [STM32MPU Wiki](https://wiki.st.com/stm32mpu/wiki/Main_Page)
  
