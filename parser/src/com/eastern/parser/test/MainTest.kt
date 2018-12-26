package com.eastern.parser.test

import com.eastern.parser.Parser
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.*


object MainTest {


    @JvmStatic
    fun main(args: Array<String>) {
        val prop = Properties()
        var input: FileInputStream? = null
        try {
            input = FileInputStream("./local.properties")
            prop.load(input)
            val ret = Parser.parse(prop.getProperty("pkgName"), File(prop.getProperty("traceFile")))
            println(ret)
        } catch (io: IOException) {
            io.printStackTrace()
        } finally {
            if (input != null) {
                try {
                    input.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

    }

}