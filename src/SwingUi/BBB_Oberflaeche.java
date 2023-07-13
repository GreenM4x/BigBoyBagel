package SwingUi;

import Logic.GetDBVallues;
import Logic.OrderCalc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BBB_Oberflaeche extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton addToCardButtonOne;
    private JButton addToCardButtonThree;
    private JButton addToCardButtonTwo;
    private JLabel firstBagelJlabel;
    private JLabel secondBagelJlabel;
    private JLabel thirdBagelJlabel;
    private JLabel firstBagelPriceJlabel;
    private JLabel secondBagelPriceJlabel;
    private JLabel thirdBagelPriceJlabel;
    private JLabel totalPriceLabel;
    private JPanel shoppingCardPanel;
    private JTextField discountCodeTextField;
    private JButton applyButton;
    private JLabel imgBigBoy;
    private JLabel imgVegiBoy;
    private JLabel imgSugarBoy;


    JLabel[] cartItemList = new JLabel[3];

    public BBB_Oberflaeche() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setBounds(50, 50, 720, 950);
        setResizable(false);

        shoppingCardPanel.setLayout(new BoxLayout(shoppingCardPanel, BoxLayout.Y_AXIS));

        firstBagelJlabel.setText(GetDBVallues.getDBArtName(1));
        secondBagelJlabel.setText(GetDBVallues.getDBArtName(2));
        thirdBagelJlabel.setText(GetDBVallues.getDBArtName(3));

        firstBagelPriceJlabel.setText(GetDBVallues.getDBArtPreisString(1));
        secondBagelPriceJlabel.setText(GetDBVallues.getDBArtPreisString(2));
        thirdBagelPriceJlabel.setText(GetDBVallues.getDBArtPreisString(3));


        buttonOK.addActionListener(e -> {
            try {
                onOK();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        addToCardButtonOne.addActionListener(e -> AddToCard(1));
        addToCardButtonTwo.addActionListener(e -> AddToCard(2));
        addToCardButtonThree.addActionListener(e -> AddToCard(3));
        applyButton.addActionListener(e -> CheckIfDiscount());
    }

    private void onOK() throws IOException {
        OrderCalc.Order();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void AddToCard(int artId) {

        switch (artId) {
            case 1 -> UpdateCard(1);
            case 2 -> UpdateCard(2);
            case 3 -> UpdateCard(3);
        }
    }

    private boolean CheckIfItemInCard(int artNr) {
        return cartItemList[artNr - 1] != null;
    }

    private void CheckIfDiscount(){
        String code = discountCodeTextField.getText();
        OrderCalc.CheckIfCodeExist(code);
        UpdatePrise();

    }
    private void UpdatePrise(){
        totalPriceLabel.setText(String.valueOf(OrderCalc.GetTotalValue()));
    }
    private void UpdateCard(int artNr) {
        if (CheckIfItemInCard(artNr)) {
            OrderCalc.AddToCounter(artNr);
            cartItemList[artNr-1].setText(String.valueOf(OrderCalc.GetCounterValue(artNr)));

            UpdatePrise();

            shoppingCardPanel.validate();
            shoppingCardPanel.repaint();


        } else {
            OrderCalc.AddToCounter(artNr);
            shoppingCardPanel.add(CreateArtikelPanel(artNr));

            UpdatePrise();

            shoppingCardPanel.validate();
            shoppingCardPanel.repaint();
        }
    }

    private void removeThisFromCard(JPanel jPanel, int artNr) {
        if (CheckIfItemInCard(artNr) && OrderCalc.GetCounterValue(artNr) > 1) {
            OrderCalc.RemoveFromCounter(artNr);
            cartItemList[artNr-1].setText(String.valueOf(OrderCalc.GetCounterValue(artNr)));

            UpdatePrise();

            shoppingCardPanel.validate();
            shoppingCardPanel.repaint();
        } else if (CheckIfItemInCard(artNr) && OrderCalc.GetCounterValue(artNr) <= 1) {
            OrderCalc.RemoveFromCounter(artNr);
            cartItemList[artNr-1] = null;
            shoppingCardPanel.remove(jPanel);

            UpdatePrise();

            shoppingCardPanel.validate();
            shoppingCardPanel.repaint();
        }
    }

    private JPanel CreateArtikelPanel(int artNr) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel artNameLabel = new JLabel(GetDBVallues.getDBArtName(artNr));
        JLabel artPreisLabel = new JLabel(GetDBVallues.getDBArtPreisString(artNr));
        JLabel artAmmountLabel = new JLabel(String.valueOf(OrderCalc.GetCounterValue(artNr)));
        JButton artRemoveButton = new JButton("Remove");


        panel.add(artAmmountLabel);
        panel.add(artNameLabel);
        panel.add(artPreisLabel);
        panel.add(artRemoveButton);

        artRemoveButton.addActionListener(e -> removeThisFromCard(panel,artNr));

        cartItemList[artNr-1] = artAmmountLabel;
        return panel;
    }


    private void createUIComponents() throws IOException {
        URL urlBild1 = new URL(GetDBVallues.GetDBArtBild(1));
        URL urlBild2 = new URL(GetDBVallues.GetDBArtBild(2));
        URL urlBild3 = new URL(GetDBVallues.GetDBArtBild(3));
        Image image1 = ImageIO.read(urlBild1);
        Image image2 = ImageIO.read(urlBild2);
        Image image3 = ImageIO.read(urlBild3);


        imgBigBoy = new JLabel( new ImageIcon(new ImageIcon(image1).getImage().getScaledInstance(150,200, Image.SCALE_SMOOTH)));
        imgVegiBoy = new JLabel( new ImageIcon(new ImageIcon(image2).getImage().getScaledInstance(150,200, Image.SCALE_SMOOTH)));
        imgSugarBoy = new JLabel(new ImageIcon(new ImageIcon(image3).getImage().getScaledInstance(150,200, Image.SCALE_SMOOTH)));
    }
}
