package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @RequestMapping(value = "results")
    public String searchResults (Model model, @RequestParam String searchType, @RequestParam String searchTerm){

        ArrayList<Job> jobsSearched;

        if (searchType.equals("all") && (searchTerm.equals("all") || searchTerm.equals(""))) {
            jobsSearched = JobData.findAll();
        } else {
            jobsSearched = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        model.addAttribute("columns", columnChoices);
        model.addAttribute("jobResults", jobsSearched);
        model.addAttribute("searchType", searchType);

        return "search";
    }

}
