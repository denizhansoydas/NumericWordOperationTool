import java.util.Locale;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import definition.NumConverterI;

public class Client implements BundleActivator, ServiceListener {

    private BundleContext bundleContext;
    private ServiceReference serviceReference;
    @Override
    public void start(BundleContext bundleContext) throws Exception {
    	System.out.println("Client's start method");
    	this.bundleContext = bundleContext;
        Locale locale = Locale.getDefault();
    	String language = locale.getLanguage();
        try{
            bundleContext.addServiceListener(this,
                    "(objectclass=" + NumConverterI.class.getName() + ")");
            ServiceReference<NumConverterI> ref = bundleContext.getServiceReference(NumConverterI.class);
            NumConverterI numConverter = (NumConverterI)bundleContext.getService(ref);
        }catch (InvalidSyntaxException e){
            e.printStackTrace();
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	//MyJFrame frame = new MyJFrame(bundleContext, language); // will be uncommented.
            	MyJFrame frame = new MyJFrame(bundleContext, "tr");
                 frame.setVisible(true);
            }
        });
        
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        if(serviceReference != null){
            bundleContext.ungetService(serviceReference);
        }
    }

    public void serviceChanged(ServiceEvent serviceEvent){
        int type = serviceEvent.getType();
        switch (type){
            case(ServiceEvent.REGISTERED):
                System.out.println("Notification of service registered");
                serviceReference = serviceEvent.getServiceReference();
                NumConverterI numConverter = (NumConverterI)(bundleContext.getService(serviceReference));
//                System.out.println(numConverter.sayHiTo("John"));
                break;
            case(ServiceEvent.UNREGISTERING):
                System.out.println("Notification of service unregistered.");
                //bundleContext.ungetService(serviceEvent.getServiceReference());
                break;
            default:
                break;
        }
    }
}
