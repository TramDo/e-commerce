package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class MaxPrincipal
 *
 */
@WebListener
public class MaxPrincipal implements HttpSessionAttributeListener {
	int counter=0;
    /**
     * Default constructor. 
     */
    public MaxPrincipal() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    	handleMax(se);
    }
    
    void handleMax(HttpSessionBindingEvent event) {
    	if (event.getName().equals("principal")) {
    		//If the attribute was replaced, this is the old value of the attribute.
    		counter++;
    		event.getSession().setAttribute("count", counter);
    		Double maxValue, oldValue, newValue;
    		oldValue = (Double) event.getValue();
    		newValue = (Double)event.getSession().getAttribute("principal");
    		maxValue = oldValue;
    		if (newValue > oldValue)
    			maxValue = newValue;
   			event.getSession().setAttribute("maxValue", maxValue.toString());
    	}
    }
	
}
