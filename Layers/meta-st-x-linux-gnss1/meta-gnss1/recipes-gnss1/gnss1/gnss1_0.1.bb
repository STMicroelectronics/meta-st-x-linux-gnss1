#SUMMARY = "bitbake-layers recipe"
#DESCRIPTION = "Recipe created by bitbake-layers"
#LICENSE = "MIT"

#python do_display_banner() {
#    bb.plain("***********************************************");
#    bb.plain("*                                             *");
#    bb.plain("*  Example recipe created by bitbake-layers   *");
#    bb.plain("*                                             *");
#    bb.plain("***********************************************");
#}

#iddtask display_banner before do_build
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = ""

DEPENDS = "curl"

inherit cmake

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = ""
SRC_URI = ""

OECMAKE_GENERATOR = "Unix Makefiles"
inherit cmake

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
# EXTRA_OECMAKE = ""
do_install(){
        install -d ${D}${bindir}
    #    install -d ${D}${libdir}
        install -m 755 ${B}/gnss_app ${D}${bindir}/
}

