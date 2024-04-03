import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        int dim = 10000000;
        System.out.println("Enter num of thread");
        Scanner scanner = new Scanner(System.in);
        int threadNum = scanner.nextInt();
        ArrClass arrClass = new ArrClass(dim, threadNum);
        arrClass.get_min_Num(0,dim);

        arrClass.threadNum();
    }
}
