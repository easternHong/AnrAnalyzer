package com.eastern.parser.er

import com.eastern.parser.model.ContentDesc

/**
 * ThreadState (defined at “dalvik/vm/thread.h “)
THREAD_UNDEFINED    = -1,       /* makes enum compatible with int32_t */

/* these match up with JDWP values 和jdwp协议匹配 */
THREAD_ZOMBIE       = 0,        /* TERMINATED */
THREAD_RUNNING      = 1,        /* RUNNABLE or running now */
THREAD_TIMED_WAIT   = 2,        /* TIMED_WAITING in Object.wait() */
THREAD_MONITOR      = 3,        /* BLOCKED on a monitor */
THREAD_WAIT         = 4,        /* WAITING in Object.wait() */

/* non-JDWP states */
THREAD_INITIALIZING = 5,        /* allocated, not yet running */
THREAD_STARTING     = 6,        /* started, not yet on thread list */
THREAD_NATIVE       = 7,        /* off in a JNI native method */
THREAD_VMWAIT       = 8,        /* waiting on a VM resource */
THREAD_SUSPENDED    = 9,        /* suspended, usually by GC or debugger */
 */
class UIThreadAnalyzer : IAnalyzer {

    override fun analyzer(contentDesc: ContentDesc): String {
        //主要分析主线程的状态
        return "[UIThreadAnalyzer:]"
    }
}