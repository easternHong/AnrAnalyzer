package com.eastern.parser.model


class ContentDesc(val pkgName: String) {
    /**
     *
     */
    val threadList = ArrayList<ThreadDesc>()

    var systemDesc = ArrayList<String>()

    var startTime = ""

    var pid = ""
}