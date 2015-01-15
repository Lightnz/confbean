package org.bonn.ooka.conference.dtos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.bonn.ooka.conference.dtos.Konferenz;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-14T15:27:15")
@StaticMetamodel(Teilnehmer.class)
public class Teilnehmer_ { 

    public static volatile SingularAttribute<Teilnehmer, String> name;
    public static volatile SingularAttribute<Teilnehmer, Integer> id;
    public static volatile ListAttribute<Teilnehmer, Konferenz> angemeldeteKonferenzen;

}