import java.util.*;
import java.lang.String;
import java.util.Calendar;
import java.util.function.IntPredicate;

class Main{

    public static void main(String[]args){

        ArrayList<course_relations> courses_list = new ArrayList<course_relations>();
        ArrayList<String> instructors_list = new ArrayList<String>(); 
        ArrayList<students> students_list = new ArrayList<students>();
        ArrayList<lecture_slides> lecture_slide_list = new ArrayList<lecture_slides>();
        ArrayList<lecture_videos> lecture_video_list = new ArrayList<lecture_videos>();
        ArrayList<assignment> all_assignments_list = new ArrayList<assignment>();
        ArrayList<quiz> all_quizes_list = new ArrayList<quiz>();

        // System.out.println("Create some courses for Backpack first");
        // Scanner backpack = new Scanner(System.in);
        // System.out.print("Enter the number of courses to be created: ");
        // int no_of_courses = backpack.nextInt();
        // backpack.nextLine();

        // for(int n=0; n<no_of_courses; n++){

        //     System.out.print("Enter name of the course: ");
        //     String course_name = backpack.nextLine();

        //     course new_course = new course();
        //     new_course.set_course_name(course_name);
        //     courses_list.add(new_course);
        // }

        while(1!=13){

            System.out.println("Welcome to Backpack");
            System.out.println("--------------------------------------");
            System.out.println("1. Enter as instructor \n2. Enter as student \n3. Exit");
            System.out.println("--------------------------------------");

            Scanner backpack = new Scanner(System.in);
            int role = backpack.nextInt();
            backpack.nextLine();

            if(role==1){
                System.out.print("Do you want to register as a instructor(YES/NO): ");
                String register = backpack.nextLine();

                if(register.equals("YES")){
                    System.out.print("Enter your name please: ");
                    String instructor = backpack.nextLine();
                    instructors_list.add(instructor);
                    System.out.println("You have registered as an instructor");
                }
                System.out.println("--------------------------------------");

                    // for(int n=0; n<courses_list.size(); n++){
                    //     System.out.println(n+")"+courses_list.get(n).get_course_name());
                    //     System.out.print("Are you the instructor of this course(YES/NO): ");
                    //     String answer = backpack.nextLine();
                    //      if(answer.equals("YES")){
                    //         courses_list.get(n).set_instructor(instructor);
                    //      }
                    // }
                int t=0;
                System.out.println("Instructors:");
                for(int n=0; n<instructors_list.size(); n++){
                    System.out.println(n+"-"+instructors_list.get(n));
                    t++;
                }
                if(t==0){
                    System.out.println("You have not registered yourself as an instructor on Backpack.");
                    break;
                }

                System.out.print("Choose id: ");
                int instructor_id = backpack.nextInt();
                System.out.println("{INSTRUCTOR MENU}");
                System.out.println("\n1. Add class material\n2. Add assessments\n3. View lecture materials\n4. View assessments\n5. Grade assessments\n6. Close assessment\n7. View comments\n8. Add comments\n9. Logout");
                    

                while(2!=13){
                    System.out.println("\nWelcome "+instructors_list.get(instructor_id));
                    System.out.println("{INSTRUCTOR MENU}");
                    
                    int function_number = backpack.nextInt();
                    backpack.nextLine();

                    if(function_number==1){
                        System.out.println("1. Add Lecture Slide \n2. Add Lecture Video");

                        int lecture_index = backpack.nextInt();
                        backpack.nextLine();

                        if(lecture_index==1){
                            System.out.print("Enter topic of slides: ");
                            String slides_title = backpack.nextLine();

                            System.out.print("Enter number of slides: ");
                            int number_of_slides = backpack.nextInt();
                            backpack.nextLine();

                            ArrayList<ArrayList<String>> slides_list = new ArrayList<ArrayList<String>>();

                            lecture_slides new_slides = new lecture_slides();
                            new_slides.set_slides_title(slides_title);
                            new_slides.set_number_of_slides(number_of_slides);
                            new_slides.set_instructor(instructors_list.get(instructor_id));
                            Calendar calendar = Calendar.getInstance();
                            new_slides.set_date_of_upload(calendar.getTime());

                            System.out.println("Enter content of slides");
                            for(int m=0; m<number_of_slides; m++){
                                System.out.print("Content of slide "+(m+1)+": ");
                                String slide_content = backpack.nextLine();

                                String[] new_content = slide_content.split(" ");
                                ArrayList<String> content_of_slide = new ArrayList<String>(
                                Arrays.asList(new_content));
                                slides_list.add(content_of_slide);
                                
                            }
                            new_slides.set_slides_list(slides_list);
                            lecture_slide_list.add(new_slides);
                        }

                        else if(lecture_index==2){
                            System.out.print("Enter topic of video: ");
                            String video_title = backpack.nextLine();

                            lecture_videos new_video = new lecture_videos();
                            new_video.set_video_title(video_title);
                            new_video.set_instructor(instructors_list.get(instructor_id));

                            while(3!=13){
                                System.out.print("Enter filename of video: ");
                                String video_file = backpack.next();


                                if(video_file.substring((video_file.length()-4), video_file.length()).equals(".mp4")){
                                    new_video.set_video_file(video_file);
                                    Calendar calendar = Calendar.getInstance();
                                    new_video.set_date_of_upload(calendar.getTime());
                                    lecture_video_list.add(new_video);

                                    break;
                                }
                                else{
                                    System.out.println("Please upload .mp4 file only");
                                    continue;
                                }
                            }
                        }
                        System.out.println("--------------------------------------");

                    }
                    else if(function_number==2){
                        System.out.println("1. Add Assignment \n2. Add Quiz");

                        int assessment_index = backpack.nextInt();
                        backpack.nextLine();

                        if(assessment_index==1){
                            System.out.print("Enter problem statement: ");
                            String problem_statement = backpack.nextLine();

                            System.out.print("Enter max marks: ");
                            int assignment_max_marks = backpack.nextInt();

                            assignment new_assignement = new assignment();
                            new_assignement.set_problem_statement(problem_statement);
                            new_assignement.set_assignment_max_marks(assignment_max_marks);

                            all_assignments_list.add(new_assignement);
                        }
                        else if(assessment_index==2){
                            System.out.print("Enter quiz question: ");
                            String quiz_question = backpack.nextLine();

                            quiz new_quiz = new quiz();
                            new_quiz.set_quiz_question(quiz_question);

                            all_quizes_list.add(new_quiz);
                        }
                        System.out.println("--------------------------------------");
                    }
                    else if(function_number==3){
                        for(int m=0; m<lecture_slide_list.size(); m++){
                            System.out.print("Title: "+lecture_slide_list.get(m).get_slides_title());
                            for(int p=0; p<lecture_slide_list.get(m).get_number_of_slides(); p++){
                                System.out.print("\nSlide "+(p+1)+": ");
                                for(int q=0; q<lecture_slide_list.get(m).get_slides_list().get(p).size(); q++){
                                    System.out.print(lecture_slide_list.get(m).get_slides_list().get(p).get(q)+" ");
                                }
                            }
                            System.out.println("\nNumber of slides: "+lecture_slide_list.get(m).get_number_of_slides());
                            System.out.println("Date of upload: "+lecture_slide_list.get(m).get_date_of_upload());
                            System.out.println("Uploaded by: "+lecture_slide_list.get(m).get_instructor());
                        }

                        for(int m=0; m<lecture_video_list.size(); m++){
                            System.out.println("\nTitle of video: "+lecture_video_list.get(m).get_video_title());
                            System.out.println("Video file: "+lecture_video_list.get(m).get_video_file());
                            System.out.println("Date of upload: "+lecture_video_list.get(m).get_date_of_upload());
                            System.out.println("Uploaded by: "+lecture_video_list.get(m).get_instructor());
                        }
                        System.out.println("--------------------------------------");
                    }
                    else if(function_number==4){
                        int k=0;
                        for(int m=0; m<all_assignments_list.size(); m++){
                            System.out.println("ID: "+k+" Assignment: "+all_assignments_list.get(m).get_problem_statement()+"   Max Marks: "+all_assignments_list.get(m).get_assignment_max_marks());
                            k++;
                            System.out.println("-----------------");
                        }
                        for(int m=0; m<all_quizes_list.size(); m++){
                            System.out.println("ID: "+k+" Question: "+all_quizes_list.get(m).get_quiz_question());
                            k++;
                            System.out.println("-----------------");
                        }
                        System.out.println("--------------------------------------");
                    }
                    else if(function_number==5){
                        HashMap<Integer, String> choosing_id = new HashMap<Integer, String>();
                        int k=0;
                        for(int m=0; m<all_assignments_list.size(); m++){
                            System.out.println("ID: "+k+" Assignment: "+all_assignments_list.get(m).get_problem_statement()+"   Max Marks: "+all_assignments_list.get(m).get_assignment_max_marks());
                            choosing_id.put(k, all_assignments_list.get(m).get_problem_statement());
                            k++;
                            System.out.println("-----------------");
                        }
                        for(int m=0; m<all_quizes_list.size(); m++){
                            System.out.println("ID: "+k+" Question: "+all_quizes_list.get(m).get_quiz_question());
                            choosing_id.put(k, all_quizes_list.get(m).get_quiz_question());
                            k++;
                            System.out.println("-----------------");
                        }
                        System.out.print("Enter ID of assessment to view submissions: ");
                        int assessment_ID = backpack.nextInt();
                        System.out.println("Choose ID from these ungraded submissions");

                        HashMap<Integer, String> choosing_student_id = new HashMap<Integer, String>();
                        int h=0;
                        for(int m=0; m<all_assignments_list.size(); m++){
                            if(all_assignments_list.get(m).get_problem_statement().equals(choosing_id.get(assessment_ID))){
                                for(int p=0; p<students_list.size(); p++){
                                    if(students_list.get(p).get_submitted_assignments().contains(all_assignments_list.get(m))){
                                        System.out.println(h+". "+students_list.get(p).get_student_name());
                                        choosing_student_id.put(h, students_list.get(p).get_student_name());
                                        h++;

                                    }
                                }
                            }
                        }
                        for(int m=0; m<all_quizes_list.size(); m++){
                            if(all_quizes_list.get(m).get_quiz_question().equals(choosing_id.get(assessment_ID))){
                                for(int p=0; p<students_list.size(); p++){
                                    if(students_list.get(p).get_submitted_quizes().contains(all_quizes_list.get(m))){
                                        System.out.println(h+". "+students_list.get(p).get_student_name());
                                        choosing_student_id.put(h, students_list.get(p).get_student_name());
                                        h++;

                                    }
                                }
                            }
                        }
                        int student_id = backpack.nextInt();
                        System.out.println("Submission:-");
                        System.out.print("Submission:");

                        for(int p=0; p<students_list.size(); p++){
                            if(students_list.get(p).get_student_name().equals(choosing_student_id.get(student_id))){
                                for(int m=0; m<students_list.get(p).get_submitted_assignments().size(); m++){
                                    if(students_list.get(p).get_submitted_assignments().get(m).get_problem_statement().equals(choosing_id.get(assessment_ID))){
                                        System.out.println(students_list.get(p).get_submitted_assignments_list().get(students_list.get(p).get_submitted_assignments().get(m)));
                                        System.out.println("\n----------------------");
                                        System.out.println("Max Marks: "+students_list.get(p).get_submitted_assignments().get(m).get_assignment_max_marks());
                                        System.out.print("Max scored: ");
                                        int assignment_marks = backpack.nextInt();
                                        students_list.get(p).get_graded_assignments_list().put(students_list.get(p).get_submitted_assignments().get(m), assignment_marks);
                                    }
                                }
                            }
                        }
                        for(int p=0; p<students_list.size(); p++){
                            if(students_list.get(p).get_student_name().equals(choosing_student_id.get(student_id))){
                                for(int m=0; m<students_list.get(p).get_submitted_quizes().size(); m++){
                                    if(students_list.get(p).get_submitted_quizes().get(m).get_quiz_question().equals(choosing_id.get(assessment_ID))){
                                        System.out.println(students_list.get(p).get_submitted_quizes_list().get(students_list.get(p).get_submitted_quizes().get(m)));
                                        System.out.println("----------------------");
                                        System.out.println("Max Marks: "+students_list.get(p).get_submitted_quizes().get(m).get_quiz_max_marks());
                                        System.out.print("Max scored: ");
                                        int quiz_marks = backpack.nextInt();
                                        students_list.get(p).get_graded_quizes_list().put(students_list.get(p).get_submitted_quizes().get(m), quiz_marks);
                                    }

                                }
                            }
                        }
                        System.out.println("--------------------------------------");

                    }
                    else if(function_number==6){

                    }
                    else if(function_number==9){
                        System.out.println("Thanks for using Backpack "+instructors_list.get(instructor_id));
                        System.out.println("--------------------------------------");

                        break;
                    }           
                }
            }
            else if(role==2){
                System.out.print("Do you want to register as a student(YES/NO): ");
                String register = backpack.nextLine();

                if(register.equals("YES")){
                    System.out.print("Enter your name please: ");
                    String student = backpack.nextLine();
                    
                    ArrayList<assignment> pending_assignments_list = new ArrayList<assignment>();
                    pending_assignments_list.addAll(all_assignments_list);
                    ArrayList<quiz> pending_quizes_list = new ArrayList<quiz>();
                    pending_quizes_list.addAll(all_quizes_list);

                    students new_student = new students();
                    new_student.set_student_name(student);
                    new_student.set_pending_assignments_list(pending_assignments_list);
                    new_student.set_pending_quizes_list(pending_quizes_list);

                    students_list.add(new_student);
                    
                    System.out.println("You have registered as a student");
                }
                System.out.println("--------------------------------------");

                int t=0;
                System.out.println("Students:");
                for(int n=0; n<students_list.size(); n++){
                    System.out.println(n+"-"+students_list.get(n).get_student_name());
                    t++;
                }
                if(t==0){
                    System.out.println("You have not registered yourself as an instructor on Backpack.");
                    break;
                }

                System.out.print("Choose id: ");
                int student_id = backpack.nextInt();
                System.out.println("{STUDENT MENU}");
                System.out.println("\n1. View lecture materials\n2. View assessments\n3. Submit assessments\n4. View grades\n5. View comments\n6. Add comments\n7. Logout");

                while(2!=13){
                    System.out.println("\nWelcome "+students_list.get(student_id).get_student_name());
                    System.out.println("{STUDENT MENU}");
                    
                    int function_number = backpack.nextInt();
                    backpack.nextLine();
                    if(function_number==1){
                        for(int m=0; m<lecture_slide_list.size(); m++){
                            System.out.print("Title: "+lecture_slide_list.get(m).get_slides_title());
                            for(int p=0; p<lecture_slide_list.get(m).get_number_of_slides(); p++){
                                System.out.print("\nSlide "+(p+1)+": ");
                                for(int q=0; q<lecture_slide_list.get(m).get_slides_list().get(p).size(); q++){
                                    System.out.print(lecture_slide_list.get(m).get_slides_list().get(p).get(q)+" ");
                                }
                            }
                            System.out.println("\nNumber of slides: "+lecture_slide_list.get(m).get_number_of_slides());
                            System.out.println("Date of upload: "+lecture_slide_list.get(m).get_date_of_upload());
                            System.out.println("Uploaded by: "+lecture_slide_list.get(m).get_instructor());
                        }

                        for(int m=0; m<lecture_video_list.size(); m++){
                            System.out.println("\nTitle of video: "+lecture_video_list.get(m).get_video_title());
                            System.out.println("Video file: "+lecture_video_list.get(m).get_video_file());
                            System.out.println("Date of upload: "+lecture_video_list.get(m).get_date_of_upload());
                            System.out.println("Uploaded by: "+lecture_video_list.get(m).get_instructor());
                        }
                        System.out.println("--------------------------------------");
                    }
                    else if(function_number==2){
                        int k=0;
                        for(int m=0; m<all_assignments_list.size(); m++){
                            System.out.println("ID: "+k+" Assignment: "+all_assignments_list.get(m).get_problem_statement()+"   Max Marks: "+all_assignments_list.get(m).get_assignment_max_marks());
                            k++;
                            System.out.println("-----------------");
                        }
                        for(int m=0; m<all_quizes_list.size(); m++){
                            System.out.println("ID: "+k+" Question: "+all_quizes_list.get(m).get_quiz_question());
                            k++;
                            System.out.println("-----------------");
                        }
                        System.out.println("--------------------------------------");
                    }
                    else if(function_number==3){
                        HashMap<Integer, String> choosing_id = new HashMap<Integer, String>();
                        int k=0;
                        if(students_list.get(student_id).get_pending_assignments_list().isEmpty() && students_list.get(student_id).get_pending_quizes_list().isEmpty()){
                            System.out.println("No pending assessments");
                        }
                        else{
                            for(int m=0; m<students_list.get(student_id).get_pending_assignments_list().size(); m++){
                                System.out.println("ID: "+k+" Assignment: "+students_list.get(student_id).get_pending_assignments_list().get(m).get_problem_statement()+"   Max Marks: "+students_list.get(student_id).get_pending_assignments_list().get(m).get_assignment_max_marks());
                                choosing_id.put(k, students_list.get(student_id).get_pending_assignments_list().get(m).get_problem_statement());
                                k++;
                            }
                            for(int m=0; m<students_list.get(student_id).get_pending_quizes_list().size(); m++){
                                System.out.println("ID: "+k+" Question: "+students_list.get(student_id).get_pending_quizes_list().get(m).get_quiz_question());
                                choosing_id.put(k, students_list.get(student_id).get_pending_quizes_list().get(m).get_quiz_question());
                                k++;
                            }
                            System.out.print("Enter ID of assessment: ");
                            int assessment_ID = backpack.nextInt();
                            backpack.nextLine();
    
                            for(int m=0; m<students_list.get(student_id).get_pending_assignments_list().size(); m++){
                                if(students_list.get(student_id).get_pending_assignments_list().get(m).get_problem_statement().equals(choosing_id.get(assessment_ID))){
                                    while(3!=13){
                                        System.out.print("Enter filename of assignment: ");
                                        String file_name = backpack.nextLine();
                                        if(file_name.substring((file_name.length()-4), file_name.length()).equals(".zip")){
                                
                                            students_list.get(student_id).get_submitted_assignments_list().put(students_list.get(student_id).get_pending_assignments_list().get(m), file_name);
                                            students_list.get(student_id).get_submitted_assignments().add(students_list.get(student_id).get_pending_assignments_list().get(m));
                                            students_list.get(student_id).get_pending_assignments_list().remove(m);
                                            break;
                                        }
                                    }
                                }
                            }
                            for(int m=0; m<students_list.get(student_id).get_pending_quizes_list().size(); m++){
                                if(students_list.get(student_id).get_pending_quizes_list().get(m).get_quiz_question().equals(choosing_id.get(assessment_ID))){
                                    System.out.print(students_list.get(student_id).get_pending_quizes_list().get(m).get_quiz_question()+" ");
                                    String quiz_answer = backpack.nextLine();
        
                                    students_list.get(student_id).get_submitted_quizes_list().put(students_list.get(student_id).get_pending_quizes_list().get(m), quiz_answer);
                                    students_list.get(student_id).get_submitted_quizes().add(students_list.get(student_id).get_pending_quizes_list().get(m));
                                    students_list.get(student_id).get_pending_quizes_list().remove(m);
                                }
                            }
                        }
                        System.out.println("--------------------------------------");
                    } 
                    else if(function_number==4){

                    } 
                    else if(function_number==7){
                        System.out.println("Thanks for using Backpack "+students_list.get(student_id));
                        System.out.println("--------------------------------------");
                        break;
                    }
                
                }        
            }
            else if(role==3){
                System.out.println("Thanks for choosing BACKPACK by IIITD.");
                System.out.println("--------------------------------------");

                break;
            }
            else{
                continue;
            }
        }
    }
}

