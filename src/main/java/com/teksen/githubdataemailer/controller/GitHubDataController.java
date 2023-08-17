package com.teksen.githubdataemailer.controller;

import com.teksen.githubdataemailer.entity.GitHubData;
import com.teksen.githubdataemailer.entity.GitHubDataRequest;
import com.teksen.githubdataemailer.service.GitHubDataService;
import com.teksen.githubdataemailer.util.GitHubDataMapper;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/github")
@Tag(name = "GitHub", description = "GitHub Data E-Mailer API")
public class GitHubDataController {

    private final GitHubDataService dataService;

    public GitHubDataController(GitHubDataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping()
    @Operation(
            summary = "Add GitHub Data and Cache Repositories",
            description = "This endpoint allows users to add their GitHub data. Upon adding, the system retrieves all repositories associated with the provided GitHub data from the GitHub API and caches them in the application's in-memory storage for future use. Additionally, an email containing the added GitHub data and cached repositories is sent to the user.",
            externalDocs = @ExternalDocumentation(description = "GitHub Data Emailer GitHub Repository", url = "https://github.com/Erenalp06/github-data-emailer")
    )
    public ResponseEntity<String> addGitHubData(@Valid @RequestBody GitHubDataRequest gitHubData) {
        return ResponseEntity.ok(dataService.addGitHubData(GitHubDataMapper.mapToGitHubData(gitHubData)));
    }

    @GetMapping
    @Operation(
            summary = "Get GitHub Data",
            description = "Get GitHub Data from in-memory cache"
    )
    public ResponseEntity<GitHubData> getGitHubData() {
        return ResponseEntity.ok(dataService.getGitHubData());
    }


}
