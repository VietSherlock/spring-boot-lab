package com.vietlnn.springbootlab.controller;

import com.vietlnn.springbootlab.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

    private final Coach coach;
    private final Coach anotherCoach;

    //Constructor Injection
    @Autowired //autowired annotation tells Spring to inject a dependency
    //hockeyCoach is a bean ID of the HockeyCoach class.
    public DemoRestController(@Qualifier("hockeyCoach") Coach coach, @Qualifier("swimCoachId") Coach anotherCoach)
    {
        this.coach = coach;
        this.anotherCoach = anotherCoach;
    }
    
    //Setter Injection
    //    @Autowired
    //    public void setCoach(Coach coach){
    //        this.coach = coach;
    //    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout()
    {
        return coach.getDailyWorkout();
        //        return String.format("Comparing beans: coach == anotherCoach, %b", coach == anotherCoach);
    }
}