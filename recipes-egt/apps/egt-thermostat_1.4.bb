DESCRIPTION = "Microchip EGT Theroststat Demo Application"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;endline=202;md5=3b83ef96387f14655fc854ddc3c6bd57"

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
"
DEPENDS = " libegt sqlite3-native"

SRC_URI = "git://github.com/linux4sam/egt-thermostat.git;protocol=https;branch=master"

SRCREV = "8c87212fe6526b6508d18a98fb3eb2c5e77a08b8"

S = "${WORKDIR}/git"

# out-of-tree building doesn't appear to work for this package.
B = "${S}"

inherit pkgconfig autotools gettext siteinfo

do_configure[network] = "1"

do_configure:prepend() {
	( cd ${S};
	${S}/autogen.sh; cd -)
}

do_install:append() {
	( cd ${S} && sqlite3 thermostat.db < thermostat.sql; cd - )
	install -m 0755 -D ${S}/thermostat.db ${D}${datadir}/egt/thermostat/thermostat.db
}

FILES:${PN} += " \
    ${datadir}/egt/* \
"

python __anonymous () {
    endianness = d.getVar('SITEINFO_ENDIANNESS')
    if endianness == 'be':
        raise bb.parse.SkipRecipe('Requires little-endian target.')
}
