package activator;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import definition.NumConverterI;
import implementation.NumConverter;

public class Activator implements BundleActivator {

    //private ServiceReference<NumConverterI> reference;
    private ServiceRegistration<NumConverterI> registration;
    public void start(BundleContext ctx) {
    	System.out.println("Registering wordFromAndToNumberConverter Service.");
        registration = ctx.registerService(NumConverterI.class, new NumConverter(), new Hashtable<String, String>());
        //reference = registration.getReference();

    }
    public void stop(BundleContext bundleContext) {
        System.out.println("Unregistering wordFromAndToNumberConverter Service.");
        registration.unregister();
    }
}
