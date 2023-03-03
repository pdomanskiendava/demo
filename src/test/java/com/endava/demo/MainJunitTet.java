package com.endava.demo;

import com.endava.demo.adapter.dto.GithubBranch;
import com.endava.demo.adapter.dto.GithubCommit;
import com.endava.demo.adapter.dto.GithubOwner;
import com.endava.demo.adapter.dto.GithubRepository;
import com.endava.demo.mapper.ResultMapper;
import com.endava.demo.model.Repository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MainJunitTet {

    private ResultMapper resultMapper = new ResultMapper();

    private static String OWNER_NAME = "owner_name";
    private static String REPO_NAME = "repo_name";
    private static String BRANCH_NAME = "branch_name";
    private static String SHA = "sha_123";


    /**
     * Test when we have all data correct
     */
    @Test
    @DisplayName("Test happy path: non fork repo with owner and one branch")
    void happy_path_test() {
        // Given

        GithubRepository repository = prepareCorrectNonForkRepository(OWNER_NAME, REPO_NAME);

        GithubBranch githubBranch = prepareCorrectBranch(BRANCH_NAME, SHA);

        GithubBranch[] branches = new GithubBranch[1];
        branches[0] = githubBranch;

        Optional<GithubBranch[]> branch = Optional.of(branches);

        // when

        Repository mappingResult = resultMapper.map(repository, branch);

        // then

        assertEquals(mappingResult.getOwner(), OWNER_NAME);
        assertEquals(mappingResult.getName(), REPO_NAME);
        assertEquals(mappingResult.getLastCommit().size(), 1);
        assertEquals(mappingResult.getLastCommit().get(0).getName(), BRANCH_NAME);
        assertEquals(mappingResult.getLastCommit().get(0).getLastCommit(), SHA);

    }

    /**
     * Test case when we do not have branches ( github before proper initialization can have issues)
     */
    @Test
    @DisplayName("Test case with missing branches")
    void missing_branch_list_path() {
        // Given

        GithubRepository repository = prepareCorrectNonForkRepository(OWNER_NAME, REPO_NAME);



        Optional<GithubBranch[]> branch = Optional.empty();

        // when

        Repository mappingResult = resultMapper.map(repository, branch);

        // then

        assertEquals(mappingResult.getOwner(), OWNER_NAME);
        assertEquals(mappingResult.getName(), REPO_NAME);
        assertNull(mappingResult.getLastCommit());
    }

    /**
     * Test case when we do not have commits ( github before proper initialization can have issues). Initial commit on main
     * branch will not be returned
     */
    @Test
    @DisplayName("Test case with missing commit")
    void missing_branch_commit() {
        // Given

        GithubRepository repository = prepareCorrectNonForkRepository(OWNER_NAME, REPO_NAME);

        GithubBranch githubBranch = prepareBranchWithoutCommit(BRANCH_NAME);

        GithubBranch[] branches = new GithubBranch[1];
        branches[0] = githubBranch;

        Optional<GithubBranch[]> branch = Optional.of(branches);

        // when

        Repository mappingResult = resultMapper.map(repository, branch);

        // then

        assertEquals(mappingResult.getOwner(), OWNER_NAME);
        assertEquals(mappingResult.getName(), REPO_NAME);
        assertEquals(mappingResult.getLastCommit().size(), 1);
        assertEquals(mappingResult.getLastCommit().get(0).getName(), BRANCH_NAME);
    }

    private static GithubBranch prepareCorrectBranch(String branchName, String sha) {
        GithubBranch githubBranch = new GithubBranch();
        githubBranch.setName(branchName);
        GithubCommit commit = new GithubCommit();
        commit.setSha(sha);
        githubBranch.setCommit(commit);
        return githubBranch;
    }

    private static GithubBranch prepareBranchWithoutCommit(String branchName) {
        GithubBranch githubBranch = new GithubBranch();
        githubBranch.setName(branchName);
        return githubBranch;
    }

    private static GithubRepository prepareCorrectNonForkRepository(String ownerName, String repoName) {
        GithubRepository repository = new GithubRepository();
        repository.setName(repoName);
        repository.setFork(false);
        GithubOwner owner = new GithubOwner();
        owner.setLogin(ownerName);
        repository.setOwner(owner);
        return repository;
    }
}
