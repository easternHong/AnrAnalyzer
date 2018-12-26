package com.eastern.parser

import com.eastern.parser.er.AnalyzerSet
import com.eastern.parser.model.ContentDesc
import com.eastern.parser.model.ThreadDesc
import com.eastern.parser.report.Reporter
import java.io.File
import java.util.regex.Pattern


object Parser : IParser {


    val FLAG_0 = "Cmd line: "
    val TIME_PID_REG = "----- pid [0-9]{1,5} at [0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} -----"
    val THREAD_LINE = "prio=[0-9]{1,5} tid=[0-9]{1,5}"

    override fun parse(pkgName: String, file: File): String {
        var preLine = ""
        val contentDesc = ContentDesc(pkgName)
        var lineNum = 1
        var pkgStart = false
        var threadStackStart = false
        var threadDesc = ThreadDesc()
        file.forEachLine {
            if (it.startsWith(FLAG_0)) {
                if (FLAG_0.plus(pkgName) == it) {
                    pkgStart = true
                    val findPid = Pattern.compile(TIME_PID_REG).matcher(preLine).find()
                    if (findPid) {
                        contentDesc.pid = preLine.replace("----- pid ", "").split(" ")[0]
                        contentDesc.startTime = preLine.substring(preLine.indexOf("at")).replace(" -----", "")
                    }
                } else {
                    //other pkg
                    pkgStart = false
                }
            }
            if (pkgStart) {
                val findThreadLine = Pattern.compile(THREAD_LINE).matcher(it).find()
                if (pkgStart && findThreadLine) {
                    threadStackStart = true
                    threadDesc = ThreadDesc()
                    threadDesc.desc = it
                    threadDesc.lineNumber = lineNum
                    //一个线程堆栈开始
                    contentDesc.threadList.add(threadDesc)
                }
                if (threadStackStart) {
                    threadDesc.stack.add(it)
                }
                if (it.isEmpty() || it.isBlank()) {
                    threadStackStart = false
                }
                if (contentDesc.threadList.isEmpty()) {
                    contentDesc.systemDesc.add(it)
                }
            }
            preLine = it
            lineNum++
        }
        val reporter = Reporter()
        for (clazz in AnalyzerSet.values()) {
            clazz.go(reporter, contentDesc)
        }
        return reporter.result.toString()
    }


    @JvmStatic
    fun main(args: Array<String>) {
        val c = "----- pid 21638 at 2018-12-13 11:07:04 -----"
        println(Pattern.compile(TIME_PID_REG).matcher(c).find())
    }
}