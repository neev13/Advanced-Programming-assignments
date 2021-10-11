import java.util.*;
import java.lang.String;

class Main_menu{

    private static String unique_ID;
    public static String generate_unique_ID(){
        Random uid = new Random();
        int unique_ID = uid.nextInt(999999);

        return String.format("%06d", unique_ID);
    }
    public static void main(String[]args){

        ArrayList<vaccines> vaccines_list = new ArrayList<vaccines>(); //this list contains the objects of my vaccine class.
        ArrayList<citizens> registered_citizens_list = new ArrayList<citizens>(); //this list contains the objects of my citizens class.
        ArrayList<slots> slots_list = new ArrayList<slots>(); //this list contains the objects of my slots class.
        ArrayList<hospitals> hospitals_record = new ArrayList<hospitals>(); //this list contains the objects of my hospitals class.

        System.out.println("CoWin Portal initialised....");
        System.out.println("--------------------------------------\n1. Add Vaccine\n2. Register Hospital\n3. Register Citizen\n4. Add Slot for Vaccination\n5. Book Slot for Vaccination\n6. List all slots for a hospital\n7. Check Vaccination Status\n8. Exit");
        System.out.println("--------------------------------------");

        Scanner cowin = new Scanner(System.in);
        System.out.print("Enter the number of tasks you want to perform: ");
        int no_of_tasks = cowin.nextInt();
        System.out.println("\n--------------------------------------");

        for(int m=0; m<no_of_tasks; m++){

            System.out.print("Enter the number for the function you want to run: ");
            int option = cowin.nextInt();
            cowin.nextLine();

            if(option == 1){
                System.out.print("Vaccine Name: ");
                String name = cowin.nextLine();
                System.out.print("Number of Doses: ");
                int no_of_doses = cowin.nextInt();
                System.out.print("Gap Between Doses: ");
                int gap_bw_doses = cowin.nextInt();

                vaccines go_covid = new vaccines(); 
                go_covid.set_name(name);
                go_covid.set_no_of_doses(no_of_doses);
                go_covid.set_gap_bw_doses(gap_bw_doses);
                
                go_covid.add_vaccine();
                vaccines_list.add(go_covid);
                System.out.println("\n--------------------------------------");
            }

            else if(option == 2){
                System.out.print("Hospital Name: ");
                String name = cowin.nextLine();
                System.out.print("PinCode: ");
                int pin_code = cowin.nextInt();

                hospitals new_hospital = new hospitals();
                new_hospital.set_name(name);
                new_hospital.set_pin_code(pin_code);

                hospitals_record.add(new_hospital);

                for(int n=0; n<hospitals_record.size(); n++)

                unique_ID = (String)generate_unique_ID();
                HashSet<String> hospital_IDs = new HashSet<String>();

                for(int n=0; n<hospitals_record.size(); n++){
                    hospital_IDs.add(hospitals_record.get(n).get_unique_ID());
                }

                if(!hospital_IDs.contains(unique_ID)){
                    new_hospital.set_unique_ID(unique_ID);
                    new_hospital.register_hospitals();
                    hospitals_record.add(new_hospital);
                }

                System.out.println("\n--------------------------------------");
            }

            else if(option == 3){
                System.out.print("Citizen Name: ");
                String name = cowin.nextLine();
                System.out.print("Age: ");
                int age = cowin.nextInt();
                cowin.nextLine();
                System.out.print("Unique ID: ");
                String citizen_unique_ID = cowin.nextLine();
                String status = "REGISTERED";

                citizens new_citizen = new citizens();
                new_citizen.set_name(name);
                new_citizen.set_age(age);
                new_citizen.set_status(status);

                if(citizen_unique_ID.length()<12){
                    for(int n=0; n<(citizen_unique_ID.length()-12); n++){
                        citizen_unique_ID = "0" + citizen_unique_ID;
                    }
                }
                new_citizen.set_unique_ID(citizen_unique_ID);

                new_citizen.register_citizens();

                if(new_citizen.get_age() >= 18){
                    registered_citizens_list.add(new_citizen);
                }
                System.out.println("\n--------------------------------------");
            }

            else if(option == 4){
                System.out.print("Enter Hospital ID: ");
                String hospital_ID = cowin.nextLine();
                System.out.print("Enter number of Slots to be added: ");
                int no_of_slots = cowin.nextInt();

                for(int n=0; n<no_of_slots; n++){
                    
                    System.out.print("\nEnter Day Number: ");
                    int day_number = cowin.nextInt();
                    System.out.print("Enter Quantity Number: ");
                    int quantity = cowin.nextInt();
                    System.out.println("Select Vaccine");

                    for(int p=0; p<vaccines_list.size(); p++){

                        System.out.println(p+". "+vaccines_list.get(p).get_name());
                    }
                    int vaccine_index = cowin.nextInt();

                    slots new_slot = new slots();
                    new_slot.set_hospital_ID(hospital_ID);
                    new_slot.set_day_number(day_number);
                    new_slot.set_quantity(quantity);
                    new_slot.set_vaccine(vaccines_list.get(vaccine_index).get_name());

                    slots_list.add(new_slot);

                    System.out.print("Slot added by Hospital "+hospital_ID);
                    System.out.print(" for Day: "+day_number+", Available Quantity: "+quantity+" of Vaccine "+vaccines_list.get(vaccine_index).get_name());
                }
                System.out.println("\n--------------------------------------");
            }
            else if(option == 5){
                System.out.print("Enter patient Unique ID: ");
                String patient_unique_ID = cowin.nextLine();
                System.out.println("1. Search by area");
                System.out.println("2. Search by Vaccine");
                System.out.println("3. Exit");

                System.out.print("Enter option: ");
                int search_option = cowin.nextInt();
                cowin.nextLine();

                HashSet<String> new_hospital_uid = new HashSet<String>();
                HashMap<Integer, String> choose_slot = new HashMap<Integer, String>();

                if(search_option == 1){
                    System.out.print("Enter PinCode: ");
                    int pin_code = cowin.nextInt();
                    cowin.nextLine();

                    for(int n=0; n<hospitals_record.size(); n++){
                        if(pin_code == hospitals_record.get(n).get_pin_code() & !new_hospital_uid.contains(hospitals_record.get(n).get_unique_ID())){
                            System.out.println(hospitals_record.get(n).get_unique_ID()+" "+hospitals_record.get(n).get_name());
                            new_hospital_uid.add(hospitals_record.get(n).get_unique_ID());
                        }
                    }
                    System.out.print("Enter hospital id: ");
                    String hospital_id = cowin.nextLine();
    
                    for(int n=0; n<slots_list.size(); n++){
                        
                        if(slots_list.get(n).get_hospital_ID().equals(hospital_id)){
                            System.out.println(n+"->Day: "+slots_list.get(n).get_day_number()+", Available Qty: "+slots_list.get(n).get_quantity()+", Vaccine: "+slots_list.get(n).get_vaccine());
                            choose_slot.put(n, slots_list.get(n).get_vaccine());
                        }
                    }
                    System.out.print("Choose Slot: ");
                    int slot_choosing = cowin.nextInt();
    
                    String name=null;
                    for( int p=0; p<registered_citizens_list.size(); p++){
                        if (patient_unique_ID.equals(registered_citizens_list.get(p).get_unique_ID())){
                            name= registered_citizens_list.get(p).get_name();
                        }
                    }
                    for( int p=0; p<registered_citizens_list.size(); p++){
                        for(int q : choose_slot.keySet()){
                            if(slot_choosing == q ){
                                System.out.println(name+" vaccinated with "+choose_slot.get(q));
                                registered_citizens_list.get(p).set_vaccine_given(choose_slot.get(q));

                                if(registered_citizens_list.get(p).get_status().equals("REGISTERED")){
                                    registered_citizens_list.get(p).set_status("PARTIALLY VACCINATED");
                                }
                                else if(registered_citizens_list.get(p).get_status().equals("PARTIALLY VACCINATED")){
                                    registered_citizens_list.get(p).set_status("FULLY VACCINATED");
                                }
        
                            }
                        }
                    }
                }

                else if(search_option == 2){
                    System.out.print("Enter Vaccine name: ");
                    String vaccine_name = cowin.nextLine();

                    for(int n=0; n<slots_list.size(); n++){
                        for(int p=0; p<hospitals_record.size(); p++){
                            if(slots_list.get(n).get_vaccine().equals(vaccine_name)){
                                if(slots_list.get(n).get_hospital_ID().equals(hospitals_record.get(p).get_unique_ID())){
                                    if(!new_hospital_uid.contains(hospitals_record.get(n).get_unique_ID())){
                                        System.out.println(slots_list.get(n).get_hospital_ID()+" "+hospitals_record.get(p).get_name());
                                        new_hospital_uid.add(slots_list.get(n).get_hospital_ID());
                                    }
                                }
                            }
                        }
                    }
                    System.out.print("Enter hospital id: ");
                    String hospital_id = cowin.nextLine();
    
                    for(int n=0; n<slots_list.size(); n++){
                        if(vaccine_name.equals(slots_list.get(n).get_vaccine())){
                            if(slots_list.get(n).get_hospital_ID().equals(hospital_id)){
                                System.out.println(n+"->Day: "+slots_list.get(n).get_day_number()+", Available Qty: "+slots_list.get(n).get_quantity()+", Vaccine: "+slots_list.get(n).get_vaccine());
                                choose_slot.put(n, slots_list.get(n).get_vaccine());
                            }
                        }
                    }
                    System.out.print("Choose Slot: ");
                    int slot_choosing = cowin.nextInt();
    
                    String name=null;
                    for( int p=0; p<registered_citizens_list.size(); p++){
                        if (patient_unique_ID.equals(registered_citizens_list.get(p).get_unique_ID())){
                            name= registered_citizens_list.get(p).get_name();
                        }
                    }
                    for( int p=0; p<registered_citizens_list.size(); p++){
                        for(int q : choose_slot.keySet()){
                            if(slot_choosing == q ){
                                System.out.println(name+" vaccinated with "+choose_slot.get(q));
                                registered_citizens_list.get(p).set_vaccine_given(choose_slot.get(q));

                                if(registered_citizens_list.get(p).get_status().equals("REGISTERED")){
                                    registered_citizens_list.get(p).set_status("PARTIALLY VACCINATED");
                                }
                                else if(registered_citizens_list.get(p).get_status().equals("PARTIALLY VACCINATED")){
                                    registered_citizens_list.get(p).set_status("FULLY VACCINATED");
                                }
        
                            }
                        }
                    }
                }

                else{
                    break;
                }
                System.out.println("\n--------------------------------------");
            }

            else if(option == 6){
                System.out.print("Enter Hospital ID: ");
                String hospital_ID = cowin.nextLine();

                for(int n=0; n<slots_list.size(); n++){
                    if(slots_list.get(n).get_hospital_ID().equals(hospital_ID)){
                        System.out.println("Day: "+slots_list.get(n).get_day_number()+" Vaccine: "+slots_list.get(n).get_vaccine()+" Available Qty: "+slots_list.get(n).get_quantity());
                    }
                }
                System.out.println("\n--------------------------------------");
            }

            else if(option == 7){
                System.out.print("Enter Patient ID: ");
                String patient_ID = cowin.nextLine();

                for(int n=0; n<registered_citizens_list.size(); n++){
                    if(registered_citizens_list.get(n).get_unique_ID().equals(patient_ID)){
                        System.out.println(registered_citizens_list.get(n).get_status());

                        if(registered_citizens_list.get(n).get_status().equals("PARTIALLY VACCINATED") || registered_citizens_list.get(n).get_status().equals("FULLY VACCINATED")){
                            System.out.println("Vaccine Given: "+registered_citizens_list.get(n).get_vaccine_given());
                            
                            if(registered_citizens_list.get(n).get_status().equals("PARTIALLY VACCINATED")){
                                System.out.println("Number of Doses given: 1");
                                
                                for(int p=0; p<vaccines_list.size(); p++){
                                    if(vaccines_list.get(p).get_name().equals(registered_citizens_list.get(n).get_vaccine_given())){
                                        System.out.println("Next Dose due date: "+vaccines_list.get(p).get_gap_bw_doses());
                                    }
                                }
                            }
                            else{
                                System.out.println("Number of Doses given: 2");
                            }
                        }
                    }
                }
                System.out.println("\n--------------------------------------");
            }
            
            else if(option == 8){
                System.out.println("Thank you for using CoWin by Neev");
                System.out.println("\n--------------------------------------");
                break; 
            }
        }
    }
}

