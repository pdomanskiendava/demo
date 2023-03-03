package com.endava.demo.adapter.dto;

import lombok.Data;

@Data
public class GithubRepository {

    private String name;
    private Boolean fork;
    private String fullName;
    private GithubOwner owner;

}
