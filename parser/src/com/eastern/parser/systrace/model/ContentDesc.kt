package com.eastern.parser.systrace.model


class ContentDesc(val pkgName: String) {
    /**
     *
     */
    val threadList = ArrayList<ThreadDesc>()

    var systemDesc = ArrayList<String>()

    var startTime = ""

    var pid = ""
}