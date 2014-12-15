/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dao;

import java.util.ArrayList;
import java.util.List;
import org.bonn.ooka.conference.dtos.Konferenz;
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
    private static boolean dummiesErstellt = false;
    
    
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
    
    private static void createDummies(){
        
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
        addKonferenz(new Konferenz(v1, "Wieso Peter gut und Horst schlecht ist - Teil 1", 0, 500));
        addKonferenz(new Konferenz(v2, "Wieso Horst besser ist als Peter - Teil 1", 1, 100));
        addKonferenz(new Konferenz(v3, "Peter AG und Horst AG, Beste Freunde", 2, 200));
        //Dekrementiere die Reputation von Veranstalter 1, sodass sie die kritische Marke von 5 unterschreitet...
        for(int i=0; i<6; i++)
            v1.decRep();
        //Die nun einzufügende Konferenz sollte nicht angelegt werden, da Reputation zu niedrig...
        addKonferenz(new Konferenz(v1, "Keep friends close but keep enemies closer", 3, 300));
        //Diese aber schon...
        addKonferenz(new Konferenz(v1, "Keep friends close but keep enemies closer", 3, 99));
        
        //ein paar Teilnehmer zuordnen
        registerParticipantToConference(t1, konferenzen.get(0));
        registerParticipantToConference(t2, konferenzen.get(0));
        registerParticipantToConference(t3, konferenzen.get(2));
        registerParticipantToConference(t4, konferenzen.get(1));
        
        
        dummiesErstellt = true;
        
    }
    
}
