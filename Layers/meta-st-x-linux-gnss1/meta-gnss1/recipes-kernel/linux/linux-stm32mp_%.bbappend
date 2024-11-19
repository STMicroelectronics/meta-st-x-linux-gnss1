inherit kernel
LINUX_VERSION = "6.1"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
#FILESEXTRAPATHS:prepend := "${THISDIR}:"

#KERNEL_CONFIG_FRAGMENTS:append:stm32mp1common = " ${WORKDIR}/fragments/${LINUX_VERSION/}fragment-X-GNSS1-STM32MP_full.config "

#KERNEL_CONFIG_FRAGMENTS:append:stm32mp25common = " ${WORKDIR}/fragments/${LINUX_VERSION}/fragment-X-GNSS1-STM32MP_full.config "


SRC_URI += " file://${LINUX_VERSION}/fragment-X-GNSS1-STM32MP_full.config"

SRC_URI  += " \
	file://0024-GNSS1-enable-sensors-dts.patch;md5=0daf7945f6da8fb4126f4e5fc2566bb0 \
	file://0028-GNSS1-enable-MP2-v6.1-STM32MP.patch;md5=d41d8cd98f00b204e9800998ecf8427e \
        file://0024-GNSS1-enable-sensors_mp157f-dts.patch;md5=5722405a3f1d7a85690d53e044169b07 \
	"

#SRC_URI:class-devupstream += "file://fragment-X-GNSS1-STM32MP_full.config;subdir=fragments"
do_configure:append() {
	cat ${B}/../${LINUX_VERSION}/fragment-X-GNSS1-STM32MP_full.config >> ${B}/.config
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

