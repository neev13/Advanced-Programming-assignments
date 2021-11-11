import java.util.*;

public class A3_2020390{

    public static void main(String[]args){


        ArrayList<matrix_types> matrices_list = new ArrayList<matrix_types>();

        System.out.println("Welcome to Matrix World");
        System.out.println("--------------------------------------");
        System.out.println("1. Input a matrix to get its matrix types. \n2. create matrix of requested matrix-type and label it with its other matrix-types. \n3. Update elements of the matrix while maintaining its fixed matrix-type. \n4. display all matrix types of requested matrix. "
                           +"\n5. Perform addition, subtraction, multiplication & division. \n6. Perform element-wise operations. \n7. Transpose matrices. \n8. Inverse matrices. "
                           +"\n9. Compute means: row-wise mean, column-wise mean, mean of all the elements. \n10. Compute determinants. \n11. Use singleton matrices as scalars, if requested. "
                           +"\n12. Compute A+A^T for a matrix A. \n13. Compute Eigen vectors and values. \n14. Solve sets of linear equations using matrices. "
                           +"\n15. Retrieve all the existing matrices (entered or created) having requested matrix-type labels. \n16. Exit");
        System.out.println("--------------------------------------");

        Scanner matrices = new Scanner(System.in);

        while(1!=13){

            System.out.print("Enter the index of the task you want to run: ");
            int task = matrices.nextInt();
            matrices.nextLine();

            if(task==1){
                System.out.print("Enter the number of rows in this matrix: ");
                int m = matrices.nextInt();
                System.out.print("Enter the number of columns in this matrix: ");
                int n = matrices.nextInt();

                int matrix[][] = new int[m][n];
                System.out.println("Enter the elements of this matrix ->");
                for (int i = 0; i < m; i++)
                    for (int j = 0; j < n; j++)
                        matrix[i][j] = matrices.nextInt();

                if(m>0 && n>0){
                    matrix_types new_matrix = new matrix_types();
                    new_matrix.set_matrix(matrix);
                    new_matrix.set_no_of_rows(m);
                    new_matrix.set_no_of_columns(n);

                    //new_matrix.identify_matrix_type();
                    matrices_list.add(new_matrix);
                }
                else{
                    System.out.println("Matrix given as input is incorrect. Try again.");
                }

                System.out.println("--------------------------------------------------");
            }

            else if(task==2){
                System.out.println("All 15 types of matrices are as follows: ");
                System.out.println("1. Rectangular Matrix \n2. Row Matrix \n3. Column Matrix \n4. Square Matrix "
                    +"\n5. Symmetric Matrix \n6. Skew-symmetric Matrix \n7. Upper-triangular Matrix \n8. Lower-triangular Matrix "
                    +"\n9. Singular Matrix \n10. Diagonal Matrix \n11. Scalar Matrix "
                    +"\n12. Identity Matrix \n13. Singleton Matrix \n14. Ones Matrix "
                    +"\n15. Null Matrix ");
                System.out.println("--------------");
                System.out.print("Enter the index of the matrix-type you want to retrieve: ");
                int index = matrices.nextInt();

                create_matrices new_matrix = new create_matrices();
                new_matrix.matrix_of_matrix_type(index, matrices_list);
                System.out.println("--------------------------------------------------");
            }

            else if(task==3){
                System.out.print("Enter the index of the matrix you want from the array: ");
                int index_1 = matrices.nextInt();
                System.out.println("The matrix chosen is "+Arrays.deepToString(matrices_list.get(index_1).get_matrix()));

                matrices_list.get(index_1).identify_matrix_type();
                HashMap<Integer, String> types_of_matrix = new HashMap<Integer, String>();
                System.out.println("The matrix-type labels of the chosen matrix are: ");

                for(int m=0; m<matrices_list.get(index_1).get_types().size(); m++){
                    System.out.println(m+") "+matrices_list.get(index_1).get_types().get(m));
                    types_of_matrix.put(m, matrices_list.get(index_1).get_types().get(m));
                }

                System.out.print("Enter the index of the matrix-type you want fixed: ");
                int index_2 = matrices.nextInt();

                while(2!=13){
                    System.out.print("Enter the row number of the element in the matrix to be updated: ");
                    int row_no = matrices.nextInt();
                    System.out.print("Enter the column number of the element in the matrix to be updated: ");
                    int column_no = matrices.nextInt();
                    System.out.print("Enter the new number which is to be updated at given location: ");
                    int new_no = matrices.nextInt();
                    
                    if(row_no<=matrices_list.get(index_1).get_no_of_rows() && column_no<=matrices_list.get(index_1).get_no_of_columns()){
                        create_matrices new_matrix = new create_matrices();
                        new_matrix.change_elements(index_1, index_2, row_no, column_no, new_no, matrices_list);
                        break;
                    }
                    else{
                        System.out.println("The given location does not exist in the chosen matrix. Try again.");
                    }
                }
                System.out.println("--------------------------------------------------");
            }

            else if(task==4){
                if(matrices_list!=null){
                    System.out.print("Enter the index of the matrix you want from the array: ");
                    int index = matrices.nextInt();

                    if(index>=0 && index<=matrices_list.size()){
                        System.out.println("The matrix chosen is "+Arrays.deepToString(matrices_list.get(index).get_matrix()));
                        matrices_list.get(index).identify_matrix_type();
                        System.out.println("The matrix-type labels of the chosen matrix are: ");
                        for(int m=0; m<matrices_list.get(index).get_types().size(); m++){
                            System.out.println(matrices_list.get(index).get_types().get(m));
                        }
                    }
                    else{
                        System.out.println("Index entered does not hold any matrix in the array.");
                    }
                }
                System.out.println("--------------------------------------------------");
            }
            else if(task==5){
                if(matrices_list.size()>=2){
                    System.out.print("Enter the index of the first matrix you want from the array: ");
                    int index_1 = matrices.nextInt();
                    System.out.print("Enter the index of the second matrix you want from the array: ");
                    int index_2 = matrices.nextInt();

                    if(index_1>=0 && index_1<=matrices_list.size() && index_2>=0 && index_2<=matrices_list.size()){
                        int[][] A = matrices_list.get(index_1).get_matrix();
                        int[][] B = matrices_list.get(index_2).get_matrix();
                            
                        System.out.println("--------------");
                        System.out.println("Choose the operation you want to run: ");
                        System.out.println("1. Addition \n2. Subtraction \n3. Multiplication \n4. Division \n5. Exit");
                        System.out.println("--------------");
                        while(2!=13){
                                
                            System.out.print("Enter the index of the operation you want to run: ");
                            int operation = matrices.nextInt();
                            operations new_matrix = new operations();

                            if(operation==1){
                                new_matrix.addition(index_1, A, index_2, B, matrices_list);
                                System.out.println("-------------------");                                
                            }

                            else if(operation==2){
                                new_matrix.subtraction(index_1, A, index_2, B, matrices_list);
                                System.out.println("-------------------");
                            }
                            else if(operation==3){
                                new_matrix.multiplication(index_1, A, index_2, B, matrices_list);
                                System.out.println("-------------------");
                            }
                            else if(operation==4){
                                new_matrix.division(index_1, A, index_2, B, matrices_list);
                                System.out.println("-------------------");
                            }
                            else if(operation==5){
                                System.out.println("Thanks for implementing the operations of matrices.");
                                System.out.println("--------------------------------------------------");
                                break;
                            }
                        }
                    }
                    else{
                        System.out.println("Either/Both of the two indices entered does not hold any matrix in the array.");
                        System.out.println("--------------------------------------------------");
                    }
                }
            }
            else if(task==6){
                if(matrices_list!=null){
                    while(2!=13){
                        System.out.print("Enter the index of the first matrix you want from the array: ");
                        int index_1 = matrices.nextInt();
                            
                        System.out.print("Enter the index of the second matrix you want from the array: ");
                        int index_2 = matrices.nextInt();

                        if(matrices_list.get(index_1).get_no_of_rows()==matrices_list.get(index_2).get_no_of_rows() && matrices_list.get(index_1).get_no_of_columns()==matrices_list.get(index_2).get_no_of_columns()){
                            int[][] element_wise_MUL = new int[matrices_list.get(index_1).get_no_of_rows()][matrices_list.get(index_1).get_no_of_columns()];
                            for (int m=0; m<matrices_list.get(index_1).get_no_of_rows(); m++){
                                for (int n=0; n<matrices_list.get(index_2).get_no_of_columns(); n++){
                                    element_wise_MUL[m][n] = (matrices_list.get(index_1).get_matrix()[m][n] * matrices_list.get(index_2).get_matrix()[m][n]);
                                }
                            }
                            System.out.println("The product of element-wise multiplication of two matrices is: "+Arrays.deepToString(element_wise_MUL));
                            break;
                        }
                        else{
                            System.out.println("The two matrices given as input don't have the same dimensions. Enter again.");
                        }

                    }
                }
                System.out.println("--------------------------------------------------");
            }
            else if(task==7){
                if(matrices_list!=null){
                    System.out.print("Enter the index of the matrix you want from the array: ");
                    int index = matrices.nextInt();

                    if(index>=0 && index<=matrices_list.size()){
                        System.out.println("The matrix chosen is "+Arrays.deepToString(matrices_list.get(index).get_matrix()));
                        System.out.println("The transpose of the chosen matrix is: "+Arrays.deepToString(matrices_list.get(index).transpose_of_matrix()));
                    }
                    else{
                        System.out.println("Index entered does not hold any matrix in the array.");
                    }
                }
                System.out.println("--------------------------------------------------");
            }
            else if(task==8){
                if(matrices_list!=null){
                    System.out.print("Enter the index of the matrix you want from the array: ");
                    int index = matrices.nextInt();

                    if(index>=0 && index<=matrices_list.size()){
                        if(matrices_list.get(index).determinant_value(matrices_list.get(index).get_matrix(), matrices_list.get(index).get_no_of_rows(), matrices_list.get(index).get_no_of_columns()) != 0 && matrices_list.get(index).get_no_of_rows()==matrices_list.get(index).get_no_of_columns()){
                            System.out.println("The matrix chosen is "+Arrays.deepToString(matrices_list.get(index).get_matrix()));
                            System.out.println("The inverse of the chosen matrix is: "+Arrays.deepToString(matrices_list.get(index).find_inverse()));
                        }
                        else{
                            System.out.println("The chosen matrix is not invertible.");
                        }
                    }
                    else{
                        System.out.println("Index entered does not hold any matrix in the array.");
                    }
                }
                System.out.println("--------------------------------------------------");
            }
            else if(task==9){
                if(matrices_list!=null){
                    System.out.print("Enter the index of the matrix you want from the array: ");
                    int index = matrices.nextInt();

                    if(index>=0 && index<=matrices_list.size()){
                        System.out.println("--------------");
                        System.out.println("Choose the operation you want to run: ");
                        System.out.println("1. Row-wise mean \n2. Column-wise mean \n3. Mean of all elements");
                        System.out.println("--------------");
                        System.out.println("The matrix chosen is "+Arrays.deepToString(matrices_list.get(index).get_matrix()));
                        System.out.print("Enter the index of the operation you want to run: ");
                        int new_index = matrices.nextInt();
                        
                        if(new_index==1){
                            System.out.print("Enter the row number you want to find the mean of: ");
                            int row_no = matrices.nextInt();
                            if(row_no>0 && row_no<=matrices_list.get(index).get_no_of_rows()){
                                float mean=0;
                                for(int m=0; m<matrices_list.get(index).get_no_of_columns(); m++){
                                    mean = mean + matrices_list.get(index).get_matrix()[row_no-1][m];
                                    if(m+1==matrices_list.get(index).get_no_of_columns()){
                                        mean = mean/(m+1);
                                        System.out.println("The Row-wise mean of row "+row_no+" is: "+mean);
                                    }
                                }
                            }
                            else{
                                System.out.println("Row number given as input is incorrect.");
                            }
                        }
                        else if(new_index==2){
                            System.out.print("Enter the column number you want to find the mean of: ");
                            int column_no = matrices.nextInt();
                            if(column_no>0 && column_no<=matrices_list.get(index).get_no_of_columns()){
                                float mean=0;
                                for(int m=0; m<matrices_list.get(index).get_no_of_rows(); m++){
                                    mean = mean + matrices_list.get(index).get_matrix()[m][column_no-1];
                                    if(m+1==matrices_list.get(index).get_no_of_rows()){
                                        mean = mean/(m+1);
                                        System.out.println("The Column-wise mean of column "+column_no+" is: "+mean);
                                    }
                                }
                            }
                            else{
                                System.out.println("Column number given as input is incorrect.");
                            }
                        }
                        else if(new_index==3){
                            float mean=0;
                            for(int m=0; m<matrices_list.get(index).get_no_of_rows(); m++){
                                for(int n=0; n<matrices_list.get(index).get_no_of_columns(); n++){
                                    mean = mean + matrices_list.get(index).get_matrix()[m][n];
                                    if(m+1==matrices_list.get(index).get_no_of_rows() && n+1==matrices_list.get(index).get_no_of_columns()){
                                        mean = mean/((m+1)*(n+1));
                                        System.out.println("The mean of all elements is: "+mean);
                                    }
                                }
                            }
                        }
                        else{
                            System.out.println("Wrong index");
                        }
                    }
                    else{
                        System.out.println("Index entered does not hold any matrix in the array.");
                    }
                }
                System.out.println("--------------------------------------------------");
            }
            else if(task==10){
                if(matrices_list!=null){
                    System.out.print("Enter the index of the matrix you want from the array: ");
                    int index = matrices.nextInt();

                    if(index>=0 && index<=matrices_list.size()){
                        System.out.println("The matrix chosen is "+Arrays.deepToString(matrices_list.get(index).get_matrix()));
                        System.out.println("The determinant of the chosen matrix is: "+matrices_list.get(index).determinant_value(matrices_list.get(index).get_matrix(), matrices_list.get(index).get_no_of_rows(), matrices_list.get(index).get_no_of_columns()));
                    }
                    else{
                        System.out.println("Index entered does not hold any matrix in the array.");
                    }
                }
                System.out.println("--------------------------------------------------");
            }
            else if(task==11){
                System.out.print("Use singleton matrix in matrix operations. Do you allow using singleton matrices as a scalar value?(yes/no) ");
                String answer = matrices.nextLine();
                if(answer.equals("yes")){
                    if(matrices_list!=null){
                        System.out.print("Enter the index of the matrix you want from the array: ");
                        int index_1 = matrices.nextInt();

                        if(index_1>=0 && index_1<=matrices_list.size()){
                            while(2!=13){
                                System.out.print("Enter the index of a singleton matrix you want from the array: ");
                                int index_2 = matrices.nextInt();
                                matrices_list.get(index_2).identify_matrix_type();
                                if(matrices_list.get(index_2).get_types().contains("Singleton Matrix") && index_1!=index_2){
                                    int[][] MUL = new int[matrices_list.get(index_1).get_no_of_rows()][matrices_list.get(index_1).get_no_of_columns()];
                                    for(int m=0; m<matrices_list.get(index_1).get_no_of_rows(); m++){
                                        for(int n=0; n<matrices_list.get(index_1).get_no_of_columns(); n++){
                                            MUL[m][n] = (matrices_list.get(index_2).get_matrix()[0][0] * matrices_list.get(index_1).get_matrix()[m][n]);
                                        }
                                    }
                                    System.out.println("The new matrix after multiplication with singleton matrix as a scalar is: "+Arrays.deepToString(MUL));
                                    break;
                                }
                                else{
                                    System.out.println("Please enter index of a SINGLETON MATRIX only.");
                                }
                            }
                        }
                        else{
                            System.out.println("Index entered does not hold any matrix in the array.");
                        }
                    }                  
                }
                System.out.println("--------------------------------------------------");
            }
            else if(task==12){
                if(matrices_list!=null){
                    System.out.print("Enter the index of the matrix you want from the array: ");
                    int index = matrices.nextInt();

                    if(index>=0 && index<=matrices_list.size()){
                        int[][] A = matrices_list.get(index).get_matrix();
                        int[][] A_transpose = matrices_list.get(index).transpose_of_matrix();
                        System.out.println("The matrix chosen is "+Arrays.deepToString(A));
                        System.out.println("The transpose of the chosen matrix is "+Arrays.deepToString(A_transpose));
                        
                        if(matrices_list.get(index).get_no_of_rows()==matrices_list.get(index).get_no_of_columns()){
                            operations new_matrix = new operations();
                            new_matrix.addition(index, A, index, A_transpose, matrices_list);
                        }
                    }
                    else{
                        System.out.println("Index entered does not hold any matrix in the array.");
                    }
                }
                System.out.println("--------------------------------------------------");
            }
            else if(task==14){
                if(matrices_list!=null){
                    while(2!=13){
                        System.out.print("Enter the index of a square matrix you want from the array: ");
                        int index_1 = matrices.nextInt();
                        matrices_list.get(index_1).identify_matrix_type();
                        if(matrices_list.get(index_1).get_types().contains("Square Matrix")){

                            while(3!=13){
                                System.out.print("Enter the index of a singleton matrix you want from the array: ");
                                int index_2 = matrices.nextInt();
                                matrices_list.get(index_2).identify_matrix_type();
                                if(matrices_list.get(index_2).get_types().contains("Column Matrix") && index_1!=index_2){

                                    if(matrices_list.get(index_2).get_no_of_rows()==matrices_list.get(index_1).get_no_of_rows()){
                                        if(matrices_list.get(index_1).determinant_value(matrices_list.get(index_1).get_matrix(), matrices_list.get(index_1).get_no_of_rows(), matrices_list.get(index_1).get_no_of_columns()) != 0){
                                            float[][] a_inverse = matrices_list.get(index_1).find_inverse();
                                            int[][] f = matrices_list.get(index_2).get_matrix();
    
                                            if(matrices_list.get(index_2).get_types().contains("Null Matrix")){
                                                int[][] DIV = new int[matrices_list.get(index_1).get_no_of_columns()][1];
                                                for (int m=0; m<matrices_list.get(index_1).get_no_of_columns(); m++){
                                                    DIV[m][0]=0;
                                                }
                                                System.out.println("The product of the two chosen matrices is: "+Arrays.deepToString(DIV));                                       
                                            }
                                            else{
                                                float[][] DIV = new float[matrices_list.get(index_1).get_no_of_columns()][1];
                                                for (int m=0; m<matrices_list.get(index_1).get_no_of_columns(); m++){
                                                    for (int p=0; p<matrices_list.get(index_2).get_no_of_rows(); p++){
                                                        DIV[m][0] = (DIV[m][0] + (a_inverse[m][p]*f[p][0]));
                                                    }                                              
                                                } 
                                                System.out.println("The product of the two chosen matrices is: "+Arrays.deepToString(DIV));
                                            }
                                        }
                                        else{
                                            System.out.println("This operation cannot be performed as the first matrix is a singular matrix.");
                                        }
                                    }
                                    else{
                                        System.out.println("This operation cannot be performed on the chosen matrices.");
                                    }
                                    break;
                                }
                                else{
                                    System.out.println("Please enter index of a COLUMN MATRIX only.");
                                }
                            }
                            break;
                        }
                        else{
                            System.out.println("Please enter index of a SQUARE MATRIX only.");
                        }
                    }
                }
                System.out.println("--------------------------------------------------");
            }
            else if(task==15){
                System.out.println("All 15 types of matrices are as follows: ");
                System.out.println("1. Rectangular Matrix \n2. Row Matrix \n3. Column Matrix \n4. Square Matrix "
                +"\n5. Symmetric Matrix \n6. Skew-symmetric Matrix \n7. Upper-triangular Matrix \n8. Lower-triangular Matrix "
                +"\n9. Singular Matrix \n10. Diagonal Matrix \n11. Scalar Matrix "
                +"\n12. Identity Matrix \n13. Singleton Matrix \n14. Ones Matrix "
                +"\n15. Null Matrix ");
                System.out.println("--------------");
                System.out.print("Enter the index of the matrix-type you want to retrieve: ");
                int index = matrices.nextInt();

                if(index==1){
                    for(int m=0; m<matrices_list.size(); m++){
                        matrices_list.get(m).identify_matrix_type();
                        if(matrices_list.get(m).get_types().contains("Rectangular Matrix")){
                            System.out.println(Arrays.deepToString(matrices_list.get(m).get_matrix()));
                        }
                    }
                }
                else if(index==2){
                    for(int m=0; m<matrices_list.size(); m++){
                        matrices_list.get(m).identify_matrix_type();
                        if(matrices_list.get(m).get_types().contains("Row Matrix")){
                            System.out.println(Arrays.deepToString(matrices_list.get(m).get_matrix()));
                        }
                    }
                }
                else if(index==3){
                    for(int m=0; m<matrices_list.size(); m++){
                        matrices_list.get(m).identify_matrix_type();
                        if(matrices_list.get(m).get_types().contains("Column Matrix")){
                            System.out.println(Arrays.deepToString(matrices_list.get(m).get_matrix()));
                        }
                    }
                }
                else if(index==4){
                    for(int m=0; m<matrices_list.size(); m++){
                        matrices_list.get(m).identify_matrix_type();
                        if(matrices_list.get(m).get_types().contains("Square Matrix")){
                            System.out.println(Arrays.deepToString(matrices_list.get(m).get_matrix()));
                        }
                    }
                }
                else if(index==5){
                    for(int m=0; m<matrices_list.size(); m++){
                        matrices_list.get(m).identify_matrix_type();
                        if(matrices_list.get(m).get_types().contains("Symmetric Matrix")){
                            System.out.println(Arrays.deepToString(matrices_list.get(m).get_matrix()));
                        }
                    }
                }
                else if(index==6){
                    for(int m=0; m<matrices_list.size(); m++){
                        matrices_list.get(m).identify_matrix_type();
                        if(matrices_list.get(m).get_types().contains("Skew-Symmetric Matrix")){
                            System.out.println(Arrays.deepToString(matrices_list.get(m).get_matrix()));
                        }
                    }
                }
                else if(index==7){
                    for(int m=0; m<matrices_list.size(); m++){
                        matrices_list.get(m).identify_matrix_type();
                        if(matrices_list.get(m).get_types().contains("Upper-Triangular Matrix")){
                            System.out.println(Arrays.deepToString(matrices_list.get(m).get_matrix()));
                        }
                    }
                }
                else if(index==8){
                    for(int m=0; m<matrices_list.size(); m++){
                        matrices_list.get(m).identify_matrix_type();
                        if(matrices_list.get(m).get_types().contains("Lower-Triangular Matrix")){
                            System.out.println(Arrays.deepToString(matrices_list.get(m).get_matrix()));
                        }
                    }
                }
                else if(index==9){
                    for(int m=0; m<matrices_list.size(); m++){
                        matrices_list.get(m).identify_matrix_type();
                        if(matrices_list.get(m).get_types().contains("Singular Matrix")){
                            System.out.println(Arrays.deepToString(matrices_list.get(m).get_matrix()));
                        }
                    }
                }
                else if(index==10){
                    for(int m=0; m<matrices_list.size(); m++){
                        matrices_list.get(m).identify_matrix_type();
                        if(matrices_list.get(m).get_types().contains("Diagonal Matrix")){
                            System.out.println(Arrays.deepToString(matrices_list.get(m).get_matrix()));
                        }
                    }
                }
                else if(index==11){
                    for(int m=0; m<matrices_list.size(); m++){
                        matrices_list.get(m).identify_matrix_type();
                        if(matrices_list.get(m).get_types().contains("Scalar Matrix")){
                            System.out.println(Arrays.deepToString(matrices_list.get(m).get_matrix()));
                        }
                    }
                }
                else if(index==12){
                    for(int m=0; m<matrices_list.size(); m++){
                        matrices_list.get(m).identify_matrix_type();
                        if(matrices_list.get(m).get_types().contains("Identity Matrix")){
                            System.out.println(Arrays.deepToString(matrices_list.get(m).get_matrix()));
                        }
                    }
                }
                else if(index==13){
                    for(int m=0; m<matrices_list.size(); m++){
                        matrices_list.get(m).identify_matrix_type();
                        if(matrices_list.get(m).get_types().contains("Singleton Matrix")){
                            System.out.println(Arrays.deepToString(matrices_list.get(m).get_matrix()));
                        }
                    }
                }
                else if(index==14){
                    for(int m=0; m<matrices_list.size(); m++){
                        matrices_list.get(m).identify_matrix_type();
                        if(matrices_list.get(m).get_types().contains("Ones Matrix")){
                            System.out.println(Arrays.deepToString(matrices_list.get(m).get_matrix()));
                        }
                    }
                }
                else if(index==15){
                    for(int m=0; m<matrices_list.size(); m++){
                        matrices_list.get(m).identify_matrix_type();
                        if(matrices_list.get(m).get_types().contains("Null Matrix")){
                            System.out.println(Arrays.deepToString(matrices_list.get(m).get_matrix()));
                        }
                    }
                }
                else{
                    System.out.println("Wrong index");
                }
                System.out.println("--------------------------------------------------");
            }
            else if(task==16){
                System.out.println("Thanks for using Matrix World");
                System.out.println("--------------------------------------------------");
                break;
            }
        }
    }
}

