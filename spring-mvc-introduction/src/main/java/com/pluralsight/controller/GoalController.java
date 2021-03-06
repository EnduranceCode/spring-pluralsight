package com.pluralsight.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pluralsight.model.Goal;

@Controller
@SessionAttributes("goal")
public class GoalController {

    @RequestMapping(value = "/addGoal", method = RequestMethod.GET)
    public String addGoal(Model model) {

        Goal goal = new Goal();
        goal.setMinutes(10);
        /*
         * The key in the model corresponds to the value of
         * the @SessionAttributes
         */
        model.addAttribute("goal", goal);

        return "addGoal";
    }

    @RequestMapping(value = "addGoal", method = RequestMethod.POST)
    /*
     * The @ModelAttribute value corresponds to the object's name that is linked
     * to
     */
    public String updateGoal(@Valid @ModelAttribute("goal") Goal goal, BindingResult result) {

        System.out.println("Result has errors: " + result.hasErrors());

        System.out.println("Minutes updated: " + goal.getMinutes());

        if (result.hasErrors()) {

            return "addGoal";
        }

        return "redirect:addMinutes.html";
    }
}
