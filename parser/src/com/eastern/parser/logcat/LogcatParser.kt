package com.eastern.parser.logcat

import com.eastern.parser.IParser
import java.io.File

/**
 * logcat 分析器主要分析 pkgName和system_server的CPU使用率，是否有iowait
 */
object LogcatParser : IParser {

    //101% 28441/com.duowan.mobile: 18% user + 82% kernel / faults: 11581 minor 11 major
    override fun parse(pkgName: String, file: File): String {

        val builder = StringBuilder()
        builder.setLength(0)
        file.forEachLine {
            if (it.contains(pkgName)) {
                builder.append(it)
                        .append("\n")
            }
            if (it.contains("system_server")) {
                builder.append(it)
                        .append("\n")
            }
            if (it.contains("iowait")) {
                builder.append(it)
                        .append("\n")
            }
        }
        return builder.toString()
    }
}