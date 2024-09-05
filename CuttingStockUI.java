import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import java.awt.*;


class Piece {
    int width, height;
    Color color; 

    private static final Map<String, Color> colorMap = new HashMap<>();
    private static final Color[] predefinedColors = {
        Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN
    }; 

    public Piece(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Piece dimensions must be positive.");
        }
        this.width = width;
        this.height = height;
        this.color = getColorForDimensions(width, height);
    }

    private static Color getColorForDimensions(int width, int height) {
        String key = width + "x" + height;
        if (!colorMap.containsKey(key)) {
            colorMap.put(key, predefinedColors[colorMap.size() % predefinedColors.length]);
        }
        return colorMap.get(key);
    }

    public int getArea() {
        return width * height;
    }

    public Color getColor() {
        return color;
    }
}


class Sheet {
    int width, height;
    int[][] occupied; 

    public Sheet(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Sheet dimensions must be positive.");
        }
        this.width = width;
        this.height = height;
        this.occupied = new int[height][width];
    }

    public boolean placePiece(Piece piece, int startX, int startY) {
        if (startX + piece.width > width || startY + piece.height > height) {
            return false; 
        }

        for (int y = 0; y < piece.height; y++) {
            for (int x = 0; x < piece.width; x++) {
                if (occupied[startY + y][startX + x] != 0) {
                    return false;
                }
            }
        }

        int pieceId = piece.hashCode();
        for (int y = 0; y < piece.height; y++) {
            for (int x = 0; x < piece.width; x++) {
                occupied[startY + y][startX + x] = pieceId;
                System.out.println(String.format("String at [%d][%d] is : %d", (startY + y),(startX + x),pieceId));
            }
        }
        return true;
    }
}

// Concrete logic for 2D Cutting stock problem
class CuttingStock {
    private Sheet sheet;
    private List<Piece> pieces;

    public CuttingStock(Sheet sheet, List<Piece> pieces) {
        this.sheet = sheet;
        this.pieces = pieces;
    }

    public void solve() {
        for (Piece piece : pieces) {
            if (piece.width <= 0 || piece.height <= 0) {
                System.err.println("Invalid piece dimensions: " + piece.width + "x" + piece.height);
                continue; // Skip invalid pieces
            }
        }

        // Sort pieces by area (largest first)
        pieces.sort((p1, p2) -> Integer.compare(p2.getArea(), p1.getArea()));

        // Track the placement status of each piece
        boolean[] placed = new boolean[pieces.size()];

        // Example placing strategy for demonstration
        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.get(i);
            boolean placedSuccessfully = false;
            for (int y = 0; y <= sheet.height - piece.height && !placedSuccessfully; y++) {
                for (int x = 0; x <= sheet.width - piece.width && !placedSuccessfully; x++) {
                    if (sheet.placePiece(piece, x, y)) {
                        placedSuccessfully = true;
                        placed[i] = true;
                    }
                }
            }

            if (!placedSuccessfully) {
                System.out.println("Piece with dimensions " + piece.width + "x" + piece.height + " could not be placed.");
            }
        }
    }
}

// Class to visualize the cutting stock problem
class CuttingStockUI extends JPanel {
    private Sheet sheet;
    private List<Piece> pieces; // Add a field for pieces
    private static final int CELL_SIZE = 50; // Increased cell size for better visualization

    public CuttingStockUI(Sheet sheet, List<Piece> pieces) {
        this.sheet = sheet;
        this.pieces = pieces; // Initialize pieces list
        setPreferredSize(new Dimension(sheet.width * CELL_SIZE, sheet.height * CELL_SIZE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < sheet.height; y++) {
            for (int x = 0; x < sheet.width; x++) {
                int pieceId = sheet.occupied[y][x];
                if (pieceId != 0) {
                    // Find the Piece object based on the ID
                    Piece piece = getPieceById(pieceId);
                    if (piece != null) {
                        g.setColor(piece.getColor());
                    } else {
                        g.setColor(Color.WHITE);
                    }
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    // Helper method to find Piece by ID
    private Piece getPieceById(int id) {
        for (Piece piece : pieces) {
            if (piece.hashCode() == id) {
                return piece;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Define sheet size
        

        // Define pieces to cut
        List<Piece> pieces = new ArrayList<>();

        Sheet sheet = new Sheet(6, 7);

        pieces.add(new Piece(2, 3)); 
        pieces.add(new Piece(2, 3)); 
        pieces.add(new Piece(1, 2)); 
        pieces.add(new Piece(1, 2)); 
        pieces.add(new Piece(1, 2)); 
        pieces.add(new Piece(1, 2)); 

        // pieces.add(new Piece(2, 2));
        // pieces.add(new Piece(2, 2));
        // pieces.add(new Piece(2, 4));

        // pieces.add(new Piece(2, 3)); 
        // pieces.add(new Piece(3, 2)); 
        // pieces.add(new Piece(1, 7)); 
        // pieces.add(new Piece(2, 7)); 
        // pieces.add(new Piece(6, 1)); 

        // pieces.add(new Piece(2, 3));
        // pieces.add(new Piece(4, 2));
        // pieces.add(new Piece(1, 2));
        // pieces.add(new Piece(1, 5));
        // pieces.add(new Piece(2, 7));
        // pieces.add(new Piece(1, 7));





        // Solve the cutting stock problem
        CuttingStock cuttingStock = new CuttingStock(sheet, pieces);
        cuttingStock.solve();

        // Visualize the solution
        JFrame frame = new JFrame("2D Cutting Stock Problem");
        CuttingStockUI ui = new CuttingStockUI(sheet, pieces); // Pass pieces to UI
        frame.add(ui);
        frame.pack(); // Use pack to fit the JFrame to the content
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
