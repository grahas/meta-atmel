SUMMARY = "Microchip WILC devices firmware files for use with Linux kernel"
SECTION = "kernel"
LICENSE = "MICROCHIP_FW"

LIC_FILES_CHKSUM = "file://LICENCE.wilc_fw;beginline=6;md5=e1a0446d046c966a27276cf8e728f687"

SRCREV = "wilc_linux_15_2"
SRC_URI = "git://github.com/linux4wilc/firmware.git;protocol=https"
S = "${WORKDIR}/git"

inherit allarch

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/mchp/
	cp -r ${S}/* ${D}${nonarch_base_libdir}/firmware/mchp/

	# remove unneeded file
	rm -f ${D}${nonarch_base_libdir}/firmware/mchp/README.md
	rm -rf ${D}${nonarch_base_libdir}/firmware/mchp/LICENCE.wilc_fw
	chmod -x ${D}${nonarch_base_libdir}/firmware/mchp/*
}


FILES_${PN} += " \
	${nonarch_base_libdir}/firmware/mchp/wilc*.bin \
	"

# TODO: use ALTERNATIVE like in "linux-firmware" package