class vaccines{
    private String name;
    private int no_of_doses;
    private int gap_bw_doses;

    public void add_vaccine(){
        System.out.print("Vaccine Name: "+name);
        System.out.print(", Number of Doses: "+no_of_doses);
        System.out.print(", Gap Between Doses: "+gap_bw_doses);
    }
    public String get_name(){
        return name;
    }
    public void set_name(String name){
        this.name = name;
    }
    public int get_no_of_doses(){
        return no_of_doses;
    }
    public void set_no_of_doses(int no_of_doses){
        this.no_of_doses = no_of_doses;
    }
    public int get_gap_bw_doses(){
        return gap_bw_doses;
    }
    public void set_gap_bw_doses(int gap_bw_doses){
        this.gap_bw_doses = gap_bw_doses;
    }
}

class citizens{
    private String name;
    private int age;
    private String unique_ID;
    private String status;
    private String vaccine_given;

    public void register_citizens(){
        System.out.print("Citizen Name: "+name);
        System.out.print(", Age: "+age);
        System.out.print(", Unique ID: "+unique_ID);

        if(age < 18){
            System.out.println("\nOnly above 18 are allowed");
        }
    }
    public String get_name(){
        return name;
    }
    public void set_name(String name){
        this.name = name;
    }
    public int get_age(){
        return age;
    }
    public void set_age(int age){
        this.age = age;
    }
    public String get_unique_ID(){
        return unique_ID;
    }public void set_unique_ID(String unique_ID){
        this.unique_ID = unique_ID;
    }
    public String get_status(){
        return status;
    }
    public void set_status(String status){
        this.status = status;
    }
    public String get_vaccine_given(){
        return vaccine_given;
    }
    public void set_vaccine_given(String vaccine_given){
        this.vaccine_given = vaccine_given;
    }
}
class hospitals{
    private String name;
    private int pin_code;
    private String unique_ID;

