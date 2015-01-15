package org.bonn.ooka.conference.dtos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.bonn.ooka.conference.dtos.Konferenz;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-14T15:27:15")
@StaticMetamodel(Veranstalter.class)
public class Veranstalter_ { 

    public static volatile SingularAttribute<Veranstalter, String> name;
    public static volatile SingularAttribute<Veranstalter, Integer> ID;
    public static volatile SingularAttribute<Veranstalter, Integer> rep;
    public static volatile ListAttribute<Veranstalter, Konferenz> konferenzen;

}