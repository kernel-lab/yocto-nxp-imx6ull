DESCRIPTION = "The Open Container Initiative develops specifications for standards on Operating System process and application containers"
HOMEPAGE = "https://github.com/opencontainers/runtime-spec"
SECTION = "devel/go"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${PKG_NAME}/LICENSE;md5=b355a61a394a504dacde901c958f662c"

SRCNAME = "runtime-spec"

PKG_NAME = "github.com/opencontainers/${SRCNAME}"
SRC_URI = "git://${PKG_NAME};destsuffix=git/src/${PKG_NAME};branch=main;protocol=https"

SRCREV = "720792f25ae6e9ee6b1332db698f37659e69ce8d"
PV = "v1.1.0-rc2+git"

S = "${WORKDIR}/git"

# NO-OP the do compile rule because this recipe is source only.
do_compile() {
}

do_install() {
	install -d ${D}${prefix}/local/go/src/${PKG_NAME}
	for j in $(cd ${S} && find src/${PKG_NAME} -name "*.go" -not -path "*/.tool/*"); do
	    if [ ! -d ${D}${prefix}/local/go/$(dirname $j) ]; then
	        mkdir -p ${D}${prefix}/local/go/$(dirname $j)
	    fi
	    cp $j ${D}${prefix}/local/go/$j
	done
	cp -r ${S}/src/${PKG_NAME}/LICENSE ${D}${prefix}/local/go/src/${PKG_NAME}/
}

SYSROOT_PREPROCESS_FUNCS += "runtime_spec_file_sysroot_preprocess"

runtime_spec_file_sysroot_preprocess () {
    install -d ${SYSROOT_DESTDIR}${prefix}/local/go/src/${PKG_NAME}
    cp -r ${D}${prefix}/local/go/src/${PKG_NAME} ${SYSROOT_DESTDIR}${prefix}/local/go/src/$(dirname ${PKG_NAME})
}

FILES:${PN} += "${prefix}/local/go/src/${PKG_NAME}/*"

CLEANBROKEN = "1"
