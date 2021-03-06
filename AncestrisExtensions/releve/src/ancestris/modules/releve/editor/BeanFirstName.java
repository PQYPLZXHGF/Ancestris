package ancestris.modules.releve.editor;

import ancestris.modules.releve.model.CompletionListener;
import ancestris.modules.releve.model.CompletionProvider;
import ancestris.modules.releve.model.CompletionProvider.IncludeFilter;
import ancestris.modules.releve.model.Field;
import ancestris.modules.releve.model.FieldSimpleValue;
import java.util.List;

/**
 *
 * @author Michel
 */
public class BeanFirstName extends Bean implements CompletionListener {
    private Java2sAutoTextField cFirst;
    CompletionProvider completionProvider;
    
    public BeanFirstName(CompletionProvider completionProvider) {
        this.completionProvider = completionProvider;
        completionProvider.addFirstNamesListener(this);
        setLayout(new java.awt.BorderLayout());
        cFirst = new Java2sAutoTextField(completionProvider.getFirstNames(IncludeFilter.INCLUDED));
        cFirst.setStrict(false);
        cFirst.setCaseSensitive(false);
        cFirst.setUpperAllFirstChar(true);
        cFirst.setLocale(completionProvider.getLocale()); 
        cFirst.addChangeListener(changeSupport);
        
        // Layout the bean
        add(cFirst, java.awt.BorderLayout.CENTER);

        // je fixe le focus par defaut
        defaultFocus = cFirst;
    }

    /**
     * Set context to edit
     */
    @Override
    public void setFieldImpl() {

        final FieldSimpleValue name = (FieldSimpleValue) getField();
        if (name == null) {
            cFirst.setText("");
        } else {
            cFirst.setText(name.toString());
        }        
    }

    @Override
    protected void replaceValueImpl(Field field) {
       final FieldSimpleValue name = (FieldSimpleValue) field;
        if (name == null) {
            cFirst.setText("");
        } else {
            cFirst.setText(name.toString());
        }
    }

    /**
     * Finish editing a property through proxy
     */
    @Override
    protected void commitImpl() {
        String firstName = cFirst.getText().trim();

        if (!firstName.isEmpty()) {
            firstName = Character.toString(firstName.charAt(0)).toUpperCase() + firstName.substring(1);
        }

        // ... store changed value
        FieldSimpleValue fieldName = (FieldSimpleValue) getField();
        fieldName.setValue(firstName);
        cFirst.setText(firstName);
    }

    /**
     * je supprime la declaration de listener
     * avant que l'objet ne soit detruit
     */
    @Override
    public void removeNotify() {
        completionProvider.removeFirstNamesListener(this);
        super.removeNotify();
    }

    /**
     * Implemente CompletionListener
     * copie la nouvelle liste de completion
     * @param keyList
     */
    @Override
    public void includedKeyUpdated(List<String> keyList) {
        cFirst.setDataList(keyList);
    }
}
