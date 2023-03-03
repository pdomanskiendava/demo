package com.endava.demo.service;

import com.endava.demo.adapter.GithubAdapter;
import com.endava.demo.adapter.dto.GithubBranch;
import com.endava.demo.adapter.dto.GithubRepository;
import com.endava.demo.mapper.ResultMapper;
import com.endava.demo.model.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RepositoryService {
    private final GithubAdapter githubAdapter;
    private final ResultMapper resultMapper;

    public RepositoryService(GithubAdapter githubAdapter, ResultMapper resultMapper) {
        this.githubAdapter = githubAdapter;
        this.resultMapper = resultMapper;
    }

    /**
     *
     * Here we will do main functionality which is download repositories, filter forks, and for non-fork repositories we
     * will download basic branch info (name and last commit sha)
     *
     * @param username GitHub username
     * @return Mapped data in REST API model
     */
    public List<Repository> getUserBranchList(String username) {
        var result = new ArrayList<Repository>();
        var repositoryList = githubAdapter.getGithubRepositoryList(username);
        var nonForkRepositoryList = Arrays.stream(repositoryList).filter(repository -> !repository.getFork()).collect(Collectors.toList());

        for (GithubRepository repository : nonForkRepositoryList) {
            var branches = getListOfBranches(repository);
            result.add(resultMapper.map(repository, branches));
        }

        return result;
    }

    private Optional<GithubBranch[]> getListOfBranches(GithubRepository repository) {
        return Optional.ofNullable(githubAdapter.getGithubRepositoryBranches(repository.getOwner().getLogin(), repository.getName()));
    }
}