interface matric_basics{
    int no_of_rows=1;
    int get_no_of_rows();
    void set_no_of_rows(int no_of_rows);

    int no_of_columns=1;
    int get_no_of_columns();
    void set_no_of_columns(int no_of_columns);

    int[][] matrix = new int[no_of_columns][no_of_rows];
    int[][] get_matrix();
    void set_matrix(int[][] matrix);
    
}

class determinants{
    private int no_of_rows;
    private int no_of_columns;
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
    public int[][] get_matrix(){
        return matrix;
    }
    public void set_matrix(int[][] matrix){
        this.matrix = matrix;
    }
    

    int determinant_value(int[][] matrix, int no_of_rows, int no_of_columns){
        
        int value=-13;
        if(no_of_rows==1 && no_of_columns==1){
            value=(matrix[0][0]);
        }
        else if(no_of_rows==2 && no_of_columns==2){
            value=(matrix[0][0]*matrix[1][1] - matrix[1][0]*matrix[0][1]);
        }
        else if(no_of_rows==3 && no_of_columns==3){
            value=(matrix[0][0]*(matrix[1][1]*matrix[2][2] - matrix[1][2]*matrix[2][1]) + matrix[0][1]*(matrix[1][2]*matrix[2][0] - matrix[2][2]*matrix[1][0]) + matrix[0][2]*(matrix[1][0]*matrix[2][1] - matrix[2][0]*matrix[1][1]));
        }
        return value;
    }
}

