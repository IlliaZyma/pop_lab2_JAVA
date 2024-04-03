public class ThreadSum extends Thread{
    private final int startIndex;
    private final int finishIndex;
    private final int[] smallest_num = new int[2];
    private final ArrClass arrClass;

    public ThreadSum(int startIndex, int finishIndex, ArrClass arrClass) {
        this.startIndex = startIndex;
        this.finishIndex = finishIndex;
        this.arrClass = arrClass;
    }

    @Override
    public void run() {
        int[] min_num = arrClass.get_min_Num(startIndex, finishIndex);
        arrClass.collectNum(smallest_num, min_num);
        arrClass.incThreadCount();
    }
}
