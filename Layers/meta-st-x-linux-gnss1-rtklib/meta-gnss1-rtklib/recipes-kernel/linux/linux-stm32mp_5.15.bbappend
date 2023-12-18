SUMMARY = "Recipe to add FTDI support for RTKLIB"

inherit kernel
#require recipes-kernel/linux/linux-yocto.inc


LINUX_VERSION = "5.15"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"


KERNEL_CONFIG_FRAGMENTS += " ${WORKDIR}/fragments/${LINUX_VERSION}/fragment-07-X-STM32MP_FTDI_full.config "

SRC_URI += " file://${LINUX_VERSION}/fragment-07-X-STM32MP_FTDI_full.config;subdir=fragments "