class transpose extends determinants{
    int[][] transpose_of_matrix(){

        int transpose_matrix[][]=new int[this.get_no_of_columns()][this.get_no_of_rows()];
        for(int n=0;n<this.get_no_of_columns();n++){    
            for(int m=0;m<this.get_no_of_rows();m++){    
                transpose_matrix[n][m] = this.get_matrix()[m][n];  
            }    
        }
    return transpose_matrix;
    }
}

class inverse extends transpose{

    void calculate_cofactor(int[][] matrix, int no_of_rows, int no_of_columns, int temp[][], int u, int v){
 
        int j=0;
        int k=0;

        for (int m=0; m<get_no_of_rows(); m++){
            for (int n=0; n<get_no_of_columns(); n++){
                if (m != u && n != v){
                    temp[j][k++] = matrix[m][n];
 
                    if (k == get_no_of_rows() - 1){
                        k = 0;
                        j++;
                    }
                }           
            }
        }
    }

    int[][] find_adjoint(int[][] matrix, int no_of_rows, int no_of_columns){

        int [][] adjoint = new int[get_no_of_rows()][get_no_of_columns()];
        if (get_no_of_rows() == 1 && get_no_of_columns()==1){
            adjoint[0][0] = 1;
        }
 
        int sign = 1;
        int [][]temp = new int[get_no_of_rows()][get_no_of_columns()];
 
        for (int m=0; m<get_no_of_rows(); m++){
            for (int n=0; n<get_no_of_columns(); n++){
                calculate_cofactor(matrix, no_of_rows, no_of_columns, temp, m, n);
 
                if((m + n) % 2 == 0){
                    sign=1;
                }
                else{
                    sign=-1;
                }
 
                adjoint[n][m] = (sign)*(determinant_value(temp, (get_no_of_rows()-1), (get_no_of_columns()-1)));
            }
        }
        return adjoint;
    }
    float[][] find_inverse(){
        float det_matrix=determinant_value(this.get_matrix(), this.get_no_of_rows(), this.get_no_of_columns());
        float[][] inverse_matrix = new float[this.get_no_of_columns()][this.get_no_of_rows()];

        if(det_matrix!=0 && this.get_no_of_rows()==this.get_no_of_columns()){
            for (int m=0; m<this.get_no_of_rows(); m++){
                for (int n=0; n<this.get_no_of_columns(); n++){
                    inverse_matrix[m][n] = (float)find_adjoint(this.get_matrix(), this.get_no_of_rows(), this.get_no_of_columns())[m][n] / det_matrix;

                }
            }
        }
        return inverse_matrix;
    }
}

