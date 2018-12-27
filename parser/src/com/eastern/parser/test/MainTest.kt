package com.eastern.parser.test

import com.eastern.parser.Parser
import com.eastern.parser.logcat.LogcatParser
import java.io.File


object MainTest {

    @JvmStatic
    fun main(args: Array<String>) {
        var mainThreadTraceFile = ""
        var logcatFile = ""
        var systrace = ""
        var pkgName = ""
        args.forEachIndexed { i, it ->
            if (it == " -m ") {
                mainThreadTraceFile = args[i + 1]
            }
            if (it == " -l ") {
                logcatFile = args[i + 1]
            }
            if (it == " -s ") {
                systrace = args[i + 1]
            }
            if (it == " -p ") {
                pkgName = args[i + 1]
            }
        }

        if (pkgName.isEmpty()) {
            throw IllegalArgumentException("包名不能为空")
        }

        val builder = StringBuilder()
        if (logcatFile.length > 1) {
            builder.append(parseLogcat(pkgName, File(logcatFile)))
        }
        if (systrace.length > 1) {
            builder.append(parseSysTrace(pkgName, File(systrace)))
        }
        if (mainThreadTraceFile.length > 1) {
            builder.append(parseMainThreadTrace(pkgName, File(mainThreadTraceFile)))
        }
        println(builder.toString())
    }

    private fun parseSysTrace(pkgName: String, file: File): String {
        return Parser.parse(pkgName, file)
    }

    private fun parseLogcat(pkgName: String, file: File): String {
        return LogcatParser.parse(pkgName, file)
    }

    private fun parseMainThreadTrace(pkgName: String, file: File): String {
        return ""
    }
}