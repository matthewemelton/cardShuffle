import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class cardshuffle extends JFrame implements ActionListener{
    // private array of buttons and images
    private JButton[] cards;
    private Icon[] cardImages;

    // buttons for the 'buttonPanel'
    private JButton shuffleButton;
    private JButton resetButton;
    private JButton quitButton;

    // objects for the layout
    private JPanel cardPanel;
    private JPanel buttonPanel;
    private Container c;

    public cardshuffle(){
        super("Card Shuffle Simulator");

        this.setSize(1500, 600);
        c = getContentPane();

        //set up the three buttons

        shuffleButton = new JButton("Shuffle");
        shuffleButton.setBackground(Color.orange);
        shuffleButton.setForeground(Color.black);
        shuffleButton.setOpaque(false);
        shuffleButton.addActionListener(this);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        resetButton.setBackground(Color.PINK);
        resetButton.setForeground(Color.black);
        resetButton.setOpaque(false);

        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);
        quitButton.setBackground(Color.black);
        quitButton.setForeground(Color.RED);
        quitButton.setOpaque(false);

        cardPanel = new JPanel();
        buttonPanel = new JPanel();

        buttonPanel.add(shuffleButton);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(resetButton);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(quitButton);

        //set up the cardImage vector
        cardImages = new ImageIcon[52];
        cards = new JButton[52];

        //set up the grid layout
        GridLayout cardGridLayout = new GridLayout(4, 13);
        cardPanel.setLayout(cardGridLayout);

        // call the helper function to create the deck
        createDeck();

        // once the button has been created add it to the cardGrid
        for(int i=0; i<52; i++)
            cardPanel.add(cards[i]);

        c.add(cardPanel, BorderLayout.NORTH);
        c.add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }


    public void actionPerformed (ActionEvent e)
    {
        // grab the source of where the mouse was clicked and then execute some logic based on that
        if(e.getSource() == quitButton){
            System.exit(0);
        }
        else if(e.getSource() == resetButton){
            resetDeck();
            repaint();
        }
        else if(e.getSource() == shuffleButton){
            shuffleCards(cards);
        }
    }

    public static void main(String[] args) {
        cardshuffle app = new cardshuffle();
        app.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
    }

    //helper function to shuffle the cards
    public static void shuffleCards(JButton[] cards){
        Icon tempIcon = new ImageIcon();
        int randomIndex;
        for(int i=0; i<52; i++) {
            tempIcon = cards[i].getIcon();
            randomIndex = (int) (Math.random() * 51);
            cards[i].setIcon(cards[randomIndex].getIcon());
            cards[randomIndex].setIcon(tempIcon);
        }
    }

    private void createDeck() {
        // add the 52 card Images, allocate memory for the buttons and assign the icons
        for(int i=0; i<52; i++) {
            //cardImages.add(new ImageIcon("cardImages/card" + i + ".png"));
            ImageIcon icon = new ImageIcon("cardImages/cards" + i + ".png");
            Image newImage = icon.getImage().getScaledInstance(55, 75, Image.SCALE_SMOOTH); // was (85,105)
            ImageIcon newIcon = new ImageIcon(newImage);
            cardImages[i] = newIcon;
            cards[i] = new JButton(newIcon);
            cards[i].setSize(55,75); // was (75, 100)
            cards[i].revalidate();
        }
    }

    private void resetDeck() {
        for(int i=0; i<52; i++){
            cards[i].setIcon(cardImages[i]);
        }
    }
}
