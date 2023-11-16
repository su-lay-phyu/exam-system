package com.nexcode.examsystem.model.projections;

public interface UserReportProjection {
    String getExamName();
    String getCourseName();
    String getLevelName();
    Integer getObtainedResult();
    Boolean getIsPassFail();
}


