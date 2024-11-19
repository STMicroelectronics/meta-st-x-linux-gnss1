META-ST-X-LINUX_GNSS1_V1.4.0 :

![latest tag](https://img.shields.io/github/v/tag/STMicroelectronics/meta-st-x-linux-gnss1.svg?color=brightgreen)
==============================================================================================================
The META-ST-X-LINUX-GNSS1 is a Yocto Recipe for GNSS and IMU application for OpenSTLinux for STM32MP157F-DK2 and STM32MP257F-EV1 Board.This recipe provides the Yocto Recipes for GNSS , RTK (Real-time kinematic) Library using X-STM32MP-GNSS1 board plugged to the 40 Pin Connector of the STM32MP157F-DK2 and STM32MP257F-EV1 Board .
Detailed User Guide "X-LINUX-GNSS1 package for developing GNSS Applications on Linux" can be foudn here  : https://www.st.com/resource/en/user_manual/um2909-getting-started-with-xlinuxgnss1-package-for-developing-gnss-applications-on-linux-os-stmicroelectronics.pdf"


# META-ST-X-LINUX-GNSS1 V1.4.0 Linux Package

## Introduction

**The META-ST-X-LINUX-GNSS1** is a Yocto Recipe for GNSS and IMU application for OpenSTLinux for STM32MP157F-DK2 and STM32MP257F-EV1 Board.This recipe provides the Yocto Recipes for GNSS , RTK (Real-time kinematic) Library using X-STM32MP-GNSS1 and X-STM32MP-GNSS2 board plugged to the 40 Pin Connector of the STM32MP157F-DK2 and STM32MP257F-EV1 Board .

![META-ST-X-LINUX-GNSS1 Package](/_htmresc/01_META-ST-X-LINUX-GNSS1-package.png "META-ST-X-LINUX-GNSS1 Package")

## Description

### META-ST-X-LINUX-GNSS1 software features:

Its main feature are :

meta-st-x-linux-gnss1: Yocto Recipe for Reading NMEA GNSS data and enabling IMU and Environmental Sensors
meta-st-x-linux-gnss1-rtklib : Yocto Recipe for Enabling Real Time Kinematics (RTK) data using RTCM Messages on Teseo-LIV3F module board
meta-st-x-linux-gnss1-ekf : Yocto Recipe for Enabling IMU +GNSS fusion using EKF.

### META-ST-X-LINUX-GNSS1 Architecture:

The software uses the OpenSTLinux UART and I2C Driver to interact with the Teseo-LIV3FL GNSS modules on X-STM32MP-GNSS1 board.
for meta-st-x-linux-gnss1  : The Yocto recipe creates the gnss_app application as a part of system image which can be flashed to the STM32MPU 
for meta-st-x-linux-gnss1-rtklib  : The Yocto recipe creates the RTK lib application along with the QT based application as a part of system image which can be flashed to the STM32MPU
for meta-st-x-linux-gnss1-ekf  : The Yocto recipe creates ekf commandline application as a part of system image which can be flashed to the STM32MPU

### X-LINUX-GNSS1 Package Structure:
+---meta-st-x-linux-gnss1
¦   LICENSES.html
¦   README.md
¦   Release_Notes.md
¦   CODE_OF_CONDUCT.md
¦   CONTRIBUTING.md
¦   LICENSE.md
¦
+---Layers
    +---meta-st-x-linux-gnss1
    ¦   ¦   README.md
    ¦   ¦
    ¦   +---meta-gnss1
    ¦       ¦   COPYING.MIT
    ¦       ¦
    ¦       +---conf
    ¦       ¦       layer.conf
    ¦       ¦
    ¦       +---recipes-gnss1
    ¦       ¦   +---gnss1
    ¦       ¦           gnss1_0.1.bb
    ¦       ¦           gnss1_0.1.bbappend
    ¦       ¦
    ¦       +---recipes-kernel
    ¦           +---linux
    ¦               ¦   linux-stm32mp_%.bbappend
    ¦               ¦
    ¦               +---linux-stm32mp
    ¦                   +---openstlinux-weston
    ¦                       ¦   0024-GNSS1-enable-sensors-dts.patch
    ¦                       ¦   0024-GNSS1-enable-sensors_mp157f-dts.patch
    ¦                       ¦   0028-GNSS1-enable-MP2-v6.1-STM32MP.patch
    ¦                       ¦   COPYING.MIT
    ¦                       ¦
    ¦                       +---6.1
    ¦                               fragment-X-GNSS1-STM32MP_full.config
    ¦
    +---meta-st-x-linux-gnss1-ekf
    ¦   ¦   README.md
    ¦   ¦
    ¦   +---meta-gnss1-ekf
    ¦       +---conf
    ¦       ¦       layer.conf
    ¦       ¦
    ¦       +---recipes-gnss1-ekf
    ¦           +---gnss1-ekf
    ¦               ¦   gnss1-ekf_0.1.bb
    ¦               ¦
    ¦               +---files
    ¦                       analyze_lonlat.py
    ¦                       arg_plot_coords.py
    ¦                       ekf_latest_mickel.patch
    ¦                       run_ekf_gnss1.sh
    ¦                       run_ekf_gnss2.sh
    ¦                       stop_ekf.sh
    ¦
    +---meta-st-x-linux-gnss1-rtklib
        ¦   README.md
        ¦
        +---meta-gnss1-rtklib
            +---conf
            ¦       layer.conf
            ¦
            +---recipes-gnss1-rtklib
                +---gnss1-rtklib
                    ¦   gnss1-rtklib_0.1.bb
                    ¦
                    +---files
                        ¦   rtklib_stm32mp1.patch
                        ¦
                        +---shell_scripts_icons
                                080-rtklib.yaml
                                rtklib-run
                                splash.png
## Hardware Setup:

The current package provides software support for the following boards
 - [X-STM32MP-GNSSx board mounted on STM32MP157F-DK2 or STM32MP257F-EV1](https://www.st.com/en/ecosystems/x-stm32mp-gnss1.html) based on Teseo-LIV3FL or Teseo-LIV4FL. 
 - The board is also compatible with X-NUCLEO-GNSSx and X-NUCLEO-LIV* boards plugged to the Arduino Connectors

## Software Setup:

The section describes the software setup that is required for building, flashing, deploying, and running the application.

### Recommended PC prerequisites

A Linux® PC running Ubuntu® 20.04 or 22.04 is recommended. Developers can follow the link below for details.
https://wiki.st.com/stm32mpu/wiki/PC_prerequisites

Follow the instructions on the ST wiki page [Image flashing](https://www.st.com/en/embedded-software/stm32mp1starter.html , https://www.st.com/en/embedded-software/stm32mp2starter.html) to prepare a bootable SD card with the starter package.  
Alternatively, a Windows / Mac computer can also be used; in that case, the following tools would be useful:
- Use [STM32CubeProgrammer](https://www.st.com/en/development-tools/stm32cubeprog.html) to flash the OpenSTLinux started package image onto the SD card
- Use [TeraTerm](https://github.com/TeraTermProject/osdn-download/releases/) or [PuTTY](https://putty.org/) to access the console interface via USB
- Use [winscp](https://winscp.net/eng/index.php) to copy the application to the MPU board

*The following conventions are used when referring to the code instructions.*
```
#Comments: Comment describing steps
PC>$ : Development or Host PC/machine command prompt. Text after $ is a command
Board>$ : STM32MPU command prompt. Text after $ is a command
```
**STMPU Software Prerequisites**

-NA


### Deploying the files to the MPU board

It is required to transfer the built binaries, Python scripts, and application resources to the STM32MP board from the development PC.

The resources can be transferred via any of the following methods:

1. **Using a network connection**

Refer to [How to Transfer a File Over a Network](https://wiki.st.com/stm32mpu/wiki/How_to_transfer_a_file_over_network)
 
To connect the MPU board to a network, you may connect it to a wired network via the Ethernet jack on the MPU board.  
 
**OR**  

To connect to a WLAN, refer to [How to Setup a WLAN Connection"](https://wiki.st.com/stm32mpu/wiki/How_to_setup_a_WLAN_connection)

2. **Using a serial protocol** (like zmodem from Teraterm or kermit)

For Linux hosts refer to [How to transfer a file over a serial console](https://wiki.st.com/stm32mpu/wiki/How_to_transfer_a_file_over_serial_console)  
For Windows hosts, refer to
[How to transfer files to Discovery kit using Tera Term](https://wiki.st.com/stm32mpu/wiki/How_to_transfer_files_to_Discovery_kit_using_Tera_Term_on_Windows_PC)

To evaluate the X-LINUX-GNSS1 package quickly, developers may copy the contents of the "application/Binaries" folder contained in the package to `/usr/local/demo/application` folder on the STM32MP board using any of the above methods.

```bash
# Go to the application folder and scp/push the binary to STM32MPU using zmodem or scp command
Then execute the below command in the STM32MPU
Board>$ chmod +x <binary_name>
# Run the binary
Board>$ ./<binary_name>
```

## License

Details about the terms under which various components are licensed are available [here](LICENSE.md)

## Release notes

Details about the content of this release are available in the release note [here](Release_Notes.md).

## Compatibility information

The software package is validated for the [OpenSTLinux](https://www.st.com/en/embedded-software/stm32-mpu-openstlinux-distribution.html) version 5.1.0 
For running the software on other ecosystem versions customization may be needed.
The software is tested on the [STM32MP157F-DK2 and STM32MP257F-EV1] board.


#### Related Information and Documentation:

- [X-STM32MP-GNSS1](https://www.st.com/en/evaluation-tools/x-stm32mp-gnss1.html)
- [STM32MP157F-DK2](https://www.st.com/en/evaluation-tools/stm32mp157f-dk2.html)
- [X-NUCLEO-GNSS1A1](https://www.st.com/en/ecosystems/x-nucleo-gnss1a1.html)
- [X-NUCLEO-GNSS2A1](https://www.st.com/en/ecosystems/x-nucleo-gnss2a1.html)
- [STM32MP257F-EV1](https://www.st.com/en/evaluation-tools/stm32mp257f-ev1.html)
