//import java.io.*;
import java.util.*;
class Main_menu{

    public static String generate_unique_ID(){
        Random uid = new Random();
        int unique_ID = uid.nextInt(999999);

        return String.format("%06d", unique_ID);
    }
    public static void main(String[]args){

        ArrayList<vaccines> vaccines_list = new ArrayList<vaccines>();
        ArrayList<citizens> registered_citizens__list = new ArrayList<citizens>();
        ArrayList<slots> slots_list = new ArrayList<slots>();
        HashMap<String, String> hospitals_uid_record = new HashMap<String, String>();
        HashMap<String, Integer> hospitals_pincode_record = new HashMap<String, Integer>();

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

                hospitals_pincode_record.put(new_hospital.get_name(), new_hospital.get_pin_code());

                String unique_ID = generate_unique_ID();
                Collection<String> values = hospitals_uid_record.values();
                List<String> hospital_IDs = new ArrayList<String>(values);

                if(!hospital_IDs.contains(unique_ID)){
                    new_hospital.register_hospitals(unique_ID);
                    hospitals_uid_record.put(new_hospital.get_name(), unique_ID);
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
                String unique_ID = cowin.nextLine();

                citizens new_citizen = new citizens();
                new_citizen.set_name(name);
                new_citizen.set_age(age);
                new_citizen.set_unique_ID(unique_ID);

                new_citizen.register_citizens();

                if(new_citizen.get_age() >= 18){
                    registered_citizens__list.add(new_citizen);
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

                ArrayList<String> new_hospital_uid = new ArrayList<String>();
                HashMap<String, Integer> choose_slot = new HashMap<String, Integer>();

                if(search_option == 1){
                    System.out.print("Enter PinCode: ");
                    int pin_code = cowin.nextInt();
                    cowin.nextLine();

                    for(int n : hospitals_pincode_record.values()){
                        if(pin_code == n){
                            System.out.println(hospitals_uid_record.get(getKey(hospitals_pincode_record, n)+" "+getKey(hospitals_pincode_record, n)));
                            new_hospital_uid.add(hospitals_uid_record.get(getKey(hospitals_pincode_record, n)));
                        }
                    }
                    

                }
                if(search_option == 2){
                    System.out.print("Enter Vaccine name: ");
                    String vaccine_name = cowin.nextLine();
                    cowin.nextLine();

                    for(slots n : slots_list){
                        if(n.get_vaccine() == vaccine_name){
                            System.out.println(n.get_hospital_ID()+" "+getKey(hospitals_uid_record, n.get_hospital_ID()));
                            new_hospital_uid.add(n.get_hospital_ID());
                        }
                    }
                }

                System.out.print("Enter hospital id: ");
                String hospital_id = cowin.nextLine();
                int p=0;
                for(slots n : slots_list){
                    if(n.get_hospital_ID() == hospital_id){
                        System.out.print(p+"->Day: "+n.get_day_number()+", Available Qty: "+n.get_quantity()+", Vaccine: "+n.get_vaccine());
                        choose_slot.put(n.get_vaccine(), p);

                    }
                    p++;
                }
                System.out.print("Choose Slot: ");
                int slot_choosing = cowin.nextInt();

                for(citizens n : registered_citizens__list){
                    if(n.get_unique_ID() == patient_unique_ID & p==0){
                        System.out.println(n.get_name()+" vaccinated with "+getKey(choose_slot, slot_choosing));
                    }
                    else if(p!=0){
                        System.out.println("No slots available");
                    }
                }
                if(search_option == 3){
                    break;
                }
                System.out.println("\n--------------------------------------");
            }

            else if(option == 6){
                System.out.print("Enter Hospital ID: ");
                String hospital_ID = cowin.nextLine();
                System.out.println(slots_list.size());

                for(int n=0; n<slots_list.size(); n++){
                    if(slots_list.get(n).get_hospital_ID().equals(hospital_ID)){
                        System.out.println("Day: "+slots_list.get(n).get_day_number()+" Vaccine: "+slots_list.get(n).get_vaccine()+" Available Qty: "+slots_list.get(n).get_quantity());
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
    private static String getKey(HashMap<String, String> hospitals_uid_record, String get_hospital_ID) {
        return null;
    }
    private static Object getKey(HashMap<String, Integer> hospitals_pincode_record, int n) {
        return null;
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
    public int et_gap_bw_doses(){
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
    }
    public void set_unique_ID(String unique_ID){
        this.unique_ID = unique_ID;
    }
}
class hospitals{
    private String name;
    private int pin_code;

    public void register_hospitals(String unique_ID){
        System.out.print("Citizen Name: "+name);
        System.out.print(", Age: "+pin_code);
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
    public static void add(slots new_slot) {
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