class matrix_types extends inverse{
    private ArrayList<String> types = new ArrayList<String>();


    private int symmetric_matrix(int A[][], int B[][]){

        int type=0;
        for (int m=0; m<this.get_no_of_rows(); m++){
            for (int n=0; n<this.get_no_of_columns(); n++){
                if (A[m][n] != B[m][n]){
                    type=1;
                }
            }
        }
        return type;
    }

    private int skew_symmetric_matrix(int A[][], int B[][]){
    
        int type=0;
        for(int m=0; m<this.get_no_of_rows(); m++){
            for(int n=0; n<this.get_no_of_columns(); n++){
                if(A[m][n] != -B[m][n]){
                    type=1;
                }
            }
        }
        return type;
    }

    private int upper_triangular_matrices(){

        int type=1;
        for(int m=0; m<this.get_no_of_rows(); m++){
            for(int n=0; n<this.get_no_of_columns(); n++){
                if(m<=n){
                    if(this.get_matrix()[m][n]==0){
                        type=0;
                    }
                }
                if(m>n){
                    if(this.get_matrix()[m][n]!=0){
                        type=0;
                    }
                }
            }
        }
        return type;
    }

    private int lower_triangular_matrices(){

        int type=1;
        for(int m=0; m<this.get_no_of_rows(); m++){
            for(int n=0; n<this.get_no_of_columns(); n++){
                if(m>=n){
                    if(this.get_matrix()[m][n]==0){
                        type=0;
                    }
                }
                if(m<n){
                    if(this.get_matrix()[m][n]!=0){
                        type=0;
                    }
                }
            }
        }
        return type;
    }

