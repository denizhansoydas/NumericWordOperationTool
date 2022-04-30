import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.json.JSONObject;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import definition.NumConverterI;

public class MyJFrame extends JFrame{
	private JLabel label_first_in;
	private JLabel label_second_in;
	private JLabel label_result;
	private JTextField tf_first_in;
	private JTextField tf_second_in;
	private JTextField tf_result;
	private JButton button_sum;
	private JButton button_sub;
	private JButton button_mul;
	private JButton button_div;
	private String language;
	static final String PROJECT_NAME = "NumericWordProcessor";
    // Constructor

    public MyJFrame(BundleContext bundleContext, String language) {
    	super("Denizhan -> Ekinoks");
    	this.language = language;
    	final int SUPPORTED_OPERATION_COUNT = 4; // COUNT(SUM, SUB, MUL, DIV).
    	
    	System.out.println("Language is : " + language);
    	
    	
    	//System.out.println(System.getProperty("osgi.bundles"));
    	String filePath = System.getProperty("osgi.bundles");
    	Properties properties = System.getProperties();
        // Java 8
        //properties.forEach((k, v) -> System.out.println(k + ":" + v));
    	filePath = filePath.substring("reference:file:".length(), filePath.length());
    	String[] filePaths = filePath.split("reference:file:");
    	for(String path : filePaths) {
    		String[] last = path.split("@")[0].split("/");
    		if(last[last.length - 1].equals(PROJECT_NAME))
    			filePath = path.split("@")[0];
    	}
    	if(!new File(filePath + "/values/" + language + "/strings.json").isFile()) { // if language is not supported, use english.
    		filePath += "/values/" + "en" + "/strings.json";
    	}
    	else
    		filePath += "/values/" + language + "/strings.json";
    	if(!new File(filePath).isFile()) { //if still not supported, then the strings are not reachable.
    		System.err.println("Default strings.json is missing.");
    	}
    	
    	System.out.println(filePath);
    	File file = new File(filePath);
    	FileReader fileReader;
    	BufferedReader bufferedReader;
    	StringBuffer stringBuffer = null;
    	
    	
    	//old code, has problems with utf 8 encoding, causes tr chars to fail. Kept for archive purposes.
//    	try {
//    		fileReader = new FileReader(file);
//	    	bufferedReader = new BufferedReader(fileReader);  //creates a buffering character input stream 
//	    	stringBuffer = new StringBuffer();    //constructs a string buffer with no characters  
//	    	String line;
//	    	
//	    	while((line =bufferedReader.readLine())!=null)  
//	    	{  
//		    	stringBuffer.append(line);      //appends line to string buffer  
//		    	stringBuffer.append("\n");     //line feed   
//	    	}
//	    	System.out.println(stringBuffer);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
    	
    	
    	try {
    		bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
    		String line;
    		stringBuffer = new StringBuffer();
    		while((line = bufferedReader.readLine()) != null ) {
    			stringBuffer.append(line);
    			stringBuffer.append("\n");
    		}
    		System.out.println(stringBuffer);
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	JSONObject obj = new JSONObject(stringBuffer.toString());
    	//System.out.println(obj);
    	//System.out.println(obj.get("label_first_in").toString());
    	 
    	GridBagLayout layout = new GridBagLayout();
    	GridBagConstraints gbc = new GridBagConstraints();
    	
    	setLayout(layout);
    	
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.ipadx = 10; gbc.ipady = 10;
    	gbc.insets = new Insets(5, 5, 5, 5);
    	
    	gbc.gridx = 0; gbc.gridy = 0;
    	label_first_in = new JLabel(obj.get("label_first_in").toString());
    	label_first_in.setHorizontalAlignment(SwingConstants.RIGHT);
    	add(label_first_in, gbc);
    	
    	gbc.gridx = 1; gbc.gridy = 0;
    	gbc.gridwidth = SUPPORTED_OPERATION_COUNT;
    	tf_first_in = new JTextField("");
    	
    	add(tf_first_in, gbc);
    	
    	gbc.gridx = 0; gbc.gridy = 1;
    	gbc.gridwidth = 1;
    	label_second_in = new JLabel(obj.get("label_second_in").toString());
    	label_second_in.setHorizontalAlignment(SwingConstants.RIGHT);
    	add(label_second_in, gbc);
    	
    	gbc.gridx = 1; gbc.gridy = 1;
    	gbc.gridwidth = SUPPORTED_OPERATION_COUNT;
    	tf_second_in = new JTextField("");
    	//tf_second_in.setPreferredSize(new Dimension(50, (int)tf_second_in.getPreferredSize().getHeight()));
    	add(tf_second_in, gbc);
    	
    	gbc.gridx = 0; gbc.gridy = 2;
    	gbc.gridwidth = 1;
    	
    	label_result = new JLabel(obj.getString("label_result").toString());
    	label_result.setHorizontalAlignment(SwingConstants.RIGHT);
    	add(label_result, gbc);
    	
    	gbc.gridx = 1; gbc.gridy = 2;
    	gbc.gridwidth = SUPPORTED_OPERATION_COUNT;
    	tf_result = new JTextField("");
    	add(tf_result, gbc);
    	
    	
    	gbc.insets = new Insets(10, 10, 10, 10);
    	
    	gbc.gridx = 1; gbc.gridy = 3;
    	gbc.gridwidth = 1;
    	button_sum = new JButton(obj.getString("button_sum").toString());
    	
    	button_sum.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			String num1 = tf_first_in.getText();
    			String num2 = tf_second_in.getText();
    			ServiceReference<NumConverterI> ref = bundleContext.getServiceReference(NumConverterI.class);
                NumConverterI numConverter = (NumConverterI)bundleContext.getService(ref);
    			int first_operand = numConverter.convertToInt(language, num1);
    			System.out.println("First operand = " + first_operand);
    			int second_operand = numConverter.convertToInt(language, num2);
    			System.out.println("Second operand = " + second_operand);
    			int result = first_operand + second_operand;
    			String resultStr = numConverter.convertToString(language, result);
    			System.out.println("Result = " + resultStr);
    			tf_result.setText(resultStr);
    		}
    	});
    	
    	add(button_sum, gbc);
    	
    	gbc.gridx = 2; gbc.gridy = 3;
    	gbc.gridwidth = 1;
    	button_sub = new JButton(obj.getString("button_sub").toString());
    	
    	button_sub.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			String num1 = tf_first_in.getText();
    			String num2 = tf_second_in.getText();
    			ServiceReference<NumConverterI> ref = bundleContext.getServiceReference(NumConverterI.class);
                NumConverterI numConverter = (NumConverterI)bundleContext.getService(ref);
    			int first_operand = numConverter.convertToInt(language, num1);
    			System.out.println("First operand = " + first_operand);
    			int second_operand = numConverter.convertToInt(language, num2);
    			System.out.println("Second operand = " + second_operand);
    			int result = first_operand - second_operand;
    			String resultStr = numConverter.convertToString(language, result);
    			System.out.println("Result = " + resultStr);
    			tf_result.setText(resultStr);
    		}
    	});
    	
    	add(button_sub, gbc);
    	
    	gbc.gridx = 3; gbc.gridy = 3;
    	gbc.gridwidth = 1;
    	button_mul = new JButton(obj.getString("button_mul").toString());
    	
    	button_mul.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			String num1 = tf_first_in.getText();
    			String num2 = tf_second_in.getText();
    			ServiceReference<NumConverterI> ref = bundleContext.getServiceReference(NumConverterI.class);
                NumConverterI numConverter = (NumConverterI)bundleContext.getService(ref);
    			int first_operand = numConverter.convertToInt(language, num1);
    			System.out.println("First operand = " + first_operand);
    			int second_operand = numConverter.convertToInt(language, num2);
    			System.out.println("Second operand = " + second_operand);
    			int result = first_operand * second_operand;
    			String resultStr = numConverter.convertToString(language, result);
    			System.out.println("Result = " + resultStr);
    			tf_result.setText(resultStr);
    		}
    	});
    	
    	add(button_mul, gbc);
    	
    	gbc.gridx = 4; gbc.gridy = 3;
    	gbc.gridwidth = 1;
    	button_div = new JButton(obj.getString("button_div").toString());
    	
    	button_div.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			String num1 = tf_first_in.getText();
    			String num2 = tf_second_in.getText();
    			ServiceReference<NumConverterI> ref = bundleContext.getServiceReference(NumConverterI.class);
                NumConverterI numConverter = (NumConverterI)bundleContext.getService(ref);
    			int first_operand = numConverter.convertToInt(language, num1);
    			System.out.println("First operand = " + first_operand);
    			int second_operand = numConverter.convertToInt(language, num2);
    			System.out.println("Second operand = " + second_operand);
    			
    			int result = 0;
    			try {
    				result = first_operand / second_operand;
    				String resultStr = numConverter.convertToString(language, result);
        			System.out.println("Result = " + resultStr);
        			tf_result.setText(resultStr);
    			}catch(ArithmeticException arithmeticException) {
    				tf_result.setText("Invalid Operation because you've tried " + arithmeticException.getMessage());
    			}    			
    		}
    	});
    	add(button_div, gbc);
    	
    	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Aborts osgi.
    	//setPreferredSize(new Dimension(900,900));
    	pack();
    	setVisible(true);
    }
}
