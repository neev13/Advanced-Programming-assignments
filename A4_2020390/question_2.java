package A4_2020390;

import java.lang.String;
import java.util.*;

public class question_2{

    public static void main(String[]args){

        ArrayList<image> images_list = new ArrayList<image>();

        System.out.println("Welcome to Image World");
        System.out.println("1. Input an image \n2. Update an image(matrix) \n3. Display an image \n4. Computation of negatives \n5. Exit");
        System.out.println("--------------------------------------");
        Scanner images = new Scanner(System.in);

        while(1!=13){

            System.out.print("Enter the index of the task you want to run: ");
            int task = images.nextInt();
            images.nextLine();
            System.out.println("---------------");
            image_functions new_function = new image_functions();

            if(task==1){

                System.out.print("Image name: ");
                String image_name = images.nextLine();
                System.out.println("---------");

                String image_type=null;
                while(2!=13){
                    System.out.print("Which image do you have for us today ?(color/grayscale): ");
                    image_type = images.nextLine();
                    if(image_type.equals("color") || image_type.equals("grayscale")){
                        System.out.println("---------");
                        break;
                    }
                    else{
                        System.out.println("Invalid image type. PLease choose either color or grayscale.");
                    }
                }
                System.out.print("Enter the number of rows in this image(matrix): ");
                int m = images.nextInt();
                System.out.println("---------");
                System.out.print("Enter the number of columns in this image(matrix): ");
                int n = images.nextInt();
                System.out.println("---------");

                int image_new[][] = new int[m][n];
                System.out.println("Enter the elements of this matrix(image) ->");
                for (int i = 0; i < m; i++){
                    for (int j = 0; j < n; j++){
                        while(2!=13){
                            image_new[i][j] = images.nextInt();
                            if(image_new[i][j]>=0 && image_new[i][j]<=255){
                                break;
                            }
                            else{
                                System.out.println("Invalid element for the image(matrix). Please put in a number in between 0 and 255, including them.");
                            }
                        }
                    }
                }
                
                image new_image = new image();
                new_image.set_matrix(image_new);
                new_image.set_image_name(image_name);
                new_image.set_image_type(image_type);
                new_image.set_no_of_rows(m);
                new_image.set_no_of_columns(n);
                images_list.add(new_image);
                System.out.println("--------------------------------------");
            }
            else if(task==2){

                System.out.print("Enter the name of the image you want: ");
                String image_name = images.nextLine();
                System.out.println("---------");

                int temporary=0;
                for(int m=0; m<images_list.size(); m++){
                    if(images_list.get(m).get_image_name().equals(image_name)){
                        System.out.print("Enter the row number of the element in the matrix to be updated: ");
                        int row_no = images.nextInt();
                        System.out.println("---------");
                        System.out.print("Enter the column number of the element in the matrix to be updated: ");
                        int column_no = images.nextInt();
                        System.out.println("---------");
                        System.out.print("Enter the new number which is to be updated at given location: ");
                        int new_no = images.nextInt();
                        System.out.println("---------");
                        images_list.get(m).get_matrix()[row_no-1][column_no-1]=new_no;
                        temporary=1;
                    }
                }
                if(temporary==1){
                    System.out.println("The image has been updated as per your choice.");
                }
                else if(temporary==0){
                    System.out.println("Image updation was unsuccessful. Try again later.");
                }
                System.out.println("--------------------------------------");
            }
            else if(task==3){
                System.out.print("Enter the name of the image you want: ");
                String image_name = images.nextLine();
                System.out.println("---------");

                new_function.display_image(images_list, image_name);
                System.out.println("--------------------------------------");
            }
            else if(task==4){
                System.out.print("Enter the name of the image you want: ");
                String image_name = images.nextLine();
                System.out.println("---------");

                new_function.computation_of_negatives(images_list, image_name);
                System.out.println("--------------------------------------");
            }
            else if(task==5){
                System.out.println("Thanks for using Image World.");
                System.out.println("--------------------------------------");
                break;
            }
        }
    }
}
class image{
    private int no_of_rows;
    private int no_of_columns;
    private String image_name;
    private String image_type;
    private int[][] matrix = new int[no_of_columns][no_of_rows];

    public int get_no_of_rows(){
        return no_of_rows;
    }
    public void set_no_of_rows(int no_of_rows){
        this.no_of_rows = no_of_rows;
    }
    public int get_no_of_columns(){
        return no_of_columns;
    }
    public void set_no_of_columns(int no_of_columns){
        this.no_of_columns = no_of_columns;
    }
    public String get_image_name(){
        return image_name;
    }
    public void set_image_name(String image_name){
        this.image_name = image_name;
    }
    public String get_image_type(){
        return image_type;
    }
    public void set_image_type(String image_type){
        this.image_type = image_type;
    }
    public int[][] get_matrix(){
        return matrix;
    }
    public void set_matrix(int[][] matrix){
        this.matrix = matrix;
    }
}
class image_functions extends image{
    void display_image(ArrayList<image> images_list, String image_name){
        for(int m=0; m<images_list.size(); m++){
            if(images_list.get(m).get_image_name().equals(image_name)){
                System.out.println("The type of image is: "+images_list.get(m).get_image_type());
                System.out.println("The matrix of the image is: "+Arrays.deepToString(images_list.get(m).get_matrix()));
            }
        }
    }
    void computation_of_negatives(ArrayList<image> images_list, String image_name){
        for(int m=0; m<images_list.size(); m++){
            if(images_list.get(m).get_image_name().equals(image_name)){
                for (int i=0; i<images_list.get(m).get_no_of_rows(); i++){
                    for (int j=0; j<images_list.get(m).get_no_of_columns(); j++){
                        images_list.get(m).get_matrix()[i][j] = (images_list.get(m).get_matrix()[i][j]-255);
                    }
                }
            }
        }
        display_image(images_list, image_name);
    }
}