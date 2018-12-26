package com.eastern.parser.er

import com.eastern.parser.model.ContentDesc
import com.eastern.parser.report.IReporter


enum class AnalyzerSet constructor(val clazz: Class<out IAnalyzer>) {

    LOCK_ANALYZER(LocksAnalyzer::class.java),
    LOCK_WAIT_ANALYZER(LockWaitAnalyzer::class.java),
    DEAD_LOCK_ANALYZER(DeadLockAnalyzer::class.java),
    UI_THREAD_ANALYZER(UIThreadAnalyzer::class.java),
    IO_WAIT_LOCK_ANALYZER(IOWaitAnalyzer::class.java),
    ALLOCATION_ANALYZER(TrackExternalAllocationAnalyzer::class.java),
    CPU_ANALYZER(CpuAnalyzer::class.java),
    SERVICE_ANALYZER(ServiceAnalyzer::class.java),
    BROADCAST_ANALYZER(BroadcastAnalyzer::class.java),
    INPUT_ANALYZER(InputDispatchingAnalyzer::class.java),
    CP_ANALYZER(ContentProviderAnalyzer::class.java),
    IPC_ANALYZER(IPCAnalyzer::class.java);

    fun go(reporter: IReporter, content: ContentDesc) {
        reporter.report(clazz.newInstance().analyzer(content))
    }
}