    private HashMap<String, Integer> diagonal_type_matrices(){

        int type_1=0;
        int type_2=0;
        HashMap<String, Integer> types = new HashMap<String, Integer>();
        for(int m=0; m<this.get_no_of_rows(); m++){
            for(int n=0; n<this.get_no_of_columns(); n++){
                if(m!=n && this.get_matrix()[m][n]!=0){
                    type_1=1;
                    break;
                }
                if(m==n && (this.get_matrix()[m][n]==0 || this.get_matrix()[m][n]!=this.get_matrix()[0][0])){

                    type_2=1;
                    break;
                }
            }
        }
        types.put("type 1", type_1);
        types.put("type 2", type_2);
        return types;
    }

    private int ones_matrices(){

        int type=0;
        first_loop:
        for(int m=0; m<this.get_no_of_rows(); m++){
            second_loop:
            for(int n=0; n<this.get_no_of_columns(); n++){
                if(this.get_matrix()[m][n]==1){
                    type=1;
                }
                else{
                    type=0;
                    break first_loop;
                }
            }
        }
        return type;
    }

    private int null_matrices(){

        int type=1;
        first_loop:
        for(int m=0; m<this.get_no_of_rows(); m++){
            second_loop:
            for(int n=0; n<this.get_no_of_columns(); n++){
                if(this.get_matrix()[m][n]==0){
                    type=0;
                }
                else{
                    type=1;
                    break first_loop;
                }
            }
        }
        return type;
    }

