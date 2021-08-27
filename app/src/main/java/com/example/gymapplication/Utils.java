package com.example.gymapplication;

import java.util.ArrayList;

public class Utils {
    //todo: step 1: creating static ArrayLists
    private static ArrayList<Training> trainings;

    //todo: step 4: static arrayList to save the plans
    private static ArrayList<Plan> plans;


    //todo: step 2: creating a method to initialise our trainings
    public static void initTrainings()
    {
        //todo: step 2.1: check, if it is null, initialise it
        if(null==trainings)
        {
            trainings = new ArrayList<>();
        }

        //todo: step 2.2: adding trainings to the "trainings" AL
        Training pushUp = new Training(1,"Push Up","ye hai chhote push up ka" +
                "chota description","aur ye hai lamba information",
                "https://image.shutterstock.com/image-photo/picture-young-athletic-man-doing-260nw-461264842.jpg");
        trainings.add(pushUp);

        Training squat = new Training(2,"Squat","if you crouch down very low and sit on your heels, you Squat"
        ,"a squat is a strenght exercise in which a trainee lower their hips from a standing position" +
                "and then stands back"
        ,"https://image.shutterstock.com/image-photo/athletic-man-doing-squats-preparing-260nw-1045041430.jpg");
        trainings.add(squat);


        Training legPress = new Training(3,"Leg Press","The leg press is pressing legs inwards"
        ,"the leg press is a weight training exercise in which the individual pushes the wight or resistance away from them",
                "https://image.shutterstock.com/image-illustration/inclined-leg-press-3d-illustration-260nw-434632384.jpg");
        trainings.add(legPress);


        Training burpees = new Training(4,"Burpees","Start by standing upright with your feet shoulder-width apart and your arms down at your sides"
        ,"Jump your feet up to your palms by hinging at the waist. Get your feet as close to your hands as you can get, landing them outside your hands if necessary.",
                "https://image.shutterstock.com/image-vector/exercise-guide-woman-doing-squat-260nw-1350246731.jpg");
        trainings.add(burpees);
        Training pullUps = new Training(5,"PullUps","It’s the ultimate test of upper-body muscular strength and one of the very few bodyweight moves that works your back and biceps",
                "The best way to build pull-up power is by doing wide-grip lat pull-downs, both heavy-weight sets and high-rep sets,” says Lerwill. “Eccentric pull-ups – where you ‘jump’ to the top position and lower back down very slowly – are also very good training drills",
                "https://image.shutterstock.com/image-vector/l-sit-hang-on-bar-600w-796165771.jpg");
        trainings.add(pullUps);

        //popeye image url = "https://spng.subpng.com/20190224/baf/kisspng-popeye-2-decal-sticker-popeye-rush-for-spinach-popeye-the-sailor-man-clip-art-cartoon-clip-art-5c7340fe8b1c03.6664593115510571505698.jpg"

        //now that we have added the trainings to the trainings AL, the best place to call this initTrainings() method is the
        //MainActivity, because that is the first activity which will run, whenever we open our app

        //so to go MainActivity

    }

    //todo: step 3: getter method for trainings AL, we will need it in AllTrainingsActivity,
    // as we have to show all the trainings there
    public static ArrayList<Training> getTrainings()
    {
        return trainings;
    }

    //todo: step 4.1: creating method to add some plans into the AL plans:
    public static boolean addPlan(Plan plan)
    {
        //first check if the "plans" AL is null or not
        if(plans==null)
        {
            plans = new ArrayList<>();
        }
        return plans.add(plan);
        //now go back to Training Activity
    }

    //todo: step 5: creating getter for the Plan's AL
    public static ArrayList<Plan> getPlans()
    {
        return plans;
        //now go back to PlanActivity for the remaining work,i.e the work of onclickListener for
        //btnAddPlan
    }

    //todo: step 6: meisam's way: making removePlan method
    public static boolean removePlan(Plan plan)
    {
        return plans.remove(plan);
    }


    //todo: step 6: smit's try
    // making removePlan() method
    /*public boolean removePlan(Plan plan, String day)
    {

        ArrayList<Plan> presentDayPlans = getPlansByDay(day);
        for(Plan p: presentDayPlans)
        {
            if(p.equals(plan))
            {
                return (presentDayPlans.remove(p));
            }
        }
        return false;
    }

    public ArrayList<Plan> getPlansByDay(String day)
    {
        ArrayList<Plan> allPlans = Utils.getPlans();
        ArrayList<Plan> presentDayPlans = new ArrayList<>();
        if(allPlans!=null)
        {
            for(Plan p: allPlans)
            {
                if(p.getDay().equalsIgnoreCase(day))
                {
                    presentDayPlans.add(p);
                }
            }
        }
        return presentDayPlans;
    }*/
}
