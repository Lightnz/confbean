/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.bonn.ooka.conference.dtos.Gutachter;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Publikation;
import org.bonn.ooka.conference.dtos.Teilnehmer;
import org.bonn.ooka.conference.dtos.Veranstalter;

/**
 *
 * @author Fabian
 */
public class FakeDB {
    
    private static List<Konferenz> konferenzen;
    private static List<Teilnehmer> teilnehmerglobal;
    private static List<Veranstalter> veranstalterglobal;
    private static List<Gutachter> gutachterglobal;
    private static boolean dummiesErstellt = false;
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    
    
    public FakeDB(){
        
    }
    
    /**
     *
     * 
     * @param teilnehmer
     * @param konferenz
     */
    
    public static void registerParticipantToConference(Teilnehmer teilnehmer, Konferenz konferenz) {
        teilnehmerglobal.get(teilnehmer.getId()).addConference(konferenz);
        konferenzen.get(konferenz.getID()).addTeilnehmer(teilnehmer);       
        
    }
    
    
    public static boolean addKonferenz(Konferenz k){
        
        if(k.getSlots()>200 && k.getVeranstalter().getRep()<5){
            return false;
        }
        else
        {            
            konferenzen.add(k);
            return true;
        }
    }
    
    public static Gutachter getDefaultGutachter(){
        return gutachterglobal.get(0);
    }
    
    public static boolean editKonferenz(Konferenz k){
        boolean ret = false;
        for(Konferenz konf : konferenzen){
            if(konf.getID() == k.getID()){
                if(deleteKonferenz(konf))
                    return konferenzen.add(k);
                else
                    return false;
            }
        }
        return false;
    }
    
    public static boolean addPublikationTo(Publikation p, Konferenz k){
        k.addPublikation(p);
        return true;
    }
    
    public static List<Publikation> getPublikationenOf(Konferenz k){
        return k.getPublikationen();
    }
    
    public static boolean deleteKonferenz(Konferenz k){
        return konferenzen.remove(k);
    }
    
    public static int getNextKonferenzID(){
        if(!dummiesErstellt){
            createDummies();
        }
        int max=0;
        for(Konferenz k : konferenzen){
            if(k.getID()>max)
                max = k.getID();
        }
        max++;
        return max;
    }
    
    public static void addVeranstalter(Veranstalter v){
        veranstalterglobal.add(v);
    }
    
    public static Veranstalter getVeranstalter(int i){
        if(!dummiesErstellt){
            createDummies();
        }
        for(Veranstalter v : veranstalterglobal){
            if(v.getID()==i){
                return v;
            }
        }
        return null;
    }
    
    /**
     *
     * @return
     */
    public static List<Konferenz> getKonferenzen(){
        if(!dummiesErstellt){
            createDummies();
        }
        
        
        return konferenzen;
    }
    
    public static List<Konferenz> getKonferenzenOf(Veranstalter v){
        if(!dummiesErstellt){
            createDummies();
        }
        List<Konferenz> konf = new ArrayList<Konferenz>();
        for(Konferenz k : konferenzen){
            if(k.getVeranstalter().getID()==v.getID())
                konf.add(k);
        }
        return konf;
    }
    
    public static List<Teilnehmer> getTeilnehmerGlobal() {
        if(!dummiesErstellt){
            createDummies();
        }
        
        
        return teilnehmerglobal;
    }
    
    public static List<Gutachter> getAllGutachter(){
        return gutachterglobal;
    }
    
    public static boolean addGutachterTo(Gutachter gutachter, Publikation publikation){
        for(Konferenz konf : konferenzen){
            for(Publikation pub : konf.getPublikationen()){
                if(pub==publikation){
                    pub.setGutachter(gutachter);
                    return true;
                }
            }
        }
        return false;
    }
    
    private static void createDummies(){
        
        gutachterglobal = new ArrayList<Gutachter>();
        Gutachter g1 = new Gutachter(0, "Johnny Bravo");
        Gutachter g2 = new Gutachter(1, "Sascha Alda");
        gutachterglobal.add(g1);
        gutachterglobal.add(g2);
        
        teilnehmerglobal = new ArrayList<Teilnehmer>();
        Teilnehmer t1 = new Teilnehmer(0, "Alex Laitenberger");
        Teilnehmer t2 = new Teilnehmer(1, "Fabian Lewalder");
        Teilnehmer t3 = new Teilnehmer(2, "Horst Michael");
        Teilnehmer t4 = new Teilnehmer(3, "Raja Nasarajadschan");
        teilnehmerglobal.add(t1);
        teilnehmerglobal.add(t2);
        teilnehmerglobal.add(t3);
        teilnehmerglobal.add(t4);
        
        veranstalterglobal = new ArrayList<Veranstalter>();
        konferenzen = new ArrayList<Konferenz>();
        Veranstalter v1 = new Veranstalter(0, "Peter AG");
        Veranstalter v2 = new Veranstalter(1, "Horst AG");
        Veranstalter v3 = new Veranstalter(2, "Horst und Peter Group");
        addVeranstalter(v1);
        addVeranstalter(v2);
        addVeranstalter(v3);
        Konferenz k1, k2, k3, k4, k5;
        k1=null;
        k2=null;
        k3=null;
        k4=null;
        k5=null;
        try{
            k1 = new Konferenz(v1, "Wieso Peter gut und Horst schlecht ist - Teil 1", 0, 500, formatter.parse("2015-01-11"));
            k2 = new Konferenz(v2, "Wieso Horst besser ist als Peter - Teil 1", 1, 100, formatter.parse("2015-2-22"));
            k3 = new Konferenz(v3, "Peter AG und Horst AG, Beste Freunde", 2, 200, formatter.parse("2015-03-21"));
            k4 = new Konferenz(v1, "Keep friends close but keep enemies closer", 3, 300, formatter.parse("2015-04-01"));
            k5 = new Konferenz(v1, "Keep friends close but keep enemies closer", 3, 99, formatter.parse("2015-05-05"));
        } catch(Exception e){
            e.printStackTrace();
        }
        addKonferenz(k1);
        addKonferenz(k2);
        addKonferenz(k3);
        //Dekrementiere die Reputation von Veranstalter 1, sodass sie die kritische Marke von 5 unterschreitet...
        for(int i=0; i<6; i++)
            v1.decRep();
        //Die nun einzufügende Konferenz sollte nicht angelegt werden, da Reputation zu niedrig...
        addKonferenz(k4);
        //Diese aber schon...
        addKonferenz(k5);
        
        //ein paar Teilnehmer zuordnen
        registerParticipantToConference(t1, konferenzen.get(0));
        registerParticipantToConference(t2, konferenzen.get(0));
        registerParticipantToConference(t3, konferenzen.get(2));
        registerParticipantToConference(t4, konferenzen.get(1));
        
        addPublikationTo(new Publikation(t1, "Peters Vorzüge", 1), k1);
        addPublikationTo(new Publikation(t2, "Horsts Vorzüge", 3), k2);
        addPublikationTo(new Publikation(t2, "Scheinheilige Konferenzen", 2), k3);
        addPublikationTo(new Publikation(t1, "Feindliche Spionage", 4), k5);
        
        
        dummiesErstellt = true;
        
    }
    
}
