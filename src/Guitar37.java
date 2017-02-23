/**
 * Implement your code here
 * Change commments
 * @Author  Thelma Andrews
 * @Date  2/22/17
 * @Course CSC526
 * @Assignment Homework3
 *
 */
public class Guitar37 implements Guitar {
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
    int timerticcount=0;
    int arrayindexnumber=0;
    int totalguitarcount=37;
    private GuitarString[] arrayofguitarstringobjects=new GuitarString[totalguitarcount];
    double[] chromaticfrequenciesvalues = {110.00, 116.00, 123.00, 130.00, 138.00, 146.00, 155.00, 164.00,
                                 174.00, 184.00, 195.00, 207.00, 220.00, 233.00, 246.00, 261.00,
                                 277.00, 293.00, 311.00, 329.00, 349.00, 369.00, 391.00, 415.00,
                                 440.00, 466.00, 493.00, 523.00, 554.00, 587.00, 622.00, 659.00,
                                 698.00, 739.00, 783.00, 830.00, 880.00};
    public Guitar37(boolean useKP) {
        timerticcount=0;
        arrayindexnumber=0;
        for(;arrayindexnumber<totalguitarcount;arrayindexnumber++) {
            double chromaticvalue=chromaticfrequenciesvalues[arrayindexnumber];
            if(useKP==false){
                arrayofguitarstringobjects[arrayindexnumber] = new SimpleGuitarString(chromaticvalue);
            }else{
                arrayofguitarstringobjects[arrayindexnumber] = new KPGuitarString(chromaticvalue);
            }
        }
    }
    @Override
    public void playNote(int pitch){
        try{
            arrayindexnumber=pitch+12;
            arrayofguitarstringobjects[arrayindexnumber].pluck();
        }catch(Exception e){}
    }
    @Override
    public boolean hasString(char key) {
        boolean isstringvalue=KEYBOARD.indexOf(key)!=-1;
        return isstringvalue;
    }
    @Override
    public void pluck(char key) {
        if(hasString(key)) {
            arrayindexnumber=KEYBOARD.indexOf(key);
            arrayofguitarstringobjects[arrayindexnumber].pluck();
        }
    }
    @Override
    public double sample() {
        double totalsample=0;
        arrayindexnumber=0;
        for(;arrayindexnumber<totalguitarcount;arrayindexnumber++) {
            totalsample+=arrayofguitarstringobjects[arrayindexnumber].sample();
        }
        return totalsample;
    }
    @Override
    public void tic() {
        for(int arrayIndexPointer=0;arrayIndexPointer<37;arrayIndexPointer++){
            arrayofguitarstringobjects[arrayIndexPointer].tic();
        }
        timerticcount=timerticcount+1;
    }
    @Override
    public int time() {
        return timerticcount;
    }
}