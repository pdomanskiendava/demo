package com.endava.demo.mapper;

import com.endava.demo.adapter.dto.GithubBranch;
import com.endava.demo.adapter.dto.GithubRepository;
import com.endava.demo.model.Branch;
import com.endava.demo.model.Repository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class ResultMapper {

    /**
     * Map from github information into Repository model from REST API
     *
     * @param githubRepository  repository which we will get name and owner
     * @param githubBranchArray branches of repository to get they name, sha of last commit
     * @return Repository.class result which contains mapped information from github
     */
    public Repository map(GithubRepository githubRepository, Optional<GithubBranch[]> githubBranchArray) {
        var repository = new Repository();
        repository.setName(githubRepository.getName());
        repository.setOwner(githubRepository.getOwner().getLogin());

        if (githubBranchArray.isPresent() && githubBranchArray.get().length > 0) {
            var branchList = mapBranchListWithLastCommitSHA(githubBranchArray);
            repository.setLastCommit(branchList);
        }

        return repository;
    }

    public ArrayList<Branch> mapBranchListWithLastCommitSHA(Optional<GithubBranch[]> githubBranchArray) {
        var branchList = new ArrayList<Branch>();
        for (GithubBranch githubBranch : githubBranchArray.get()) {
            var branch = new Branch();
            if (githubBranch.getCommit() != null) {
                branch.setLastCommit(githubBranch.getCommit().getSha());
            }
            branch.setName(githubBranch.getName());
            branchList.add(branch);
        }
        return branchList;
    }
}
