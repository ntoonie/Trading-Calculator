package Javaproject.github;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;

public class TradingCalculator extends JFrame implements ActionListener {
    private JLabel header, stoplosslbl, profitlbl, risklbl, leveragelbl, marginlbl, potprofitlbl, potlosslbl;
    private JButton compute, clear, exit;
    private JTextField stoplosstxt, profittxt, risktxt, leveragetxt, margintxt, potprofittxt, potlosstxt;
    private JSlider leverageslid;


    public TradingCalculator(){
        setSize(500,600);
        setTitle("Trading Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        header = new JLabel("TRADING CALCULATOR");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        stoplosslbl = new JLabel("Stoploss");
        profitlbl = new JLabel("Profit");
        risklbl = new JLabel("Risk");
        leveragelbl = new JLabel("Leverage");

        stoplosstxt = new JTextField(5);
        profittxt = new JTextField(5);
        risktxt = new JTextField(5);
        leveragetxt = new JTextField(5);
        margintxt = new JTextField(5);
        potprofittxt = new JTextField(5);
        potlosstxt = new JTextField(5);

        leverageslid = new JSlider(0,200,0);
        leverageslid.setMajorTickSpacing(100);
        leverageslid.setMinorTickSpacing(5);
        leverageslid.setPaintTicks(true);
        leverageslid.setPaintLabels(true);
        leverageslid.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e){
                leveragetxt.setText(String.valueOf(leverageslid.getValue()));
            }
        });

        compute = new JButton("Compute");
        compute.addActionListener(this);

        add(header, 0,0,2,0.0);
        add(stoplosslbl, 0,1,0,0.0);
        add(stoplosstxt, 1, 1, 1, 0.0);
        add(profitlbl, 0,2,1,0.0);
        add(profittxt, 1, 2, 1, 0.0);
        add(risklbl, 0, 3,1,0.0);
        add(risktxt, 1,3,1,0.0);
        add(leveragelbl, 0,4,1,0.0);
        add(leveragetxt,1,4,1,0.0);
        add(leverageslid,1,5,1,0.0);
        add(compute,1,6,1,0.0);

        marginlbl = new JLabel("Margin");
        potprofitlbl = new JLabel("Pot.Profit");
        potlosslbl = new JLabel("Pot.Loss");

        margintxt = new JTextField(5);
        potprofittxt = new JTextField(5);
        potlosstxt = new JTextField(5); 

        add(marginlbl, 0, 7, 1,0.0);
        add(margintxt, 1,7,1,0.0);
        add(potprofitlbl,0,8,1,0.0);
        add(potprofittxt,1,8,1,0.0);
        add(potlosslbl,0,9,1,0.0);
        add(potlosstxt,1,9,1,0.0);

        clear = new JButton("Clear");
        clear.addActionListener(this);

        exit = new JButton("Exit");
        exit.addActionListener(this);

        add(clear, 1,10,1,0.0);
        add(exit,1,11,1,0.0);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == compute){
        try{
            double margin = 0.0, potprofit = 0.0, potloss = 0.0;
            double stoploss, profit ,risk;
            int leverage;

            stoploss = Double.parseDouble(stoplosstxt.getText());
            profit = Double.parseDouble(profittxt.getText());
            risk = Double.parseDouble(risktxt.getText());
            leverage = Integer.parseInt(leveragetxt.getText());

            margin = risk / (stoploss * leverage);
            potprofit = margin * (profit * leverage);
            potloss = margin * (leverage*stoploss);

            margintxt.setText(String.format("%.2f", margin));
            potprofittxt.setText(String.format("%.2f", potprofit));
            potlosstxt.setText(String.format("%.2f", potloss));

        } catch (NumberFormatException r) {
            JOptionPane.showMessageDialog(this, "Enter Valid Numbers!", "Input Error", JOptionPane.ERROR_MESSAGE);
         }   
        }
        if(e.getSource() == clear){
            stoplosstxt.setText("");
            profittxt.setText("");
            risktxt.setText("");
            leveragetxt.setText("");
            margintxt.setText("");
            potlosstxt.setText("");
            potprofittxt.setText("");
            leverageslid.setValue(0);
        }
        if(e.getSource() == exit){
            System.exit(0);
        }
    }

    private void add(Component comp, int x, int y, int width, double weightx){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.weightx = weightx;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 10, 5);
        add(comp, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            TradingCalculator frame = new TradingCalculator();
            frame.setVisible(true);
        });
    }
}
