package com.endava.demo.adapter;


import com.endava.demo.adapter.dto.GithubBranch;
import com.endava.demo.adapter.dto.GithubRepository;
import com.endava.demo.exception.TUIException;
import com.endava.demo.exception.TUIExceptionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class GithubAdapter {

    private static String repositoryListUrl = "https://api.github.com/users/%s/repos";
    private static String repositoryBranchesUrl = "https://api.github.com/repos/%s/%s/branches";


    private final RestTemplate restTemplate;

    public GithubAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Downloading repository information by username. In case of problem we register message
     * and throw exception to ExceptionHandler.
     *
     * @param name github user name
     * @return Repository array
     */
    public GithubRepository[] getGithubRepositoryList(String name) {
        ResponseEntity<GithubRepository[]> response;
        try {
            response = restTemplate.getForEntity(String.format(repositoryListUrl, name), GithubRepository[].class);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TUIException(TUIExceptionType.PROBLEM_WITH_DOWNLOAD_REPOSITORY_LIST, e.getMessage());
        }

        return response.getBody();
    }

    /**
     * Downloading repository branches. In case of problem we register message
     * and throw exception to ExceptionHandler.
     *
     * @param owner repository owner
     * @param repository repository name
     * @return Array of branches of repository
     */
    public GithubBranch[] getGithubRepositoryBranches(String owner, String repository) {
        ResponseEntity<GithubBranch[]> response;
        try {
            response = restTemplate.getForEntity(String.format(repositoryBranchesUrl, owner, repository), GithubBranch[].class);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TUIException(TUIExceptionType.PROBLEM_WITH_DOWNLOAD_REPOSITORY_LIST, e.getMessage());
        }
        return response.getBody();

    }
}
