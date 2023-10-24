import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.File;
import java.io.File.*;

public class sol_eq_rotate {
    public sol_eq_rotate() {

    }
    
    
    public static void main(String[] args) throws IOException {
        double c = 0.13; // скорость переноса
        double[] x_init = {0, 1}; int N = 100; // Начальная координата, конечная координата и число точек разбиения по пространству
        double[] t_init = {0, 0.1}; int M = 100; // Начальное время, конечное время и число точек разбиения по времени
        double h = (x_init[1] - x_init[0]) / (N - 1); // Шаг по пространству
        double tau = (t_init[1] - t_init[0]) / (M - 1); // Шаг по времени
        double[][] u = new double[M][N];
        
        // u[0] = linspace(0, 1, N); 
        // System.out.println(Arrays.deepToString(u));

        for (int x = 0; x < N; x++) {
            u[0][x] = initial_conditions(x * h);
            // System.out.println(boundary_conditions(x * h));
        }

        double predictor = 0.0;
        double prev_predictor = 0.0;
        for (int t = 0; t < M - 1; t++) {
            for (int x = 1; x < N - 1; x++) {
                predictor = u[t][x] - c * tau / h * (u[t][x + 1] - u[t][x]); // Предиктор
                prev_predictor = u[t][x - 1] - c * tau / h * (u[t][x] - u[t][x - 1]);
                u[t + 1][x] = 0.5 * (u[t][x] + predictor - c * tau / h * (predictor - prev_predictor));
            }
        }


        for(double[] t : u) {
            for(double x : t) {
                System.out.print(x + "\t");
            }
            System.out.println();
        }
        // System.out.println(Arrays.toString(linspace(0, 1, N)));
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < M; i++)//for each row
        {
           for(int j = 0; j < N; j++)//for each column
           {
              builder.append(u[i][j]+"");//append to the output string
              if(j < N - 1)//if this is not the last row element
                 builder.append(" ");//then add comma (if you don't like commas you can use spaces)
           }
           builder.append("\n");//append new line at the end of the row
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("results" + ".txt"));
        writer.write(builder.toString());//save the string representation of the board
        writer.close();
        
    }

    private static double initial_conditions(double x) {
        if ((x >= 0 && x < 0.1) || (x >= 0.4 && x <= 1.0)) {
            return 0;
        }
        else if (x >= 0.1 && x < 0.2) {
            return 10 * (x - 0.1);
        }
        else if (x >= 0.2 && x < 0.3) {
            return 1;
        }
        else if (x >= 0.3 && x < 0.4) {
            return -10 * (x - 0.4);
        }
        else {
            System.out.println("Out of interval");
            return Integer.MAX_VALUE;
        }
    }

    private void maccormack_scheme(double[][] u) {
        for(int i = 1; i < u.length; ++i)
            for(int n = 1; n < u[i].length; ++n) {
                
            }
    }

    public static double[] linspace(double min, double max, int points) {  
        double[] d = new double[points];  
        for (int i = 0; i < points; i++){  
            d[i] = min + i * (max - min) / (points - 1);  
        }  
        return d;  
    }


}