class course_relations{
    private String instructor;
    private String student;

    public String get_instructor(){
        return instructor;
    }
    public void set_instructor(String instructor){
        this.instructor = instructor;
    }
    public String get_student(){
        return student;
    }
    public void set_student(String student){
        this.student = student;
    }
}
class lecture_videos extends course_relations{
    private String video_title;
    private String video_file;
    private Date date_of_upload;

    public String get_video_title(){
        return video_title;
    }
    public void set_video_title(String video_title){
        this.video_title = video_title;
    }
    public String get_video_file(){
        return video_file;
    }
    public void set_video_file(String video_file){
        this.video_file = video_file;
    }
    public Date get_date_of_upload(){
        return date_of_upload;
    }
    public void set_date_of_upload(Date date_of_upload){
        this.date_of_upload = date_of_upload;
    }
}
class lecture_slides extends course_relations{
    private String slides_title;
    private int number_of_slides;
    private ArrayList<ArrayList<String>> slides_list = new ArrayList<ArrayList<String>>();
    private Date date_of_upload;

    public String get_slides_title(){
        return slides_title;
    }
    public void set_slides_title(String slides_title){
        this.slides_title = slides_title;
    }
    public int get_number_of_slides(){
        return number_of_slides;
    }
    public void set_number_of_slides(int number_of_slides){
        this.number_of_slides = number_of_slides;
    }
    public ArrayList<ArrayList<String>> get_slides_list(){
        return slides_list;
    }
    public void set_slides_list(ArrayList<ArrayList<String>> slides_list){
        this.slides_list = slides_list;
    }
    public Date get_date_of_upload(){
        return date_of_upload;
    }
    public void set_date_of_upload(Date date_of_upload){
        this.date_of_upload = date_of_upload;
    }
}
class backpack_comments extends course_relations{
    private String comment;
    private Date date_of_upload;

