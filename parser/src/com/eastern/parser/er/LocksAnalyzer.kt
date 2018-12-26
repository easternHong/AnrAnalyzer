package com.eastern.parser.er

import com.eastern.parser.model.ContentDesc
import java.util.regex.Pattern


/**
 * 每一个线程持有的锁，或者等待的锁
 */
class LocksAnalyzer : IAnalyzer {

    val patterns = Pattern.compile("<0x[0-9a-f]{8}>")
    override fun analyzer(contentDesc: ContentDesc): String {
        contentDesc.threadList.forEach { thread ->
            thread.stack.forEach {
                if (patterns.matcher(it).find()) {
                    if (it.contains("locked")) {
                        thread.lockedLocks.add(it.substring(it.indexOf("<") + 1, it.indexOf(">")))
                    } else if (it.contains("wait")) {
                        thread.waitingLocks.add(it.substring(it.indexOf("<") + 1, it.indexOf(">")))
                    }
                }
            }
        }
        return ""
    }

    private fun analyze(contentDesc: ContentDesc) {

    }
}