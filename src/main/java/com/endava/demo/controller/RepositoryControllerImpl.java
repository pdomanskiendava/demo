package com.endava.demo.controller;

import com.endava.demo.api.RepositoryApi;
import com.endava.demo.model.Repository;
import com.endava.demo.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping()
public class RepositoryControllerImpl extends BaseController implements RepositoryApi {

    private final RepositoryService repositoryService;

    @Autowired
    public RepositoryControllerImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Override
    public ResponseEntity<List<Repository>> callList(
            String username
    ) {
        return ResponseEntity.ok(repositoryService.getUserBranchList(username));
    }

}
