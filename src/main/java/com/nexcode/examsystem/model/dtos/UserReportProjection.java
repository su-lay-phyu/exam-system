package com.nexcode.examsystem.model.dtos;

public interface UserReportProjection {
    String getExamName();
    String getCourseName();
    String getLevelName();
    Integer getObtainedResult();
    Boolean getIsPass();
}


