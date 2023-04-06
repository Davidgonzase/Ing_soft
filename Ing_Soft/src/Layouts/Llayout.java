package Layouts;
import java.awt.*;

public class Llayout extends Layouts{
    @Override
    public void addLayoutComponent(String name, Component comp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addLayoutComponent'");
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeLayoutComponent'");
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return new Dimension(300,520);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(50,50);
    }

    @Override
    public void layoutContainer(Container parent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'layoutContainer'");
    }
}