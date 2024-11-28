# Copyright (C) 2024 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Add packages for i.MX Matter baseline components"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit packagegroup

RDEPENDS:${PN} += "${@bb.utils.contains_any('MACHINE', "imx6ullevk imx8ulpevk ", ' otbr tayga ', '', d)}"
RDEPENDS:${PN} += "${@bb.utils.contains('MACHINE', "imx8mmevk-matter", ' otbr tayga otbr-iwxxx-spi ', '', d)}"
RDEPENDS:${PN} += "${@bb.utils.contains('MACHINE', "imx93evk-iwxxx-matter", ' otbr-iwxxx-spi ', '', d)}"