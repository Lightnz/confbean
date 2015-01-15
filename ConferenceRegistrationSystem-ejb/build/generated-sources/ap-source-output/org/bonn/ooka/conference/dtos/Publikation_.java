package org.bonn.ooka.conference.dtos;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.bonn.ooka.conference.dtos.Gutachten;
import org.bonn.ooka.conference.dtos.Gutachter;
import org.bonn.ooka.conference.dtos.Teilnehmer;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-14T15:27:15")
@StaticMetamodel(Publikation.class)
public class Publikation_ { 

    public static volatile SingularAttribute<Publikation, String> titel;
    public static volatile SingularAttribute<Publikation, Gutachten> gutachten;
    public static volatile SingularAttribute<Publikation, Boolean> visible;
    public static volatile SingularAttribute<Publikation, Integer> ID;
    public static volatile SingularAttribute<Publikation, Teilnehmer> autor;
    public static volatile SingularAttribute<Publikation, Gutachter> gutachter;

}