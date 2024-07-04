SUMMARY = "gnss1-ekf application uses GPS+IMU data ane EKF filter for better accuracy"

LICENSE = "CLOSED"

SRC_URI += " gitsm://github.com/balamuruganky/EKF_IMU_GPS.git;protocol=https;branch=master \
	   file://ekf_support.patch \
	   file://run_ekf.sh \
	   file://stop_ekf.sh \
	   file://arg_plot_coords.py \
"
SRCREV = "aa295addbeb4387fb274acf607965182e221ac5d"

inherit cmake pkgconfig

do_patch(){
	cd ${WORKDIR}/git
	#git submodule update --init --recursive
	#cd ${WORKDIR}/
	git apply ../ekf_support.patch
}

do_configure(){
    echo ${WORKDIR}
    mkdir -p ${WORKDIR}/git/build
    cd ${WORKDIR}/git/build
    export CMAKE_MODULE_PATH=${WORKDIR}/recipe-sysroot/usr/share/eigen3/cmake/
    echo "cmakemodule:" ${CMAKE_MODULE_PATH}
    cmake -DEigen3_DIR=${WORKDIR}/recipe-sysroot/usr/share/eigen3/cmake/ -DWORKDIR=${WORKDIR} ..
}


do_compile(){
	echo "${CXX}"
	echo "${CPP}"
	cd ${WORKDIR}/git/build
	
	make CXXFLAGS+= " -std=c++17 " ekf_linux
	make CXXFLAGS+= " -std=c++17 " ekf_gnss1_test
}

do_install(){
	install -d ${D}${bindir}
	install -d ${D}${libdir}
	install -d ${D}/usr/python_utils
	install -m 0755 ${WORKDIR}/git/bin/ekf_gnss1_test ${D}${bindir}/
	install -m 0755 ${WORKDIR}/git/lib/libekf_linux.so ${D}${libdir}/
	install -m 0755 ${WORKDIR}/run_ekf.sh ${D}/${bindir}
	install -m 0755 ${WORKDIR}/stop_ekf.sh ${D}/${bindir}
	install -m 0755 ${WORKDIR}/arg_plot_coords.py ${D}/usr/python_utils/
	#echo "ls {D}"
	#ls -lR ${D}
}

EXTRA_OECMAKE += " -DCMAKE_SKIP_RPATH=TRUE "

FILES:${PN} += " /usr/bin/* \
		/usr/python_utils/* \
		"
FILES:${PN}-dev += " ${libdir}/* "

INSANE_SKIP:${PN} += " ldflags "
INSANE_SKIP:${PN} += " build-deps "
INSANE_SKIP:${PN}-dev += " dev-elf "

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

SOLIBS = ".so"

FILES_SOLIBSDEV = ""

DEPENDS = " libeigen libeigen-native  python3"
RDEPENDS:${PN} += " python3-core gnss1-ekf-dev python3-modules bash"



