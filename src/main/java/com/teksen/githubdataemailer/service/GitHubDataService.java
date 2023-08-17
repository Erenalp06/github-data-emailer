package com.teksen.githubdataemailer.service;

import com.teksen.githubdataemailer.entity.GitHubData;
import com.teksen.githubdataemailer.entity.RepoDetails;
import com.teksen.githubdataemailer.entity.ViewData;
import com.teksen.githubdataemailer.exception.CustomException;
import com.teksen.githubdataemailer.exception.custom.GitHubApiRequestException;
import com.teksen.githubdataemailer.exception.custom.GitHubRepoDetailsFetchException;
import com.teksen.githubdataemailer.repository.GitHubDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Service
public class GitHubDataService {

    private final GitHubDataRepository repository;
    private final Logger LOGGER = LoggerFactory.getLogger(GitHubDataService.class);
    private final GitHubAPIService service;

    public GitHubDataService(GitHubDataRepository repository, GitHubAPIService service) {
        this.repository = repository;
        this.service = service;
    }

    public String addGitHubData(GitHubData gitHubData) {

        if(gitHubData == null || gitHubData.isEmpty()){
            LOGGER.error("Data is empty.");
            throw new GitHubRepoDetailsFetchException("Data is empty.", HttpStatus.BAD_REQUEST);
        }

        try {
            List<RepoDetails> repoDetails = service.fetchGitHubRepoDetailsByUsername(gitHubData.getUsername());
            repository.save(gitHubData, repoDetails);
            LOGGER.info("Data added successfully.");
            List<ViewData> viewData = getAllGitHubDataFromAPI();
            return "Data added successfully. Your email will be sent soon.";
        } catch (CustomException e) {
            LOGGER.error("Error occurred while adding data: {}", e.getMessage());
            throw new GitHubRepoDetailsFetchException("Error occurred while adding data:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public GitHubData getGitHubData() {
        return repository.findAll();
    }

    private List<ViewData> getAllGitHubDataFromAPI(){
        try {
            List<ViewData> body = service.fetchGitHubDataFromAPI(getGitHubData());
            LOGGER.info("Data fetched successfully.");
            LOGGER.info("Your email will be sent soon.");

            return body;
        } catch (CustomException e) {
            LOGGER.error("Error occurred while fetching data: {}", e.getMessage());
            throw new GitHubApiRequestException("Error occurred while fetching data:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
