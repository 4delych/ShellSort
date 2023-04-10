import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws DbException{
        
        try {
            InputStream stream = new FileInputStream("src/numbers.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = reader.readLine()) != null){
                String[] fields = line.split(" ");
                ArrayList<Integer> arr = new ArrayList<>();
                for (int i = 0; i < fields.length; i++){
                    arr.add(Integer.parseInt(fields[i]));
                }
                int iterations = iter(arr.size(), arr);

                long startTime = System.nanoTime();
                shellSort(arr);
                long endTime = System.nanoTime();

                System.out.print("отсортированный массив:");
                for(int j =0; j < arr.size(); j++){
                    System.out.print(" " + arr.get(j));
                }

                System.out.println("");
                System.out.print("Время работы: ");
                System.out.print((double)(endTime - startTime)/1000000 + " миллисекунд");
                System.out.println("");
                System.out.println("Количество итераций: " + iterations);
            }

        } catch(IOException e){
            throw new DbException(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e){
            throw new DbException("wrong");
        }




    }
    public static void shellSort(ArrayList<Integer> arr) {
        int n = arr.size();
        for (int gap = n/2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i ++) {
                int temp = arr.get(i);
                int j;
                for (j = i; j >= gap && arr.get(j - gap) > temp; j -= gap) {
                    arr.set(j,arr.get(j - gap));
                }
                arr.set(j,temp);
            }
        }
    }

    public static int iter(int n, ArrayList<Integer> arr){
        int iterations=0;
        for (int gap = n/2; gap > 0; gap /= 2) {
            iterations++;
            for (int i = gap; i < n; i ++) {
                iterations++;
                int temp = arr.get(i);
                int j;
                for (j = i; j >= gap && arr.get(j - gap) > temp; j -= gap) {
                    iterations++;
                }
            }
        }
        return iterations;
    }
}