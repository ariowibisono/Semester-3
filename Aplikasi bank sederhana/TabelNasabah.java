package Coba;

import java.util.Vector;
/**
 * Write a description of class TabelNasabah here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TabelNasabah
{
    private Vector<Nasabah> daftarNasabah = new Vector<Nasabah>();
   
    public TabelNasabah()
    {
        // data nasabah default
        tambah(new Nasabah("James","1234","Kutisari","1234",10000));
        tambah(new Nasabah("Kimcil","1235","sana","1234",20000));
        tambah(new Nasabah("Ken","1236","situ","1234",30000));
        tambah(new Nasabah("Bos","1237","manyar","1234",40000));
        tambah(new Nasabah("Jiin","1238","kedung","1234",50000));
    }

    public void tambah(Nasabah nBaru){
        daftarNasabah.add(nBaru);
    }
    
    public void tarikTunai (String noRek, int jmlTarikTunai){
        // cari data rek by noRek
        //jika ketemu maka lakukan tarik tunai pada index tsb
        int idx=cari(noRek);
        if (idx!=-1){
            //tarik tunai
            daftarNasabah.elementAt(idx).tarikTunai(jmlTarikTunai);
        } else {
            System.out.println("No Rek : "+noRek+" Tidak Ada di database bos... ");
        }
    }
    
    public void transfer(String noRekAsal, String noRekTujuan, int jmlTransfer){
        //1. cari rekAsal
        //2. cari rekTujuan
        //3. jika ketemu semua
        //4. tarik tunai rekAsal
        //5. setor tunai rekTujuan
            int idx1=cari(noRekAsal);
            int idx2=cari(noRekTujuan);
            if ((idx1!=-1)&&(idx2!=-1)){
                daftarNasabah.elementAt(idx1).tarikTunai(jmlTransfer);
                daftarNasabah.elementAt(idx2).setorTunai(jmlTransfer);
            } else{
               //noRek tidak ketemu
               System.out.println ("No Rekening tidak ditemukan, cek inputan");
            }
    }
    
    public int cari(String noRek){
        int idx=-1;
        int i=0;
        do {
            if (daftarNasabah.elementAt(i).getnoRek().equals(noRek))
                idx=i;
                i++;
            
        }while ((i<daftarNasabah.size())&&(idx==-1));  
        return idx;
    }
}
