package com.teksen.githubdataemailer.service;

import com.teksen.githubdataemailer.entity.GitHubData;
import com.teksen.githubdataemailer.entity.RepoDetails;
import com.teksen.githubdataemailer.entity.View;
import com.teksen.githubdataemailer.entity.ViewData;
import com.teksen.githubdataemailer.exception.custom.GitHubApiRequestException;
import com.teksen.githubdataemailer.exception.custom.GitHubRepoDetailsFetchException;
import jakarta.mail.MessagingException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GitHubAPIService {

    private final RestTemplate restTemplate;
    private final EmailService emailService;

    public GitHubAPIService(RestTemplate restTemplate, EmailService emailService) {
        this.restTemplate = restTemplate;
        this.emailService = emailService;
    }

    public List<ViewData> fetchGitHubDataFromAPI(GitHubData gitHubData) {
        List<ViewData> viewDataList = new ArrayList<>();
        for(RepoDetails repo : gitHubData.getRepos()) {
            String url = String.format("https://api.github.com/repos/%s/traffic/views", repo.getFullName());
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + gitHubData.getApiKey());

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            try {
                ResponseEntity<ViewData> response = restTemplate.exchange(url, HttpMethod.GET, entity, ViewData.class);
                repo.setViewData(response.getBody());
                viewDataList.add(response.getBody());
            } catch (RestClientException e) {
                throw new GitHubApiRequestException("Error while fetching GitHub Data", HttpStatus.INTERNAL_SERVER_ERROR);

            }
        }

        return viewDataList;
    }

    public List<RepoDetails> fetchGitHubRepoDetailsByUsername(String username){
        List<RepoDetails> repoDetailsList;
        String url = String.format("https://api.github.com/users/%s/repos", username);

        try {
            ResponseEntity<RepoDetails[]> response = restTemplate.getForEntity(url, RepoDetails[].class);
            repoDetailsList = List.of(Objects.requireNonNull(response.getBody()));
        } catch (RestClientException e) {
            throw new GitHubRepoDetailsFetchException("Error while fetching GitHub Repo Details", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return repoDetailsList;
    }


    public void sendGitHubDataEmailsViaHtml(GitHubData gitHubData) throws MessagingException {

        if (gitHubData == null) {
            throw new IllegalArgumentException("GitHubDataList is empty");
        }

        StringBuilder emailContent = new StringBuilder();
        emailContent.append("<html><body>");

        for (RepoDetails repoDetails : gitHubData.getRepos()) {
            String fullRepoName = repoDetails.getFullName();
            String[] parts = fullRepoName.split("/");

            String repoName = parts[parts.length - 1];
            int totalCount = repoDetails.getViewData().count();

            emailContent.append(String.format("<h2>Repository: %s</h2>", repoName));
            emailContent.append(String.format("<p><strong>Total Views:</strong> %d</p>", totalCount));
            emailContent.append(String.format("<p><strong>Total Uniques:</strong> %d</p>", repoDetails.getViewData().uniques()));
            emailContent.append(String.format("<p><strong>Stargazers Count:</strong> %d</p>", repoDetails.getStargazersCount()));
            emailContent.append(String.format("<p><strong>Forks Count:</strong> %d</p>", repoDetails.getForksCount()));

            emailContent.append("<table style=\"border-collapse: collapse; width: 100%;\">");
            emailContent.append("<tr style=\"background-color: #f2f2f2;\">");
            emailContent.append("<th>Date</th><th>Views</th><th>Uniques</th>");
            emailContent.append("</tr>");

            for (View view : repoDetails.getViewData().views()) {
                String timestamp = view.timestamp();
                int viewCount = view.count();
                int uniques = view.uniques();

                emailContent.append("<tr>");
                emailContent.append(String.format("<td>%s</td><td>%d</td><td>%d</td>", timestamp, viewCount, uniques));
                emailContent.append("</tr>");
            }

            emailContent.append("</table>");
            emailContent.append("</div>");
        }

        emailContent.append("</body></html>");

        String content = emailContent.toString();
        String to = gitHubData.getEmail();
        String subject = "GitHub Repository View Information";

        emailService.sendStyledHTMLFormattedEmail(to, subject, content);
    }
}
