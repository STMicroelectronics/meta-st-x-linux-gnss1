inherit kernel
LINUX_VERSION = "6.1"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

KERNEL_CONFIG_FRAGMENTS += "${WORKDIR}/fragments/${LINUX_VERSION}/fragment-08-X-STM32MP_full.config"

SRC_URI += " file://${LINUX_VERSION}/fragment-08-X-STM32MP_full.config;subdir=fragments"

SRC_URI  += " \
	file://0025-v6.1-stm32mp-r1-X-STM32MP.patch \
	file://0024-GNSS1-enable-sensors-dts.patch \
	"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PACKAGE_ARCH = "${MACHINE_ARCH}"
