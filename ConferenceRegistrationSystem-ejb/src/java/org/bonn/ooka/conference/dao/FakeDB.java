/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dao;

import java.util.ArrayList;
import java.util.List;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Veranstalter;

/**
 *
 * @author Fabian
 */
public class FakeDB implements ConferenceDAO{
    
    private static List<Konferenz> konferenzen = new ArrayList<Konferenz>();
    private FakeDB instance;
    
    public FakeDB(){
        createDummies();
    }
    
    public void addKonferenz(Konferenz k){
        if(k.getSlots()<100 || k.getVeranstalter().getRep()>5)
            konferenzen.add(k);
        else
            //TODO: vernünftigen Output..
            System.out.println("Die Reputation des Veranstalters ist für die Größe dieser Veranstaltung nicht ausreichend!");
    }
    
    public List<Konferenz> getKonferenzen(){
        return konferenzen;
    }
    
    private void createDummies(){
        Veranstalter v1 = new Veranstalter(1, "Peter AG");
        Veranstalter v2 = new Veranstalter(2, "Horst AG");
        Veranstalter v3 = new Veranstalter(3, "Horst und Peter Group");
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
    }
    
}