    public void identify_matrix_type(){
             
            if(this.get_no_of_columns() != this.get_no_of_rows()){
                types.add("Rectangular Matrix");

                if(this.get_no_of_rows() == 1){                
                    types.add("Row Matrix");            
                }           
                else if(this.get_no_of_columns() == 1){              
                    types.add("Column Matrix");
                }
            }
            else if(this.get_no_of_columns() == this.get_no_of_rows()){
                types.add("Square Matrix");

                if(this.get_no_of_rows() == 1 && this.get_no_of_columns()==1){
                    types.add("Singleton Matrix");
                }
                if(this.symmetric_matrix(this.get_matrix(), this.transpose_of_matrix())==0){
                    types.add("Symmetric Matrix");
                }
                if(this.skew_symmetric_matrix(this.get_matrix(), this.transpose_of_matrix())==0){
                    types.add("Skew-Symmetric Matrix");
                }
                if(this.upper_triangular_matrices()==1){
                    types.add("Upper-Triangular Matrix");
                }
                if(this.lower_triangular_matrices()==1){
                    types.add("Lower-Triangular Matrix");
                }
                if(this.determinant_value(this.get_matrix(), this.get_no_of_rows(), this.get_no_of_columns())==0){
                    types.add("Singular Matrix");
                }
                if(this.diagonal_type_matrices().get("type 1")==0){
                    types.add("Diagonal Matrix");

                    if(this.diagonal_type_matrices().get("type 2")==0){
                        types.add("Scalar Matrix");

                        if(this.get_matrix()[0][0]==1){
                            types.add("Identity Matrix");
                        }
                    }
                }
            }
            if(this.ones_matrices()==1){
                types.add("Ones Matrix");
            }
            if(this.null_matrices()==0){
                types.add("Null Matrix");
            }
    }
    public ArrayList<String> get_types(){
        return types;
    }
}