    public void add_comment(){
        System.out.println(this.comment+" - "+this.get_instructor());
        System.out.println()
    }

    public String get_comment(){
        return comment;
    }
    public void set_comment(String comment){
        this.comment = comment;
    }
    public Date get_date_of_upload(){
        return date_of_upload;
    }
    public void set_date_of_upload(Date date_of_upload){
        this.date_of_upload = date_of_upload;
    }
}
class assignment{
    private String problem_statement;
    private int assignment_max_marks;

    public String get_problem_statement(){
        return problem_statement;
    }
    public void set_problem_statement(String problem_statement){
        this.problem_statement = problem_statement;
    }
    public int get_assignment_max_marks(){
        return assignment_max_marks;
    }
    public void set_assignment_max_marks(int assignment_max_marks){
        this.assignment_max_marks = assignment_max_marks;
    }
}
class quiz extends assignment{
    private String quiz_question;
    private int quiz_max_marks=1;

    public String get_quiz_question(){
        return quiz_question;
    }
    public void set_quiz_question(String quiz_question){
        this.quiz_question = quiz_question;
    }
    public int get_quiz_max_marks(){
        return quiz_max_marks;
    }
}
class students extends quiz{
    private String student_name;
    private ArrayList<assignment> pending_assignments_list = new ArrayList<assignment>();
    private ArrayList<assignment> submitted_assignments = new ArrayList<assignment>();
    private HashMap<assignment, String> submitted_assignments_list = new HashMap<assignment, String>();
    private HashMap<assignment, Integer> graded_assignments_list = new HashMap<assignment, Integer>();
    private ArrayList<quiz> pending_quizes_list = new ArrayList<quiz>();
    private ArrayList<quiz> submitted_quizes = new ArrayList<quiz>();
    private HashMap<quiz, String> submitted_quizes_list = new HashMap<quiz, String>();
    private HashMap<quiz, Integer> graded_quizes_list = new HashMap<quiz, Integer>();

