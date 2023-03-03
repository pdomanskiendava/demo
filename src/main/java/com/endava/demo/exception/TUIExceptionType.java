package com.endava.demo.exception;

public enum TUIExceptionType {

    UNKNOWN_REASON("Unknown reason"),
    PROBLEM_WITH_DOWNLOAD_REPOSITORY_LIST("We cannot get information about repository"),
    PROBLEM_WITH_DOWNLOAD_BRANCH_LIST("We cannot get information about branches");
    private String description;

    TUIExceptionType(String description){
        this.description = description;
    }

}
