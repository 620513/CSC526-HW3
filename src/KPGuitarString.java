/**
 * Implements your code here.
 * Change comments
 * @Author Thelma Andrews
 * @Date 2/22/17
 * @Course CSC526
 * @Assignment Homework3
 */
public class KPGuitarString implements GuitarString{
    static int audiosamplerate=44100;
    public GuitarQueue queue;
    public int ticCalledCounter;
    public KPGuitarString(double frequency){
        int capacity_numbers=(int) Math.round((audiosamplerate/frequency));
        queue = new GuitarQueue(capacity_numbers);
        int number=0;
        while(number<capacity_numbers){
            queue.enqueue(0);
            number++;
        }
        this.ticCalledCounter = 0;
    }
    public KPGuitarString(double[] frequency){
        queue=new GuitarQueue(frequency.length);
        int number=0;
        while(number<frequency.length){
            queue.enqueue(frequency[number]);
            number++;
        }
    }
    @Override
    public void pluck(){
        int number=0;
        while(number<queue.queuenumbers.length){
            queue.dequeue();
            double randomValue=(Math.random()-0.5);
            queue.enqueue(randomValue);
            number++;
        }
    }
    @Override
    public void tic(){
        double value=queue.dequeue();
        double value1 = queue.peek();
        queue.enqueue(((value+value1)*0.5)*.996);
        this.ticCalledCounter++;
    }
    @Override
    public double sample(){
        return queue.peek();
    }
}
class GuitarQueue {
    int queuezise;
    double[] queuenumbers;
    int start;
    public GuitarQueue(int capacity){
        queuenumbers=new double[capacity];
        queuezise=0;
        start=0;
    }
    public int size(){
        return queuezise;
    }
    public boolean isEmpty(){
        return (queuezise<=0);
    }
    public boolean isFull(){
        return (queuezise>=queuenumbers.length);
    }
    public void enqueue(double numElements) throws RuntimeException {
        if(isFull()){
            throw new RuntimeException("Now Queue is full it has no space available !!!");
        }
        queuenumbers[(start+queuezise)%queuenumbers.length]=numElements;
        queuezise++;
    }
    public double dequeue() throws RuntimeException{
        double popValue = peek();
        start=(start+1)%queuenumbers.length;
        queuezise--;
        return popValue;
    }
    double peek() throws RuntimeException{
        if(isEmpty()){
            throw new RuntimeException("Now Queue is empty");
        }
        return (queuenumbers[start]);
    }
}