    public String get_student_name(){
        return student_name;
    }
    public void set_student_name(String student_name){
        this.student_name = student_name;
    }
    public ArrayList<assignment> get_pending_assignments_list(){
        return pending_assignments_list;
    }
    public void set_pending_assignments_list(ArrayList<assignment> pending_assignments_list){
        this.pending_assignments_list = pending_assignments_list;
    }
    public ArrayList<assignment> get_submitted_assignments(){
        return submitted_assignments;
    }
    public void set_submitted_assignments(ArrayList<assignment> submitted_assignments){
        this.submitted_assignments = submitted_assignments;
    }
    public HashMap<assignment, String> get_submitted_assignments_list(){
        return submitted_assignments_list;
    }
    public void set_submitted_assignments_list(HashMap<assignment, String> submitted_assignments_list){
        this.submitted_assignments_list = submitted_assignments_list;
    }
    public HashMap<assignment, Integer> get_graded_assignments_list(){
        return graded_assignments_list;
    }
    public void set_graded_assignments_list(HashMap<assignment, Integer> graded_assignments_list){
        this.graded_assignments_list = graded_assignments_list;
    }
    public ArrayList<quiz> get_pending_quizes_list(){
        return pending_quizes_list;
    }
    public void set_pending_quizes_list(ArrayList<quiz> pending_quizes_list){
        this.pending_quizes_list = pending_quizes_list;
    }
    public ArrayList<quiz> get_submitted_quizes(){
        return submitted_quizes;
    }
    public void set_submitted_quizes(ArrayList<quiz> submitted_quizes){
        this.submitted_quizes = submitted_quizes;
    }
    public HashMap<quiz, String> get_submitted_quizes_list(){
        return submitted_quizes_list;
    }
    public void set_submitted_quizes_list(HashMap<quiz, String> submitted_quizes_list){
        this.submitted_quizes_list = submitted_quizes_list;
    }
    public HashMap<quiz, Integer> get_graded_quizes_list(){
        return graded_quizes_list;
    }
    public void set_graded_quizes_list(HashMap<quiz, Integer> graded_quizes_list){
        this.graded_quizes_list = graded_quizes_list;
    }
}