import controller.EstoqueController;
import view.EstoqueView;

public class Main {
    public static void main(String[] args) {
        EstoqueController controller = new EstoqueController();
        EstoqueView view = new EstoqueView(controller);
        view.setVisible(true);
    }
}
