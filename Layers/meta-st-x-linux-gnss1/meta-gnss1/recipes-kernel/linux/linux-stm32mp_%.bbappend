inherit kernel
LINUX_VERSION = "5.15"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

KERNEL_CONFIG_FRAGMENTS += "${WORKDIR}/fragments/${LINUX_VERSION}/fragment-07-X-STM32MP_full.config"

SRC_URI += " file://${LINUX_VERSION}/fragment-07-X-STM32MP_full.config;subdir=fragments"

SRC_URI  += " \
	file://0023-ARM-5.15.24-stm32mp1-r1-X-STM32MP1.patch \
	file://0024-GNSS1-enable-sensors-dts.patch \
	file://0025-TEMP-GNSS1-Use-old-Pressure-sensor-driver.patch \
	"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PACKAGE_ARCH = "${MACHINE_ARCH}"
