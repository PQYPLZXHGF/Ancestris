/*
 * Ancestris - http://www.ancestris.org
 * 
 * Copyright 2016 Ancestris
 * 
 * Author: Frédéric Lapeyre (frederic@ancestris.org).
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package ancestris.modules.editors.standard.tools;

import genj.gedcom.Entity;
import genj.gedcom.Indi;
import genj.gedcom.Property;
import genj.gedcom.PropertyAssociation;
import genj.gedcom.PropertyDate;
import genj.gedcom.PropertyForeignXRef;
import genj.gedcom.PropertyRelationship;
import genj.gedcom.PropertySex;
import genj.gedcom.time.PointInTime;

/**
 *
 * @author frederic
 */
public class AssoWrapper {

    public PropertyAssociation assoProp = null; // The association property with the ASSO tag
    public String assoTxt = "";                 // The association text
    
    public Indi assoIndi = null;                // Indi the ASSO tag belongs to. The Gedcom norm only allows for INDI to have ASSO tags.
    public String assoLastname = "";            // The associated individual lastname
    public String assoFirstname = "";           // The associated individual firstname
    public int assoSex = PropertySex.UNKNOWN;   // The associated individual sex
    public String assoOccupation = "";          // The associated individual occupation

    private Entity targetEntity = null;         // Entity the association refers to. The Gedcom norm only allows for INDI to be pointed to by an ASSO tag. However, Ancestris allows FAM to be referenced too.
    public EventWrapper targetEvent = null;     // Event of the entity, the association refers to.
    private String targetEventTag = "";         // Event tag of the associated event
    public String targetEventDesc = "";         // The event text to be displayed
    

    public AssoWrapper(String text) {
        assoTxt = text;
    }

    public AssoWrapper(PropertyForeignXRef xrefProperty, EventWrapper event) {

        if (xrefProperty == null) {
            return;
        }
        
        Indi associatedIndi = (Indi) xrefProperty.getTargetEntity();
        PropertyAssociation assoProperty = (PropertyAssociation) xrefProperty.getTarget();
        setValues(associatedIndi, assoProperty, event);
        
    }

    public AssoWrapper(PropertyAssociation assoProperty) {

        if (assoProperty == null) {
            return;
        }
        
        Indi associatedIndi = (Indi) assoProperty.getEntity();
        Property eventProp = assoProperty.getTargetParent();
        EventWrapper event = new EventWrapper(eventProp, null);
        setValues(associatedIndi, assoProperty, event);
        
    }

    private AssoWrapper(AssoWrapper asso) {
        assoProp = asso.assoProp;
        assoTxt = asso.assoTxt;
        assoIndi = asso.assoIndi;
        assoLastname = asso.assoLastname;
        assoFirstname = asso.assoFirstname;
        assoSex = asso.assoSex;
        assoOccupation = asso.assoOccupation;
        targetEntity = asso.targetEntity;
        targetEvent = asso.targetEvent;
        targetEventTag = asso.targetEventTag;
        targetEventDesc = asso.targetEventDesc;
    }

    /**
     * Set values based on ASSO tag and reference
     * 
     * @param associatedIndi : the indi with the ASSO tag
     * @param assoProperty : the ASSO property
     * @param referedToProperty : the event property pointed to by the ASSO relation (RELA)
     */
    private void setValues(Indi associatedIndi, PropertyAssociation assoProperty, EventWrapper event) {

        // Get key elements
        assoProp = assoProperty;
        assoIndi = associatedIndi;
        targetEntity = event.eventProperty.getEntity();
        targetEvent = event;
        
        // Get table elements
        assoLastname = assoIndi.getLastName();
        assoFirstname = assoIndi.getFirstName();
        assoSex = assoIndi.getSex();
        assoOccupation = getOccupation(assoIndi);
        targetEventDesc = assoProp.getDisplayValue(false);
        PropertyRelationship relaP = (PropertyRelationship) assoProp.getProperty("RELA");
        assoTxt = relaP.getDisplayValue();
    }

    public String getOccupation(Indi indi) {
        String occu = "";
        // Select latest occupation
        Property props[] = indi.getProperties("OCCU");
        PointInTime latestPIT = null;
        for (Property prop : props) {
            Property date = prop.getProperty("DATE");
            if (date != null) {
                PropertyDate pdate = (PropertyDate) date;
                PointInTime pit = pdate.getEnd();
                if (!pit.isValid()) {
                    pit = pdate.getStart();
                }
                if (latestPIT == null || pit.compareTo(latestPIT) > 0) {
                    latestPIT = pit;
                    occu = prop.getDisplayValue();
                }
            } else {
                occu = prop.getDisplayValue();
            }
        }
        return occu;
    }

    public static AssoWrapper clone(AssoWrapper asso) {
        return new AssoWrapper(asso);
    }

    @Override
    public String toString() {
        String name = (assoLastname + " " + assoFirstname).trim();
        return assoTxt + (!name.isEmpty() ? " | " + name : "");
    }
    
    public void update(Indi indi) {
        
    }



    public void remove(Indi indi) {

    }

    
}
