// Emmanuel Lemi 
// Section 004 


package scientificcalc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class SciCalc {

	private JFrame frame;
	
	private final JLabel lblScientificCalculator = new JLabel("Scientific Calculator");
	private JTextField textField;
	
	int first;
	int second;
	int result;
	String operation;
	String answer;
	
	
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SciCalc window = new SciCalc();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SciCalc() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 559, 516);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(6, 0, 547, 59);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnClear = new JButton("C");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
			}
		});
		btnClear.setBounds(442, 56, 111, 59);
		frame.getContentPane().add(btnClear);
		
		JButton btnSquared = new JButton("X^2");
		btnSquared.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = (Double.parseDouble(textField.getText()));
				a = a*a;
				textField.setText("");
				textField.setText(textField.getText() + a);
			}
		});
		btnSquared.setBounds(465, 116, 88, 45);
		frame.getContentPane().add(btnSquared);
		
		JButton btnDivide = new JButton("÷");
		btnDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first = (Integer.parseInt(textField.getText()));
				textField.setText("");
				operation = "÷";
			}
		});
		btnDivide.setBounds(379, 116, 88, 45);
		frame.getContentPane().add(btnDivide);
		
		JButton btnMulitply = new JButton("x");
		btnMulitply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first = Integer.parseInt(textField.getText());
				textField.setText("");
				operation = "x";
			}
		});
		btnMulitply.setBounds(293, 116, 88, 45);
		frame.getContentPane().add(btnMulitply);
		
		final JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn9.getText();
				textField.setText(number);
			}
		});
		btn9.setBounds(207, 116, 88, 45);
		frame.getContentPane().add(btn9);
		
		final JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn8.getText();
				textField.setText(number);
			}
		});
		btn8.setBounds(118, 116, 88, 45);
		frame.getContentPane().add(btn8);
		
		final JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn7.getText();
				textField.setText(number);
			}
		});
		btn7.setBounds(29, 116, 88, 45);
		frame.getContentPane().add(btn7);
		
		JButton btnCubed = new JButton("X^3");
		btnCubed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = (Double.parseDouble(textField.getText()));
				a = a*a*a;
				textField.setText("");
				textField.setText(textField.getText() + a);
			}
		});
		btnCubed.setBounds(465, 160, 88, 45);
		frame.getContentPane().add(btnCubed);
		
		JButton btnSubtract = new JButton("-");
		btnSubtract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first = Integer.parseInt(textField.getText());
				textField.setText("");
				operation = "-";
			}
		});
		btnSubtract.setBounds(379, 160, 88, 45);
		frame.getContentPane().add(btnSubtract);
		
		JButton btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first = Integer.parseInt(textField.getText());
				textField.setText("");
				operation = "+";
				
			}
		});
		btnPlus.setBounds(293, 160, 88, 45);
		frame.getContentPane().add(btnPlus);
		
		final JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn6.getText();
				textField.setText(number);
			}
		});
		btn6.setBounds(207, 160, 88, 45);
		frame.getContentPane().add(btn6);
		
		final JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn5.getText();
				textField.setText(number);
			}
		});
		btn5.setBounds(118, 160, 88, 45);
		frame.getContentPane().add(btn5);
		
		final JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn4.getText();
				textField.setText(number);
			}
		});
		btn4.setBounds(29, 160, 88, 45);
		frame.getContentPane().add(btn4);
		
		JButton btnMod = new JButton("%");
		btnMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = (Double.parseDouble(textField.getText()));
				a = a/100;
				textField.setText("");
				textField.setText(textField.getText()+a);
				
			}
		});
		btnMod.setBounds(379, 246, 88, 45);
		frame.getContentPane().add(btnMod);
		
		JButton btnEquals = new JButton("=");
		btnEquals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				second = Integer.parseInt(textField.getText());
				if (operation == "+") {
					result = first+second;
					answer = Double.toString(result);
					textField.setText(answer);
				}
				else if (operation == "-") {
					result = first-second;
					answer = Double.toString(result);
					textField.setText(answer);
				}
				else if (operation == "x") {
					result = first*second;
					answer = Double.toString(result);
					textField.setText(answer);
				}
				else if (operation == "÷") {
					result = first/second;
					answer = Double.toString(result);
					textField.setText(answer);
				}
				else if (operation == "%") {
					result = first%second;
					answer = Double.toString(result);
					textField.setText(answer);
				}
			}
		});
		btnEquals.setBounds(293, 203, 174, 45);
		frame.getContentPane().add(btnEquals);
		
		final JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn3.getText();
				textField.setText(number);
			}
		});
		btn3.setBounds(207, 203, 88, 45);
		frame.getContentPane().add(btn3);
		
		final JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn2.getText();
				textField.setText(number);
			}
		});
		btn2.setBounds(118, 203, 88, 45);
		frame.getContentPane().add(btn2);
		
		final JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn1.getText();
				textField.setText(number);
			}
		});
		btn1.setBounds(29, 203, 88, 45);
		frame.getContentPane().add(btn1);
		
		JButton btnRoot = new JButton(" √");
		btnRoot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Math.sqrt(Double.parseDouble(textField.getText()));
				textField.setText("");
				textField.setText(textField.getText()+a);
			}
		});
		btnRoot.setBounds(465, 246, 88, 45);
		frame.getContentPane().add(btnRoot);
		
		JButton btnModulo = new JButton("Mod");
		btnModulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first = Integer.parseInt(textField.getText());
				textField.setText("");
				operation = "%";
			}
		});
		btnModulo.setBounds(465, 203, 88, 45);
		frame.getContentPane().add(btnModulo);
		
		JButton btnOnedivideN = new JButton("1/n");
		btnOnedivideN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = 1/(Double.parseDouble(textField.getText()));
				textField.setText("");
				textField.setText(textField.getText()+a);
			}
		});
		btnOnedivideN.setBounds(293, 246, 88, 45);
		frame.getContentPane().add(btnOnedivideN);
		
		JButton btnPositiveNegative = new JButton("±");
		btnPositiveNegative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Double.parseDouble(String.valueOf(textField.getText()));
				a = a*(-1);
				textField.setText(String.valueOf(a));
			}
		});
		btnPositiveNegative.setBounds(207, 246, 88, 45);
		frame.getContentPane().add(btnPositiveNegative);
		
		JButton btnDot = new JButton(".");
		btnDot.setBounds(118, 246, 88, 45);
		frame.getContentPane().add(btnDot);
		
		final JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btn0.getText();
				textField.setText(number);
			}
		});
		btn0.setBounds(29, 246, 88, 45);
		frame.getContentPane().add(btn0);
		
		JButton btnNaturalLog = new JButton("ln");
		btnNaturalLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Math.log(Double.parseDouble(textField.getText()));
				textField.setText("");
				textField.setText(textField.getText()+a);
			}
		});
		btnNaturalLog.setBounds(465, 319, 88, 45);
		frame.getContentPane().add(btnNaturalLog);
		
		JButton btnLog = new JButton("log");
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Math.log10(Double.parseDouble(textField.getText()));
				textField.setText("");
				textField.setText(textField.getText()+a);
			}
		});
		btnLog.setBounds(379, 319, 88, 45);
		frame.getContentPane().add(btnLog);
		
		JButton btnTan = new JButton("Tan");
		btnTan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Math.tan(Double.parseDouble(textField.getText()));
				textField.setText("");
				textField.setText(textField.getText()+a);
			}
		});
		btnTan.setBounds(293, 319, 88, 45);
		frame.getContentPane().add(btnTan);
		
		JButton btnCos = new JButton("Cos");
		btnCos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Math.cos(Double.parseDouble(textField.getText()));
				textField.setText("");
				textField.setText(textField.getText()+a);
				
			
				
			}
		});
		btnCos.setBounds(207, 319, 88, 45);
		frame.getContentPane().add(btnCos);
		
		JButton btnSin = new JButton("Sin");
		btnSin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Math.sin(Double.parseDouble(textField.getText()));
				textField.setText("");
				textField.setText(textField.getText()+a);
				
			}
		});
		btnSin.setBounds(118, 319, 88, 45);
		frame.getContentPane().add(btnSin);
		
		JButton btnAbsoluteVal = new JButton("abs");
		btnAbsoluteVal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Math.abs(Double.parseDouble(textField.getText()));
				textField.setText("");
				textField.setText(textField.getText()+a);
			}
		});
		btnAbsoluteVal.setBounds(465, 365, 88, 45);
		frame.getContentPane().add(btnAbsoluteVal);
		
		JButton btntentopowern = new JButton("10^n");
		btntentopowern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Math.pow(10,(Integer.parseInt(textField.getText())));
				textField.setText("");
				textField.setText(textField.getText()+a);
			}
		});
		btntentopowern.setBounds(379, 365, 88, 45);
		frame.getContentPane().add(btntentopowern);
		
		JButton btnatan = new JButton("atan");
		btnatan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Math.atan(Integer.parseInt(textField.getText()));
				textField.setText("");
				textField.setText(textField.getText()+a);
			}
		});
		btnatan.setBounds(293, 365, 88, 45);
		frame.getContentPane().add(btnatan);
		
		JButton btnacos = new JButton("acos");
		btnacos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Math.acos(Integer.parseInt(textField.getText()));
				textField.setText("");
				textField.setText(textField.getText()+a);
			}
		});
		btnacos.setBounds(207, 365, 88, 45);
		frame.getContentPane().add(btnacos);
		
		JButton btnasin = new JButton("asin");
		btnasin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Math.asin(Integer.parseInt(textField.getText()));
				textField.setText("");
				textField.setText(textField.getText()+a);
			}
		});
		btnasin.setBounds(118, 365, 88, 45);
		frame.getContentPane().add(btnasin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(379, 408, 174, 45);
		frame.getContentPane().add(btnExit);
		
		JButton btntanh = new JButton("tanh");
		btntanh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Math.tanh(Integer.parseInt(textField.getText()));
				textField.setText("");
				textField.setText(textField.getText()+a);
			}
		});
		btntanh.setBounds(293, 408, 88, 45);
		frame.getContentPane().add(btntanh);
		
		JButton btncosh = new JButton("cosh");
		btncosh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Math.cosh(Integer.parseInt(textField.getText()));
				textField.setText("");
				textField.setText(textField.getText()+a);
			}
		});
		btncosh.setBounds(207, 408, 88, 45);
		frame.getContentPane().add(btncosh);
		
		JButton btnsinh = new JButton("sinh");
		btnsinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Math.sinh(Integer.parseInt(textField.getText()));
				textField.setText("");
				textField.setText(textField.getText()+a);
			}
		});
		btnsinh.setBounds(118, 408, 88, 45);
		frame.getContentPane().add(btnsinh);
	}
}
