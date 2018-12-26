package com.eastern.parser

import java.io.File


interface IParser {

    /**
     * 解析
     */
    fun parse(pkgName: String, file: File): String

}