    public void register_hospitals(){
        System.out.print("Hospital Name: "+name);
        System.out.print(", PinCode: "+pin_code);
        System.out.print(", Unique ID: "+unique_ID);
    }
    public String get_name(){
        return name;
    }
    public void set_name(String name){
        this.name = name;
    }
    public int get_pin_code(){
        return pin_code;
    }
    public void set_pin_code(int pin_code){
        this.pin_code = pin_code;
    }
    public String get_unique_ID(){
        return unique_ID;
    }
    public void set_unique_ID(String unique_ID){
        this.unique_ID = unique_ID;
    }
}
class slots{
    private String hospital_ID;
    private int no_of_slots;
    private int day_number;
    private int quantity;
    private String vaccine;

    public String get_hospital_ID(){
        return hospital_ID;
    }
    public void set_hospital_ID(String hospital_ID){
        this.hospital_ID = hospital_ID;
    }
    public int get_no_of_slots(){
        return no_of_slots;
    }
    public void set_no_of_slots(int no_of_slots){
        this.no_of_slots = no_of_slots;
    }
    public int get_day_number(){
        return day_number;
    }
    public void set_day_number(int day_number){
        this.day_number = day_number;
    }
    public int get_quantity(){
        return quantity;
    }
    public void set_quantity(int quantity){
        this.quantity = quantity;
    }
    public String get_vaccine(){
        return vaccine;
    }
    public void set_vaccine(String vaccine){
        this.vaccine = vaccine;
    }
}
