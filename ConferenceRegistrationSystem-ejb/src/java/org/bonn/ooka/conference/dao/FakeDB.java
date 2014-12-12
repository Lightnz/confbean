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
    
    
    public static void addKonferenz(Konferenz k){
        if(k.getSlots()<100 || k.getVeranstalter().getRep()>5)
            konferenzen.add(k);
        else
            //TODO: vernünftigen Output..
            System.out.println("Die Reputation des Veranstalters ist für die Größe dieser Veranstaltung nicht ausreichend!");
    }
    
    /**
     *
     * @return
     */
    public static List<Konferenz> getKonferenzen(){
        if (konferenzen == null){
            
            createDummies();
        }
        
        
        return konferenzen;
    }
    
    public static List<Teilnehmer> getTeilnehmerGlobal() {
        if (teilnehmerglobal == null){
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
        
        
        konferenzen = new ArrayList<Konferenz>();
        Veranstalter v1 = new Veranstalter(0, "Peter AG");
        Veranstalter v2 = new Veranstalter(1, "Horst AG");
        Veranstalter v3 = new Veranstalter(2, "Horst und Peter Group");
        addKonferenz(new Konferenz(v1, "Wieso Peter gut und Horst schlecht ist - Teil 1", 1, 500));
        addKonferenz(new Konferenz(v2, "Wieso Horst besser ist als Peter - Teil 1", 2, 100));
        addKonferenz(new Konferenz(v3, "Peter AG und Horst AG, Beste Freunde", 3, 200));
        //Dekrementiere die Reputation von Veranstalter 1, sodass sie die kritische Marke von 5 unterschreitet...
        for(int i=0; i<6; i++)
            v1.decRep();
        //Die nun einzufügende Konferenz sollte nicht angelegt werden, da Reputation zu niedrig...
        addKonferenz(new Konferenz(v1, "Keep friends close but keep enemies closer", 4, 300));
        //Diese aber schon...
        addKonferenz(new Konferenz(v1, "Keep friends close but keep enemies closer", 4, 99));
        
        //ein paar Teilnehmer zuordnen
        konferenzen.get(0).addTeilnehmer(t1);
        konferenzen.get(0).addTeilnehmer(t2);
        
        konferenzen.get(2).addTeilnehmer(t3);
        
    }
    
}