class operations{
    void addition(int index_1, int[][] A, int index_2, int[][] B, ArrayList<matrix_types> matrices_list){
        if(matrices_list.get(index_1).get_no_of_rows()==matrices_list.get(index_2).get_no_of_rows() && matrices_list.get(index_1).get_no_of_columns()==matrices_list.get(index_2).get_no_of_columns()){
                                    
            ArrayList<ArrayList<Integer>> ADD = new ArrayList<ArrayList<Integer>>();
            for(int m=0; m<matrices_list.get(index_1).get_no_of_rows(); m++){
                ArrayList<Integer> ADD_new = new ArrayList<Integer>();
                for(int n=0; n<matrices_list.get(index_1).get_no_of_columns(); n++){
                    ADD_new.add(A[m][n] + B[m][n]);
                    if(ADD_new.size()==matrices_list.get(index_1).get_no_of_columns()){
                        ADD.add(ADD_new);
                    }
                }
            }
            System.out.println("The sum of the two chosen matrices is: "+ADD);
        }
        else{
            System.out.println("These operations cannot be performed on the chosen matrices.");
        }
    }
    void subtraction(int index_1, int[][] A, int index_2, int[][] B, ArrayList<matrix_types> matrices_list){
        if(matrices_list.get(index_1).get_no_of_rows()==matrices_list.get(index_2).get_no_of_rows() && matrices_list.get(index_1).get_no_of_columns()==matrices_list.get(index_2).get_no_of_columns()){
                                    
            ArrayList<ArrayList<Integer>> SUB = new ArrayList<ArrayList<Integer>>();
            for(int m=0; m<matrices_list.get(index_1).get_no_of_rows(); m++){
                ArrayList<Integer> SUB_new = new ArrayList<Integer>();
                for(int n=0; n<matrices_list.get(index_1).get_no_of_columns(); n++){
                    SUB_new.add(A[m][n] - B[m][n]);
                    if(SUB_new.size()==matrices_list.get(index_1).get_no_of_columns()){
                        SUB.add(SUB_new);
                    }
                }
            }
            System.out.println("The difference between the two chosen matrices is: "+SUB);
        }
        else{
            System.out.println("This operation cannot be performed on the chosen matrices.");
        }
    }
    void multiplication(int index_1, int[][] A, int index_2, int[][] B, ArrayList<matrix_types> matrices_list){
        if(matrices_list.get(index_2).get_no_of_rows()==matrices_list.get(index_1).get_no_of_columns()){
            if(matrices_list.get(index_1).get_types().contains("Null Matrix") || matrices_list.get(index_2).get_types().contains("Null Matrix")){
                int[][] MUL = new int[matrices_list.get(index_1).get_no_of_rows()][matrices_list.get(index_2).get_no_of_columns()];
                for(int m=0; m<matrices_list.get(index_1).get_no_of_rows(); m++){
                    for(int n=0; n<matrices_list.get(index_2).get_no_of_columns(); n++){
                        MUL[m][n]=0;
                    }
                }
                System.out.println("The product of the two chosen matrices is: "+Arrays.deepToString(MUL));
                
            }
            else if(matrices_list.get(index_1).get_types().contains("Identity Matrix") || matrices_list.get(index_2).get_types().contains("Identity Matrix")){
                if(!matrices_list.get(index_1).get_types().contains("Identity Matrix")){
                    System.out.println("The product of the two chosen matrices is: "+Arrays.deepToString(A));
                }
                else if(!matrices_list.get(index_2).get_types().contains("Identity Matrix")){
                    System.out.println("The product of the two chosen matrices is: "+Arrays.deepToString(B));
                }
                else{
                    System.out.println("The product of the two chosen matrices is: "+Arrays.deepToString(A));
                }
            }
            else{
                int[][] MUL = new int[matrices_list.get(index_1).get_no_of_rows()][matrices_list.get(index_2).get_no_of_columns()];
                for(int m=0; m<matrices_list.get(index_1).get_no_of_rows(); m++){
                    for(int n=0; n<matrices_list.get(index_2).get_no_of_columns(); n++){
                        for(int p=0; p<matrices_list.get(index_2).get_no_of_rows(); p++){
                            MUL[m][n] = MUL[m][n] + (A[m][p]*B[p][n]);
                        }                                              
                    }
                } 
                System.out.println("The product of the two chosen matrices is: "+Arrays.deepToString(MUL)
                );
            }
        }
        else{
            System.out.println("This operation cannot be performed on the chosen matrices.");
        }

    }
    void division(int index_1, int[][] A, int index_2, int[][] B, ArrayList<matrix_types> matrices_list){
        if(matrices_list.get(index_2).get_no_of_columns()==matrices_list.get(index_1).get_no_of_columns() && matrices_list.get(index_2).get_no_of_rows()==matrices_list.get(index_2).get_no_of_columns()){
            if(matrices_list.get(index_2).determinant_value(matrices_list.get(index_2).get_matrix(), matrices_list.get(index_2).get_no_of_rows(), matrices_list.get(index_2).get_no_of_columns()) != 0){
                float[][] B_inverse = matrices_list.get(index_2).find_inverse();

                if(matrices_list.get(index_1).get_types().contains("Null Matrix")){
                    int[][] DIV = new int[matrices_list.get(index_1).get_no_of_rows()][matrices_list.get(index_2).get_no_of_rows()];
                    for(int m=0; m<matrices_list.get(index_1).get_no_of_rows(); m++){
                        for(int n=0; n<matrices_list.get(index_2).get_no_of_rows(); n++){
                            DIV[m][n]=0;
                        }
                    }
                    System.out.println("The product of the two chosen matrices is: "+Arrays.deepToString(DIV));                                        
                }

                else if(matrices_list.get(index_1).get_types().contains("Identity Matrix")){
                    System.out.println("The product of the two chosen matrices is: "+Arrays.deepToString(B_inverse));
        
                }
                else{
                    float[][] DIV = new float[matrices_list.get(index_1).get_no_of_rows()][matrices_list.get(index_2).get_no_of_rows()];
                    for(int m=0; m<matrices_list.get(index_1).get_no_of_rows(); m++){
                        for(int n=0; n<matrices_list.get(index_2).get_no_of_rows(); n++){
                            for(int p=0; p<matrices_list.get(index_2).get_no_of_columns(); p++){
                                DIV[m][n] = (DIV[m][n] + (A[m][p]*B_inverse[p][n]));
                            }                                              
                        }
                    } 
                    System.out.println("The product of the two chosen matrices is: "+Arrays.deepToString(DIV));
                }
            }
            else{
                System.out.println("This operation cannot be performed as the second matrix is a singular matrix.");
            }
        }
        else{
            System.out.println("This operation cannot be performed on the chosen matrices.");
        }
    }
}

class create_matrices{

