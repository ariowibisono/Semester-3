
/**
 * Write a description of class Waktu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Waktu
{
    // instance variables - replace the example below with your own
    private int jam;
    private int menit;
    private int detik;

    /**
     * Constructor for objects of class Waktu
     */
    public Waktu()
    {
        // initialise instance variables
        jam=0;
        menit=0;
        detik=0;
    }
    
    public Waktu (int jam, int menit, int detik){
        this.jam=jam;
        this.menit=menit;
        this.detik=detik;
    }
    
    public void setJam(int jam){
        if ((jam<0)||(jam>23)){
            System.err.print("Metode 24 jam, tidak boleh < 0 atau > 23");
            this.jam=0;
        } else {
            this.jam=jam;
        }
    }
    
    public void setMenit(int menit){
        if ((menit<0)||(detik>59)){
            System.out.print("Menit tidak boleh > 59 atau < 0");
            this.menit=0;
        } else {
            this.menit=menit;
        }
    }
    
    public void setDetik(int detik){
        if ((detik<0)||(detik>59)){
            System.err.print("Detik tidak boleh > 59 atau < 0");
            this.detik=0;
        } else {
            this.detik=detik;
        }
    }
    
    public int getJam(){
        return jam;
    }
    
    public int getMenit(){
        return menit;
    }
    
    public int getDetik(){
        return detik;
    }
    
    public void jamUp(){
        if ((jam>0) || (jam <23)){
            jam++;
        }
    }
    
    public void jamDown(){
        if ((jam>0) || (jam <23)){
            jam--;
        }
    }
    
    public void menitUp(){
        if ((menit>0) || (menit <60)){
            menit++;
        }
    }
    
    public void menitDown(){
        if ((menit>0) || (menit <60)){
            menit--;
        }
    }
    
        public void detikUp(){
        if ((detik>0) || (detik <60)){
            detik++;
        }
    }
    
    public void detikDown(){
        if ((detik>0) || (detik <60)){
            detik--;
        }
    }
    //memunculkan waktu 00:00:00
    public String toString(){
        return jam+":"+menit+":"+detik;
    }
    //selisih waktu
    public Waktu selisih(Waktu waktu2){
        return toWaktu(toDetik()-waktu2.toDetik());
    }
    
    //method untuk mengubah jam ke detik
    private long toDetik (){             
        return detik+(60*menit)+(60*60*jam);
    }
    //method untuk mengubah detik ke waktu
    public Waktu toWaktu(long detik){
        int j=(int)detik/3600;
        int m=(int)(detik%3600)/60;
        int d=(int)detik%60;
        return new Waktu(j,m,d);
    }
}