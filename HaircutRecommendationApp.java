import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class HaircutRecommendationApp extends JFrame {
    private JComboBox<String> faceShapeComboBox;
    private JComboBox<String> hairTypeComboBox;

    public HaircutRecommendationApp() {
        initialize();
    }

    private void initialize() {
        setTitle("Haircut Recommendation App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 800);
        getContentPane().setBackground(Color.BLACK);

        setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20));

        Font newFont = new Font("Times New Roman", Font.PLAIN, 15);

        add(createLabel("Select Face Shape:", newFont));
        faceShapeComboBox = createRoundedComboBox(new String[]{"Round", "Oval", "Square", "Heart", "Diamond", "Pear", "Oblong"});
        faceShapeComboBox.setFont(newFont);
        add(faceShapeComboBox);

        JPanel hairTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        hairTypePanel.setBackground(Color.BLACK);
        hairTypePanel.add(createLabel("Select Hair Type:", newFont));
        hairTypeComboBox = createRoundedComboBox(new String[]{"Straight", "Wavy", "Curly"});
        hairTypeComboBox.setFont(newFont);
        hairTypePanel.add(hairTypeComboBox);

        add(hairTypePanel);

        JButton btnRecommendHaircut = new RoundedCornerButton("Recommend Haircut");
        btnRecommendHaircut.addActionListener(e -> recommendHaircut());

        btnRecommendHaircut.setBackground(Color.WHITE);
        btnRecommendHaircut.setForeground(Color.BLACK);
        btnRecommendHaircut.setFont(newFont);
        add(btnRecommendHaircut);
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(font);
        return label;
    }
private JComboBox<String> createRoundedComboBox(String[] options) {
    JComboBox<String> comboBox = new JComboBox<>(options);
    comboBox.setUI(new BasicComboBoxUI() {
        @Override
        protected JButton createArrowButton() {
            return new BasicArrowButton(BasicArrowButton.SOUTH) {
                @Override
                public void paintTriangle(Graphics g, int x, int y, int size, int direction, boolean isEnabled) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(isEnabled ? Color.BLACK : Color.GRAY);

                    int mid = size / 2;

                    if (direction == NORTH) {
                        g2.drawLine(x, y + size, x + mid, y);
                        g2.drawLine(x + mid, y, x + size, y + size);
                        g2.drawLine(x, y + size, x + size, y + size);
                    } else if (direction == SOUTH) {
                        g2.drawLine(x, y, x + mid, y + size);
                        g2.drawLine(x + mid, y + size, x + size, y);
                        g2.drawLine(x, y, x + size, y);
                    }
                    g2.dispose();
                }
            };
        }
    });

    // Set a rounded border with white color
    comboBox.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
    
    comboBox.setRenderer(new CustomRoundedComboBoxRenderer());
    comboBox.setBackground(Color.WHITE);
    comboBox.setForeground(Color.BLACK);
    return comboBox;
}

      private void recommendHaircut() {
        String selectedFaceShape = (String) faceShapeComboBox.getSelectedItem();
        String selectedHairType = (String) hairTypeComboBox.getSelectedItem();
        String recommendation = getHaircutRecommendation(selectedFaceShape, selectedHairType);

        JOptionPane.showMessageDialog(this, "Recommended Haircut for " + selectedFaceShape +
                " face shape and " + selectedHairType + " hair type: " + recommendation);
    }

    private String getHaircutRecommendation(String faceShape, String hairType) {
      switch (faceShape) {
            case "Round":
                switch (hairType) {
                    case "Straight":
                        return "Layered cut with volume on top";
                    case "Wavy":
                        return "Textured layers to add movement";
                    case "Curly":
                        return "Curly bob for a playful look";
                }
                break;
            case "Oval":
                switch (hairType) {
                    case "Straight":
                        return "Versatile styles, such as bob or pixie";
                    case "Wavy":
                        return "Soft waves for an elegant look";
                    case "Curly":
                        return "Curly shag for a trendy appearance";
                }
                break;
            case "Square":
                switch (hairType) {
                    case "Straight":
                        return "Soft, textured layers to soften the angles";
                    case "Wavy":
                        return "Beachy waves to add softness";
                    case "Curly":
                        return "Long, curly layers for a romantic feel";
                }
                break;
            case "Heart":
                switch (hairType) {
                    case "Straight":
                        return "Side-swept bangs and layers";
                    case "Wavy":
                        return "Bob with soft waves";
                    case "Curly":
                        return "Curly pixie cut for a bold look";
                }
                break;
               case "Diamond":
            switch (hairType) {
                case "Straight":
                    return "Soft layers to complement the angular features";
                case "Wavy":
                    return "Side-swept bangs and textured waves";
                case "Curly":
                    return "Loose curls for an elegant and balanced look";
            }
            break;
        case "Pear":
            switch (hairType) {
                case "Straight":
                    return "Long, sleek layers to balance the narrow forehead";
                case "Wavy":
                    return "Chin-length bob with soft waves for a flattering look";
                case "Curly":
                    return "Curly layers starting below the chin for volume";
            }
            break;
        case "Oblong":
            switch (hairType) {
                case "Straight":
                    return "Long layers to add softness and break up the length";
                case "Wavy":
                    return "Shoulder-length waves for a balanced appearance";
                case "Curly":
                    return "Curly shoulder-length cut for a playful vibe";
            }
            break;
    }

    return "No specific recommendation available";
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                HaircutRecommendationApp window = new HaircutRecommendationApp();
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    class CustomRoundedComboBoxRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            setBorder(null);
            setForeground(Color.BLACK);

            return this;
        }
    }

    class RoundedCornerButton extends JButton {
        private int radius = 15;

        public RoundedCornerButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setBorderPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            super.paintComponent(g2d);
            g2d.dispose();
        }
    }
}