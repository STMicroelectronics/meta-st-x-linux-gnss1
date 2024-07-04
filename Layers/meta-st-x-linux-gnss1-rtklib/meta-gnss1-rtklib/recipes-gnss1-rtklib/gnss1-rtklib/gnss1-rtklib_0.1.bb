#SUMMARY = "bitbake-layers recipe"
#DESCRIPTION = "Recipe created by bitbake-layers"
#LICENSE = "MIT"
SUMMARY = "gnss1-rtklib: ported standard RTKLIB application with QT "

#python do_display_banner() {
#	bb.plain("***TODO:write display banner****");
#}


#iddtask display_banner before do_build
#SRC_URI[sha256sum] = "33e964cf9a0004f27a74fda21f9a71e6831bca8a738cc71c798ed4a67f9a0e66"

LICENSE = "CLOSED"
#LIC_FILES_CHKSUM = ""


# No information for SRC_URI yet (only an external source tree was specified)

SRC_URI += " git://github.com/Francklin2/RTKLIB_Touchscreen_GUI.git;protocol=https;branch=master \
		file://rtklib_stm32mp1.patch \
		file://shell_scripts_icons/080-rtklib.yaml \
		file://shell_scripts_icons/rtklib-run \
		file://shell_scripts_icons/splash.png \
" 
SRCREV = "25ba6b93a704c7beba2f5330a6678e6c6884d1bd"


#file://pictures/*

DEPENDS = "python3 cairo glibc qtlocation qt3d qtbase "

TARGET_CC_ARCH += "${LDFLAGS}"

#inherit cmake
#specify any options you want to pass to cmake using EXTRA_OECMAKE:
#EXTRA_OECMAKE = ""

inherit pkgconfig qmake5 autotools
#inherit gettext autotools-brokensep

#EXTRA_OEMAKE = "CFLAGS='${CFLAGS}' CC=${CC} CXX=${CXX} AR=${AR} -pipe  -O2 -pipe -g -feliminate-unused-debug-types -ansi -pedantic -Wall -Wextra -D_REENTRANT -fPIC "

do_patch(){
	cd ${WORKDIR}/git
	git apply ../rtklib_stm32mp1.patch
}
do_configure(){
    cd ${WORKDIR}/git/RTKBASE/
    ${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}/qmake
}

do_compile() {
   export CFLAGS="${CFLAGS} --sysroot=${STAGING_DIR_HOST} -pipe  -O2 -pipe -g -feliminate-unused-debug-types -ansi -pedantic -Wall -Wextra -D_REENTRANT -fPIC "
   export STRIP="${STRIP}"
    cd ${WORKDIR}/git/RTKBASE/lib/rtklib
    echo "cd ${WORKDIR}/git/RTKBASE/lib/rtklib"
#    cd ${WORKDIR}/git/RTKBASE/lib/rtklib
    ./make_library.sh
#    oe_runmake
     make
#    ${AR} rsc librtk.a src/*.o src/rcv/*.o
    cd ${WORKDIR}/git/RTKBASE/
    #oe_runmake
    make
}


do_install(){
#	mkdir -p ${D}/lib/firmware/
#	mkdir -p ${D}/usr/
#	mkdir -p ${D}/usr/local/
#	mkdir -p ${D}/usr/local/demo
#	mkdir -p ${D}/usr/local/demo/application/
	install -d ${D}/bin
	cp -r ${WORKDIR}/git/ ${D}/RTKLIB_Touchscreen_GUI/
	chmod -R 777 ${D}/RTKLIB_Touchscreen_GUI/
}


do_install:append() {
	install -d ${D}/usr/local/ 
	install -d ${D}/usr/local/demo 
	install -d ${D}/usr/local/demo/application/ 
	install -m 755 ${WORKDIR}/shell_scripts_icons/rtklib-run  ${D}/bin
	install -m 755 ${WORKDIR}/shell_scripts_icons/080-rtklib.yaml  ${D}/usr/local/demo/application/
	install -m 755 ${WORKDIR}/shell_scripts_icons/splash.png  ${D}/usr/local/demo/application/
}

#FILES:${PN} += "/usr/local/demo/pictures"
FILES:${PN} += " /bin/rtklib-run \
		/usr/local/demo/application/* \
		/RTKLIB_Touchscreen_GUI/* \
		${libdir} \
		"

INSANE_SKIP:${PN} += "installed-vs-shipped"
INSANE_SKIP:${PN} += "staticdev"
INSANE_SKIP:${PN} += "file-rdeps"
INSANE_SKIP:${PN} += "arch"
INSANE_SKIP:${PN} += "ldflags"
 
#RDEPENDS:${PN} += "python3-core glibc qtlocation qt3d qtbase"
RDEPENDS:${PN} += "python3-core glibc qtbase qtlocation qt3d"

FILES_SOLIBDEV = ""
