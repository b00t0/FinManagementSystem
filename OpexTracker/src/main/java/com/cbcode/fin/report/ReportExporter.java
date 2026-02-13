package com.cbcode.fin.report;

import java.nio.file.Path;

public interface ReportExporter {
    void export(OpexReport report, Path path);
}
