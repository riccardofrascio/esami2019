package a01b.e1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.IntStream;

public class GridImpl<E> implements Grid<E> {

    private Map<Pair<Integer,Integer>, E> cels = new HashMap<>();
    private int rows;
    private int cols;

    public GridImpl(int nRows, int nCols) {
        super();
        this.rows=nRows;
        this.cols=nCols;
    }

    @Override
    public int getRows() {
        return this.rows;
    }

    @Override
    public int getColumns() {
        return this.cols;
    }

    @Override
    public E getValue(int row, int column) {
        return this.cels.get(new Pair<>(row, column));
    }

    @Override
    public void setColumn(int column, E value) {
        for(int i=0; i<this.rows; i++) {
            cels.put(new Pair<>(i, column), value);
        }
    }

    @Override
    public void setRow(int row, E value) {
        for(int i=0; i<this.cols; i++) {
            this.cels.put(new Pair<>(row, i), value);
        }
        
    }

    @Override
    public void setBorder(E value) {
        setColumn(0, value);
        setColumn(this.cols-1, value);
        setRow(0, value);
        setRow(this.rows-1, value);
    }

    @Override
    public void setDiagonal(E value) {
        for(int i= 0; (i<this.rows && i<this.cols); i++) {
            cels.put(new Pair<>(i, i), value);
        }
        
    }

    @Override
    public Iterator<Cell<E>> iterator(boolean onlyNonNull) {
        return IntStream.range(0,this.rows)
						.boxed()
				        .flatMap(r-> IntStream.range(0,this.cols).boxed().map(c->new Pair<>(r,c)))
				        .map(p -> new Cell<>(p.getX(),p.getY(),cels.get(p)))
				        .filter(p -> !onlyNonNull || p.getValue()!=null)
				        .iterator();
    }
    
}
