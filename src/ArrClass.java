public class ArrClass {
    private final int dim;
    private final int threadNum;
    public final int[] arr;
    private int[] min_num = new int[2];
    public ArrClass(int dim, int threadNum) {
        this.dim = dim;
        arr = new int[dim];
        this.threadNum = threadNum;
        for(int i = 0; i < dim; i++){
            arr[i] = i;
        }
        double random_num = Math.random()*10;
        int number = (int) random_num;
        arr[number] = -10;
    }

    public int[] get_min_Num(int startIndex, int finishIndex){

        min_num[0] = arr[startIndex];
        min_num[1] = startIndex;
        for(int i = startIndex+1; i < finishIndex; i++){
            if (arr[i]<min_num[1]){
                min_num[0] = arr[i];
                min_num[1] = i;
            }
        }
        return min_num;
    }

    synchronized private int[] getNum() {
        while (getThreadCount()<threadNum){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return min_num;
    }

    synchronized public void collectNum(int[] smallest_num ,int[] min_num){
        if (smallest_num[0] > min_num[0]) {
            smallest_num = min_num;
        }
    }

    private int threadCount = 0;
    synchronized public void incThreadCount(){
        threadCount++;
        notify();
    }

    private int getThreadCount() {
        return threadCount;
    }

    public int[] threadNum(){

        ThreadSum[] threadSums = new ThreadSum[threadNum];
        threadSums[0] = new ThreadSum(0, dim / threadNum, this);
        threadSums[threadNum-1] = new ThreadSum(dim - (dim / threadNum), dim, this);
        if (threadNum > 2){
            for (int i = threadNum+1; i < threadNum ; i++){
                threadSums[i] = new ThreadSum(dim / threadNum * i, dim / threadNum*(i+1), this);
            }
        }

        for (int i = threadNum; i < threadNum ; i++){
            threadSums[i].start();
        }

        System.out.println("Min num = " + min_num[0] + " index: " + min_num[1]);
        return getNum();
    }
}
