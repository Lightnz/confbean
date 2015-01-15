package org.bonn.ooka.conference.dtos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.bonn.ooka.conference.dtos.Teilnehmer;
import org.bonn.ooka.conference.dtos.Veranstalter;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-14T15:27:15")
@StaticMetamodel(Konferenz.class)
public class Konferenz_ { 

    public static volatile SingularAttribute<Konferenz, Date> date;
    public static volatile SingularAttribute<Konferenz, Integer> slots;
    public static volatile ListAttribute<Konferenz, Teilnehmer> teilnehmerliste;
    public static volatile SingularAttribute<Konferenz, String> titel;
    public static volatile SingularAttribute<Konferenz, Integer> ID;
    public static volatile SingularAttribute<Konferenz, Veranstalter> veranstalter;
    public static volatile SingularAttribute<Konferenz, Integer> bewertung;

}