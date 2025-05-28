package ch.supsi.frontend;

import ch.supsi.backend.application.cell.CellActionApplication;
import ch.supsi.backend.application.game.GameBoardApplication;
import ch.supsi.backend.application.game.GameBombApplication;
import ch.supsi.backend.business.mine.BombPlacer;
import ch.supsi.backend.business.mine.MinePlacementStrategy;
import ch.supsi.backend.business.mine.MineRevealer;
import ch.supsi.backend.business.model.AbstractModel;
import ch.supsi.frontend.controller.GameController;
import ch.supsi.frontend.controller.GameEventHandler;
import ch.supsi.frontend.controller.PlayerEventHandler;
import ch.supsi.frontend.model.*;
import ch.supsi.frontend.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class MainFx extends Application {

    public static final String APP_TITLE = "mine sweeper";

    private final AbstractModel gameModel;
    private final ControlledFxView menuBarView;
    private final ControlledFxView gameBoardView;
    //private final UncontrolledFxView welcomeView;
    private final UncontrolledFxView userFeedbackView;
    private final GameEventHandler gameEventHandler;
    private final PlayerEventHandler playerEventHandler;
    private BorderPane mainBorderPane;

    public MainFx() {
        // GAME MODEL
        GameBoardApplication gameBoardApplication = new GameBoardApplication();
        gameBoardApplication.setDimensions(9);
        MinePlacementStrategy bombPlacer = new BombPlacer(gameBoardApplication);
        CellActionApplication mineRevealer = new MineRevealer(gameBoardApplication);
        GameBombApplication gameBombApplication = new GameBombApplication();
        gameBombApplication.setMinBomb(1);
        gameBombApplication.setMaxBomb(80);

        this.gameModel = GameModel.getInstance(bombPlacer, mineRevealer, gameBombApplication, gameBoardApplication);

        // VIEWS
        this.menuBarView = MenuBarViewFxml.getInstance();
        this.gameBoardView = GameBoardViewFxml.getInstance();
        this.userFeedbackView = UserFeedbackViewFxml.getInstance();
        //this.welcomeView = WelcomeViewFxml.getInstance();

        // CONTROLLERS
        //TODO casting corretto? - Opzione: dichiarare gameModel col tipo specifico (e non AbstractModel)
        GameController controller = GameController.getInstance((GameModel) gameModel);
        this.gameEventHandler = controller;
        this.playerEventHandler = controller;

        // SCAFFOLDING of M-V-C
        this.menuBarView.initialize(this.gameEventHandler, this.gameModel);
        this.gameBoardView.initialize(this.playerEventHandler, this.gameModel);
        this.userFeedbackView.initialize(this.gameModel);
        //this.welcomeView.initialize(this.gameModel);
        //GameController.getInstance().setMainFx(this);
        controller.initialize(List.of(this.menuBarView, this.gameBoardView, this.userFeedbackView));

    }

//    public  void switchGameBoard() {
//        mainBorderPane.setCenter(this.gameBoardView.getNode());
//    }

    @Override
    public void start(Stage primaryStage) {
//        Parent root = FXMLLoader.load(getClass().getResource("/view/WelcomeView.fxml"));
//        primaryStage.setTitle("Welcome Page");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();

        // handle the main window close request
        // in real life, this event should not be dealt with here!
        // it should actually be delegated to a suitable ExitController!
        primaryStage.setOnCloseRequest(
                windowEvent -> {
                    // consume the window event (the main window would be closed otherwise no matter what)
                    windowEvent.consume();

                    // quit the app
                    // replace this hard close
                    // by delegating the work to a suitable controller
                    primaryStage.close();
                }
        );

        // SCAFFOLDING OF MAIN PANE
        mainBorderPane = new BorderPane();
        mainBorderPane.setTop(this.menuBarView.getNode());
        mainBorderPane.setCenter(this.gameBoardView.getNode());
        mainBorderPane.setBottom(this.userFeedbackView.getNode());

        // SCENE
        Scene scene = new Scene(mainBorderPane);

        // PRIMARY STAGE
        primaryStage.setTitle(MainFx.APP_TITLE);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.toFront();
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
