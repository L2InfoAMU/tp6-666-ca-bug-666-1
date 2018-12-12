package model;

import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * {@link Grid} instances represent the grid in <i>The Game of Life</i>.
 */
public class Grid implements Iterable<Cell> {

    private final int numberOfRows;
    private final int numberOfColumns;
    private final Cell[][] cells;

    /**
     * Creates a new {@code Grid} instance given the number of rows and columns.
     *
     * @param numberOfRows    the number of rows
     * @param numberOfColumns the number of columns
     * @throws IllegalArgumentException if {@code numberOfRows} or {@code numberOfColumns} are
     *                                  less than or equal to 0
     */
    public Grid(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.cells = createCells();
    }

    /**
     * Returns an iterator over the cells in this {@code Grid}.
     *
     * @return an iterator over the cells in this {@code Grid}
     */

    @Override
    public Iterator<Cell> iterator() {
        return new GridIterator(this);
    }

    private Cell[][] createCells() {
        Cell[][] cells = new Cell[getNumberOfRows()][getNumberOfColumns()];
        for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
                cells[rowIndex][columnIndex] = new Cell();
            }
        }
        return cells;
    }

    /**
     * Returns the {@link Cell} at the given index.
     *
     * <p>Note that the index is wrapped around so that a {@link Cell} is always returned.
     *
     * @param rowIndex    the row index of the {@link Cell}
     * @param columnIndex the column index of the {@link Cell}
     * @return the {@link Cell} at the given row and column index
     */
    public Cell getCell(int rowIndex, int columnIndex) {
        return cells[getWrappedRowIndex(rowIndex)][getWrappedColumnIndex(columnIndex)];
    }

    private int getWrappedRowIndex(int rowIndex) {
        return (rowIndex + getNumberOfRows()) % getNumberOfRows();
    }

    private int getWrappedColumnIndex(int columnIndex) {
        return (columnIndex + getNumberOfColumns()) % getNumberOfColumns();
    }

    /**
     * Returns the number of rows in this {@code Grid}.
     *
     * @return the number of rows in this {@code Grid}
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Returns the number of columns in this {@code Grid}.
     *
     * @return the number of columns in this {@code Grid}
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    //méthode getNeighbours; retourne une liste des 8 voisins de la cellule avec les coordonnées données en argument.

    private List<Cell> getNeighbours(int rowIndex, int columnIndex) {

        List<Cell> neighboursCells = null;

        for (int index1 = -1; index1 <=1 ; index1++) {
            for (int index2 = -1; index2 <= 1; index2++) {
                if(index1!=0 && index2!=0){
               neighboursCells.add(getCell(rowIndex+index1,columnIndex+index2));}
            }
        }
        return neighboursCells;
    }

    // La méthode countAliveNeighbours renvoie le nombre de cellules vivantes dans les cellules voisines de la cellule
    // représentée par les coordonnées données en argument
    private int countAliveNeighbours(int rowIndex, int columnIndex) {
        List<Cell> neighboursCells = getNeighbours(rowIndex,columnIndex);
        int counter = 0;
        for(Cell cell : neighboursCells){
            if(cell.isAlive()){
                counter++;
            }
        }
        return counter;
    }

    // la méthode calculateNextState() renvoie l'état suivant d'une cellule indiquée par les coordonnées données en argument
    // en fonction du nombre de cellules voisines vivantes.
    private CellState calculateNextState(int rowIndex, int columnIndex) {
        Cell actualCell = getCell(rowIndex,columnIndex);
        int aliveNeighbourCells = countAliveNeighbours(rowIndex,columnIndex);

        if(!actualCell.isAlive()){
            if(aliveNeighbourCells == 3){
                return CellState.ALIVE;
            }
            return CellState.DEAD;
        }

        if(actualCell.isAlive()){
            if(aliveNeighbourCells == 2 || aliveNeighbourCells == 3){
                return CellState.ALIVE;
            }
        }
        return CellState.DEAD;
    }



    // La méthode calculateNextStates() renvoie une matrice représentant les états suivants des cellules
    private CellState[][] calculateNextStates() {
        CellState[][] nextCellState = new CellState[getNumberOfRows()][getNumberOfColumns()];
        for (int index1 = 0; index1 < getNumberOfRows() ; index1++) {
            for (int index2 = 0; index2 < getNumberOfColumns() ; index2++) {
                nextCellState[index1][index2] = calculateNextState(index1, index2);
                }
            }
        return nextCellState;
    }

    //La méthode updtateStates met à jour les états des cellules de la grille en fonction de leur état suivant calculé.
    private void updateStates(CellState[][] nextState) {
        for (int index1 = 0; index1 < getNumberOfRows() ; index1++) {
            for (int index2 = 0; index2 < getNumberOfColumns() ; index2++) {
                getCell(index1,index2).setState(nextState[index1][index2]);
            }
        }
    }

    /**
     * Transitions all {@link Cell}s in this {@code Grid} to the next generation.
     *
     * <p>The following rules are applied:
     * <ul>
     * <li>Any live {@link Cell} with fewer than two live neighbours dies, i.e. underpopulation.</li>
     * <li>Any live {@link Cell} with two or three live neighbours lives on to the next
     * generation.</li>
     * <li>Any live {@link Cell} with more than three live neighbours dies, i.e. overpopulation.</li>
     * <li>Any dead {@link Cell} with exactly three live neighbours becomes a live cell, i.e.
     * reproduction.</li>
     * </ul>
     */
    // TODO: Écrire une version correcte de cette méthode.
    void updateToNextGeneration() {

    }

    /**
     * Sets all {@link Cell}s in this {@code Grid} as dead.
     */
    // TODO: Écrire une version correcte de cette méthode.
    void clear() {

    }

    /**
     * Goes through each {@link Cell} in this {@code Grid} and randomly sets its state as ALIVE or DEAD.
     *
     * @param random {@link Random} instance used to decide if each {@link Cell} is ALIVE or DEAD.
     * @throws NullPointerException if {@code random} is {@code null}.
     */
    // TODO: Écrire une version correcte de cette méthode.
    void randomGeneration(Random random) {

    }
}