    void matrix_of_matrix_type(int index, ArrayList<matrix_types> matrices_list){
        if(index==1){
            int matrix[][]={{1, 2}, {4, 5}, {7, 8}};
            System.out.println("Rectangular matrix is "+Arrays.deepToString(matrix));
            matrix_types new_matrix = new matrix_types();
            new_matrix.set_matrix(matrix);
            new_matrix.set_no_of_rows(3);
            new_matrix.set_no_of_columns(2);
            matrices_list.add(new_matrix);
        }
        else if(index==2){
            int matrix[][]={{1, 2, 3}};
            System.out.println("Row matrix is "+Arrays.deepToString(matrix));
            matrix_types new_matrix = new matrix_types();
            new_matrix.set_matrix(matrix);
            new_matrix.set_no_of_rows(1);
            new_matrix.set_no_of_columns(3);
            matrices_list.add(new_matrix);
        }
        else if(index==3){
            int matrix[][]={{0}, {9}};
            System.out.println("Column matrix is "+Arrays.deepToString(matrix));
            matrix_types new_matrix = new matrix_types();
            new_matrix.set_matrix(matrix);
            new_matrix.set_no_of_rows(2);
            new_matrix.set_no_of_columns(1);
            matrices_list.add(new_matrix);
        }
        else if(index==4){
            int matrix[][]={{90, 0, 1}, {10, 100, 15}, {13, 7, 32}};
            System.out.println("Square matrix is "+Arrays.deepToString(matrix));
            matrix_types new_matrix = new matrix_types();
            new_matrix.set_matrix(matrix);
            new_matrix.set_no_of_rows(3);
            new_matrix.set_no_of_columns(3);
            matrices_list.add(new_matrix);
        }
        else if(index==5){
            int matrix[][]={{0, 21, 19}, {21, 9, 27}, {19, 27, 11}};
            System.out.println("Symmetric matrix is "+Arrays.deepToString(matrix));
            matrix_types new_matrix = new matrix_types();
            new_matrix.set_matrix(matrix);
            new_matrix.set_no_of_rows(3);
            new_matrix.set_no_of_columns(3);
            matrices_list.add(new_matrix);
        }
        else if(index==6){
            int matrix[][]={{0, -21, 0}, {21, 0, 27}, {0, -27, 0}};
            System.out.println("Skew-Symmetric matrix is "+Arrays.deepToString(matrix));
            matrix_types new_matrix = new matrix_types();
            new_matrix.set_matrix(matrix);
            new_matrix.set_no_of_rows(3);
            new_matrix.set_no_of_columns(3);
            matrices_list.add(new_matrix);
        }
        else if(index==7){
            int matrix[][]={{41, 29}, {0, 11}};
            System.out.println("Upper-triangular matrix is "+Arrays.deepToString(matrix));
            matrix_types new_matrix = new matrix_types();
            new_matrix.set_matrix(matrix);
            new_matrix.set_no_of_rows(2);
            new_matrix.set_no_of_columns(2);
            matrices_list.add(new_matrix);
        }
        else if(index==8){
            int matrix[][]={{1, 0, 0}, {7, 2, 0}, {7, 11, 3}};
            System.out.println("Lower-triangular matrix is "+Arrays.deepToString(matrix));
            matrix_types new_matrix = new matrix_types();
            new_matrix.set_matrix(matrix);
            new_matrix.set_no_of_rows(3);
            new_matrix.set_no_of_columns(3);
            matrices_list.add(new_matrix);
        }
        else if(index==9){
            int matrix[][]={{0, 1, 0}, {8, 10, 2}, {16, 23, 4}};
            System.out.println("Singular matrix is "+Arrays.deepToString(matrix));
            matrix_types new_matrix = new matrix_types();
            new_matrix.set_matrix(matrix);
            new_matrix.set_no_of_rows(3);
            new_matrix.set_no_of_columns(3);
            matrices_list.add(new_matrix);
        }
        else if(index==10){
            int matrix[][]={{69, 0}, {0, 96}};
            System.out.println("Diagonal matrix is "+Arrays.deepToString(matrix));
            matrix_types new_matrix = new matrix_types();
            new_matrix.set_matrix(matrix);
            new_matrix.set_no_of_rows(2);
            new_matrix.set_no_of_columns(2);
            matrices_list.add(new_matrix);
        }
        else if(index==11){
            int matrix[][]={{11, 0, 0}, {0, 11, 0}, {0, 0, 11}};
            System.out.println("Scalar matrix is "+Arrays.deepToString(matrix));
            matrix_types new_matrix = new matrix_types();
            new_matrix.set_matrix(matrix);
            new_matrix.set_no_of_rows(3);
            new_matrix.set_no_of_columns(3);
            matrices_list.add(new_matrix);
        }
        else if(index==12){
            int matrix[][]={{1, 0}, {0, 1}};
            System.out.println("Identity matrix is "+Arrays.deepToString(matrix));
            matrix_types new_matrix = new matrix_types();
            new_matrix.set_matrix(matrix);
            new_matrix.set_no_of_rows(2);
            new_matrix.set_no_of_columns(2);
            matrices_list.add(new_matrix);
        }
        else if(index==13){
            int matrix[][]={{21}};
            System.out.println("Singleton matrix is "+Arrays.deepToString(matrix));
            matrix_types new_matrix = new matrix_types();
            new_matrix.set_matrix(matrix);
            new_matrix.set_no_of_rows(1);
            new_matrix.set_no_of_columns(1);
            matrices_list.add(new_matrix);
        }
        else if(index==14){
            int matrix[][]={{1, 1}, {1, 1}, {1, 1}};
            System.out.println("Ones matrix is "+Arrays.deepToString(matrix));
            matrix_types new_matrix = new matrix_types();
            new_matrix.set_matrix(matrix);
            new_matrix.set_no_of_rows(3);
            new_matrix.set_no_of_columns(2);
            matrices_list.add(new_matrix);
        }
        else if(index==15){
            int matrix[][]={{0}, {0}, {0}};
            System.out.println("Null matrix is "+Arrays.deepToString(matrix));
            matrix_types new_matrix = new matrix_types();
            new_matrix.set_matrix(matrix);
            new_matrix.set_no_of_rows(3);
            new_matrix.set_no_of_columns(1);
            matrices_list.add(new_matrix); 
        }
        else{
            System.out.println("Wrong index");
        }
    }
    void change_elements(int index_1, int index_2, int row_no, int column_no, int new_no, ArrayList<matrix_types> matrices_list){

        matrices_list.get(index_1).identify_matrix_type();
        if(matrices_list.get(index_1).get_types().get(index_2).equals("Square Matrix") || matrices_list.get(index_1).get_types().get(index_2).equals("Rectangular Matrix") || matrices_list.get(index_1).get_types().get(index_2).equals("Row Matrix") || matrices_list.get(index_1).get_types().get(index_2).equals("Column Matrix")){
            matrices_list.get(index_1).get_matrix()[row_no-1][column_no-1]=new_no;
            System.out.println("The new matrix after updation is "+Arrays.deepToString(matrices_list.get(index_1).get_matrix()));
        }
        else if(matrices_list.get(index_1).get_types().get(index_2).equals("Symmetric Matrix") || matrices_list.get(index_1).get_types().get(index_2).equals("Diagonal Matrix")){
            if(row_no==column_no){
                matrices_list.get(index_1).get_matrix()[row_no-1][column_no-1]=new_no;
                System.out.println("The new matrix after updation is "+Arrays.deepToString(matrices_list.get(index_1).get_matrix()));
            }
            else{
                System.out.println("The requested location of the matrix cannot be updated with the given new number.");
            }
        }
        else if(matrices_list.get(index_1).get_types().get(index_2).equals("Upper-Triangular Matrix")){
            if(column_no>=row_no){
                matrices_list.get(index_1).get_matrix()[row_no-1][column_no-1]=new_no;
                System.out.println("The new matrix after updation is "+Arrays.deepToString(matrices_list.get(index_1).get_matrix()));
            }
            else{
                System.out.println("The requested location of the matrix cannot be updated with the given new number.");
            }
        }
        else if(matrices_list.get(index_1).get_types().get(index_2).equals("Lower-Triangular Matrix")){
            if(column_no<=row_no){
                matrices_list.get(index_1).get_matrix()[row_no-1][column_no-1]=new_no;
                System.out.println("The new matrix after updation is "+Arrays.deepToString(matrices_list.get(index_1).get_matrix()));
            }
            else{
                System.out.println("The requested location of the matrix cannot be updated with the given new number.");
            }
        }
        else{
            System.out.println("The fixed matrix-type of the chosen matrix does not allow matrix-updation.");
        }
    }
}