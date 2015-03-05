/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Publikation;

/**
 *
 * @author Fabian
 */
public class Utils {
    
    public static List<Konferenz> sortKonferenzen(List<Konferenz> list){
        
        Collections.sort(list, new Comparator<Konferenz>(){

            @Override
            public int compare(Konferenz o1, Konferenz o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
            
        });
        return list;
    }
    
    public static List<Publikation> sortPublikationen(List<Publikation> list){
        Collections.sort(list, new Comparator<Publikation>(){

            @Override
            public int compare(Publikation o1, Publikation o2) {
                return o1.getKonferenz().getDate().compareTo(o2.getKonferenz().getDate());
            }
            
        });
        return list;
    }
    
}
